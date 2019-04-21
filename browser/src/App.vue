<template>
  <div id="app" style="overflow-x: hidden;">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    provide() {
      return {
        reload: this.reload,
      };
    },
    data() {
      return {
        isRouterAlive: true,
      };
    },
    methods: {
      reload() {
        console.log('reloading');
        this.isRouterAlive = false;
        this.$nextTick(function() {
          this.isRouterAlive = true;
        });
        console.log('reload END');
      },
    },

  };
</script>
