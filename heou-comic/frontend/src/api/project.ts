import request from '@/utils/request'

export const getProjectList = (params: any) => {
  return request.get('/project/list', { params })
}

export const createProject = (data: any) => {
  return request.post('/project', data)
}

export const updateProject = (id: number, data: any) => {
  return request.put(`/project/${id}`, data)
}

export const deleteProject = (id: number) => {
  return request.delete(`/project/${id}`)
}

export const generateComic = (id: number) => {
  return request.post(`/project/${id}/generate`)
}
