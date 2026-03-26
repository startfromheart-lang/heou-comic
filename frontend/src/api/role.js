import request from '../utils/request'

// 获取角色列表
export function getRoleList(params) {
  return request({
    url: '/api/role/list',
    method: 'get',
    params
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/api/role/add',
    method: 'post',
    data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/api/role/update',
    method: 'put',
    data
  })
}

// 删除角色
export function deleteRole(id) {
  return request({
    url: `/api/role/delete/${id}`,
    method: 'delete'
  })
}
