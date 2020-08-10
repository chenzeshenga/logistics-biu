<template>
  <div>
    <el-form>
      <el-form-item>
        <el-row :gutter="20" style="margin-left: 4%">
          <el-col :span="6">
            <el-tooltip content="订单创建时间,大于开始时间(0:00),小于结束时间23:59" placement="top">
              <el-date-picker
                  v-model="daterange"
                  type="daterange"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  :picker-options="pickerOptions2"
                  value-format="yyyy-MM-dd"
                  style="width: 400px"
              >
              </el-date-picker>
            </el-tooltip>
          </el-col>
          <el-col :span="3">
            <el-tooltip content="订单号" placement="top">
              <el-input
                  v-model="search.ordno"
                  clearable="clearable"
                  placeholder="请输入订单号"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="3">
            <el-tooltip content="用户定义订单号" placement="top">
              <el-input
                  v-model="search.userCustomOrderNo"
                  clearable="clearable"
                  placeholder="请输入用户定义订单号"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="3">
            <el-tooltip content="追踪单号" placement="top">
              <el-input
                  v-model="search.trackNo"
                  clearable="clearable"
                  placeholder="请输入追踪单号"
              ></el-input>
            </el-tooltip>
          </el-col>
          <el-col :span="3">
            <el-tooltip content="创建人" placement="top">
              <el-select
                  filterable
                  clearable
                  v-model="search.creator"
                  placeholder="请选择创建人"
                  value=""
              >
                <el-option
                    v-for="creator in users"
                    :key="creator.uname"
                    :label="creator.nick"
                    :value="creator.uname"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="3">
            <el-tooltip content="相关渠道" placement="top">
              <el-select
                  value=""
                  clearable
                  filterable
                  v-model="search.channelCode"
                  placeholder="对应渠道"
              >
                <el-option
                    v-for="item in channels"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                ></el-option>
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="1">
            <el-form-item label="">
              <el-button
                  icon="el-icon-search"
                  @click="fetchData()"
              ></el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-left: 4%;margin-top: 1%">
          <el-radio-group v-model="search.pickup">
            <el-radio :label='1'>拣货完成</el-radio>
            <el-radio :label="2">未完成</el-radio>
            <el-radio :label="3">无关</el-radio>
          </el-radio-group>
        </el-row>
        <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
          <el-col :span="2">
            <el-button
                type="primary"
                @click="applyTrackNo()"
                v-if="multiSelection"
            >
              批量修改单号
            </el-button>
          </el-col>
          <el-col :span="2">
            <el-button
                type="primary"
                @click="batchStatusUpdate()"
                v-if="multiSelection"
            >
              批量提交
            </el-button>
          </el-col>
          <el-col :span="2">
            <el-button
                type="primary"
                @click="batchForceStatusUpdate()"
                v-if="multiSelection"
            >
              批量强制提交
            </el-button>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" @click="route2NewOrd()"
            >新建订单
            </el-button
            >
          </el-col>
          <el-col :span="2">
            <el-tooltip effect="dark" content="导出当前状态下未筛选情况下所有订单，订单内容仅提供前3项" placement="top">
              <el-button
                  type="primary"
                  @click="exportExcel()"
              >导出excel
              </el-button>
            </el-tooltip>
          </el-col>
          <el-col :span="2">
            <el-tooltip effect="dark" content="导出当前状态所有订单" placement="top">
              <el-button
                  type="primary"
                  @click="exportExcelV2()"
              >导出excel-v2
              </el-button>
            </el-tooltip>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <el-table
        style="width: 100%"
        :data="tableData"
        v-loading.body="tableLoading"
        element-loading-text="加载中"
        stripe
        highlight-current-row
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <el-alert type="info" title="订单内容" :closable="false"/>
          <el-table
              :data="tableData.row.contentList"
              show-summary
              :summary-method="getSummary"
              style="margin-bottom: 0"
          >
            <el-table-column
                prop="dySku"
                label="东岳Sku"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="name"
                label="商品名称"
                width="300"
            ></el-table-column>
            <el-table-column
                prop="price"
                label="商品价格(JPY)"
                width="180"
            ></el-table-column>
            <el-table-column
                prop="num"
                label="商品数量"
                width="180"
            ></el-table-column>
            <el-table-column
                prop="picked"
                label="已拣货"
                width="180"
            ></el-table-column>
          </el-table>
          <el-alert type="info" title="包裹列表" :closable="false" style="margin-top: 1%"/>
          <el-table
              :data="tableData.row.orderPackageList"
          >
            <el-table-column
                prop="uuid"
                label="uuid"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="orderNo"
                label="订单号"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="length"
                label="长(cm)"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="width"
                label="宽(cm)"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="height"
                label="高(cm)"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="totalWeight"
                label="重量(kg)"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="carrierNo"
                label="承运人"
                width="200"
            ></el-table-column>
            <el-table-column
                prop="trackNo"
                label="运单号"
                width="200"
            ></el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column
          width="180"
          prop="orderNo"
          label="订单号"
      ></el-table-column>
      <el-table-column
          width="180"
          prop="userCustomOrderNo"
          label="用户定义订单号"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="categoryName"
          label="订单类型"
      ></el-table-column>
      <el-table-column width="200" label="运送渠道">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>渠道名称: {{ scope.row['channelDesc'] }}</p>
            <p>渠道编码: {{ scope.row['channel'] }}</p>
            <p>
              <el-button
                  type="text"
                  v-on:click="route2ChannelPage(scope.$index, scope.row)"
              >查看详情
              </el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium"
              >{{ scope.row.channelDesc }}
              </el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column
          width="100"
          prop="statusDesc"
          label="订单状态"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="carrierNo"
          label="承运人"
      ></el-table-column>
      <el-table-column
          width="170"
          prop="trackNo"
          label="追踪单号"
      ></el-table-column>
      <el-table-column
          width="250"
          prop="fromAddressDesc"
          label="寄件地址"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="fromName"
          label="寄件人"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="fromContact"
          label="寄件人联系方式"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="fromZipCode"
          label="寄件人邮编"
      ></el-table-column>
      <el-table-column
          width="250"
          prop="toAddressDesc"
          label="收件地址"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="toName"
          label="收件人"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="toContact"
          label="收件人联系方式"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="toZipCode"
          label="收件人邮编"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="collect"
          label="是否代收商品费用"
      ></el-table-column>
      <el-table-column
          width="80"
          prop="collectNum"
          label="总计代收费用"
      ></el-table-column>
      <el-table-column
          width="120"
          prop="files"
          label="关联文件"
      >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>查看关联文件</p>
            <p>
              <el-button type="text" v-on:click="triggerDialog5(scope.$index, scope.row)">查看</el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">
                <svg-icon icon-class="doc"/>
              </el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
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
          prop="creator"
          label="创建人"
      ></el-table-column>
      <el-table-column
          width="150"
          prop="updator"
          label="修改人"
      ></el-table-column>
      <el-table-column label="操作" width="350" fixed="right">
        <template slot-scope="scope">
          <el-tooltip
              content="提交发货"
              placement="top"
              v-if="msgData.buttonVisible6"
              v-perm="'b:order:submit'"
          >
            <el-button
                @click="triggerVolumeAndWeight(scope.$index, scope.row)"
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="提交拣货"
              placement="top"
              v-if="msgData.buttonVisible1"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="申请单号"
              placement="top"
              v-if="msgData.buttonVisible2"
          >
            <el-button
                @click="applyTrackno(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-info"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="快速拣货"
              placement="top"
              v-if="msgData.buttonVisible11"
          >
            <el-button
                @click="
                                route2PickUpPage(scope.$index, scope.row)
                            "
                size="small"
                type="info"
                circle
                plain
            >
              <svg-icon icon-class="go-to-link"/>
            </el-button>
          </el-tooltip>
          <el-tooltip
              content="编辑体积重量"
              placement="top"
              v-if="msgData.buttonVisible10"
          >
            <el-button
                @click="triggerVolumeAndWeightWithOutStatus(scope.$index,scope.row)"
                size="small"
                type="info"
                circle
                plain
            >
              <svg-icon icon-class="et-calculate"/>
            </el-button>
          </el-tooltip>
          <el-tooltip
              content="编辑"
              placement="top"
              v-if="msgData.buttonVisible3"
          >
            <el-button
                @click="handleUpdate(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-edit"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="打印配货单"
              placement="top"
              v-if="msgData.buttonVisible4"
          >
            <el-button
                @click="print(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-printer"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="打印配货单-条形码"
              placement="top"
              v-if="msgData.buttonVisible4"
          >
            <el-button
                @click="printBarcode(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-printer"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="废弃"
              placement="top"
              v-if="msgData.buttonVisible5"
          >
            <el-button
                @click="abandon(scope.$index, scope.row)"
                size="mini"
                type="danger"
                icon="el-icon-remove"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="归档"
              placement="top"
              v-if="msgData.buttonVisible7"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="查询物流进度"
              placement="top"
              v-if="msgData.buttonVisible8"
          >
            <el-button
                @click="findWhere(scope.$index, scope.row)"
                size="mini"
                type="info"
                icon="el-icon-question"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="还原"
              placement="top"
              v-if="msgData.buttonVisible9"
          >
            <el-button
                @click="statusUpdate(scope.$index, scope.row)"
                size="small"
                type="info"
                icon="el-icon-check"
                circle
                plain
            ></el-button>
          </el-tooltip>
          <el-tooltip
              content="上传"
              placement="top"
              v-if="msgData.buttonVisible12"
          >
            <el-button
                @click="uploadOrderFile(scope.$index, scope.row)"
                size="small"
                circle
                plain
            >
              <svg-icon icon-class="upload"/>
            </el-button>
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
    <el-dialog title="申请单号" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form">
        <el-col :span="12">
          <el-form-item label="承运人">
            <el-cascader
                :options="carrier"
                v-model="form.selectedCarrier"
                @change="handleCarrierChange"
                filterable
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="追踪单号">
            <el-input
                v-model="form.trackNo"
                placeholder="请填写对应的单号"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="fillInTrackNo"
                >确 定</el-button
                >
            </span>
    </el-dialog>
    <el-dialog
        title="批量申请单号"
        :visible.sync="dialogVisibleList"
        width="30%"
    >
      <el-col :span="18">
        <el-upload
            :action="actionLink1"
            with-credentials
            multiple
            ref="upload"
            :auto-upload="false"
            :on-success="handleSuccess"
        >
          <el-button slot="trigger" size="small" type="primary"
          >选取文件
          </el-button>
          <el-button
              style="margin-left: 10px;"
              size="small"
              type="success"
              @click="submitUpload"
          >上传
          </el-button>
        </el-upload>
      </el-col>
      <el-col :span="2">
        <el-button type="info" @click="downloadTemplate">下载模板</el-button>
      </el-col>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisibleList = false">取 消</el-button>
            </span>
    </el-dialog>
    <el-dialog title="提交发货" :visible.sync="dialogVisible2" width="50%">
      <el-form :model="form">
        <el-row :span="24">
          <el-form-item label="订单号">
            <el-input v-model="form.orderNo" disabled></el-input>
          </el-form-item>
        </el-row>
        <el-alert type="info" title="每个订单默认1个包裹，如需拆分包裹，请点击按钮" :closable="false"/>
        <el-row :span="24" style="margin-top: 10px">
          <el-col :span="7">
            <el-form-item label="长(cm)">
              <el-input-number
                  v-model="form.length"
                  @change="calculateIndex"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="宽(cm)">
              <el-input-number
                  @change="calculateIndex"
                  v-model="form.width"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="高(cm)">
              <el-input-number
                  v-model="form.height"
                  @change="calculateIndex"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :span="24" style="margin-bottom: 2%">
          <label>根据页面输入计算结果如下(仅供参考):</label>
        </el-row>
        <el-form-item style="margin-top: 2%">
          <el-col :span="8">
            <el-tooltip
                placement="top"
                content="总体积(cm^3)=长*宽*高"
            >
              <el-form-item label="总体积(cm^3)">
                <el-input-number
                    v-model="form.totalVolumeFrontEnd"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip
                content="三边和(cm)=长+宽+高"
                placement="top"
            >
              <el-form-item label="三边和(cm)">
                <el-input-number
                    v-model="form.sum"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip
                content="体积重=长*宽*高/6000"
                placement="top"
            >
              <el-form-item label="体积重(cm^3)">
                <el-input-number
                    v-model="form.totalVolumeWithWeight"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
        </el-form-item>
        <el-row :span="24">
          <el-form-item label="当前订单总重量(kg)">
            <el-input-number
                v-model="form.totalWeight"
            ></el-input-number>
          </el-form-item>
        </el-row>
        <el-row :span="24">
          <el-form-item label="承运人">
            <el-select filterable clearable v-model="form.carrierNo" placeholder="请选择创建人">
              <el-option v-for="ele in carrier" :key="ele.label" :label="ele.label"
                         :value="ele.value"></el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :span="24">
          <el-form-item label="追踪单号">
            <el-input placeholder="请输入或者扫描对应的追踪单号" v-model="form.trackNo"></el-input>
          </el-form-item>
        </el-row>
        <el-row style="margin-top: 2%">
          <el-tooltip placement="top" content="增加该包裹">
            <el-button type="primary" @click="addPackage" style="margin-left: 85%">
              增加
            </el-button>
          </el-tooltip>
        </el-row>
        <h5>当前订单包裹列表</h5>
        <el-table style="margin-top: 2%;width: 100%"
                  :data="tableDataInDialog"
                  stripe
                  highlight-current-row
        >
          <el-table-column prop="orderNo" label="订单号"/>
          <el-table-column prop="length" label="长(cm)"/>
          <el-table-column prop="width" label="宽(cm)"/>
          <el-table-column prop="height" label="高(cm)"/>
          <el-table-column prop="totalWeight" label="包裹重量(kg)"/>
          <el-table-column prop="carrierNo" label="承运人"/>
          <el-table-column prop="trackNo" label="追踪单号"/>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-tooltip
                  content="删除"
                  placement="top"
              >
                <el-button
                    @click="removePackage(scope.$index, scope.row)"
                    size="small"
                    type="danger"
                    icon="el-icon-remove"
                    circle
                    plain
                ></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <span slot="footer" class="dialog-footer">
         <el-button @click="dialogVisible2 = false">取 消</el-button>
         <el-button type="primary" @click="updateOrd">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
        title="编辑订单体积重量"
        :visible.sync="dialogVisible3"
        width="50%"
    >
      <el-form :model="form">
        <el-row :span="24">
          <el-form-item label="订单号">
            <el-input v-model="form.orderNo" disabled></el-input>
          </el-form-item>
        </el-row>
        <el-alert type="info" title="每个订单默认1个包裹，如需拆分包裹，请点击按钮" :closable="false"/>
        <el-row :span="24" style="margin-top: 10px">
          <el-col :span="7">
            <el-form-item label="长(cm)">
              <el-input-number
                  v-model="form.length"
                  @change="calculateIndex"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="宽(cm)">
              <el-input-number
                  @change="calculateIndex"
                  v-model="form.width"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="高(cm)">
              <el-input-number
                  v-model="form.height"
                  @change="calculateIndex"
              ></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :span="24" style="margin-bottom: 2%">
          <label>根据页面输入计算结果如下(仅供参考):</label>
        </el-row>
        <el-form-item style="margin-top: 2%">
          <el-col :span="8">
            <el-tooltip
                placement="top"
                content="总体积(cm^3)=长*宽*高"
            >
              <el-form-item label="总体积(cm^3)">
                <el-input-number
                    v-model="form.totalVolumeFrontEnd"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip
                content="三边和(cm)=长+宽+高"
                placement="top"
            >
              <el-form-item label="三边和(cm)">
                <el-input-number
                    v-model="form.sum"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
          <el-col :span="8">
            <el-tooltip
                content="体积重=长*宽*高/6000"
                placement="top"
            >
              <el-form-item label="体积重(cm^3)">
                <el-input-number
                    v-model="form.totalVolumeWithWeight"
                ></el-input-number>
              </el-form-item>
            </el-tooltip>
          </el-col>
        </el-form-item>
        <el-row :span="24">
          <el-form-item label="当前订单总重量(kg)">
            <el-input-number
                v-model="form.totalWeight"
            ></el-input-number>
          </el-form-item>
        </el-row>
        <el-row :span="24">
          <el-form-item label="承运人">
            <el-select filterable clearable v-model="form.carrierNo" placeholder="请选择创建人">
              <el-option v-for="ele in carrier" :key="ele.label" :label="ele.label"
                         :value="ele.value"></el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row :span="24">
          <el-form-item label="追踪单号">
            <el-input placeholder="请输入或者扫描对应的追踪单号" v-model="form.trackNo"></el-input>
          </el-form-item>
        </el-row>
        <el-row style="margin-top: 2%">
          <el-tooltip placement="top" content="增加该包裹">
            <el-button type="primary" @click="addPackage" style="margin-left: 85%">
              增加
            </el-button>
          </el-tooltip>
        </el-row>
        <h5>当前订单包裹列表</h5>
        <el-table style="margin-top: 2%;width: 100%"
                  :data="tableDataInDialog"
                  stripe
                  highlight-current-row
        >
          <el-table-column prop="orderNo" label="订单号"/>
          <el-table-column prop="length" label="长(cm)"/>
          <el-table-column prop="width" label="宽(cm)"/>
          <el-table-column prop="height" label="高(cm)"/>
          <el-table-column prop="totalWeight" label="包裹重量(kg)"/>
          <el-table-column prop="carrierNo" label="承运人"/>
          <el-table-column prop="trackNo" label="追踪单号"/>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-tooltip
                  content="删除"
                  placement="top"
              >
                <el-button
                    @click="removePackage(scope.$index, scope.row)"
                    size="small"
                    type="danger"
                    icon="el-icon-remove"
                    circle
                    plain
                ></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible3 = false">取 消</el-button>
                <el-button type="primary" @click="updateOrdWithOutStatus"
                >确 定</el-button
                >
            </span>
    </el-dialog>
    <el-dialog title="配货单-条形码" :visible.sync="dialogVisible4Barcode" width="20%">
      <div id="pdfDom">
        <custom-barcode4-order v-bind:barcode="barcodeSetting" :key="timestamp"></custom-barcode4-order>
      </div>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible4Barcode = false">取 消</el-button>
          <el-button type="primary" @click="getPdfWithSetting(barcodeSetting.value,'#barcode',setting)">下 载</el-button>
        </span>
    </el-dialog>
    <el-dialog title="上传外箱标签" :visible.sync="dialogVisible4" width="30%">
      <span>订单号: {{ tmpOrderNo }}</span>
      <el-upload
          :action="actionLink2uploadOrderFile"
          with-credentials
          multiple
          :file-list="fileList"
          ref="upload"
          :limit="3"
      >
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <div slot="tip" class="el-upload__tip">
          只能上传excel/pdf文件
        </div>
      </el-upload>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible4 = false">取 消</el-button>
            </span>
    </el-dialog>
    <el-dialog title="关联文件" :visible.sync="dialogVisible5" width="30%">
      <el-table
          style="width: 100%"
          :data="tableData4File"
          v-loading.body="tableLoading2"
          element-loading-text="加载中"
          stripe
          highlight-current-row
      >
        <el-table-column
            width="180"
            prop="uuid"
            label="uuid"
        ></el-table-column>
        <el-table-column
            width="180"
            prop="fileName"
            label="文件名"
        ></el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template slot-scope="scope">
            <el-tooltip
                content="下载"
                placement="top"
            >
              <el-button
                  @click="handleSystemFile(scope.$index, scope.row)"
                  size="small"
                  type="info"
                  icon="el-icon-check"
                  circle
                  plain
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible5 = false">取 消</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '../../../utils/service';
import CustomBarcode4Order from '../../@syncfusion/order/customBarcode4Order';

export default {
  name: 'orderTable',
  components: {CustomBarcode4Order},
  data() {
    return {
      msgData: {
        category: this.msg.category,
        status: this.msg.status,
        statusTo: this.msg.statusTo,
        statusAbandon: this.msg.statusAbandon,
        buttonVisible1: this.msg.buttonVisible1,
        buttonVisible2: this.msg.buttonVisible2,
        buttonVisible3: this.msg.buttonVisible3,
        buttonVisible4: this.msg.buttonVisible4,
        buttonVisible5: this.msg.buttonVisible5,
        buttonVisible6: this.msg.buttonVisible6,
        buttonVisible7: this.msg.buttonVisible7,
        buttonVisible8: this.msg.buttonVisible8,
        buttonVisible9: this.msg.buttonVisible9,
        buttonVisible10: this.msg.buttonVisible10,
        buttonVisible11: this.msg.buttonVisible11,
        buttonVisible12: this.msg.buttonVisible12,
      },
      timestamp: '',
      tablePage: {
        current: 1,
        pages: null,
        size: null,
        total: null,
      },
      tableLoading: false,
      tableLoading2: false,
      tableData: [],
      daterange: null,
      pickerOptions2: {
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 7
              );
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 30
              );
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(
                  start.getTime() - 3600 * 1000 * 24 * 90
              );
              picker.$emit('pick', [start, end]);
            },
          },
        ],
      },
      dialogVisible: false,
      dialogVisible2: false,
      dialogVisible3: false,
      dialogVisible4: false,
      dialogVisible5: false,
      carrier: [],
      form: {
        orderNo: '',
        status: '',
        selectedCarrier: [],
        carrierNo: '',
        trackNo: '',
        length: 0,
        width: 0,
        height: 0,
        totalVolumeFrontEnd: 0,
        sum: 0,
        totalVolumeWithWeight: 0,
        totalWeight: 0,
        orderPackageList: [],
      },
      ord4TrackNo: [],
      dialogVisibleList: false,
      multiSelection: false,
      search: {
        ordno: '',
        creator: '',
        channelCode: '',
        trackNo: '',
        userCustomOrderNo: '',
        pickup: 3,
      },
      users: [],
      channels: [],
      actionLink2uploadOrderFile: process.env.BASE_API + '/file/order',
      actionLink1: process.env.BASE_API + '/ord/trackno/list',
      dialogVisible4Barcode: false,
      barcodeSetting: {
        width: 200,
        height: 150,
        value: '',
        textSize: 12,
      },
      setting: {
        width: 270,
        length: 200,
      },
      fileList: [],
      tmpOrderNo: '',
      tableData4File: [],
      tableDataInDialog: [],
    };
  },
  props: ['msg'],
  created() {
    this.fetchData();
    this.initTrackno();
    this.initUserList();
    this.initChannel();
  },
  watch: {
    $route() {
      this.onQuickSearch();
    },
  },
  methods: {
    reRouter() {
      this.$router.push({
        path: '/order-list/mgt/type1/status1',
      });
    },
    onQuickSearch() {
      const ordno = this.$route.query.ordno;
      if (ordno !== undefined && ordno.length > 0) {
        this.search.ordno = ordno;
        this.searchOrd();
        this.reRouter();
      }
    },
    fetchData() {
      this.tableLoading = true;
      request({
        url:
            'ord/v2/list/' +
            this.msgData.category +
            '/' +
            this.msgData.status + '?' + this.generateUrlParam(),
        method: 'post',
        data: this.tablePage,
      }).then((res) => {
        this.tableData = res.data.page.records;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
        this.onQuickSearch();
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
        path: '/new-order/new-order?ordno=' + row.orderNo,
      });
    },
    statusUpdate(index, row) {
      this.$confirm('您确定要提交该订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                  'ord/update/' +
                  this.msgData.category +
                  '/' +
                  row.orderNo +
                  '/' +
                  this.msgData.statusTo,
              method: 'get',
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    batchStatusUpdate() {
      if (this.ord4TrackNo.length <= 0) {
        this.$message.warning('请勾选每一行前的勾选框');
        return;
      }
      this.$confirm('您确定要提交这些订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                  'ord/update/' +
                  this.msgData.category +
                  '/' +
                  this.msgData.statusTo,
              method: 'post',
              data: this.ord4TrackNo,
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    batchForceStatusUpdate() {
      this.$confirm('您确定要强制提交这些订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                  'ord/update/force/' +
                  this.msgData.category +
                  '/' +
                  this.msgData.statusTo,
              method: 'post',
              data: this.ord4TrackNo,
            }).then((res) => {
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    abandon(index, row) {
      this.$confirm('您确定要废弃该订单？', '提示', confirm)
          .then(() => {
            request({
              url:
                  'ord/update/' +
                  this.msgData.category +
                  '/' +
                  row.orderNo +
                  '/' +
                  this.msgData.statusAbandon,
              method: 'get',
            }).then((res) => {
              console.log(res);
              this.fetchData();
              this.$message.success('废弃成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消废弃');
          });
    },
    route2NewOrd() {
      this.$router.push({
        path: '/new-order/new-order',
      });
    },
    initTrackno() {
      request({
        url: 'ord/carrier/distinct',
        method: 'get',
      }).then((res) => {
        this.carrier = res.data.data;
      });
    },
    initUserList() {
      request({
        url: '/sys_user/query4Option',
        method: 'post',
        data: {
          current: null,
          size: 'all',
        },
      }).then((res) => {
        this.users = res.data.page.records;
      });
    },
    initChannel() {
      request({
        url: '/channel/list',
        method: 'get',
      }).then((res) => {
        this.channels = res.data.data;
      });
    },
    applyTrackno(index, row) {
      this.form.orderNo = row.orderNo;
      this.form.carrierNo = row.carrierNo;
      this.form.trackNo = row.trackNo;
      const tmpNo = [];
      tmpNo.push('carrier_' + row.carrierNo);
      this.form.selectedCarrier = tmpNo;
      this.dialogVisible = true;
    },
    handleCarrierChange(value) {
      this.form.carrierNo = value[0];
    },
    getOrdNo2() {
      request({
        url: '/trackno/pk/' + this.form.carrierNo,
        method: 'get',
      }).then((res) => {
        this.form.trackNo = res.data.data;
        this.$message.success('获取订单号成功');
      });
    },
    fillInTrackNo() {
      request({
        url: 'ord/trackno',
        method: 'post',
        data: this.form,
      })
          .then(() => {
            this.$message.success('追踪单号申请成功');
            this.dialogVisible = false;
            this.fetchData();
          })
          .catch((err) => {
            console.log(err);
          });
    },
    getSummary(param) {
      const {columns, data} = param;
      const sums = [];
      if (columns.length > 0 && data.length > 0) {
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '总价';
          } else {
            sums[index] = '';
          }
        });
        sums[3] = data[0]['totalPrice'];
      }
      return sums;
    },
    print(index, row) {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/pdf/ord/' + row.orderNo;
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    handleSelectionChange(val) {
      this.multiSelection = val.length > 0;
      this.ord4TrackNo = [];
      val.forEach((ord) => {
        this.ord4TrackNo.push(ord.orderNo);
      });
    },
    applyTrackNo() {
      if (this.ord4TrackNo.length <= 0) {
        this.$message.warning('请勾选每一行前的勾选框');
        return;
      }
      this.dialogVisibleList = true;
    },
    fillInTrackNoList() {
      request({
        url: 'ord/trackno/list',
        method: 'post',
        data: {
          ords: this.ord4TrackNo,
          carrierNo: this.form.carrierNo,
        },
      })
          .then((res) => {
            console.log(res);
            this.$message.success('追踪单号申请成功');
            this.dialogVisibleList = false;
            this.fetchData();
          })
          .catch((err) => {
            console.log(err);
          });
    },
    exportExcel() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/ord/excel/' + this.msgData.category + '/' + this.msgData.status + '?ordno=' +
          this.search.ordno +
          '&creator=' +
          this.search.creator +
          '&channelCode=' +
          this.search.channelCode +
          '&trackNo=' +
          this.search.trackNo +
          '&pickup=' +
          this.search.pickup;
      if (this.daterange) {
        link.href = link.href +
            '&fromDate=' +
            this.daterange[0] +
            '&toDate=' +
            this.daterange[1];
      }
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    exportExcelV2() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/ord/excel/v2/' + this.msgData.category + '/' + this.msgData.status + '?ordno=' +
          this.search.ordno +
          '&channelCode=' +
          this.search.channelCode +
          '&trackNo=' +
          this.search.trackNo +
          '&pickup=' +
          this.search.pickup;
      if (this.daterange) {
        link.href = link.href +
            '&fromDate=' +
            this.daterange[0] +
            '&toDate=' +
            this.daterange[1];
      }
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    route2ChannelPage(index, row) {
      this.$router.push({
        path: '/system/channel?filter=' + row.channel,
      });
    },
    findWhere() {
      this.$message.info('物流查询');
    },
    triggerVolumeAndWeight(index, row) {
      this.dialogVisible2 = true;
      this.form.orderNo = row.orderNo;
      this.form.length = row.length;
      this.form.width = row.width;
      this.form.height = row.height;
      this.form.sum = row.sum;
      this.form.totalWeight = row.totalWeight;
      this.form.totalVolumeFrontEnd = row.totalVolumeFrontEnd;
      this.form.totalVolumeWithWeight = row.totalVolumeWithWeight;
      this.form.carrierNo = row.carrierNo;
      this.form.trackNo = row.trackNo;
      this.tableDataInDialog = row.orderPackageList;
    },
    triggerVolumeAndWeightWithOutStatus(index, row) {
      this.dialogVisible3 = true;
      this.form.orderNo = row.orderNo;
      this.form.length = row.length;
      this.form.width = row.width;
      this.form.height = row.height;
      this.form.sum = row.sum;
      this.form.totalWeight = row.totalWeight;
      this.form.totalVolumeFrontEnd = row.totalVolumeFrontEnd;
      this.form.totalVolumeWithWeight = row.totalVolumeWithWeight;
      this.tableDataInDialog = row.orderPackageList;
    },

    calculateIndex() {
      this.form.totalVolumeFrontEnd =
          this.form.length * this.form.height * this.form.width;
      this.form.totalVolumeFrontEnd = this.form.totalVolumeFrontEnd.toFixed(
          3
      );
      this.form.sum =
          this.form.length + this.form.height + this.form.width;
      this.form.sum = this.form.sum.toFixed(3);
      this.form.totalVolumeWithWeight =
          (this.form.length * this.form.height * this.form.width) / 6000;
      this.form.totalVolumeWithWeight = this.form.totalVolumeWithWeight.toFixed(
          3
      );
    },
    updateOrd() {
      this.form.status = this.msgData.statusTo;
      this.form.orderPackageList = this.tableDataInDialog;
      console.log(this.form);
      request({
        url: '/ord/updateOrd',
        method: 'post',
        data: this.form,
      }).then((resp) => {
        const succ = resp.data['succ'];
        if (succ) {
          this.$message.success(resp.data['msg']);
        } else {
          this.$message.error(resp.data['msg']);
        }
        this.dialogVisible2 = false;
        this.fetchData();
      });
    },
    updateOrdWithOutStatus() {
      delete this.form['statusTo'];
      delete this.form['status'];
      this.form.orderPackageList = this.tableDataInDialog;
      request({
        url: '/ord/update',
        method: 'post',
        data: this.form,
      }).then((resp) => {
        const succ = resp['data']['succ'];
        if (succ) {
          this.$message.success('当前订单已更新');
        } else {
          this.$message.error(resp['msg']);
        }
        this.dialogVisible3 = false;
        this.fetchData();
      });
    },
    route2PickUpPage(index, row) {
      this.$router.push({path: '/order-list/mgt/type1/pickup?orderNo=' + row.orderNo});
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    handleSuccess(response, file, fileList) {
      this.dialogVisibleList = false;
      this.fetchData();
    },
    downloadTemplate() {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/template/file/ORD_TRACKNO_MAPPING';
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    printBarcode(index, row) {
      this.barcodeSetting.value = row.orderNo;
      this.timestamp = new Date().getTime();
      this.dialogVisible4Barcode = true;
    },
    generateUrlParam() {
      let urlParam = 'ordno=' +
          this.search.ordno +
          '&creator=' +
          this.search.creator +
          '&channelCode=' +
          this.search.channelCode +
          '&trackNo=' +
          this.search.trackNo +
          '&pickup=' +
          this.search.pickup;
      if (this.daterange) {
        urlParam = urlParam +
            '&fromDate=' +
            this.daterange[0] +
            '&toDate=' +
            this.daterange[1];
      }
      return urlParam;
    },
    uploadOrderFile(index, row) {
      this.tmpOrderNo = row.orderNo;
      this.actionLink2uploadOrderFile = process.env.BASE_API + '/file/order?orderNo=' + this.tmpOrderNo;
      this.dialogVisible4 = true;
    },
    handleSystemFile(index, row) {
      const link = document.createElement('a');
      link.style.display = 'none';
      link.href = process.env.BASE_API + '/file/' + row.uuid;
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    triggerDialog5(index, row) {
      const postData = {orderNo: row.orderNo};
      request({
        url: '/file/list',
        method: 'post',
        data: postData,
      }).then((ret) => {
        this.tableData4File = ret.data.data;
      });
      this.dialogVisible5 = true;
    },
    addPackage() {
      this.tableDataInDialog.push(this.form);
      this.form = {
        orderNo: this.form.orderNo,
        status: this.form.status,
        selectedCarrier: [],
        carrierNo: '',
        trackNo: '',
        length: 0,
        width: 0,
        height: 0,
        totalVolumeFrontEnd: 0,
        sum: 0,
        totalVolumeWithWeight: 0,
        totalWeight: 0,
      };
    },
    removePackage(index, row) {
      this.tableDataInDialog.splice(index, 1);
    },
  },
};
</script>

<style>
.el-table .success-row {
  background: rgba(103, 194, 58, 0.1);
}

.el-table .danger-row {
  background: rgb(253, 226, 226);
}
</style>
