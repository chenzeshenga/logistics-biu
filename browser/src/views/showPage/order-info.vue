<template>
  <el-row :gutter="10">
    <div class="app-container">
      <el-col v-for="item in orders">
        <h6>order-info</h6>
      </el-col>
    </div>
  </el-row>
</template>

<script>
  export default {
    name: 'order-info',
    data() {
      return {
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
