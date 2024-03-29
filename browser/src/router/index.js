import Vue from 'vue';
import Router from 'vue-router';
import Layout from '../views/layout/Layout';

const _import = require('./_import_' + process.env.NODE_ENV);

Vue.use(Router);
/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
 **/
export const constantRouterMap = [
  {
    path: '/login',
    component: _import('login/index'),
    hidden: true,
  },
  {
    path: '/intro',
    component: _import('intro/intro'),
  },
  {
    path: '/order-info',
    component: _import('showPage/order-info'),
    hidden: true,
  },
  {
    path: '/authredirect',
    component: _import('login/authredirect'),
    hidden: true,
  },
  {
    path: '/404',
    component: _import('errorPage/404'),
    hidden: true,
  },
  {
    path: '/401',
    component: _import('errorPage/401'),
    hidden: true,
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: _import('dashboard/index'),
        name: 'dashboard',
        meta: {
          title: '东岳',
          icon: 'dashboard',
          noCache: true,
        },
      },
    ],
  },
];

export default new Router({
  scrollBehavior: () => ({
    y: 0,
  }),
  routes: constantRouterMap,
});

export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    meta: {
      perm: 'm:sys',
      title: '系统',
      icon: 'chart',
    },
    children: [
      {
        path: 'user_manage',
        name: 'user_manage',
        component: _import('_system/user/index'),
        meta: {
          perm: 'm:sys:user',
          title: '用户管理',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'role_manage',
        name: 'role_manage',
        component: _import('_system/role/index'),
        meta: {
          perm: 'm:sys:role',
          title: '角色管理',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        hidden: true,
        path: 'role_manage/:roleId/assign_perm',
        name: 'role_manage_assign_perm',
        component: _import('_system/role/assign_perm'),
        meta: {
          hiddenTag: true,
          title: '角色授权',
        },
      },
      {
        path: 'perm_manage',
        name: 'perm_manage',
        component: _import('_system/perm/index'),
        meta: {
          perm: 'm:sys:perm',
          title: '权限管理',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'trackno',
        name: 'trackno',
        component: _import('_system/config/trackno'),
        meta: {
          perm: 'm:config:list:a',
          title: '追踪单号维护',
          icon: 'chart',
        },
      },
      {
        path: 'channel',
        name: 'channel',
        component: _import('_system/config/channel'),
        meta: {
          perm: 'm:config:list:b',
          title: '渠道配置',
          icon: 'chart',
        },
      },
      {
        path: 'properties',
        name: 'properties',
        component: _import('_system/config/properties'),
        meta: {
          perm: 'm:config:list:b1',
          title: '参数配置',
          icon: 'chart',
        },
      },
      {
        path: 'costOnPerVolume',
        name: 'costOnPerVolume',
        component: _import('_system/config/costOnPerVolume'),
        meta: {
          perm: 'm:config:list:b2',
          title: '用户单位体积费用配置',
          icon: 'chart',
        },
      },
      {
        path: 'template',
        name: 'fileTemplate',
        component: _import('_system/config/template'),
        meta: {
          perm: 'm:config:list:c',
          title: '模版查看',
          icon: 'chart',
        },
      },
      {
        path: 'shelf',
        name: 'system-config-shelf',
        component: _import('_system/config/shelf'),
        meta: {
          perm: 'm:config:list:shelf',
          title: '货架设置',
          icon: 'chart',
        },
      },
      {
        path: 'dyProfile',
        name: 'dyProfile',
        component: _import('_system/config/dyProfile'),
        meta: {
          perm: 'm:profile:dy',
          title: '东岳资料',
          icon: 'chart',
        },
      },
      {
        path: 'amazonStore',
        name: 'amazonStore',
        component: _import('_system/config/amazonStore'),
        meta: {
          perm: 'm:amazon:store',
          title: '亚马逊店铺维护',
          icon: 'chart',
        },
      },
      {
        path: 'about',
        name: 'about',
        component: _import('_system/config/about'),
        meta: {
          perm: 'm:sys:about',
          title: '关于',
          icon: 'chart',
        },
      },
    ],
  },
  {
    path: '/new-order',
    component: Layout,
    children: [
      {
        path: 'new-order',
        name: 'new-order',
        component: _import('menu/new-order'),
        meta: {
          perm: 'm:order',
          title: '新建订单',
          icon: 'icon',
        },
      },
    ],
  },
  {
    path: '/new-returns',
    component: Layout,
    children: [
      {
        path: 'new-returns',
        name: 'new-returns',
        component: _import('returns/new-returns'),
        meta: {
          perm: 'm:returns',
          title: '新建退货单',
          icon: 'icon',
        },
      },
    ],
  },
  {
    path: '/new-product',
    component: Layout,
    children: [
      {
        path: 'new-product',
        name: 'new-product',
        component: _import('product/new-product'),
        meta: {
          perm: 'm:product',
          title: '新建商品',
          icon: 'icon',
        },
      },
    ],
  },
  {
    path: '/new-warehousing',
    component: Layout,
    children: [
      {
        path: 'new-warehousing',
        name: 'new-warehousing',
        component: _import('warehousing/new-warehousing'),
        meta: {
          perm: 'm:warehousing',
          title: '新建入库单',
          icon: 'icon',
        },
      },
    ],
  },
  {
    path: '/warehousing/upshelf',
    component: Layout,
    children: [
      {
        path: 'upshelf',
        name: 'upshelf',
        component: _import('warehousing/upshelf'),
        meta: {
          perm: 'm:warehousing:upshelf',
          title: '上架清点',
          icon: 'icon',
        },
      },
    ],
  },
  {
    path: '/order-list/mgt',
    name: 'order-list-mgt',
    component: Layout,
    meta: {
      perm: 'm:order:list',
      title: '订单管理',
      icon: 'chart',
    },
    children: [
      {
        path: '/order-list/mgt/type1',
        name: 'order-list-mgt-type1',
        component: _import('order/type1/index'),
        meta: {
          perm: 'm:order1:list',
          title: '海外仓代发',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('order/type1/list/status1'),
            name: 'order-list-mgt-type1-status1',
            meta: {
              perm: 'm:order1:list:2',
              title: '待审核',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('order/type1/list/status2'),
            name: 'status2',
            meta: {
              perm: 'm:order1:list:3',
              title: '待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status2',
            component: _import('order/type1/list/status2-user'),
            name: 'user-status2',
            meta: {
              perm: 'm:order1:list:user:status2',
              title: '待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('order/type1/list/status3'),
            name: 'status3',
            meta: {
              perm: 'm:order1:list:4',
              title: '[管理]费用确认',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status3',
            component: _import('order/type1/list/status3-user'),
            name: 'user-status3',
            meta: {
              perm: 'm:order1:list:user:status3',
              title: '费用确认',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('order/type1/list/status4'),
            name: 'status4',
            meta: {
              perm: 'm:order1:list:5',
              title: '问题件',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status5',
            component: _import('order/type1/list/status5'),
            name: 'status5',
            meta: {
              perm: 'm:order1:list:6',
              title: '已废弃',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status6',
            component: _import('order/type1/list/status6'),
            name: 'status6',
            meta: {
              perm: 'm:order1:list:7',
              title: '暂存',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status7',
            component: _import('order/type1/list/status7'),
            name: 'status7',
            meta: {
              perm: 'm:order1:list:8',
              title: '历史',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'pickup',
            component: _import('order/type1/list/pickup'),
            name: 'order-list-mgt-type1-pickup',
            meta: {
              perm: 'm:order1:list:pickup:1',
              title: '拣货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'pickup-scan',
            component: _import('order/type1/list/pickup-scan'),
            name: 'order-list-mgt-type1-pickup-scan',
            meta: {
              perm: 'm:order1:list:pickup:2',
              title: '拣货-扫描',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'pickup-info',
            component: _import('order/type1/list/pickup-info'),
            name: 'order-list-mgt-type1-info-scan',
            meta: {
              perm: 'm:order1:list:pickup:3',
              title: '拣货-信息',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
      {
        path: '/order-list/mgt/type2',
        name: 'order-list-mgt-type2',
        component: _import('order/type2/index'),
        meta: {
          perm: 'm:order2:list',
          title: '单票单请',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('order/type2/list/status1'),
            name: 'order-list-mgt-type2-status1',
            meta: {
              perm: 'm:order2:list:1',
              title: '待审核',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('order/type2/list/status2'),
            name: 'order-list-mgt-type2-status2',
            meta: {
              perm: 'm:order2:list:2',
              title: '国内待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status2',
            component: _import('order/type2/list/status2-user'),
            name: 'order-list-mgt-type2-status2-user',
            meta: {
              perm: 'm:order2:list:user:2',
              title: '国内待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('order/type2/list/status3'),
            name: 'order-list-mgt-type2-status3',
            meta: {
              perm: 'm:order2:list:3',
              title: '国内已发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status3',
            component: _import('order/type2/list/status3-user'),
            name: 'order-list-mgt-type2-status3-user',
            meta: {
              perm: 'm:order2:list:user:3',
              title: '国内已发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('order/type2/list/status4'),
            name: 'order-list-mgt-type2-status4',
            meta: {
              perm: 'm:order2:list:4',
              title: '问题件',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status5',
            component: _import('order/type2/list/status5'),
            name: 'order-list-mgt-type2-status5',
            meta: {
              perm: 'm:order2:list:5',
              title: '已废弃',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status6',
            component: _import('order/type2/list/status6'),
            name: 'order-list-mgt-type2-status6',
            meta: {
              perm: 'm:order2:list:6',
              title: '暂存',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status7',
            component: _import('order/type2/list/status7'),
            name: 'order-list-mgt-type2-status7',
            meta: {
              perm: 'm:order2:list:7',
              title: '历史',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
      {
        path: '/order-list/mgt/type3',
        name: 'order-list-mgt-type3',
        component: _import('order/type3/index'),
        meta: {
          perm: 'm:order3:list',
          title: '虚拟海外仓',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('order/type3/list/status1'),
            name: 'order-list-mgt-type3-status1',
            meta: {
              perm: 'm:order3:list:1',
              title: '待审核',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('order/type3/list/status2'),
            name: 'order-list-mgt-type3-status2',
            meta: {
              perm: 'm:order3:list:2',
              title: '待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status2',
            component: _import('order/type3/list/status2-user'),
            name: 'order-list-mgt-type3-status2-user',
            meta: {
              perm: 'm:order3:list:user:2',
              title: '待发货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('order/type3/list/status3'),
            name: 'order-list-mgt-type3-status3',
            meta: {
              perm: 'm:order3:list:3',
              title: '已打印',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status3',
            component: _import('order/type3/list/status3-user'),
            name: 'order-list-mgt-type3-status3-user',
            meta: {
              perm: 'm:order3:list:user:3',
              title: '已打印',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('order/type3/list/status4'),
            name: 'order-list-mgt-type3-status4',
            meta: {
              perm: 'm:order3:list:6',
              title: '已打包',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status4',
            component: _import('order/type3/list/status4-user'),
            name: 'order-list-mgt-type3-status4-user',
            meta: {
              perm: 'm:order3:list:user:4',
              title: '已打包',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status5',
            component: _import('order/type3/list/status5'),
            name: 'order-list-mgt-type3-status5',
            meta: {
              perm: 'm:order3:list:7',
              title: '已完成',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status5',
            component: _import('order/type3/list/status5-user'),
            name: 'order-list-mgt-type3-status5-user',
            meta: {
              perm: 'm:order3:list:user:5',
              title: '已完成',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status6',
            component: _import('order/type3/list/status6'),
            name: 'order-list-mgt-type3-status6',
            meta: {
              perm: 'm:order3:list:8',
              title: '暂存',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status8',
            component: _import('order/type3/list/status8'),
            name: 'order-list-mgt-type3-status8',
            meta: {
              perm: 'm:order3:list:4',
              title: '异常',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status7',
            component: _import('order/type3/list/status7'),
            name: 'order-list-mgt-type3-status7',
            meta: {
              perm: 'm:order3:list:9',
              title: '历史',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
    ],
  },
  {
    path: '/returns-list/mgt',
    name: 'returns-list-mgt',
    component: Layout,
    meta: {
      perm: 'm:returns:list',
      title: '退货单管理',
      icon: 'chart',
    },
    children: [
      {
        path: '/returns-list/mgt/withoutUser',
        name: 'returns-list-mgt-withoutUser',
        component: _import('returns/withoutUser/index'),
        meta: {
          perm: 'm:returns1:list',
          title: '无主退货单',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('returns/withoutUser/list/status1'),
            name: 'returns-list-mgt-withoutUser-status1',
            meta: {
              perm: 'm:returns1:list:2',
              title: '待认领退货单',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('returns/withoutUser/list/status2'),
            name: 'returns-list-mgt-withoutUser-status2',
            meta: {
              perm: 'm:returns1:list:3',
              title: '历史待认领退货单',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
      {
        path: '/returns-list/mgt/withUser',
        name: 'returns-list-mgt-withUser',
        component: _import('returns/withUser/index'),
        meta: {
          perm: 'm:returns2:list',
          title: '有主退货单',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('returns/withUser/list/status1'),
            name: 'returns-list-mgt-withUser-status1',
            meta: {
              perm: 'm:returns2:list:1',
              title: '退货单处理',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('returns/withUser/list/status2'),
            name: 'returns-list-mgt-withUser-status2',
            meta: {
              perm: 'm:returns2:list:2',
              title: '收货处理',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('returns/withUser/list/status4'),
            name: 'returns-list-mgt-withUser-status4',
            meta: {
              perm: 'm:returns2:list:4',
              title: '[用户]收货处理确认',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('returns/withUser/list/status3'),
            name: 'returns-list-mgt-withUser-status3',
            meta: {
              perm: 'm:returns2:list:3',
              title: '历史',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
    ],
  },
  {
    path: '/warehousing-list/mgt',
    name: 'warehousing-list-mgt',
    component: Layout,
    meta: {
      perm: 'm:warehousing:list',
      title: '入库单管理',
      icon: 'chart',
    },
    children: [
      {
        path: '/warehousing/mgt/dy',
        name: 'warehousing-mgt-dy',
        component: _import('warehousing/mgt/dy/index'),
        meta: {
          perm: 'm:warehousing:dy:list',
          title: '东岳入库单',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('warehousing/mgt/dy/list/status1'),
            name: 'warehousing-mgt-dy-list-status1',
            meta: {
              perm: 'm:warehousing:dy:list:1',
              title: '新建',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('warehousing/mgt/dy/list/status2'),
            name: 'warehousing-mgt-dy-list-status2',
            meta: {
              perm: 'm:warehousing:dy:list:2',
              title: '前置海外仓',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status2',
            component: _import('warehousing/mgt/dy/list/status2-user'),
            name: 'warehousing-mgt-dy-list-status2-user',
            meta: {
              perm: 'm:warehousing:dy:list:user:2',
              title: '前置海外仓',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('warehousing/mgt/dy/list/status3'),
            name: 'warehousing-mgt-dy-list-status3',
            meta: {
              perm: 'm:warehousing:dy:list:3',
              title: '头程已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status3',
            component: _import('warehousing/mgt/dy/list/status3-user'),
            name: 'warehousing-mgt-dy-list-status3-user',
            meta: {
              perm: 'm:warehousing:dy:list:user:3',
              title: '头程已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('warehousing/mgt/dy/list/status4'),
            name: 'warehousing-mgt-dy-list-status4',
            meta: {
              perm: 'm:warehousing:dy:list:4',
              title: '日仓已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status4',
            component: _import('warehousing/mgt/dy/list/status4-user'),
            name: 'warehousing-mgt-dy-list-status4-user',
            meta: {
              perm: 'm:warehousing:dy:list:user:4',
              title: '日仓已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status5',
            component: _import('warehousing/mgt/dy/list/status5'),
            name: 'warehousing-mgt-dy-list-status5',
            meta: {
              perm: 'm:warehousing:dy:list:5',
              title: '入库清点',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status5',
            component: _import('warehousing/mgt/dy/list/status5-user'),
            name: 'warehousing-mgt-dy-list-status5-user',
            meta: {
              perm: 'm:warehousing:dy:list:user:5',
              title: '入库清点',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status6',
            component: _import('warehousing/mgt/dy/list/status6'),
            name: 'warehousing-mgt-dy-list-status6',
            meta: {
              perm: 'm:warehousing:dy:list:6',
              title: '待上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status6',
            component: _import('warehousing/mgt/dy/list/status6-user'),
            name: 'warehousing-mgt-dy-list-status6-user',
            meta: {
              perm: 'm:warehousing:dy:list:user:6',
              title: '待上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status7',
            component: _import('warehousing/mgt/dy/list/status7'),
            name: 'warehousing-mgt-dy-list-status7',
            meta: {
              perm: 'm:warehousing:dy:list:7',
              title: '已上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status88',
            component: _import('warehousing/mgt/dy/list/status88'),
            name: 'warehousing-mgt-dy-list-status88',
            meta: {
              perm: 'm:warehousing:dy:list:88',
              title: '废弃',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status99',
            component: _import('warehousing/mgt/dy/list/status99'),
            name: 'warehousing-mgt-dy-list-status99',
            meta: {
              perm: 'm:warehousing:dy:list:99',
              title: '异常',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
      {
        path: '/warehousing/mgt/other',
        name: 'warehousing-mgt-other',
        component: _import('warehousing/mgt/other/index'),
        meta: {
          perm: 'm:warehousing:other:list',
          title: '其他入库单',
          icon: 'chart',
        },
        children: [
          {
            path: 'status1',
            component: _import('warehousing/mgt/other/list/status1'),
            name: 'warehousing-mgt-other-list-status1',
            meta: {
              perm: 'm:warehousing:other:list:1',
              title: '新建',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status2',
            component: _import('warehousing/mgt/other/list/status2'),
            name: 'warehousing-mgt-other-list-status2',
            meta: {
              perm: 'm:warehousing:other:list:2',
              title: '已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status2',
            component: _import('warehousing/mgt/other/list/status2-user'),
            name: 'warehousing-mgt-other-list-status2-user',
            meta: {
              perm: 'm:warehousing:other:list:user:2',
              title: '已收货',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status3',
            component: _import('warehousing/mgt/other/list/status3'),
            name: 'warehousing-mgt-other-list-status3',
            meta: {
              perm: 'm:warehousing:other:list:3',
              title: '入库清点',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status3',
            component: _import('warehousing/mgt/other/list/status3-user'),
            name: 'warehousing-mgt-other-list-status3-user',
            meta: {
              perm: 'm:warehousing:other:list:user:3',
              title: '入库清点',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status4',
            component: _import('warehousing/mgt/other/list/status4'),
            name: 'warehousing-mgt-other-list-status4',
            meta: {
              perm: 'm:warehousing:other:list:4',
              title: '待上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'user-status4',
            component: _import('warehousing/mgt/other/list/status4-user'),
            name: 'warehousing-mgt-other-list-status4-user',
            meta: {
              perm: 'm:warehousing:other:list:user:4',
              title: '待上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status5',
            component: _import('warehousing/mgt/other/list/status5'),
            name: 'warehousing-mgt-other-list-status5',
            meta: {
              perm: 'm:warehousing:other:list:5',
              title: '已上架',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status88',
            component: _import('warehousing/mgt/other/list/status88'),
            name: 'warehousing-mgt-other-list-status88',
            meta: {
              perm: 'm:warehousing:other:list:88',
              title: '废弃',
              icon: 'chart',
              noCache: true,
            },
          },
          {
            path: 'status99',
            component: _import('warehousing/mgt/other/list/status99'),
            name: 'warehousing-mgt-other-list-status99',
            meta: {
              perm: 'm:warehousing:other:list:99',
              title: '异常',
              icon: 'chart',
              noCache: true,
            },
          },
        ],
      },
    ],
  },
  {
    path: '/product',
    component: Layout,
    meta: {
      perm: 'm:product:mgt',
      title: '商品管理',
      icon: 'chart',
    },
    children: [
      {
        path: 'status4user',
        name: 'status4user',
        component: _import('product/mgt/status4user'),
        meta: {
          perm: 'm:product:mgt:status4user',
          title: '待审核商品',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'status0',
        name: 'status0',
        component: _import('product/mgt/status0'),
        meta: {
          perm: 'm:product:mgt:1',
          title: '待审核商品-管理',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'status1',
        name: 'status1',
        component: _import('product/mgt/status1'),
        meta: {
          perm: 'm:product:mgt:2',
          title: '在库商品',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'product-statistic',
        name: 'product-statistic',
        component: _import('product/product-statistic'),
        meta: {
          perm: 'm:product:mgt:3',
          title: '商品当前库存费用',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'product-statistic-history',
        name: 'product-statistic-history',
        component: _import('product/product-statistic-history'),
        meta: {
          perm: 'm:product:mgt:3:his',
          title: '商品历史库存费用',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'product-in-warehouse',
        name: 'product-in-warehouse',
        component: _import('product/product-in-warehouse'),
        meta: {
          perm: 'm:product:mgt:4',
          title: '在库商品-统计',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'product-out-warehouse-records',
        name: 'product-out-warehouse-records',
        component: _import('product/product-out-warehouse-records'),
        meta: {
          perm: 'm:product:mgt:5',
          title: '商品出库记录',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'product-in-warehouse-records',
        name: 'product-in-warehouse-records',
        component: _import('product/product-in-warehouse-records'),
        meta: {
          perm: 'm:product:mgt:6',
          title: '商品入库记录',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'user-volume-weight',
        name: 'user-volume-weight',
        component: _import('product/user-volume-weight'),
        meta: {
          perm: 'm:product:mgt:7',
          title: '用户体积重量',
          icon: 'chart',
          noCache: true,
        },
      },
    ],
  },
  {
    path: '/finance',
    component: Layout,
    meta: {
      perm: 'm:finance:mgt',
      title: '账单管理',
      icon: 'chart',
    },
    children: [
      {
        path: 'mgt/list',
        component: _import('finance/finance-list'),
        name: 'finance-list-mgt',
        meta: {
          perm: 'm:finance:mgt:list',
          title: '账单管理',
          icon: 'chart',
          noCache: true,
        },
      },
      {
        path: 'mgt/statistics',
        component: _import('finance/finance-statistics'),
        name: 'finance-statistics-mgt',
        meta: {
          perm: 'm:finance:mgt:statistics',
          title: '账户',
          icon: 'chart',
          noCache: true,
        },
      },
    ],
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true,
  },
];
