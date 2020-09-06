<template>
  <div class="login-container">
    <div class="app-container">
      <el-form :ref="form" :model="form" label-width="120px">
        <el-form-item label="退货单所属用户" v-if="adminRole">
          <el-col :span="12">
            <el-form-item label="所属用户">
              <el-tooltip content="所属用户" placement="top">
                <el-select
                    filterable
                    clearable
                    v-model="form.creator"
                    placeholder="请选择所属用户"
                >
                  <el-option
                      v-for="creator in users"
                      :key="creator.uname"
                      :label="creator.nick"
                      :value="creator.uname"
                  ></el-option>
                </el-select>
              </el-tooltip>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="退货单号">
          <el-col :span="12">
            <el-form-item label="退货单号">
              <el-input
                  placeholder="请输入或点击按钮获取退货单号"
                  v-model="form.returnNo"
                  @input="trimInput"
              >
                <el-button
                    slot="append"
                    v-bind:disabled="onUpdate"
                    @click="getOrdNo"
                >获取单号
                </el-button>
              </el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="订单号">
          <el-col :span="12">
            <el-form-item label="是否对应订单号">
              <el-switch
                  v-model="form.withoutOrderNoFlag"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.withoutOrderNoFlag">
            <el-form-item label="">
              <el-input
                  placeholder="请输入订单号"
                  v-model="form.orderNo"
              >
                <el-button slot="append" icon="el-icon-search" @click="initOrderContent">订单内容填充</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" v-if="oriOrd.showFlag">
            <el-col :span="6">订单号:{{ oriOrd.orderNo }}</el-col>
            <el-col :span="6">订单类型:{{ oriOrd.categoryName }}</el-col>
            <el-col :span="6">订单当前状态:{{ oriOrd.statusDesc }}</el-col>
            <el-col :span="24">订单发货信息:</el-col>
            <el-col :span="6">道/府/县:{{ oriOrd.fromKenName }}</el-col>
            <el-col :span="6">城市:{{ oriOrd.fromCityName }}</el-col>
            <el-col :span="6">乡:{{ oriOrd.fromTownName }}</el-col>
            <el-col :span="6">邮编:{{ oriOrd.fromZipCode }}</el-col>
            <el-col :span="6">详细地址:{{ oriOrd.fromDetailAddress }}</el-col>
            <el-col :span="6">发货人:{{ oriOrd.fromName }}</el-col>
            <el-col :span="6">发货人联系方式:{{ oriOrd.fromContact }}</el-col>
            <el-col :span="24">订单收货信息:</el-col>
            <el-col :span="6">道/府/县:{{ oriOrd.toKenName }}</el-col>
            <el-col :span="6">城市:{{ oriOrd.toCityName }}</el-col>
            <el-col :span="6">乡:{{ oriOrd.toTownName }}</el-col>
            <el-col :span="6">邮编:{{ oriOrd.toZipCode }}</el-col>
            <el-col :span="6">详细地址:{{ oriOrd.toDetailAddress }}</el-col>
            <el-col :span="6">发货人:{{ oriOrd.toName }}</el-col>
            <el-col :span="6">发货人联系方式:{{ oriOrd.toContact }}</el-col>
            <el-col :span="24">订单内容:</el-col>
            <div v-for="content in oriOrd.contentList" v-bind:key="content.uuid">
              <h6>
                <el-col :span="6">sku: {{ content.sku }}</el-col>
              </h6>
              <h6>
                <el-col :span="6">商品名称: {{ content.name }}</el-col>
              </h6>
              <h6>
                <el-col :span="6">商品数量: {{ content.picked }}</el-col>
              </h6>
              <h6>
                <el-col :span="6">商品价格: {{ content.price }}</el-col>
              </h6>
            </div>
          </el-col>
        </el-form-item>
        <el-form-item label="">
          <el-col :span="12">
            <el-form-item label="承运人">
              <el-input
                  placeholder="请输入退货订单承运人"
                  v-model="form.carrier"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="承运人追踪单号">
              <el-input
                  placeholder="请输入退货订单追踪单号"
                  v-model="form.trackNo"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item style="color: #606266;font-size: 14px;"
        >收件人信息可不填，默认为 收件地址 [日本岡山仓(okayama)]
          收件人 [东岳物流]
        </el-form-item>
        <el-form-item label="收件人信息">
          <el-col :span="8">
            <el-form-item label="收件人姓名">
              <el-input
                  v-model="form.toName"
                  placeholder="收件人姓名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人联系方式">
              <el-input
                  v-model="form.toContact"
                  placeholder="收件人联系方式"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人邮编">
              <el-input
                  v-model="form.toZipCode"
                  placeholder="收件人邮编"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" style="margin-top: 1%">
            <el-form-item label="收件地址">
              <el-input v-model="form.toAddressLine1" clearable placeholder="请输入道/府/县-城市-乡"></el-input>
              <el-input v-model="form.toAddressLine2" clearable placeholder="请输入详细地址1"
                        style="margin-top: 1%"></el-input>
              <el-input v-model="form.toAddressLine3" clearable placeholder="请输入详细地址2"
                        style="margin-top: 1%"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="发件人信息">
          <el-col :span="8">
            <el-form-item label="发件人姓名">
              <el-input
                  v-model="form.fromName"
                  placeholder="发件人姓名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发件人联系方式">
              <el-input
                  v-model="form.fromContact"
                  placeholder="发件人联系方式"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发件人邮编">
              <el-input
                  v-model="form.fromZipCode"
                  placeholder="发件人邮编"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" style="margin-top: 1%">
            <el-form-item label="发件地址">
              <el-input v-model="form.fromAddressLine1" clearable placeholder="请输入道/府/县-城市-乡"></el-input>
              <el-input v-model="form.fromAddressLine2" clearable placeholder="请输入详细地址1"
                        style="margin-top: 1%"></el-input>
              <el-input v-model="form.fromAddressLine3" clearable placeholder="请输入详细地址2"
                        style="margin-top: 1%"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="退件货物">
          <el-col :span="5">
            <el-form-item label="sku">
              <el-input
                  v-model="content.sku"
                  placeholder="请输入或者扫描sku"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="名称">
              <el-input
                  v-model="content.name"
                  placeholder="请输入产品名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="商品数量">
              <el-input-number
                  v-model="content.num"
                  :min="1"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="2" style="margin-left: 2%">
            <el-button type="primary" round @click="add2Cart"
            >添加
            </el-button>
          </el-col>
        </el-form-item>
        <el-form-item label="">
          <el-table
              :data="form.contentList"
              stripe
              style="width: 90%"
          >
            <el-table-column
                prop="sku"
                label="sku"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
            ></el-table-column>
            <el-table-column
                prop="num"
                label="商品数量"
                width="180"
            ></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    type="danger"
                    @click="
                                        handleDelete(scope.$index, scope.row)
                                    "
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="退件相关图片">
          <el-upload
              :action="actionLink1"
              with-credentials
              multiple
              list-type="picture"
              :file-list="fileList"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :data="form"
              ref="upload"
              :on-error="handleError"
              :limit="3"
          >
            <el-button slot="trigger" size="small" type="primary"
            >选取文件
            </el-button>
            <el-button
                style="margin-left: 10px;"
                size="small"
                type="success"
                @click="submitUpload"
            >上传到服务器
            </el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件，且不超过2MB
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt=""/>
          </el-dialog>
        </el-form-item>
        <el-form-item>
          <el-col :offset="18">
            <el-button
                type="primary"
                @click="submitForm()"
                v-if="onCreate"
            >立即创建
            </el-button>
            <el-button
                type="primary"
                @click="updateForm()"
                v-if="onUpdate"
            >立即更新
            </el-button>
            <el-button @click="resetForm('form')">重置</el-button>
          </el-col>
        </el-form-item>
      </el-form>

      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt=""/>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from '../../utils/service';

export default {
  name: 'new-returns',
  data() {
    return {
      actionLink1: process.env.BASE_API + '/return/img/put',
      fileList: [],
      onCreate: true,
      dialogImageUrl: '',
      dialogVisible: false,
      onUpdate: false,
      adminRole: false,
      showFlag: false,
      users: [],
      orders: {},
      ordersOption: [],
      address: [],
      form: {
        returnNo: '',
        creator: '',
        withoutOrderNoFlag: false,
        toName: '',
        toContact: '',
        toZipCode: '',
        toDetailAddress: '',
        fromName: '',
        fromContact: '',
        chinaCarrier: '',
        fromZipCode: '',
        fromDetailAddress: '',
        address: {},
        toAddress: {},
        selectedAddress: [],
        selectedtoAddress: [],
        carrier: '',
        trackNo: '',
        contentList: [],
        fromKenId: '',
        fromCityId: '',
        fromTownId: '',
        toKenId: '',
        toCityId: '',
        toTownId: '',
      },
      content: {
        returnNo: '',
        sku: '',
        num: '',
        name: '',
        selectedProductMaxNum: 20,
      },
      contentMap: {},
      oriOrd: {},
      toKenAddress: [],
      fromKenAddress: [],
      toCityAddress: [],
      fromCityAddress: [],
      toTownAddress: [],
      fromTownAddress: [],
      toTownAddressMap: {},
      fromTownAddressMap: {},
    };
  },
  created() {
    this.hasAdminRole();
    this.initUserList();
    this.initPage();
  },
  inject: ['reload'],
  watch: {
    $route() {
      this.initPage();
    },
  },
  methods: {
    initPage() {
      const returnNo = this.$route.query.returnNo;
      if (returnNo) {
        this.getReturnOrdDetail(returnNo);
      }
    },
    getReturnOrdDetail(returnNo) {
      // todo more specific
      request({
        url: '/return/returnOrdDetail?returnNo=' + returnNo,
        method: 'get',
      }).then((ret) => {
        this.form = ret.data.returnOrd;
      });
    },
    getOrdNo() {
      request({
        url: '/generate/pk/returning',
        method: 'get',
      }).then((res) => {
        this.form.returnNo = res.data.data;
      });
    },
    trimInput() {
      this.form.returnNo = this.form.returnNo.trim();
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
    hasAdminRole() {
      request({
        url: '/sys_user/info',
        method: 'get',
      }).then((res) => {
        const roles = res.data.userInfo.roles;
        for (let i = 0; i < roles.length; i++) {
          const role = roles[i];
          const val = role['val'];
          if (val === 'root' || val === 'operator') {
            this.adminRole = true;
          }
        }
      });
    },
    handleAddressChange(value) {
      this.form.address.ken = value[0];
      this.form.address.city = value[1];
      this.form.address.town = value[2];
    },
    handleAddressChange2(value) {
      this.form.toAddress.ken = value[0];
      this.form.toAddress.city = value[1];
      this.form.toAddress.town = value[2];
    },
    initOrderContent() {
      request({
        url: '/ord/ordContent?ordNo=' + this.form.orderNo,
        method: 'get',
      }).then((ret) => {
        this.oriOrd = ret.data.data;
        if (this.oriOrd.toKenId) {
          this.form.fromKenId = this.oriOrd.toKenId;
          this.form.fromCityId = this.oriOrd.toCityId;
          this.getFromCityAddress();
          if (this.oriOrd.toCityId) {
            this.form.fromCityId = this.oriOrd.toCityId;
            this.form.fromTownId = this.oriOrd.toTownId;
            this.getFromTownAddress();
            this.form.fromTownId = this.oriOrd.toTownId;
            if (this.oriOrd.toZipCode) {
              this.form.fromZipCode = this.oriOrd.toZipCode;
            }
            if (this.oriOrd.toName) {
              this.form.fromName = this.oriOrd.toName;
            }
            if (this.oriOrd.toContact) {
              this.form.fromContact = this.oriOrd.toContact;
            }
            if (this.oriOrd.toDetailAddress) {
              this.form.fromDetailAddress = this.oriOrd.toDetailAddress;
            }
          }
        }
        this.oriOrd.showFlag = true;
      });
    },
    add2Cart() {
      const sku = this.content.sku;
      if (sku === '') {
        this.$message.warning('请输入sku');
        return;
      }
      let content4CurrSku;
      if (this.contentMap.hasOwnProperty(sku)) {
        content4CurrSku = this.contentMap[sku];
        content4CurrSku.num += this.content.num;
        content4CurrSku.returnNo = this.content.returnNo;
        content4CurrSku.name = this.content.name;
      } else {
        content4CurrSku = this.content;
      }
      this.contentMap[sku] = content4CurrSku;
      this.form.contentList = [];
      for (const key in this.contentMap) {
        if (this.contentMap.hasOwnProperty(key)) {
          this.form.contentList.push(this.contentMap[key]);
        }
      }
      this.content = {
        returnNo: '',
        sku: '',
        num: '',
        name: '',
        selectedProductMaxNum: 20,
      };
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleRemove(file) {
      request({
        url: '/return/img/drop?returnNo=' + this.form.returnNo,
        method: 'get',
      }).then(() => {
        this.$message.success('成功删除关联图片');
      });
    },
    handleError(err) {
      this.$message.error(JSON.parse(err.message)['message']);
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    submitForm() {
      const toAddr = this.form.address;
      const fromAddr = this.form.toAddress;
      this.form.fromKenId = fromAddr.ken;
      this.form.fromCityId = fromAddr.city;
      this.form.fromTownId = fromAddr.town;
      this.form.toKenId = toAddr.ken;
      this.form.toCityId = toAddr.city;
      this.form.toTownId = toAddr.town;

      request({
        url: '/return/add',
        method: 'post',
        data: this.form,
      }).then(() => {
        this.$message.success('退货创建成功');
        this.reload();
      });
    },
    getFromCityAddress() {
      if (this.form.fromKenId) {
        request({
          url: '/address/getCity?kenId=' + this.form.fromKenId,
          method: 'get',
        }).then((ret) => {
          this.fromCityAddress = ret.data.data;
        });
        this.form.fromCityId = '';
        this.form.fromTownId = '';
      } else {
        this.$message.warning('请选择 道/府/县');
        this.form.fromCityId = '';
        this.form.fromTownId = '';
        this.fromCityAddress = [];
        this.fromTownAddress = [];
      }
    },
    getToCityAddress() {
      if (this.form.toKenId) {
        request({
          url: '/address/getCity?kenId=' + this.form.toKenId,
          method: 'get',
        }).then((ret) => {
          this.toCityAddress = ret.data.data;
        });
        this.form.toCityId = '';
        this.form.toTownId = '';
      } else {
        this.$message.warning('请选择 道/府/县');
        this.form.toCityId = '';
        this.form.toTownId = '';
        this.toCityAddress = [];
        this.toTownAddress = [];
      }
    },
    getToTownAddress() {
      if (this.form.toCityId) {
        request({
          url: '/address/getTown?cityId=' + this.form.toCityId,
          method: 'get',
        }).then((ret) => {
          this.toTownAddress = ret.data.data;
          for (let i = 0; i < this.toTownAddress.length; i++) {
            const town = this.toTownAddress[i];
            if (!this.toTownAddressMap.hasOwnProperty(town.value)) {
              this.toTownAddressMap[town.value] = {
                'value': town.value,
                'label': town.label,
                'zip': town.zip,
              };
            }
          }
        });
        this.form.toTownId = '';
      } else {
        this.$message.warning('请选择 城市');
        this.form.toTownId = '';
        this.toTownAddress = [];
      }
    },
    getFromTownAddress() {
      if (this.form.fromCityId) {
        request({
          url: '/address/getTown?cityId=' + this.form.fromCityId,
          method: 'get',
        }).then((ret) => {
          this.fromTownAddress = ret.data.data;
          for (let i = 0; i < this.fromTownAddress.length; i++) {
            const town = this.fromTownAddress[i];
            if (!this.fromTownAddressMap.hasOwnProperty(town.value)) {
              this.fromTownAddressMap[town.value] = {
                'value': town.value,
                'label': town.label,
                'zip': town.zip,
              };
            }
          }
        });
        this.form.fromTownId = '';
      } else {
        this.$message.warning('请选择 城市');
        this.form.fromTownId = '';
        this.fromTownAddress = [];
      }
    },
    fillFromZipCode() {
      if (this.form.fromTownId) {
        const town = this.fromTownAddressMap[this.form.fromTownId];
        const zip = town.zip;
        if (zip !== this.form.fromZipCode) {
          this.$confirm('当前地址对应的邮编为 ' + zip + ' ,是否修改？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }).then(() => {
            this.form.fromZipCode = zip;
            this.$message({
              type: 'success',
              message: '修改成功!',
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消修改',
            });
          });
        }
      } else {
        this.$message.warning('请选择乡镇');
      }
    },
  },
};
</script>
