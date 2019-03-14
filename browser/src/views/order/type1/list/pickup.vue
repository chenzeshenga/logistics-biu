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
        stripe border
        highlight-current-row
        @row-click="handleCurrentChange"
        :row-class-name="tableRowClassName"
      >
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
        <el-table-column prop="address" label="拣货数量">
          <template scope="content">
            <el-input-number size="small" v-model="content.row.actual_num" placeholder="请输入内容"
                             @change="handleEdit(scope.$index, scope.row)"></el-input-number>
          </template>
        </el-table-column>
      </el-table>
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
        request({})
      },
      handleCurrentChange(row, event, column) {
        console.log(row, event, column, event.currentTarget)
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      tableRowClassName({row, rowIndex}) {
        console.log(row);
        console.log(rowIndex);
      }
    }
  }
</script>

<style>
  .el-table .warning-row {
    background: #e6c393;
  }

  .el-table .success-row {
    background: #94c287;
  }

  .el-table .danger-row {
    background: #f599a1;
  }
</style>
