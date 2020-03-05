<template>
  <ejs-barcodegenerator
    id="barcode"
    ref="barcodeControl"
    :width="width"
    :height="height"
    :type="type"
    :value="value"
    :mode="mode"
  />
</template>

<script>
import Vue from 'vue';
import {BarcodeGeneratorPlugin} from '@syncfusion/ej2-vue-barcode-generator';

Vue.use(BarcodeGeneratorPlugin);
let barCodeInstance;

const textPositiondataSourceValue = [
  {type: 'Bottom', text: 'Bottom'},
  {type: 'Top', text: 'Top'},
];

const textAlignmentdataSourceValue = [
  {type: 'Center', text: 'Center'},
  {type: 'Left', text: 'Left'},
  {type: 'Right', text: 'Right'},
];

export default {
  name: 'customBarcode4Order',
  props: ['barcode'],
  data: function() {
    return {
      width: this.barcode.width,
      height: this.barcode.height,
      mode: 'SVG',
      type: 'Code128',
      value: this.barcode.value,
      fontcolorvalue: '#000',
      textPositionwidth: '100%',
      ftextPositionfields: {value: 'type', text: 'text'},
      textPositiondataSource: textPositiondataSourceValue,
      textAlignmentdataSource: textAlignmentdataSourceValue,
      created: (args) => {
        addEvents();
      },
    };
  },
  mounted: function() {
    barCodeInstance = this.$refs.barcodeControl.ej2Instances;
    if (this.barcode.textSize) {
      barCodeInstance.displayText.size = this.barcode.textSize;
    }
  },
};
</script>

<style scoped>

</style>
