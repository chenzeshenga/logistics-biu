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
            prop="userId"
            label="用户id"
        ></el-table-column>
        <el-table-column
            prop="userName"
            label="用户名"
        ></el-table-column>
        <el-table-column
            prop="chineseName"
            label="企业名称"
        ></el-table-column>
        <el-table-column
            prop="costOnVolume"
            label="单位体积仓储费(JPY/m^3)"
        ></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="更新单位体积仓储费" placement="top">
              <el-button
                  @click="handleUpdate(scope.$index, scope.row)"
                  size="small"
                  icon="el-icon-edit"
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
          width="25%"
      >
        <el-row>
          <span>用户id: <b>{{ curr.userId }}</b></span>
        </el-row>
        <el-row style="margin-top: 2%">
          <span>用户名: <b>{{ curr.userName }}</b></span>
        </el-row>
        <el-row style="margin-top: 2%">
          <span>企业名称: </span><br>
          <el-input v-model="curr.chineseName"></el-input>
        </el-row>
        <el-row style="margin-top: 2%">
          <span>单位体积仓储费(JPY/m^3): </span>
          <el-input-number v-model="curr.costOnVolume"></el-input-number>
        </el-row>
        <el-row style="margin-top: 5%">
          <el-button @click="update()">确定</el-button>
        </el-row>
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
      curr: {}
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
      this.fetch();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetch();
    },
    handleUpdate(index, row) {
      this.dialogVisible = true;
      this.curr = row;
    },
    update() {
      request({
        url: '/profile/mgtUpdate',
        method: 'post',
        data: this.curr,
      }).then(() => {
        this.$message.success('更新成功');
        this.dialogVisible = false;
      });
    },
  },
};
</script>

<style scoped></style>
