<template>
  <div class="login-container">
    <el-table>

    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="tablePage.current"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="tablePage.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tablePage.total">
    </el-pagination>
  </div>
</template>


<script>
  export default {
    name: 'order-list-mgt-type1-status1',
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        }
      }
    },
    created() {
      // this.$router.push({path: '/order-list/mgt/type1/status2'})
    }
  }

</script>
