<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入商品sku/东岳sku" placement="top">
            <el-input
                v-model="search.dySku"
                placeholder="请输入商品sku/东岳sku"
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
              @click="fetchData()"
          ></el-button>
        </el-col>
      </el-row>
      <el-table
          style="width: 100%;"
          :data="tableData"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column
            prop="dySku"
            label="东岳sku"
            min-width="120"
        ></el-table-column>
        <el-table-column
            prop="productName"
            label="商品名称"
            min-width="150"
        ></el-table-column>
        <el-table-column
            prop="owner"
            label="属主"
            min-width="100"
        ></el-table-column>
        <el-table-column
            prop="shelfNo"
            label="货架号"
            min-width="180"
        ></el-table-column>
        <el-table-column
            prop="totalNum"
            label="当前在库总数"
            min-width="120"
        ></el-table-column>
        <el-table-column
            prop="volume"
            label="体积(cm^3)"
            min-width="100"
        ></el-table-column>
        <el-table-column
            prop="costOnVolume"
            label="仓储费(体积/JPY)"
            min-width="100"
        ></el-table-column>
        <el-table-column
            prop="weight"
            label="重量(kg)"
            min-width="100"
        ></el-table-column>
        <el-table-column
            prop="statisticalTime"
            label="上一次统计时间"
            min-width="180"
        ></el-table-column>
        <el-table-column
            prop="adminUpdate"
            label="管理员更新"
            min-width="100"
        ></el-table-column>
        <el-table-column
            prop="updateBy"
            label="更新管理员"
            min-width="120"
        ></el-table-column>
        <el-table-column
            prop="updateTime"
            label="更新时间"
            min-width="180"
        ></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template slot-scope="scope">
            <el-tooltip
                content="编辑"
                placement="top"
            >
              <el-button
                  @click="handleUpdate(scope.$index, scope.row)"
                  size="mini"
                  type="info"
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
      >
      </el-pagination>
      <el-dialog title="统计调整" :visible.sync="dialogVisible" width="20%">
        <el-form label-width="120">
          <el-form-item label="东岳sku">
            <span>{{ curr.dySku }}</span>
          </el-form-item>
          <el-form-item label="商品名称">
            <span>{{ curr.productName }}</span>
          </el-form-item>
          <el-form-item label="商品属主">
            <span>{{ curr.owner }}</span>
          </el-form-item>
          <el-form-item label="货架">
            <span>{{ curr.shelfNo }}</span>
          </el-form-item>
          <el-form-item label="当前在库总数">
            <el-input-number v-model="curr.totalNum"></el-input-number>
          </el-form-item>
          <el-form-item label="体积(cm^3)">
            <el-input-number v-model="curr.volume"></el-input-number>
          </el-form-item>
          <el-form-item label="仓储费(体积/JPY)">
            <el-input-number v-model="curr.costOnVolume"></el-input-number>
          </el-form-item>
          <el-form-item label="重量(kg)">
            <el-input-number v-model="curr.weight"></el-input-number>
          </el-form-item>
        </el-form>
        <el-row>
          <el-button type="primary" @click="submit">确定</el-button>
          <el-button @click="this.dialogVisible=false">取消</el-button>
        </el-row>
      </el-dialog>
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
      dialogVisible: false,
      curr: {},
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
        url: '/statistics/history/product/list',
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
    handleUpdate(index, row) {
      this.dialogVisible = true;
      this.curr = row;
    },
    submit() {
      request({
        url: '/statistics/history/product/update',
        method: 'post',
        data: this.curr,
      }).then((res) => {
        this.fetchData();
        this.dialogVisible = false;
      });
    },
  },
};
</script>
