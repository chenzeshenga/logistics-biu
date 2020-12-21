<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20">
        <el-col :span="1" :offset="2">
          <el-tooltip content="刷新" placement="top">
            <el-button size="mini" circle @click="fetch">
              <svg-icon icon-class="refresh"></svg-icon>
            </el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="4">
          <el-tooltip content="根据用户企业查询" placement="top">
            <el-tooltip content="用户企业" placement="top">
              <el-select filterable clearable v-model="search.userId" placeholder="请选择用户企业">
                <el-option
                    v-for="user in users"
                    :key="user.uname"
                    :label="user.nick"
                    :value="user.uname"
                />
              </el-select>
            </el-tooltip>
          </el-tooltip>
        </el-col>
        <el-col :span="2">
          <el-tooltip class="item" content="搜索" placement="top">
            <el-button
                icon="el-icon-search"
                circle
                @click="fetch"
            ></el-button>
          </el-tooltip>
        </el-col>
      </el-row>
      <el-table
          style="width: 100%;margin-top: 10px;margin-left: 2%"
          :data="tableData"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column
            prop="id"
            label="id"
        ></el-table-column>
        <el-table-column
            prop="key"
            label="键值"
        ></el-table-column>
        <el-table-column
            prop="value"
            label="参数值"
        ></el-table-column>
        <el-table-column
            prop="ctime"
            label="创建时间"
        ></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="删除" placement="top">
              <el-button
                  @click="handleDelete(scope.$index, scope.row)"
                  size="small"
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  plain
              ></el-button>
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
          :total="tablePage.total"
          style="margin-left: 2%;margin-top: 1%"
      >
      </el-pagination>
      <el-dialog
          title="参数新增"
          :visible.sync="dialogVisible"
          width="55%"
      >
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'costOnPerVolume',
  data() {
    return {
      tableLoading: false,
      tableData: [],
      search: {
        userId: '',
      },
      dialogVisible: false,
      users: [],
      tablePage: {
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
    };
  },
  created() {
    this.initUserList();
    this.fetch();
  },
  methods: {
    fetch() {
      this.tableLoading = true;
      const postData = {
        'pagination': this.tablePage,
        'entity': this.search.userId,
      };
      request({
        url: '/profile/list',
        method: 'post',
        data: postData,
      }).then((res) => {
        this.tablePage.total = res.data.data.total;
        this.tableData = res.data.data.data;
        this.tableLoading = false;
      });
    },
    submitForm() {
      request({
        url: '/channel/add',
        method: 'post',
        data: this.form,
      }).then(() => {
        this.dialogVisible = false;
        this.$message.success('成功');
        this.fetch();
      });
    },
    triggerDialog() {
      this.dialogVisible = true;
    },
    initUserList() {
      request({
        url: '/sys_user/query4Option',
        method: 'post',
        data: {
          current: null,
          size: 'all',
        },
      }).then((res) => {
        this.users = res.data.page.records;
      });
    },
    handleSizeChange(val) {
      this.tablePage.size = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetchData();
    },
  },
};
</script>

<style scoped></style>
