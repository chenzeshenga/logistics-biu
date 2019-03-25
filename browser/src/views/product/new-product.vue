<template>
    <div class="login-container">
        <div class="app-container">
            <el-form ref="form" :rules="checkRules" :model="form" label-width="120px">
                <el-form-item label="商品信息">
                    <el-col :span="12">
                        <el-form-item label="sku" prop="sku">
                            <el-input v-model="form.sku" v-bind:disabled="onUpdate" placeholder="请输入或者扫描商品sku">
                                <el-button slot="append" v-bind:disabled="onUpdate" @click="getDySku">获取唯一sku
                                </el-button>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="商品名称" prop="productName">
                            <el-input v-model="form.productName" placeholder="请输入商品名称"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="商品类型" prop="category">
                            <el-select v-model="form.category" placeholder="请选择商品类型">
                                <el-option value="1" label="小物"/>
                                <el-option value="2" label="服装"/>
                                <el-option value="3" label="户外运动"/>
                                <el-option value="4" label="电子产品"/>
                                <el-option value="5" label="家居用品"/>
                                <el-option value="6" label="玩具"/>
                                <el-option value="7" label="大件"/>
                                <el-option value="8" label="其他"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="商品颜色">
                            <el-input v-model="form.color" placeholder="请输入商品颜色"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="商品价格(JPY)" prop="price">
                            <el-input-number v-model="form.price"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="商品尺寸">
                            <el-input v-model="form.size" placeholder="请输入商品尺寸"></el-input>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="商品尺寸">
                    <el-col :span="8">
                        <el-form-item label="长(cm)">
                            <el-input-number v-model="form.length"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="宽(cm)">
                            <el-input-number v-model="form.width"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="高(cm)">
                            <el-input-number v-model="form.height"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="8">
                        <el-form-item label="重量(kg)">
                            <el-input-number v-model="form.weight"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="商品图片">
                    <el-upload action="http://localhost:8888/api/v1/product/img/put" with-credentials multiple
                               list-type="picture"
                               :file-list="fileList" :on-preview="handlePictureCardPreview" :on-remove="handleRemove"
                               :data="form" :on-change="handleFileChange"
                               ref="upload" :on-error="handleError" :limit="3">
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器
                        </el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2MB</div>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                </el-form-item>
                <el-form-item>
                    <el-col :offset="18">
                        <el-button type="primary" @click="createByFile" v-if="onCreate">批量创建</el-button>
                        <el-button type="primary" @click="submitForm('form')" v-if="onCreate">立即创建</el-button>
                        <el-button type="primary" @click="updateForm()" v-if="onUpdate">立即更新</el-button>
                        <el-button @click="resetForm('form')">重置</el-button>
                    </el-col>
                </el-form-item>
            </el-form>
            <el-dialog title="批量创建" :visible.sync="dialogVisible4Excel" width="30%">
                <el-form :model="form">
                    <el-form-item label="商品文件">
                        <el-upload action="http://localhost:8888/api/v1/ord/excel" with-credentials :on-error="handleError" :limit="1">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload4Excel">上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传excel文件(xls/xlsx)，记录条数小于200条</div>
                        </el-upload>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible4Excel = false">取 消</el-button>
                    <el-button type="primary" @click="uploadExcel">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "new-product",
        data() {
            return {
                onUpdate: false,
                onCreate: true,
                dialogImageUrl: '',
                dialogVisible: false,
                form: {
                    sku: '',
                    productName: '',
                    category: '8',
                    color: '',
                    price: 0,
                    size: '',
                    length: 0,
                    width: 0,
                    height: 0,
                    weight: 0,
                },
                checkRules: {
                    sku: [
                        {required: true, message: "请输入sku", trigger: 'blur'},
                        {min: 3, max: 20, message: "sku长度在3-20之间", trigger: 'blur'},
                    ],
                    productName: [
                        {required: true, message: "请输入商品名称", trigger: 'change'}
                    ],
                    category: [
                        {required: true, message: "请选择商品类型", trigger: 'change'}
                    ],
                    price: [
                        {required: true, message: "请输入商品价格", trigger: 'change'}
                    ]
                },
                fileList: [],
                dialogVisible4Excel: false
            };
        },
        created() {
            this.initPage();
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
            handleRemove(file, fileList) {
                request({
                    url: "/product/img/drop/" + file.uid + "/" + this.form.sku + "/" + file.index,
                    method: "get"
                }).then(() => {
                    this.$message.success("成功删除关联图片");
                })
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            getDySku() {
                request({
                    url: "/generate/sku",
                    method: "get"
                }).then(res => {
                    this.form.sku = res.data.data;
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        request({
                            url: "/product/add",
                            method: "post",
                            data: this.form
                        }).then(res => {
                            console.log(res);
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            submitUpload4Excel() {
                this.$refs.upload.submit();
            },
            handleError(err, file, fileList) {
                this.$message.error(JSON.parse(err.message)["message"]);
            },
            updateForm() {
                request({
                    url: "/product/update",
                    method: "post",
                    data: this.form
                }).then(() => {
                    this.$message.success("更新成功");
                    this.$router.push({path: '/product/status0'});
                })
            },
            handleFileChange(file, fileList) {
                file.index = fileList.length;
            },
            createByFile() {
                this.dialogVisible4Excel = true;
            },
            uploadExcel() {

            }
        }
    }
</script>
