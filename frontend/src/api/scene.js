import request from '../utils/request'

// 获取场景列表
export function getSceneList(params) {
  return request({
    url: '/api/scene/list',
    method: 'get',
    params
  })
}

// 新增场景
export function addScene(data) {
  return request({
    url: '/api/scene/add',
    method: 'post',
    data
  })
}

// 修改场景
export function updateScene(data) {
  return request({
    url: '/api/scene/update',
    method: 'put',
    data
  })
}

// 删除场景
export function deleteScene(id) {
  return request({
    url: `/api/scene/delete/${id}`,
    method: 'delete'
  })
}
