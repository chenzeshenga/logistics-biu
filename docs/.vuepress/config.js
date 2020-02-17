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
        '',     /* /foo/ */
        'One',     /* /foo/ */
        'Two',     /* /foo/ */
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
