<template>
  <div class="login-container">
    <div class="app-container">
      <warehousing-table v-bind:msg="this.msg"></warehousing-table>
    </div>
  </div>
</template>

<script>
import WarehousingTable from '../../../../../components/dy/WarehousingTable/index';

export default {
  name: 'warehousing-mgt-dy-list-status2',
  components: {WarehousingTable},
  data() {
    return {
      msg: {
        status: '2',
        category: '1',
        buttonVisible1: true,
        buttonVisible3: true,
        buttonVisible4: true,
      },
    };
  },
};
</script>
