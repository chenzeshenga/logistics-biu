<template>
  <div class="login-container">
    <div class="app-container">
      <shelf-table v-bind:msg="this.msg"></shelf-table>
    </div>
  </div>
</template>

<script>
import ShelfTable from "../../../components/dy/shelf/index";

export default {
  name: "shelf",
  components: { ShelfTable },
  data() {
    return {
      msg: {}
    };
  }
};
</script>

<style>
</style>