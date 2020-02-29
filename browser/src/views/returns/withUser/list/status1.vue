<template>
  <div class="login-container">
    <div class="app-container">
      <returning-table v-bind:msg="this.msg"></returning-table>
    </div>
  </div>
</template>

<script>
import ReturningTable from '../../../../components/dy/returning/index';

export default {
  name: 'order-list-mgt-type2-status1',
  components: {ReturningTable},
  data() {
    return {
      msg: {
        status: '新建',
        type: 'withUser',
        buttonVisible8: true,
        buttonVisible5: true,
      },
    };
  },
};
</script>
