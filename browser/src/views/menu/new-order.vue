<template>
  <div class="login-container">
    <div class="app-container">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="订单基本信息">
          <el-col :span="12">
            <el-form-item label="订单号">
              <el-input v-model="form.orderNo" v-bind:disabled="onUpdate" placeholder="请输入或点击按钮获取订单号">
                <el-button slot="append" v-bind:disabled="onUpdate" @click="getOrdNo">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单类型">
              <el-select v-model="form.category" placeholder="请选择订单类型">
                <el-option label="海外仓代发订单" value="1"/>
                <el-option label="虚拟海外仓" value="3"/>
                <el-option label="单票单清" value="2"/>
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
            <el-form-item label="中国追踪单号">
              <el-input v-model="form.chinaNo" placeholder="如果为特色小包和单票单清业务，请输入中国国内单号"></el-input>
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
        <el-form-item style="color: #606266;font-size: 14px;">发件人信息可不填，默认为 发件地址 [日本岡山仓(okayama)] 发件人 [东岳物流]</el-form-item>
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
        <el-form-item label="是否代收">
          <el-switch
            v-model="form.collect"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </el-form-item>
        <el-form-item label="订单内容">
          <el-col :span="5">
            <el-form-item label="sku">
              <el-cascader :options="myProducts" v-model="selectedProduct" @change="handleProductChange" filterable></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="名称">
              <el-input disabled v-model="content.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="商品价值">
              <el-input disabled v-model="content.price"></el-input>
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
        <el-table :data="form.contentList" stripe style="width: 100%">
          <el-table-column prop="sku" label="sku/东岳Sku" width="200"></el-table-column>
          <el-table-column prop="name" label="商品名称"></el-table-column>
          <el-table-column prop="price" label="商品价格" width="180"></el-table-column>
          <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </div>
    <el-button @click="createOrd" v-if="onCreate" type="primary" style="margin-left: 90%">确认</el-button>
    <el-button @click="updateOrd" v-if="onUpdate" type="primary" style="margin-left: 90%">更新</el-button>
  </div>
</template>

<script>
  import request from '@/utils/request';

  export default {
    name: 'Menu1',
    data() {
      return {
        onUpdate: false,
        onCreate: true,
        form: {
          orderNo: '',
          category: '',
          channel: '',
          chinaNo: '',
          trackNo: '',
          fromName: '',
          fromContact: '',
          fromZipCode: '',
          fromDetailAddress: '',
          toName: '',
          toContact: '',
          toZipCode: '',
          toDetailAddress: '',
          address: {},
          toAddress: {},
          collect: false,
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
      };
    },
    created() {
      this.getAddress();
      this.getMyProducts();
      this.defaultFormData = JSON.parse(JSON.stringify(this.form));
    },
    methods: {
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
        }).then(res => {
          this.$message.success('当前订单已更新');
          console.log('/order-list/mgt/type' + this.form.category + '/status' + this.status);
          this.$router.push({
            path: '/order-list/mgt/type' + this.form.category + '/status' + this.status,
          });
        });
      },
    },
  };
</script>

