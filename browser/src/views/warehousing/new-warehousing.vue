<template>
    <div class="login-container">
        <div class="app-container">
            <el-form ref="form" label-width="120px" :model="form">
                <el-form-item label="仓库地址">
                    <el-col :span="12">
                        <el-input v-model="form.target" disabled></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="承运信息">
                    <el-col :span="12">
                        <el-form-item label="单号">
                            <el-input v-model="form.warehousingNo" v-bind:disabled="onUpdate" placeholder="请点击按钮获取单号">
                                <el-button slot="append" v-bind:disabled="onUpdate" @click="getOrdNo">获取单号</el-button>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="头程方式">
                            <el-select v-model="form.method" placeholder="请选择头程方式">
                                <el-option label="东岳头程" value="1"></el-option>
                                <el-option label="其他头程" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12" v-if="form.method==='2'">
                        <el-form-item label="头程承运人">
                            <el-input v-model="form.carrier" placeholder="请输入承运人"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" v-if="form.method==='2'">
                        <el-form-item label="承运人追踪号">
                            <el-input v-model="form.trackno" placeholder="请输入承运人追踪号"></el-input>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="头程渠道">
                            <el-select v-model="form.channel" placeholder="请选择头程渠道">
                                <el-option v-for="item in channels" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="运输方式">
                            <el-select v-model="form.delivermethod" placeholder="请选择运输方式">
                                <el-option label="海运整柜20GP/HQ" value="1"></el-option>
                                <el-option label="海运整柜40GP/HQ" value="2"></el-option>
                                <el-option label="海运散货" value="3"></el-option>
                                <el-option label="空运" value="4"></el-option>
                                <el-option label="卡车派送" value="5"></el-option>
                                <el-option label="其他" value="6"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="关税相关">
                    <el-col :span="12">
                        <el-form-item label="报关类型">
                            <el-radio v-model="form.clearanceType" label="1">单独退税报关</el-radio>
                            <el-radio v-model="form.clearanceType" label="2">无退税报关</el-radio>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="关税类型">
                            <el-radio v-model="form.taxType" label="1">预缴关税</el-radio>
                            <el-radio v-model="form.taxType" label="2">实报实销</el-radio>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="保险服务">
                            <el-switch v-model="form.insurance" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12" v-if="form.insurance">
                        <el-form-item label="保险总值(JPY)">
                            <el-input-number v-model="form.insuranceNum"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="预计到港时间">
                            <el-date-picker v-model="estimatedDate" type="date" placeholder="选择日期"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="入库商品">
                    <el-col :span="5">
                        <el-form-item label="sku">
                            <el-select v-model="currContent.sku" placeholder="请从已审核产品中选择">
                                <el-option v-for="item in products" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="名称">
                            <el-input v-model="currContent.name" placeholder="商品名称" disabled></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="数量">
                            <el-input-number v-model="currContent.totalNum"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="包装方式">
                            <el-select v-model="currContent.wrapType">
                                <el-option :key="自带包装" :label="自带包装" :value="自带包装"></el-option>
                                <el-option :key="非自带包装" :label="非自带包装" :value="非自带包装"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="3">
                        <el-button type="primary" @click="add2Cart">添加</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>

    import request from '@/utils/request'

    export default {
        name: "new-warehousing",
        data() {
            return {
                onUpdate: false,
                onCreate: true,
                dialogImageUrl: '',
                dialogVisible: false,
                form: {
                    target: "岡山县岡山市中区新京橋3丁目4-26",
                    warehousingNo: "",
                    method: '1',
                    carrier: '',
                    trackno: '',
                    delivermethod: "",
                    clearanceType: "",
                    taxType: "",
                    insurance: false,
                    insuranceNum: 0,
                    estimatedDate: null,
                    contentList: []
                },
                currContent: {
                    warehousingNo: "",
                    sku: "",
                    dySku: "",
                    name: "",
                    boxSeq: "",
                    totalNum: "",
                    wrapType: "",
                },
                contentMap: {},
                checkRules: {},
                channels: [],
                products: []
            };
        },
        created() {
            // this.initPage();
        },
        methods: {
            initPage() {
                let sku = this.$route.query.sku;
                if (sku !== undefined && sku.length > 0) {
                    request({
                        url: "/product/get/" + sku,
                        method: "get"
                    }).then(res => {
                        this.form = res.data.data;
                        this.onCreate = false;
                        this.onUpdate = true;
                        if (res.data.data.img1 !== null && res.data.data.img1.length > 0) {
                            let tmp = {
                                "name": "img1",
                                "index": "1",
                                "url": "http://47.105.107.242:8888/api/v1/img/" + res.data.data.img1,
                                "uid": res.data.data.img1
                            };
                            this.fileList.push(tmp)
                        }
                        if (res.data.data.img2 !== null && res.data.data.img2.length > 0) {
                            let tmp = {
                                "name": "img2",
                                "index": "2",
                                "url": "http://47.105.107.242:8888/api/v1/img/" + res.data.data.img2,
                                "uid": res.data.data.img2
                            };
                            this.fileList.push(tmp)
                        }
                        if (res.data.data.img3 !== null && res.data.data.img3.length > 0) {
                            let tmp = {
                                "name": "img3",
                                "index": "3",
                                "url": "http://47.105.107.242:8888/api/v1/img/" + res.data.data.img3,
                                "uid": res.data.data.img3
                            };
                            this.fileList.push(tmp)
                        }
                    })
                }
            },
            getOrdNo() {
                request({
                    url: "/generate/pk",
                    method: 'get'
                }).then(res => {
                    this.form.warehousingNo = res.data.data;
                })
            },
            add2Cart() {
                request({
                    url: "/product/img/drop/" + file.uid + "/" + this.form.sku + "/" + file.index,
                    method: "get"
                }).then(() => {
                    this.$message.success("成功删除关联图片");
                })
            }
        }
    }
</script>

