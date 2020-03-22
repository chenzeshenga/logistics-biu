<template>
  <div class="login-container">
    <div class="app-container">
      <el-row :gutter="20">
        <el-col :span="1">
          <el-tooltip content="刷新" placement="top">
            <el-button size="mini" circle @click="fetch">
              <svg-icon icon-class="refresh"></svg-icon>
            </el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="2">
          <el-button
            style="margin-left: 10px"
            v-if="multiSelected"
            type="primary"
            @click="batchEnable"
          >批量启用
          </el-button>
        </el-col>
        <el-col :span="2">
          <el-button
            style="margin-left: 10px"
            v-if="multiSelected"
            type="primary"
            @click="batchDisable"
          >批量禁用
          </el-button>
        </el-col>
        <el-col :span="4">
          <el-tooltip content="快速查询" placement="top">
            <el-form>
              <el-input
                v-model="regTxt"
                placeholder="系统根据输入的内容进行全文匹配"
              ></el-input>
            </el-form>
          </el-tooltip>
        </el-col>
        <el-col :span="2">
          <el-tooltip class="item" content="搜索" placement="top">
            <el-button
              icon="el-icon-search"
              circle
              @click="searchByReg()"
            ></el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="2" :offset="10">
          <el-button type="primary" @click="triggerDialog"
          >新增渠道
          </el-button>
        </el-col>
      </el-row>
      <el-table
        style="width: 100%;margin-top: 10px"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column
          width="160"
          prop="channelCode"
          label="渠道编码"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="channelName"
          label="渠道名称"
        ></el-table-column>
        <el-table-column
          width="100"
          prop="active"
          label="是否激活"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="adapterDesc"
          label="适用类型"
        ></el-table-column>
        <el-table-column
          width="300"
          prop="ruleDesc"
          label="规则"
        ></el-table-column>
        <el-table-column
          width="200"
          prop="calculateRuleDesc"
          label="计算规则"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="min"
          label="最短运输天数"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="max"
          label="最长运输天数"
        ></el-table-column>
        <el-table-column
          width="200"
          prop="partnerDesc"
          label="合作物流"
        ></el-table-column>
        <el-table-column
          width="300"
          prop="comments"
          label="备注"
        ></el-table-column>
        <el-table-column
          width="170"
          prop="createOn"
          label="创建时间"
        ></el-table-column>
        <el-table-column
          width="170"
          prop="updateOn"
          label="修改时间"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="createBy"
          label="创建人"
        ></el-table-column>
        <el-table-column
          width="150"
          prop="updateBy"
          label="修改人"
        ></el-table-column>
        <el-table-column label="操作" width="300" fixed="right">
          <template slot-scope="scope">
            <el-tooltip content="编辑" placement="top">
              <el-button
                @click="update(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-edit"
                circle
                plain
              ></el-button>
            </el-tooltip>
            <el-tooltip content="快速启用" placement="top">
              <el-button
                @click="handleEnable(scope.$index, scope.row)"
                size="small"
                type="success"
                circle
                plain
              >
                <svg-icon icon-class="enable"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="快速禁用" placement="top">
              <el-button
                @click="handleDisable(scope.$index, scope.row)"
                size="small"
                type="warning"
                circle
                plain
              >
                <svg-icon icon-class="disable"></svg-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button
                @click="handleDelete(scope.$index, scope.row)"
                size="small"
                type="danger"
                icon="el-icon-delete"
                circle
                plain
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        style="margin-left: 65%;margin-top: 10px"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="tablePage.current"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="tablePage.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tablePage.total"
      >
      </el-pagination>
      <el-dialog
        title="渠道修改"
        :visible.sync="dialogVisible"
        width="55%"
      >
        <div class="app-container">
          <el-form
            ref="form"
            :rules="checkRules"
            :model="form"
            label-width="120px"
          >
            <el-form-item label="渠道基本信息">
              <el-col :span="12">
                <el-form-item label="渠道名称">
                  <el-input
                    v-model="form.channelName"
                    placeholder="请输入渠道名称"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="渠道适用">
                  <el-select
                    filterable
                    v-model="form.adapter"
                    clearable
                  >
                    <el-option
                      label="入库单"
                      value="4"
                    ></el-option>
                    <el-option
                      label="单票单请"
                      value="2"
                    ></el-option>
                    <el-option
                      label="虚拟海外仓"
                      value="3"
                    ></el-option>
                    <el-option
                      label="海外仓代发"
                      value="1"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col :span="12">
                <el-form-item label="渠道合作方">
                  <el-select
                    filterable
                    v-model="form.partner"
                  >
                    <el-option
                      label="日本邮政"
                      value="1"
                    ></el-option>
                    <el-option
                      label="佐川急便"
                      value="2"
                    ></el-option>
                    <el-option
                      label="黑猫运输"
                      value="3"
                    ></el-option>
                    <el-option
                      label="西浓运输"
                      value="4"
                    ></el-option>
                    <el-option
                      label="卡车派送"
                      value="5"
                    ></el-option>
                    <el-option
                      label="其他"
                      value="6"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col :span="12">
                <el-form-item label="最小运输天数">
                  <el-input-number
                    v-model="form.min"
                  ></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="最大运输天数">
                  <el-input-number
                    v-model="form.max"
                  ></el-input-number>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item label="费用相关信息">
              <el-col :span="12">
                <el-form-item label="计算规则">
                  <el-select filterable v-model="form.rule">
                    <el-option
                      label="人工输入"
                      value="1"
                    ></el-option>
                    <el-option
                      label="后台计算之后人工确认"
                      value="2"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item label="规则">
              <el-col :span="24">
                <el-checkbox
                  :indeterminate="isIndeterminate"
                  v-model="checkAll"
                  @change="handleCheckAllChange"
                >全选
                </el-checkbox>
                <el-checkbox-group
                  v-model="form.checkedRules2"
                  @change="handleCheckedChange"
                >
                  <el-checkbox
                    v-for="rule in rules"
                    :key="rule.label"
                    :label="rule.value"
                  >{{ rule.label }}
                  </el-checkbox>
                </el-checkbox-group>
              </el-col>
            </el-form-item>
            <el-form-item label="是否激活">
              <el-switch
                v-model="form.active"
                active-color="#13ce66"
                inactive-color="#ff4949"
              >
              </el-switch>
            </el-form-item>
            <el-form-item label="备注">
              <el-col>
                <el-form-item>
                  <el-input
                    v-model="form.comments"
                    placeholder="备注"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-col :offset="22">
                <el-button
                  type="primary"
                  @click="submitForm()"
                  v-if="onCreate"
                >立即创建
                </el-button>
                <el-button
                  type="primary"
                  @click="updateForm()"
                  v-if="onUpdate"
                >立即更新
                </el-button>
              </el-col>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'amazonStore',
  data() {
    return {}
  },
  created() {
  },
  watch: {
    $route() {

    },
  },
  methods: {}
};
</script>

<style scoped>

</style>
