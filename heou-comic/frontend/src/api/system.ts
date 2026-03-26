import request from '@/utils/request'

export const getSystemStatus = () => {
  return request.get('/system/status')
}

export const getSystemConfigs = () => {
  return request.get('/system/config')
}

export const createSystemConfig = (data: any) => {
  return request.post('/system/config', data)
}

export const updateSystemConfig = (id: number, data: any) => {
  return request.put(`/system/config/${id}`, data)
}

export const deleteSystemConfig = (id: number) => {
  return request.delete(`/system/config/${id}`)
}

export const getSystemGuide = () => {
  return request.get('/system/guide')
}
