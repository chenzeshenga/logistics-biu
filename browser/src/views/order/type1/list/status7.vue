<template>
    <div class="login-container">
        <order-type1 v-bind:msg="this.msg"></order-type1>
    </div>
</template>

<script>
import OrderType1 from '../../../../components/dy/orderTable/Type1/index'
export default {
    name: 'order-list-mgt-type1-status7',
    components: { OrderType1 },
    data() {
        return {
            msg: {
                category: '1',
                status: '7',
            },
        }
    },
}
</script>
