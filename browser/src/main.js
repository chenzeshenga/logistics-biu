import Vue from 'vue';

import 'normalize.css/normalize.css'; // A modern alternative to CSS resets

import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

import '@/styles/index.scss'; // global css

import App from './App';
import router from './router';
import store from './store';

import './icons'; // icon
import './errorLog'; // error log
import './permission'; // permission control
import * as filters from './filters'; // global filters

// 权限指令
import hasPerm from '@/directive/permission/hasPerm';
import perm from '@/directive/permission/perm';
// 注册全局的权限判断方法和指令
Vue.prototype.$hasPerm = hasPerm;
Vue.directive('perm', perm);

Vue.use(Element, {
  size: 'medium', // set element-ui default size
});
Vue.use(Loading);

// register global utility filters.
Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key]);
});

Vue.config.productionTip = false;

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App},
});
