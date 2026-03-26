import request from '../utils/request'

// 获取剧本列表
export function getScriptList(params) {
  return request({
    url: '/api/script/list',
    method: 'get',
    params
  })
}

// 新增剧本
export function addScript(data) {
  return request({
    url: '/api/script/add',
    method: 'post',
    data
  })
}

// 修改剧本
export function updateScript(data) {
  return request({
    url: '/api/script/update',
    method: 'put',
    data
  })
}

// 删除剧本
export function deleteScript(id) {
  return request({
    url: `/api/script/delete/${id}`,
    method: 'delete'
  })
}
