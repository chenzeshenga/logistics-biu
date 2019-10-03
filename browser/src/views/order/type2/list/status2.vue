<template>
    <div class="login-container">
        <div class="app-container">
            <order-type2 v-bind:msg="this.msg"></order-type2>

            <el-dialog
                title="提交发货"
                :visible.sync="dialogVisible"
                width="50%"
            >
                <el-form :model="form">
                    <el-col :span="24">
                        <el-form-item label="订单号">
                            <el-input
                                v-model="form.orderNo"
                                disabled
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24" style="margin-top: 10px">
                        <el-col :span="8">
                            <el-form-item label="长(cm)">
                                <el-input-number
                                    v-model="form.length"
                                    @change="calculateIndex"
                                ></el-input-number>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="宽(cm)">
                                <el-input-number
                                    v-model="form.width"
                                    @change="calculateIndex"
                                ></el-input-number>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="高(cm)">
                                <el-input-number
                                    v-model="form.height"
                                    @change="calculateIndex"
                                ></el-input-number>
                            </el-form-item>
                        </el-col>
                        <label>后台计算总体积为:</label>
                        <span>{{ form.totalVolume }} cm^3</span><br />
                        <label>根据页面输入计算结果如下:</label>
                        <el-form-item>
                            <el-col :span="8">
                                <el-form-item label="总体积(cm^3)">
                                    <el-input-number
                                        v-model="form.totalVolumeFrontEnd"
                                    ></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="三边和(cm)">
                                    <el-input-number
                                        v-model="form.sum"
                                    ></el-input-number>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="体积重(cm^3)">
                                    <el-input-number
                                        v-model="form.totalVolumeWithWeight"
                                    ></el-input-number>
                                </el-form-item>
                            </el-col>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="当前订单总重量(kg)">
                            <el-input-number
                                v-model="form.totalWeight"
                            ></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateVolumeAndWeight"
                        >确 定</el-button
                    >
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
import OrderType2 from '../../../../components/dy/orderTable/Type2/index';

export default {
  name: 'order-list-mgt-type2-status2',
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
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.tableLoading = true;
      request({
        url: 'ord/list/2/2',
        method: 'post',
        data: {
          current: this.tablePage.current,
          size: this.tablePage.size,
        },
      }).then((res) => {
        this.tableData = res.data.page.records;
        this.tableLoading = false;
      });
    },
    handleSizeChange(val) {
      this.tablePage.size = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetchData();
    },
    handleUpdate(index, row) {
      this.$router.push({
        path: '/new-order/index?ordno=' + row.orderNo,
      });
    },
    handleDelete(index, row) {
      this.$confirm('您确定要永久删除该记录？', '提示', confirm)
          .then(() => {
            request({
              url: 'ord/delete/' + row.orderNo,
              method: 'get',
            }).then((res) => {
              this.fetchData();
              this.$message.success('删除成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消删除');
          });
    },
    statusUpdate(index, row) {
      this.form.orderNo = row.orderNo;
      this.dialogVisible = true;
    },
    tableRowClassName({row, rowIndex}) {
      if (Number(row.picked) === Number(row.num)) {
        row.satisfied = true;
        return 'success-row';
      } else {
        row.satisfied = false;
        return 'danger-row';
      }
    },
    updateVolumeAndWeight() {
      this.$confirm('您确定要提交发货该订单？', '提示', confirm).then(
          () => {
            request({
              url: 'ord/update/' + this.form.orderNo,
              method: 'post',
              data: {
                totalVolume: this.form.totalVolume,
                totalWeight: this.form.totalWeight,
                ordFee: this.form.ordFee,
              },
            }).then((res) => {
              request({
                url: 'ord/update/2/' + this.form.orderNo + '/3',
                method: 'get',
              }).then((res) => {
                this.fetchData();
                this.$message.success('提交成功');
              });
            });
          }
      );
    },
    calculateIndex() {
      this.form.totalVolumeFrontEnd =
                this.form.length * this.form.height * this.form.width;
      this.form.sum =
                this.form.length + this.form.height + this.form.width;
      this.form.totalVolumeWithWeight =
                (this.form.length * this.form.height * this.form.width) / 6000;
    },
  },
};
</script>
