import request from '@/utils/request'

export const getSceneList = (params: any) => {
  return request.get('/scene/list', { params })
}

export const createScene = (data: any) => {
  return request.post('/scene', data)
}

export const updateScene = (id: number, data: any) => {
  return request.put(`/scene/${id}`, data)
}

export const deleteScene = (id: number) => {
  return request.delete(`/scene/${id}`)
}
