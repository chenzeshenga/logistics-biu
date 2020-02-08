<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8"> 入库单号: {{ warehousing.warehousingNo }} </el-col>
        <el-col :span="8"> 发货地址: {{ warehousing.fromAddress }} </el-col>
        <el-col :span="8"> 收货地址: {{ warehousing.target }} </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8"> 头程类型: {{ warehousing.method }} </el-col>
        <el-col :span="8"> 头程承运: {{ warehousing.carrier }} </el-col>
        <el-col :span="8"> 国内追踪单号: {{ warehousing.trackNo }} </el-col>
      </el-row>
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8">日本承运人: {{ warehousing.carrierInJapan }}</el-col>
        <el-col :span="8"
          >日本追踪单号: {{ warehousing.trackNoInJapan }}</el-col
        >
        <el-col :span="8">运输方式: {{ warehousing.deliverMethod }}</el-col>
      </el-row>
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8">清关方式: {{ warehousing.clearanceType }}</el-col>
        <el-col :span="8">关税类型: {{ warehousing.taxType }}</el-col>
        <el-col :span="8">是否保险: {{ warehousing.insurance }}</el-col>
      </el-row>
      <el-row :gutter="20" style="margin-left:4%;margin-top:2%">
        <el-col :span="8">保险金额: {{ warehousing.insuranceNum }}</el-col>
        <el-col :span="8">预计到港时间: {{ warehousing.estimatedDate }}</el-col>
      </el-row>
      <el-row style="margin-left:4%;margin-top:2%">
        <div>入库内容</div>
        <div
          v-for="content in warehousing.warehousingContentList"
          v-bind:key="content.uuid"
          style="margin-top:2%;margin-left:4%"
        >
          箱号: {{ content.boxSeq }} sku: {{ content.sku }} 数量:
          {{ content.actual }} 名称: {{ content.name }}
        </div>
      </el-row>
      <el-row style="margin-left:4%;margin-top:2%">
        <el-col :span="4" style="margin-left:4%;">
          <div class="sub-title">sku</div>
          <el-select
            v-model="currContent.sku"
            placeholder="请从已审核产品中选择"
            @change="handleValueChange"
          >
            <el-option
              v-for="item in products"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-col>
        <el-col :span="4">
          <div class="sub-title">名称</div>
          <el-input
            v-model="currContent.name"
            placeholder="商品名称"
            :disabled="true"
          />
        </el-col>
        <el-col :span="4">
          <div class="sub-title">上架数量</div>
          <el-input-number v-model="currContent.num" />
        </el-col>
        <el-col :span="4">
          <div class="sub-title">货架编号</div>
          <el-select v-model="currContent.shelfNo" placeholder="请选择货架">
            <el-option
              v-for="item in shelves"
              :key="item.shelfNo"
              :label="item.shelfNo"
              :value="item.shelfNo"
            >
              <span
                >货架号 {{ item.shelfNo }} 货架区域 {{ item.area }} 货架行数
                {{ item.layer }} 货架层数 {{ item.rowNo }}</span
              >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="4" style="margin-top:1.3%">
          <el-button type="primary" @click="add2Cart">上架</el-button>
        </el-col>
        <el-table
          :data="warehousingContentList"
          stripe
          highlight-current-row
          style="margin-left:4%;width: 90%"
        >
          <el-table-column prop="sku" label="sku" />
          <el-table-column prop="name" label="名称" />
          <el-table-column prop="num" label="数量" />
          <el-table-column prop="shelfNo" label="货架" />
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-button
        style="margin-top:4%;margin-left:75%"
        type="primary"
        @click="upshelf2backend"
      >
        上架完成</el-button
      >
    </div>
  </div>
</template>

<script>
import request from '../../utils/service';

export default {
  name: 'upshelf',
  data() {
    return {
      warehousing: {},
      products: [],
      currContent: {},
      warehousingContentList: [],
      shelves: [],
      currShelvesMapping: {},
    };
  },
  inject: ['reload'],
  created() {
    this.initData();
    this.fetchShelves();
  },
  watch: {
    $route() {
      this.initData();
    },
  },
  methods: {
    initData() {
      const warehousingNo = this.$route.query.warehousingNo;
      if (warehousingNo !== undefined && warehousingNo.length > 0) {
        request({
          url:
            '/warehousing/fetchByWarehousingNo?warehousingNo=' + warehousingNo,
          method: 'get',
        }).then((ret) => {
          const retWarehousing = ret.data.warehousing;
          this.warehousing = {
            warehousingNo: warehousingNo,
            fromAddress: retWarehousing.fromAddress,
            target: retWarehousing.target,
            method: retWarehousing.method,
            carrier: retWarehousing.carrier,
            trackNo: retWarehousing.trackNo,
            carrierInJapan: retWarehousing.carrierInJapan,
            trackNoInJapan: retWarehousing.trackNoInJapan,
            deliverMethod: retWarehousing.deliverMethod,
            clearanceType: retWarehousing.clearanceType,
            taxType: retWarehousing.taxType,
            insurance: retWarehousing.insurance,
            insuranceNum: retWarehousing.insuranceNum,
            estimatedDate: retWarehousing.estimatedDate,
            warehousingContentList: retWarehousing.warehousingContentList,
          };
          const warehousingContentList = retWarehousing.warehousingContentList;
          for (const content of warehousingContentList) {
            const product = {
              value: content.sku,
              label: content.name,
            };
            this.products.push(product);
          }
        });
      }
    },
    handleValueChange(val) {
      for (const product of this.products) {
        const sku = product.value;
        if (val === sku) {
          this.currContent.name = product.label;
          break;
        }
      }
    },
    add2Cart() {
      const key = this.currContent.sku + '-' + this.currContent.shelfNo;
      if (this.currShelvesMapping.hasOwnProperty(key)) {
        const value = this.currShelvesMapping[key];
        this.$message.info('相同sku与相同货架号的上架内容将合并');
        value['num'] = this.currContent.num + value['num'];
      } else {
        this.currShelvesMapping[key] = this.currContent;
      }
      this.warehousingContentList = [];
      for (const k in this.currShelvesMapping) {
        this.warehousingContentList.push(this.currShelvesMapping[k]);
      }
      this.currContent = {};
    },
    fetchShelves() {
      request({
        url: '/shelf/list/enable',
        method: 'get',
      }).then((res) => {
        this.shelves = res.data.data;
      });
    },
    handleDelete(index, row) {
      this.warehousingContentList.splice(index, 1);
    },
    upshelf2backend() {
      request({
        url:
          '/product/shelf/add?warehousingNo=' + this.warehousing.warehousingNo,
        method: 'post',
        data: this.warehousingContentList,
      }).then((ret) => {
        this.$router.push({path: '/warehousing/mgt/other/status4'});
      });
    },
  },
};
</script>

<style scoped>
.sub-title {
  text-align: center;
  margin-bottom: 2%;
}

.el-input {
  padding-left: 2%;
  padding-right: 2%;
}
</style>
