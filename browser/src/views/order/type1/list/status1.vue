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
    name: 'order-list-mgt-type1-status1',
    components: { OrderType1 },
    data() {
        return {
            msg: {
                category: '1',
                status: '1',
                statusTo: '2',
                statusAbandon: '5',
                buttonVisible1: true,
                buttonVisible2: true,
                buttonVisible3: true,
                buttonVisible4: true,
                buttonVisible5: true,
            },
        }
    },
}
</script>
