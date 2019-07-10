<template>
  <div class="login-container">
    <div class="app-container">
      <order-type2 v-bind:msg="this.msg"></order-type2>
    </div>
  </div>
</template>


<script>
  import OrderType2 from '../../../../components/dy/orderTable/Type2/index'

  export default {
    name: 'order-list-mgt-type2-status3',
    components: { OrderType2 },
    data() {
      return {
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null,
        },
        tableLoading: false,
        tableData: [],
        daterange: null,
        pickerOptions2: {
          shortcuts: [
            {
              text: '最近一周',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', [start, end]);
              },
            }, {
              text: '最近一个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                picker.$emit('pick', [start, end]);
              },
            }, {
              text: '最近三个月',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                picker.$emit('pick', [start, end]);
              },
            }],
        },
        search: {
          ordno: '',
          creator: '',
          channelCode: '',
        },
        users: [],
        channels: [],
      };
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        this.tableLoading = true;
        request({
          url: 'ord/list/2/3',
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        });
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
        this.$router.push({path: '/new-order/index?ordno=' + row.orderNo});
      },
      handleDelete(index, row) {
        this.$confirm('您确定要永久删除该记录？', '提示', confirm).then(() => {
          request({
            url: 'ord/delete/' + row.orderNo,
            method: 'get',
          }).then(res => {
            this.fetchData();
            this.$message.success('删除成功');
          });
        }).catch(() => {
          this.$message.info('已取消删除');
        });
      },
      statusUpdate(index, row) {
        this.$confirm('您确定要归档该订单？', '提示', confirm).then(() => {
          console.log(row);
          console.log(index);
          request({
            url: 'ord/update/2/' + row.orderNo + '/7',
            method: 'get',
          }).then(res => {
            this.fetchData();
            this.$message.success('归档成功');
          });
        }).catch(() => {
          this.$message.info('已取消提交');
        });
      },
      searchOrd() {
        console.log(this.daterange);
        if (this.daterange == null || this.daterange[0] === 0 || this.daterange[1] === 0) {
          this.$message.warning('请选择日期');
          return;
        }
        this.tableLoading = true;
        request({
          url: 'ord/list/2/3/' + this.daterange[0] + '/' + this.daterange[1],
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size,
          },
        }).then(res => {
          this.tableData = res.data.page.records;
          this.tableLoading = false;
        });
      },
      findWhere(index, row) {
        this.$message.info('查询物流进度');
      },
    },
  };

</script>
