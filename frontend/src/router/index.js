import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../store'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/index.vue')
  },
  {
    path: '/',
    component: () => import('../views/layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/dashboard/index.vue')
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('../views/system/user/index.vue')
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('../views/system/role/index.vue')
      },
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('../views/system/menu/index.vue')
      },
      {
        path: 'project',
        name: 'Project',
        component: () => import('../views/material/project/index.vue')
      },
      {
        path: 'script',
        name: 'Script',
        component: () => import('../views/material/script/index.vue')
      },
      {
        path: 'character',
        name: 'Character',
        component: () => import('../views/material/character/index.vue')
      },
      {
        path: 'scene',
        name: 'Scene',
        component: () => import('../views/material/scene/index.vue')
      },
      {
        path: 'theme',
        name: 'Theme',
        component: () => import('../views/comic/theme/index.vue')
      },
      {
        path: 'task',
        name: 'Task',
        component: () => import('../views/comic/task/index.vue')
      },
      {
        path: 'comic',
        name: 'Comic',
        component: () => import('../views/comic/comic/index.vue')
      },
      {
        path: 'system/status',
        name: 'SystemStatus',
        component: () => import('../views/system/status/index.vue')
      },
      {
        path: 'system/config',
        name: 'SystemConfig',
        component: () => import('../views/system/config/index.vue')
      },
      {
        path: 'system/guide',
        name: 'SystemGuide',
        component: () => import('../views/system/guide/index.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  const token = userStore.token
  if (to.path === '/login') {
    if (token) {
      next('/')
    } else {
      next()
    }
  } else {
    if (token) {
      next()
    } else {
      next('/login')
    }
  }
})

export default router