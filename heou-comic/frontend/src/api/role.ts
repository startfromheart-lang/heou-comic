import request from '@/utils/request'

export const getRoleList = (params: any) => {
  return request.get('/role/list', { params })
}

export const createRole = (data: any) => {
  return request.post('/role', data)
}

export const updateRole = (id: number, data: any) => {
  return request.put(`/role/${id}`, data)
}

export const deleteRole = (id: number) => {
  return request.delete(`/role/${id}`)
}

export const getRoleMenus = (roleId: number) => {
  return request.get(`/role/${roleId}/menus`)
}

export const assignRoleMenus = (roleId: number, menuIds: number[]) => {
  return request.post(`/role/${roleId}/menus`, { menuIds })
}
