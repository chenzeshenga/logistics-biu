<template>
    <div class="login-container">
        <div class="app-container">
            <order-type1 v-bind:msg="this.msg"></order-type1>
        </div>
    </div>
</template>

<script>
import OrderType1 from '../../../../components/dy/orderTable/Type1/index'

export default {
    name: 'order-list-mgt-type1-status4',
    components: { OrderType1 },
    data() {
        return {
            msg: {
                category: '1',
                status: '4',
                statusTo: '1',
                statusAbandon: '5',
                buttonVisible5: true,
                buttonVisible9: true,
            },
        }
    },
}
</script>
