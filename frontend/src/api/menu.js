import request from '../utils/request'

// 获取菜单列表
export function getMenuList() {
  return request({
    url: '/api/menu/list',
    method: 'get'
  })
}

// 获取菜单树
export function getMenuTree() {
  return request({
    url: '/api/menu/tree',
    method: 'get'
  })
}

// 新增菜单
export function addMenu(data) {
  return request({
    url: '/api/menu/add',
    method: 'post',
    data
  })
}

// 修改菜单
export function updateMenu(data) {
  return request({
    url: '/api/menu/update',
    method: 'put',
    data
  })
}

// 删除菜单
export function deleteMenu(id) {
  return request({
    url: `/api/menu/delete/${id}`,
    method: 'delete'
  })
}
