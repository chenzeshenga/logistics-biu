<template>
  <ejs-barcodegenerator
    id="barcode"
    :invalid="invalidInput"
    ref="barcodeControl"
    :width="width"
    :height="height"
    :type="type"
    :value="value"
    :mode="mode"
  />
</template>

<script>
import Vue from "vue";
import { CheckBoxPlugin } from "@syncfusion/ej2-vue-buttons";
import { BarcodeGeneratorPlugin } from "@syncfusion/ej2-vue-barcode-generator";
import {
  NumericTextBoxPlugin,
  TextBoxPlugin,
  ColorPickerPlugin,
  FormValidator
} from "@syncfusion/ej2-vue-inputs";

Vue.use(NumericTextBoxPlugin);
Vue.use(BarcodeGeneratorPlugin);
Vue.use(NumericTextBoxPlugin);
Vue.use(CheckBoxPlugin);
Vue.use(ColorPickerPlugin);
Vue.use(TextBoxPlugin);
let barCodeInstance;
let canShowError = false;

const customFn = args => {
  if (canShowError) {
    return false;
  }
  return true;
};
const options = {
  rules: {
    password: { minLength: [customFn, "Invalid input"] }
  },
  keyup() {
    if (canShowError) {
      canShowError = false;
    }
  },
  focusout(args) {
    displayText.value = barcode.value = document.getElementById(
      "barcodevaluediv"
    ).value;
    // barcode.dataBind();
  }
};
let formObject;
const textPositiondataSourceValue = [
  { type: "Bottom", text: "Bottom" },
  { type: "Top", text: "Top" }
];

const textAlignmentdataSourceValue = [
  { type: "Center", text: "Center" },
  { type: "Left", text: "Left" },
  { type: "Right", text: "Right" }
];

export default {
  name: "customSyncfusionBarcode",
  props: ['barcode'],
  data: function() {
    return {
      width: "200px",
      height: "150px",
      mode: "SVG",
      type: "Code128",
      value: this.barcode,
      fontcolorvalue: "#000",
      textPositionwidth: "100%",
      ftextPositionfields: { value: "type", text: "text" },
    
      textPositiondataSource: textPositiondataSourceValue,
      textAlignmentdataSource: textAlignmentdataSourceValue,
      invalidInput: arg => {
        canShowError = true;
        formObject.validate();
      },
      created: args => {
        addEvents();
      }
    };
  },
  mounted: function() {
    barCodeInstance = this.$refs.barcodeControl.ej2Instances;
    formObject = new FormValidator("#form-element", options);
  }
};
</script>

<style>
</style>