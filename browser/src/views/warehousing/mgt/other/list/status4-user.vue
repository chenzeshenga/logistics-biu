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
  name: 'warehousing-mgt-other-list-status4-user',
  components: {WarehousingTable},
  data() {
    return {
      msg: {
        status: '4',
        category: '2',
        buttonVisible1: true,
        buttonVisible4: true,
      },
    };
  },
};
</script>
