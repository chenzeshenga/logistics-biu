<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8">入库单号: {{ warehousing.warehousingNo }}</el-col>
        <el-col :span="8">发货地址: {{ warehousing.warehousingNo }}</el-col>
        <el-col :span="8">入库单号: {{ warehousing.warehousingNo }}</el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import request from "../../utils/service";

export default {
  name: "upshelf",
  data() {
    return {
      warehousing: {}
    };
  },
  inject: ["reload"],
  created() {
    this.initData();
  },
  watch: {
    $route() {
      this.initData();
    }
  },
  methods: {
    initData() {
      const warehousingNo = this.$route.query.warehousingNo;
      if (warehousingNo !== undefined && warehousingNo.length > 0) {
        this.warehousing["warehousingNo"] = warehousingNo;
        if (warehousingNo !== window.sessionStorage.getItem("warehousingNo")) {
          request({
            url:
              "/warehousing/fetchByWarehousingNo?warehousingNo=" +
              warehousingNo,
            method: "get"
          }).then(ret => {
            window.sessionStorage.setItem("warehousingContent", ret);
          });
          location.reload();
        }
        window.sessionStorage.setItem("warehousingNo", warehousingNo);
        console.log(warehousingNo);
      }
    }
  }
};
</script>

<style scoped></style>
