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
                  @change="initOrderContent"
              ></el-input>
            </el-form-item>
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
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="道/府/县-城市-乡">
              <el-cascader
                  :span="12"
                  :options="address"
                  v-model="form.selectedAddress"
                  @change="handleAddressChange"
                  style="width: 80%"
              >
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="收件人详细地址">
              <el-input
                  v-model="form.toDetailAddress"
                  placeholder="收件人详细地址"
              ></el-input>
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
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="道/府/县-城市-乡">
              <el-cascader
                  :span="12"
                  :options="address"
                  v-model="form.selectedfromAddress"
                  @change="handleAddressChange2"
                  style="width: 80%"
              >
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="发件人详细地址">
              <el-input
                  v-model="form.fromDetailAddress"
                  placeholder="发件人详细地址"
              ></el-input>
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
    };
  },
  created() {
    this.hasAdminRole();
    this.initUserList();
    this.getAddress();
  },
  inject: ['reload'],
  watch: {
    $route() {
      this.initPage();
    },
  },
  methods: {
    getOrdNo() {
      request({
        url: '/generate/pk/returning',
        method: 'get',
      }).then((res) => {
        this.form.returnNo = res.data.data;
      });
    },
    trimInput() {
      this.form.orderNo = this.form.orderNo.trim();
    },
    getAddress() {
      request({
        url: '/address/getKen',
        method: 'get',
      }).then((res) => {
        this.address = res.data.data;
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
    initOrderContent(val) {
      console.log(val);
    },
    add2Cart() {
      const sku = this.content.sku;
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
  },
};
</script>
