<template>
    <div class="login-container">
        <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading"
                  element-loading-text="加载中" stripe
                  highlight-current-row>
            <el-table-column width="150" prop="sku" label="sku"></el-table-column>
            <el-table-column width="150" prop="dySku" label="东岳sku"></el-table-column>
            <el-table-column width="150" prop="totalNum" label="在库商品总数"></el-table-column>
            <el-table-column width="150" prop="onWayNum" label="在途入库商品数量"></el-table-column>
            <el-table-column width="150" prop="uncheckNum" label="商品类型"></el-table-column>
            <el-table-column prop="pendingPickupNum" label="商品颜色" width="150"></el-table-column>
            <el-table-column prop="pendingDeliverNum" label="商品尺寸" width="150"></el-table-column>
            <el-table-column prop="defectNum" label="商品长度(cm)" width="150"></el-table-column>
            <el-table-column prop="statisticalTime" label="商品宽度(cm)" width="150"></el-table-column>
            <el-table-column label="操作" width="200" fixed="right">
                <template slot-scope="scope">
                    <el-tooltip content="审核" placement="top">
                        <el-button @click="statusUpdate(scope.$index,scope.row)" size="mini" type="info"
                                   icon="el-icon-check" circle
                                   plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="编辑" placement="top">
                        <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info"
                                   icon="el-icon-edit" circle plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button @click="handleDelete(scope.$index,scope.row)" size="mini" type="danger"
                                   icon="el-icon-remove" circle plain></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="tablePage.current"
                       :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="tablePage.total">
        </el-pagination>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "product-statistic",
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
            }
        },
        created() {
            this.fetchData();
        },
        methods: {
            fetchData() {
                this.tableLoading = true;
                request({
                    url: "product/list/0",
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
            statusUpdate(index, row) {
                console.log(row);
                this.$confirm('您确定审核通过该商品？', '提示', confirm).then(() => {
                    request({
                        url: "/product/status/" + row.sku + "/1",
                        method: "get"
                    }).then(() => {
                        this.$message.success("审核成功");
                        this.fetchData();
                    })
                }).catch(() => {
                    this.$message.info("已取消审核")
                })
            },
            handleDelete(index, row) {
                console.log(row);
                this.$confirm("您确定删除该商品？", "提示", confirm).then(() => {
                    request({
                        url: "/product/delete/" + row.sku,
                        method: "get"
                    }).then(() => {
                        this.$message.success(row.sku + "删除成功");
                        this.fetchData();
                    })
                }).catch(() => {
                    this.$message.info("已取消删除");
                })
            },
            handleUpdate(index, row) {
                this.$router.push({path: '/new-product/new-product?sku=' + row.sku});
            }
        }
    }
</script>

