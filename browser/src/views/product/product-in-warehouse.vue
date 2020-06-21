<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请输入商品sku/东岳sku" placement="top">
            <el-input v-model="search.dySku" placeholder="请输入商品sku/东岳sku"></el-input>
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
        <el-col :span="2">
          <el-tooltip content="导出库存记录-统计" placement="top">
            <el-button type="primary" @click="exportStatistics()">下载</el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="1" v-if="adminRole">
          <el-tooltip content="库存补录" placement="top">
            <el-button @click="adjustShelfContent()">
              <svg-icon icon-class="adjustment"/>
            </el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="5" style="margin-top: 1%">刷新时间： {{tip.timestamp}}</el-col>
      </el-row>
      <el-table
        style="width: 100%;margin: 10px;margin-left:50px"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
      >
        <el-table-column prop="dySku" label="东岳sku"></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column prop="owner" label="属主"></el-table-column>
        <el-table-column prop="num" label="在库总数量"></el-table-column>
        <el-table-column prop="totalVolume" label="在库总体积(cm^3)"></el-table-column>
        <el-table-column prop="totalWeight" label="在库总重量(kg)"></el-table-column>
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
            <el-tooltip content="商品东岳sku" placement="top">
              <el-select filterable clearable v-model="currContent.dySku" @change="showProductName" placeholder="商品东岳sku">
                <el-option
                  v-for="product in userProduct"
                  :key="product.dySku"
                  :label="product.dySku"
                  :value="product.dySku"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="16">
            <el-tooltip content="商品名称" placement="top">
              <el-input v-model="currContent.name" disabled></el-input>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row style="margin-top: 1%">
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
          商品sku:{{shelfSubContent.dySku}} 商品数量:{{shelfSubContent.num}} 上架货架:{{shelfSubContent.shelfNo}}
          上架时间:{{shelfSubContent.uptime}} 入库单号:{{shelfSubContent.warehousingNo}}
          <el-button style="margin-left: 2%" type="danger" @click="removeSubContent(index)">删除</el-button>
        </div>
        <el-row style="margin-top: 5%">
          <el-button type="primary" @click="shelfContent2Backend">更新</el-button>
          <el-button @click="closeDialog">取消</el-button>
        </el-row>
        <el-row style="margin-top: 5%">批量导入</el-row>
        <el-form :model="form">
        <el-upload
          :action="actionLink"
          with-credentials
          :on-error="handleError"
          :limit="1"
          :auto-upload="false"
        >
          <el-button
            slot="trigger"
            size="small"
            type="primary"
          >选取文件
          </el-button>
          <el-button
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload4Excel"
          >上传
          </el-button>
          <el-button
            style="margin-left: 150px;"
            size="small"
            type="success"
            @click="downloadTemplate"
          >
            <svg-icon icon-class="doc"></svg-icon>
            模版文件
          </el-button>
          <div slot="tip" class="el-upload__tip">
            只能上传excel文件(xls/xlsx)，建议记录条数小于200条
          </div>
        </el-upload>
        </el-form>
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
        dySku: '',
        name: '',
        owner: '',
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
        size: 10,
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
        dySku: '',
        sku: '',
        name: '',
        num: '',
        shelfNo: '',
        uptime: new Date(),
        warehousingNo: '',
      },
      userProduct: [],
      userProductMap: {},
      dialogVisible4ShelfContent: false,
      shelfOptions: [],
      actionLink: process.env.BASE_API + '/product/shelf/addShelfContentByFile?owner=',
      form: {},
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
        'pagination':
          {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        'entity':
          {
            dySku: this.search.dySku,
            name: this.search.name,
            owner: this.search.owner,
          },
      };
      this.tableLoading = true;
      request({
        url: '/statistics/productInWarehouse',
        method: 'post',
        data: postData,
      }).then((ret) => {
        this.tableData = ret.data.data;
        this.tablePage.total = ret.data.page.total;
        this.tableLoading = false;
      });
    },
    fetchProduct(val) {
      request({
        url: '/product/listByUser?username=' + val,
        method: 'get',
      }).then((ret) => {
        this.userProduct = ret.data.data;
        for (let i = 0; i < this.userProduct.length; i++) {
          const product = this.userProduct[i];
          this.userProductMap[product.dySku] = product;
        }
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
    showProductName(val) {
      if (this.userProductMap[val]) {
        this.currContent.name = this.userProductMap[val].name;
      } else {
        this.currContent.name = '';
      }
    },
    closeDialog() {
      this.dialogVisible4ShelfContent = false;
    },
    exportStatistics() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/statistics/excel/productInWarehouse?dySku=' + this.search.dySku + '&name=' + this.search.name + '&owner=' + this.search.owner;
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    handleError(err) {
      this.$message.error(JSON.parse(err.message)['message']);
    },
    downloadTemplate() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/template/file/PRODUCT_IN_WAREHOUSE_TEMPLATE';
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    submitUpload4Excel() {
      if (!this.shelfContent.owner) {
        this.$message.warning('请选择库存属主');
        return;
      }
      this.actionLink = process.env.BASE_API + '/product/shelf/addShelfContentByFile?owner=' + this.shelfContent.owner;
      this.$refs.upload.submit();
    },
  },
};
</script>

<style></style>
