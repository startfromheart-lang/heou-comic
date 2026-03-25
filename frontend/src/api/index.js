import request from '@/utils/request'

export function login(data) {
  return request.post('/auth/login', data)
}

export function getUserInfo() {
  return request.get('/auth/info')
}

export function getUserList(params) {
  return request.get('/user/list', { params })
}

export function getUserById(id) {
  return request.get(`/user/${id}`)
}

export function createUser(data) {
  return request.post('/user', data)
}

export function updateUser(data) {
  return request.put('/user', data)
}

export function deleteUser(id) {
  return request.delete(`/user/${id}`)
}

export function getRoleList(params) {
  return request.get('/role/list', { params })
}

export function getRoleById(id) {
  return request.get(`/role/${id}`)
}

export function createRole(data) {
  return request.post('/role', data)
}

export function updateRole(data) {
  return request.put('/role', data)
}

export function deleteRole(id) {
  return request.delete(`/role/${id}`)
}

export function getMenuList(params) {
  return request.get('/menu/list', { params })
}

export function getMenuTree() {
  return request.get('/menu/tree')
}

export function getMenuById(id) {
  return request.get(`/menu/${id}`)
}

export function createMenu(data) {
  return request.post('/menu', data)
}

export function updateMenu(data) {
  return request.put('/menu', data)
}

export function deleteMenu(id) {
  return request.delete(`/menu/${id}`)
}

export function getProjectList(params) {
  return request.get('/project/list', { params })
}

export function getProjectById(id) {
  return request.get(`/project/${id}`)
}

export function createProject(data) {
  return request.post('/project', data)
}

export function updateProject(data) {
  return request.put('/project', data)
}

export function deleteProject(id) {
  return request.delete(`/project/${id}`)
}

export function getScriptList(params) {
  return request.get('/script/list', { params })
}

export function getScriptById(id) {
  return request.get(`/script/${id}`)
}

export function createScript(data) {
  return request.post('/script', data)
}

export function updateScript(data) {
  return request.put('/script', data)
}

export function deleteScript(id) {
  return request.delete(`/script/${id}`)
}

export function getActorList(params) {
  return request.get('/actor/list', { params })
}

export function getActorById(id) {
  return request.get(`/actor/${id}`)
}

export function createActor(data) {
  return request.post('/actor', data)
}

export function updateActor(data) {
  return request.put('/actor', data)
}

export function deleteActor(id) {
  return request.delete(`/actor/${id}`)
}

export function getSceneList(params) {
  return request.get('/scene/list', { params })
}

export function getSceneById(id) {
  return request.get(`/scene/${id}`)
}

export function createScene(data) {
  return request.post('/scene', data)
}

export function updateScene(data) {
  return request.put('/scene', data)
}

export function deleteScene(id) {
  return request.delete(`/scene/${id}`)
}

export function getTopicList(params) {
  return request.get('/topic/list', { params })
}

export function getHotTopics() {
  return request.get('/topic/hot')
}

export function getTopicById(id) {
  return request.get(`/topic/${id}`)
}

export function createTopic(data) {
  return request.post('/topic', data)
}

export function updateTopic(data) {
  return request.put('/topic', data)
}

export function deleteTopic(id) {
  return request.delete(`/topic/${id}`)
}

export function getTaskList(params) {
  return request.get('/task/list', { params })
}

export function getTaskById(id) {
  return request.get(`/task/${id}`)
}

export function createTask(data) {
  return request.post('/task', data)
}

export function updateTask(data) {
  return request.put('/task', data)
}

export function deleteTask(id) {
  return request.delete(`/task/${id}`)
}

export function stopTask(id) {
  return request.put(`/task/${id}/stop`)
}

export function startTask(id) {
  return request.put(`/task/${id}/start`)
}

export function getComicList(params) {
  return request.get('/comic/list', { params })
}

export function getComicById(id) {
  return request.get(`/comic/${id}`)
}

export function getChapterById(id) {
  return request.get(`/comic/chapter/${id}`)
}

export function createComic(data) {
  return request.post('/comic', data)
}

export function updateComic(data) {
  return request.put('/comic', data)
}

export function deleteComic(id) {
  return request.delete(`/comic/${id}`)
}

export function getSystemStatus() {
  return request.get('/system/status')
}

export function getConfigList(params) {
  return request.get('/system/config/list', { params })
}

export function getConfigById(id) {
  return request.get(`/system/config/${id}`)
}

export function createConfig(data) {
  return request.post('/system/config', data)
}

export function updateConfig(data) {
  return request.put('/system/config', data)
}

export function deleteConfig(id) {
  return request.delete(`/system/config/${id}`)
}

export function getHelp() {
  return request.get('/system/help')
}
