<template>
  <div class="login-container">
    <!--<h1>新建订单</h1>-->
    <div class="app-container">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item>
          <el-col :span="11">
            <el-form-item label="订单号">
              <el-input v-model="form.orderNo">
                <el-button slot="append" @click="getOrdNo">获取单号</el-button>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="订单类型">
              <el-select v-model="form.category" placeholder="请选择订单类型">
                <el-option label="海外仓代发订单" value="1"/>
                <el-option label="特色小包" value="2"/>
                <el-option label="单票单清" value="3"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="Activity time">
          <el-col :span="11">
            <el-date-picker v-model="form.date1" type="date" placeholder="Pick a date" style="width: 100%;"/>
          </el-col>
          <el-col :span="2" class="line">-</el-col>
          <el-col :span="11">
            <el-time-picker v-model="form.date2" type="fixed-time" placeholder="Pick a time" style="width: 100%;"/>
          </el-col>
        </el-form-item>
        <el-form-item label="Instant delivery">
          <el-switch v-model="form.delivery"/>
        </el-form-item>
        <el-form-item label="Activity type">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="Online activities" name="type"/>
            <el-checkbox label="Promotion activities" name="type"/>
            <el-checkbox label="Offline activities" name="type"/>
            <el-checkbox label="Simple brand exposure" name="type"/>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="Resources">
          <el-radio-group v-model="form.resource">
            <el-radio label="Sponsor"/>
            <el-radio label="Venue"/>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Activity form">
          <el-input v-model="form.desc" type="textarea"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Create</el-button>
          <el-button @click="onCancel">Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Menu1',
    data() {
      return {
        form: {
          orderNo: '',
          category: '',
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        this.$message('submit!')
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      },
      getOrdNo() {
        this.$message('submit!')
      }
    }
  }
</script>

