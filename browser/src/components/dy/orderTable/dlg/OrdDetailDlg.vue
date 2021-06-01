<template>
  <el-dialog title="订单详情" :visible.sync="dlgSys.dlg.ordDetailDlg" width="50%">
    <el-container>
      <el-main>
        <el-row>
          <el-tag effect="plain">订单系统信息</el-tag>
        </el-row>
        <el-divider/>
        <el-row>
          <el-col :span="12">
            订单号: <strong>{{ ord.orderNo }}</strong>
          </el-col>
          <el-col :span="12">
            订单所属用户: <strong>{{ ord.creator }}</strong>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            当前状态: <strong>{{ ord.statusDesc }}</strong>
          </el-col>
        </el-row>
        <el-divider/>
        <el-row>
          <el-tag effect="plain">用户输入信息</el-tag>
        </el-row>
        <el-divider/>
        <el-row>
          <el-col :span="12">
            <strong>用户自定义订单号: {{ ord.userCustomOrderNo }}</strong>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            寄件地址:<br>
            寄件人: <strong>{{ ord.fromName }}</strong><br>
            寄件地址: <strong>{{ ord.fromAddressLine1 }} {{ ord.fromAddressLine3 }} {{ ord.fromAddressLine3 }}</strong><br>
            寄件人联系方式: <strong>{{ ord.fromContact }}</strong><br>
            寄件人邮编: <strong>{{ ord.fromZipCode }}</strong><br>
          </el-col>
          <el-col :span="12">
            收件地址:<br>
            收件人: <strong>{{ ord.toName }}</strong><br>
            收件地址: <strong>{{ ord.toAddressLine1 }} {{ ord.toAddressLine3 }} {{ ord.toAddressLine3 }}</strong><br>
            收件人联系方式: <strong>{{ ord.toContact }}</strong><br>
            收件人邮编: <strong>{{ ord.toZipCode }}</strong><br>
          </el-col>
        </el-row>
        <el-divider/>
        <el-row>
          <el-tag effect="plain">操作员输入信息</el-tag>
        </el-row>
      </el-main>
      <el-footer>
        脚注
      </el-footer>
    </el-container>
  </el-dialog>
</template>

<script>
export default {
  name: 'OrdDetailDlg',
  props: ['ord', 'ordDetailDlgSys'],
  data() {
    return {
      currOrd: this.ord,
      dlgSys: this.ordDetailDlgSys,
    };
  },
  created() {
  },
};
</script>

<style scoped>

</style>
