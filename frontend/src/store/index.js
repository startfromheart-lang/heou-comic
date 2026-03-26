import { createPinia } from 'pinia'
import { defineStore } from 'pinia'
import { login, getUserInfo } from '../api/auth'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: null,
    menus: []
  }),
  actions: {
    async login(userInfo) {
      const res = await login(userInfo)
      this.token = res.data.token
      localStorage.setItem('token', res.data.token)
      return res
    },
    async getUserInfo() {
      const res = await getUserInfo()
      this.userInfo = res.data
      return res
    },
    logout() {
      this.token = ''
      this.userInfo = null
      this.menus = []
      localStorage.removeItem('token')
    }
  }
})

const pinia = createPinia()

export default pinia