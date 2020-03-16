<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入商品sku/东岳sku" placement="top">
            <el-input v-model="search.sku" placeholder="请输入商品sku/东岳sku"></el-input>
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
        <el-col :span="1" v-if="adminRole">
          <el-tooltip content="库存补录" placement="top">
            <el-button @click="adjustShelfContent()">
              <svg-icon icon-class="adjustment"/>
            </el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="6">刷新时间： {{tip.timestamp}}</el-col>
      </el-row>
      <el-alert title="当前页面显示的在库时间仅做参考，实际在库时间以账单为准" type="info"></el-alert>
      <el-table
        style="width: 100%;margin: 10px;margin-left:50px"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
      >
        <el-table-column type="expand">
          <template slot-scope="tableData">
            <el-table :data="tableData.row.children">
              <el-table-column prop="sku" label="sku"/>
              <el-table-column prop="name" label="商品名称"/>
              <el-table-column prop="shelfNo" label="货架号"/>
              <el-table-column prop="num" label="数量"/>
              <el-table-column prop="owner" label="属主"/>
              <el-table-column prop="warehousingNo" label="入库单号"/>
              <el-table-column prop="uptime" label="上架时间"/>
              <el-table-column prop="datePoor" label="在库时间"/>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column prop="sku" label="sku"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="owner" label="属主"></el-table-column>
        <el-table-column prop="num" label="在库总数量"></el-table-column>
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
      <el-dialog title="库存补录" :visible.sync="dialogVisible4ShelfContent" width="50%">
        <el-row>
          <el-tooltip content="请选择商品属主" placement="top">
            <el-select filterable @change="fetchProduct" clearable v-model="shelfContent.owner" placeholder="请选择商品属主">
              <el-option
                v-for="creator in options.owners"
                :key="creator.uname"
                :label="creator.nick"
                :value="creator.uname"
              ></el-option>
            </el-select>
          </el-tooltip>
        </el-row>
        <el-row style="margin-top: 1%">
          <el-col :span="8">
            <el-tooltip content="商品sku" placement="top">
              <el-select filterable clearable v-model="currContent.sku" placeholder="商品sku">
                <el-option
                  v-for="product in userProduct"
                  :key="product.dySku"
                  :label="product.name"
                  :value="product.dySku"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip content="商品数量" placement="top">
              <el-input-number v-model="currContent.num"></el-input-number>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip content="上架货架" placement="top">
              <el-select v-model="currContent.shelfNo" filterable placeholder="上架货架">
                <el-option
                  v-for="item in shelfOptions"
                  :key="item.shelfNo"
                  :label="item.shelfNo"
                  :value="item.shelfNo"
                >
              <span>
                货架号 {{ item.shelfNo }} 货架区域 {{ item.area }} 货架行数
                {{ item.layer }} 货架层数 {{ item.rowNo }}
              </span>
                </el-option>
              </el-select>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row style="margin-top: 1%">
          <el-col :span="8">
            <el-tooltip content="商品上架时间" placement="top">
              <el-date-picker
                v-model="currContent.uptime"
                type="date"
                placeholder="选择上架日期"/>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-input clearable v-model="currContent.warehousingNo" placeholder="入库单号"></el-input>
          </el-col>
          <el-col :span="8">
            <el-button type="primary" style="margin-left: 2%" @click="add2Shelf()">添加</el-button>
          </el-col>
        </el-row>
        <div v-for="(shelfSubContent,index) in shelfContent.content" :key="index" style="margin-top: 2%">
          商品sku:{{shelfSubContent.sku}} 商品数量:{{shelfSubContent.num}} 上架货架:{{shelfSubContent.shelfNo}}
          上架时间:{{shelfSubContent.uptime}} 入库单号:{{shelfSubContent.warehousingNo}}
          <el-button style="margin-left: 2%" type="danger" @click="removeSubContent(index)">删除</el-button>
        </div>
        <el-row style="margin-top: 5%">
          <el-button type="primary" @click="shelfContent2Backend">更新</el-button>
          <el-button @click="this.dialogVisible4ShelfContent=false">取消</el-button>
        </el-row>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';
import * as moment from 'moment';

export default {
  name: 'product-in-warehouse',
  data() {
    return {
      search: {
        sku: '',
        name: '',
        owner: '',
      },
      options: {
        owners: [],
      },
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
    searchProductInWarehouse() {
      this.tip.timestamp = moment().format('YYYY-MM-DD HH:mm:ss ddd');
      const postData = {
        current: this.tablePage.current,
        pages: this.tablePage.pages,
        size: this.tablePage.size,
        total: this.tablePage.total,
        sku: this.search.sku,
        name: this.search.name,
        owner: this.search.owner,
      };
      this.tableLoading = true;
      request({
        url: '/statistics/productInWarehouse',
        method: 'post',
        data: postData,
      }).then((ret) => {
        this.tableData = ret.data.data;
        this.tableLoading = false;
      });
    },
    fetchProduct(val) {
      request({
        url: '/product/listByUser?username=' + val,
        method: 'get',
      }).then((ret) => {
        this.userProduct = ret.data.data;
      });
    },
    adjustShelfContent() {
      this.dialogVisible4ShelfContent = true;
      this.fetchShelves();
    },
    fetchShelves() {
      request({
        url: '/shelf/list/enable',
        method: 'get',
      }).then((res) => {
        this.shelfOptions = res.data.data;
      });
    },
    removeSubContent(index) {
      this.shelfContent.content.splice(index, 1);
    },
    add2Shelf() {
      this.currContent.uptime = moment(this.currContent.uptime).format('YYYY-MM-DD');
      this.shelfContent.content.push(this.currContent);
      this.currContent = {
        sku: '',
        name: '',
        num: '',
        shelfNo: '',
        uptime: new Date(),
        warehousingNo: '',
      };
    },
    shelfContent2Backend() {
      const subContent = [];
      for (let i = 0; i < this.shelfContent.content.length; i++) {
        const sub = this.shelfContent.content[i];
        sub.uptime = sub.uptime + ' 00:00:00';
        subContent.push(sub);
      }
      request({
        url: 'product/shelf/addShelfContent',
        method: 'post',
        data: {
          'owner': this.shelfContent.owner,
          'content': subContent,
        },
      }).then((ret) => {
        this.dialogVisible4ShelfContent = false;
        this.$message.success('库存补录成功');
      });
    },
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
  },
};
</script>

<style></style>
