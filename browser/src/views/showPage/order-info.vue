<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="10" style="width: 100%">
        <el-alert :title="alert.title" type="info" :description="alert.description" show-icon></el-alert>
        <el-col :span="24" v-for="(o, index) in tableData" :key="o" style="margin-top: 2%">
          <el-card :body-style="{ padding: '0px' }">
            <img :src=o.imgUrl style="width: 100%">
            <div style="padding: 14px;">
              <span>商品sku : {{ o.sku }}</span><br>
              <span>商品名称 : {{ o.name }}</span><br>
              <span>商品位置 : {{ o.location }}</span><br>
              <span>商品数量 : {{ o.num }}</span>
            </div>
          </el-card>
        </el-col>
        <el-col>
          <el-table style="width: 100%;margin-top: 2%" :data="tableData" v-loading.body="tableLoading"
                    element-loading-text="加载中" stripe
                    highlight-current-row>
            <el-table-column width="150" prop="sku" label="sku"></el-table-column>
            <el-table-column width="200" prop="name" label="商品名称"></el-table-column>
            <el-table-column width="150" prop="location" label="货架位置"></el-table-column>
            <el-table-column width="150" prop="num" label="待拣货数量">2</el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import request from '../../utils/service';

export default {
  name: 'order-info',
  data() {
    return {
      alert: {
        title: '订单号 ',
        description: '发往 ',
      },
      orders: [1, 2, 2],
      tableData: [],
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
      const ordno = this.$route.query.ord;
      request({
        url: '/ord/get/' + ordno,
        method: 'get',
      }).then((res) => {
        const data = res.data.data;
        this.alert.title = this.alert.title + ordno;
        this.alert.description = this.alert.description + data.fromKenName + data.fromCityName + data.fromTownName + data.fromDetailAddress + ' 收件人: ' + data.toName;
        this.tableData = data.contentList;
      });
    },
  },
};
</script>

<style scoped>

</style>
