<template>
  <div class="login-container">
    <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading"
              element-loading-text="加载中" stripe
              highlight-current-row>
      <el-table-column width="150" prop="sku" label="sku"></el-table-column>
      <el-table-column width="150" prop="dySku" label="东岳sku"></el-table-column>
      <el-table-column width="150" prop="statusDesc" label="状态"></el-table-column>
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
          <el-tooltip content="编辑" placement="top">
            <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info"
                       icon="el-icon-edit" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="打印商品标签" placement="top">
            <el-button @click="handlePrint(scope.$index,scope.row)" size="mini" type="info"
                       circle plain>
              <svg-icon icon-class="print"></svg-icon>
            </el-button>
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
  import request from '@/utils/request';

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
        link.href = 'http://47.105.107.242:8888/api/v1/pdf/sku/' + row.dySku;
        // link.href = 'http://localhost:8888/api/v1/pdf/sku/' + row.dySku;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
    },
  };
</script>

<style scoped>

</style>
