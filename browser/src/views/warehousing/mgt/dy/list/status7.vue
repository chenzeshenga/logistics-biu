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
    name: 'warehousing-mgt-dy-list-status7',
    components: { WarehousingTable },
    data() {
        return {
            msg: {
                status: '7',
                buttonVisible1: false,
                buttonVisible2: true,
                buttonVisible3: true,
                buttonVisible4: true,
                buttonVisible5: true,
                buttonVisible6: true,
                buttonVisible7: true,
                buttonVisible8: false,
                buttonVisible9: false,
                buttonVisibleA: false,
                buttonVisibleB: false,
                buttonVisibleC: false,
            },
        }
    },
}
</script>
