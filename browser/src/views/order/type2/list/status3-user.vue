<template>
  <div class="login-container">
    <div class="app-container">
      <order-table v-bind:msg="this.msg"></order-table>
    </div>
  </div>
</template>


<script>
import OrderTable from '../../../../components/dy/orderTable/index';

export default {
  name: 'order-list-mgt-type2-status3-user',
  components: {OrderTable},
  data() {
    return {
      msg: {
        category: '2',
        status: '3',
        statusTo: '4',
        statusAbandon: '5',
        buttonVisible8: true,
        buttonVisible4: true,
      },
    };
  },
};
</script>
