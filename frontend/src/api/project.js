import request from '../utils/request'

// 获取项目列表
export function getProjectList(params) {
  return request({
    url: '/api/project/list',
    method: 'get',
    params
  })
}

// 新增项目
export function addProject(data) {
  return request({
    url: '/api/project/add',
    method: 'post',
    data
  })
}

// 修改项目
export function updateProject(data) {
  return request({
    url: '/api/project/update',
    method: 'put',
    data
  })
}

// 删除项目
export function deleteProject(id) {
  return request({
    url: `/api/project/delete/${id}`,
    method: 'delete'
  })
}

// 获取项目详情
export function getProjectDetail(id) {
  return request({
    url: `/api/project/detail/${id}`,
    method: 'get'
  })
}

// 生成漫画
export function generateComic(id) {
  return request({
    url: `/api/project/generate/${id}`,
    method: 'post'
  })
}
