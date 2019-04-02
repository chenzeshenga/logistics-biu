<template>
  <div class="login-container">
    <div class="app-container">
      <el-table style="width: 100%;margin: 10px" :data="tableData" v-loading.body="tableLoading"
                element-loading-text="加载中" stripe
                highlight-current-row>
        <el-table-column prop="uuid" label="id"></el-table-column>
        <el-table-column prop="fileName" label="文件名"></el-table-column>
        <el-table-column prop="createOn" label="创建时间"></el-table-column>
        <el-table-column prop="link" label="文件">
          <template slot-scope="scope">
            <svg-icon icon-class="doc"></svg-icon>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import request from '@/utils/request';

  export default {
    name: 'template',
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
      this.fetch();
    },
    methods: {
      fetch() {
        this.tableLoading = true;
        request({
          url: '/template/list',
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
          this.tablePage = res.data.page;
        });
      },
    },
  };
</script>
