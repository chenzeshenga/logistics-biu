<template>
  <div class="login-container">
    <div class="app-container">
      <warehousing-table v-bind:msg="this.msg" />
    </div>
  </div>
</template>

<script>
import WarehousingTable from '../../../../../components/dy/WarehousingTable/index';

export default {
  name: 'warehousing-mgt-dy-list-status1',
  components: {WarehousingTable},
  data() {
    return {
      msg: {
        status: '1',
        category: '1',
        buttonVisible1: true,
        buttonVisible2: true,
        buttonVisible3: true,
        buttonVisible4: true,
        buttonVisible5: true,
        buttonVisible6: true,
        buttonVisible7: true,
      },
    };
  },
};
</script>
