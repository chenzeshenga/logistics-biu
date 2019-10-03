<template>
    <div class="login-container">
        <div class="app-container">
            <order-type1 v-bind:msg="this.msg"></order-type1>
        </div>
    </div>
</template>

<script>
import OrderType1 from '../../../../components/dy/orderTable/Type1/index';

export default {
  name: 'order-list-mgt-type1-status5',
  components: {OrderType1},
  data() {
    return {
      msg: {
        category: '1',
        status: '5',
        statusTo: '1',
        statusAbandon: '5',
        // todo delete one of below
        buttonVisible5: true,
        buttonVisible9: true,
      },
    };
  },
};
</script>
