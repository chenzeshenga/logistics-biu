<template>
  <div class="login-container">
    <div class="app-container">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="订单基本信息">
          <el-col :span="12">
            <el-form-item label="订单号">
              <el-input v-model="form.orderNo" placeholder="请输入或点击按钮获取订单号">
                <el-button slot="append" @click="getOrdNo">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="订单类型">
              <el-select v-model="form.category" placeholder="请选择订单类型">
                <el-option label="海外仓代发订单" value="1"/>
                <el-option label="特色小包" value="2"/>
                <el-option label="单票单清" value="3"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="运送渠道">
              <el-cascader
                :options="channels"
                v-model="selectedChannels"
                @change="handleChange">
              </el-cascader>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-col :span="12">
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
              <el-cascader :options="myProducts"
                           v-model="selectedProduct"
                           @change="handleProductChange"
                           filterable>
              </el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="名称">
              <el-input disabled v-model="content.name">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="商品价值">
              <el-input disabled v-model="content.price">
              </el-input>
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
        <el-table
          :data="form.contentList"
          stripe
          style="width: 100%">
          <el-table-column
            prop="sku"
            label="sku/东岳Sku"
            width="200">
          </el-table-column>
          <el-table-column
            prop="name"
            label="商品名称"
          >
          </el-table-column>
          <el-table-column
            prop="price"
            label="商品价格"
            width="180">
          </el-table-column>
          <el-table-column
            prop="num"
            label="商品数量"
            width="180">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </div>
    <el-button @click="createOrd" type="primary" style="margin-left: 90%">确认</el-button>
  </div>
</template>

<script>

  import request from '@/utils/request'

  export default {
    name: 'Menu1',
    data() {
      return {
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
          selectedtoAddress: []
        },
        channels: [],
        selectedChannels: [],
        address: [],
        content: {
          sku: '',
          name: '',
          price: '',
          num: 0
        },
        myProducts: [],
        selectedProduct: [],
        productMap: {},
      }
    },
    created() {
      this.getAddress();
      this.getMyProducts();
    },
    methods: {
      initPage() {
        let ordno = this.$route.query.ordno;
        if (ordno != null && ordno.length > 0) {
          request({
            url: "ord/get/" + ordno,
            method: "get"
          }).then(res => {
            console.log(res);
            this.form = res.data.data;
            this.selectedChannels.push(res.data.data.channel)
          })
        }
      },
      onSubmit() {
        this.$message('submit!')
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      },
      getOrdNo() {
        request({
          url: "/generate/pk",
          method: 'get'
        }).then(res => {
          this.form.orderNo = res.data.data;
        })
      },
      getOrdNo2() {
        request({
          url: "/generate/pk",
          method: 'get'
        }).then(res => {
          this.form.trackNo = res.data.data;
        })
      },
      listChannel() {
        request({
          url: "/channel/list",
          method: 'get'
        }).then(res => {
          this.channels = res.data.data
          this.initPage();
        })
      },
      getAddress() {
        request({
          url: "/address/getKen",
          method: 'get'
        }).then(res => {
          this.address = res.data.data;
          this.listChannel();
        })
      },
      getMyProducts() {
        request({
          url: "/product/list",
          method: 'get'
        }).then(res => {
          this.myProducts = res.data.data;
          for (let index in this.myProducts) {
            let subProduct = this.myProducts[index];
            this.productMap[subProduct["value"].split("/")[0]] = subProduct;
          }
          console.log(this.productMap);
        })
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
        let str = value[0].split("/");
        let sku = str[0];
        let skuLabel = sku + "/" + str[1];
        let product = this.productMap[sku];
        this.content.sku = skuLabel;
        this.content.name = product.name;
        this.content.price = product.price;
      },
      add2Cart() {
        let tmpContent = {};
        tmpContent = JSON.parse(JSON.stringify(this.content));
        this.form.contentList.push(tmpContent);
      },
      handleDelete(index, row) {
        this.form.contentList.splice(index, 1);
      },
      createOrd() {
        request({
          url: "/ord/add",
          method: "post",
          data: this.form
        }).then(res => {
          this.$message(res.data.data + '个订单已保存');
        });
      }
    }
  }
</script>

