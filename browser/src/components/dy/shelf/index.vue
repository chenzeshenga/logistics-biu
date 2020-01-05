<template>
  <div class="login-container">
    <div class="app-container">
      <el-row>
        <el-col :span="18">
          <el-button type="primary" round @click="dialogVisible = true">
            新建
          </el-button>
        </el-col>
        <el-col :span="6">
          <el-input
              placeholder="请输入货架号或者货架区域"
              v-model="regTxt"
              class="input-with-select"
          >
            <el-button
                icon="el-icon-search"
                slot="append"
                @click="fetch()"
            >搜索
            </el-button>
          </el-input>
        </el-col>
      </el-row>
      <el-table
          style="margin-top: 10px"
          :data="tableData"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column
            width="300"
            prop="shelfNo"
            label="货架号"
        />
        <el-table-column
            width="300"
            prop="area"
            label="货架区域"
        />
        <el-table-column
            width="300"
            prop="rowNo"
            label="货架行数"
        />
        <el-table-column
            width="300"
            prop="layer"
            label="货架层数"
        />
        <el-table-column width="300" label="是否启用">
          <template slot-scope="scope">
            <el-tag
                :type="scope.row.enable ? 'success' : 'danger'"
                disable-transitions
            >
              {{ scope.row.enable ? "启用" : "禁用" }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column width="300" label="操作" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="打印货架标签" placement="top">
              <el-button
                  @click="printShelfNo(scope.$index, scope.row)"
                  size="mini"
                  type="info"
                  circle
                  plain
                  icon="el-icon-printer"
              >
              </el-button>
            </el-tooltip>
            <el-tooltip content="快速启用" placement="top">
              <el-button
                  @click="enable(scope.$index, scope.row)"
                  size="mini"
                  type="success"
                  circle
                  plain
              >
                <svg-icon icon-class="enable"/>
              </el-button>
            </el-tooltip>
            <el-tooltip content="快速禁用" placement="top">
              <el-button
                  @click="disable(scope.$index, scope.row)"
                  size="mini"
                  type="warning"
                  circle
                  plain
              >
                <svg-icon icon-class="disable"/>
              </el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button
                  @click="drop(scope.$index, scope.row)"
                  size="mini"
                  type="danger"
                  icon="el-icon-remove"
                  circle
                  plain
              >
              </el-button>
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
      />
      <el-dialog title="新建货架" :visible.sync="dialogVisible" width="30%">
        <el-row>
          <el-input placeholder="新建货架号" v-model="form.shelfNo" disabled>
            <el-button slot="append" @click="generateShelfNo()"
            >生成货架号
            </el-button
            >
          </el-input>
        </el-row>
        <el-row style="margin-top:2%">
          <el-col :span="10">
            <div class="sub-title">区域</div>
            <el-input v-model="form.area"/>
          </el-col>
          <el-col :span="10" style="margin-left:3%">
            <div class="sub-title">行数</div>
            <el-input v-model="form.rowNo"/>
          </el-col>
        </el-row>
        <el-row style="margin-top:2%">
          <el-col :span="10">
            <div class="sub-title">层数</div>
            <el-input v-model="form.layer"/>
          </el-col>
          <el-col :span="10" style="margin-left:3%">
            <div class="sub-title">启用</div>
            <el-switch
                v-model="form.enable"
                active-color="#13ce66"
                inactive-color="#ff4949"
            >
            </el-switch>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="createShelf()">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="货架标签" :visible.sync="dialogVisible2" width="20%">
        <div id="pdfDom">
          <custom-syncfusion-barcode4-shelf-no v-bind:barcode="barcodeSetting"/>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="getPdf()">下 载</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import request from '../../../utils/service';
  import CustomSyncfusionBarcode4ShelfNo from '../../@syncfusion/customSyncfusionBarcode4ShelfNo';

  export default {
    name: 'shelfTable',
    components: {CustomSyncfusionBarcode4ShelfNo},
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
        dialogVisible2: false,
        form: {
          shelfNo: '',
          area: '',
          rowNo: '',
          layer: '',
          enable: true,
        },
        barcodeSetting: {
          width: 200,
          height: 150,
          value: '',
        },
      };
    },
    props: ['msg'],
    created() {
      this.fetch();
    },
    watch: {
      $route() {
        this.fetch();
      },
    },
    methods: {
      fetch() {
        const filter = this.$route.query.filter;
        if (filter !== undefined && filter.length > 0) {
          this.regTxt = filter;
        }
        this.tableLoading = true;
        request({
          url: '/shelf/list?reg=' + this.regTxt,
          method: 'post',
          data: this.tablePage,
        }).then((res) => {
          this.tableData = res.data.page.records;
          this.tablePage.current = res.data.page.current;
          this.tablePage.pages = res.data.page.pages;
          this.tablePage.size = res.data.page.size;
          this.tablePage.total = res.data.page.total;
          this.tableLoading = false;
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
      enable(index, row) {
        const shelfNo = row.shelfNo;
        request({
          url: '/shelf/enable?shelfNo=' + shelfNo,
          method: 'get',
        }).then(() => {
          this.$message.success('启用成功');
          this.fetch();
        });
      },
      disable(index, row) {
        const shelfNo = row.shelfNo;
        request({
          url: '/shelf/disable?shelfNo=' + shelfNo,
          method: 'get',
        }).then(() => {
          this.$message.success('禁用成功');
          this.fetch();
        });
      },
      drop(index, row) {
        const shelfNo = row.shelfNo;
        request({
          url: '/shelf/drop?shelfNo=' + shelfNo,
          method: 'get',
        }).then(() => {
          this.$message.success('删除成功');
          this.fetch();
        });
      },
      createShelf() {
        request({
          url: '/shelf/add',
          method: 'post',
          data: this.form,
        }).then(() => {
          this.$message.success('添加成功');
          this.dialogVisible = false;
          this.fetch();
        });
      },
      generateShelfNo() {
        request({
          url: '/generate/pk',
          method: 'get',
        }).then((res) => {
          this.form.shelfNo = res.data.data;
        });
      },
      printShelfNo(index, row) {
        this.barcodeSetting.value = row.shelfNo;
        this.dialogVisible2 = true;
      },
    },
  };
</script>
