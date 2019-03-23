<template>
    <div class="login-container">
        <el-col :offset="8" :span="16" style="margin-top: 10px;margin-bottom: 10px" class="block">
            <el-date-picker v-model="daterange" type="daterange" align="right" unlink-panels range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd"
                            style="width: 400px">
            </el-date-picker>
            <el-button icon="el-icon-search" @click="searchOrd()"></el-button>
        </el-col>
        <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                  border fit highlight-current-row>
            <el-table-column type="expand">
                <template slot-scope="tableData">
                    <el-col :span="12">
                        <el-table :data="tableData.row.contentList" stripe border :row-class-name="tableRowClassName">
                            <el-table-column prop="sku" label="sku/东岳Sku" width="200"></el-table-column>
                            <el-table-column prop="name" label="商品名称" width="250"></el-table-column>
                            <el-table-column prop="price" label="商品价格" width="180"></el-table-column>
                            <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
                            <el-table-column prop="picked" label="已拣货数量" width="180"></el-table-column>
                        </el-table>
                    </el-col>
                </template>
            </el-table-column>
            <el-table-column width="160" prop="orderNo" label="订单号"></el-table-column>
            <el-table-column width="150" prop="categoryName" label="订单类型"></el-table-column>
            <el-table-column width="200" prop="channelDesc" label="运送渠道"></el-table-column>
            <el-table-column width="100" prop="statusDesc" label="订单状态"></el-table-column>
            <el-table-column width="250" prop="fromAddressDesc" label="寄件地址"></el-table-column>
            <el-table-column width="150" prop="fromName" label="寄件人"></el-table-column>
            <el-table-column width="150" prop="fromContact" label="寄件人联系方式"></el-table-column>
            <el-table-column width="150" prop="fromZipCode" label="寄件人邮编"></el-table-column>
            <el-table-column width="250" prop="toAddressDesc" label="收件地址"></el-table-column>
            <el-table-column width="150" prop="toName" label="收件人"></el-table-column>
            <el-table-column width="150" prop="toContact" label="收件人联系方式"></el-table-column>
            <el-table-column width="150" prop="toZipCode" label="收件人邮编"></el-table-column>
            <el-table-column prop="collect" width="80" label="是否代收商品费用"></el-table-column>
            <el-table-column width="170" prop="createOn" label="创建时间"></el-table-column>
            <el-table-column width="170" prop="updateOn" label="修改时间"></el-table-column>
            <el-table-column width="150" prop="creator" label="创建人"></el-table-column>
            <el-table-column width="150" prop="updator" label="修改人"></el-table-column>
            <el-table-column label="操作" width="250" fixed="right">
                <template slot-scope="scope">
                    <el-tooltip content="提交发货" placement="top">
                        <el-button @click="statusUpdate(scope.$index,scope.row)" size="small" type="info"
                                   icon="el-icon-check" circle plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="编辑" placement="top">
                        <el-button @click="handleUpdate(scope.$index,scope.row)" size="small" type="info"
                                   icon="el-icon-edit" circle plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button @click="handleDelete(scope.$index,scope.row)" size="small" type="danger"
                                   icon="el-icon-delete" circle plain></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="tablePage.current"
            :page-sizes="[10, 20, 30, 40, 50]"
            :page-size="tablePage.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="tablePage.total">
        </el-pagination>
        <el-dialog title="提交发货" :visible.sync="dialogVisible" width="30%">
            <el-form :model="form">
                <el-col>
                    <el-input v-model="form.orderNo" disabled></el-input>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="当前订单总体积(cm^3)">
                        <el-input-number v-model="form.totalVolume"></el-input-number>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="当前订单总重量(kg)">
                        <el-input-number v-model="form.totalWeight"></el-input-number>
                    </el-form-item>
                </el-col>
                <el-col>
                    <el-form-item label="当前订单运费(JPY)">
                        <el-input-number v-model="form.ordFee"></el-input-number>
                    </el-form-item>
                </el-col>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateVolumeAndWeight">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>


<script>
    import request from '@/utils/request'

    export default {
        name: 'order-list-mgt-type2-status1',
        data() {
            return {
                tablePage: {
                    current: null,
                    pages: null,
                    size: null,
                    total: null
                },
                tableLoading: false,
                tableData: [],
                daterange: null,
                pickerOptions2: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                dialogVisible: false,
                form: {
                    ordno: "",
                    totalVolume: 0,
                    totalWeight: 0,
                    ordFee: 0
                }
            }
        },
        created() {
            this.fetchData();
        },
        methods: {
            fetchData() {
                this.tableLoading = true;
                request({
                    url: "ord/list/2/2",
                    method: "post",
                    data: {
                        current: this.tablePage.current,
                        size: this.tablePage.size
                    }
                }).then(res => {
                    this.tableData = res.data.page.records;
                    this.tableLoading = false;
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
            handleUpdate(index, row) {
                this.$router.push({path: '/new-order/index?ordno=' + row.orderNo + "&status=2"})
            },
            handleDelete(index, row) {
                this.$confirm('您确定要永久删除该记录？', '提示', confirm).then(() => {
                    request({
                        url: "ord/delete/" + row.orderNo,
                        method: "get",
                    }).then(res => {
                        this.fetchData();
                        this.$message.success("删除成功");
                    })
                }).catch(() => {
                    this.$message.info("已取消删除")
                });
            },
            statusUpdate(index, row) {
                this.form.orderNo = row.orderNo;
                this.dialogVisible = true;
            },
            tableRowClassName({row, rowIndex}) {
                if (Number(row.picked) === Number(row.num)) {
                    row.satisfied = true;
                    return "success-row";
                } else {
                    row.satisfied = false;
                    return "danger-row";
                }
            },
            updateVolumeAndWeight() {
                this.$confirm('您确定要提交发货该订单？', '提示', confirm).then(() => {
                    request({
                        url: "ord/update/" + this.form.orderNo,
                        method: "post",
                        data: {
                            "totalVolume": this.form.totalVolume,
                            "totalWeight": this.form.totalWeight,
                            "ordFee": this.form.ordFee,
                        }
                    }).then(res => {
                        request({
                            url: "ord/update/2/" + this.form.orderNo + "/3",
                            method: "get"
                        }).then(res => {
                            this.fetchData();
                            this.$message.success("提交成功");
                        });
                    });
                });
            }
        }
    }

</script>

<style>
    .el-table .success-row {
        background: rgba(103, 194, 58, .1);
    }

    .el-table .danger-row {
        background: rgb(253, 226, 226);
    }
</style>
