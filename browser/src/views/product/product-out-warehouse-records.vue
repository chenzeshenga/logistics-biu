<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入东岳sku" placement="top">
            <el-input v-model="search.dySku" placeholder="请输入东岳sku"></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="6">
          <el-tooltip content="请输入商品sku" placement="top">
            <el-input v-model="search.sku" placeholder="请输入商品sku"></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="6">
          <el-tooltip content="请输入商品名称" placement="top">
            <el-input v-model="search.name" placeholder="请输入商品名称"></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="3">
          <el-tooltip content="请选择商品属主" placement="top">
            <el-select filterable clearable v-model="search.owner" placeholder="请选择商品属主">
              <el-option
                v-for="creator in options.owners"
                :key="creator.uname"
                :label="creator.nick"
                :value="creator.uname"
              ></el-option>
            </el-select>
          </el-tooltip>
        </el-col>
      </el-row>
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入订单号" placement="top">
            <el-input v-model="search.orderNo" placeholder="请输入订单号"></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="6">
          <el-tooltip content="请输入追踪单号" placement="top">
            <el-input v-model="search.trackNo" placeholder="请输入追踪单号"></el-input>
          </el-tooltip>
        </el-col>
        <el-col :span="6">
          <el-date-picker
            v-model="search.dateTimeRange"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right">
          </el-date-picker>
        </el-col>
        <el-col :span="1">
          <el-tooltip content="搜索" placement="top">
            <el-button icon="el-icon-search" @click="searchProductInWarehouse()"></el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="1">
          <el-tooltip content="刷新" placement="top">
            <el-button icon="el-icon-refresh" @click="searchProductInWarehouse()"></el-button>
          </el-tooltip>
        </el-col>
      </el-row>
      <el-row>
        <el-col offset="16" :span="5">刷新时间： {{tip.timestamp}}</el-col>
      </el-row>
      <el-table
        style="width: 100%;margin: 10px;margin-left:50px"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
      >
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tablePage.current"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="tablePage.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tablePage.total"
        style="margin-left: 65%;margin-top: 10px"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';
import * as moment from 'moment';

export default {
  name: 'product-out-warehouse-records',
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          },
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          },
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          },
        }],
      },
      search: {
        dySku: '',
        sku: '',
        name: '',
        owner: '',
        orderNo: '',
        trackNo: '',
        startDate: '',
        endDate: '',
        dateTimeRange: '',
      },
      options: {
        owners: [],
      },
      outSide: true,
      inSide: false,
      tableLoading: false,
      tableData: [],
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null,
      },
      tip: {
        timestamp: null,
      },
      adminRole: false,
      shelfContent: {
        owner: '',
        content: [],
      },
      currContent: {
        sku: '',
        name: '',
        num: '',
        shelfNo: '',
        uptime: new Date(),
        warehousingNo: '',
      },
      userProduct: [],
      dialogVisible4ShelfContent: false,
      shelfOptions: [],
    };
  },
  created() {
    this.initUserList();
    this.searchProductInWarehouse();
    this.hasAdminRole();
  },
  methods: {
    hasAdminRole() {
      request({
        url: '/sys_user/info',
        method: 'get',
      }).then((res) => {
        const roles = res.data.userInfo.roles;
        for (let i = 0; i < roles.length; i++) {
          const role = roles[i];
          const val = role['val'];
          if (val === 'root' || val === 'operator') {
            this.adminRole = true;
          }
        }
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
      this.searchProductInWarehouse();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.searchProductInWarehouse();
    },
    searchProductOutWarehouseRecord() {
      const postData = {
        current: this.tablePage.current,
        pages: this.tablePage.pages,
        size: this.tablePage.size,
        total: this.tablePage.total,
        sku: this.search.sku,
        name: this.search.name,
        owner: this.search.owner,
      };
      this.outSide = false;
      this.inSide = true;
      this.tableLoading = true;
      request({
        url: '/statistics/productOutWarehouse',
        method: 'post',
        data: postData,
      }).then((ret) => {
        this.tableData = ret.data.data;
        this.tableLoading = false;
      });
    },
  },
};
</script>

<style></style>
