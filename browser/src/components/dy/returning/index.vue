<template>
  <div>
    <el-alert
        :title="noteTxt"
        type="info"
        show-icon
        center
        style="margin: 1%"
    ></el-alert>
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
                  @change="reGenSearchData"
                  style="width: 400px"
              >
              </el-date-picker>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="退货单号" placement="top">
              <el-input
                  v-model="search.returnNo"
                  clearable="clearable"
                  placeholder="请输入退货单号"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="所属用户" placement="top">
              <el-select
                  filterable
                  clearable
                  v-model="search.creator"
                  placeholder="请选择所属用户"
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
          <el-col :span="1">
            <el-form-item label="">
              <el-button
                  icon="el-icon-search"
                  @click="fetchData()"
              ></el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
          <el-col :span="2">
            <el-button type="primary" @click="route2NewReturning()"
            >新建退货单
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
    >
      <el-table-column
          type="selection"
          width="55"
          show-overflow-tooltip
      ></el-table-column>
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <span style="font-weight: bold;margin-top: 1%;margin-bottom: 1%">退货单货物</span>
          <el-table :data="tableData.row.contentList" style="margin-bottom: 1%">
            <el-table-column
                prop="returnNo"
                label="退货单号"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="sku"
                label="sku"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="num"
                label="数量"
                width="150"
            ></el-table-column>
            <el-table-column
                prop="dealWith"
                label="处理方式"
                width="200"
            ></el-table-column>
          </el-table>
          <div>
            <span style="font-weight: bold;margin-top: 1%;margin-bottom: 1%">退货单货物处理方式</span>
          </div>
          <el-table :data="tableData.row.contentDealingList" style="margin-bottom: 1%">
            <el-table-column
                prop="returnNo"
                label="退货单号"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="sku"
                label="sku"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="num"
                label="数量"
                width="150"
            ></el-table-column>
            <el-table-column
                prop="dealWith"
                label="处理方式"
                width="200"
            ></el-table-column>
          </el-table>
          <div>
            <span style="font-weight: bold;margin-top: 1%;margin-bottom: 1%">退货单包裹信息</span>
          </div>
          <el-table :data="tableData.row.claimPackageList" style="margin-bottom: 1%">
            <el-table-column
                prop="returnNo"
                label="退货单号"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="length"
                label="长(cm)"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="width"
                label="宽(cm)"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="height"
                label="高(cm)"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="weight"
                label="重(kg)"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="carrier"
                label="承运人"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="trackNo"
                label="追踪单号"
                width="250"
            ></el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column
          width="200"
          prop="returnNo"
          label="退货单号"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="withoutOrderNoFlagLabel"
          label="是否对应订单"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="orderNo"
          label="订单号"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="status"
          label="状态"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="toName"
          label="收件人"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="toContact"
          label="收件人联系方式"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="toZipCode"
          label="收件人邮编"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="toDetailAddressLbl"
          label="收件详细地址"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="fromName"
          label="发件人姓名"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="fromContact"
          label="发件人联系方式"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="fromZipCode"
          label="发件人邮编"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="fromDetailAddressLbl"
          label="发件人详细地址"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="carrier"
          label="承运人"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="trackNo"
          label="承认人追踪单号"
      ></el-table-column>
      <el-table-column width="200" label="图片">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <div style="height: 100px;width: 200px;overflow: auto">
              <el-image v-for="link in scope.row.imgsLinks" :key="link" :src="link"
                        :preview-src-list="scope.row.imgsLinks"></el-image>
            </div>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">图片</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          width="200"
          prop="createOn"
          label="创建时间"
      ></el-table-column>
      <el-table-column
          width="200"
          prop="updateOn"
          label="修改时间"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="creator"
          label="所属用户"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="updator"
          label="修改人"
      ></el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template slot-scope="scope">
          <el-tooltip
              content="认领"
              placement="top"
              v-if="msgData.buttonVisible1"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row, 2)"
                size="mini"
                type="success"
                circle
                plain
            >
              <svg-icon icon-class="claim"></svg-icon>
            </el-button>
          </el-tooltip>
          <el-tooltip
              content="编辑"
              placement="top"
              v-if="msgData.buttonVisible5"
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
              content="删除"
              placement="top"
              v-if="msgData.buttonVisible6"
          >
            <el-button
                @click="handleDelete(scope.$index, scope.row)"
                size="mini"
                type="danger"
                icon="el-icon-remove"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="确认收货"
              placement="top"
              v-if="msgData.buttonVisible8"
          >
            <el-button
                @click="accept(scope.$index, scope.row)"
                circle
                plain
            >
              <svg-icon icon-class="receiving"></svg-icon>
            </el-button>
          </el-tooltip>
          <el-tooltip
              content="退货品处理"
              placement="top"
              v-if="msgData.dealWithReturnContent"
          >
            <el-button
                @click="dealWithReturnContent(scope.$index, scope.row)"
                circle
                plain
            >
              <svg-icon icon-class="dealWithReturnContent"></svg-icon>
            </el-button>
          </el-tooltip>
          <el-tooltip
              content="删除"
              placement="top"
              v-if="msgData.delete"
          >
            <el-button
                @click="deleteReturnOrd(scope.$index, scope.row)"
                circle
                plain
            >
              <svg-icon icon-class="delete"></svg-icon>
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
    >
    </el-pagination>
    <el-dialog title="确认收货" :visible.sync="dialogVisible" width="50%">
      <el-alert title="可点击右侧加号按钮添加一个收到的退货包裹。当点击确定按钮时，若下方输入框均不为0，则也会当成一个包裹" type="info"></el-alert>
      <el-row>
        <el-col :span="2" :offset="20">
          <el-tooltip content="添加包裹" placement="top">
            <el-button type="primary" size="small" plain @click="addPkg">
              <svg-icon icon-class="plus-icon"/>
            </el-button>
          </el-tooltip>
        </el-col>
      </el-row>
      <div :style="pkgInfoStyle">
        <el-table :data="pkgInfoTblData">
          <el-table-column prop="length" label="长"/>
          <el-table-column prop="width" label="宽"/>
          <el-table-column prop="height" label="高"/>
          <el-table-column prop="weight" label="重"/>
          <el-table-column prop="carrier" label="承运人"/>
          <el-table-column prop="trackNo" label="追踪单号"/>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-tooltip content="删除" placement="top">
                <el-button @click="dropPkg(scope.$index, scope.row, 2)" size="mini" type="info" plain>
                  <svg-icon icon-class="delete"/>
                </el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-form :ref="formInDialog1" :model="formInDialog1" label-width="80px">
        <el-form-item label="退货单号">{{ formInDialog1.returnNo }}</el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="长(cm)">
              <el-input-number v-model="formInDialog1.length"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="宽(cm)">
              <el-input-number v-model="formInDialog1.width"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="高(cm)">
              <el-input-number v-model="formInDialog1.height"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重(kg)">
              <el-input-number v-model="formInDialog1.weight"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="承运人">
              <el-input v-model="formInDialog1.carrier" placeholder="承运人"/>
            </el-form-item>
          </el-col>
          <el-col :span="8" :offset="4">
            <el-form-item label="追踪单号">
              <el-input v-model="formInDialog1.trackNo" placeholder="追踪单号"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="returnPkgInfoUpdate">确 定</el-button>
            </span>
    </el-dialog>
    <el-dialog title="退货品处理" :visible.sync="dealWithReturnContentDlg" width="40%">
      <span>当前退货单待处理内容如下:</span>
      <el-table :data="returnContentList">
        <el-table-column prop="sku" label="sku"/>
        <el-table-column prop="name" label="名称"/>
        <el-table-column prop="num" label="数量"/>
      </el-table>
      <div style="margin-top: 2%">
        <span>当前退货单处理结果如下:</span>
      </div>
      <el-table :data="returnContentDealingList">
        <el-table-column prop="sku" label="sku"/>
        <el-table-column prop="name" label="名称"/>
        <el-table-column prop="num" label="数量"/>
        <el-table-column prop="dealWith" label="处理方式"/>
        <el-table-column prop="shelfNo" label="货架号"/>
        <el-table-column prop="comment" label="备注"/>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip content="删除" placement="top">
              <el-button @click="deleteContentDealing(scope.$index, scope.row)" size="mini" type="info" plain>
                <svg-icon icon-class="delete"/>
              </el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 2%">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-tooltip content="sku" placement="top">
              <el-select v-model="contentDealing.sku" placeholder="请选择商品" @change="fillName">
                <el-option
                    v-for="item in skus"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip content="商品名称" placement="top">
              <el-input v-model="contentDealing.name" placeholder="商品名称" disabled/>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip content="商品数量" placement="top">
              <el-input-number v-model="contentDealing.num" placeholder="商品数量"/>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row style="margin-top: 2%">
          <el-col :span="12">
            <el-tooltip content="处理方式" placement="top">
              <el-select v-model="contentDealing.dealWith" placeholder="请选择处理方式">
                <el-option
                    v-for="item in dealWiths"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="12">
            <el-tooltip content="货架号" placement="top">
              <el-select v-model="contentDealing.shelfNo" placeholder="请选择货架">
                <el-option
                    v-for="item in shelves"
                    :key="item.shelfNo"
                    :label="item.shelfNo"
                    :value="item.shelfNo"
                >
              <span
              >货架号 {{ item.shelfNo }} 货架区域 {{ item.area }} 货架行数
                {{ item.layer }} 货架层数 {{ item.rowNo }}</span
              >
                </el-option>
              </el-select>
            </el-tooltip>
          </el-col>
        </el-row>
        <el-row style="margin-top: 2%" :gutter="20">
          <el-col :span="20">
            <el-tooltip content="备注" placement="top">
              <el-input v-model="contentDealing.comment" placeholder="备注"/>
            </el-tooltip>
          </el-col>
          <el-col :span="2" :offset="1">
            <el-button type="primary" @click="add2ContentDealing">确定</el-button>
          </el-col>
        </el-row>
      </div>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dealWithReturnContentDlg = false">取 消</el-button>
          <el-button type="primary" @click="dealWithReturnContentDlgSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '../../../utils/service';

export default {
  name: 'returningTable',
  data() {
    return {
      msgData: {
        status: this.msg.status,
        type: this.msg.type,
        buttonVisible8: this.msg.buttonVisible8 === true,
        buttonVisible1: this.msg.buttonVisible1 === true,
        buttonVisible2: this.msg.buttonVisible2 === true,
        buttonVisible3: this.msg.buttonVisible3 === true,
        buttonVisible4: this.msg.buttonVisible4 === true,
        buttonVisible5: this.msg.buttonVisible5 === true,
        buttonVisible6: this.msg.buttonVisible6 === true,
        buttonVisible7: this.msg.buttonVisible7 === true,
        buttonVisible9: this.msg.buttonVisible9 === true,
        buttonVisibleA: this.msg.buttonVisibleA === true,
        buttonVisibleB: this.msg.buttonVisibleB === true,
        buttonVisibleC: this.msg.buttonVisibleC === true,
        dealWithReturnContent: this.msg.dealWithReturnContent === true,
        delete: this.msg.delete === true,
      },
      // page data
      tablePage: {
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      daterange: [],
      dialogVisible1: false,
      dialogVisible2: false,
      dialogVisible3: false,
      pickerOptions2: {
        disabledDate(time) {
          const dateBeforeNow = new Date();
          dateBeforeNow.setDate(new Date().getDate() - 7);
          return time.getTime() < dateBeforeNow.getTime();
        },
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            },
          },
        ],
      },
      users: [],
      channels: [],
      search: {
        returnNo: '',
        creator: '',
        fromDate: '',
        endDate: '',
        status: '',
        type: '',
      },
      dialog: {
        carrier: '',
        trackNo: '',
        warehousingNo: '',
      },
      print: {},
      dialogForm3: {},
      noteTxt:
          '该页面显示过去14天的无主退货单，您可以在当前页面进行退货单认领',
      dialogVisible: false,
      formInDialog1: {
        returnNo: '',
        height: '',
        width: '',
        length: '',
        weight: '',
        carrier: '',
        trackNo: '',
      },
      dealWithReturnContentDlg: false,
      returnContentList: [],
      returnContentDealingList: [],
      pkgInfoStyle: {
        'display': 'none',
      },
      pkgInfoTblData: [],
      shelves: [],
      contentDealing: {
        sku: '',
        name: '',
        num: '',
        dealWith: '',
        shelfNo: '',
        comment: '',
        returnNo: '',
      },
      skus: [],
      dealWiths: [
        {
          'value': '遗弃',
          'label': '遗弃',
        },
        {
          'value': '重新上架',
          'label': '重新上架',
        },
        {
          'value': '寄回',
          'label': '寄回',
        },
      ],
    };
  },
  props: ['msg'],
  created() {
    this.fetchData();
    this.initUserList();
    this.fetchShelves();
  },
  methods: {
    fetchShelves() {
      request({
        url: '/shelf/list/enable',
        method: 'get',
      }).then((res) => {
        this.shelves = res.data.data;
      });
    },
    fetchData() {
      this.tableLoading = true;
      this.search.status = this.msgData.status;
      this.search.type = this.msgData.type;
      const postData = {
        'pagination': this.tablePage,
        'entity': this.search,
      };
      request({
        url: 'return/listV2',
        method: 'post',
        data: postData,
      }).then((res) => {
        console.log(res);
        const records = res.data.data.data;
        for (let i = 0; i < records.length; i++) {
          const subRecords = records[i];
          if (subRecords['withoutOrderNoFlag']) {
            subRecords['withoutOrderNoFlagLabel'] = '对应';
          } else {
            subRecords['withoutOrderNoFlagLabel'] = '不对应';
          }
          const imgs = subRecords['imgs'];
          if (imgs == null) {
            subRecords['imgsLinks'] = [];
            continue;
          }
          const imgsArr = imgs.split(';');
          const imgsLinks = [];
          for (let j = 0; j < imgsArr.length; j++) {
            const sublink =
                process.env.BASE_API + '/img/' + imgsArr[j];
            imgsLinks.push(sublink);
          }
          subRecords['imgsLinks'] = imgsLinks;
        }
        this.tableData = res.data.data.data;
        this.tablePage.current = res.data.data.current;
        this.tablePage.size = res.data.data.size;
        this.tablePage.total = res.data.data.total;
        this.tableLoading = false;
      });
    },
    handleUpdate(index, row) {
      this.$router.push({
        path:
            '/new-returns/new-returns?returnNo=' + row.returnNo,
      });
    },
    statusUpdate(index, row, statusUpdateTo) {
      this.$confirm('您确定要认领该订单?', '提示', confirm)
          .then(() => {
            request({
              url: 'return/claim?returnNo=' + row.returnNo,
              method: 'get',
            }).then(() => {
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    route2NewReturning() {
      this.$router.push({
        path: '/new-returns/new-returns',
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
    handleError(err) {
      this.$message.error(JSON.parse(err.message)['message']);
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
    accept(index, row) {
      this.dialogVisible = true;
      this.formInDialog1.returnNo = row.returnNo;
      this.formInDialog1.carrier = row.carrier;
      this.formInDialog1.trackNo = row.trackNo;
    },
    returnPkgInfoUpdate() {
      if (this.formInDialog1.length !== 0 && this.formInDialog1.width !== 0 && this.formInDialog1.height !== 0 && this.formInDialog1.weight !== 0) {
        this.pkgInfoTblData.push(this.formInDialog1);
      }
      request({
        url: '/return/claimPkg',
        method: 'post',
        data: this.pkgInfoTblData,
      }).then(() => {
        this.$message.success('退货单收货成功');
        this.dialogVisible = false;
        this.fetchData();
      });
    },
    handleDelete(index, row) {
      console.log(index);
      console.log(row);
    },
    dealWithReturnContent(index, row) {
      this.dealWithReturnContentDlg = true;
      this.skus = [];
      this.returnContentList = row.contentList;
      for (const content of this.returnContentList) {
        const tmp = {
          'value': content.sku,
          'label': content.name,
        };
        this.skus.push(tmp);
      }
      this.contentDealing.returnNo = row.returnNo;
    },
    dealWithReturnContentDlgSubmit() {
      request({
        url: '/return/claimContentDealing',
        method: 'post',
        data: this.returnContentDealingList,
      }).then(() => {
        this.$message.success('退货单处理成功');
        this.dealWithReturnContentDlg = false;
        this.fetchData();
      });
    },
    reGenSearchData() {
      if (this.daterange) {
        this.search.fromDate = this.daterange[0];
        this.search.endDate = this.daterange[1];
      }
    },
    addPkg() {
      this.pkgInfoTblData.push(this.formInDialog1);
      const carrier = this.formInDialog1.carrier;
      const trackNo = this.formInDialog1.trackNo;
      const returnNo = this.formInDialog1.returnNo;
      this.formInDialog1 = {
        returnNo: returnNo,
        height: '',
        width: '',
        length: '',
        weight: '',
        carrier: carrier,
        trackNo: trackNo,
      };
      this.pkgInfoStyle = {'display': 'block'};
    },
    dropPkg(index, row) {
      this.pkgInfoTblData.splice(index, 1);
      if (this.pkgInfoTblData.length <= 0) {
        this.pkgInfoStyle = {'display': 'none'};
      }
    },
    deleteContentDealing(index, row) {
      this.returnContentDealingList.splice(index, 1);
    },
    fillName(val) {
      for (const content of this.skus) {
        if (content.value === val) {
          this.contentDealing.name = content.label;
          break;
        }
      }
    },
    add2ContentDealing() {
      this.returnContentDealingList.push(this.contentDealing);
      this.contentDealing = {
        sku: '',
        name: '',
        num: '',
        dealWith: '',
        shelfNo: '',
        comment: '',
        returnNo: this.contentDealing.returnNo,
      };
    },
    deleteReturnOrd(index, row) {
      this.$confirm('确定删除该退货单吗？', '提示', confirm).then(() => {
        request({
          url: '/return/drop?returnNo=' + row.returnNo,
          method: 'get',
        }).then(() => {
          this.$message.success('退货单删除成功');
          this.fetchData();
        });
      })
    },
  },
};
</script>

<style scoped></style>
