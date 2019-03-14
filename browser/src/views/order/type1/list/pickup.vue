<template>
  <div class="login-container">
    <el-col offset="2" :span="18" style="margin-top: 20px">
      <el-input placeholder="请输入订单号" v-model="search" class="input-with-select">
        <el-button slot="append" icon="el-icon-search" @click="searchOrdContent()"></el-button>
      </el-input>
    </el-col>
    <el-col offset="1" :span="22" style="margin-top: 10px">
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
          <template scope="content">
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
        console.log(row);
        console.log(rowIndex);
        console.log("tableRowClassName");
        if (Number(row.pickup) === Number(row.num)) {
          return "success-row";
        } else {
          return "danger-row";
        }
      },
      pickupSubmit() {
        request({
          url: "ord/pickup",
          method: "post",
          data: this.content
        }).then(res => {
          console.log(res);
          // this.content = res.data.data;
        })
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
