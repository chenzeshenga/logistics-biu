<template>
    <div class="login-container">
        <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                  highlight-current-row>
            <el-table-column width="150" prop="sku" label="sku"></el-table-column>
            <el-table-column width="150" prop="dySku" label="东岳sku"></el-table-column>
            <el-table-column width="200" prop="productName" label="商品名称"></el-table-column>
            <el-table-column width="150" prop="categoryName" label="商品类型"></el-table-column>
            <el-table-column prop="color" label="商品颜色" width="150"></el-table-column>
            <el-table-column prop="size" label="商品尺寸" width="150"></el-table-column>
            <el-table-column prop="length" label="商品长度(cm)" width="150"></el-table-column>
            <el-table-column prop="width" label="商品宽度(cm)" width="150"></el-table-column>
            <el-table-column prop="height" label="商品高度(cm)" width="150"></el-table-column>
            <el-table-column prop="weight" label="商品重量(kg)" width="150"></el-table-column>
            <el-table-column prop="price" label="商品价格" width="150"></el-table-column>
            <el-table-column prop="createdBy" label="创建者" width="150"></el-table-column>
            <el-table-column prop="updateBy" label="更新者" width="150"></el-table-column>
            <el-table-column prop="createOn" label="创建时间" width="160"></el-table-column>
            <el-table-column prop="updateOn" label="更新时间" width="160"></el-table-column>
            <el-table-column label="操作" width="200" fixed="right">
                <template slot-scope="scope">
                    <el-tooltip content="审核" placement="top">
                        <el-button @click="statusUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-check" circle
                                   plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="编辑" placement="top">
                        <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-edit" circle plain></el-button>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="top">
                        <el-button @click="abandon(scope.$index,scope.row)" size="mini" type="danger" icon="el-icon-remove" circle plain></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="tablePage.current"
                       :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size" layout="total, sizes, prev, pager, next, jumper"
                       :total="tablePage.total">
        </el-pagination>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "status0",
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
        }
    }
</script>

<style scoped>

</style>
