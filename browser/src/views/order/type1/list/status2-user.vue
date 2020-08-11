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
  name: 'order-list-mgt-type1-status2-user',
  components: {OrderTable},
  data() {
    return {
      msg: {
        category: '1',
        status: '2',
        statusTo: '3',
        statusAbandon: '5',
        buttonVisible4: true,
      },
    };
  },
};
</script>
