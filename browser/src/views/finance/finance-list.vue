<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin: 1%">
        <el-col :span="6">
          <el-tooltip content="请选择账单月度" placement="top">
            <el-date-picker
                v-model="search.month"
                type="monthrange"
                align="right"
                unlink-panels
                range-separator="-"
                start-placeholder="开始"
                end-placeholder="结束"
                :picker-options="pickerOptions">
            </el-date-picker>
          </el-tooltip>
        </el-col>
        <el-col :span="3">
          <el-tooltip content="请选择账单属主" placement="top">
            <el-select
                filterable
                clearable
                v-model="search.userId"
                placeholder="请选择账单属主"
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
        <el-col :span="2" :offset="10">
          <el-button type="primary" @click="triggerAddDlg">新增账单</el-button>
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
        <el-table-column label="详情" width="150">
          <template slot-scope="scope">
            <el-button type="info" @click="showDetails(scope.row.uuid)">详情</el-button>
          </template>
        </el-table-column>
        <el-table-column width="250" prop="userId" label="用户id"/>
        <el-table-column width="250" prop="nick" label="用户名称"/>
        <el-table-column width="100" prop="relatedMonth" label="费用期间"/>
        <el-table-column width="100" prop="currency" label="货币"/>
        <el-table-column width="200" prop="fee1" label="退货手续费"/>
        <el-table-column width="200" prop="fee2" label="代付到付手续费"/>
        <el-table-column width="200" prop="fee3" label="一件代发"/>
        <el-table-column width="200" prop="fee4" label="FBA转仓"/>
        <el-table-column width="200" prop="fee5" label="仓储费"/>
        <el-table-column width="200" prop="fee6" label="检测等其他费用"/>
        <el-table-column width="200" prop="fee7" label="头程费用"/>
        <el-table-column width="200" prop="fee8" label="转运回国费用"/>
        <el-table-column width="200" prop="fee9" label="月租&客服费用"/>
        <el-table-column width="200" prop="comments" label="备注"/>
        <el-table-column width="200" prop="fileUuid" label="账单文件"/>
        <el-table-column label="操作" width="200" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="删除" placement="top">
              <el-button
                  @click="handleDelete(scope.$index, scope.row)"
                  size="mini"
                  type="danger"
                  icon="el-icon-remove"
                  circle
                  plain
              />
            </el-tooltip>
          </template>
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
      <el-dialog :visible.sync="dialogVisible" width="50%" title="新增账单">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="用户id">
            <el-tooltip content="请选择账单属主" placement="top">
              <el-select
                  filterable
                  clearable
                  v-model="form.userId"
                  placeholder="请选择账单属主"
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
          <el-form-item label="账单期间">
            <el-date-picker
                v-model="form.relatedMonthLbl"
                type="month"
                placeholder="选择月">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="货币">
            <el-input v-model="form.currency"/>
          </el-form-item>
          <el-form-item label="退货手续费">
            <el-input-number v-model="form.fee1"/>
          </el-form-item>
          <el-form-item label="代付到付手续费">
            <el-input-number v-model="form.fee2"/>
          </el-form-item>
          <el-form-item label="一件代发">
            <el-input-number v-model="form.fee3"/>
          </el-form-item>
          <el-form-item label="FBA转仓">
            <el-input-number v-model="form.fee4"/>
          </el-form-item>
          <el-form-item label="仓储费">
            <el-input-number v-model="form.fee5"/>
          </el-form-item>
          <el-form-item label="检测等其他费用">
            <el-input-number v-model="form.fee6"/>
          </el-form-item>
          <el-form-item label="头程费用">
            <el-input-number v-model="form.fee7"/>
          </el-form-item>
          <el-form-item label="转运回国费用">
            <el-input-number v-model="form.fee8"/>
          </el-form-item>
          <el-form-item label="月租&客服费用">
            <el-input-number v-model="form.fee9"/>
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
      <el-dialog :visible.sync="dialogVisible1" width="40%" title="账单详情">
        <el-form ref="form" :model="curr" label-width="120px">
          <el-form-item label="用户id">
            <el-tooltip content="用户id" placement="top">
              <span>{{ curr.userId }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="用户名称">
            <el-tooltip content="用户名称" placement="top">
              <span>{{ curr.nick }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="账单期间">
            <el-tooltip content="账单期间" placement="top">
              <span>{{ curr.relatedMonth }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="货币">
            <el-tooltip content="货币" placement="top">
              <span>{{ curr.currency }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="退货手续费">
            <el-tooltip content="退货手续费" placement="top">
              <span>{{ curr.fee1 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="代付到付手续费">
            <el-tooltip content="代付到付手续费" placement="top">
              <span>{{ curr.fee2 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="一件代发">
            <el-tooltip content="一件代发" placement="top">
              <span>{{ curr.fee3 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="FBA转仓">
            <el-tooltip content="FBA转仓" placement="top">
              <span>{{ curr.fee4 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="仓储费">
            <el-tooltip content="仓储费" placement="top">
              <span>{{ curr.fee5 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="检测等其他费用">
            <el-tooltip content="检测等其他费用" placement="top">
              <span>{{ curr.fee6 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="头程费用">
            <el-tooltip content="头程费用" placement="top">
              <span>{{ curr.fee7 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="转运回国费用">
            <el-tooltip content="转运回国费用" placement="top">
              <span>{{ curr.fee8 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="月租&客服费用">
            <el-tooltip content="月租&客服费用" placement="top">
              <span>{{ curr.fee9 }}</span>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.comments" type="textarea" disabled/>
          </el-form-item>
          <el-form-item>
            <el-button @click="this.dialogVisible1=false">关闭</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';
import moment from 'moment';

export default {
  name: 'finance-list',
  data() {
    return {
      search: {
        month: null,
        userId: '',
        current: 1,
        pages: null,
        size: 10,
        total: null,
        startMonth: null,
        endMonth: null,
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
      tableData: [],
      dialogVisible: false,
      dialogVisible1: false,
      form: {
        userId: '',
        relatedMonth: null,
        relatedMonthLbl: null,
        currency: 'JPY',
        fee1: 0,
        fee2: 0,
        fee3: 0,
        fee4: 0,
        fee5: 0,
        fee6: 0,
        fee7: 0,
        fee8: 0,
        fee9: 0,
        comments: '',
      },
      curr: {},
    };
  },
  created() {
    this.fetchData();
    this.initUserList();
  },
  methods: {
    fetchData() {
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
      request({
        url: '/finance/fee/list',
        method: 'post',
        data: this.search,
      }).then((res) => {
        this.tableData = res.data.data.data;
        this.search.total = res.data.data.total;
        this.tableLoading = false;
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
    onsubmit() {
      this.form.relatedMonth = Number(moment(this.form.relatedMonthLbl).format('yyyyMM'));
      request({
        url: '/finance/fee/add',
        method: 'post',
        data: this.form,
      }).then(() => {
        this.$message.success('账单新增成功');
        this.dialogVisible = false;
        this.fetchData();
      });
    },
    showDetails(uuid) {
      request({
        url: '/finance/fee/detail/' + uuid,
        method: 'get',
      }).then((res) => {
        this.curr = res.data.data;
        this.dialogVisible1 = true;
      });
    },
  },
};
</script>
