<template>
  <div class="login-container">
    <div class="app-container">
      <el-button @click="scanBarcode">确定</el-button>
      <el-button @click="stopBarcodeScan">停止</el-button>
      <div id="test" style="width: 100%;height: 20%"></div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';
import Quagga from 'quagga';

export default {
  name: 'order-list-mgt-type1-pickup-test',
  data() {
    return {
      search: '',
      content: [],
      abnormalReason: '',
      dialogVisible: false,
    };
  },
  created() {
    // this.scanBarcode();
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
        type: 'get',
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
    scanBarcode() {
      if (navigator.mediaDevices && typeof navigator.mediaDevices.getUserMedia === 'function') {
        // safely access `navigator.mediaDevices.getUserMedia`
        this.$message.info('请点击按钮扫描条形码');
        Quagga.init({
          inputStream: {
            name: 'Live',
            type: 'LiveStream',
            target: document.querySelector('#test'), // Or '#yourElement' (optional)
          },
          decoder: {
            readers: ['code_128_reader'],
          },
        }, function(err) {
          if (err) {
            console.log(err);
            return;
          }
          console.log('Initialization finished. Ready to start');
          Quagga.start();
        });
      } else {
        this.$message.error('当前浏览器不支持扫码功能');
      }
    },
    stopBarcodeScan() {
      Quagga.stop();
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
