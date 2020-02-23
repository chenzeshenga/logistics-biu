<!--todo 下拉框组件化-->
<!--refen https://www.cnblogs.com/sunshine-wy/p/11064761.html-->

<!--<template>-->
<!--  <el-col :span="12" style="margin-top: 1%">-->
<!--    <el-form-item label="道/府/县-城市-乡">-->
<!--      <el-select v-model="form.fromKenId" filterable-->
<!--                 clearable placeholder="请选择:道/府/县" :span="4"-->
<!--                 @change="getFromCityAddress()">-->
<!--        <el-option v-for="item in fromKenAddress"-->
<!--                   :key="item.value"-->
<!--                   :label="item.label"-->
<!--                   :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-select v-model="form.fromCityId" filterable-->
<!--                 clearable placeholder="请选择:城市" :span="4"-->
<!--                 @change="getFromTownAddress">-->
<!--        <el-option v-for="item in fromCityAddress"-->
<!--                   :key="item.value"-->
<!--                   :label="item.label"-->
<!--                   :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-select v-model="form.fromTownId" filterable-->
<!--                 clearable placeholder="请选择:乡" :span="4"-->
<!--                 @change="fillFromZipCode"-->
<!--      >-->
<!--        <el-option v-for="item in fromTownAddress"-->
<!--                   :key="item.value"-->
<!--                   :label="item.label"-->
<!--                   :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--    </el-form-item>-->
<!--  </el-col>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: 'japan-address-option',-->
<!--  data() {-->
<!--    return {-->
<!--      msgData: {-->
<!--        initKen-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  props: ['msg'],-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
