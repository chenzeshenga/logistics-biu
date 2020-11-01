<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入商品sku/东岳sku" placement="top">
            <el-input
                v-model="search.sku"
                placeholder="请输入商品sku/东岳sku"
            ></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="6">
          <el-tooltip content="请输入商品名称" placement="top">
            <el-input
                v-model="search.name"
                placeholder="请输入商品名称"
            ></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="3">
          <el-tooltip content="请选择商品属主" placement="top">
            <el-select
                filterable
                clearable
                v-model="search.owner"
                placeholder="请选择商品属主"
            >
              <el-option
                  v-for="creator in options.owners"
                  :key="creator.uname"
                  :label="creator.nick"
                  :value="creator.uname"
              ></el-option>
            </el-select>
          </el-tooltip>
        </el-col>
        <el-col :span="1">
          <el-button
              icon="el-icon-search"
              @click="searchProductStatistics()"
          ></el-button>
        </el-col>
      </el-row>
      <el-alert
          title="可售数量=在库总数量-待拣货数量-待出库数量-瑕疵品数量"
          type="info"
          show-icon
          center
      ></el-alert>
      <el-table
          style="width: 100%;margin: 10px"
          :data="tableData"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column
            prop="dySku"
            label="东岳sku"
        ></el-table-column>
        <el-table-column
            prop="productName"
            label="商品名称"
        ></el-table-column>
        <el-table-column
            prop="owner"
            label="属主"
        ></el-table-column>
        <el-table-column
            prop="totalNum"
            label="当前在库总数"
        ></el-table-column>
        <el-table-column
            prop="volume"
            label="体积(cm^3)"
        ></el-table-column>
        <el-table-column
            prop="weight"
            label="重量(kg)"
        ></el-table-column>
        <el-table-column
            prop="statisticalTime"
            label="上一次统计时间"
        ></el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="tablePage.current"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size="tablePage.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="tablePage.total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'product-statistic',
  data() {
    return {
      search: {
        dySku: '',
        shelfNo: '',
        owner: '',
      },
      options: {
        owners: [],
      },
      tablePage: {
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      tableLoading: false,
      tableData: [],
    };
  },
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
    fetchData() {
      this.tableLoading = true;
      const requestParamJson = {
        'entity': {
          'dySku': this.search.dySku,
          'owner': this.search.owner,
          'shelfNo': this.search.shelfNo,
        },
        'pagination': {
          'current': this.tablePage.current,
          'size': this.tablePage.size,
        },
      };
      request({
        url: '/statistics/product/list',
        method: 'post',
        data: requestParamJson,
      }).then((res) => {
        console.log(res);
        this.tableData = res.data.data.data;
        this.tablePage.total = res.data.data.total;
        this.tableLoading = false;
      });
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
        this.options.owners = res.data.page.records;
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
    statusUpdate(index, row) {
      this.$confirm('您确定审核通过该商品？', '提示', confirm)
          .then(() => {
            request({
              url: '/statistics/list',
              method: 'get',
            }).then(() => {
              this.$message.success('审核成功');
              this.fetchData();
            });
          })
          .catch(() => {
            this.$message.info('已取消审核');
          });
    },
    handleDelete(index, row) {
      this.$confirm('您确定删除该商品？', '提示', confirm)
          .then(() => {
            request({
              url: '/product/delete/' + row.sku,
              method: 'get',
            }).then(() => {
              this.$message.success(row.sku + '删除成功');
              this.fetchData();
            });
          })
          .catch(() => {
            this.$message.info('已取消删除');
          });
    },
    handleUpdate(index, row) {
      this.$router.push({
        path: '/new-product/new-product?sku=' + row.sku,
      });
    },
    searchProductStatistics() {
      const postData = this.tablePage;
      postData['sku'] = this.search.sku;
      postData['name'] = this.search.name;
      postData['owner'] = this.search.owner;
      request({
        url: '/statistics/listBySearch',
        method: 'post',
        data: postData,
      }).then((res) => {
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
