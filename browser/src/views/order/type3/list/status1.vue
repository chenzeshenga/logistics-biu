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
  name: 'order-list-mgt-type3-status1',
  components: {OrderTable},
  data() {
    return {
      msg: {
        category: '3',
        status: '1',
        statusTo: '2',
        statusAbandon: '5',
        buttonVisible1: true,
        buttonVisible2: true,
        buttonVisible3: true,
        buttonVisible4: true,
        buttonVisible5: true,
        buttonVisible12: true,
        buttonVisible13: true,
      },
    };
  },
};
</script>
