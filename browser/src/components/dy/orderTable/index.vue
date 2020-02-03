<template>
  <div>
    <el-form>
      <el-form-item>
        <el-row :gutter="20" style="margin-left: 4%">
          <el-col :span="6">
            <el-tooltip content="订单创建时间" placement="top">
              <el-date-picker
                  v-model="daterange"
                  type="daterange"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :picker-options="pickerOptions2"
                  value-format="yyyy-MM-dd"
                  style="width: 400px"
              >
              </el-date-picker>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="订单号" placement="top">
              <el-input
                  v-model="search.ordno"
                  clearable="clearable"
                  placeholder="请输入订单号"
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
                  value=""
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
          <el-col :span="4">
            <el-tooltip content="相关渠道" placement="top">
              <el-select
                  value=""
                  clearable
                  filterable
                  v-model="search.channelCode"
                  placeholder="对应渠道"
              >
                <el-option
                    v-for="item in channels"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="1">
            <el-form-item label="">
              <el-button
                  icon="el-icon-search"
                  @click="searchOrd()"
              ></el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
          <el-col :span="2">
            <el-button
                type="primary"
                @click="applyTrackNo()"
                v-if="multiSelection"
            >
              批量申请单号
            </el-button>
          </el-col>
          <el-col :span="2">
            <el-button
                type="primary"
                @click="batchStatusUpdate()"
                v-if="multiSelection"
            >
              批量提交
            </el-button>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" @click="route2NewOrd()"
            >新建订单
            </el-button
            >
          </el-col>
          <el-col :span="2">
            <el-button
                type="primary"
                @click="exportExcel()"
                icon="iconfont icon-jichukongjiantubiao-gonggongxuanzekuang"
            >导出excel
            </el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <el-table
        style="width: 100%"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <el-table
              :data="tableData.row.contentList"
              show-summary
              :summary-method="getSummary"
              style="margin-bottom: 0"
          >
            <el-table-column
                prop="sku"
                label="东岳Sku"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
                width="300"
            ></el-table-column>
            <el-table-column
                prop="price"
                label="商品价格(JPY)"
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
          width="180"
          prop="orderNo"
          label="订单号"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="categoryName"
          label="订单类型"
      ></el-table-column>
      <el-table-column width="200" label="运送渠道">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>渠道名称: {{ scope.row.channelDesc }}</p>
            <p>渠道编码: {{ scope.row.channel }}</p>
            <p>
              <el-button
                  type="text"
                  v-on:click="
                                    route2ChannelPage(scope.$index, scope.row)
                                "
              >查看详情
              </el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium"
              >{{ scope.row.channelDesc }}
              </el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          width="100"
          prop="statusDesc"
          label="订单状态"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="carrierName"
          label="承运人"
      ></el-table-column>
      <el-table-column
          width="170"
          prop="trackNo"
          label="追踪单号"
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
          width="80"
          prop="collect"
          label="是否代收商品费用"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="collectNum"
          label="总计代收费用"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="length"
          label="长(cm)"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="width"
          label="宽(cm)"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="height"
          label="高(cm)"
      ></el-table-column>
      <el-table-column
          width="120"
          prop="totalVolumeFrontEnd"
          label="总体积(cm^3)"
      ></el-table-column>
      <el-table-column
          width="120"
          prop="sum"
          label="三边和(cm)"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="totalVolumeWithWeight"
          label="体积重"
      ></el-table-column>
      <el-table-column
          width="120"
          prop="totalWeight"
          label="总重量(kg)"
      ></el-table-column>
      <el-table-column
          width="170"
          prop="createOn"
          label="创建时间"
      ></el-table-column>
      <el-table-column
          width="170"
          prop="updateOn"
          label="修改时间"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="creator"
          label="创建人"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="updator"
          label="修改人"
      ></el-table-column>
      <el-table-column label="操作" width="350" fixed="right">
        <template slot-scope="scope">
          <el-tooltip
              content="提交拣货"
              placement="top"
              v-if="msgData.buttonVisible1"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="申请单号"
              placement="top"
              v-if="msgData.buttonVisible2"
          >
            <el-button
                @click="applyTrackno(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-info"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="编辑"
              placement="top"
              v-if="msgData.buttonVisible3"
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
          <el-tooltip
              content="打印配货单"
              placement="top"
              v-if="msgData.buttonVisible4"
          >
            <el-button
                @click="print(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-printer"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="废弃"
              placement="top"
              v-if="msgData.buttonVisible5"
          >
            <el-button
                @click="abandon(scope.$index, scope.row)"
                size="mini"
                type="danger"
                icon="el-icon-remove"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="提交发货"
              placement="top"
              v-if="msgData.buttonVisible6"
          >
            <el-button
                @click="
                                triggerVolumeAndWeight(scope.$index, scope.row)
                            "
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="快速拣货"
              placement="top"
              v-if="msgData.buttonVisible11"
          >
            <el-button
                @click="
                                route2PickUpPage(scope.$index, scope.row)
                            "
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="编辑体积重量"
              placement="top"
              v-if="msgData.buttonVisible10"
          >
            <el-button
                @click="
                                triggerVolumeAndWeightWithOutStatus(
                                    scope.$index,
                                    scope.row
                                )
                            "
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="归档"
              placement="top"
              v-if="msgData.buttonVisible7"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="查询物流进度"
              placement="top"
              v-if="msgData.buttonVisible8"
          >
            <el-button
                @click="findWhere(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-question"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="还原"
              placement="top"
              v-if="msgData.buttonVisible9"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
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
    >
    </el-pagination>
    <el-dialog title="申请单号" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form">
        <el-col :span="12">
          <el-form-item label="承运人">
            <el-cascader
                :options="carrier"
                v-model="form.selectedCarrier"
                @change="handleCarrierChange"
                filterable
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="追踪单号">
            <el-input
                v-model="form.trackNo"
                placeholder="请填写对应的单号"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="fillInTrackNo"
                >确 定</el-button
                >
            </span>
    </el-dialog>
    <el-dialog
        title="批量申请单号"
        :visible.sync="dialogVisibleList"
        width="30%"
    >
      <div style="margin-bottom: 20px">
        <span>请选择承运人,系统将自动分配追踪单号</span>
      </div>
      <el-form :model="form">
        <el-col :span="24">
          <el-form-item label="承运人">
            <el-cascader
                :options="carrier"
                v-model="form.selectedCarrier"
                @change="handleCarrierChange"
                filterable
            ></el-cascader>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibleList = false">取 消</el-button>
                <el-button type="primary" @click="fillInTrackNoList"
                >确 定</el-button
                >
            </span>
    </el-dialog>
    <el-dialog title="提交发货" :visible.sync="dialogVisible2" width="50%">
      <el-form :model="form">
        <el-col :span="24">
          <el-form-item label="订单号">
            <el-input v-model="form.orderNo" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24" style="margin-top: 10px">
          <el-col :span="7">
            <el-form-item label="长(cm)">
              <el-input-number
                  v-model="form.length"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="宽(cm)">
              <el-input-number
                  v-model="form.width"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="高(cm)">
              <el-input-number
                  v-model="form.height"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="2">
            <el-tooltip content="计算" placement="top">
              <el-button circle @click="calculateIndex">
                <svg-icon icon-class="calculate"></svg-icon>
              </el-button>
            </el-tooltip>
          </el-col>
          <el-col :span="24" style="margin-bottom: 2%">
            <label>根据页面输入计算结果如下(仅供参考):</label>
          </el-col>
          <el-form-item style="margin-top: 2%">
            <el-col :span="8">
              <el-tooltip
                  placement="top"
                  content="总体积(cm^3)=长*宽*高"
              >
                <el-form-item label="总体积(cm^3)">
                  <el-input-number
                      v-model="form.totalVolumeFrontEnd"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
            <el-col :span="8">
              <el-tooltip
                  content="三边和(cm)=长+宽+高"
                  placement="top"
              >
                <el-form-item label="三边和(cm)">
                  <el-input-number
                      v-model="form.sum"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
            <el-col :span="8">
              <el-tooltip
                  content="体积重=长*宽*高/6000"
                  placement="top"
              >
                <el-form-item label="体积重(cm^3)">
                  <el-input-number
                      v-model="form.totalVolumeWithWeight"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="当前订单总重量(kg)">
            <el-input-number
                v-model="form.totalWeight"
            ></el-input-number>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="updateOrd">确 定</el-button>
            </span>
    </el-dialog>
    <el-dialog
        title="编辑订单体积重量"
        :visible.sync="dialogVisible3"
        width="50%"
    >
      <el-form :model="form">
        <el-col :span="24">
          <el-form-item label="订单号">
            <el-input v-model="form.orderNo" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24" style="margin-top: 10px">
          <el-col :span="7">
            <el-form-item label="长(cm)">
              <el-input-number
                  v-model="form.length"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="宽(cm)">
              <el-input-number
                  v-model="form.width"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="高(cm)">
              <el-input-number
                  v-model="form.height"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="2">
            <el-tooltip content="计算" placement="top">
              <el-button circle @click="calculateIndex">
                <svg-icon icon-class="calculate"></svg-icon>
              </el-button>
            </el-tooltip>
          </el-col>
          <el-col :span="24" style="margin-bottom: 2%">
            <label>根据页面输入计算结果如下(仅供参考):</label>
          </el-col>
          <el-form-item style="margin-top: 2%">
            <el-col :span="8">
              <el-tooltip
                  placement="top"
                  content="总体积(cm^3)=长*宽*高"
              >
                <el-form-item label="总体积(cm^3)">
                  <el-input-number
                      v-model="form.totalVolumeFrontEnd"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
            <el-col :span="8">
              <el-tooltip
                  content="三边和(cm)=长+宽+高"
                  placement="top"
              >
                <el-form-item label="三边和(cm)">
                  <el-input-number
                      v-model="form.sum"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
            <el-col :span="8">
              <el-tooltip
                  content="体积重=长*宽*高/6000"
                  placement="top"
              >
                <el-form-item label="体积重(cm^3)">
                  <el-input-number
                      v-model="form.totalVolumeWithWeight"
                  ></el-input-number>
                </el-form-item>
              </el-tooltip>
            </el-col>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="当前订单总重量(kg)">
            <el-input-number
                v-model="form.totalWeight"
            ></el-input-number>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible3 = false">取 消</el-button>
                <el-button type="primary" @click="updateOrdWithOutStatus"
                >确 定</el-button
                >
            </span>
    </el-dialog>
  </div>
</template>

<script>
  import request from '../../../utils/service';

  export default {
    name: 'orderTable',
    data() {
      return {
        msgData: {
          category: this.msg.category,
          status: this.msg.status,
          statusTo: this.msg.statusTo,
          statusAbandon: this.msg.statusAbandon,
          buttonVisible1: this.msg.buttonVisible1,
          buttonVisible2: this.msg.buttonVisible2,
          buttonVisible3: this.msg.buttonVisible3,
          buttonVisible4: this.msg.buttonVisible4,
          buttonVisible5: this.msg.buttonVisible5,
          buttonVisible6: this.msg.buttonVisible6,
          buttonVisible7: this.msg.buttonVisible7,
          buttonVisible8: this.msg.buttonVisible8,
          buttonVisible9: this.msg.buttonVisible9,
          buttonVisible10: this.msg.buttonVisible10,
          buttonVisible11: this.msg.buttonVisible11,
        },
        tablePage: {
          current: 1,
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
                start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 7
                );
                picker.$emit('pick', [start, end]);
              },
            },
            {
              text: '最近一个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 30
                );
                picker.$emit('pick', [start, end]);
              },
            },
            {
              text: '最近三个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 90
                );
                picker.$emit('pick', [start, end]);
              },
            },
          ],
        },
        dialogVisible: false,
        dialogVisible2: false,
        dialogVisible3: false,
        carrier: [],
        form: {
          orderNo: '',
          status: '',
          selectedCarrier: [],
          carrierNo: '',
          trackNo: '',
          length: 0,
          width: 0,
          height: 0,
          totalVolumeFrontEnd: 0,
          sum: 0,
          totalVolumeWithWeight: 0,
          totalWeight: 0,
        },
        ord4TrackNo: [],
        dialogVisibleList: false,
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
    props: ['msg'],
    created() {
      this.fetchData();
      this.initTrackno();
      this.initUserList();
      this.initChannel();
    },
    watch: {
      $route() {
        this.onQuickSearch();
      },
    },
    methods: {
      reRouter() {
        this.$router.push({
          path: '/order-list/mgt/type1/status1',
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
          url:
            'ord/list/' +
            this.msgData.category +
            '/' +
            this.msgData.status,
          method: 'post',
          data: this.tablePage,
        }).then((res) => {
          this.tableData = res.data.page.records;
          this.tablePage.current = res.data.page.current;
          this.tablePage.pages = res.data.page.pages;
          this.tablePage.size = res.data.page.size;
          this.tablePage.total = res.data.page.total;
          this.tableLoading = false;
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
        this.$router.push({
          path: '/new-order/index?ordno=' + row.orderNo,
        });
      },
      statusUpdate(index, row) {
        this.$confirm('您确定要提交该订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                'ord/update/' +
                this.msgData.category +
                '/' +
                row.orderNo +
                '/' +
                this.msgData.statusTo,
              method: 'get',
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
      },
      batchStatusUpdate() {
        if (this.ord4TrackNo.length <= 0) {
          this.$message.warning('请勾选每一行前的勾选框');
          return;
        }
        this.$confirm('您确定要提交这些订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                'ord/update/' +
                this.msgData.category +
                '/' +
                this.msgData.statusTo,
              method: 'post',
              data: this.ord4TrackNo,
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
      },
      abandon(index, row) {
        this.$confirm('您确定要废弃该订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                'ord/update/' +
                this.msgData.category +
                '/' +
                row.orderNo +
                '/' +
                this.msgData.statusAbandon,
              method: 'get',
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('废弃成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消废弃');
          });
      },
      searchOrd() {
        let url = '';
        if (
          this.daterange == null ||
          this.daterange[0] === 0 ||
          this.daterange[1] === 0
        ) {
          this.$message.warning('请选择您想要查询的日期范围');
          url =
            'ord/list/' +
            this.msgData.category +
            '/' +
            this.msgData.status +
            '/2000-01-01/2099-01-01?ordno=' +
            this.search.ordno +
            '&creator=' +
            this.search.creator +
            '&channelCode=' +
            this.search.channelCode;
        } else {
          url =
            'ord/list/' +
            this.msgData.category +
            '/' +
            this.msgData.status +
            this.daterange[0] +
            '/' +
            this.daterange[1] +
            '?ordno=' +
            this.search.ordno +
            '&creator=' +
            this.search.creator +
            '&channelCode=' +
            this.search.channelCode;
        }
        this.tableLoading = true;
        request({
          url: url,
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then((res) => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        });
      },
      route2NewOrd() {
        this.$router.push({
          path: '/new-order/index',
        });
      },
      initTrackno() {
        request({
          url: 'ord/carrier/distinct',
          method: 'get',
        }).then((res) => {
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
        }).then((res) => {
          this.users = res.data.page.records;
        });
      },
      initChannel() {
        request({
          url: '/channel/list',
          method: 'get',
        }).then((res) => {
          this.channels = res.data.data;
        });
      },
      applyTrackno(index, row) {
        this.form.orderNo = row.orderNo;
        this.form.carrierNo = row.carrierNo;
        this.form.trackNo = row.trackNo;
        const tmpNo = [];
        tmpNo.push('carrier_' + row.carrierNo);
        this.form.selectedCarrier = tmpNo;
        this.dialogVisible = true;
      },
      handleCarrierChange(value) {
        this.form.carrierNo = value[0];
      },
      getOrdNo2() {
        request({
          url: '/trackno/pk/' + this.form.carrierNo,
          method: 'get',
        }).then((res) => {
          this.form.trackNo = res.data.data;
          this.$message.success('获取订单号成功');
        });
      },
      fillInTrackNo() {
        request({
          url: 'ord/trackno',
          method: 'post',
          data: this.form,
        })
          .then(() => {
            this.$message.success('追踪单号申请成功');
            this.dialogVisible = false;
            this.fetchData();
          })
          .catch((err) => {
            console.log(err);
          });
      },
      getSummary(param) {
        const {columns, data} = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '总价';
          } else {
            sums[index] = '';
          }
        });
        sums[3] = data[0].totalPrice;
        return sums;
      },
      print(index, row) {
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/pdf/ord/' + row.orderNo;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
      handleSelectionChange(val) {
        this.multiSelection = val.length > 0;
        this.ord4TrackNo = [];
        val.forEach((ord) => {
          this.ord4TrackNo.push(ord.orderNo);
        });
      },
      applyTrackNo() {
        if (this.ord4TrackNo.length <= 0) {
          this.$message.warning('请勾选每一行前的勾选框');
          return;
        }
        this.dialogVisibleList = true;
      },
      fillInTrackNoList() {
        request({
          url: 'ord/trackno/list',
          method: 'post',
          data: {
            ords: this.ord4TrackNo,
            carrierNo: this.form.carrierNo,
          },
        })
          .then((res) => {
            console.log(res);
            this.$message.success('追踪单号申请成功');
            this.dialogVisibleList = false;
            this.fetchData();
          })
          .catch((err) => {
            console.log(err);
          });
      },
      exportExcel() {
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/ord/excel/1';
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
      route2ChannelPage(index, row) {
        this.$router.push({
          path: '/system/channel?filter=' + row.channel,
        });
      },
      findWhere() {
        this.$message.info('物流查询，功能待开发');
      },
      triggerVolumeAndWeight(index, row) {
        this.dialogVisible2 = true;
        this.form.orderNo = row.orderNo;
        this.form.length = row.length;
        this.form.width = row.width;
        this.form.height = row.height;
        this.form.sum = row.sum;
        this.form.totalWeight = row.totalWeight;
        this.form.totalVolumeFrontEnd = row.totalVolumeFrontEnd;
        this.form.totalVolumeWithWeight = row.totalVolumeWithWeight;
        this.form.status = this.msgData.statusTo;
      },
      triggerVolumeAndWeightWithOutStatus(index, row) {
        this.dialogVisible3 = true;
        this.form.orderNo = row.orderNo;
        this.form.length = row.length;
        this.form.width = row.width;
        this.form.height = row.height;
        this.form.sum = row.sum;
        this.form.totalWeight = row.totalWeight;
        this.form.totalVolumeFrontEnd = row.totalVolumeFrontEnd;
        this.form.totalVolumeWithWeight = row.totalVolumeWithWeight;
      },
      calculateIndex() {
        this.form.totalVolumeFrontEnd =
          this.form.length * this.form.height * this.form.width;
        this.form.totalVolumeFrontEnd = this.form.totalVolumeFrontEnd.toFixed(
          3
        );
        this.form.sum =
          this.form.length + this.form.height + this.form.width;
        this.form.sum = this.form.sum.toFixed(3);
        this.form.totalVolumeWithWeight =
          (this.form.length * this.form.height * this.form.width) / 6000;
        this.form.totalVolumeWithWeight = this.form.totalVolumeWithWeight.toFixed(
          3
        );
      },
      updateOrd() {
        request({
          url: '/ord/update',
          method: 'post',
          data: this.form,
        }).then((resp) => {
          const succ = resp['succ'];
          if (succ) {
            this.$message.success('当前订单已更新');
          } else {
            this.$message.error(resp['msg']);
          }
          this.dialogVisible2 = false;
          this.fetchData();
        });
      },
      updateOrdWithOutStatus() {
        delete this.form['statusTo'];
        delete this.form['status'];
        request({
          url: '/ord/update',
          method: 'post',
          data: this.form,
        }).then((resp) => {
          const succ = resp['data']['succ'];
          if (succ) {
            this.$message.success('当前订单已更新');
          } else {
            this.$message.error(resp['msg']);
          }
          this.dialogVisible3 = false;
          this.fetchData();
        });
      },
      route2PickUpPage(index, row) {
        this.$router.push({path: '/order-list/mgt/type1/pickup?orderNo=' + row.orderNo});
      },
    },
  };
</script>

<style>
  .el-table .success-row {
    background: rgba(103, 194, 58, 0.1);
  }

  .el-table .danger-row {
    background: rgb(253, 226, 226);
  }
</style>
