import request from '@/utils/request'

export const getComicTaskList = (params: any) => {
  return request.get('/comic-task/list', { params })
}

export const createComicTask = (data: any) => {
  return request.post('/comic-task', data)
}

export const updateComicTask = (id: number, data: any) => {
  return request.put(`/comic-task/${id}`, data)
}

export const deleteComicTask = (id: number) => {
  return request.delete(`/comic-task/${id}`)
}

export const startComicTask = (id: number) => {
  return request.post(`/comic-task/${id}/start`)
}

export const stopComicTask = (id: number) => {
  return request.post(`/comic-task/${id}/stop`)
}
