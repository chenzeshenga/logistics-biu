<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin-left: 5%">
        <el-col :span="6">
          <el-input v-model="regTxt" placeholder="快捷搜索">
            <el-button
              icon="el-icon-search"
              slot="append"
              @click="fetch()"
            ></el-button>
          </el-input>
        </el-col>
        <el-button @click="triggerNewStore()">新增店铺</el-button>
      </el-row>
      <el-button @click="syncOrder()">同步</el-button>
      <el-table style="width: 100%;margin-top: 10px"
                :data="tableData"
                v-loading.body="tableLoading"
                element-loading-text="加载中"
                stripe
                highlight-current-row>
        <el-table-column
          prop="uuid"
          label="uuid"
        ></el-table-column>
        <el-table-column
          prop="userId"
          label="东岳用户id"
        ></el-table-column>
        <el-table-column
          prop="storeName"
          label="店名"
        ></el-table-column>
        <el-table-column
          prop="sellerId"
          label="sellerId"
        ></el-table-column>
        <el-table-column
          prop="mwsAuthToken"
          label="mwsAuthToken"
        ></el-table-column>
        <el-table-column
          prop="marketplaceId"
          label="marketplaceId"
        ></el-table-column>
        <el-table-column
          prop="enable"
          label="启用"
        ></el-table-column>
        <el-table-column label="操作" width="300" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="编辑" placement="top">
              <el-button
                @click="update(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-edit"
                circle
                plain
              ></el-button>
            </el-tooltip>
            <el-tooltip content="快速启用" placement="top">
              <el-button
                @click="handleEnable(scope.$index, scope.row)"
                size="small"
                type="success"
                circle
                plain
              >
                <svg-icon icon-class="enable"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="同步订单" placement="top">
              <el-button
                @click="syncOrder(scope.$index, scope.row)"
                size="small"
                type="success"
                circle
                plain
              >
                <svg-icon icon-class="enable"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="快速禁用" placement="top">
              <el-button
                @click="handleDisable(scope.$index, scope.row)"
                size="small"
                type="warning"
                circle
                plain
              >
                <svg-icon icon-class="disable"></svg-icon>
              </el-button>
            </el-tooltip>
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
        style="margin-left: 65%;margin-top: 10px"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tablePage.current"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="tablePage.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tablePage.total"
      >
      </el-pagination>
      <el-dialog title="亚马逊店铺" :visible.sync="dialogVisible" width="40%">
        <el-row :gutter="20">
          <el-col :span="12">
            店铺名称:
            <el-input v-model="data.storeName" placeholder="店铺名称"></el-input>
          </el-col>
          <el-col :span="12">
            sellerId:
            <el-input v-model="data.sellerId" placeholder="sellerId"></el-input>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 2%">
          <el-col :span="12">
            mwsAuthToken:
            <el-input v-model="data.mwsAuthToken" placeholder="mwsAuthToken"></el-input>
          </el-col>
          <el-col :span="12">
            marketplaceId:
            <el-input v-model="data.marketplaceId" placeholder="marketplaceId"></el-input>
          </el-col>
        </el-row>
        <el-button @click="post2Backend()" style="margin-top: 2%">确认</el-button>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'amazonStore',
  data() {
    return {
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      regTxt: '',
      dialogVisible: false,
      data: {
        storeName: '',
        sellerId: '',
        mwsAuthToken: '',
        marketplaceId: '',
      },
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
        this.$message.success('3月订单同步完成');
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
    fetch() {

    },
    triggerNewStore() {
      this.dialogVisible = true;
    },
    post2Backend() {
      request({
        url: '/amazon/storeInfo',
        method: 'post',
        data: this.data,
      }).then((ret) => {
        console.log(ret);
      });
    },
  },
};
</script>

<style scoped>

</style>
