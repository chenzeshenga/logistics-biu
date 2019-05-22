<template>
  <div class="login-container">
    <div class="app-container">
      <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                highlight-current-row @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column type="expand">
          <template slot-scope="tableData">
            <el-table :data="tableData.row.warehousingContentList">
              <el-table-column prop="sku" label="sku/东岳Sku" width="250"></el-table-column>
              <el-table-column prop="name" label="商品名称" width="250"></el-table-column>
              <el-table-column prop="boxSeq" label="箱号" width="150"></el-table-column>
              <el-table-column prop="totalNum" label="数量" width="200"></el-table-column>
              <el-table-column prop="wrapType" label="包装方式" width="250"></el-table-column>
            </el-table>
          </template>
        </el-table-column>
        <el-table-column width="160" prop="warehousingNo" label="入库单号"></el-table-column>
        <el-table-column width="150" prop="target" label="仓库地址"></el-table-column>
        <el-table-column width="150" prop="statusDesc" label="状态"></el-table-column>
        <el-table-column width="200" prop="methodDesc" label="头程方式"></el-table-column>
        <el-table-column width="100" prop="carrierDesc" label="承运人"></el-table-column>
        <el-table-column width="150" prop="trackNo" label="追踪单号"></el-table-column>
        <el-table-column width="170" prop="deliverMethodDesc" label="运输方式"></el-table-column>
        <el-table-column width="250" prop="clearanceTypeDesc" label="报关类型"></el-table-column>
        <el-table-column width="150" prop="taxTypeDesc" label="关税类型"></el-table-column>
        <el-table-column width="150" prop="insurance" label="保险服务"></el-table-column>
        <el-table-column width="150" prop="insuranceNum" label="保险总值(JPY)"></el-table-column>
        <el-table-column width="180" prop="estimatedDate" label="预计到港时间"></el-table-column>
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
    name: 'warehousing-mgt-dy-list-status2',
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
          url: 'warehousing/list/1/2',
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
