<template>
  <div class="login-container">
    <div class="app-container">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="订单基本信息">
          <el-col :span="12">
            <el-form-item label="订单号">
              <el-input v-model="form.orderNo" v-bind:disabled="onUpdate" placeholder="请输入或点击按钮获取订单号"
                        @input="trimInput">
                <el-button slot="append" v-bind:disabled="onUpdate" @click="getOrdNo">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单类型">
              <el-select filterable v-model="form.category" placeholder="请选择订单类型" @change="changeByCategory">
                <el-option label="海外仓代发订单" value="1"/>
                <el-option label="单票单清" value="2"/>
                <el-option label="虚拟海外仓" value="3"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="运送渠道">
              <el-cascader :options="channels" v-model="selectedChannels" @change="handleChange"></el-cascader>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12" v-if="form.category !== '1'">
            <el-form-item label="中国承运人">
              <el-input v-model="form.chinaCarrier" placeholder="顺丰/圆通/中通..."></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.category !== '1'">
            <el-form-item label="中国追踪单号">
              <el-input v-model="form.chinaNo" placeholder="如果为特色小包和单票单清业务，请输入中国国内单号"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
            <el-form-item label="日本承运人">
              <el-input v-model="form.carrierDesc" disabled placeholder="请选择运送渠道"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="日本追踪单号">
              <el-input v-model="form.trackNo" placeholder="您可点击按钮预先获取单号，后台操作员将根据实际情况进行调整">
                <el-button slot="append" @click="getOrdNo2">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item style="color: #606266;font-size: 14px;">发件人信息可不填，默认为 发件地址 [日本岡山仓(okayama)] 发件人 [东岳物流]
        </el-form-item>
        <el-form-item label="发件人信息">
          <el-col :span="8">
            <el-form-item label="发件人姓名">
              <el-input v-model="form.fromName" placeholder="发件人姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发件人联系方式">
              <el-input v-model="form.fromContact" placeholder="发件人联系方式"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="发件人邮编">
              <el-input v-model="form.fromZipCode" placeholder="发件人邮编"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="道/府/县-城市-乡">
              <el-cascader :span="12"
                           :options="address"
                           v-model="form.selectedAddress"
                           @change="handleAddressChange"
                           style="width: 80%"
              >
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="发件人详细地址">
              <el-input v-model="form.fromDetailAddress" placeholder="发件人详细地址"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="收件人信息">
          <el-col :span="8">
            <el-form-item label="收件人姓名">
              <el-input v-model="form.toName" placeholder="收件人姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人联系方式">
              <el-input v-model="form.toContact" placeholder="收件人联系方式"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人邮编">
              <el-input v-model="form.toZipCode" placeholder="收件人邮编"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="道/府/县-城市-乡">
              <el-cascader :span="12"
                           :options="address"
                           v-model="form.selectedtoAddress"
                           @change="handleAddressChange2"
                           style="width: 80%"
              >
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 1%">
            <el-form-item label="收件人详细地址">
              <el-input v-model="form.toDetailAddress" placeholder="收件人详细地址"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="是否代收" v-if="!whetherChargeForThem">
                <el-switch
                  v-model="form.collect"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
                </el-switch>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="代收金额" v-if="form.collect && !whetherChargeForThem">
                <el-input-number v-model="form.collectNum"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="订单内容">
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="sku">
              <el-cascader :options="myProducts" v-model="selectedProduct" @change="handleProductChange"
                           filterable></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="sku">
              <el-input v-model="content.sku" placeholder="请输入或者扫描sku"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="名称">
              <el-input disabled v-model="content.name" placeholder="请输入产品名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="名称">
              <el-input v-model="content.name" placeholder="请输入产品名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="!skuFlag">
            <el-form-item label="商品价值">
              <el-input disabled v-model="content.price"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5" v-if="skuFlag">
            <el-form-item label="商品价值">
              <el-input-number v-model="content.price"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="商品数量">
              <el-input-number v-model="content.num" :min="1"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="2" style="margin-left: 2%">
            <el-button type="primary" round @click="add2Cart">添加</el-button>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-table :data="form.contentList" stripe style="width: 90%">
            <el-table-column prop="sku" label="sku/东岳Sku" width="250"></el-table-column>
            <el-table-column prop="name" label="商品名称"></el-table-column>
            <el-table-column prop="price" label="商品价格JPY" width="180"></el-table-column>
            <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
    </div>
    <el-row>
      <el-col :offset="17" :span="2">
        <el-button @click="createBatchOrd" v-if="onCreate" type="primary">批量创建</el-button>
      </el-col>
      <el-col :span="2">
        <el-button @click="createOrd" v-if="onCreate" type="primary">立即创建</el-button>
      </el-col>
    </el-row>
    <el-button @click="updateOrd" v-if="onUpdate" type="primary" style="margin-left: 90%">更新</el-button>
    <el-dialog title="批量创建" :visible.sync="dialogVisible4Excel" width="30%">
      <el-form :model="form">
        <el-form-item label="订单文件">
          <el-upload :action="actionLink" with-credentials :limit="1">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <!--            <el-button style="margin-left: 10px;" size="small" type="success" @click="uploadExcel">上传</el-button>-->
            <el-button style="margin-left: 150px;" size="small" type="success" @click="downloadTemplate">
              <svg-icon icon-class="doc"></svg-icon>
              模版文件
            </el-button>
            <div slot="tip" class="el-upload__tip">只能上传excel文件(xls/xlsx)，记录条数小于200条</div>
          </el-upload>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import request from '@/utils/service';

  export default {
    name: 'Menu1',
    data() {
      return {
        actionLink: process.env.BASE_API + '/ord/excel',
        onUpdate: false,
        onCreate: true,
        form: {
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
        address: [],
        content: {
          sku: '',
          name: '',
          price: '',
          num: 0,
          totalNum: 0,
        },
        myProducts: [],
        selectedProduct: [],
        productMap: {},
        selectedProductMap: {},
        status: '',
        defaultFormData: {},
        channelMap: {},
        skuFlag: false,
        whetherChargeForThem: false,
        dialogVisible4Excel: false,
      };
    },
    created() {
      this.getAddress();
      this.getMyProducts();
      this.defaultFormData = JSON.parse(JSON.stringify(this.form));
    },
    methods: {
      trimInput() {
        this.form.orderNo = this.form.orderNo.trim();
      },
      initPage() {
        const ordno = this.$route.query.ordno;
        this.status = this.$route.query.status;
        if (ordno != null && ordno.length > 0) {
          request({
            url: 'ord/get/' + ordno,
            method: 'get',
          }).then(res => {
            this.form = res.data.data;
            this.selectedChannels.push(res.data.data.channel);
            this.onUpdate = true;
            this.onCreate = false;
          });
        }
      },
      onSubmit() {
        this.$message('submit!');
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning',
        });
      },
      getOrdNo() {
        request({
          url: '/generate/pk',
          method: 'get',
        }).then(res => {
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
        }).then(res => {
          this.form.trackNo = res.data.data;
        });
      },
      listChannel() {
        request({
          url: '/channel/list',
          method: 'get',
        }).then(res => {
          this.channels = res.data.data;
          for (const index in this.channels) {
            const channel = this.channels[index];
            this.channelMap[channel['value']] = channel;
          }
          this.initPage();
        });
      },
      getAddress() {
        request({
          url: '/address/getKen',
          method: 'get',
        }).then(res => {
          this.address = res.data.data;
          this.listChannel();
        });
      },
      getMyProducts() {
        request({
          url: '/product/list',
          method: 'get',
        }).then(res => {
          this.myProducts = res.data.data;
          for (const index in this.myProducts) {
            const subProduct = this.myProducts[index];
            this.productMap[subProduct['value'].split('/')[0]] = subProduct;
          }
        });
      },
      handleChange(value) {
        this.form.channel = value[0];
        this.form.carrierDesc = this.channelMap[value[0]]['partnerDesc'];
        const checkedRules = this.channelMap[value[0]]['checkedRules'];
        this.whetherChargeForThem = (checkedRules.indexOf('whetherChargeForThem') <= -1);
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
        const str = value[0].split('/');
        const sku = str[0];
        const skuLabel = sku + '/' + str[1];
        const product = this.productMap[sku];
        this.content.sku = skuLabel;
        this.content.name = product.name;
        this.content.price = product.price;
      },
      add2Cart() {
        let tmpContent = {};
        tmpContent = JSON.parse(JSON.stringify(this.content));
        tmpContent['index'] = this.form.contentList.length;
        if (this.selectedProductMap.hasOwnProperty(this.content['sku'])) {
          this.selectedProductMap[this.content['sku']]['num'] += Number(this.content.num);
        } else {
          this.selectedProductMap[this.content['sku']] = tmpContent;
          this.form.contentList.push(this.selectedProductMap[this.content['sku']]);
        }
        // this.selectedProductMap[this.content["sku"]] = this.content;
      },
      handleDelete(index, row) {
        this.form.contentList.splice(index, 1);
      },
      createOrd() {
        request({
          url: '/ord/add',
          method: 'post',
          data: this.form,
        }).then(res => {
          this.$message.success(res.data.data + '个订单已保存');
          this.form = this.defaultFormData;
        }).catch(err => {
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
            path: '/order-list/mgt/type' + this.form.category + '/status' + this.status,
          });
        });
      },
      changeByCategory(val) {
        this.skuFlag = (val !== '1');
      },
      createBatchOrd() {
        this.dialogVisible4Excel = true;
      },
      downloadTemplate() {
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/template/file/PRODUCT_TEMPLATE';
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      },
    },
  };
</script>

