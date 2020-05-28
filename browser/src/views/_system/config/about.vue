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
      activities: [
        {
          content: 'todo v1.14 1. 新增在库商品明细页面 2. 在库商品统计页面改成单纯的表格 分页细化',
          timestamp: '2020-05-29',
        },
        {
          content: 'v1.13 1. 订单导出修正为东岳sku 2. 在库商品统计页面改成单纯的表格',
          timestamp: '2020-05-28',
        },
        {
          content: 'v1.12 1. 订单列表的展示改为dySku',
          timestamp: '2020-05-27',
        },
        {
          content: 'v1.11 1. 修复库存补录功能',
          timestamp: '2020-05-26',
        },
        {
          content: 'v1.10 1. 新建订单页面商品sku统一为东岳sku 2. 修复某些批量导入的订单商品数量不规范时影响页面显示的问题',
          timestamp: '2020-05-25',
        },
        {
          content: 'v1.9 1. 入库订单默认查询时间范围调整 2. 入库订单上架时将上一个订单的内容清空',
          timestamp: '2020-05-19',
        },
        {
          content: 'v1.8 1. 订单新增渠道列 2. 修复订单部分状态页不可见',
          timestamp: '2020-05-18',
        },
        {
          content: 'v1.7 1. 入库单列表页修正',
          timestamp: '2020-05-14',
        },
        {
          content: 'v1.6 1. 添加强制更新状态按钮 2. 修复入库单偶尔未保存入库单内容 3. 修复订单展示问题',
          timestamp: '2020-05-11',
        },
        {
          content: 'v1.5 1. 修复新建用户 2. 修复批量提交发货 3. 新建入库单取消箱号限制 4. 订单导出增加用户定义订单号',
          timestamp: '2020-05-11',
        },
        {
          content: 'v1.4 1. 修复订单查询和导出功能',
          timestamp: '2020-05-08',
        },
        {
          content: 'v1.3 1. 修复邮编未保存 2. 导出保留搜索条件',
          timestamp: '2020-05-08',
        },
        {
          content: 'v1.2 1. 修复自定义订单号未保存 2. 订单列表页面新增查询条件-拣货是否完成',
          timestamp: '2020-05-07',
        },
        {
          content: 'v1.1 1. 批量导入订单字段/模板更新 2.修复订单发货按钮 3.出库记录查询页面优化',
          timestamp: '2020-05-06',
        },
        {
          content: 'v1.0 1. 角色-新增是否管理角色字段 => 管理角色- 可查看所有用户数据;非管理角色- 仅可查看自己的数据;1个用户仅可对应1个角色 2.新增出库记录查询',
          timestamp: '2020-05-04',
        },
      ],
    };
  },
};
</script>

<style scoped>

</style>
