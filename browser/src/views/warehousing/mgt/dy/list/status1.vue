<template>
  <div class="login-container">
    <div class="app-container">
      <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                highlight-current-row @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="tableData">
            <el-table :data="tableData.row.contentList" show-summary :summary-method="getSummary"
                      style="margin-bottom: 0">
              <el-table-column prop="sku" label="sku/东岳Sku" width="200"></el-table-column>
              <el-table-column prop="name" label="商品名称" width="300"></el-table-column>
              <el-table-column prop="price" label="商品价格(JPY)" width="180"></el-table-column>
              <el-table-column prop="num" label="商品数量" width="180"></el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column width="160" prop="orderNo" label="订单号"></el-table-column>
        <el-table-column width="150" prop="categoryName" label="订单类型"></el-table-column>
        <el-table-column width="200" prop="channelDesc" label="运送渠道"></el-table-column>
        <el-table-column width="100" prop="statusDesc" label="订单状态"></el-table-column>
        <el-table-column width="150" prop="carrierName" label="承运人"></el-table-column>
        <el-table-column width="170" prop="trackNo" label="追踪单号"></el-table-column>
        <el-table-column width="250" prop="fromAddressDesc" label="寄件地址"></el-table-column>
        <el-table-column width="150" prop="fromName" label="寄件人"></el-table-column>
        <el-table-column width="150" prop="fromContact" label="寄件人联系方式"></el-table-column>
        <el-table-column width="150" prop="fromZipCode" label="寄件人邮编"></el-table-column>
        <el-table-column width="250" prop="toAddressDesc" label="收件地址"></el-table-column>
        <el-table-column width="150" prop="toName" label="收件人"></el-table-column>
        <el-table-column width="150" prop="toContact" label="收件人联系方式"></el-table-column>
        <el-table-column width="150" prop="toZipCode" label="收件人邮编"></el-table-column>
        <el-table-column width="80" prop="collect" label="是否代收商品费用"></el-table-column>
        <el-table-column width="170" prop="createOn" label="创建时间"></el-table-column>
        <el-table-column width="170" prop="updateOn" label="修改时间"></el-table-column>
        <el-table-column width="150" prop="creator" label="创建人"></el-table-column>
        <el-table-column width="150" prop="updator" label="修改人"></el-table-column>
        <el-table-column label="操作" width="300" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="提交拣货" placement="top">
              <el-button @click="statusUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-check"
                         circle
                         plain></el-button>
            </el-tooltip>
            <el-tooltip content="申请单号" placement="top">
              <el-button @click="applyTrackno(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-info"
                         circle plain></el-button>
            </el-tooltip>
            <el-tooltip content="编辑" placement="top">
              <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-edit"
                         circle plain></el-button>
            </el-tooltip>
            <el-tooltip content="打印配货单" placement="top">
              <el-button @click="print(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-printer" circle
                         plain></el-button>
            </el-tooltip>
            <el-tooltip content="废弃" placement="top">
              <el-button @click="abandon(scope.$index,scope.row)" size="mini" type="danger" icon="el-icon-remove" circle
                         plain></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-left: 65%;margin-top: 10px" background @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="tablePage.current"
                     :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="tablePage.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import request from '../../../../../utils/service';

  export default {
    name: 'warehousing-mgt-dy-list-status',
    data() {
      return {
        tablePage: {
          current: 1,
          pages: null,
          size: null,
          total: null,
        },
        tableLoading: false,
        tableData: [],
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: 'warehousing/list/1/1',
          method: 'post',
          data: this.tablePage,
        }).then(res => {
          console.log(res);
          this.tableData = res.data.page.records;
          this.tablePage.current = res.data.page.current;
          this.tablePage.pages = res.data.page.pages;
          this.tablePage.size = res.data.page.size;
          this.tablePage.total = res.data.page.total;
          this.tableLoading = false;
        });
      }
    }
  };
</script>

<style scoped>

</style>
