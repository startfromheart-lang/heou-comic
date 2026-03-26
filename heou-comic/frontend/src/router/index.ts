import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { getMenus } from '@/api/auth'
import { ElMessage } from 'element-plus'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/login/index.vue'),
      meta: { public: true }
    },
    {
      path: '/',
      name: 'Layout',
      component: () => import('@/layouts/index.vue'),
      redirect: '/dashboard',
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          component: () => import('@/views/dashboard/index.vue'),
          meta: { title: '首页', icon: 'HomeFilled' }
        }
      ]
    }
  ]
})

const generateRoutes = (menus: any[]) => {
  const routes: any[] = []
  const modules = import.meta.glob('@/views/**/*.vue')

  menus.forEach((menu) => {
    if (menu.component && menu.path !== '/') {
      const route = {
        path: menu.path,
        name: menu.name,
        component: modules[`/src/views${menu.component}.vue`],
        meta: { title: menu.title, icon: menu.icon }
      }
      routes.push(route)
    }
    if (menu.children && menu.children.length > 0) {
      routes.push(...generateRoutes(menu.children))
    }
  })
  return routes
}

router.beforeEach(async (to, from, next) => {
  const userStore = useUserStore()

  if (to.meta.public) {
    next()
    return
  }

  if (!userStore.isLoggedIn) {
    next('/login')
    return
  }

  if (userStore.menus.length === 0) {
    try {
      const menus = await getMenus()
      userStore.setMenus(menus)
      const dynamicRoutes = generateRoutes(menus)
      dynamicRoutes.forEach((route) => {
        router.addRoute('Layout', route)
      })
      next({ ...to, replace: true })
    } catch (error) {
      ElMessage.error('获取菜单失败')
      userStore.logout()
      next('/login')
    }
    return
  }

  next()
})

export default router
