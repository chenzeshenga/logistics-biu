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
                            >
                            </el-date-picker>
                        </el-tooltip>
                    </el-col>
                    <el-col :span="4">
                        <el-tooltip content="入库单号" placement="top">
                            <el-input
                                    v-model="search.warehousingNo"
                                    clearable
                                    placeholder="请输入入库单号"
                            />
                        </el-tooltip>
                    </el-col>
                    <el-col :span="4">
                        <el-tooltip content="创建人" placement="top">
                            <el-select
                                    filterable
                                    clearable
                                    v-model="search.creator"
                                    placeholder="请选择创建人"
                            >
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
                            <el-select
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
                                />
                            </el-select>
                        </el-tooltip>
                    </el-col>
                    <el-col :span="1">
                        <el-form-item label="">
                            <el-button
                                    icon="el-icon-search"
                                    @click="searchOrd()"
                            />
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20" style="margin-top: 1%;margin-left: 4%">
                    <!--            <el-col :span="2">-->
                    <!--              <el-button type="primary" @click="applyTrackNo()" v-if="multiSelection">-->
                    <!--                批量申请单号-->
                    <!--              </el-button>-->
                    <!--            </el-col>-->
                    <!--            <el-col :span="2">-->
                    <!--              <el-button type="primary" @click="batchStatusUpdate()" v-if="multiSelection">-->
                    <!--                批量提交-->
                    <!--              </el-button>-->
                    <!--            </el-col>-->
                    <el-col :span="2">
                        <el-button
                                type="primary"
                                @click="route2NewWarehousing()"
                        >新建入库单
                        </el-button>
                    </el-col>
                    <el-col :span="2">
                        <el-button
                                type="primary"
                                @click="exportExcel()"
                                icon="iconfont icon-jichukongjiantubiao-gonggongxuanzekuang"
                        >导出excel
                        </el-button>
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
            <el-table-column type="selection" width="55"/>
            <el-table-column type="expand">
                <template slot-scope="tableData">
                    <el-table :data="tableData.row.warehousingContentList">
                        <el-table-column
                                prop="sku"
                                label="sku/东岳Sku"
                                width="250"
                        />
                        <el-table-column
                                prop="name"
                                label="商品名称"
                                width="250"
                        />
                        <el-table-column
                                prop="boxSeq"
                                label="箱号"
                                width="150"
                        />
                        <el-table-column
                                prop="totalNum"
                                label="数量"
                                width="200"
                        />
                        <el-table-column
                                prop="wrapType"
                                label="包装方式"
                                width="250"
                        />
                    </el-table>
                </template>
            </el-table-column>
            <el-table-column
                    width="200"
                    prop="warehousingNo"
                    label="入库单号"
            />
            <el-table-column
                    width="150"
                    prop="target"
                    label="仓库地址"
            />
            <el-table-column
                    width="100"
                    prop="statusDesc"
                    label="状态"
            />
            <el-table-column
                    width="150"
                    prop="method"
                    label="头程方式"
            />
            <el-table-column width="150" label="头程渠道">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top" v-if="scope.row.channelFlag">
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
                            >{{ scope.row['channelDesc'] }}
                            </el-tag>
                        </div>
                    </el-popover>
                    <el-popover trigger="hover" placement="top" v-if="!scope.row.channelFlag">
                        <p>未选择头程渠道</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium"
                            >未选择头程渠道
                            </el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column
                    width="100"
                    prop="carrier"
                    label="承运人"
            />
            <el-table-column
                    width="150"
                    prop="trackNo"
                    label="追踪单号"
            />
            <el-table-column
                    width="170"
                    prop="deliverMethod"
                    label="运输方式"
            />
            <el-table-column
                    width="200"
                    prop="clearanceType"
                    label="报关类型"
            />
            <el-table-column
                    width="150"
                    prop="taxType"
                    label="关税类型"
            />
            <el-table-column
                    width="150"
                    prop="insurance"
                    label="保险服务"
            />
            <el-table-column
                    width="150"
                    prop="insuranceNum"
                    label="保险总值(JPY)"
            />
            <el-table-column
                    width="180"
                    prop="estimatedDate"
                    label="预计到港时间"
            />
            <el-table-column width="150" label="系统生成报关单下载">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>点击按钮下载文件</p>
                        <p>
                            <el-button
                                    type="text"
                                    v-on:click="
                                    handleSystemFile(scope.$index, scope.row)
                                "
                            >下载
                            </el-button>
                        </p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">
                                <svg-icon icon-class="doc"/>
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
                >下载
                </el-button
                >
            </el-table-column>
            <el-table-column width="150" label="用户上传报关单下载">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>点击按钮下载文件</p>
                        <p>
                            <el-button
                                    type="text"
                                    v-on:click="
                                    handleUserWarehousingFile(
                                        scope.$index,
                                        scope.row
                                    )
                                "
                            >下载
                            </el-button>
                        </p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">
                                <svg-icon icon-class="doc"/>
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
                >下载
                </el-button
                >
            </el-table-column>
            <el-table-column
                    width="170"
                    prop="createOn"
                    label="创建时间"
            />
            <el-table-column
                    width="170"
                    prop="updateOn"
                    label="修改时间"
            />
            <el-table-column
                    width="150"
                    prop="creator"
                    label="创建人"
            />
            <el-table-column
                    width="150"
                    prop="updator"
                    label="修改人"
            />
            <el-table-column label="操作" width="400" fixed="right">
                <template slot-scope="scope">
                    <el-tooltip
                            content="打印入单标签"
                            placement="top"
                            v-if="msgData.buttonVisible1"
                    >
                        <el-button
                                @click="printWarehousingBarcode(scope.$index, scope.row, 2)"
                                size="mini"
                                type="info"
                                icon="el-icon-download"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="发往东岳国内前置海外仓"
                            placement="top"
                            v-if="msgData.buttonVisible1"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 2)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="东岳国内前置海外仓已收货，发往头程校验"
                            placement="top"
                            v-if="msgData.buttonVisible8"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 3)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="头程校验完成，发往日本"
                            placement="top"
                            v-if="msgData.buttonVisible9"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 4)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="日本仓库已收货，发往入库清点"
                            placement="top"
                            v-if="msgData.buttonVisibleA"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 5)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="入库清点完成，发往仓库上架"
                            placement="top"
                            v-if="msgData.buttonVisibleB"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 6)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="上架完成，开始计仓储费"
                            placement="top"
                            v-if="msgData.buttonVisibleC"
                    >
                        <el-button
                                @click="statusUpdate(scope.$index, scope.row, 7)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="预申请单号"
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
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="获取报关单"
                            placement="top"
                            v-if="msgData.buttonVisible3"
                    >
                        <el-button
                                @click="handlePrint(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-printer"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="上传报关单"
                            placement="top"
                            v-if="msgData.buttonVisible4"
                    >
                        <el-button
                                @click="handleUploadFile(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-upload"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="编辑"
                            placement="top"
                            v-if="msgData.buttonVisible5"
                    >
                        <el-button
                                @click="handleUpdate(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-edit"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="删除"
                            placement="top"
                            v-if="msgData.buttonVisible6"
                    >
                        <el-button
                                @click="handleDelete(scope.$index, scope.row)"
                                size="mini"
                                type="danger"
                                icon="el-icon-remove"
                                circle
                                plain
                        />
                    </el-tooltip>
                    <el-tooltip
                            content="废弃"
                            placement="top"
                            v-if="msgData.buttonVisible7"
                    >
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
        >
        </el-pagination>
        <el-dialog title="申请单号" :visible.sync="dialogVisible1" width="30%">
            <el-form :model="dialog">
                <el-form-item label="承运人">
                    <el-tooltip
                            content="东岳头程默认承运人为东岳"
                            placement="top"
                    >
                        <el-input v-model="dialog.carrier"></el-input>
                    </el-tooltip>
                </el-form-item>
                <el-form-item label="追踪单号">
                    <el-tooltip
                            content="东岳头程默认追踪单号为订单号"
                            placement="top"
                    >
                        <el-input v-model="dialog.trackNo"></el-input>
                    </el-tooltip>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible1 = false">取 消</el-button>
                <el-button type="primary" @click="fillInTrackNo"
                >确 定</el-button
                >
            </span>
        </el-dialog>
        <el-dialog
                title="申请报关单"
                :visible.sync="dialogVisible2"
                width="40%"
        >
            <el-form :model="profile" label-width="135px">
                <el-alert
                        title="此页面提供系统生成的报关单内容，如您修改了下载的报关单，请通过上传报关单上传新版的报关单，您也可以自己编写响应的报关单通过上传报关单功能进行上传.当前页面所有输入框必填"
                        type="info"
                        style="margin-bottom: 2%"
                />
                <el-col :span="12">
                    <el-form-item label="发货时间">
                        <el-date-picker
                                v-model="profile.deliverDate"
                                type="date"
                                placeholder="选择日期"
                        />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="国际运单号">
                        <el-input v-model="profile.trackNo"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业名称（中文）" prop="chineseName">
                        <el-input v-model="profile.chineseName"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业名称（英文）" prop="englishName">
                        <el-input v-model="profile.englishName"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业地址（中文）" prop="chineseAddr">
                        <el-input v-model="profile.chineseAddr"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业地址（英文）" prop="englishAddr">
                        <el-input v-model="profile.englishAddr"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业邮编" prop="zipCode">
                        <el-input v-model="profile.zipCode"/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                            label="企业联系人姓名（中文）"
                            prop="contactEnglishName"
                    >
                        <el-input
                                v-model="profile.contactEnglishName"
                        />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item
                            label="企业联系人姓名（英文）"
                            prop="contactChineseName"
                    >
                        <el-input
                                v-model="profile.contactChineseName"
                        />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="企业联系方式" prop="phone">
                        <el-input v-model="profile.phone"/>
                    </el-form-item>
                </el-col>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="printAndSave"
                >确 定</el-button
                >
            </span>
        </el-dialog>
        <el-dialog
                title="上传报关单"
                :visible.sync="dialogVisible3"
                width="20%"
        >
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
                        <el-button slot="trigger" size="small" type="primary"
                        >选取文件
                        </el-button
                        >
                        <el-button
                                style="margin-left: 10px;"
                                size="small"
                                type="success"
                                @click="submitUpload"
                        >上传
                        </el-button
                        >
                        <div slot="tip" class="el-upload__tip">
                            上传文件大小必须小于20M<br/>
                            请点击上传按钮进行上传
                        </div>
                    </el-upload>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-dialog title="获取入库单标签" :visible.sync="dialogVisible4" width="20%">
            <ejs-barcodegenerator
                    id="barcode"
                    :invalid="invalidInput"
                    ref="barcodeControl"
                    :width="width"
                    :height="height"
                    :type="type"
                    :value="value"
                    :mode="mode"
            ></ejs-barcodegenerator>
        </el-dialog>
    </div>
</template>

<script>
    import request from '../../../utils/service'
    import {
        Button,
        ButtonPlugin,
        CheckBoxPlugin,
    } from '@syncfusion/ej2-vue-buttons'
    import { Browser } from '@syncfusion/ej2-base'
    import { BarcodeGeneratorPlugin } from '@syncfusion/ej2-vue-barcode-generator'
    import {
        NumericTextBox,
        ColorPicker,
        NumericTextBoxPlugin,
        TextBoxPlugin,
        ColorPickerPlugin,
        ColorPickerEventArgs,
        FormValidator,
    } from '@syncfusion/ej2-vue-inputs'
    import * as Vue from 'vue'

    Vue.use(NumericTextBoxPlugin)
    Vue.use(BarcodeGeneratorPlugin)
    Vue.use(NumericTextBoxPlugin)
    Vue.use(CheckBoxPlugin)
    Vue.use(ColorPickerPlugin)
    Vue.use(TextBoxPlugin)
    export default {
        name: 'warehousingTable',
        data() {
            return {
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
                },
                // page data
                tablePage: {
                    current: 1,
                    pages: null,
                    size: null,
                    total: null,
                },
                tableLoading: false,
                tableData: [],
                daterange: null,
                dialogVisible1: false,
                dialogVisible2: false,
                dialogVisible3: false,
                dialogVisible4: false,
                pickerOptions2: {
                    shortcuts: [
                        {
                            text: '最近一周',
                            onClick(picker) {
                                const end = new Date()
                                const start = new Date()
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 7,
                                )
                                picker.$emit('pick', [start, end])
                            },
                        },
                        {
                            text: '最近一个月',
                            onClick(picker) {
                                const end = new Date()
                                const start = new Date()
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 30,
                                )
                                picker.$emit('pick', [start, end])
                            },
                        },
                        {
                            text: '最近三个月',
                            onClick(picker) {
                                const end = new Date()
                                const start = new Date()
                                start.setTime(
                                    start.getTime() - 3600 * 1000 * 24 * 90,
                                )
                                picker.$emit('pick', [start, end])
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
            }
        },
        props: ['msg'],
        created() {
            this.fetchData()
            this.initUserList()
        },
        methods: {
            fetchData() {
                this.tableLoading = true
                request({
                    url: 'warehousing/list/' + this.msgData.category + '/' + this.msgData.status,
                    method: 'post',
                    data: this.tablePage,
                }).then((res) => {
                    const tableRecords = res.data.page.records
                    for (let i = 0; i < tableRecords.length; i++) {
                        const record = tableRecords[i]
                        record.channelFlag = Boolean(record.channel)
                    }
                    this.tableData = tableRecords
                    this.tablePage.current = res.data.page.current
                    this.tablePage.pages = res.data.page.pages
                    this.tablePage.size = res.data.page.size
                    this.tablePage.total = res.data.page.total
                    this.tableLoading = false
                    console.log(this.tableData)
                })
            },
            handleUpdate(index, row) {
                this.$router.push({
                    path:
                        '/new-warehousing/new-warehousing?warehousingNo=' +
                        row.warehousingNo,
                })
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
                            this.fetchData()
                            this.$message.success('提交成功')
                        })
                    })
                    .catch(() => {
                        this.$message.info('已取消提交')
                    })
            },
            route2NewWarehousing() {
                this.$router.push({
                    path: '/new-warehousing/new-warehousing',
                })
            },
            hold(index, row) {
                const warehousingNo = row.warehousingNo
                this.$confirm(
                    '您确定要暂存该入库单？（该订单可在暂存页面查看）',
                    '提示',
                    confirm,
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
                            this.fetchData()
                            this.$message.success('暂存成功')
                        })
                    })
                    .catch(() => {
                        this.$message.info('已取消提交')
                    })
            },
            handleDelete(index, row) {
                this.$confirm(
                    '您确定要删除该入库单？(该订单将无法恢复)',
                    '提示',
                    confirm,
                )
                    .then(() => {
                        request({
                            url:
                                'warehousing/drop?warehousingNo=' +
                                row.warehousingNo,
                            method: 'get',
                        }).then(() => {
                            this.fetchData()
                            this.$message.success('删除成功')
                        })
                    })
                    .catch(() => {
                        this.$message.info('已取消')
                    })
            },
            handlePrint(index, row) {
                this.profile.trackNo = row.trackNo
                this.profile.warehousingNo = row.warehousingNo
                request({
                    url: '/profile/init',
                    method: 'get',
                }).then((res) => {
                    const profile = res.data.data
                    this.profile.chineseName = profile.chineseName
                    this.profile.englishName = profile.englishName
                    this.profile.chineseAddr = profile.chineseAddr
                    this.profile.englishAddr = profile.englishAddr
                    this.profile.zipCode = profile.zipCode
                    this.profile.contactEnglishName = profile.contactEnglishName
                    this.profile.contactChineseName = profile.contactChineseName
                    this.profile.phone = profile.phone
                })
                this.dialogVisible2 = true
            },
            handleSizeChange(val) {
                this.tablePage.size = val
                this.fetchData()
            },
            handleCurrentChange(val) {
                this.tablePage.current = val
                this.fetchData()
            },
            route2ChannelPage(index, row) {
                this.$router.push({
                    path: '/system/channel?filter=' + row.channel,
                })
            },
            exportExcel() {
                const link = document.createElement('a')
                link.style.display = 'none'
                if (this.search.creator.length > 0) {
                    link.href =
                        process.env.BASE_API +
                        '/warehousing/excel/1?method=东岳头程&creator=' +
                        this.search.creator
                } else {
                    link.href =
                        process.env.BASE_API +
                        '/warehousing/excel/1?method=东岳头程'
                }
                link.target = '_blank'
                document.body.appendChild(link)
                link.click()
            },
            applyTrackno(index, row) {
                this.dialog.warehousingNo = row.warehousingNo
                this.dialog.carrier = row.carrier
                this.dialog.trackNo = row.trackNo
                this.dialogVisible1 = true
            },
            fillInTrackNo() {
                request({
                    url: 'warehousing/trackno',
                    method: 'post',
                    data: this.dialog,
                }).then(() => {
                    this.dialogVisible1 = false
                    this.fetchData()
                })
            },
            printAndSave() {
                request({
                    url: '/warehousing/printCustomsDeclaration',
                    method: 'post',
                    data: this.profile,
                }).then((res) => {
                    const uuid = res.data.data
                    const link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = process.env.BASE_API + '/file/' + uuid
                    link.target = '_blank'
                    document.body.appendChild(link)
                    link.click()
                    this.dialogVisible2 = false
                })
            },
            handleUploadFile(index, row) {
                this.dialogVisible3 = true
                this.uploadLink =
                    this.uploadLink + '?warehousingNo=' + row.warehousingNo
            },
            submitUpload() {
                this.$refs.upload.submit()
                this.$message.success('上传成功')
            },
            handleError(err) {
                this.$message.error(JSON.parse(err.message)['message'])
            },
            handleSystemFile(index, row) {
                if (row['systemFileUuid'] == null) {
                    this.$message.warning(
                        '报关单未生成，请点击获取报关单生成报关单',
                    )
                } else {
                    const link = document.createElement('a')
                    const uuid = row.systemFileUuid
                    link.style.display = 'none'
                    link.href = process.env.BASE_API + '/file/' + uuid
                    link.target = '_blank'
                    document.body.appendChild(link)
                    link.click()
                }
            },
            handleUserWarehousingFile(index, row) {
                if (row.userWarehousingFileUuid == null) {
                    this.$message.warning('报关单未上传，请点击上传报关单进行上传')
                } else {
                    const link = document.createElement('a')
                    const uuid = row.userWarehousingFileUuid
                    link.style.display = 'none'
                    link.href = process.env.BASE_API + '/file/' + uuid
                    link.target = '_blank'
                    document.body.appendChild(link)
                    link.click()
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
                    this.users = res.data.page.records
                })
            },
            printWarehousingBarcode(index, row) {
                this.dialogVisible4 = true
            },
        },
    }
</script>

