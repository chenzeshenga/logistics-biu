<template>
    <div class="login-container">
        <div class="app-container">
            <order-type2 v-bind:msg="this.msg"></order-type2>
        </div>
    </div>
</template>

<script>
import OrderType2 from '../../../../components/dy/orderTable/Type2/index'

export default {
    name: 'order-list-mgt-type2-status1',
    components: { OrderType2 },
    data() {
        return {
            msg: {},
        }
    },
}
</script>
