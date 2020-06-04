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
  name: 'order-list-mgt-type3-status4',
  components: {OrderTable},
  data() {
    return {
      msg: {
        category: '3',
        status: '4',
        statusTo: '5',
        statusAbandon: '5',
        buttonVisible6: true,
        buttonVisible2: true,
        buttonVisible3: true,
        buttonVisible4: true,
        buttonVisible5: true,
        buttonVisible12: true,
      },
    };
  },
};

</script>
