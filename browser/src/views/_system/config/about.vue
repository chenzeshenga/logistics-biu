<template>
  <div class="login-container">
    <div class="app-container">
      <h3 style="margin-left: 2%">版本历史</h3>
      <el-timeline style="margin-top: 2%">
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :timestamp="activity.timestamp">
          {{activity.content}}
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: 'about',
  data() {
    return {
      activities: [{
        content: '1. 角色-新增是否管理角色字段 => 管理角色- 可查看所有用户数据;非管理角色- 仅可查看自己的数据;1个用户仅可对应1个角色 2.新增出库记录查询',
        timestamp: '2020-05-04',
      },
      {
        content: '1. 批量导入订单字段/模板更新 2.修复订单发货按钮 3.出库记录查询页面优化',
        timestamp: '2020-05-06',
      },
      ],
    };
  },
};
</script>

<style scoped>

</style>
