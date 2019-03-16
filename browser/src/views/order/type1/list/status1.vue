<template>
  <div class="login-container">
    <el-col :offset="6" :span="22" style="margin-top: 10px;margin-bottom: 10px" class="block">
      <el-date-picker v-model="daterange" type="daterange" align="right" unlink-panels range-separator="至" start-placeholder="开始日期"
                      end-placeholder="结束日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd" style="width: 400px">
      </el-date-picker>
      <el-button icon="el-icon-search" @click="searchOrd()"></el-button>
      <el-button type="primary" @click="route2NewOrd()" style="margin-left: 400px">新建订单</el-button>
    </el-col>
    <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe border fit
              highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <el-col :span="12">
            <el-table :data="tableData.row.contentList" stripe border>
              <el-table-column prop="sku" label="sku/东岳Sku" width="200"></el-table-column>
              <el-table-column prop="name" label="商品名称" width="300"></el-table-column>
              <el-table-column prop="price" label="商品价格" width="180"></el-table-column>
              <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
            </el-table>
          </el-col>
        </template>
      </el-table-column>
      <el-table-column width="160" prop="orderNo" label="订单号"></el-table-column>
      <el-table-column width="150" prop="categoryName" label="订单类型"></el-table-column>
      <el-table-column width="200" prop="channelDesc" label="运送渠道"></el-table-column>
      <el-table-column width="100" prop="statusDesc" label="订单状态"></el-table-column>
      <el-table-column width="150" prop="carrierName" label="承运人"></el-table-column>
      <el-table-column width="170" prop="trackNo" label="追踪单号"></el-table-column>
      <el-table-column width="250" prop="fromAddressDesc" label="寄件地址"></el-table-column>
      <el-table-column width="150" prop="fromName" label="寄件人"></el-table-column>
      <el-table-column width="150" prop="fromContact" label="寄件人联系方式"></el-table-column>
      <el-table-column width="150" prop="fromZipCode" label="寄件人邮编"></el-table-column>
      <el-table-column width="250" prop="toAddressDesc" label="收件地址"></el-table-column>
      <el-table-column width="150" prop="toName" label="收件人"></el-table-column>
      <el-table-column width="150" prop="toContact" label="收件人联系方式"></el-table-column>
      <el-table-column width="150" prop="toZipCode" label="收件人邮编"></el-table-column>
      <el-table-column width="80" prop="collect" label="是否代收商品费用"></el-table-column>
      <el-table-column width="170" prop="createOn" label="创建时间"></el-table-column>
      <el-table-column width="170" prop="updateOn" label="修改时间"></el-table-column>
      <el-table-column width="150" prop="creator" label="创建人"></el-table-column>
      <el-table-column width="150" prop="updator" label="修改人"></el-table-column>
      <el-table-column label="操作" width="250" fixed="right">
        <template slot-scope="scope">
          <el-tooltip content="提交拣货" placement="top">
            <el-button @click="statusUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-check" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="申请单号" placement="top">
            <el-button @click="applyTrackno(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-info" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="编辑" placement="top">
            <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-edit" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="废弃" placement="top">
            <el-button @click="abandon(scope.$index,scope.row)" size="mini" type="danger" icon="el-icon-remove" circle plain></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="tablePage.current"
                   :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size" layout="total, sizes, prev, pager, next, jumper"
                   :total="tablePage.total">
    </el-pagination>
    <!--:before-close="handleClose"-->
    <el-dialog title="申请单号" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form">
        <el-col :span="12">
          <el-form-item label="承运人">
            <el-cascader :options="carrier" v-model="form.selectedCarrier" @change="handleCarrierChange" filterable></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="追踪单号">
            <el-input v-model="form.trackNo" placeholder="您可点击按钮预先获取单号，后台操作员将根据实际情况进行调整">
              <el-button slot="append" @click="getOrdNo2">获取单号</el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="fillInTrackNo">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>


<script>
  import request from '@/utils/request'

  export default {
    name: 'order-list-mgt-type1-status1',
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        },
        tableLoading: false,
        tableData: [],
        daterange: null,
        pickerOptions2: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        dialogVisible: false,
        carrier: [],
        form: {
          orderNo: "",
          selectedCarrier: [],
          carrierNo: "",
          trackNo: ""
        }
      }
    },
    created() {
      this.fetchData();
      this.initTrackno();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: "ord/list/1/1",
          method: "post",
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        })
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
        this.$router.push({path: '/new-order/index?ordno=' + row.orderNo + "&status=1"})
      },
      statusUpdate(index, row) {
        this.$confirm('您确定要提交该订单？', '提示', confirm).then(() => {
          request({
            url: "ord/update/1/" + row.orderNo + "/2",
            method: "get"
          }).then(res => {
            this.fetchData();
            this.$message.success("提交成功");
          })
        }).catch(() => {
          this.$message.info("已取消提交")
        })
      },
      abandon(index, row) {
        this.$confirm('您确定要废弃该订单？', '提示', confirm).then(() => {
          request({
            url: "ord/update/1/" + row.orderNo + "/5",
            method: "get"
          }).then(res => {
            this.fetchData();
            this.$message.success("废弃成功");
          })
        }).catch(() => {
          this.$message.info("已取消废弃")
        })
      },
      searchOrd() {
        if (this.daterange == null || this.daterange[0] === 0 || this.daterange[1] === 0) {
          this.$message.warning("请选择您想要查询的日期范围");
          return;
        }
        this.tableLoading = true;
        request({
          url: "ord/list/1/1/" + this.daterange[0] + "/" + this.daterange[1],
          method: "post",
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        })
      },
      route2NewOrd() {
        this.$router.push({path: '/new-order/index'});
      },
      initTrackno() {
        request({
          url: "ord/carrier/distinct",
          method: "get"
        }).then(res => {
          this.carrier = res.data.data;
        });
      },
      applyTrackno(index, row) {
        this.form.orderNo = row.orderNo;
        this.form.carrierNo = row.carrierNo;
        this.form.trackNo = row.trackNo;
        let tmpNo = [];
        tmpNo.push("carrier_" + row.carrierNo);
        this.form.selectedCarrier = tmpNo;
        this.dialogVisible = true;
      },
      handleCarrierChange(value) {
        console.log(value);
        this.form.carrierNo = value[0];
      },
      getOrdNo2() {
        request({
          url: "/generate/pk",
          method: 'get'
        }).then(res => {
          this.form.trackNo = res.data.data;
        })
      },
      fillInTrackNo() {
        request({
          url: "ord/trackno",
          method: "post",
          data: this.form
        }).then(res => {
          console.log(res);
          this.$message.success("追踪单号申请成功");
          this.dialogVisible = false;
          this.fetchData();
        }).catch(err => {
          console.log(err);
        })
      }
    }
  }

</script>
