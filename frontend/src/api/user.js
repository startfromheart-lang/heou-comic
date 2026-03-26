import request from '../utils/request'

// 获取用户列表
export function getUserList(params) {
  return request({
    url: '/api/user/list',
    method: 'get',
    params
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/api/user/add',
    method: 'post',
    data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/api/user/update',
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(id) {
  return request({
    url: `/api/user/delete/${id}`,
    method: 'delete'
  })
}

// 重置密码
export function resetPassword(userId, newPassword) {
  return request({
    url: `/api/user/resetPwd`,
    method: 'put',
    data: {
      userId,
      newPassword
    }
  })
}

// 获取角色列表
export function getRoleList() {
  return request({
    url: '/api/role/all',
    method: 'get'
  })
}
