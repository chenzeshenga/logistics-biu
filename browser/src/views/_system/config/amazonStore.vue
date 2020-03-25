<template>
  <div class="login-container">
    <div class="app-container">
      <el-button @click="syncOrder()">同步</el-button>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'amazonStore',
  data() {
    return {
    };
  },
  created() {
  },
  watch: {
    $route() {

    },
  },
  methods: {
    syncOrder() {
      request({
        url: '/amazon/sync',
        method: 'get',
      }).then(() => {
        this.$message.success('3月订单同步完成')
      });
    },
  },
};
</script>

<style scoped>

</style>
