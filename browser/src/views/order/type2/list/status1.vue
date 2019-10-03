<template>
    <div class="login-container">
        <div class="app-container">
            <order-type2 v-bind:msg="this.msg"></order-type2>
        </div>
    </div>
</template>

<script>
import OrderType2 from '../../../../components/dy/orderTable/Type2/index';

export default {
  name: 'order-list-mgt-type2-status1',
  components: {OrderType2},
  data() {
    return {
      msg: {
        category: '2',
        status: '1',
        statusTo: '2',
        statusAbandon: '5',
        buttonVisible1: true,
        buttonVisible2: true,
        buttonVisible3: true,
        buttonVisible4: true,
        buttonVisible5: true,
      },
    };
  },
};
</script>
