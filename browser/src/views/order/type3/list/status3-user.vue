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
  name: 'order-list-mgt-type3-status3',
  components: {OrderTable},
  data() {
    return {
      msg: {
        category: '3',
        status: '3',
        statusTo: '4',
        statusAbandon: '5',
        buttonVisible4: true,
        buttonVisible12: true,
      },
    };
  },
};

</script>
