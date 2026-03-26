<template>
  <el-container class="layout-container">
    <el-aside width="220px" class="layout-aside">
      <div class="logo">
        <h3>AI漫画生成系统</h3>
      </div>
      <el-menu
        :default-active="activeMenu"
        class="layout-menu"
        router
        unique-opened
      >
        <el-menu-item index="/dashboard">
          <el-icon><Odometer /></el-icon>
          <span>系统首页</span>
        </el-menu-item>
        
        <el-sub-menu index="1">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>基础管理</span>
          </template>
          <el-menu-item index="/user">用户管理</el-menu-item>
          <el-menu-item index="/role">角色管理</el-menu-item>
          <el-menu-item index="/menu">菜单管理</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <el-icon><Folder /></el-icon>
            <span>素材管理</span>
          </template>
          <el-menu-item index="/project">项目管理</el-menu-item>
          <el-menu-item index="/script">剧本管理</el-menu-item>
          <el-menu-item index="/character">演员角色</el-menu-item>
          <el-menu-item index="/scene">场景管理</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>
            <el-icon><Picture /></el-icon>
            <span>漫画生成</span>
          </template>
          <el-menu-item index="/theme">主题管理</el-menu-item>
          <el-menu-item index="/task">任务管理</el-menu-item>
          <el-menu-item index="/comic">漫画管理</el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="4">
          <template #title>
            <el-icon><Monitor /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="/system/status">系统状态</el-menu-item>
          <el-menu-item index="/system/config">系统配置</el-menu-item>
          <el-menu-item index="/system/guide">使用说明</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="layout-header">
        <div class="header-left">
          <el-button @click="toggleCollapse" circle>
            <el-icon><Expand /></el-icon>
          </el-button>
        </div>
        <div class="header-right">
          <el-dropdown @command="handleCommand">
            <span class="user-info">
              <el-icon><User /></el-icon>
              <span>{{ userStore.userInfo?.realName || '管理员' }}</span>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main class="layout-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from '../../store'
import { ElMessage } from 'element-plus'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const activeMenu = ref('/dashboard')
const collapse = ref(false)

onMounted(() => {
  activeMenu.value = route.path
  userStore.getUserInfo()
})

const toggleCollapse = () => {
  collapse.value = !collapse.value
}

const handleCommand = (command) => {
  if (command === 'logout') {
    userStore.logout()
    ElMessage.success('退出成功')
    router.push('/login')
  }
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.layout-aside {
  background: linear-gradient(180deg, #304156 0%, #1f2d3d 100%);
  transition: width 0.3s;
}

.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.logo h3 {
  color: #fff;
  margin: 0;
  font-size: 18px;
}

.layout-menu {
  border-right: none;
  background: transparent;
}

.layout-menu :deep(.el-menu-item) {
  color: #bfcbd9;
}

.layout-menu :deep(.el-menu-item:hover) {
  background-color: rgba(255, 255, 255, 0.1);
}

.layout-menu :deep(.el-menu-item.is-active) {
  background-color: #1890ff;
  color: #fff;
}

.layout-menu :deep(.el-sub-menu__title) {
  color: #bfcbd9;
}

.layout-header {
  background: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  color: #606266;
}

.layout-main {
  background: #f0f2f5;
  padding: 20px;
}
</style>