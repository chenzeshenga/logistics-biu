<template>
  <div class="login-container">
    <div class="app-container">
      <div>
        <input type="file" accept="image/*;capture=camera" id="fileCapture" capture="camera"
               v-on:change="uploadFile($event)"/>
        <el-row>
          <img id="preview" src="" alt="预览" style="width: 100%"/>
        </el-row>
        <el-row>
          <el-button type="success" @click="scanBarcode">识别条形码</el-button>
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
      alert('未完成功能');
      console.log(this.file);
      request({
        url: 'ord/barcode/scan',
        method: 'post',
        contentType: 'multipart/form-data',
        data: {
          'file': this.file,
        },
      }).then((res) => {
        alert(res);
      });
    },
  },
  watch: {},
};
</script>

<style scoped>
</style>
