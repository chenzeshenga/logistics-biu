<template>
  <div class="login-container">
    <div class="app-container">
      <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading"
                element-loading-text="加载中" stripe
                highlight-current-row>
        <el-table-column width="100" prop="uuid" label="id"></el-table-column>
        <el-table-column width="150" prop="link" label="文件">
          <template slot-scope="scope">
            <svg-icon icon-class="doc"></svg-icon>
          </template>
        </el-table-column>
        <el-table-column width="200" prop="fileName" label="文件名"></el-table-column>
        <el-table-column width="200" prop="createOn" label="创建时间"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'template',
  };
</script>

<style scoped>

</style>
