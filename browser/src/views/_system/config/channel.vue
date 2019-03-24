<template>
    <div class="login-container">
        <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                  highlight-current-row>
            <el-table-column width="160" prop="channelCode" label="渠道编码"></el-table-column>
            <el-table-column width="200" prop="channelName" label="渠道名称"></el-table-column>
            <el-table-column width="200" prop="active" label="是否激活"></el-table-column>
            <el-table-column width="100" prop="checkedRules" label="规则"></el-table-column>
            <el-table-column width="150" prop="min" label="最短运输天数"></el-table-column>
            <el-table-column width="170" prop="max" label="最长运输天数"></el-table-column>
            <el-table-column width="250" prop="partner" label="partner"></el-table-column>
            <el-table-column width="150" prop="fromName" label="合作方"></el-table-column>
            <el-table-column width="170" prop="createOn" label="创建时间"></el-table-column>
            <el-table-column width="170" prop="updateOn" label="修改时间"></el-table-column>
            <el-table-column width="150" prop="createBy" label="创建人"></el-table-column>
            <el-table-column width="150" prop="updateBy" label="修改人"></el-table-column>
            <el-table-column label="操作" width="300" fixed="right">
                <template slot-scope="scope">
                    <el-tooltip content="编辑" placement="top">
                        <el-button @click="update(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-edit" circle
                                   plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button @click="handleDelete(scope.$index,scope.row)" size="small" type="danger" icon="el-icon-delete" circle
                                   plain></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="margin-left: 65%;margin-top: 10px" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="tablePage.current"
                       :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size" layout="total, sizes, prev, pager, next, jumper"
                       :total="tablePage.total">
        </el-pagination>
        <el-dialog title="渠道修改" :visible.sync="dialogVisible" width="55%">
            <div class="app-container">
                <el-form ref="form" :rules="checkRules" :model="form" label-width="120px">
                    <el-form-item label="渠道基本信息">
                        <el-col :span="12">
                            <el-form-item label="渠道名称">
                                <el-input v-model="form.channelName" placeholder="请输入渠道名称"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="渠道适用">
                                <el-select v-model="form.adapter">
                                    <el-option label="头程" value="1"></el-option>
                                    <el-option label="单票单请" value="2"></el-option>
                                    <el-option label="虚拟海外仓" value="3"></el-option>
                                    <el-option label="海外仓代发" value="4"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="12">
                            <el-form-item label="渠道合作方">
                                <el-select v-model="form.partner">
                                    <el-option label="日本邮政" value="1"></el-option>
                                    <el-option label="佐川急便" value="2"></el-option>
                                    <el-option label="黑猫运输" value="3"></el-option>
                                    <el-option label="西浓运输" value="4"></el-option>
                                    <el-option label="卡车派送" value="5"></el-option>
                                    <el-option label="其他" value="6"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :span="12">
                            <el-form-item label="最小运输天数">
                                <el-input-number v-model="form.min"></el-input-number>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="最大运输天数">
                                <el-input-number v-model="form.max"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="费用相关信息">
                        <el-col :span="12">
                            <el-form-item label="计算规则">
                                <el-select v-model="form.rule">
                                    <el-option label="人工输入" value="1"></el-option>
                                    <el-option label="后台计算之后人工确认" value="2"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col style="margin-top: 10px;margin-left: 20px">
                            <el-form-item>
                                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                                <el-checkbox-group v-model="form.checkedRules" @change="handleCheckedChange">
                                    <el-checkbox v-for="rule in rules" :key="rule.label" :label="rule.value">{{rule.label}}
                                    </el-checkbox>
                                </el-checkbox-group>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="是否激活">
                        <el-switch
                            v-model="form.active"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                        </el-switch>
                    </el-form-item>
                    <el-form-item label="备注">
                        <el-col>
                            <el-form-item>
                                <el-input v-model="form.comments" placeholder="备注"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-col :offset="22">
                            <el-button type="primary" @click="submitForm()" v-if="onCreate">立即创建</el-button>
                            <el-button type="primary" @click="updateForm()" v-if="onUpdate">立即更新</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>

    const rules = [
        {label: "是否支持多包裹", value: "whetherMultiPackage"},
        {label: "是否需要体积", value: "whetherVolume"},
        {label: "是否支持保险", value: "whetherInsurance"},
        {label: "是否支持代收费用", value: "whetherChargeForThem"},
        {label: "是否立即扣费", value: "whetherChargeAtFirst"},
    ];

    import request from '@/utils/request'

    export default {
        name: "channel",
        data() {
            return {
                tablePage: {
                    current: 1,
                    pages: null,
                    size: null,
                    total: null
                },
                tableLoading: false,
                tableData: [],
                onCreate: false,
                onUpdate: true,
                form: {
                    channelName: "",
                    adapter: "",
                    partner: "",
                    min: "",
                    max: "",
                    checkedRules: [],
                    active: false,
                    comments: "",
                },
                rules: rules,
                checkRules: {},
                isIndeterminate: true,
                checkAll: false,
                dialogVisible: false
            }
        },
        created() {
            this.fetch();
        },
        methods: {
            fetch() {
                this.tableLoading = true;
                request({
                    url: "/channel/page",
                    method: "post",
                    data: this.tablePage
                }).then(res => {
                    this.tableData = res.data.page.records;
                    this.tablePage.current = res.data.page.current;
                    this.tablePage.pages = res.data.page.pages;
                    this.tablePage.size = res.data.page.size;
                    this.tablePage.total = res.data.page.total;
                    this.tableLoading = false;
                })
            },
            handleCheckAllChange(val) {
                if (val) {
                    let tmp = [];
                    for (let key in rules) {
                        tmp.push(rules[key]["value"]);
                    }
                    this.form.checkedRules = tmp;
                    this.isIndeterminate = false;
                } else {
                    this.form.checkedRules = [];
                    this.isIndeterminate = true;
                }
            },
            handleCheckedChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.rules.length;
                this.isIndeterminate = (checkedCount > 0 && checkedCount < this.rules.length);
                console.log(this.form.checkedRules);
            },
            submitForm() {
                request({
                    url: "/channel/add",
                    method: "post",
                    data: this.form
                }).then(res => {
                    this.$message.success("成功");
                    this.fetch();
                })
            },
            updateForm() {
                request({
                    url: "/channel/update",
                    method: "post",
                    data: this.form
                }).then(res => {
                    this.$message.success("成功");
                    this.fetch();
                })
            },
            handleSizeChange(val) {
                this.tablePage.size = val;
                this.fetchData();
            },
            handleCurrentChange(val) {
                this.tablePage.current = val;
                this.fetchData();
            },
            update(index, row) {
                this.dialogVisible = true;
                this.form = this.tableData[index];
            }
        }
    }
</script>

<style scoped>

</style>
