import request from '../utils/request'

// 获取角色列表
export function getCharacterList(params) {
  return request({
    url: '/api/character/list',
    method: 'get',
    params
  })
}

// 新增角色
export function addCharacter(data) {
  return request({
    url: '/api/character/add',
    method: 'post',
    data
  })
}

// 修改角色
export function updateCharacter(data) {
  return request({
    url: '/api/character/update',
    method: 'put',
    data
  })
}

// 删除角色
export function deleteCharacter(id) {
  return request({
    url: `/api/character/delete/${id}`,
    method: 'delete'
  })
}
