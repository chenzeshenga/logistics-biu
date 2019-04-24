<template>
  <div class="login-container">
    <div class="app-container">
      <el-form>
        <el-form-item>
          <el-row :gutter="20" style="margin-left: 4%">
            <el-col :span="6">
              <el-tooltip content="订单创建时间" placement="top">
                <el-date-picker v-model="daterange" type="daterange" align="right" unlink-panels range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd"
                                style="width: 400px">
                </el-date-picker>
              </el-tooltip>
            </el-col>
            <el-col :span="4">
              <el-tooltip content="订单号" placement="top">
                <el-input v-model="search.ordno" clearable placeholder="请输入订单号"></el-input>
              </el-tooltip>
            </el-col>
            <el-col :span="4">
              <el-tooltip content="创建人" placement="top">
                <el-select filterable clearable v-model="search.creator" placeholder="请选择创建人">
                  <el-option v-for="creator in users" :key="creator.uname" :label="creator.nick"
                             :value="creator.uname"></el-option>
                </el-select>
              </el-tooltip>
            </el-col>
            <el-col :span="4">
              <el-tooltip content="相关渠道" placement="top">
                <el-select clearable filterable v-model="search.channelCode" placeholder="对应渠道">
                  <el-option v-for="item in channels" :key="item.value" :label="item.label"
                             :value="item.value"></el-option>
                </el-select>
              </el-tooltip>
            </el-col>
            <el-col :span="1">
              <el-form-item label="">
                <el-button icon="el-icon-search" @click="searchOrd()"></el-button>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
            <el-col :span="2">
              <el-button type="primary" @click="applyTrackNo()" v-if="multiSelection">
                批量申请单号
              </el-button>
            </el-col>
            <el-col :span="2">
              <el-button type="primary" @click="batchStatusUpdate()" v-if="multiSelection">
                批量提交
              </el-button>
            </el-col>
            <el-col :span="2">
              <el-button type="primary" @click="route2NewOrd()">新建订单</el-button>
            </el-col>
            <el-col :span="2">
              <el-button type="primary" @click="exportExcel()"
                         icon="iconfont icon-jichukongjiantubiao-gonggongxuanzekuang">导出excel
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe highlight-current-row
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="tableData">
            <el-col :span="12">
              <el-table
                :data="tableData.row.contentList"
                stripe border>
                <el-table-column
                  prop="sku"
                  label="sku/东岳Sku"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="name"
                  label="商品名称"
                >
                </el-table-column>
                <el-table-column
                  prop="price"
                  label="商品价格JPY"
                  width="180">
                </el-table-column>
                <el-table-column
                  prop="num"
                  label="商品数量"
                  width="180">
                </el-table-column>
              </el-table>
            </el-col>
          </template>
        </el-table-column>

        <el-table-column
          width="160"
          prop="orderNo"
          label="订单号"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="categoryName"
          label="订单类型"
        ></el-table-column>
        <el-table-column
          width="200"
          prop="channelDesc"
          label="运送渠道"
        ></el-table-column>
        <el-table-column
          width="100"
          prop="statusDesc"
          label="订单状态"
        ></el-table-column>
        <el-table-column
          width="250"
          prop="fromAddressDesc"
          label="寄件地址"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="fromName"
          label="寄件人"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="fromContact"
          label="寄件人联系方式"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="fromZipCode"
          label="寄件人邮编"
        ></el-table-column>
        <el-table-column
          width="250"
          prop="toAddressDesc"
          label="收件地址"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="toName"
          label="收件人"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="toContact"
          label="收件人联系方式"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="toZipCode"
          label="收件人邮编"
        ></el-table-column>
        <el-table-column
          prop="collect"
          width="80"
          label="是否代收商品费用"
        ></el-table-column>
        <el-table-column label="操作" width="250" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="提交" placement="top">
              <el-button @click="handleUpdate(scope.$index,scope.row)" size="small" type="info" icon="el-icon-check" circle plain></el-button>
            </el-tooltip>
            <el-tooltip content="编辑" placement="top">
              <el-button @click="handleUpdate(scope.$index,scope.row)" size="small" type="info" icon="el-icon-edit" circle plain></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button @click="handleDelete(scope.$index,scope.row)" size="small" type="danger" icon="el-icon-delete" circle plain></el-button>
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
        :total="tablePage.total">
      </el-pagination>
    </div>
  </div>
</template>


<script>
  import request from '@/utils/service';

  export default {
    name: 'order-list-mgt-type1-status1',
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
        daterange: null,
        pickerOptions2: {
          shortcuts: [
            {
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
        multiSelection: false,
        search: {
          ordno: '',
          creator: '',
          channelCode: '',
        },
        users: [],
        channels: [],
      };
    },
    created() {
      this.fetchData();
    },
    watch: {
      $route() {
        this.onQuickSearch();
      },
    },
    methods: {
      reRouter() {
        this.$router.push({
          path: '/order-list/mgt/type1/status6',
        });
      },
      onQuickSearch() {
        const ordno = this.$route.query.ordno;
        if (ordno !== undefined && ordno.length > 0) {
          this.search.ordno = ordno;
          this.searchOrd();
          this.reRouter();
        }
      },
      fetchData() {
        this.tableLoading = true;
        request({
          url: 'ord/list/1/6',
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
          this.tablePage.total = res.data.page.total;
          this.tablePage.current = res.data.page.current;
          this.tablePage.size = res.data.page.size;
          this.onQuickSearch();
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
        this.$router.push({path: '/new-order/index?ordno=' + row.orderNo});
      },
      handleDelete(index, row) {
        this.$confirm('您确定要永久删除该记录？', '提示', confirm).then(() => {
          request({
            url: 'ord/delete/' + row.orderNo,
            method: 'get',
          }).then(res => {
            this.fetchData();
            this.$message.success('删除成功');
          });
        }).catch(() => {
          this.$message.info('已取消删除');
        });
      },
      searchOrd() {
        let url = '';
        if (this.daterange == null || this.daterange[0] === 0 || this.daterange[1] === 0) {
          this.$message.warning('请选择您想要查询的日期范围');
          url = 'ord/list/1/6/2000-01-01/2099-01-01?ordno=' + this.search.ordno + '&creator=' + this.search.creator +
            '&channelCode=' + this.search.channelCode;
        } else {
          url = 'ord/list/1/6/' + this.daterange[0] + '/' + this.daterange[1] + '?ordno=' + this.search.ordno + '&creator=' + this.search.creator +
            '&channelCode=' + this.search.channelCode;
        }
        this.tableLoading = true;
        request({
          url: url,
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        });
      },
      handleSelectionChange(val) {
        this.multiSelection = (val.length > 0);
        this.ord4TrackNo = [];
        val.forEach(ord => {
          this.ord4TrackNo.push(ord.orderNo);
        });
      },
      initTrackno() {
        request({
          url: 'ord/carrier/distinct',
          method: 'get',
        }).then(res => {
          this.carrier = res.data.data;
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
        }).then(res => {
          this.users = res.data.page.records;
        });
      },
      initChannel() {
        request({
          url: '/channel/list',
          method: 'get',
        }).then(res => {
          this.channels = res.data.data;
        });
      },
    },
  };

</script>
