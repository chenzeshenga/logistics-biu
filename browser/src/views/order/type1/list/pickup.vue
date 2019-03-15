<template>
  <div class="login-container">
    <el-col :offset="2" :span=18 style="margin-top: 20px">
      <el-input placeholder="请输入订单号" v-model="search" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="searchOrdContent()"></el-button>
      </el-input>
    </el-col>
    <el-col :offset="1" :span=22 style="margin-top: 10px">
      <el-table
        :data="content"
        border
        @row-click="handleCurrentChange"
        :row-class-name="tableRowClassName">
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
        <el-table-column label="拣货数量">
          <template slot-scope="content">
            <el-input-number size="small" v-model="content.row.picked" placeholder="请输入内容"
                             @change="handleEdit(content.$index, content.row)"></el-input-number>
          </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-col>
      <el-button @click="pickupSubmit()" type="primary" style="margin-left: 90%;margin-top: 10px">拣货完成</el-button>
    </el-col>
  </div>
</template>

<script>
  import request from '@/utils/request'

  export default {
    name: 'order-list-mgt-type1-pickup',
    data() {
      return {
        search: "",
        content: []
      }
    },
    methods: {
      searchOrdContent() {
        request({
          url: "ord/pickup/" + this.search,
          type: "get"
        }).then(res => {
          console.log(res);
          this.content = res.data.data;
        })
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
          return "success-row";
        } else {
          row.satisfied = false;
          return "danger-row";
        }
      },
      pickupSubmit() {
        let flag = false;
        for (let index in this.content) {
          let subContent = this.content[index];
          console.log(subContent);
          flag = subContent.satisfied;
        }
        if (flag) {
          request({
            url: "ord/pickup",
            method: "post",
            data: this.content
          }).then(res => {
            this.$message.success("拣货完成");
          });
        } else {
          this.$confirm('当前订单有商品未完全拣货', '提示', confirm).then(() => {
            request({
              url: "ord/pickup",
              method: "post",
              data: this.content
            }).then(res => {
              this.$message.success("拣货完成");
            })
          }).catch(() => {
            this.$message.info("请拣货");
          })
        }
      }
    }
  }
</script>

<style>
  .el-table .success-row {
    background: rgba(103, 194, 58, .1);
  }

  .el-table .danger-row {
    background: rgb(253, 226, 226);
  }
</style>
