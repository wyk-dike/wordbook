import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import WindiCSS from 'vite-plugin-windicss'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
    WindiCSS(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },

  // 配置代理，解决跨域问题
  server: {
    proxy: {
      '/serverURL': {
        target: 'http://localhost:8080', // 被代理的地址：后端服务器地址（目前是本机）
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/serverURL/, '')
      },

    }
  }
})
