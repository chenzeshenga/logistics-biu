<template>
  <div class="login-container">
    <div class="app-container">
      <div style="margin-top: 5%;margin-left: 5%">
        <input type="file" accept="image/*;capture=camera" id="fileCapture" capture="camera"
               v-on:change="uploadFile($event)"/>
        <el-row style="margin-top: 2%">
          <img id="preview" src="" alt="预览" style="width: 100%"/>
        </el-row>
        <el-row style="margin-top: 2%">
          <el-button type="success" @click="scanBarcode">识别条形码</el-button>
        </el-row>
        <el-row style="margin-top: 2%">
          <el-tooltip content="查看详情" placement="top">
            <el-button @click="router2pickupPage">{{ ordno }}</el-button>
          </el-tooltip>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import request from '@/utils/service';

export default {
  name: 'order-list-mgt-type1-pickup-scan',
  data() {
    return {
      file: null,
      ordno: '订单号',
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {
    uploadFile($event) {
      const file = document.getElementById('fileCapture');
      const fileObj = file.files[0];
      if (fileObj) {
        this.file = fileObj;
        const windowURL = window.URL || window.webkitURL;
        const img = document.getElementById('preview');
        const dataURl = windowURL.createObjectURL(fileObj);
        img.setAttribute('src', dataURl);
        this.scanBarcode();
      }
    },
    scanBarcode() {
      const data = new FormData();
      data.append('file', this.file);
      request({
        url: 'ord/barcode/scan',
        method: 'post',
        data: data,
      }).then((res) => {
        this.$message.info('订单号为 ' + res.data.msg);
        this.ordno = res.data.msg;
      });
    },
    router2pickupPage() {
      this.$router.push({path: '/order-list/mgt/type1/pickup-info?orderNo=' + this.ordno});
    },
  },
  watch: {},
};
</script>

<style scoped>
</style>
