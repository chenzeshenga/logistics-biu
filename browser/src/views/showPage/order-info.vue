<template>
  <el-row :gutter="10" style="width: 100%">
    <div class="app-container">
      <el-alert :title="alert.title" type="info" :description="alert.description" show-icon></el-alert>
      <el-col :span="24" v-for="(o, index) in 1" :key="o" style="margin-top: 2%">
        <el-card :body-style="{ padding: '0px' }">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
               style="width: 100%">
          <div style="padding: 14px;">
            <span>商品sku : 2082695407010/1234567890123</span><br>
            <span>商品名称 : 商品名称</span><br>
            <span>商品位置 : A-2-2</span><br>
            <span>商品数量 : 2</span>
          </div>
        </el-card>
      </el-col>
      <el-table style="width: 100%;margin-top: 2%" :data="tableData" v-loading.body="tableLoading"
                element-loading-text="加载中" stripe
                highlight-current-row>
        <el-table-column width="150" prop="sku" label="sku"></el-table-column>
        <el-table-column width="150" prop="dySku" label="东岳sku"></el-table-column>
        <el-table-column width="200" prop="productName" label="商品名称"></el-table-column>
        <el-table-column width="150" prop="location" label="货架位置"></el-table-column>
        <el-table-column width="150" prop="num" label="待拣货数量">2</el-table-column>
      </el-table>
    </div>
  </el-row>
</template>

<script>
  export default {
    name: 'order-info',
    data() {
      return {
        alert: {
          title: '订单号 DY779e99ec-0',
          description: '发往 青森県下北郡東通村大利xxxxxx, 收件人: xxxxx',
        },
        orders: [1, 2, 2],
        tableData: [
          {
            'sku': '2082695407010',
            'dySku': '1234567890123',
            'productName': '商品名称',
            'location': 'A-2-2',
            'num': 2
          }
        ]
      };
    },
    watch: {
      $route() {
        this.init();
      },
    },
    created() {
      this.init();
    },
    methods: {
      init() {
        console.log(this.$route.query.ord);
      },
    },
  };
</script>

<style scoped>

</style>
