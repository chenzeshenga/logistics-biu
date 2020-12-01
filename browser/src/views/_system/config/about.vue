<template>
  <div class="login-container">
    <div class="app-container">
      <h3 style="margin-left: 2%">版本历史</h3>
      <el-timeline style="margin-top: 2%">
        <el-timeline-item
            v-for="(activity, index) in activities"
            :key="index"
            :timestamp="activity.timestamp">
          {{ activity.content }}
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
          content: 'v1.43 1. 增加参数配置 2. 增加仓储费字段',
          timestamp: '2020-12-1',
        },
        {
          content: 'v1.42 1. 更新登录超时时间为1天',
          timestamp: '2020-11-4',
        },
        {
          content: 'v1.41 1. 商品统计菜单增加体积重量显示',
          timestamp: '2020-11-1',
        },
        {
          content: 'v1.40 1. 退货单列表增加退货单包裹信息 2. 退货单列表增加删除按钮',
          timestamp: '2020-09-27',
        },
        {
          content: 'v1.39 1. 开放商品出库入库记录',
          timestamp: '2020-09-17',
        },
        {
          content: 'v1.38 1. 退货功能[功能]',
          timestamp: '2020-09-15',
        },
        {
          content: 'v1.37 1. 退货订单列表页完善[功能][WIP]',
          timestamp: '2020-09-10',
        },
        {
          content: 'v1.36 1. 退货单新建字段更改[功能][WIP]',
          timestamp: '2020-09-06',
        },
        {
          content: 'v1.35 1. 订单编辑功能修复[缺陷]',
          timestamp: '2020-09-05',
        },
        {
          content: 'v1.34 1. 订单扫描新增商品sku扫描',
          timestamp: '2020-09-03',
        },
        {
          content: 'v1.33 1. 新建商品时修复无法上传图片',
          timestamp: '2020-09-01',
        },
        {
          content: 'v1.32 1. 完善移动端订单详情页',
          timestamp: '2020-08-31',
        },
        {
          content: 'v1.31 1. 移动端订单详情页',
          timestamp: '2020-08-30',
        },
        {
          content: 'v1.30 1. 订单导出可根据选择的创建人进行导出',
          timestamp: '2020-08-24',
        },
        {
          content: 'v1.29 1. 其他入库单用户可见中间状态',
          timestamp: '2020-08-12',
        },
        {
          content: 'v1.28 1. 虚拟海外仓用户可见中间过程 2. 东岳入库单用户可见中间状态',
          timestamp: '2020-08-12',
        },
        {
          content: 'v1.27 1. 单票单请/海外仓代发 增加中间过程用户可见',
          timestamp: '2020-08-11',
        },
        {
          content: 'v1.26 1. 入库单展示渠道名称',
          timestamp: '2020-08-08',
        },
        {
          content: 'v1.25 1. 更新虚拟海外仓步骤名称',
          timestamp: '2020-07-20',
        },
        {
          content: 'v1.24 1. 订单编辑功能修复 2. 订单拆分包裹功能优化 3. 订单列表包裹展示',
          timestamp: '2020-07-02',
        },
        {
          content: 'v1.23 1. 订单对应多个包裹',
          timestamp: '2020-06-30',
        },
        {
          content: 'v1.22 1. 增加入库单批量导入',
          timestamp: '2020-06-23',
        },
        {
          content: 'v1.21 1. 修复订单长宽高记录',
          timestamp: '2020-06-21',
        },
        {
          content: 'v1.20 1. 库存补录时新增入库记录',
          timestamp: '2020-06-18',
        },
        {
          content: 'v1.19 1. 修复用户密码修改功能',
          timestamp: '2020-06-16',
        },
        {
          content: 'v1.18 1. 修复用户上传文件无法查看的问题',
          timestamp: '2020-06-11',
        },
        {
          content: 'v1.17 1. fix订单页面根据日期无法搜索 2. 订单关联文件新增弹窗列表展示',
          timestamp: '2020-06-08',
        },
        {
          content: 'v1.16 1. 订单列表增加关联文件列',
          timestamp: '2020-06-05',
        },
        {
          content: 'v1.15 1. 出库记录页面分页查询完善 2. 拣货页面优化 3. 记录入库记录',
          timestamp: '2020-06-02',
        },
        {
          content: 'v1.14 1. 修复缺陷-导入订单的sku为东岳sku 2. 修复缺陷-手动新建订单无法选择商品 3. 增强商品库存信息统计页面分页功能 4. 增加商品库存信息导出按钮',
          timestamp: '2020-06-01',
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
