<template>
  <div class="login-container">
    <div class="app-container">
      <warehousing-table v-bind:msg="this.msg"/>
    </div>
  </div>
</template>

<script>
import WarehousingTable from '../../../../../components/dy/WarehousingTable/index';

export default {
  name: 'warehousing-mgt-other-list-status2',
  components: {WarehousingTable},
  data() {
    return {
      msg: {
        status: '2',
        category: '2',
        buttonVisible1: true,
        buttonVisible4: true,
        buttonVisible5: true,
        buttonVisible6: true,
        buttonVisible7: true,
        buttonVisible10: true,
        buttonVisible12: true,
      },
    };
  },
};
</script>
