<template>
  <div class="login-container">
    <div class="app-container">
      <el-form>
        <el-row :gutter="20" style="margin-left: 4%">
          <el-col :span="6">
            <el-tooltip
                content="请输入或者扫描商品sku"
                placement="top"
            >
              <el-input
                  placeholder="请输入或者扫描商品sku"
                  clearable
                  v-model="search.sku"
                  @clear="searchProduct"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="6">
            <el-tooltip
                content="请输入或者扫描商品东岳sku"
                placement="top"
            >
              <el-input
                  placeholder="请输入或者扫描商品东岳sku"
                  clearable
                  v-model="search.dySku"
                  @clear="searchProduct"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="6">
            <el-tooltip content="请输入商品名称" placement="top">
              <el-input
                  placeholder="请输入商品名称"
                  clearable
                  v-model="search.productName"
                  @clear="searchProduct"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="创建人" placement="top">
              <el-select
                  filterable
                  clearable
                  v-model="search.createdBy"
                  placeholder="请选择创建人"
              >
                <el-option
                    v-for="creator in users"
                    :key="creator.uname"
                    :label="creator.nick"
                    :value="creator.uname"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="1">
            <el-form-item label="">
              <el-button
                  icon="el-icon-search"
                  @click="searchProduct()"
              ></el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table
          :data="tableData"
          element-loading-text="加载中"
          highlight-current-row
          stripe
          style="width: 100%;margin: 10px"
          v-loading.body="tableLoading"
          @expand-change="expandChange"
      >
        <el-table-column type="expand">
          <template slot-scope="tableData">
            <el-table :data="tableData.row.productOnShelfList">
              <el-table-column
                  prop="sku"
                  label="sku"
                  width="150"
              ></el-table-column>
              <el-table-column
                  prop="dySku"
                  label="东岳sku"
                  width="150"
              ></el-table-column>
              <el-table-column
                  prop="name"
                  label="商品名称"
                  width="300"
              ></el-table-column>
              <el-table-column
                  prop="location"
                  label="货架位置"
                  width="180"
              ></el-table-column>
              <el-table-column
                  prop="num"
                  label="商品数量"
                  width="180"
              ></el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column
            label="图片"
            width="150"
        >
          <template slot-scope="tableData">
            <img :src="tableData.row.imgPath" alt="商品" style="width: 100%">
          </template>
        </el-table-column>
        <el-table-column
            label="sku"
            prop="sku"
            width="150"
        ></el-table-column>
        <el-table-column
            label="东岳sku"
            prop="dySku"
            width="150"
        ></el-table-column>
        <el-table-column
            label="状态"
            prop="statusDesc"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品名称"
            prop="productName"
            width="200"
        ></el-table-column>
        <el-table-column
            label="商品类型"
            prop="categoryName"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品颜色"
            prop="color"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品尺寸"
            prop="size"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品长度(cm)"
            prop="length"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品宽度(cm)"
            prop="width"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品高度(cm)"
            prop="height"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品重量(kg)"
            prop="weight"
            width="150"
        ></el-table-column>
        <el-table-column
            label="商品价格JPY"
            prop="price"
            width="150"
        ></el-table-column>
        <el-table-column
            label="创建者"
            prop="createdBy"
            width="150"
        ></el-table-column>
        <el-table-column
            label="更新者"
            prop="updateBy"
            width="150"
        ></el-table-column>
        <el-table-column
            label="创建时间"
            prop="createOn"
            width="160"
        ></el-table-column>
        <el-table-column
            label="更新时间"
            prop="updateOn"
            width="160"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="200">
          <template slot-scope="scope">
            <el-tooltip content="编辑" placement="top">
              <el-button
                  @click="handleUpdate(scope.$index, scope.row)"
                  circle
                  icon="el-icon-edit"
                  plain
                  size="mini"
                  type="info"
              ></el-button>
            </el-tooltip>
            <el-tooltip content="打印商品标签" placement="top">
              <el-button
                  @click="handlePrint(scope.$index, scope.row)"
                  circle
                  plain
                  size="mini"
                  type="info"
              >
                <svg-icon icon-class="print"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button
                  @click="handleDelete(scope.$index, scope.row)"
                  circle
                  icon="el-icon-remove"
                  plain
                  size="mini"
                  type="danger"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          :current-page="tablePage.current"
          :page-size="tablePage.size"
          :page-sizes="[10, 20, 30, 40, 50]"
          :total="tablePage.total"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          layout="total, sizes, prev, pager, next, jumper"
      >
      </el-pagination>
      <el-dialog title="商品标签" :visible.sync="dialogTableVisible" width="35%">
        <div style="margin-bottom: 2%;padding: 3%">
          <el-radio v-model="radio" label="1">备选项</el-radio>
          <el-radio v-model="radio" label="2">备选项</el-radio>
          <el-radio v-model="radio" label="2">备选项</el-radio>
        </div>
        <div id="productBarcode" style="border-style: solid;border-width:1px;height:841.89px;width:595.28px  ">
          <div v-for="i in 10" v-bind:key="i" style="margin-top: 1px;margin-left: 9px">
            <div v-for="j in 4" v-bind:key="j"
                 style="display: table-cell;vertical-align:middle;border-style: dashed;border-width:0.5px">
              <syncfusion-barcode-product ref="barcode" v-bind:barcode="barcode"
                                          :key="timer"></syncfusion-barcode-product>
            </div>
          </div>
        </div>
        <el-button @click="downloadPDF('#productBarcode','test')">打印</el-button>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '../../../utils/service';
import SyncfusionBarcodeProduct from '../../../components/@syncfusion/syncfusion-barcode-product';


export default {
  name: 'status1',
  components: {SyncfusionBarcodeProduct},
  data() {
    return {
      timer: new Date().getTime(),
      tablePage: {
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      search: {
        sku: '',
        dySku: '',
        productName: '',
        createdBy: '',
      },
      users: [],
      dialogTableVisible: false,
      barcode: {
        value: '23424324328439028',
      },
      radio: '1',
    };
  },
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
    expandChange(row, expandedRows) {
      console.log(row);
      console.log(expandedRows);
      row.productOnShelfList = [
        {
          'sku': '123',
          'dySku': '123',
          'name': '123',
          'location': '123',
        },
      ];
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
    fetchData() {
      this.tableLoading = true;
      const postData = {
        'pagination': this.tablePage,
        'entity': this.search,
      };
      request({
        url: 'product/list/1',
        method: 'post',
        data: postData,
      }).then((res) => {
        this.tableData = res.data.data;
        this.tablePage = res.data.page;
        this.tableLoading = false;
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
      console.log(row);
      this.$confirm('您确定审核通过该商品？', '提示', confirm)
          .then(() => {
            request({
              url: '/product/status/' + row.sku + '/1',
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
      console.log(row);
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
    handlePrint(index, row) {
      this.dialogTableVisible = true;
      this.barcode.value = row.dySku;
      this.barcode.name = row.productName;
      this.timer = new Date().getTime();
    },
    searchProduct() {
      this.fetchData();
    },
  },
};
</script>

<style scoped>
.div-center {
  display: -webkit-box;
  -webkit-box-orient: horizontal;
  -webkit-box-pack: center;
  -webkit-box-align: center;

  display: -moz-box;
  -moz-box-orient: horizontal;
  -moz-box-pack: center;
  -moz-box-align: center;

}
</style>
