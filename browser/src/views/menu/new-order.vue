<template>
  <div class="login-container">
    <!--<h1>新建订单</h1>-->
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
                           v-model="form.selectedaddress"
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
        <el-form-item label="Instant delivery">
          <el-switch v-model="form.delivery"/>
        </el-form-item>
        <el-form-item label="Activity type">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="Online activities" name="type"/>
            <el-checkbox label="Promotion activities" name="type"/>
            <el-checkbox label="Offline activities" name="type"/>
            <el-checkbox label="Simple brand exposure" name="type"/>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="Resources">
          <el-radio-group v-model="form.resource">
            <el-radio label="Sponsor"/>
            <el-radio label="Venue"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Activity form">
          <el-input v-model="form.desc" type="textarea"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Create</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>
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
          selectedAddress: [],
          selectedtoAddress: []
        },
        channels: [],
        selectedChannels: [],
        address: []
      }
    },
    created() {
      this.listChannel();
      this.getAddress();
    },
    methods: {
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
        })
      },
      getAddress() {
        request({
          url: "/address/getKen",
          method: 'get'
        }).then(res => {
          this.address = res.data.data
        })
      },
      handleChange(value) {
        console.log(value);
        this.form.channel = value[0];
      },
      handleAddressChange(value) {
        this.form.address.ken = value[0];
        this.form.address.city = value[1];
        this.form.address.town = value[2];
        console.log(this.form);
      },
      handleAddressChange2(value) {
        this.form.toAddress.ken = value[0];
        this.form.toAddress.city = value[1];
        this.form.toAddress.town = value[2];
      }
    }
  }
</script>

