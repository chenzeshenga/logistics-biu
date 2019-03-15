<template>
  <div class="login-container">
    <el-table style="width: 100%"
              :data="tableData"
              v-loading.body="tableLoading"
              element-loading-text="加载中"
              stripe
              border fit highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <el-col :span="12">
            <el-table
              :data="tableData.row.contentList"
              stripe border>
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
            </el-table>
          </el-col>
        </template>
      </el-table-column>

      <el-table-column
        width="160"
        prop="orderNo"
        label="订单号"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="categoryName"
        label="订单类型"
      ></el-table-column>
      <el-table-column
        width="200"
        prop="channelDesc"
        label="运送渠道"
      ></el-table-column>
      <el-table-column
        width="100"
        prop="statusDesc"
        label="订单状态"
      ></el-table-column>
      <el-table-column
        width="250"
        prop="fromAddressDesc"
        label="寄件地址"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="fromName"
        label="寄件人"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="fromContact"
        label="寄件人联系方式"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="fromZipCode"
        label="寄件人邮编"
      ></el-table-column>
      <el-table-column
        width="250"
        prop="toAddressDesc"
        label="收件地址"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="toName"
        label="收件人"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="toContact"
        label="收件人联系方式"
      ></el-table-column>
      <el-table-column
        width="150"
        prop="toZipCode"
        label="收件人邮编"
      ></el-table-column>
      <el-table-column
        prop="collect"
        width="80"
        label="是否代收商品费用"
      ></el-table-column>
      <el-table-column label="操作" width="250" fixed="right">
        <template slot-scope="scope">
          <el-tooltip content="提交拣货" placement="top">
            <el-button @click="statusUpdate(scope.$index,scope.row)" size="small" type="info" icon="el-icon-check" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="编辑" placement="top">
            <el-button @click="handleUpdate(scope.$index,scope.row)" size="small" type="info" icon="el-icon-edit" circle plain></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button @click="handleDelete(scope.$index,scope.row)" size="small" type="danger" icon="el-icon-delete" circle plain></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="tablePage.current"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="tablePage.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tablePage.total">
    </el-pagination>
  </div>
</template>


<script>
  import request from '@/utils/request'

  export default {
    name: 'order-list-mgt-type1-status1',
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        },
        tableLoading: false,
        tableData: []
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: "ord/list/1/1",
          method: "post",
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        })
      },
      handleSizeChange(val) {
        this.tablePage.size = val;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.tablePage.current = val;
        this.fetchData();
      },
      handleUpdate(index, row) {
        this.$router.push({path: '/new-order/index?ordno=' + row.orderNo})
      },
      handleDelete(index, row) {
        this.$confirm('您确定要永久删除该记录？', '提示', confirm).then(() => {
          request({
            url: "ord/delete/" + row.orderNo,
            method: "get",
          }).then(res => {
            this.fetchData();
            this.$message.success("删除成功");
          })
        }).catch(() => {
          this.$message.info("已取消删除")
        });
      },
      statusUpdate(index, row) {
        this.$confirm('您确定要提交该订单？', '提示', confirm).then(() => {
          console.log(row);
          console.log(index);
          request({
            url: "ord/update/1/" + row.orderNo + "/2",
            method: "get"
          }).then(res => {
            this.fetchData();
            this.$message.success("提交成功");
          })
        }).catch(() => {
          this.$message.info("已取消提交")
        })
      }
    }
  }

</script>
