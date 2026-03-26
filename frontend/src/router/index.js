import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores/user'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('@/layout/MainLayout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { title: '首页', icon: 'HomeFilled' }
      },
      {
        path: 'base/user',
        name: 'UserManage',
        component: () => import('@/views/base/UserManage.vue'),
        meta: { title: '用户管理', icon: 'User' }
      },
      {
        path: 'base/role',
        name: 'RoleManage',
        component: () => import('@/views/base/RoleManage.vue'),
        meta: { title: '角色管理', icon: 'UserFilled' }
      },
      {
        path: 'base/menu',
        name: 'MenuManage',
        component: () => import('@/views/base/MenuManage.vue'),
        meta: { title: '菜单管理', icon: 'Menu' }
      },
      {
        path: 'material/project',
        name: 'ProjectManage',
        component: () => import('@/views/material/ProjectManage.vue'),
        meta: { title: '项目管理', icon: 'Folder' }
      },
      {
        path: 'material/project/:id',
        name: 'ProjectDetail',
        component: () => import('@/views/material/ProjectDetail.vue'),
        meta: { title: '项目详情', hidden: true }
      },
      {
        path: 'material/script',
        name: 'ScriptManage',
        component: () => import('@/views/material/ScriptManage.vue'),
        meta: { title: '剧本管理', icon: 'Document' }
      },
      {
        path: 'material/actor',
        name: 'ActorManage',
        component: () => import('@/views/material/ActorManage.vue'),
        meta: { title: '演员角色管理', icon: 'Avatar' }
      },
      {
        path: 'material/scene',
        name: 'SceneManage',
        component: () => import('@/views/material/SceneManage.vue'),
        meta: { title: '场景管理', icon: 'PictureFilled' }
      },
      {
        path: 'system/status',
        name: 'SystemStatus',
        component: () => import('@/views/system/SystemStatus.vue'),
        meta: { title: '系统状态', icon: 'Monitor' }
      },
      {
        path: 'system/config',
        name: 'SystemConfig',
        component: () => import('@/views/system/SystemConfig.vue'),
        meta: { title: '系统配置', icon: 'Setting' }
      },
      {
        path: 'system/help',
        name: 'SystemHelp',
        component: () => import('@/views/system/SystemHelp.vue'),
        meta: { title: '使用说明', icon: 'QuestionFilled' }
      },
      {
        path: 'comic/topic',
        name: 'TopicManage',
        component: () => import('@/views/comic/TopicManage.vue'),
        meta: { title: '主题管理', icon: 'Star' }
      },
      {
        path: 'comic/task',
        name: 'TaskManage',
        component: () => import('@/views/comic/TaskManage.vue'),
        meta: { title: '任务管理', icon: 'List' }
      },
      {
        path: 'comic/manage',
        name: 'ComicManage',
        component: () => import('@/views/comic/ComicManage.vue'),
        meta: { title: '漫画管理', icon: 'Picture' }
      },
      {
        path: 'comic/view/:id',
        name: 'ComicView',
        component: () => import('@/views/comic/ComicView.vue'),
        meta: { title: '漫画浏览', hidden: true }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title ? `${to.meta.title} - HEOU漫画生成系统` : 'HEOU漫画生成系统'
  const userStore = useUserStore()
  if (to.path !== '/login' && !userStore.token) {
    next('/login')
  } else {
    next()
  }
})

export default router
