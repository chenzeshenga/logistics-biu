<template>
  <div class="login-container">
    <div class="app-container">
      <div style="margin-left:3%;margin-top:2%">
        订单号 <span class="ord-content">{{ ord.orderNo }}</span>
        <el-tag type="warning">{{ ord.statusDesc }}</el-tag>
        <el-button @click="showDetail" :style="ordDetail" style="margin-top: 2%">收起</el-button>
        <el-button @click="hiddenDetail" :style="ordDetailOpp" style="margin-top: 2%">展开</el-button>
        <br>
        <div :style="ordDetail">
          <el-divider/>
          <div style="margin-top: 1%;margin-left: 1%">
            <div>
              <span class="ord-title">订单基本信息</span><br>
              <div style="margin-top: 3%;margin-bottom: 2%">
            <span>
              订单号 <span class="ord-content">{{ ord.orderNo }}</span><br>
              用户自定义订单号 <span class="ord-content">{{ ord.userCustomOrderNo }}</span><br>
              订单类型 <span class="ord-content">{{ ord.categoryName }}</span><br>
              渠道 <span class="ord-content">{{ ord.channel }}</span><br>
              承运人 <span class="ord-content">{{ ord.carrierNo }}</span><br>
              追踪单号 <span class="ord-content">{{ ord.trackNo }}</span><br>
            </span>
              </div>
            </div>
            <el-divider/>
            <div>
              <span class="ord-title">订单地址信息</span>
              <div style="margin-top: 3%;margin-bottom: 2%">
                <span>
                  发件 <br>
                  发件人 <span class="ord-content">{{ ord.fromName }}</span><br>
                  联系方式 <span class="ord-content">{{ ord.fromContact }}</span><br>
                  邮编 <span class="ord-content">{{ ord.fromZipCode }}</span><br>
                  地址 <span class="ord-content">{{ ord.fromAddressLine1 }} {{ ord.fromAddressLine2 }} {{
                    ord.fromAddressLine3
                  }}</span><br>
                  <el-divider/>
                  收件 <br>
                  收件人 <span class="ord-content">{{ ord.toName }}</span><br>
                  联系方式 <span class="ord-content">{{ ord.toContact }}</span><br>
                  邮编 <span class="ord-content">{{ ord.toZipCode }}</span><br>
                  地址 <span class="ord-content">{{ ord.toAddressLine1 }} {{ ord.toAddressLine2 }} {{
                    ord.toAddressLine3
                  }}</span><br>
                </span>
              </div>
            </div>
            <el-divider/>
          </div>
        </div>
        <div>
          <div>
            <span class="ord-title">订单内容</span>
            <div v-for="content in ord.contentList" :key="content.uuid" style="margin-top: 3%;">
              <img :src="content.imgPath" alt="商品图片" style="width: 80%;"/><br>
              东岳sku <span class="ord-content">{{ content.dySku }}</span><br>
              sku <span class="ord-content">{{ content.sku }}</span><br>
              名称 <span class="ord-content">{{ content.name }}</span><br>
              数量 <span class="ord-content">{{ content.num }}</span><br>
              货架号 <span class="ord-content">{{ content.shelfNo }}</span><br>
              拣货数量
              <el-input-number v-model="content.picked"></el-input-number>
              <el-divider/>
            </div>
          </div>
        </div>
        <div>
          <el-row>
            <el-button @click="donePickup">拣货完成</el-button>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'order-list-mgt-type1-pickup-info',
  data() {
    return {
      ord: {},
      ordDetail: {
        'display': 'block',
      },
      ordDetailOpp: {
        'display': 'none',
      },
      show: {
        'display': 'block',
      },
      hidden: {
        'display': 'none',
      },
    };
  },
  created() {
    this.fetchOrd();
  },
  mounted() {
  },
  methods: {
    fetchOrd() {
      if (this.$route.query.orderNo !== undefined && this.$route.query.orderNo.length > 0) {
        this.$message.info('当前订单号为 ' + this.$route.query.orderNo);
        request({
          url: 'ord/detail',
          method: 'post',
          data: {
            ordNo: this.$route.query.orderNo,
          },
        }).then((res) => {
          console.log(res);
          this.ord = res.data.data;
        });
      } else {
        this.$message.warning('订单号为空');
      }
    },
    showDetail() {
      this.ordDetail = this.hidden;
      this.ordDetailOpp = this.show;
    },
    hiddenDetail() {
      this.ordDetail = this.show;
      this.ordDetailOpp = this.hidden;
    },
    donePickup() {
      console.log(this.ord);
      request({
        url: 'ord/pickup',
        method: 'post',
        data: this.ord.contentList,
      }).then((res) => {
        console.log(res);
      });
    },
  },
  watch: {},
};
</script>

<style scoped>
.ord-title {
  font-size: 18px;
  font-weight: bold;
  margin: 1%;
  text-align: left;
}

.ord-content {
  font-size: 16px;
  font-weight: bold;
  margin: 1%;
}
</style>
