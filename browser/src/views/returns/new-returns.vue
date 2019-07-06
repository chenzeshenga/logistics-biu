<template>
    <div class="login-container">
        <div class="app-container">
            <el-form :ref="form" :model="form" label-width="120px">
                <el-form-item label="退货单所属用户" v-if="adminRole">
                    <el-col :span="12">
                        <el-form-item label="所属用户">
                            <el-tooltip content="所属用户" placement="top">
                                <el-select
                                    filterable
                                    clearable
                                    v-model="form.creator"
                                    placeholder="请选择所属用户"
                                    @change="filterOrderContent"
                                >
                                    <el-option
                                        v-for="creator in users"
                                        :key="creator.uname"
                                        :label="creator.nick"
                                        :value="creator.uname"
                                    ></el-option>
                                </el-select>
                            </el-tooltip>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否对应订单号">
                            <el-switch
                                v-model="form.withoutOrderNoFlag"
                                active-color="#13ce66"
                                inactive-color="#ff4949"
                                @change="handleOrderNoShow"
                            >
                            </el-switch>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="订单号" v-if="showFlag">
                    <el-form-item label="">
                        <el-select
                            filterable
                            clearable
                            v-model="form.orderNo"
                            placeholder="请选择订单号"
                        >
                            <el-option
                                v-for="order in ordersOption"
                                :key="order"
                                :label="order"
                                :value="order"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import request from '../../utils/service'

export default {
    name: 'new-returns',
    data() {
        return {
            adminRole: false,
            showFlag: false,
            users: [],
            orders: {},
            ordersOption: [],
            form: {
                creator: '',
                withoutOrderNoFlag: false,
            },
        }
    },
    created() {
        this.hasAdminRole()
        this.initUserList()
    },
    inject: ['reload'],
    watch: {
        $route() {
            this.initPage()
        },
    },
    methods: {
        initUserList() {
            request({
                url: '/sys_user/query4Option',
                method: 'post',
                data: {
                    current: null,
                    size: 'all',
                },
            }).then(res => {
                this.users = res.data.page.records
            })
        },
        hasAdminRole() {
            request({
                url: '/sys_user/info',
                method: 'get',
            }).then(res => {
                const roles = res.data.userInfo.roles
                for (let i = 0; i < roles.length; i++) {
                    const role = roles[i]
                    const val = role['val']
                    if (val === 'root' || val === 'operator') {
                        this.adminRole = true
                    }
                }
            })
        },
        handleOrderNoShow(val) {
            this.form.withoutOrderNoFlag = val
            if (this.orders.length > 0 && this.form.withoutOrderNoFlag) {
                this.showFlag = true
            }
        },
        filterOrderContent(val) {
            request({
                url: '/ord/fetchOrderNo',
                method: 'post',
                data: this.form,
            }).then(res => {
                const backEndOrder = res.data
                for (let i = 0; i < backEndOrder.length; i++) {
                    const subOrder = backEndOrder[i]
                    this.orders[subOrder.orderNo] = subOrder
                    this.ordersOption.push(subOrder.orderNo)
                }
            })
            if (this.orders.length > 0 && this.form.withoutOrderNoFlag) {
                this.showFlag = true
            }
        },
    },
}
</script>
