import request from '@/utils/request'

export const getUserList = (params: any) => {
  return request.get('/user/list', { params })
}

export const createUser = (data: any) => {
  return request.post('/user', data)
}

export const updateUser = (id: number, data: any) => {
  return request.put(`/user/${id}`, data)
}

export const deleteUser = (id: number) => {
  return request.delete(`/user/${id}`)
}

export const getUserRoles = (userId: number) => {
  return request.get(`/user/${userId}/roles`)
}

export const assignUserRoles = (userId: number, roleIds: number[]) => {
  return request.post(`/user/${userId}/roles`, { roleIds })
}
