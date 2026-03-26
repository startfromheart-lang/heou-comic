import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import Cookies from 'js-cookie'

export const useUserStore = defineStore('user', () => {
  const token = ref(Cookies.get('token') || '')
  const userInfo = ref(JSON.parse(Cookies.get('userInfo') || '{}'))
  const menus = ref(JSON.parse(Cookies.get('menus') || '[]'))

  const isLoggedIn = computed(() => !!token.value)

  const setToken = (newToken: string) => {
    token.value = newToken
    Cookies.set('token', newToken, { expires: 7 })
  }

  const setUserInfo = (info: any) => {
    userInfo.value = info
    Cookies.set('userInfo', JSON.stringify(info), { expires: 7 })
  }

  const setMenus = (menuList: any[]) => {
    menus.value = menuList
    Cookies.set('menus', JSON.stringify(menuList), { expires: 7 })
  }

  const logout = () => {
    token.value = ''
    userInfo.value = {}
    menus.value = []
    Cookies.remove('token')
    Cookies.remove('userInfo')
    Cookies.remove('menus')
  }

  return {
    token,
    userInfo,
    menus,
    isLoggedIn,
    setToken,
    setUserInfo,
    setMenus,
    logout
  }
})
