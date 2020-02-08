<template>
    <div class="login-container">
        <div class="app-container">
            <order-type1 v-bind:msg="this.msg"></order-type1>
        </div>
    </div>
</template>

<script>
import OrderType1 from '../../../../components/dy/orderTable/index';

export default {
  name: 'order-list-mgt-type1-status3',
  components: {OrderType1},
  data() {
    return {
      msg: {
        category: '1',
        status: '3',
        statusTo: '7',
        statusAbandon: '5',
        buttonVisible7: true,
        buttonVisible8: true,
      },
    };
  },
};
</script>
