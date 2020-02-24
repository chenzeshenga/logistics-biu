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
                  align="right"
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
                  @click="searchReturning()"
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
          <el-table :data="tableData.row.contentList">
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
          prop="toDetailAddress"
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
          prop="fromDetailAddress"
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
            <el-image
                style="width: 100px; height: 100px"
                :src="scope.row.imgsLinks[0]"
                :preview-src-list="scope.row.imgsLinks"
            >
            </el-image>
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
              content="头程校验完成，发往日本"
              placement="top"
              v-if="msgData.buttonVisible9"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row, 4)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="日本仓库已收货，发往入库清点"
              placement="top"
              v-if="msgData.buttonVisibleA"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row, 5)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="入库清点完成，发往仓库上架"
              placement="top"
              v-if="msgData.buttonVisibleB"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row, 6)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="上架完成，开始计仓储费"
              placement="top"
              v-if="msgData.buttonVisibleC"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row, 7)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
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
              content="废弃"
              placement="top"
              v-if="msgData.buttonVisible7"
          >
            <el-button
                @click="hold(scope.$index, scope.row)"
                size="mini"
                type="danger"
                icon="el-icon-remove-outline"
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
    <el-dialog title="确认" :visible.sync="dialogVisible" width="30%">
      <span>需要注意的是内容是默认不居中的</span>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false"
                >确 定</el-button
                >
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
      },
      // page data
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      daterange: null,
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
      users: [],
      channels: [],
      search: {
        warehousingNo: '',
        creator: '',
        channelCode: '',
      },
      dialog: {
        carrier: '',
        trackNo: '',
        warehousingNo: '',
      },
      print: {},
      dialogForm3: {},
      noteTxt:
          '该页面显示过去7天的无主退货单，您可以在当前页面进行退货单认领',
      dialogVisible: false,
    };
  },
  props: ['msg'],
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
    searchReturning() {},
    fetchData() {
      this.tableLoading = true;
      request({
        url: 'return/list?type=' + this.msgData.type + '&status=新建',
        method: 'post',
        data: this.tablePage,
      }).then((res) => {
        const records = res.data.page.records;
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
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
    handleUpdate(index, row) {
      this.$router.push({
        path:
            '/new-returns/new-returns?returnNo='+row.returnNo,
      });
    },
    statusUpdate(index, row, statusUpdateTo) {
      this.$confirm('您确定要提交该订单？', '提示', confirm)
          .then(() => {
            request({
              url: 'warehousing/status',
              method: 'post',
              data: {
                to: statusUpdateTo,
                warehousingNo: row.warehousingNo,
              },
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
      console.log(index, row);
      this.dialogVisible = true;
    },
  },
};
</script>

<style scoped></style>
