const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // 设置前端页面访问端口（选填）
      host: 'localhost',
      port: '8081',
      // 跨域配置
      proxy: {
          '/api': {
              target: 'http://localhost:8080',		// 后台地址
              changeOrigin: true,						// 允许跨域
              /*
              * 路径重写
      * 例：开发者工具查看网络请求URL地址是：http://localhost/api/user/pagePlus,
      * 实际访问地址是：http://localhost:8080/user/pagePlus,  因为重写了 /api
      * */
              pathRewrite: {
                  '^/api': ''
              }
          }
      }
  }
})
