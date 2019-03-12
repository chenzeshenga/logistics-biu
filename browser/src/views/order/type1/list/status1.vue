<template>
  <div class="login-container">
    <el-table style="width: 100%"
              :data="tableData"
              v-loading.body="tableLoading"
              element-loading-text="加载中"
              border fit highlight-current-row>
      <el-table-column></el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-tooltip content="编辑" placement="top">
            <el-button @click="handleUpdate(scope.$index,scope.row)" size="medium" type="info" icon="el-icon-edit" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button @click="handleDelete(scope.$index,scope.row)" size="medium" type="danger" icon="el-icon-delete" circle plain></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
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
  import request from '@/utils/request'

  export default {
    name: 'order-list-mgt-type1-status1',
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        },
        tableLoading: false,
        tableData: []
      }
    },
    created() {
      // this.$router.push({path: '/order-list/mgt/type1/status2'})
      this.fetchData();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: "ord/list/1/1",
          method: "post",
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
          console.log(this.tableData);
        })
      },
      handleSizeChange(val) {
        this.tablePage.size = val;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.tablePage.current = val;
        this.fetchData();
      },
      handleUpdate(index, row) {
        console.log(index);
        console.log(row);
      },
      handleDelete(index, row) {
        this.$confirm('您确定要永久删除该记录？', '提示', confirm).then(() => {
          request({
            url: "",
            method: "get",
          }).then(res => {
            console.log(res);
            this.fetchData();
            this.$message.success("删除成功");
          })
        }).catch(() => {
          this.$message.info("已取消删除")
        });

        console.log(index);
        console.log(row);
      }
    }
  }

</script>
