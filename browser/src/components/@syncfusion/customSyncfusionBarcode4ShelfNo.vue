<template>
  <ejs-barcodegenerator
      id="barcode"
      ref="barcodeControl"
      :width="width"
      :height="height"
      :type="type"
      :value="value"
      :mode="mode"
      style="margin:5%"
  />
</template>

<script>
  import Vue from 'vue';
  import {BarcodeGeneratorPlugin} from '@syncfusion/ej2-vue-barcode-generator';

  Vue.use(BarcodeGeneratorPlugin);
  let barCodeInstance;

  export default {
    name: 'customSyncfusionBarcode4ShelfNo',
    props: ['barcode'],
    data: function () {
      return {
        width: this.barcode.width,
        height: this.barcode.height,
        mode: 'SVG',
        type: 'Code128',
        value: this.barcode.value,
        fontcolorvalue: '#000',
        textPositionwidth: '100%',
        ftextPositionfields: {value: 'type', text: 'text'},
        textPositiondataSource: {type: 'Bottom', text: 'Bottom'},
        textAlignmentdataSource: {type: 'Center', text: 'Center'},
      };
    },
    mounted: function () {
      barCodeInstance = this.$refs.barcodeControl.ej2Instances;
      if (this.barcode.textSize) {
        barCodeInstance.displayText.size = this.barcode.textSize;
      }
    },
  }
</script>

<style scoped>

</style>
