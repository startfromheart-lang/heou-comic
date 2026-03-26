import request from '@/utils/request'

export const getTopicList = (params: any) => {
  return request.get('/topic/list', { params })
}

export const createTopic = (data: any) => {
  return request.post('/topic', data)
}

export const updateTopic = (id: number, data: any) => {
  return request.put(`/topic/${id}`, data)
}

export const deleteTopic = (id: number) => {
  return request.delete(`/topic/${id}`)
}

export const generateScript = (id: number) => {
  return request.post(`/topic/${id}/generate-script`)
}
