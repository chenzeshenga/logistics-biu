import Vue from 'vue'
import Router from 'vue-router'

const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/** note: submenu only apppear when children.length>=1
 *   detail see  https://panjiachen.github.io/vue-element-admin-site/#/router-and-nav?id=sidebar
 **/

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
 **/
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/authredirect', component: _import('login/authredirect'), hidden: true},
  {path: '/404', component: _import('errorPage/404'), hidden: true},
  {path: '/401', component: _import('errorPage/401'), hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index'),
      name: 'dashboard',
      meta: {title: '东岳', icon: 'dashboard', noCache: true}
    }]
  },
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

export const asyncRouterMap = [

  {
    path: '/system',
    component: Layout,
    meta: {perm: 'm:sys', title: '系统', icon: 'chart'},
    children: [
      {
        path: 'user_manage',
        name: 'user_manage',
        component: _import('_system/user/index'),
        meta: {perm: 'm:sys:user', title: '用户管理', icon: 'chart', noCache: true}
      },
      {
        path: 'role_manage',
        name: 'role_manage',
        component: _import('_system/role/index'),
        meta: {perm: 'm:sys:role', title: '角色管理', icon: 'chart', noCache: true},
      },
      {
        hidden: true,
        path: 'role_manage/:roleId/assign_perm',
        name: 'role_manage_assign_perm',
        component: _import('_system/role/assign_perm'),
        meta: {hiddenTag: true, title: '角色授权'},
      },
      {
        path: 'perm_manage',
        name: 'perm_manage',
        component: _import('_system/perm/index'),
        meta: {perm: 'm:sys:perm', title: '权限管理', icon: 'chart', noCache: true}

      },
    ]
  },
  {
    path: '/new-order',
    component: Layout,
    children: [{
      path: 'index',
      name: 'menu1',
      component: _import('menu/new-order'),
      meta: {perm: 'm:menu1', title: '新建订单', icon: 'icon'}
    }]
  },
  {
    path: "/order-list/mgt",
    name: 'order-list-mgt',
    component: Layout,
    meta: {
      perm: 'm:order:list',
      title: '订单管理',
      icon: 'chart'
    },
    children: [
      {
        path: '/order-list/mgt/type1',
        name: 'order-list-mgt-type1',
        component: _import('order/type1/index'),
        meta: {
          perm: 'm:order:list',
          title: '海外仓代发',
          icon: 'chart'
        },
        children: [
          {
            path: 'pickup',
            component: _import('order/type1/list/pickup'),
            name: 'order-list-mgt-type1-pickup',
            meta: {perm: 'm:order:list', title: '拣货', icon: 'chart', noCache: true}
          },
          {
            path: 'status1',
            component: _import('order/type1/list/status1'),
            name: 'order-list-mgt-type1-status1',
            meta: {perm: 'm:order:list', title: '待审核', icon: 'chart', noCache: true}
          },
          {
            path: 'status2',
            component: _import('order/type1/list/status2'),
            name: 'status2',
            meta: {perm: 'm:order:list', title: '待发货', icon: 'chart', noCache: true}
          },
          {
            path: 'status3',
            component: _import('order/type1/list/status3'),
            name: 'status3',
            meta: {perm: 'm:order:list', title: '已发货', icon: 'chart', noCache: true}
          },
          {
            path: 'status4',
            component: _import('order/type1/list/status4'),
            name: 'status4',
            meta: {perm: 'm:order:list', title: '问题件', icon: 'chart', noCache: true}
          },
          {
            path: 'status5',
            component: _import('order/type1/list/status5'),
            name: 'status5',
            meta: {perm: 'm:order:list', title: '已废弃', icon: 'chart', noCache: true}
          },
          {
            path: 'status6',
            component: _import('order/type1/list/status6'),
            name: 'status6',
            meta: {perm: 'm:order:list', title: '暂存', icon: 'chart', noCache: true}
          },
          {
            path: 'status7',
            component: _import('order/type1/list/status7'),
            name: 'status7',
            meta: {perm: 'm:order:list', title: '历史', icon: 'chart', noCache: true}
          }]
      }
    ]
  },
  {
    path: '/menu2',
    component: Layout,
    children: [{
      path: 'index',
      name: 'menu2',
      component: _import('menu/menu2'),
      meta: {perm: 'm:menu2', title: '菜单2', icon: 'icon'}
    }]
  },

  {
    path: '/menu3',
    component: Layout,
    meta: {
      perm: 'm:menu3',
      title: '菜单3',
      icon: 'chart'
    },
    children: [
      {path: 'menu3_1', component: _import('menu/menu3_1'), name: 'menu3_1', meta: {perm: 'm:menu3:1', title: '菜单3-1', icon: 'chart', noCache: true}},
      {path: 'menu3_2', component: _import('menu/menu3_2'), name: 'menu3_2', meta: {perm: 'm:menu3:2', title: '菜单3-2', icon: 'chart', noCache: true}},
      {path: 'menu3_3', component: _import('menu/menu3_3'), name: 'menu3_3', meta: {perm: 'm:menu3:3', title: '菜单3-3', icon: 'chart', noCache: true}}
    ]
  },
  // {
  //   path: '/menu5',
  //   name: 'menu5',
  //   component: Layout,
  //   redirect: '/menu5/menu5_1/a',
  //   meta: {
  //     perm: 'm:menu5',
  //     title: '新建订单',
  //     icon: 'example'
  //   },
  //   children: [
  //     {
  //       path: '/menu4/menu4_1',
  //       name: 'menu4_1',
  //       component: _import('menu/menu4_1/index'),
  //       redirect: '/menu4/menu4_1/a',
  //       meta: {
  //         perm: 'm:menu4:1',
  //         title: '海外仓代发',
  //         icon: 'table'
  //       },
  //       children: [
  //         {path: 'a', name: 'menu4_1_a', component: _import('menu/menu4_1/a'), meta: {perm: 'm:menu4:1:a', title: '菜单4-1-a'}},
  //         {path: 'b', name: 'menu4_1_b', component: _import('menu/menu4_1/b'), meta: {perm: 'm:menu4:1:b', title: '菜单4-1-b'}},
  //         {path: 'c', name: 'menu4_1_c', component: _import('menu/menu4_1/c'), meta: {perm: 'm:menu4:1:c', title: '菜单4-1-c'}}
  //       ]
  //     },
  //     {path: 'menu4/menu4_2', name: 'menu4_2', icon: 'tab', component: _import('menu/menu4_2/index'), meta: {perm: 'm:menu4:2', title: '菜单4-2'}}
  //   ]
  // },
  {
    path: '/menu4',
    name: 'menu4',
    component: Layout,
    redirect: '/menu4/menu4_1/a',
    meta: {
      perm: 'm:menu4',
      title: '订单管理',
      icon: 'example'
    },
    children: [
      {
        path: '/menu4/menu4_1',
        name: 'menu4_1',
        component: _import('menu/menu4_1/index'),
        redirect: '/menu4/menu4_1/a',
        meta: {
          perm: 'm:menu4:1',
          title: '海外仓代发',
          icon: 'table'
        },
        children: [
          {path: 'a', name: 'menu4_1_a', component: _import('menu/menu4_1/a'), meta: {perm: 'm:menu4:1:a', title: '菜单4-1-a'}},
          {path: 'b', name: 'menu4_1_b', component: _import('menu/menu4_1/b'), meta: {perm: 'm:menu4:1:b', title: '菜单4-1-b'}},
          {path: 'c', name: 'menu4_1_c', component: _import('menu/menu4_1/c'), meta: {perm: 'm:menu4:1:c', title: '菜单4-1-c'}}
        ]
      },
      {path: 'menu4/menu4_2', name: 'menu4_2', icon: 'tab', component: _import('menu/menu4_2/index'), meta: {perm: 'm:menu4:2', title: '菜单4-2'}}
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
