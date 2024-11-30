import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/store/userStore'

import Index from '@/pages/index.vue'
import Login from '@/pages/login.vue'
import NotFound from '@/pages/404.vue'
import MyWordbook from '@/pages/mainPages/myWordbook.vue'
import NotLogin from '@/pages/mainPages/notLogin.vue'
import Setting from '@/pages/mainPages/setting.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 默认进入首页
    {
      path:'/',
      name: 'Index',
      component: Index,
      children: [
        {
          path:'/notLogin',
          name: 'NotLogin',
          component: NotLogin
        },
        {
          path:'/myWordbook',
          name: 'MyWordbook',
          component: MyWordbook,
          beforeEnter: (to,from,next) => {
            let userStore = useUserStore()
            if(to.name === 'MyWordbook' && (userStore.userId == -1 || userStore.account == '' )) {
              // 未登录，执行拦截
              next({ name: 'NotLogin' })
            }
            else {
              // 放行
              next()
            }
          }
        },
        {
          path: '/setting',
          name: 'Setting',
          component: Setting,
          beforeEnter: (to,from,next) => {
            let userStore = useUserStore()
            if(to.name === 'Setting' && (userStore.userId == -1 || userStore.account == '' )) {
              // 未登录，执行拦截
              next({ name: 'NotLogin' })
            }
            else {
              // 放行
              next()
            }
          }
        }
      ]
    },
    // 登录页
    {
      path:'/login',
      name: 'Login',
      component: Login
    },
    // 页面不存在，跳转至 404 页面
    { 
      path: '/:pathMatch(.*)*', 
      name: 'NotFound', 
      component: NotFound 
    }
  ],
})

export default router
