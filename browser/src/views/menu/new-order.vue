<template>
  <div class="login-container">
    <loading :active.sync="isLoading"
             :can-cancel="true"
             :is-full-page="fullPage"/>
    <div class="app-container">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="订单所属用户" v-if="adminRole">
          <el-col :span="12">
            <el-form-item label="所属用户">
              <el-tooltip content="所属用户" placement="top">
                <el-select
                    filterable
                    clearable
                    v-model="form.creator"
                    placeholder="请选择所属用户"
                    @change="filterProduct"
                    value="">
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
        <el-form-item label="订单基本信息">
          <el-col :span="12">
            <el-form-item label="订单号">
              <el-input
                  v-model="form.orderNo"
                  v-bind:disabled="onUpdate"
                  placeholder="请输入或点击按钮获取订单号"
                  @input="trimInput"
              >
                <el-button
                    slot="append"
                    v-bind:disabled="onUpdate"
                    @click="getOrdNo"
                >获取单号
                </el-button
                >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单类型">
              <el-select
                  filterable
                  v-model="form.category"
                  placeholder="请选择订单类型"
                  @change="changeByCategory"
                  value="">
                <el-option label="海外仓代发订单" value="1"/>
                <el-option label="单票单清" value="2"/>
                <el-option label="虚拟海外仓" value="3"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="运送渠道">
              <el-cascader
                  :options="channels"
                  v-model="selectedChannels"
                  @change="handleChange"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12" v-if="form.category !== '1'">
            <el-form-item label="中国承运人">
              <el-input
                  v-model="form.chinaCarrier"
                  placeholder="顺丰/圆通/中通..."
              />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.category !== '1'">
            <el-form-item label="中国追踪单号">
              <el-input
                  v-model="form.chinaNo"
                  placeholder="如果为特色小包和单票单清业务，请输入中国国内单号"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
            <el-form-item label="日本承运人">
              <el-input
                  v-model="form.carrierDesc"
                  disabled
                  placeholder="请选择运送渠道"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="日本追踪单号">
              <el-input
                  v-model="form.trackNo"
                  placeholder="您可点击按钮预先获取单号，后台操作员将根据实际情况进行调整"
              >
                <el-button slot="append" @click="getOrdNo2"
                >获取单号
                </el-button
                >
              </el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-alert type="info" title="普通订单发件人信息可不填，默认为 发件地址 [日本岡山仓(okayama)] 发件人 [东岳物流], 若为单票单清业务，请填写发件人相关信息"
                  :closable="false" center show-icon></el-alert>
        <el-form-item label="发件人信息" style="margin-top: 1%">
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
              />
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
              <el-select v-model="form.fromKenId" filterable
                         clearable placeholder="请选择:道/府/县" :span="4"
                         @change="getFromCityAddress()">
                <el-option v-for="item in fromKenAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
              <el-select v-model="form.fromCityId" filterable
                         clearable placeholder="请选择:城市" :span="4"
                         @change="getFromTownAddress">
                <el-option v-for="item in fromCityAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
              <el-select v-model="form.fromTownId" filterable
                         clearable placeholder="请选择:乡" :span="4"
                         @change="fillFromZipCode"
              >
                <el-option v-for="item in fromTownAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="发件人详细地址">
              <el-input
                  v-model="form.fromDetailAddress"
                  placeholder="发件人详细地址"
              />
            </el-form-item>
          </el-col>
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
              <el-select v-model="form.toKenId" filterable
                         clearable placeholder="请选择:道/府/县" :span="4"
                         @change="getToCityAddress()">
                <el-option v-for="item in toKenAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
              <el-select v-model="form.toCityId" filterable
                         clearable placeholder="请选择:城市" :span="4"
                         @change="getToTownAddress">
                <el-option v-for="item in toCityAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
              <el-select v-model="form.toTownId" filterable
                         clearable placeholder="请选择:乡" :span="4"
                         @change="fillToZipCode"
              >
                <el-option v-for="item in toTownAddress"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
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
        <el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item
                  label="是否代收"
                  v-if="!whetherChargeForThem"
              >
                <el-switch
                    v-model="form.collect"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                >
                </el-switch>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                  label="代收金额"
                  v-if="form.collect && !whetherChargeForThem"
              >
                <el-input-number
                    v-model="form.collectNum"
                ></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="订单内容">
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="sku">
              <el-cascader
                  :options="myProducts"
                  v-model="selectedProduct"
                  @change="handleProductChange"
                  filterable
              ></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="sku">
              <el-input
                  v-model="content.sku"
                  placeholder="请输入或者扫描sku"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="名称">
              <el-input
                  disabled
                  v-model="content.name"
                  placeholder="请输入产品名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="名称">
              <el-input
                  v-model="content.name"
                  placeholder="请输入产品名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="商品价值">
              <el-input
                  disabled
                  v-model="content.price"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="商品价值">
              <el-input-number
                  v-model="content.price"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="商品数量">
              <el-input-number
                  v-model="content.num"
                  :min="1"
                  :max="selectedProductMaxNum"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="2" style="margin-left: 2%">
            <el-button type="primary" round @click="add2Cart"
            >添加
            </el-button
            >
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-table
              :data="form.contentList"
              stripe
              style="width: 90%"
          >
            <el-table-column
                prop="sku"
                label="东岳Sku"
                width="250"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
            ></el-table-column>
            <el-table-column
                prop="price"
                label="商品价格JPY"
                width="180"
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
                </el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
    </div>
    <el-row>
      <el-col :offset="17" :span="2">
        <el-button
            @click="createBatchOrd"
            v-if="onCreate"
            type="primary"
        >批量创建
        </el-button
        >
      </el-col>
      <el-col :span="2">
        <el-button @click="createOrd" v-if="onCreate" type="primary"
        >立即创建
        </el-button
        >
      </el-col>
    </el-row>
    <el-button
        @click="updateOrd"
        v-if="onUpdate"
        type="primary"
        style="margin-left: 90%"
    >更新
    </el-button
    >
    <el-dialog
        title="批量创建"
        :visible.sync="dialogVisible4Excel"
        width="30%"
    >
      <el-form :model="form">
        <el-form-item label="订单文件">
          <el-upload :action="actionLink" ref="upload" with-credentials :auto-upload="false" :limit="1">
            <el-button slot="trigger" size="small" type="primary"
            >选取文件
            </el-button
            >
            <el-button
                style="margin-left: 10px;"
                size="small"
                type="success"
                @click="submitUpload"
            >上传
            </el-button>
            <el-button
                style="margin-left: 150px;"
                size="small"
                type="success"
                @click="downloadTemplate"
            >
              <svg-icon icon-class="doc"/>
              模版文件
            </el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传excel文件(xls/xlsx)，推荐记录条数小于200条
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog
        title="代表"
        :visible.sync="dialogVisible4StandFor"
        width="25%"
    >
      <el-form>
        <el-form-item label="请选择您所代表的用户">
          <el-tooltip content="请选择您所代表的用户" placement="top">
            <el-select
                filterable
                clearable
                v-model="standFor"
                placeholder="请选择您所代表的用户"
                @change="changeUpdateLink"
                value="">
              <el-option
                  v-for="creator in users"
                  :key="creator.uname"
                  :label="creator.nick"
                  :value="creator.uname"
              ></el-option>
            </el-select>
          </el-tooltip>
        </el-form-item>
      </el-form>
      <el-row>
        <el-col :offset="14" :span="2">
          <el-button @click="triggerUploadDialog" type="primary"
          >确认
          </el-button
          >
        </el-col>
        <el-col :offset="3" :span="2">
          <el-button
              @click="dialogVisible4StandFor = false"
              type="primary"
          >取消
          </el-button
          >
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import request from '../../utils/service';
import loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  name: 'Menu1',
  components: {loading},
  data() {
    return {
      props: {
        lazy: true,
        lazyLoad(node, resolve) {
          const level = node.level;
          switch (level) {
            case 1:
              const kenId = node.data.value;
              request({
                url: '/address/getCity?kenId=' + kenId,
                method: 'get',
              }).then((ret) => {
                resolve(ret.data.data);
              });
              break;
            case 2:
              const cityId = node.data.value;
              request({
                url: '/address/getTown?cityId=' + cityId,
                method: 'get',
              }).then((ret) => {
                const townList = ret.data.data;
                const results = [];
                for (let i = 0; i < townList.length; i++) {
                  const sub = townList[i];
                  const result = {
                    label: sub.label,
                    value: sub.value,
                    leaf: true,
                  };
                  results.push(result);
                }
                console.log(results);
                resolve(results);
              });
              break;
            case 3:
              resolve(node);
              break;
            default:
              break;
          }
        },
      },
      isLoading: false,
      fullPage: true,
      actionLink: process.env.BASE_API + '/ord/excel',
      onUpdate: false,
      onCreate: true,
      adminRole: false,
      standFor: '',
      form: {
        creator: '',
        orderNo: '',
        category: '',
        channel: '',
        carrier: '',
        carrierDesc: '',
        chinaNo: '',
        trackNo: '',
        fromName: '',
        fromContact: '',
        chinaCarrier: '',
        fromZipCode: '',
        fromDetailAddress: '',
        toName: '',
        toContact: '',
        toZipCode: '',
        toDetailAddress: '',
        address: {},
        toAddress: {},
        collect: false,
        collectNum: 0,
        contentList: [],
        selectedAddress: [],
        selectedtoAddress: [],
      },
      channels: [],
      selectedChannels: [],
      toKenAddress: [],
      fromKenAddress: [],
      toCityAddress: [],
      fromCityAddress: [],
      toTownAddress: [],
      fromTownAddress: [],
      toTownAddressMap: {},
      fromTownAddressMap: {},
      content: {
        sku: '',
        name: '',
        price: '',
        num: 0,
        totalNum: 0,
      },
      myProducts: [],
      selectedProduct: [],
      selectedProductMaxNum: Number.POSITIVE_INFINITY,
      productMap: {},
      selectedProductMap: {},
      status: '',
      defaultFormData: {},
      channelMap: {},
      skuFlag: false,
      whetherChargeForThem: false,
      dialogVisible4Excel: false,
      dialogVisible4StandFor: false,
      users: [],
      search: {
        creator: '',
      },
    };
  },
  created() {
    this.defaultFormData = JSON.parse(JSON.stringify(this.form));
    this.initUserList();
    this.hasAdminRole();
    this.initPage();
    this.getKenAddress();
  },
  watch: {
    $route() {
      this.initPage();
    },
  },
  methods: {
    trimInput() {
      this.form.orderNo = this.form.orderNo.trim();
    },
    getOrdNo() {
      request({
        url: '/generate/pk',
        method: 'get',
      }).then((res) => {
        this.form.orderNo = res.data.data;
      });
    },
    getOrdNo2() {
      if (this.form.channel.length <= 0) {
        this.$message.warning('请选择渠道');
        return;
      }
      request({
        url: '/trackno/pk?channelCode=' + this.form.channel,
        method: 'get',
      })
          .then((res) => {
            this.form.trackNo = res.data.data;
          });
    },
    listChannel(val) {
      request({
        url: '/channel/list/' + val,
        method: 'get',
      }).then((res) => {
        this.channels = res.data.data;
        if (this.channels.length <= 0) {
          this.$message.warning(
              '当前无激活的渠道，请到渠道页面进行配置或者联系管理员',
          );
          return;
        }
        for (const channel of this.channels) {
          this.channelMap[channel['value']] = channel;
        }
      });
    },
    initPage() {
      const ordno = this.$route.query.ordno;
      if (ordno != null && ordno.length > 0) {
        request({
          url: 'ord/get/' + ordno,
          method: 'get',
        }).then((res) => {
          const ord = res.data.data;
          this.onUpdate = true;
          this.onCreate = false;
          if (ord.creator) {
            this.filterProduct(ord.creator);
          }
          if (ord.category) {
            this.skuFlag = (ord.category !== '1');
            this.listChannel(ord.category);
            this.selectedChannels.push(ord.channel);
          }
          if (ord.fromKenId) {
            this.form.fromKenId = ord.fromKenId;
            this.form.fromCityId = ord.fromCityId;
            this.getFromCityAddress();
            if (ord.fromCityId) {
              this.form.fromCityId = ord.fromCityId;
              this.form.fromTownId = ord.fromTownId;
              this.getFromTownAddress();
              this.form.fromTownId = ord.fromTownId;
            }
          }
          if (ord.toKenId) {
            this.form.toKenId = ord.toKenId;
            this.form.toCityId = ord.toCityId;
            this.getToCityAddress();
            if (ord.toCityId) {
              this.form.toCityId = ord.toCityId;
              this.form.toTownId = ord.toTownId;
              this.getToTownAddress();
              this.form.toTownId = ord.toTownId;
            }
          }
          setTimeout(() => {
            this.form = ord;
            const contentList = ord.contentList;
            this.form.contentList = [];
            for (const content of contentList) {
              this.content = content;
              this.add2Cart();
            }
          }, 1000);
        });
      }
    },
    getKenAddress() {
      request({
        url: '/address/getKen',
        method: 'get',
      }).then((res) => {
        this.toKenAddress = res.data.data;
        this.fromKenAddress = res.data.data;
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
    fillToZipCode() {
      if (this.form.toTownId) {
        const town = this.toTownAddressMap[this.form.toTownId];
        const zip = town.zip;
        if (zip !== this.form.toZipCode) {
          this.$confirm('当前地址对应的邮编为 ' + zip + ' ,是否修改？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }).then(() => {
            this.form.toZipCode = zip;
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
    getMyProducts() {
      request({
        url: '/product/list',
        method: 'get',
      }).then((res) => {
        this.myProducts = res.data.data;
        for (const myProduct of this.myProducts) {
          const subProduct = myProduct;
          this.productMap[
              subProduct['value'].split('/')[0]
          ] = subProduct;
        }
      });
    },
    handleChange(value) {
      this.form.channel = value[0];
      this.form.carrierDesc = this.channelMap[value[0]]['partnerDesc'];
      const checkedRules = this.channelMap[value[0]]['checkedRules'];
      this.whetherChargeForThem =
          checkedRules.indexOf('whetherChargeForThem') <= -1;
      if (this.whetherChargeForThem) {
        this.form.collect = false;
      }
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
    handleProductChange(value) {
      const sku = value[0];
      const product = this.productMap[sku];
      this.content.sku = sku;
      this.content.name = product.name;
      this.content.price = product.price;
      this.selectedProductMaxNum = Number(product.num);
      this.$message.info(
          '您选择的商品' + product.label + '当前可售数量为' + product.num,
      );
    },
    add2Cart() {
      let tmpContent = {};
      this.content.num = Number(this.content.num);
      tmpContent = JSON.parse(JSON.stringify(this.content));
      tmpContent['index'] = this.form.contentList.length;
      if (this.selectedProductMap.hasOwnProperty(this.content['sku'])) {
        const plannedNum =
            this.selectedProductMap[this.content['sku']]['num'] +
            Number(this.content.num);
        const product = this.productMap[
            this.content['sku']
        ];
        if (plannedNum > product.num) {
          this.selectedProductMap[this.content['sku']]['num'] =
              product.num;
          this.$message.warning(
              '当前订单中商品' +
              product.label +
              '总数量大于该商品可售数量，系统已自动调整为最大可售数量',
          );
        } else {
          this.selectedProductMap[this.content['sku']][
              'num'
          ] += Number(this.content.num);
        }
      } else {
        this.selectedProductMap[this.content['sku']] = tmpContent;
        this.form.contentList.push(
            this.selectedProductMap[this.content['sku']],
        );
      }
    },
    handleDelete(index, row) {
      const content = this.form.contentList[index];
      delete this.selectedProductMap[content.sku];
      this.form.contentList.splice(index, 1);
    },
    createOrd() {
      if (this.adminRole && this.form.creator.length <= 0) {
        this.$message.warning('请选择订单所属人');
        return;
      }
      request({
        url: '/ord/add',
        method: 'post',
        data: this.form,
      })
          .then(() => {
            this.$message.success('订单号' + this.form.orderNo + '已保存');
            this.form = this.defaultFormData;
          })
          .catch((err) => {
            console.log(err);
          });
    },
    updateOrd() {
      request({
        url: '/ord/update',
        method: 'post',
        data: this.form,
      }).then(() => {
        this.$message.success('当前订单已更新');
        this.$router.push({
          path:
              '/order-list/mgt/type' +
              this.form.category +
              '/status' +
              this.status,
        });
      });
    },
    changeByCategory(val) {
      this.skuFlag = val !== '1';
      this.listChannel(val);
    },
    createBatchOrd() {
      if (this.adminRole) {
        this.dialogVisible4StandFor = true;
      } else {
        this.dialogVisible4Excel = true;
      }
    },
    downloadTemplate() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/template/file/ORDER_TEMPLATE';
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
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
        url: '/sys_user//info',
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
    filterProduct(val) {
      request({
        url: '/product/listByUser',
        method: 'post',
        data: {
          user: val,
        },
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
        for (const myProduct of this.myProducts) {
          const subProduct = myProduct;
          this.productMap[subProduct['sku']] = subProduct;
        }
      });
    },
    changeUpdateLink(val) {
      this.actionLink += '?user=' + val;
      console.log(this.standFor);
      this.standFor = val;
    },
    triggerUploadDialog() {
      console.log(this.standFor);
      if (this.standFor.length > 0) {
        this.dialogVisible4StandFor = false;
        this.dialogVisible4Excel = true;
      } else {
        this.$message.warning('请选择所属用户');
      }
    },
    checkChannelSize() {
      console.log(this.channels);
      return false;
    },
    submitUpload() {
      this.$refs.upload.submit();
      this.$message.success('上传成功');
      this.dialogVisible4Excel = false;
    },
  },
};
</script>
