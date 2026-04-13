import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref(JSON.parse(localStorage.getItem('userInfo') || '{}'))
  const menus = ref(JSON.parse(localStorage.getItem('menus') || '[]'))

  const setToken = (newToken) => {
    token.value = newToken
    localStorage.setItem('token', newToken)
  }

  const setUserInfo = (info) => {
    userInfo.value = info
    localStorage.setItem('userInfo', JSON.stringify(info))
  }

  const setMenus = (menuList) => {
    menus.value = menuList
    localStorage.setItem('menus', JSON.stringify(menuList))
  }

  const logout = () => {
    token.value = ''
    userInfo.value = {}
    menus.value = []
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
    localStorage.removeItem('menus')
  }

  return {
    token,
    userInfo,
    menus,
    setToken,
    setUserInfo,
    setMenus,
    logout
  }
})
