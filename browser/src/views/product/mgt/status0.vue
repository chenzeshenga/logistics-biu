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
                                v-model="search.name"
                                @clear="searchProduct"
                            ></el-input>
                        </el-tooltip>
                    </el-col>
                    <el-col :span="4">
                        <el-tooltip content="创建人" placement="top">
                            <el-select
                                filterable
                                clearable
                                v-model="search.creator"
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
            <el-row :gutter="20">
                <el-col :span="2" :offset="2" v-if="multiSelected">
                    <el-button type="primary" @click="batchUpdate"
                        >批量审核</el-button
                    >
                </el-col>
                <el-col :offset="4" :span="2">
                    <el-button type="primary" @click="route2New"
                        >新建商品</el-button
                    >
                </el-col>
                <el-col :offset="4" :span="2">
                    <el-button type="primary" @click="export2Excel"
                        >导出</el-button
                    >
                </el-col>
            </el-row>
            <el-table
                style="width: 100%;margin: 10px"
                :data="tableData"
                v-loading.body="tableLoading"
                element-loading-text="加载中"
                stripe
                highlight-current-row
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column
                    width="150"
                    prop="sku"
                    label="sku"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="dySku"
                    label="东岳sku"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="statusDesc"
                    label="状态"
                ></el-table-column>
                <el-table-column
                    width="200"
                    prop="productName"
                    label="商品名称"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="categoryName"
                    label="商品类型"
                ></el-table-column>
                <el-table-column
                    prop="color"
                    label="商品颜色"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="size"
                    label="商品尺寸"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="length"
                    label="商品长度(cm)"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="width"
                    label="商品宽度(cm)"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="height"
                    label="商品高度(cm)"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="weight"
                    label="商品重量(kg)"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="price"
                    label="商品价格JPY"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="createdBy"
                    label="创建者"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="updateBy"
                    label="更新者"
                    width="150"
                ></el-table-column>
                <el-table-column
                    prop="createOn"
                    label="创建时间"
                    width="160"
                ></el-table-column>
                <el-table-column
                    prop="updateOn"
                    label="更新时间"
                    width="160"
                ></el-table-column>
                <el-table-column label="操作" width="200" fixed="right">
                    <template slot-scope="scope">
                        <el-tooltip content="审核" placement="top">
                            <el-button
                                @click="statusUpdate(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="编辑" placement="top">
                            <el-button
                                @click="handleUpdate(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-edit"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="删除" placement="top">
                            <el-button
                                @click="handleDelete(scope.$index, scope.row)"
                                size="mini"
                                type="danger"
                                icon="el-icon-remove"
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
            <el-dialog
                title="商品体积重量确认"
                :visible.sync="dialogVisible"
                width="30%"
            >
                <el-alert
                    title="请在当前页面再一次确认商品体积重量，也可在在库商品管理页面进行编辑"
                    type="info"
                    show-icon
                >
                </el-alert>
                <el-form ref="form" :model="form" label-width="100px">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-form-item label="sku">
                                <span>{{ form.sku }}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="东岳sku">
                                <span>{{ form.sku }}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label="体积(cm^3)">
                        <el-form-item label="长(cm)">
                            <el-col :span="8">
                                <el-input-number
                                    v-model="form.length"
                                ></el-input-number>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="宽(cm)" style="margin-top: 2%">
                            <el-col :span="8">
                                <el-input-number
                                    v-model="form.width"
                                ></el-input-number>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="高(cm)" style="margin-top: 2%">
                            <el-col :span="8">
                                <el-input-number
                                    v-model="form.height"
                                ></el-input-number>
                            </el-col>
                        </el-form-item>
                    </el-form-item>
                    <el-form-item label="重量(kg)">
                        <el-form-item label="重量">
                            <el-input-number
                                v-model="form.weight"
                            ></el-input-number>
                        </el-form-item>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="statusUpdateInDialog"
                        >审核通过</el-button
                    >
                    <el-button @click="dialogVisible = false">取消</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import request from '../../../utils/service';

export default {
  name: 'status0',
  data() {
    return {
      tablePage: {
        current: null,
        pages: null,
        size: null,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      multiSelected: false,
      skus: [],
      form: {
        sku: null,
        dySku: null,
        length: null,
        width: null,
        height: null,
        weight: null,
      },
      users: [],
      search: {
        sku: '',
        dySku: '',
        name: '',
        creator: '',
      },
      dialogVisible: false,
    };
  },
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
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
    searchProduct() {
      this.tableLoading = true;
      request({
        url: 'product/list/0',
        method: 'post',
        data: {
          current: this.tablePage.current,
          size: this.tablePage.size,
          sku: this.search.sku,
          dySku: this.search.dySku,
          creator: this.search.creator,
          name: this.search.name,
        },
      }).then((res) => {
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
    fetchData() {
      this.tableLoading = true;
      request({
        url: '/product/list/0',
        method: 'post',
        data: {
          current: this.tablePage.current,
          size: this.tablePage.size,
        },
      }).then((res) => {
        this.tableData = res.data.page.records;
        this.tablePage.total = res.data.page.total;
        this.tablePage.current = res.data.page.current;
        this.tablePage.size = res.data.page.size;
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
      this.form.sku = row.sku;
      this.form.dySku = row.dySku;
      this.form.length = row.length;
      this.form.width = row.width;
      this.form.height = row.height;
      this.form.weight = row.weight;
      this.dialogVisible = true;
    },
    statusUpdateInDialog() {
      this.$confirm('您确定审核通过该商品？', '提示', confirm)
          .then(() => {
            request({
              url: '/product/update',
              method: 'post',
              data: this.form,
            }).then(() => {
              this.$message.success('体积重量信息更新成功');
              request({
                url: '/product/status/' + this.form.sku + '/1',
                method: 'get',
              }).then(() => {
                this.$message.success('审核成功');
                this.dialogVisible = false;
                this.fetchData();
              });
            });
          })
          .catch(() => {
            this.$message.info('已取消审核');
            this.dialogVisible = false;
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
    handleSelectionChange(val) {
      this.multiSelected = val.length > 1;
      for (let i = 0; i < val.length; i++) {
        this.skus.push(val[i]['sku']);
      }
    },
    batchUpdate() {
      this.$confirm('您确定要审核这些商品吗？', '提示', confirm)
          .then(() => {
            request({
              url: '/product/update/approval',
              method: 'post',
              data: this.skus,
            }).then(() => {
              this.$message.success('更新成功');
              this.fetchData();
            });
          })
          .catch(() => {
            this.$message.info('已取消审核');
          });
    },
    route2New() {
      this.$router.push({
        path: '/new-product/new-product',
      });
    },
    export2Excel() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/product/excel/0';
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
  },
};
</script>
