import request from '@/utils/request'

export const getActorList = (params: any) => {
  return request.get('/actor/list', { params })
}

export const createActor = (data: any) => {
  return request.post('/actor', data)
}

export const updateActor = (id: number, data: any) => {
  return request.put(`/actor/${id}`, data)
}

export const deleteActor = (id: number) => {
  return request.delete(`/actor/${id}`)
}
