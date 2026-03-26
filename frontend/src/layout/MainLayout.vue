<template>
  <div class="main-container">
    <div class="header-container">
      <div class="header-title">
        <el-icon size="28"><MagicStick /></el-icon>
        <span>HEOU漫画生成系统</span>
      </div>
      <div class="header-user">
        <el-dropdown @command="handleCommand">
          <span style="cursor: pointer; display: flex; align-items: center; gap: 5px;">
            <el-avatar :size="32" :icon="UserFilled" />
            <span>{{ userStore.userInfo.nickname || userStore.userInfo.username }}</span>
            <el-icon><ArrowDown /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="logout">
                <el-icon><SwitchButton /></el-icon>
                退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <div style="display: flex; flex: 1;">
      <div class="sidebar-container" :style="{ width: isCollapse ? '64px' : '220px' }">
        <div style="padding: 10px; text-align: center;">
          <el-button 
            :icon="isCollapse ? Expand : Fold" 
            @click="isCollapse = !isCollapse" 
            text 
            style="color: white;"
          />
        </div>
        <el-menu
          :default-active="activeMenu"
          :collapse="isCollapse"
          :collapse-transition="false"
          background-color="#304156"
          text-color="#bfcbd9"
          active-text-color="#409EFF"
          router
        >
          <template v-for="menu in menuList" :key="menu.id">
            <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="String(menu.id)">
              <template #title>
                <el-icon><component :is="menu.icon" /></el-icon>
                <span>{{ menu.menuName }}</span>
              </template>
              <el-menu-item 
                v-for="child in menu.children" 
                :key="child.id" 
                :index="child.path"
              >
                <el-icon><component :is="child.icon" /></el-icon>
                <span>{{ child.menuName }}</span>
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item v-else :index="menu.path">
              <el-icon><component :is="menu.icon" /></el-icon>
              <span>{{ menu.menuName }}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </div>
      <div class="main-content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { UserFilled, ArrowDown, SwitchButton, Fold, Expand } from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const isCollapse = ref(false)
const activeMenu = computed(() => route.path)

const menuList = computed(() => {
  const menus = userStore.menus || []
  const result = []
  const map = {}
  
  menus.forEach(menu => {
    map[menu.id] = { ...menu, children: [] }
  })
  
  menus.forEach(menu => {
    if (menu.parentId === 0) {
      result.push(map[menu.id])
    } else if (map[menu.parentId]) {
      map[menu.parentId].children.push(map[menu.id])
    }
  })
  
  return result
})

const handleCommand = (command) => {
  if (command === 'logout') {
    userStore.logout()
    router.push('/login')
  }
}
</script>
