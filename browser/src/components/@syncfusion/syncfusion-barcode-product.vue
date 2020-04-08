<template>
  <div style="width: 145px;height: 80px;display: table-cell;vertical-align: middle;horiz-align: center">
    <span
      style="margin-left:15px;text-align: center;width:120px;display: block;font-size:10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">{{name}}</span>
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
      width: 140,
      height: 50,
      value: this.barcode.value,
      fontcolorvalue: '#000',
      textPositionwidth: '80%',
      ftextPositionfields: {value: 'type', text: 'text'},
      textPositiondataSource: {type: 'Bottom', text: 'Bottom'},
      textAlignmentdataSource: {type: 'Center', text: 'Center'},
      name: this.barcode.name,
    };
  },
  mounted() {
    barCodeInstance = this.$refs.barcodeControl.ej2Instances;
    if (this.barcode.value) {
      barCodeInstance.value = this.barcode.value;
      barCodeInstance.displayText.text = this.barcode.value;
      barCodeInstance.margin.top = 2;
      barCodeInstance.margin.bottom = 2;
      barCodeInstance.margin.left = 20;
      barCodeInstance.margin.right = 20;

    }
  },
};
</script>

<style scoped>

</style>
