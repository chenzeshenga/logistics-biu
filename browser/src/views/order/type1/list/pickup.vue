<template>
  <div class="login-container">
    <el-col :offset="2" :span="18" style="margin-top: 20px">
      <el-input
          placeholder="请输入订单号"
          v-model="search"
          class="input-with-select"
      >
        <el-button
            slot="append"
            icon="el-icon-search"
            @click="searchOrdContent()"
        ></el-button>
      </el-input>
    </el-col>
    <el-col :offset="1" :span="22" style="margin-top: 10px">
      <el-table
          :data="content"
          border
          @row-click="handleCurrentChange"
          :row-class-name="tableRowClassName"
      >
        <el-table-column prop="dySku" label="东岳Sku" width="200">
        </el-table-column>
        <el-table-column prop="name" label="商品名称">
        </el-table-column>
        <el-table-column prop="price" label="商品价格JPY" width="180">
        </el-table-column>
        <el-table-column prop="num" label="商品数量" width="180">
        </el-table-column>
        <el-table-column prop="shelfNo" label="货架号" width="180">
        </el-table-column>
        <el-table-column label="拣货数量">
          <template slot-scope="content">
            <el-input-number
                size="small"
                v-model="content.row.picked"
                placeholder="请输入内容"
                @change="handleEdit(content.$index, content.row)"
            ></el-input-number>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-col>
      <el-button
          @click="pickupSubmit()"
          type="primary"
          style="margin-left: 90%;margin-top: 10px"
      >拣货完成
      </el-button
      >
    </el-col>
    <el-dialog title="标记异常" :visible.sync="dialogVisible" width="30%">
      <el-input
          v-model="abnormalReason"
          placeholder="请填写未拣货完全的原因"
      ></el-input>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="abnormalUpdate"
                >确 定</el-button
                >
            </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'order-list-mgt-type1-pickup',
  data() {
    return {
      search: '',
      content: [],
      abnormalReason: '',
      dialogVisible: false,
    };
  },
  methods: {
    searchOrdContent() {
      console.log(this.$route.query.orderNo);
      if (this.$route.query.orderNo !== undefined && this.$route.query.orderNo.length > 0) {
        this.search = this.$route.query.orderNo;
      }
      if (this.search === undefined || this.search.length <= 0) {
        this.$message.warning('请填写订单号');
        return;
      }
      request({
        url: 'ord/pickup/' + this.search,
        method: 'get',
      }).then((res) => {
        this.content = res.data.data;
      });
    },
    handleCurrentChange(row, event, column) {
      // console.log(row, event, column, event.currentTarget)
    },
    handleEdit(index, row) {
      // console.log(index, row);
    },
    handleDelete(index, row) {
      // console.log(index, row);
    },
    tableRowClassName({row, rowIndex}) {
      if (Number(row.picked) === Number(row.num)) {
        row.satisfied = true;
        return 'success-row';
      } else {
        row.satisfied = false;
        return 'danger-row';
      }
    },
    pickupSubmit() {
      if (this.search.length <= 0) {
        this.$message.warning('请输入订单号');
        return;
      }
      let flag = false;
      for (const index in this.content) {
        const subContent = this.content[index];
        console.log(subContent);
        flag = subContent.satisfied;
      }
      if (flag) {
        request({
          url: 'ord/pickup',
          method: 'post',
          data: this.content,
        }).then((res) => {
          console.log(res);
          this.$message.success('拣货完成');
          this.search = '';
          this.content = [];
        });
      } else {
        this.$confirm(
            '当前订单有商品未完全拣货，是否提交',
            '提示',
            confirm
        )
            .then(() => {
              request({
                url: 'ord/pickup',
                method: 'post',
                data: this.content,
              }).then((res) => {
                console.log(res);
                this.$message.success('拣货完成');
                this.abnormalReasonFun();
              });
            })
            .catch(() => {
              this.abnormalReasonFun();
            });
      }
    },
    abnormalReasonFun() {
      this.$confirm('订单发货是否标记异常', '提示', confirm).then(() => {
        this.dialogVisible = true;
      });
    },
    abnormalUpdate() {
      request({
        url: 'ord/abnormal',
        method: 'post',
        data: {
          orderNo: this.search,
          abnormalReason: this.abnormalReason,
        },
      }).then((res) => {
        console.log(res);
        this.$message.success('异常标记成功');
        this.dialogVisible = false;
      });
    },
  },
  watch: {
    $route() {
      this.searchOrdContent();
    },
  },
};
</script>

<style>
  .el-table .success-row {
    background: rgba(103, 194, 58, 0.1);
  }

  .el-table .danger-row {
    background: rgb(253, 226, 226);
  }
</style>
