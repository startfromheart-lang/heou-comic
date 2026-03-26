import request from '@/utils/request'

export const getComicList = (params: any) => {
  return request.get('/comic/list', { params })
}

export const getComicById = (id: number) => {
  return request.get(`/comic/${id}`)
}

export const deleteComic = (id: number) => {
  return request.delete(`/comic/${id}`)
}
