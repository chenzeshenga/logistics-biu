<template>
  <div class="login-container">
    <div class="app-container">
      <div>
        <v-quagga :onDetected="logIt" :readerSize="readerSize" :readerTypes="['ean_reader']"></v-quagga>
        <input
            type="file"
            @change="handleFileChange($event)"
            id="barCode"
            accept="image/*"
            mutiple="mutiple"
            capture="camera"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import VueQuagga from 'vue-quaggajs';
import Quagga from 'quagga';

Vue.use(VueQuagga);
export default {
  name: 'order-list-mgt-type1-pickup-test',
  data() {
    return {
      readerSize: {
        width: 640,
        height: 480,
      },
      detecteds: [],
      fileList: [],
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {
    logIt(data) {
      console.log('detected', data);
      alert(data);
    },
    decode(file) {
      Quagga.decodeSingle(
          {
            inputStream: {
              size: 800, // 这里指定图片的大小，需要自己测试一下
              singleChannel: false,
            },
            locator: {
              patchSize: 'medium',
              halfSample: false,
            },
            numOfWorkers: 1,
            decoder: {
              readers: [
                {
                  format: 'code_128_reader', // ean_reader 这里指定ean条形码，就是国际13位的条形码   code39    code_128
                  config: {},
                },
              ],
            },
            // readers: ['code_128_reader'],
            locate: true,
            src: URL.createObjectURL(file),
          },
          function(result) {
            // let code = result.codeResult.code
            console.log(result);
            alert(result);
          },
      );
    },
    handleFileChange(evt) {
      console.log('evt', evt);
      const file = evt.target.files[0];
      this.decode(file);
    },

  },
  watch: {},
};
</script>

<style scoped>
</style>
