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
            <el-button type="primary" round @click="downloadFile(scope.$index, scope.row)">
              <svg-icon icon-class="doc"></svg-icon>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import request from '@/utils/request';

  export default {
    name: 'fileTemplate',
    data() {
      return {
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
          method: 'get',
        }).then(res => {
          this.tableData = res.data.data;
          this.tableLoading = false;
        });
      },
      downloadFile(index, row) {
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = row.link;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
    },
  };
</script>
