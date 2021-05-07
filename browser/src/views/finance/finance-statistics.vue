<template>
  <div class="login-container">
    <div class="app-container">
      <el-row style="margin: 1%">
        <el-alert type="warning" title="账户余额以及消费情况每2小时统计一次, 页面信息可能存在误差, 触发统计请点下统计按钮" closable/>
      </el-row>
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="3">
          <el-tooltip content="请选择账户属主" placement="top">
            <el-select
                filterable
                clearable
                v-model="search.userId"
                placeholder="请选择账户属主"
            >
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
          <el-button
              icon="el-icon-search"
              @click="fetchData()"
          ></el-button>
        </el-col>
        <el-col :span="2" :offset="2">
          <el-tooltip content="导出账户列表文件">
            <el-button type="primary" @click="exportData">导出</el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="2" :offset="1">
          <el-button type="primary" @click="triggerImportDlg">导入充值记录</el-button>
        </el-col>
        <el-col :span="2" :offset="3">
          <el-button type="primary" @click="triggerAddDlg">新增充值记录</el-button>
        </el-col>
        <el-col :span="2" :offset="3">
          <el-button type="primary" @click="triggerAccountStatistics">统计</el-button>
        </el-col>
      </el-row>
      <el-table
          style="width: 100%;margin: 10px"
          :data="tableData"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column label="充值记录" width="150">
          <template slot-scope="scope">
            <el-button type="info" @click="showDetails(scope.row.userId)">充值记录</el-button>
          </template>
        </el-table-column>
        <el-table-column width="250" prop="userId" label="用户id"/>
        <el-table-column width="250" prop="nick" label="用户名称"/>
        <el-table-column width="200" prop="totalInJpy" label="账户日元余额"/>
        <el-table-column width="200" prop="totalInCny" label="账户人民币余额"/>
        <el-table-column width="200" prop="feeInJpy" label="日元合计费用"/>
        <el-table-column width="200" prop="feeInCny" label="人民币合计费用"/>
        <el-table-column width="200" prop="timestamp" label="统计时间"/>
        <el-table-column label="操作" width="200" fixed="right">
          预留
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="search.current"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size="search.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="search.total"
      >
      </el-pagination>
      <el-dialog :visible.sync="dialogVisible" width="50%" title="新增充值记录" show-close :close-on-click-modal="false">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="用户id">
            <el-tooltip content="请选择属主" placement="top">
              <el-select
                  filterable
                  clearable
                  v-model="form.userId"
                  placeholder="请选择属主"
              >
                <el-option
                    v-for="creator in options.owners"
                    :key="creator.uname"
                    :label="creator.nick"
                    :value="creator.uname"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="充值时间">
            <el-date-picker v-model="form.rechargeDate"/>
          </el-form-item>
          <el-form-item label="充值日元数量">
            <el-input-number v-model="form.amountInJpy"/>
          </el-form-item>
          <el-form-item label="充值人民币数量">
            <el-input-number v-model="form.amountInCny"/>
          </el-form-item>
          <el-form-item label="汇率">
            <el-input-number v-model="form.exchangeRate"/>
          </el-form-item>
          <el-form-item label="支付方式">
            <el-input v-model="form.payMethod"/>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.comments" type="textarea"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onsubmit">确定</el-button>
            <el-button @click="this.dialogVisible=false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-dialog :visible.sync="dialogVisible1" width="70%" title="充值记录">
        <el-row>
          <el-col offset="20">
            <el-button @click="exportRechargeData">导出充值记录</el-button>
          </el-col>
        </el-row>
        <el-table
            style="width: 100%;margin: 10px"
            :data="tableData1"
            v-loading.body="tableLoading1"
            element-loading-text="加载中"
            stripe
            highlight-current-row
        >
          <el-table-column width="250" prop="userId" label="用户id"/>
          <el-table-column width="250" prop="nick" label="用户名称"/>
          <el-table-column width="200" prop="rechargeDate" label="充值时间"/>
          <el-table-column width="200" prop="amountInJpy" label="日元充值金额"/>
          <el-table-column width="200" prop="amountInCny" label="人民币充值金额"/>
          <el-table-column width="200" prop="exchangeRate" label="汇率"/>
          <el-table-column width="200" prop="payMethod" label="支付方式"/>
          <el-table-column width="200" prop="timestamp" label="备注"/>
          <el-table-column label="操作" width="200" fixed="right">
            预留
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange2"
            @current-change="handleCurrentChange2"
            :current-page="search1.current"
            :page-sizes="[10, 20, 30, 40, 50]"
            :page-size="search1.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="search1.total"
        >
        </el-pagination>
      </el-dialog>
      <el-dialog :visible.sync="dialogVisible2" width="20%" title="充值记录批量导入">
        <el-alert type="info" title="请下载模版文件, 按照模版文件形式填写内容" :closable="false"/>
        <el-upload
            ref="upload"
            :action="actionLink1"
            with-credentials
            :limit="1"
            :auto-upload="false"
            :on-success="handleSuccess1"
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
              @click="upload"
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
        </el-upload>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';
import moment from 'moment';

export default {
  name: 'finance-statistics',
  data() {
    return {
      search: {
        userId: '',
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      search1: {
        userId: '',
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      options: {
        owners: [],
      },
      pickerOptions: {
        shortcuts: [{
          text: '本月',
          onClick(picker) {
            picker.$emit('pick', [new Date(), new Date()]);
          },
        }, {
          text: '今年至今',
          onClick(picker) {
            const end = new Date();
            const start = new Date(new Date().getFullYear(), 0);
            picker.$emit('pick', [start, end]);
          },
        }, {
          text: '最近六个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setMonth(start.getMonth() - 6);
            picker.$emit('pick', [start, end]);
          },
        }],
      },
      tableLoading: false,
      tableLoading1: false,
      tableData: [],
      tableData1: [],
      dialogVisible: false,
      dialogVisible1: false,
      dialogVisible2: false,
      form: {
        userId: '',
        rechargeDate: null,
        amountInJpy: 0,
        amountInCny: 0,
        exchangeRate: 0,
        payMethod: '',
        comments: '',
      },
      curr: {},
      actionLink1: process.env.BASE_API + '/finance/recharge/batch/import',
    };
  },
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
    fetchData() {
      this.dialogVisible = false;
      this.dialogVisible1 = false;
      this.dialogVisible2 = false;
      this.customSearchVal();
      request({
        url: '/finance/account/list',
        method: 'post',
        data: this.search,
      }).then((res) => {
        this.tableData = res.data.data.data;
        this.search.total = res.data.data.total;
        this.tableLoading = false;
      });
    },
    fetchRechargeData() {
      request({
        url: '/finance/recharge/list',
        method: 'post',
        data: this.search1,
      }).then((res) => {
        this.tableData1 = res.data.data.data;
        this.search1.size = res.data.data.size;
        this.search1.total = res.data.data.total;
        this.search1.current = res.data.data.current;
        this.tableLoading1 = false;
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
      this.search.size = val;
      this.fetchData();
    },
    handleSizeChange2(val) {
      this.search1.size = val;
      this.fetchRechargeData();
    },
    handleCurrentChange2(val) {
      this.search1.current = val;
      this.fetchRechargeData();
    },
    handleCurrentChange(val) {
      this.search.current = val;
      this.fetchData();
    },
    handleDelete(index, row) {
      this.$confirm('您确定删除该记录吗？', '提示', confirm)
          .then(() => {
            request({
              url: '/finance/fee/delete/' + row.uuid,
              method: 'get',
            }).then(() => {
              this.$message.success('账单删除成功');
              this.fetchData();
            });
          })
          .catch(() => {
            this.$message.info('已取消删除');
          });
    },
    triggerAddDlg() {
      this.dialogVisible = true;
    },
    triggerImportDlg() {
      this.dialogVisible2 = true;
    },
    onsubmit() {
      const postData = this.form;
      postData.rechargeDate = moment(this.form.rechargeDate).format('yyyy-MM-DD HH:mm:ss');
      request({
        url: '/finance/recharge/add',
        method: 'post',
        data: postData,
      }).then(() => {
        this.$message.success('充值新增成功');
        this.dialogVisible = false;
        this.fetchData();
      });
    },
    showDetails(userId) {
      this.dialogVisible1 = true;
      this.search1.userId = userId;
      this.fetchRechargeData();
    },
    handleSuccess(response, file, fileList) {
      this.form.fileUuid = response.data.uuid;
    },
    handleSuccess1(response, file, fileList) {
      console.log(file);
      console.log(fileList);
      this.fetchData();
    },
    downloadFile(uuid) {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/v2/common/file/' + uuid;
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    exportData() {
      request({
        url: '/finance/account/generateAccountFile',
        method: 'get',
      }).then((res) => {
        this.downloadFile(res.data.data.uuid);
      });
    },
    exportRechargeData() {
      request({
        url: '/finance/recharge/generateRechargeFile?userId=' + this.search1.userId,
        method: 'get',
      }).then((res) => {
        this.downloadFile(res.data.data.uuid);
      });
    },
    downloadTemplate() {
      request({
        url: '/finance/account/generateTemplate',
        method: 'get',
      }).then((res) => {
        this.downloadFile(res.data.data.uuid);
      });
    },
    triggerAccountStatistics() {
      request({
        url: '/finance/account/triggerAccount',
        method: 'get',
      }).then(() => {
        this.$message.warning('账户数据统计中, 页面数据可能不准确, 请稍等...');
      });
    },
    customSearchVal() {
      const month = this.search.month;
      if (month != null && month !== '') {
        const startMonth = moment(this.search.month[0]).format('YYYYMM');
        const endMonth = moment(this.search.month[1]).format('YYYYMM');
        this.search.startMonth = Number(startMonth);
        this.search.endMonth = Number(endMonth);
      } else {
        this.search.startMonth = null;
        this.search.endMonth = null;
      }
    },
    upload() {
      this.$refs.upload.submit();
    },
  },
};
</script>
