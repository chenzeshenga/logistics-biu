<template>
    <div class="login-container">
        <div class="app-container">
            <warehousing-table v-bind:msg="this.msg"></warehousing-table>
        </div>
    </div>
</template>

<script>
import WarehousingTable from '../../../../../components/dy/WarehousingTable/index'

export default {
    name: 'warehousing-mgt-dy-list-status9',
    components: { WarehousingTable },
    data() {
        return {
            msg: {
                status: '9',
                statusUpdateTo: '1',
                statusHoldTo: '8',
            },
        }
    },
}
</script>
