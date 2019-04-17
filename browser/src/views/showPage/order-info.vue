<template>
  <el-row :gutter="10" style="width: 100%">
    <div class="app-container">
      <el-alert :title="alert.title" type="info" :description="alert.description" show-icon></el-alert>
      <el-col :span="24" v-for="(o, index) in 2" :key="o" style="margin-top: 2%">
        <el-card :body-style="{ padding: '0px' }">
          <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" style="width: 100%">
          <div style="padding: 14px;">
            <span>好吃的汉堡</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading"
                element-loading-text="加载中" stripe
                highlight-current-row>
        <el-table-column width="150" prop="sku" label="sku"></el-table-column>
        <el-table-column width="150" prop="dySku" label="东岳sku"></el-table-column>
        <el-table-column width="200" prop="productName" label="商品名称"></el-table-column>
        <el-table-column width="150" prop="statusDesc" label="货架位置"></el-table-column>
        <el-table-column width="150" prop="categoryName" label="待拣货数量"></el-table-column>
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
          title: '消息提示的文案',
          description: '文字说明文字说明文字说明文字说明文字说明文字说明',
        },
        orders: [1, 2, 2],
      };
    },
    watch: {
      $route() {
        this.init();
      },
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
