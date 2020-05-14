<template>
  <div>
    <el-form>
      <el-form-item>
        <el-row :gutter="20" style="margin-left: 4%">
          <el-col :span="6">
            <el-tooltip content="订单创建时间" placement="top">
              <el-date-picker
                v-model="daterange"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2"
                value-format="yyyy-MM-dd"
                style="width: 400px"
                @change="reGenSearchData"
              ></el-date-picker>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="入库单号" placement="top">
              <el-input v-model="search.warehousingNo" clearable placeholder="请输入入库单号" />
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="创建人" placement="top">
              <el-select filterable clearable v-model="search.creator" placeholder="请选择创建人">
                <el-option
                  v-for="creator in users"
                  :key="creator.uname"
                  :label="creator.nick"
                  :value="creator.uname"
                />
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="4">
            <el-tooltip content="相关渠道" placement="top">
              <el-select clearable filterable v-model="search.channelCode" placeholder="对应渠道">
                <el-option
                  v-for="item in channels"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                />
              </el-select>
            </el-tooltip>
          </el-col>
          <el-col :span="1">
            <el-form-item label>
              <el-button icon="el-icon-search" @click="fetchData()" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
          <el-col :span="2">
            <el-button type="primary" @click="route2NewWarehousing()">新建入库单</el-button>
          </el-col>
          <el-col :span="2">
            <el-button
              type="primary"
              @click="exportExcel()"
              icon="iconfont icon-jichukongjiantubiao-gonggongxuanzekuang"
            >导出excel</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <el-alert
      title="您可点击获取报关单按钮使用系统生成的报关单进行报关，也可使用您自己制作的报关单报关。若您使用您自己制作的报关单，请使用上传报关单按钮进行报关单上传，方便我们后期追踪"
      type="info"
      show-icon
      center
      style="margin-bottom: 2%"
    />
    <el-table
      style="width: 100%"
      :data="tableData"
      v-loading.body="tableLoading"
      element-loading-text="加载中"
      stripe
      highlight-current-row
    >
      <el-table-column type="selection" width="55" />
      <el-table-column type="expand">
        <template slot-scope="tableData">
          <el-table :data="tableData.row.warehousingContentList">
            <el-table-column prop="sku" label="sku/东岳Sku" width="250" />
            <el-table-column prop="name" label="商品名称" width="250" />
            <el-table-column prop="boxSeq" label="箱号" width="150" />
            <el-table-column prop="totalNum" label="数量" width="200" />
            <el-table-column prop="wrapType" label="包装方式" width="250" />
            <el-table-column prop="actual" label="实际收货数量" width="250" />
          </el-table>
        </template>
      </el-table-column>
      <el-table-column width="200" prop="warehousingNo" label="入库单号" />
      <el-table-column width="150" prop="fromAddress" label="发货地址" />
      <el-table-column width="150" prop="target" label="仓库地址" />
      <el-table-column width="100" prop="statusDesc" label="状态" />
      <el-table-column width="150" prop="method" label="头程方式" />
      <el-table-column width="150" label="头程渠道">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top" v-if="scope.row.channelFlag">
            <p>渠道名称: {{ scope.row["channelDesc"] }}</p>
            <p>渠道编码: {{ scope.row["channel"] }}</p>
            <p>
              <el-button type="text" v-on:click="route2ChannelPage(scope.$index, scope.row)">查看详情</el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row["channelDesc"] }}</el-tag>
            </div>
          </el-popover>
          <el-popover trigger="hover" placement="top" v-if="!scope.row.channelFlag">
            <p>未选择头程渠道</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">未选择头程渠道</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column width="100" prop="carrier" label="承运人" />
      <el-table-column width="150" prop="trackNo" label="追踪单号" />
      <el-table-column width="170" prop="deliverMethod" label="运输方式" />
      <el-table-column width="200" prop="clearanceType" label="报关类型" />
      <el-table-column width="150" prop="taxType" label="关税类型" />
      <el-table-column width="150" prop="insurance" label="保险服务" />
      <el-table-column width="150" prop="insuranceNum" label="保险总值(JPY)" />
      <el-table-column width="180" prop="estimatedDate" label="预计到港时间" />
      <el-table-column width="150" label="系统生成报关单下载">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>点击按钮下载文件</p>
            <p>
              <el-button type="text" v-on:click="handleSystemFile(scope.$index, scope.row)">下载</el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">
                <svg-icon icon-class="doc" />
              </el-tag>
            </div>
          </el-popover>
        </template>
        <el-button
          size="mini"
          circle
          plain
          @click="handleSystemFile(scope.$index, scope.row)"
          icon="el-icon-download"
        >下载</el-button>
      </el-table-column>
      <el-table-column width="150" label="用户上传报关单下载">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>点击按钮下载文件</p>
            <p>
              <el-button
                type="text"
                v-on:click="handleUserWarehousingFile(scope.$index, scope.row)"
              >下载</el-button>
            </p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">
                <svg-icon icon-class="doc" />
              </el-tag>
            </div>
          </el-popover>
        </template>
        <el-button
          size="mini"
          circle
          plain
          @click="handleSystemFile(scope.$index, scope.row)"
          icon="el-icon-download"
        >下载</el-button>
      </el-table-column>
      <el-table-column width="170" prop="createOn" label="创建时间" />
      <el-table-column width="170" prop="updateOn" label="修改时间" />
      <el-table-column width="150" prop="creator" label="创建人" />
      <el-table-column width="150" prop="updator" label="修改人" />
      <el-table-column label="操作" width="400" fixed="right">
        <template slot-scope="scope">
          <el-tooltip content="打印入单标签" placement="top" v-if="msgData.buttonVisible1">
            <el-button
              @click="printWarehousingBarcode(scope.$index, scope.row, 2)"
              size="mini"
              type="info"
              icon="el-icon-download"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="发往日本东岳" placement="top" v-if="msgData.buttonVisible11">
            <el-button
              @click="statusUpdate(scope.$index, scope.row, 2)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="发往东岳国内前置海外仓" placement="top" v-if="msgData.buttonVisible2">
            <el-button
              @click="statusUpdate(scope.$index, scope.row, 2)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="东岳国内前置海外仓已收货，记录整体体积重量" placement="top" v-if="msgData.buttonVisible8">
            <el-button
              @click="toggleHeadCheckDialog(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="头程校验完成，发往日本" placement="top" v-if="msgData.buttonVisible9">
            <el-button
              @click="confirmTrackNo(scope.$index,scope.row)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="日本仓库已收货，发往入库清点" placement="top" v-if="msgData.buttonVisibleA">
            <el-button
              @click="statusUpdate(scope.$index, scope.row, 5)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="入库清点完成，发往仓库上架" placement="top" v-if="msgData.buttonVisibleB">
            <el-button
              @click="statusUpdate(scope.$index, scope.row, 6)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="上架完成，开始计仓储费" placement="top" v-if="msgData.buttonVisibleC">
            <el-button
              @click="statusUpdate(scope.$index, scope.row, 7)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="预申请单号" placement="top" v-if="msgData.buttonVisible2">
            <el-button
              @click="applyTrackno(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-info"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="获取报关单" placement="top" v-if="msgData.buttonVisible3">
            <el-button
              @click="handlePrint(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-printer"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="上传报关单" placement="top" v-if="msgData.buttonVisible4">
            <el-button
              @click="handleUploadFile(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-upload"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="预审入库单" placement="top" v-if="msgData.buttonVisible12">
            <el-button
              @click="handleDialogVisible5(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="货物清点" placement="top" v-if="msgData.buttonVisible13">
            <el-button
              @click="handleDialogVisible6(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="货物上架" placement="top" v-if="msgData.buttonVisible14">
            <el-button
              @click="handleDialogVisible7(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-check"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="编辑" placement="top" v-if="msgData.buttonVisible5">
            <el-button
              @click="handleUpdate(scope.$index, scope.row)"
              size="mini"
              type="info"
              icon="el-icon-edit"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="删除" placement="top" v-if="msgData.buttonVisible6">
            <el-button
              @click="handleDelete(scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-remove"
              circle
              plain
            />
          </el-tooltip>
          <el-tooltip content="废弃" placement="top" v-if="msgData.buttonVisible7">
            <el-button
              @click="hold(scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-remove-outline"
              circle
              plain
            />
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
    />
    <el-dialog title="申请单号" :visible.sync="dialogVisible1" width="30%">
      <el-form :model="dialog">
        <el-form-item label="承运人">
          <el-tooltip content="东岳头程默认承运人为东岳" placement="top">
            <el-input v-model="dialog.carrier"></el-input>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="追踪单号">
          <el-tooltip content="东岳头程默认追踪单号为订单号" placement="top">
            <el-input v-model="dialog.trackNo"></el-input>
          </el-tooltip>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="fillInTrackNo">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="确认单号" :visible.sync="dialogVisible1Sub" width="30%">
      <el-form :model="dialog">
        <el-form-item label="承运人">
          <el-tooltip content="东岳头程默认承运人为东岳" placement="top">
            <el-input v-model="dialog.carrier"></el-input>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="追踪单号">
          <el-tooltip content="东岳头程默认追踪单号为订单号" placement="top">
            <el-input v-model="dialog.trackNo"></el-input>
          </el-tooltip>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible1Sub = false">取 消</el-button>
        <el-button type="primary" @click="submitTrackNo(4)">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="申请报关单" :visible.sync="dialogVisible2" width="40%">
      <el-form :model="profile" label-width="135px">
        <el-alert
          title="此页面提供系统生成的报关单内容，如您修改了下载的报关单，请通过上传报关单上传新版的报关单，您也可以自己编写响应的报关单通过上传报关单功能进行上传.当前页面所有输入框必填"
          type="info"
          style="margin-bottom: 2%"
        />
        <el-col :span="12">
          <el-form-item label="发货时间">
            <el-date-picker v-model="profile.deliverDate" type="date" placeholder="选择日期" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="国际运单号">
            <el-input v-model="profile.trackNo" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业名称（中文）" prop="chineseName">
            <el-input v-model="profile.chineseName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业名称（英文）" prop="englishName">
            <el-input v-model="profile.englishName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业地址（中文）" prop="chineseAddr">
            <el-input v-model="profile.chineseAddr" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业地址（英文）" prop="englishAddr">
            <el-input v-model="profile.englishAddr" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业邮编" prop="zipCode">
            <el-input v-model="profile.zipCode" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业联系人姓名（中文）" prop="contactEnglishName">
            <el-input v-model="profile.contactEnglishName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业联系人姓名（英文）" prop="contactChineseName">
            <el-input v-model="profile.contactChineseName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业联系方式" prop="phone">
            <el-input v-model="profile.phone" />
          </el-form-item>
        </el-col>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="printAndSave">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="上传报关单" :visible.sync="dialogVisible3" width="20%">
      <el-form :model="dialogForm3">
        <el-form-item label="报关单文件">
          <el-upload
            ref="upload"
            :action="uploadLink"
            :auto-upload="false"
            with-credentials
            :on-error="handleError"
            :limit="1"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button
              style="margin-left: 10px;"
              size="small"
              type="success"
              @click="submitUpload"
            >上传</el-button>
            <div slot="tip" class="el-upload__tip">
              上传文件大小必须小于20M
              <br />请点击上传按钮进行上传
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="获取入库单标签" :visible.sync="dialogVisible4Main" width="20%">
      <div>
        <el-radio v-model="dialog4.radio" label="3*8">3*8</el-radio>
        <el-radio v-model="dialog4.radio" label="4*10">4*10</el-radio>
        <el-radio v-model="dialog4.radio" label="2*2">2*2</el-radio>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible4Main = false">取 消</el-button>
        <el-button type="primary" @click="getBarcode()">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="获取入库单标签" :visible.sync="dialogVisible4" width="40%">
      <div id="pdfDom" class="a4-pdf">
        <div v-for="item in dialog4.barcodeLength8" v-bind:key="item.item">
          <div
            v-for="item in dialog4.barcodeLength3"
            v-bind:key="item.item"
            style="display:inline-block;margin:1%"
          >
            <custom-syncfusion-barcode v-bind:barcode="barcode1"></custom-syncfusion-barcode>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible4 = false">取 消</el-button>
        <el-button type="primary" @click="getPdf('#pdfDom')">下载</el-button>
      </span>
    </el-dialog>
    <el-dialog title="获取入库单标签" :visible.sync="dialogVisible4Sub" width="40%">
      <div id="pdfDom" class="a4-pdf-v2">
        <div v-for="item in dialog4.barcodeLength10" v-bind:key="item.item">
          <div
            v-for="item in dialog4.barcodeLength4"
            v-bind:key="item.item"
            style="display:inline-block;margin:1%"
          >
            <custom-syncfusion-barcode v-bind:barcode="barcode2"></custom-syncfusion-barcode>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible4Sub = false">取 消</el-button>
        <el-button type="primary" @click="getPdf('#pdfDom')">下载</el-button>
      </span>
    </el-dialog>
    <el-dialog title="获取入库单标签" :visible.sync="dialogVisible4Sub2" width="40%">
      <div id="pdfDom" class="a4-pdf-v2">
        <div v-for="item in dialog4.barcodeLength2" v-bind:key="item.item">
          <div
            v-for="item in dialog4.barcodeLength2"
            v-bind:key="item.item"
            style="display:inline-block;margin:1%"
          >
            <custom-syncfusion-barcode-detail v-bind:barcode="barcode2"></custom-syncfusion-barcode-detail>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible4Sub2 = false">取 消</el-button>
        <el-button type="primary" @click="getPdf('#pdfDom')">下载</el-button>
      </span>
    </el-dialog>
    <el-dialog title="入库单预审" :visible.sync="dialogVisible5" width="20%">
      <p>入库单号:{{ dialogForm5.warehousingNo }}</p>
      <div
        v-for="warehousingContent in dialogForm5.warehousingContentList"
        v-bind:key="warehousingContent.uuid"
        style="margin:2%"
      >
        箱号:{{ warehousingContent.boxSeq }}
        <span style="margin-left:5px">
          是否收货
          <el-switch
            style="margin-left:5px"
            v-model="warehousingContent.received"
            active-color="#13ce66"
            inactive-color="#ff4949"
          ></el-switch>
        </span>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible5 = false">取 消</el-button>
        <el-button type="primary" @click="updateWarehousingContent()">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="货物清点" :visible.sync="dialogVisible6" width="40%">
      <el-row>
        <el-col :span="14">
          <p>入库单号:{{ dialogForm6.warehousingNo }}</p>
        </el-col>
        <el-col :span="10">
          <el-input
            v-model="dialogForm6.skuFromScanner"
            @change="searchWarehousingContentList()"
            placeholder="请扫描商品sku"
          />
        </el-col>
      </el-row>
      <div
        v-for="warehousingContent in dialogForm6.warehousingContentList"
        v-bind:key="warehousingContent.uuid"
        style="margin:2%"
      >
        箱号:
        <b style="margin-left:4px">{{ warehousingContent.boxSeq }}</b>
        sku:
        <b style="margin-left:4px">{{ warehousingContent.sku }}</b>
        名称:
        <b style="margin-left:4px">{{ warehousingContent.name }}</b>
        <el-row style="margin-top:1%">
          发货数量:
          <b style="margin-left:4px">{{ warehousingContent.totalNum }}</b>
          收货数量:
          <el-input-number
            style="margin-left:4px"
            v-model="warehousingContent.actual"
            placeholder="收货数量"
          ></el-input-number>
        </el-row>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible6 = false">取 消</el-button>
        <el-button type="primary" @click="updateWarehousingContent2nd()">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="货物上架" :visible.sync="dialogVisible7" width="40%">
      <el-row>
        <el-col :span="14">
          <p>入库单号:{{ dialogForm7.warehousingNo }}</p>
        </el-col>
        <el-col :span="10">
          <el-input
            v-model="dialogForm7.skuFromScanner"
            @change="searchWarehousingContentList7()"
            placeholder="请扫描商品sku"
          />
        </el-col>
      </el-row>
      <div
        v-for="warehousingContent in dialogForm7.warehousingContentList"
        v-bind:key="warehousingContent.uuid"
        style="margin:2%"
      >
        <el-row>
          箱号:
          <b style="margin-left:4px">{{ warehousingContent.boxSeq }}</b>
          sku:
          <b style="margin-left:4px">{{ warehousingContent.sku }}</b>
        </el-row>
        <el-row style="margin-top:1%">
          名称:
          <b style="margin-left:4px">{{ warehousingContent.name }}</b>
          收货数量:
          <b style="margin-left:4px">{{ warehousingContent.actual }}</b>
        </el-row>
        <el-row style="margin-top: 1%">
          上架数量:
          <el-input-number v-model="upshelfNum" clearable placeholder="上架数量" />上架货架:
          <el-select v-model="shelfNo" filterable placeholder="上架货架">
            <el-option
              v-for="item in options"
              :key="item.shelfNo"
              :label="item.shelfNo"
              :value="item.shelfNo"
            >
              <span>
                货架号 {{ item.shelfNo }} 货架区域 {{ item.area }} 货架行数
                {{ item.layer }} 货架层数 {{ item.rowNo }}
              </span>
            </el-option>
          </el-select>
          <el-button
            icon="el-icon-circle-plus-outline"
            circle
            @click="addUpShelf(warehousingContent)"
          />
        </el-row>
        <el-table
          style="margin-top:1%;width: 100%"
          :data="warehousingContent['upshelfData']"
          v-loading.body="tableLoading"
          element-loading-text="加载中"
          stripe
          highlight-current-row
        >
          <el-table-column width="200" prop="sku" label="sku" />
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible7 = false">取 消</el-button>
        <el-button type="primary" @click="up2shelf()">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="头程校验" :visible.sync="dialogVisible8" width="40%">
      <div v-for="box in dialogForm8.boxList" v-bind:key="box.boxSeq" style="margin:2%">
        <el-row>
          箱号:
          <b>{{box.boxSeq}}</b>
        </el-row>
        <el-row style="margin-top:2%">
          <el-col :span="8">
            长(cm):
            <el-input-number v-model="box.length"></el-input-number>
          </el-col>
          <el-col :span="8">
            宽(cm):
            <el-input-number v-model="box.width"></el-input-number>
          </el-col>
          <el-col :span="8">
            高(cm):
            <el-input-number v-model="box.height"></el-input-number>
          </el-col>
        </el-row>
        <el-row style="margin-top:2%">
          重量(kg):
          <el-input-number v-model="box.weight"></el-input-number>
        </el-row>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible8 = false">取消</el-button>
        <el-button type="primary" @click="headCheck()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import request from '../../../utils/service';
import CustomSyncfusionBarcode from '../../@syncfusion/custom-syncfusion-barcode';
import CustomSyncfusionBarcodeDetail from '../../@syncfusion/custom-syncfusion-barcode-detail';

export default {
  name: 'warehousingTable',
  components: {CustomSyncfusionBarcode, CustomSyncfusionBarcodeDetail},
  data() {
    return {
      width: '200px',
      height: '150px',
      mode: 'SVG',
      type: 'Code128',
      value: 'Code128',
      msgData: {
        status: this.msg.status,
        category: this.msg.category,
        buttonVisible1: this.msg.buttonVisible1 === true,
        buttonVisible2: this.msg.buttonVisible2 === true,
        buttonVisible3: this.msg.buttonVisible3 === true,
        buttonVisible4: this.msg.buttonVisible4 === true,
        buttonVisible5: this.msg.buttonVisible5 === true,
        buttonVisible6: this.msg.buttonVisible6 === true,
        buttonVisible7: this.msg.buttonVisible7 === true,
        buttonVisible8: this.msg.buttonVisible8 === true,
        buttonVisible9: this.msg.buttonVisible9 === true,
        buttonVisibleA: this.msg.buttonVisibleA === true,
        buttonVisibleB: this.msg.buttonVisibleB === true,
        buttonVisibleC: this.msg.buttonVisibleC === true,
        buttonVisible10: this.msg.buttonVisible10 === true,
        buttonVisible11: this.msg.buttonVisible11 === true,
        buttonVisible12: this.msg.buttonVisible12 === true,
        buttonVisible13: this.msg.buttonVisible13 === true,
        buttonVisible14: this.msg.buttonVisible14 === true,
      },
      // page data
      tablePage: {
        current: 1,
        pages: null,
        size: 10,
        total: null,
      },
      tableLoading: false,
      tableData: [],
      daterange: [new Date(new Date().getTime() - 3600 * 1000 * 24 * 7), new Date()],
      dialogVisible1: false,
      dialogVisible1Sub: false,
      dialogVisible2: false,
      dialogVisible3: false,
      dialogVisible4: false,
      dialogVisible4Main: false,
      dialogVisible4Sub: false,
      dialogVisible4Sub2: false,
      dialog4: {
        radio: '3*8',
        barcodeLength2: [{item: 1}, {item: 2}],
        barcodeLength3: [{item: 1}, {item: 2}, {item: 3}],
        barcodeLength4: [{item: 1}, {item: 2}, {item: 3}, {item: 4}],
        barcodeLength8: [
          {item: 1},
          {item: 2},
          {item: 3},
          {item: 4},
          {item: 5},
          {item: 6},
          {item: 7},
          {item: 8},
        ],
        barcodeLength10: [
          {item: 1},
          {item: 2},
          {item: 3},
          {item: 4},
          {item: 5},
          {item: 6},
          {item: 7},
          {item: 8},
          {item: 9},
          {item: 10},
        ],
      },
      dialogVisible5: false,
      dialogVisible6: false,
      dialogVisible7: false,
      dialogVisible8: false,
      pickerOptions2: {
        shortcuts: [
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            },
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            },
          },
        ],
      },
      users: [],
      channels: [],
      search: {
        warehousingNo: '',
        creator: '',
        channelCode: '',
        from: '',
        to: '',
        status: '',
        category: '',
      },
      dialog: {
        carrier: '',
        trackNo: '',
        warehousingNo: '',
      },
      print: {},
      profile: {
        chineseName: '',
        englishName: '',
        chineseAddr: '',
        englishAddr: '',
        zipCode: '',
        contactEnglishName: '',
        contactChineseName: '',
        phone: '',
        deliverDate: '',
        trackNo: '',
        warehousingNo: '',
      },
      dialogForm3: {},
      uploadLink: process.env.BASE_API + '/warehousing/userFile',
      barcode1: {},
      barcode2: {},
      barcode3: {},
      dialogForm5: {
        warehousingNo: '',
        warehousingContentList: [],
        warehousing: {},
      },
      dialogForm6: {
        warehousingNo: '',
        warehousingContentList: '',
        warehousing: '',
        skuFromScanner: '',
      },
      dialogForm7: {
        warehousingNo: '',
        warehousingContentList: '',
        warehousing: '',
        skuFromScanner: '',
      },
      dialogForm8: {},
      options: [],
      upshelfNum: 0,
      shelfNo: '',
    };
  },
  props: ['msg'],
  created() {
    this.search.from = this.daterange[0];
    this.search.to = this.daterange[1];
    this.fetchData();
    this.initUserList();
  },
  methods: {
    fetchData() {
      this.tableLoading = true;
      this.search.status=this.msgData.status;
      this.search.category=this.msgData.category;
      const postData = {
        'entity': this.search,
        'pagination': this.tablePage,
      };
      request({
        url:
          'warehousing/v2/list/',
        method: 'post',
        data: postData,
      }).then((res) => {
        const tableRecords = res.data.page.data;
        for (let i = 0; i < tableRecords.length; i++) {
          const record = tableRecords[i];
          record.channelFlag = Boolean(record.channel);
        }
        this.tableData = tableRecords;
        this.tablePage.current = res.data.page.current;
        this.tablePage.pages = res.data.page.pages;
        this.tablePage.size = res.data.page.size;
        this.tablePage.total = res.data.page.total;
        this.tableLoading = false;
      });
    },
    handleUpdate(index, row) {
      this.$router.push({
        path:
          '/new-warehousing/new-warehousing?warehousingNo=' + row.warehousingNo,
      });
    },
    statusUpdate(index, row, statusUpdateTo) {
      this.$confirm('您确定要提交该订单？', '提示', confirm)
          .then(() => {
            request({
              url: 'warehousing/status',
              method: 'post',
              data: {
                to: statusUpdateTo,
                warehousingNo: row.warehousingNo,
              },
            }).then(() => {
              this.fetchData();
              this.$message.success('提交成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    route2NewWarehousing() {
      this.$router.push({
        path: '/new-warehousing/new-warehousing',
      });
    },
    hold(index, row) {
      const warehousingNo = row.warehousingNo;
      this.$confirm(
          '您确定要暂存该入库单？（该订单可在暂存页面查看）',
          '提示',
          confirm
      )
          .then(() => {
            request({
              url: 'warehousing/status',
              method: 'post',
              data: {
                to: '8',
                warehousingNo: warehousingNo,
              },
            }).then(() => {
              this.fetchData();
              this.$message.success('暂存成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消提交');
          });
    },
    handleDelete(index, row) {
      this.$confirm('您确定要删除该入库单？(该订单将无法恢复)', '提示', confirm)
          .then(() => {
            request({
              url: 'warehousing/drop?warehousingNo=' + row.warehousingNo,
              method: 'get',
            }).then(() => {
              this.fetchData();
              this.$message.success('删除成功');
            });
          })
          .catch(() => {
            this.$message.info('已取消');
          });
    },
    handlePrint(index, row) {
      this.profile.trackNo = row.trackNo;
      this.profile.warehousingNo = row.warehousingNo;
      request({
        url: '/profile/init',
        method: 'get',
      }).then((res) => {
        const profile = res.data.data;
        this.profile.chineseName = profile.chineseName;
        this.profile.englishName = profile.englishName;
        this.profile.chineseAddr = profile.chineseAddr;
        this.profile.englishAddr = profile.englishAddr;
        this.profile.zipCode = profile.zipCode;
        this.profile.contactEnglishName = profile.contactEnglishName;
        this.profile.contactChineseName = profile.contactChineseName;
        this.profile.phone = profile.phone;
      });
      this.dialogVisible2 = true;
    },
    handleSizeChange(val) {
      this.tablePage.size = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.tablePage.current = val;
      this.fetchData();
    },
    route2ChannelPage(index, row) {
      this.$router.push({
        path: '/system/channel?filter=' + row.channel,
      });
    },
    exportExcel() {
      const link = document.createElement('a');
      link.style.display = 'none';
      if (this.search.creator.length > 0) {
        link.href =
          process.env.BASE_API +
          '/warehousing/excel/1?method=东岳头程&creator=' +
          this.search.creator;
      } else {
        link.href =
          process.env.BASE_API + '/warehousing/excel/1?method=东岳头程';
      }
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
    },
    applyTrackno(index, row) {
      this.dialog.warehousingNo = row.warehousingNo;
      this.dialog.carrier = row.carrier;
      this.dialog.trackNo = row.trackNo;
      this.dialogVisible1 = true;
    },
    fillInTrackNo() {
      request({
        url: 'warehousing/trackno',
        method: 'post',
        data: this.dialog,
      }).then(() => {
        this.dialogVisible1 = false;
        this.fetchData();
      });
    },
    printAndSave() {
      request({
        url: '/warehousing/printCustomsDeclaration',
        method: 'post',
        data: this.profile,
      }).then((res) => {
        const uuid = res.data.data;
        const link = document.createElement('a');
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/file/' + uuid;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
        this.dialogVisible2 = false;
      });
    },
    handleUploadFile(index, row) {
      this.dialogVisible3 = true;
      this.uploadLink = this.uploadLink + '?warehousingNo=' + row.warehousingNo;
    },
    submitUpload() {
      this.$refs.upload.submit();
      this.$message.success('上传成功');
    },
    handleError(err) {
      this.$message.error(JSON.parse(err.message)['message']);
    },
    handleSystemFile(index, row) {
      if (row['systemFileUuid'] == null) {
        this.$message.warning('报关单未生成，请点击获取报关单生成报关单');
      } else {
        const link = document.createElement('a');
        const uuid = row.systemFileUuid;
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/file/' + uuid;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      }
    },
    handleUserWarehousingFile(index, row) {
      if (row.userWarehousingFileUuid == null) {
        this.$message.warning('报关单未上传，请点击上传报关单进行上传');
      } else {
        const link = document.createElement('a');
        const uuid = row.userWarehousingFileUuid;
        link.style.display = 'none';
        link.href = process.env.BASE_API + '/file/' + uuid;
        link.target = '_blank';
        document.body.appendChild(link);
        link.click();
      }
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
    printWarehousingBarcode(index, row) {
      this.dialogVisible4Main = true;
      console.log(row);
      this.barcode1 = {
        value: row.warehousingNo,
        width: 185,
        height: 93,
      };
      this.barcode2 = {
        value: row.warehousingNo,
        width: 130,
        height: 72,
        textSize: 13,
      };
      this.barcode3 = {
        value: row.warehousingNo,
        width: 130,
        height: 72,
        textSize: 13,
      };
    },
    handleDialogVisible5(index, row) {
      this.dialogVisible5 = true;
      this.dialogForm5.warehousingNo = row.warehousingNo;
      this.dialogForm5.warehousingContentList = row.warehousingContentList;
      this.dialogForm5.warehousing = row;
    },
    handleDialogVisible6(index, row) {
      this.dialogVisible6 = true;
      this.dialogForm6.warehousingNo = row.warehousingNo;
      this.dialogForm6.warehousingContentList = row.warehousingContentList;
      this.dialogForm6.warehousing = row;
    },
    handleDialogVisible7(index, row) {
      this.$router.push({
        path: '/warehousing/upshelf/upshelf?warehousingNo=' + row.warehousingNo,
      });
    },
    fetchShelves() {
      request({
        url: '/shelf/list/enable',
        method: 'get',
      }).then((res) => {
        this.options = res.data.data;
      });
    },
    up2shelf() {
      this.$message.success('上架成功');
      this.dialogVisible7 = false;
    },
    updateWarehousingContent() {
      this.dialogForm5.warehousing.warehousingContentList = this.dialogForm5.warehousingContentList;
      this.dialogForm5.warehousing.status = '3';
      const method = this.dialogForm5.warehousing.method;
      if ('东岳头程' === method) {
        this.dialogForm5.warehousing.status = '5';
      }
      request({
        url: '/warehousing/update',
        method: 'post',
        data: this.dialogForm5.warehousing,
      }).then(() => {
        this.$message.success('预审完成，准备上架清点');
        this.dialogVisible5 = false;
        this.fetchData();
      });
    },
    updateWarehousingContent2nd() {
      let flag = true;
      for (const i in this.dialogForm6.warehousingContentList) {
        if (this.dialogForm6.warehousingContentList.hasOwnProperty(i)) {
          const element = this.dialogForm6.warehousingContentList[i];
          if (element['actual'] != element['totalNum']) {
            this.$message.warning(element['sku'] + '到货数量与预期不符');
            flag = false;
            break;
          }
        }
      }
      if (flag) {
        this.dialogForm6.warehousing.warehousingContentList = this.dialogForm6.warehousingContentList;
        this.dialogForm6.warehousing.status = '4';
        const method = this.dialogForm6.warehousing.method;
        if ('东岳头程' === method) {
          this.dialogForm6.warehousing.status = '6';
        }
        request({
          url: '/warehousing/update',
          method: 'post',
          data: this.dialogForm6.warehousing,
        }).then(() => {
          this.dialogVisible6 = false;
          this.$message.success('上架清点完成');
          this.fetchData();
        });
      }
    },
    getBarcode() {
      if (this.dialog4['radio'] === '3*8') {
        this.dialogVisible4Main = false;
        this.dialogVisible4Sub = false;
        this.dialogVisible4Sub2 = false;
        this.dialogVisible4 = true;
      } else if (this.dialog4['radio'] === '4*10') {
        this.dialogVisible4 = false;
        this.dialogVisible4Main = false;
        this.dialogVisible4Sub2 = false;
        this.dialogVisible4Sub = true;
      } else if (this.dialog4['radio'] === '2*2') {
        this.dialogVisible4 = false;
        this.dialogVisible4Main = false;
        this.dialogVisible4Sub = false;
        this.dialogVisible4Sub2 = true;
      }
    },
    customBarcodeSize(val) {
      if (val === '3*8') {
        this.dialogVisible4 = false;
        this.dialogVisible4Sub = true;
        this.dialog4['radio'] = '3*8';
      } else if (val === '4*10') {
        this.dialogVisible4 = true;
        this.dialogVisible4Sub = false;
        this.dialog4['radio'] = '4*10';
      }
    },
    searchWarehousingContentList() {
      const sku = this.dialogForm6.skuFromScanner.trim();
      const contentList = this.dialogForm6.warehousingContentList;
      for (const i in contentList) {
        if (contentList.hasOwnProperty(i)) {
          const element = contentList[i];
          if (element['sku'] === sku) {
            if (
              element.hasOwnProperty('actualNum') &&
              element['actualNum'] >= 0
            ) {
              element['actualNum'] += 1;
            } else {
              element['actualNum'] = 1;
            }
            break;
          }
        }
      }
    },
    addUpShelf(warehousingContent) {
      if (this.shelfNo.length <= 0 || this.upshelfNum <= 0) {
        this.$message.warning('请选择货架和上架数量');
        return;
      }
      let upshelfData = warehousingContent['upshelfData'];
      if (upshelfData === undefined || upshelfData.length <= 0) {
        upshelfData = [];
      }
      upshelfData.push({
        shelfNo: this.shelfNo,
        upshelfNum: this.upshelfNum,
        sku: warehousingContent.sku,
      });
      warehousingContent['upshelfData'] = upshelfData;
      console.log(warehousingContent);
      console.log(this.dialogForm7.warehousingContentList);
    },
    removeUpShelf(item, all) {
      console.log(item);
      console.log(all);
    },
    toggleHeadCheckDialog(index, row) {
      this.dialogVisible8 = true;
      this.dialogForm8.ori = row;
      const warehousingContentList = row.warehousingContentList;
      const distinctSeqList = new Set();
      for (const warehousingContent of warehousingContentList) {
        distinctSeqList.add(warehousingContent.boxSeq);
      }
      this.dialogForm8.boxList = [];
      for (const seq of distinctSeqList) {
        const tmp = {
          boxSeq: seq,
          warehousingNo: row.warehousingNo,
        };
        this.dialogForm8.boxList.push(tmp);
      }
    },
    headCheck() {
      const warehousingNo = this.dialogForm8.ori.warehousingNo;
      request({
        url: '/warehousing/content/headCheck?warehousingNo=' + warehousingNo,
        method: 'post',
        data: this.dialogForm8.boxList,
      }).then(() => {
        this.$message.success(
            '入库单' + warehousingNo + '头程检查结束，等待发货'
        );
        this.dialogVisible8 = false;
        this.fetchData();
      });
    },
    confirmTrackNo(index, row) {
      this.dialog.warehousingNo = row.warehousingNo;
      this.dialog.carrier = row.carrier;
      this.dialog.trackNo = row.trackNo;
      this.dialogVisible1Sub = true;
    },
    submitTrackNo(statusUpdateTo) {
      request({
        url: 'warehousing/status',
        method: 'post',
        data: {
          to: statusUpdateTo,
          warehousingNo: this.dialog.warehousingNo,
        },
      }).then(() => {
        this.fetchData();
        this.$message.success('更新成功');
        this.dialogVisible1Sub = false;
      });
    },
    reGenSearchData(val) {
      this.search.from = val[0];
      this.search.to = val[1];
    },
  },
  mounted() {},
};
</script>

<style>
.center {
  margin-left: 14px;
  margin-right: 17px;
  margin-top: 14px;
  min-width: 280px;
  width: auto;
  border: 2px solid lightgray;
  min-height: 40%;
  padding-top: 35px;
}

.a4-pdf {
  height: 842px;
  width: 595px;
  margin-left: 40px;
  margin-right: 40px;
}

.a4-pdf-v2 {
  height: 842px;
  width: 595px;
  margin-left: 40px;
  margin-right: 40px;
}

.a4-pdf-v2 text {
  font-size: 14px;
}
</style>
