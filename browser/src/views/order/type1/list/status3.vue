<template>
    <div class="login-container">
        <el-col :offset="8" :span="22" style="margin-top: 10px;margin-bottom: 10px" class="block">
            <el-date-picker v-model="daterange" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期"
                            end-placeholder="结束日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd" style="width: 400px">
            </el-date-picker>
            <el-button icon="el-icon-search" @click="searchOrd()"></el-button>
        </el-col>
        <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中"
                  stripe border fit highlight-current-row>
            <el-table-column type="expand">
                <template slot-scope="tableData">
                    <el-col :span="12">
                        <el-table :data="tableData.row.contentList" stripe border>
                            <el-table-column prop="sku" label="sku/东岳Sku" width="200"></el-table-column>
                            <el-table-column prop="name" label="商品名称"></el-table-column>
                            <el-table-column prop="price" label="商品价格" width="180"></el-table-column>
                            <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
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
                    <el-tooltip content="归档" placement="top">
                        <el-button @click="statusUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-check" circle
                                   plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="查询物流进度" placement="top">
                        <el-button @click="findWhere(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-question" circle
                                   plain></el-button>
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
    </div>
</template>


<script>
    import request from '@/utils/request'

    export default {
        name: 'order-list-mgt-type1-status1',
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
            }
        },
        created() {
            this.fetchData();
        },
        methods: {
            fetchData() {
                this.tableLoading = true;
                request({
                    url: "ord/list/1/3",
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
                this.$router.push({path: '/new-order/index?ordno=' + row.orderNo})
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
                this.$confirm('您确定要归档该订单？', '提示', confirm).then(() => {
                    console.log(row);
                    console.log(index);
                    request({
                        url: "ord/update/1/" + row.orderNo + "/7",
                        method: "get"
                    }).then(res => {
                        this.fetchData();
                        this.$message.success("归档成功");
                    })
                }).catch(() => {
                    this.$message.info("已取消提交")
                })
            },
            searchOrd() {
                console.log(this.daterange);
                if (this.daterange == null || this.daterange[0] === 0 || this.daterange[1] === 0) {
                    this.$message.warning("请选择日期");
                    return;
                }
                this.tableLoading = true;
                request({
                    url: "ord/list/1/3/" + this.daterange[0] + "/" + this.daterange[1],
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
            findWhere(index, row) {
                this.$message.info("查询物流进度");
            }
        }
    }

</script>
