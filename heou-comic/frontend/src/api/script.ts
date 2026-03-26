import request from '@/utils/request'

export const getScriptList = (params: any) => {
  return request.get('/script/list', { params })
}

export const createScript = (data: any) => {
  return request.post('/script', data)
}

export const updateScript = (id: number, data: any) => {
  return request.put(`/script/${id}`, data)
}

export const deleteScript = (id: number) => {
  return request.delete(`/script/${id}`)
}
