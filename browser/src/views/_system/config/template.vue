<template>
    <div class="login-container">
        <div class="app-container">
            <el-col :span="4" :offset="16">
                <el-tooltip content="快速查询" placement="top">
                    <el-form>
                        <el-input
                            v-model="regTxt"
                            placeholder="请输入模版id或者文件名"
                        ></el-input>
                    </el-form>
                </el-tooltip>
            </el-col>
            <el-col :span="2" :offset="1">
                <el-tooltip class="item" content="搜索" placement="top">
                    <el-button
                        icon="el-icon-search"
                        circle
                        @click="searchByReg()"
                    ></el-button>
                </el-tooltip>
            </el-col>
            <el-table
                style="width: 100%;margin: 10px"
                :data="tableData"
                v-loading.body="tableLoading"
                element-loading-text="加载中"
                stripe
                highlight-current-row
            >
                <el-table-column prop="uuid" label="id"></el-table-column>
                <el-table-column
                    prop="fileName"
                    label="文件名"
                ></el-table-column>
                <el-table-column
                    prop="createOn"
                    label="创建时间"
                ></el-table-column>
                <el-table-column prop="link" label="文件">
                    <template slot-scope="scope">
                        <el-button
                            circle
                            @click="downloadFile(scope.$index, scope.row)"
                        >
                            <svg-icon icon-class="doc"></svg-icon>
                        </el-button>
                    </template>
                </el-table-column>
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
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
import request from '../../../utils/service';

export default {
  name: 'fileTemplate',
  data() {
    return {
      tableLoading: false,
      tableData: [],
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null,
      },
      regTxt: null,
    };
  },
  created() {
    this.fetch();
  },
  methods: {
    fetch() {
      this.tableLoading = true;
      request({
        url: '/template/list?reg=' + this.regTxt,
        method: 'post',
        data: {
          current: this.tablePage.current,
          size: this.tablePage.size,
        },
      }).then((res) => {
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
    downloadFile(index, row) {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/template/file/' + row.uuid;
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    searchByReg() {
      this.fetch();
    },
    handleSizeChange(val) {
      this.tablePage.size = val;
      this.fetch();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetch();
    },
  },
};
</script>
