module.exports = {
  title: '东岳用户手册',
  description: '东岳用户手册',
  base: "/docs",
  themeConfig: {
    lastUpdated: '上一次更新',
    nav: [
      {text: '用户指南', link: '/'},
      {text: '主页', link: 'http://47.105.33.48/'},
    ],
    sidebar: {
      '/user/': [
        {
          title: "退货单",
          path: '/user/returnOrd/',      // 可选的, 应该是一个绝对路径
          collapsable: false, // 可选的, 默认值是 true,
          sidebarDepth: 1,    // 可选的, 默认值是 1
          children: [
            '/'
          ]
        },
        '',     /* /foo/ */
        'One',     /* /foo/ */
        'Two',     /* /foo/ */
        {
          title: '你好',   // 必要的
          path: '/One/',      // 可选的, 应该是一个绝对路径
          collapsable: false, // 可选的, 默认值是 true,
          sidebarDepth: 1,    // 可选的, 默认值是 1
          children: [
            '/'
          ]
        },
      ],
      '/manager/': [
        '',      /* /bar/ */
      ],
      // fallback
      '/': [
        '',        /* / */
      ]
    },
    search: true,
    searchMaxSuggestions: 10
  }
}
