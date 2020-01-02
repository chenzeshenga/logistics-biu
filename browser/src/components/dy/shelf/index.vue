<template>
  <div class="login-container">
    <div class="app-container">
      <el-table
        style="margin-top: 10px"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column width="200" prop="shelfNo" label="货架号"></el-table-column>
        <el-table-column width="200" prop="area" label="货架区域"></el-table-column>
        <el-table-column width="200" prop="rowNo" label="货架行数"></el-table-column>
        <el-table-column width="200" prop="layer" label="货架层数"></el-table-column>
        <el-table-column width="200" prop="enable" label="是否启用"></el-table-column>
      </el-table>
      <el-pagination
        style="margin-left: 65%;margin-top: 10px"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tablePage.current"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="tablePage.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tablePage.total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import request from "../../../utils/service";

export default {
  name: "shelfTable",
  data() {
    return {
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null
      },
      tableLoading: false,
      tableData: [],
      regTxt: "",
      multiSelected: false,
      shelfNoList: []
    };
  },
  props: ["msg"],
  created() {
    this.fetch();
  },
  watch: {
    $route() {
      this.fetch();
    }
  },
  methods: {
    fetch() {
      const filter = this.$route.query.filter;
      if (filter !== undefined && filter.length > 0) {
        this.regTxt = filter;
      }
      this.tableLoading = true;
      request({
        url: "/shelf/list?reg=" + this.regTxt,
        method: "post",
        data: this.tablePage
      }).then(res => {
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
    handleSizeChange(val) {
      this.tablePage.size = val;
      this.fetch();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetch();
    },
    handleSelectionChange(val) {
      this.multiSelected = val.length > 1;
      if (this.multiSelected) {
        this.shelfNoList = [];
        for (const index in val) {
          this.shelfNoList.push(val[index]["shelfNo"]);
        }
      }
    }
  }
};
</script>

<style>
</style>