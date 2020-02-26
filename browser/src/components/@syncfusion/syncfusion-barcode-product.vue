<template>
  <div style="width: 145px;height: 80px">
    <span>{{name}}</span>
    <ejs-barcodegenerator
      id="barcode4product"
      ref="barcodeControl"
      :width="width"
      :height="height"
      :type="type"
      :value="value"
      :mode="mode"
    />
  </div>
</template>

<script>
import Vue from 'vue';
import {BarcodeGeneratorPlugin} from '@syncfusion/ej2-vue-barcode-generator';

Vue.use(BarcodeGeneratorPlugin);
let barCodeInstance;

export default {
  name: 'syncfusion-barcode-product',
  props: ['barcode'],
  data: function() {
    return {
      mode: 'SVG',
      type: 'Code128',
      width: 145,
      height: 60,
      value: this.barcode.value,
      fontcolorvalue: '#000',
      textPositionwidth: '100%',
      ftextPositionfields: {value: 'type', text: 'text'},
      textPositiondataSource: {type: 'Bottom', text: 'Bottom'},
      textAlignmentdataSource: {type: 'Center', text: 'Center'},
      name: '我我我',
    };
  },
  mounted() {
    barCodeInstance = this.$refs.barcodeControl.ej2Instances;
    if (this.barcode.value) {
      barCodeInstance.displayText.text = this.barcode.value;
      barCodeInstance.margin.top = 2;
      barCodeInstance.margin.bottom = 2;
    }
  },
};
</script>

<style scoped>

</style>
