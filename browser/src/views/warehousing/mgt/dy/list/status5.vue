<template>
  <div class="login-container">
    <div class="app-container">
      <warehousing-table v-bind:msg="this.msg"></warehousing-table>
    </div>
  </div>
</template>

<script>
import WarehousingTable from "../../../../../components/dy/WarehousingTable/index";

export default {
  name: "warehousing-mgt-dy-list-status5",
  components: { WarehousingTable },
  data() {
    return {
      msg: {
        status: "5",
        category: "1",
        buttonVisible1: true,
        buttonVisible4: true,
        buttonVisible5: true,
        buttonVisible6: true,
        buttonVisible7: true,
        buttonVisible10: true,
        buttonVisible13: true
      }
    };
  }
};
</script>
