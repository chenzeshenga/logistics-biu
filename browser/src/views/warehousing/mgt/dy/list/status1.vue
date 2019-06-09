<template>
    <div class="login-container">
        <div class="app-container">
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
                                    v-model="search.ordno"
                                    clearable
                                    placeholder="请输入入库单号"
                                ></el-input>
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
                                    ></el-option>
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
                                    ></el-option>
                                </el-select>
                            </el-tooltip>
                        </el-col>
                        <el-col :span="1">
                            <el-form-item label="">
                                <el-button
                                    icon="el-icon-search"
                                    @click="searchOrd()"
                                ></el-button>
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
            <el-table
                style="width: 100%"
                :data="tableData"
                v-loading.body="tableLoading"
                element-loading-text="加载中"
                stripe
                highlight-current-row
            >
                <!--                @selection-change="handleSelectionChange"-->
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column type="expand">
                    <template slot-scope="tableData">
                        <el-table :data="tableData.row.warehousingContentList">
                            <el-table-column
                                prop="sku"
                                label="sku/东岳Sku"
                                width="250"
                            ></el-table-column>
                            <el-table-column
                                prop="name"
                                label="商品名称"
                                width="250"
                            ></el-table-column>
                            <el-table-column
                                prop="boxSeq"
                                label="箱号"
                                width="150"
                            ></el-table-column>
                            <el-table-column
                                prop="totalNum"
                                label="数量"
                                width="200"
                            ></el-table-column>
                            <el-table-column
                                prop="wrapType"
                                label="包装方式"
                                width="250"
                            ></el-table-column>
                        </el-table>
                    </template>
                </el-table-column>
                <el-table-column
                    width="160"
                    prop="warehousingNo"
                    label="入库单号"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="target"
                    label="仓库地址"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="statusDesc"
                    label="状态"
                ></el-table-column>
                <el-table-column
                    width="200"
                    prop="method"
                    label="头程方式"
                ></el-table-column>
                <el-table-column width="150" label="头程渠道">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>渠道名称: {{ scope.row.channelDesc }}</p>
                            <p>渠道编码: {{ scope.row.channel }}</p>
                            <p>
                                <el-button
                                    type="text"
                                    v-on:click="
                                        channelLink(scope.$index, scope.row)
                                    "
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
                    prop="carrier"
                    label="承运人"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="trackNo"
                    label="追踪单号"
                ></el-table-column>
                <el-table-column
                    width="170"
                    prop="deliverMethod"
                    label="运输方式"
                ></el-table-column>
                <el-table-column
                    width="250"
                    prop="clearanceType"
                    label="报关类型"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="taxType"
                    label="关税类型"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="insurance"
                    label="保险服务"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="insuranceNum"
                    label="保险总值(JPY)"
                ></el-table-column>
                <el-table-column
                    width="180"
                    prop="estimatedDate"
                    label="预计到港时间"
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
                    prop="creator"
                    label="创建人"
                ></el-table-column>
                <el-table-column
                    width="150"
                    prop="updator"
                    label="修改人"
                ></el-table-column>
                <el-table-column label="操作" width="300" fixed="right">
                    <template slot-scope="scope">
                        <el-tooltip content="送往前置海外仓" placement="top">
                            <el-button
                                @click="statusUpdate(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-check"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="预申请单号" placement="top">
                            <el-button
                                @click="applyTrackno(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-info"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="编辑" placement="top">
                            <el-button
                                @click="handleUpdate(scope.$index, scope.row)"
                                size="mini"
                                type="info"
                                icon="el-icon-edit"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="删除" placement="top">
                            <el-button
                                @click="handleDelete(scope.$index, scope.row)"
                                size="mini"
                                type="danger"
                                icon="el-icon-remove"
                                circle
                                plain
                            ></el-button>
                        </el-tooltip>
                        <el-tooltip content="暂存" placement="top">
                            <el-button
                                @click="hold(scope.$index, scope.row)"
                                size="mini"
                                type="danger"
                                icon="el-icon-remove-outline"
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
        </div>
    </div>
</template>

<script>
import request from '../../../../../utils/service'

export default {
    name: 'warehousing-mgt-dy-list-status1',
    data() {
        return {
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
            pickerOptions2: {
                shortcuts: [
                    {
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date()
                            const start = new Date()
                            start.setTime(
                                start.getTime() - 3600 * 1000 * 24 * 7
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
                                start.getTime() - 3600 * 1000 * 24 * 30
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
                                start.getTime() - 3600 * 1000 * 24 * 90
                            )
                            picker.$emit('pick', [start, end])
                        },
                    },
                ],
            },
            users: [],
            channels: [],
            search: {
                ordno: '',
                creator: '',
                channelCode: '',
            },
        }
    },
    created() {
        this.fetchData()
    },
    methods: {
        fetchData() {
            this.tableLoading = true
            request({
                url: 'warehousing/list/1/1',
                method: 'post',
                data: this.tablePage,
            }).then(res => {
                this.tableData = res.data.page.records
                this.tablePage.current = res.data.page.current
                this.tablePage.pages = res.data.page.pages
                this.tablePage.size = res.data.page.size
                this.tablePage.total = res.data.page.total
                this.tableLoading = false
            })
        },
        handleUpdate(index, row) {
            this.$router.push({
                path:
                    '/new-warehousing/new-warehousing?warehousingNo=' +
                    row.warehousingNo,
            })
        },
        statusUpdate(index, row) {
            this.$confirm('您确定要提交该订单？', '提示', confirm)
                .then(() => {
                    request({
                        url: 'warehousing/status',
                        method: 'post',
                        data: {
                            to: '2',
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
            this.$confirm('您确定要暂存该入库单？', '提示', confirm)
                .then(() => {
                    request({
                        url: 'ord/update/3/' + warehousingNo + '/2',
                        method: 'post',
                        data: {
                            to,
                        },
                    }).then(res => {
                        console.log(res)
                        this.fetchData()
                        this.$message.success('提交成功')
                    })
                })
                .catch(() => {
                    this.$message.info('已取消提交')
                })
        },
        handleDelete(index, row) {
            this.$confirm('您确定要删除该入库单？', '提示', confirm)
                .then(() => {
                    request({
                        url:
                            'warehousing/drop?warehousingNo=' +
                            row.warehousingNo,
                        method: 'get',
                    }).then(res => {
                        this.fetchData()
                        this.$message.success('提交成功')
                    })
                })
                .catch(() => {
                    this.$message.info('已取消')
                })
        },
        handleSizeChange(val) {
            this.tablePage.size = val
            this.fetchData()
        },
        handleCurrentChange(val) {
            this.tablePage.current = val
            this.fetchData()
        },
        channelLink(index, row) {
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
    },
}
</script>
