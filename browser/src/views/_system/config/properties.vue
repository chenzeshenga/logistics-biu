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
          <el-tooltip content="根据键值查询" placement="top">
            <el-form>
              <el-input v-model="key" placeholder="根据键值查询"></el-input>
            </el-form>
          </el-tooltip>
        </el-col>
        <el-col :span="2">
          <el-tooltip class="item" content="搜索" placement="top">
            <el-button
                icon="el-icon-search"
                circle
                @click="search"
            ></el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="2" :offset="10">
          <el-button type="primary" @click="triggerDialog">新增</el-button>
        </el-col>
      </el-row>
      <el-table
          style="width: 100%;margin-top: 10px"
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
      <el-dialog
          title="参数新增"
          :visible.sync="dialogVisible"
          width="55%"
      >
        <div class="app-container">
          <el-form
              ref="form"
              :model="form"
              label-width="120px"
          >
            <el-form-item label="参数key">
              <el-col :span="12">
                <el-input
                    v-model="form.key"
                    placeholder="参数key"
                ></el-input>
              </el-col>
              <el-col :span="12">
                <el-form-item label="参数值">
                  <el-input
                      v-model="form.value"
                      placeholder="参数key"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col :offset="22">
                <el-button
                    type="primary"
                    @click="submitForm()"
                >立即创建
                </el-button>
              </el-col>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'properties',
  data() {
    return {
      tableLoading: false,
      tableData: [],
      form: {
        key: '',
        value: '',
      },
      checkAll: false,
      dialogVisible: false,
      key: '',
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    fetch() {
      this.tableLoading = true;
      request({
        url: '/dictController/get',
        method: 'get',
      }).then((res) => {
        this.tableData = res.data.data;
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
    handleDelete(index, row) {
      this.$confirm('您确定要永久删除该记录？', '提示', confirm)
          .then(() => {
            request({
              url: '/channel/delete/' + row.channelCode,
              method: 'get',
            }).then(() => {
              this.$message.success('删除成功');
              this.fetch();
            });
          })
          .catch(() => {
            this.$message.info('取消删除');
          });
    },
    search() {
      this.tableLoading = true;
      request({
        url: '/dictController/getByKey?key=' + this.key,
        method: 'get',
      }).then((res) => {
        console.log(key);
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
