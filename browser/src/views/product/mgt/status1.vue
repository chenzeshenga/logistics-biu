<template>
  <div class="login-container">
    <div class="app-container">
      <el-table :data="tableData" element-loading-text="加载中" highlight-current-row
                stripe style="width: 100%;margin: 10px"
                v-loading.body="tableLoading">
        <el-table-column label="sku" prop="sku" width="150"></el-table-column>
        <el-table-column label="东岳sku" prop="dySku" width="150"></el-table-column>
        <el-table-column label="状态" prop="statusDesc" width="150"></el-table-column>
        <el-table-column label="商品名称" prop="productName" width="200"></el-table-column>
        <el-table-column label="商品类型" prop="categoryName" width="150"></el-table-column>
        <el-table-column label="商品颜色" prop="color" width="150"></el-table-column>
        <el-table-column label="商品尺寸" prop="size" width="150"></el-table-column>
        <el-table-column label="商品长度(cm)" prop="length" width="150"></el-table-column>
        <el-table-column label="商品宽度(cm)" prop="width" width="150"></el-table-column>
        <el-table-column label="商品高度(cm)" prop="height" width="150"></el-table-column>
        <el-table-column label="商品重量(kg)" prop="weight" width="150"></el-table-column>
        <el-table-column label="商品价格JPY" prop="price" width="150"></el-table-column>
        <el-table-column label="创建者" prop="createdBy" width="150"></el-table-column>
        <el-table-column label="更新者" prop="updateBy" width="150"></el-table-column>
        <el-table-column label="创建时间" prop="createOn" width="160"></el-table-column>
        <el-table-column label="更新时间" prop="updateOn" width="160"></el-table-column>
        <el-table-column fixed="right" label="操作" width="200">
          <template slot-scope="scope">
            <el-tooltip content="编辑" placement="top">
              <el-button @click="handleUpdate(scope.$index,scope.row)" circle icon="el-icon-edit"
                         plain size="mini" type="info"></el-button>
            </el-tooltip>
            <el-tooltip content="打印商品标签" placement="top">
              <el-button @click="handlePrint(scope.$index,scope.row)" circle plain
                         size="mini" type="info">
                <svg-icon icon-class="print"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button @click="handleDelete(scope.$index,scope.row)" circle icon="el-icon-remove"
                         plain size="mini" type="danger"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination :current-page="tablePage.current" :page-size="tablePage.size"
                     :page-sizes="[10, 20, 30, 40, 50]"
                     :total="tablePage.total" @current-change="handleCurrentChange"
                     @size-change="handleSizeChange"
                     layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
      <el-dialog>

      </el-dialog>
    </div>
  </div>
</template>

<script>
  import request from '@/utils/service';

  export default {
    name: 'status1',
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null,
        },
        tableLoading: false,
        tableData: [],
      };
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: 'product/list/1',
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        });
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
            url: '/product/status/' + row.sku + '/1',
            method: 'get',
          }).then(() => {
            this.$message.success('审核成功');
            this.fetchData();
          });
        }).catch(() => {
          this.$message.info('已取消审核');
        });
      },
      handleDelete(index, row) {
        console.log(row);
        this.$confirm('您确定删除该商品？', '提示', confirm).then(() => {
          request({
            url: '/product/delete/' + row.sku,
            method: 'get',
          }).then(() => {
            this.$message.success(row.sku + '删除成功');
            this.fetchData();
          });
        }).catch(() => {
          this.$message.info('已取消删除');
        });
      },
      handleUpdate(index, row) {
        this.$router.push({
          path: '/new-product/new-product?sku=' + row.sku,
        });
      },
      handlePrint(index, row) {
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/pdf/sku/' + row.dySku;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
    },
  };
</script>

<style scoped>

</style>
