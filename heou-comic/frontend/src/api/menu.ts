import request from '@/utils/request'

export const getMenuList = (params?: any) => {
  return request.get('/menu/list', { params })
}

export const createMenu = (data: any) => {
  return request.post('/menu', data)
}

export const updateMenu = (id: number, data: any) => {
  return request.put(`/menu/${id}`, data)
}

export const deleteMenu = (id: number) => {
  return request.delete(`/menu/${id}`)
}

export const getMenuTree = () => {
  return request.get('/menu/tree')
}
