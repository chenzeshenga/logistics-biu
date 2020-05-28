<template>
  <div class="login-container">
    <div class="app-container">
      <el-form ref="form" label-width="120px" :model="form">
        <el-form-item label="入库单所属用户" v-if="adminRole">
          <el-col :span="12">
            <el-form-item label="所属用户">
              <el-tooltip content="所属用户" placement="top">
                <el-select
                  filterable
                  clearable
                  v-model="form.creator"
                  placeholder="请选择所属用户"
                  @change="filterProductAndUserAddress"
                >
                  <el-option
                    v-for="creator in users"
                    :key="creator.uname"
                    :label="creator.nick"
                    :value="creator.uname"
                    :disabled="creator.disabled"
                  />
                </el-select>
              </el-tooltip>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="发货地址">
          <el-col :span="12">
            <el-form-item label="发货地址">
              <el-input v-model="form.fromAddress" />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库地址">
          <el-col :span="12">
            <el-form-item label="默认地址">
              <el-input v-model="form.target" disabled />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="承运信息">
          <el-col :span="12">
            <el-form-item label="单号">
              <el-input
                v-model="form.warehousingNo"
                clearable
                v-bind:disabled="onUpdate"
                placeholder="请点击按钮获取单号"
              >
                <el-button slot="append" v-bind:disabled="onUpdate" @click="getOrdNo">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="头程方式">
              <el-select filterable v-model="form.method" placeholder="请选择头程方式" clearable>
                <el-option label="东岳头程" value="东岳头程" />
                <el-option label="其他头程" value="其他头程" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12" v-if="form.method === '其他头程'">
            <el-form-item label="头程承运人">
              <el-input v-model="form.carrier" placeholder="请输入承运人" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.method === '其他头程'">
            <el-form-item label="承运人追踪号">
              <el-input v-model="form.trackNo" placeholder="请输入承运人追踪号" clearable />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
            <el-form-item label="头程渠道">
              <el-select filterable v-model="form.channel" placeholder="请选择头程渠道" clearable>
                <el-option
                  v-for="item in channels"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="运输方式">
              <el-select v-model="form.deliverMethod" clearable filterable placeholder="请选择运输方式">
                <el-option label="海运整柜20GP/HQ" value="海运整柜20GP/HQ" />
                <el-option label="海运整柜40GP/HQ" value="海运整柜40GP/HQ" />
                <el-option label="海运散货" value="海运散货" />
                <el-option label="空运" value="空运" />
                <el-option label="卡车派送" value="卡车派送" />
                <el-option label="其他" value="其他" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="关税相关">
          <el-col :span="12">
            <el-form-item label="报关类型">
              <el-radio v-model="form.clearanceType" label="单独退税报关">单独退税报关</el-radio>
              <el-radio v-model="form.clearanceType" label="无退税报关">无退税报关</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关税类型">
              <el-radio v-model="form.taxType" label="预缴关税">预缴关税</el-radio>
              <el-radio v-model="form.taxType" label="实报实销">实报实销</el-radio>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
            <el-form-item label="保险服务">
              <el-switch v-model="form.insurance" active-color="#13ce66" inactive-color="#ff4949" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.insurance">
            <el-form-item label="保险总值(JPY)">
              <el-input-number v-model="form.insuranceNum" />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
            <el-form-item label="预计到港时间">
              <el-date-picker
                v-model="form.estimatedDate"
                type="date"
                placeholder="选择日期"
                :picker-options="pickerOptions1"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="入库商品">
          <el-col :span="4">
            <el-form-item label="箱号">
              <el-input-number v-model="currContent.boxSeq"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="东岳sku">
              <el-select
                v-model="currContent.dySku"
                placeholder="请从已审核产品中选择"
                @change="handleValueChange"
                clearable
                filterable
              >
                <el-option
                  v-for="item in myProducts"
                  :key="item.dySku"
                  :label="item.dySku"
                  :value="item.dySku"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="名称">
              <el-input v-model="currContent.name" placeholder="商品名称" />
            </el-form-item>
          </el-col>
          <el-col :span="6" style="margin-top: 22px">
            <el-form-item label="数量">
              <el-input-number v-model="currContent.totalNum" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="包装方式" style="margin-top: 22px">
              <el-select v-model="currContent.wrapType">
                <el-option v-for="item in wrapTypeArr" :key="item" :label="item" :value="item" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4" style="margin-top: 22px">
            <el-button type="primary" @click="add2Cart">添加</el-button>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-table
            :data="form.warehousingContentList"
            stripe
            highlight-current-row
            style="width: 90%"
          >
            <el-table-column prop="boxSeq" label="箱号" />
            <el-table-column prop="dySku" label="东岳sku"/>
            <el-table-column prop="name" label="名称" />
            <el-table-column prop="totalNum" label="数量" />
            <el-table-column prop="wrapType" label="包装方式" />
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="danger"
                  @click="
                                        handleDelete(scope.$index, scope.row)
                                    "
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item>
          <el-col :offset="18" v-if="onCreate">
            <el-button type="primary" @click="submitForm()">立即创建</el-button>
          </el-col>
          <el-col :offset="18" v-if="onUpdate">
            <el-button type="primary" @click="updateForm()">立即更新</el-button>
          </el-col>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from '../../utils/service';

export default {
  name: 'new-warehousing',
  data() {
    return {
      adminRole: false,
      onUpdate: false,
      onCreate: true,
      dialogImageUrl: '',
      dialogVisible: false,
      form: {
        fromAddress: '',
        target: '岡山县岡山市中区新京橋3丁目4-26',
        warehousingNo: '',
        method: '东岳头程',
        creator: '',
        carrier: '',
        trackNo: '',
        deliverMethod: '',
        clearanceType: '',
        taxType: '',
        insurance: false,
        insuranceNum: 0,
        channel: '',
        estimatedDate: new Date(),
        warehousingContentList: [],
      },
      currContent: {
        warehousingNo: '',
        sku: '',
        name: '',
        boxSeq: '',
        totalNum: '',
        wrapType: '',
      },
      contentMap: {},
      checkRules: {},
      channels: [],
      products: [],
      productMap: {},
      selectedProductMap: {},
      tableLoading: false,
      arr: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      wrapTypeArr: ['自带包装', '非自带包装'],
      users: [],
      pickerOptions1: {
        disabledDate(time) {
          return time.getTime() < Date.now();
        },
        shortcuts: [
          {
            text: '明天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', date);
            },
          },
          {
            text: '一周后',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            },
          },
          {
            text: '两周后',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7 * 2);
              picker.$emit('pick', date);
            },
          },
        ],
      },
      myProducts: [],
    };
  },
  created() {
    this.initPage();
    this.initUserList();
    this.hasAdminRole();
  },
  inject: ['reload'],
  watch: {
    $route() {
      this.initPage();
    },
  },
  methods: {
    initUserList: function() {
      request({
        url: '/sys_user/query4Option',
        method: 'post',
        data: {
          current: null,
          size: 'all',
        },
      }).then((res) => {
        this.users = res.data['page'].records;
      });
    },
    hasAdminRole() {
      request({
        url: '/sys_user/info',
        method: 'get',
      }).then((res) => {
        const roles = res.data['userInfo'].roles;
        for (let i = 0; i < roles.length; i++) {
          const role = roles[i];
          const val = role['val'];
          if (val === 'root' || val === 'operator') {
            this.adminRole = true;
          }
        }
        if (!this.adminRole) {
          this.filterProductAndUserAddress(res.data.userInfo.uname);
        }
      });
    },
    filterProductAndUserAddress(val) {
      request({
        url: '/product/listByUser?username='+val,
        method: 'get',
      }).then((res) => {
        this.myProducts = res.data.data;
        if (this.myProducts == null || this.myProducts.length <= 0) {
          this.$message.error('当前用户未配置商品，请重新选择用户或者去为该用户添加商品');
          for (let i = 0; i < this.users.length; i++) {
            const subUser = this.users[i];
            if (subUser['uname'] === val) {
              subUser['disabled'] = true;
            }
            this.users[i] = subUser;
          }
          this.form.creator = '';
          return;
        }
        this.storeProduct2Local(this.myProducts);
        this.form.warehousingContentList = [];
        this.currContent = {
          dySku: '',
          warehousingNo: '',
          sku: '',
          name: '',
          boxSeq: '',
          totalNum: '',
          wrapType: '',
        };
      });
    },
    initPage() {
      request({
        url: '/channel/list/4',
        method: 'get',
      }).then((res) => {
        this.channels = res.data.data;
      });
      const warehousingNo = this.$route.query.warehousingNo;
      if (warehousingNo !== undefined && warehousingNo.length > 0) {
        this.onUpdate = true;
        this.onCreate = false;
        request({
          url: '/warehousing/info?warehousingNo=' + warehousingNo,
          method: 'get',
        }).then((res) => {
          const warehousing = res.data.data;
          this.form.warehousingNo = warehousing.warehousingNo;
          this.form.method = warehousing.method;
          this.form.carrier = warehousing.carrier;
          this.form.trackNo = warehousing.trackNo;
          this.form.deliverMethod = warehousing.deliverMethod;
          this.form.clearanceType = warehousing.clearanceType;
          this.form.taxType = warehousing.taxType;
          if (this.form.insurance === 'Y') {
            this.form.insurance = true;
            this.form.insuranceNum = warehousing.insuranceNum;
          }
          this.form.channel = warehousing.channel;
          this.form.estimatedDate = warehousing.estimatedDate;
          this.form.warehousingContentList = warehousing.warehousingContentList;
        });
      }
    },
    initChannel() {
      request({
        url: '/channel/list/1',
        method: 'get',
      }).then((res) => {
        this.channels = res.data.data;
      });
    },
    getOrdNo() {
      request({
        url: '/generate/pk/warehousing',
        method: 'get',
      }).then((res) => {
        this.form.warehousingNo = res.data.data;
        this.currContent.warehousingNo = res.data.data;
      });
    },
    add2Cart() {
      if (!this.currContent.dySku || this.currContent.totalNum === 0) {
        this.$message.warning('请选择入库商品或者调整数量');
        return;
      }
      this.currContent.warehousingNo = this.form.warehousingNo;
      const dySku = this.currContent.dySku;
      const boxSeq = this.currContent.boxSeq;
      const boxContentMap = this.selectedProductMap[boxSeq];
      if (boxContentMap === undefined || boxContentMap.length <= 0) {
        this.selectedProductMap[boxSeq] = {};
        this.selectedProductMap[boxSeq][dySku] = this.currContent;
        this.pushData2Table();
      } else {
        if (boxContentMap.hasOwnProperty(dySku)) {
          this.$confirm('相同箱号中的相同sku产品将合并', '提示', confirm)
              .then(() => {
                const oriContent = boxContentMap[dySku];
                oriContent.totalNum += this.currContent.totalNum;
                this.pushData2Table();
              })
              .catch(() => {
                this.$message.info('请重新选择箱号');
              });
        } else {
          this.selectedProductMap[boxSeq][dySku] = this.currContent;
          this.pushData2Table();
        }
      }
    },
    pushData2Table() {
      this.form.warehousingContentList = [];
      for (const boxSeq in this.selectedProductMap) {
        const boxContainer = this.selectedProductMap[boxSeq];
        for (const key in boxContainer) {
          if (boxContainer.hasOwnProperty(key)) {
            const product = boxContainer[key];
            this.form.warehousingContentList.push(product);
          }
        }
      }
      this.currContent = {
        warehousingNo: '',
        dySku: '',
        name: '',
        boxSeq: '',
        totalNum: '',
        wrapType: '',
      };
    },
    handleValueChange(value) {
      this.currContent.name = this.productMap[value]['name'];
    },
    handleDelete(index, row) {
      this.form.warehousingContentList.splice(index, 1);
      delete this.selectedProductMap[row.boxSeq][row.dySku];
    },
    submitForm() {
      if (this.adminRole && this.form.creator.length <= 0) {
        this.$message.warning('请选择入库单所属人');
        return;
      }
      if (this.form.insurance) {
        this.form.insurance = 'Y';
      } else {
        this.form.insurance = 'N';
      }
      request({
        url: '/warehousing/add',
        method: 'post',
        data: this.form,
      }).then((ret) => {
        this.$message.success('成功新建入库订单' + ret.data.warehousingNo);
        this.reload();
      });
    },
    updateForm() {
      if (this.form.insurance) {
        this.form.insurance = 'Y';
      } else {
        this.form.insurance = 'N';
      }
      request({
        url: '/warehousing/update',
        method: 'post',
        data: this.form,
      }).then(() => {
        this.$message.success('更新订单');
        this.$router.push({path: '/new-warehousing/new-warehousing'});
        this.initPage();
        this.reload();
      });
    },
    storeProduct2Local(myProducts) {
      for (const myProduct of myProducts) {
        const subProduct = myProduct;
        this.productMap[subProduct['dySku']] = subProduct;
      }
    },
  },
};
</script>
