<template>
  <div class="login-container">
    <div class="app-container">
      <el-row style="margin: 1%">
        <el-col span="22" offset="1">
          <el-alert type="info" closable title="点击每行左侧展开按钮可查看具体的退货处理方式，点击确认按钮确认相应的处理方式"></el-alert>
        </el-col>
      </el-row>
      <returning-table v-bind:msg="this.msg"></returning-table>
    </div>
  </div>
</template>

<script>
import ReturningTable from '../../../../components/dy/returning/index';

export default {
  name: 'order-list-mgt-type2-status4',
  components: {ReturningTable},
  data() {
    return {
      msg: {
        status: '待用户确认',
        type: 'withUser',
        buttonVisibleD: true,
      },
    };
  },
};
</script>
