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
  name: "warehousing-mgt-dy-list-status7",
  components: { WarehousingTable },
  data() {
    return {
      msg: {
        status: "7",
        category: "1",
        buttonVisible1: true,
        buttonVisible6: true,
        buttonVisible7: true
      }
    };
  }
};
</script>
