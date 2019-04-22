<template>
  <div class="login-container">
    <div class="app-container">
      <el-col :offset="2">
        <el-form :model="form">
          <el-form-item label="承运人">
            <el-cascader :options="carrier" v-model="form.selectedCarrier" @change="handleCarrierChange"
                         filterable></el-cascader>
            <el-button style="margin-left: 700px" size="small" type="primary" @click="newTrackno">新增追踪单号</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col>
        <el-table style="width: 100%" :data="tableData" v-loading.body="tableLoading" element-loading-text="加载中" stripe
                  highlight-current-row>
          <el-table-column prop="id" label="id"></el-table-column>
          <el-table-column prop="carrierDesc" label="承运人"></el-table-column>
          <el-table-column prop="channelName" label="对应渠道"></el-table-column>
          <el-table-column prop="min" label="当前最小订单号"></el-table-column>
          <el-table-column prop="max" label="当前最大订单号"></el-table-column>
          <el-table-column label="操作" width="300" fixed="right">
            <template slot-scope="scope">
              <el-tooltip content="编辑" placement="top">
                <el-button @click="handleUpdate(scope.$index,scope.row)" size="mini" type="info" icon="el-icon-edit"
                           circle
                           plain></el-button>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <el-button @click="dropRecord(scope.$index,scope.row)" size="mini" type="danger" icon="el-icon-error"
                           circle
                           plain></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                       :current-page="tablePage.current"
                       :page-sizes="[10, 20, 30, 40, 50]" :page-size="tablePage.size"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="tablePage.total">
        </el-pagination>
      </el-col>
    </div>
    <el-dialog title="新增追踪单号" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="承运人">
          <el-select filterable v-model="form.carrier" placeholder="承运人">
            <el-option label="佐川急便" value="carrier_2"/>
            <el-option label="黑猫运输" value="carrier_3"/>
            <el-option label="西浓运输" value="carrier_4"/>
            <el-option label="卡车派送" value="carrier_5"/>
          </el-select>
        </el-form-item>
        <el-form-item label="最小追踪单号">
          <el-input-number v-model="form.min"></el-input-number>
        </el-form-item>
        <el-form-item label="最大追踪单号">
          <el-input-number v-model="form.max"></el-input-number>
        </el-form-item>
        <el-form-item label="对应渠道">
          <el-select filterable v-model="form.channelCode" placeholder="对应渠道">
            <el-option v-for="item in channels" :key="item.value" :label="item.label"
                       :value="item.value"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="newTracknoSubmit" v-if="onCreate">确定</el-button>
        <el-button type="primary" @click="updateOnSubmit" v-if="onUpdate">更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import request from '@/utils/service'

  export default {
    name: 'trackno',
    data() {
      return {
        onCreate: true,
        onUpdate: false,
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        },
        tableData: [],
        tableLoading: false,
        carrier: [],
        form: {
          carrier: null,
          selectedCarrier: null,
          min: null,
          max: null,
          selectedNewCarrier: [],
          channel: null
        },
        dialogVisible: false,
        channels: []
      }
    },
    created() {
      this.initTrackno()
      this.initTableData()
    },
    methods: {
      initTrackno() {
        request({
          url: 'ord/carrier/distinct',
          method: 'get'
        }).then(res => {
          this.carrier = res.data.data
        })
        request({
          url: "/channel/list",
          method: "get"
        }).then(res => {
          this.channels = res.data.data;
        })
      },
      handleCarrierChange(value) {
        this.form.carrierNo = value[0]
        this.tableLoading = true
        request({
          url: 'trackno/list/' + this.form.carrierNo,
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records
          this.tableLoading = false
        })
      },
      initTableData() {
        request({
          url: 'trackno/list',
          method: 'post',
          data: {
            current: this.tablePage.current,
            size: this.tablePage.size
          }
        }).then(res => {
          this.tableData = res.data.page.records
          this.tableLoading = false
        })
      },
      handleSizeChange(val) {
        this.tablePage.size = val
        this.initTableData()
      },
      handleCurrentChange(val) {
        this.tablePage.current = val
        this.initTableData()
      },
      newTrackno() {
        this.dialogVisible = true
        this.form = {
          carrier: null,
          min: null,
          max: null,
          channel: null,
        };
      },
      newTracknoSubmit() {
        request({
          url: '/trackno/add',
          method: 'post',
          data: {
            carrier: this.form.carrier,
            min: this.form.min,
            max: this.form.max,
            channelCode: this.form.channelCode,
          }
        }).then(res => {
          console.log(res)
          this.$message.success('追踪单号新建成功')
          this.dialogVisible = false
          this.initTableData()
        })
      },
      handleDialogCarrierChange(value) {
        this.form.carrierNo2 = value[0]
      },
      dropRecord(index, row) {
        this.$confirm('您确定要永久删除该追踪单号？', '提示', confirm).then(() => {
          request({
            url: '/trackno/del/' + row.id,
            method: 'get'
          }).then(res => {
            console.log(res)
            this.$message.success('追踪单号删除成功')
            this.initTableData()
          })
        }).catch(() => {
          this.$message.info('取消删除')
        })
      },
      handleUpdate(index, row) {
        console.log(row);
        this.onUpdate = true
        this.onCreate = false
        this.dialogVisible = true
        this.form.selectedNewCarrier = 'carrier_' + row.carrier
        this.form.min = row.min
        this.form.max = row.max
        this.form.id = row.id
        this.form.channelCode = row.channelCode;
        this.form.carrier = 'carrier_' + row.carrier;
      },
      updateOnSubmit() {
        request({
          url: '/trackno/update',
          method: 'post',
          data: {
            id: this.form.id,
            carrier: this.form.carrier,
            min: this.form.min,
            max: this.form.max,
            channelCode: this.form.channelCode,
          }
        }).then(res => {
          console.log(res)
          this.$message.success('追踪单号更新成功')
          this.dialogVisible = false
          this.initTableData()
        })
      }
    }
  }
</script>

