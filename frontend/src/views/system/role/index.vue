<template>
  <div class="role-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>角色管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增角色
          </el-button>
        </div>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="角色名称" prop="roleName">
          <el-input
            v-model="queryParams.roleName"
            placeholder="请输入角色名称"
            clearable
            style="width: 200px"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="loading"
        :data="roleList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="角色名称" prop="roleName" />
        <el-table-column label="角色编码" prop="roleCode" />
        <el-table-column label="描述" prop="description" show-overflow-tooltip />
        <el-table-column label="状态" prop="status">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '正常' : '停用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="角色信息" width="700px">
      <el-form ref="roleForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="form.roleCode" placeholder="请输入角色编码" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单权限">
          <el-tree
            ref="menuTree"
            :data="menuOptions"
            :props="menuProps"
            show-checkbox
            node-key="id"
            :default-expanded-keys="[1]"
            :default-checked-keys="form.menuIds"
            check-strictly
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox, ElTree } from 'element-plus'
import { getRoleList, addRole, updateRole, deleteRole } from '../../../api/role'

const loading = ref(false)
const dialogVisible = ref(false)
const roleList = ref([])
const menuTree = ref(null)

const queryParams = reactive({
  roleName: ''
})

const form = reactive({
  id: null,
  roleName: '',
  roleCode: '',
  description: '',
  status: 1,
  menuIds: []
})

const rules = reactive({
  roleName: [
    { required: true, message: '请输入角色名称', trigger: 'blur' }
  ],
  roleCode: [
    { required: true, message: '请输入角色编码', trigger: 'blur' }
  ]
})

const menuOptions = ref([
  {
    id: 1,
    label: '系统管理',
    children: [
      { id: 11, label: '用户管理' },
      { id: 12, label: '角色管理' },
      { id: 13, label: '菜单管理' }
    ]
  },
  {
    id: 2,
    label: '素材管理',
    children: [
      { id: 21, label: '项目管理' },
      { id: 22, label: '剧本管理' },
      { id: 23, label: '角色管理' },
      { id: 24, label: '场景管理' }
    ]
  },
  {
    id: 3,
    label: '漫画生成',
    children: [
      { id: 31, label: '主题管理' },
      { id: 32, label: '任务管理' },
      { id: 33, label: '漫画管理' }
    ]
  }
])

const menuProps = {
  children: 'children',
  label: 'label'
}

const queryForm = ref(null)
const roleForm = ref(null)

const getList = async () => {
  loading.value = true
  try {
    const res = await getRoleList(queryParams)
    roleList.value = res.data
  } finally {
    loading.value = false
  }
}

const handleQuery = () => {
  getList()
}

const resetQuery = () => {
  queryForm.value.resetFields()
  getList()
}

const handleAdd = () => {
  form.id = null
  form.roleName = ''
  form.roleCode = ''
  form.description = ''
  form.status = 1
  form.menuIds = []
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.id = row.id
  form.roleName = row.roleName
  form.roleCode = row.roleCode
  form.description = row.description
  form.status = row.status
  form.menuIds = []
  dialogVisible.value = true
}

const submitForm = async () => {
  try {
    await roleForm.value.validate()
    const checkedKeys = menuTree.value?.getCheckedKeys() || []
    form.menuIds = checkedKeys
    
    if (form.id) {
      await updateRole(form)
      ElMessage.success('修改成功')
    } else {
      await addRole(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    getList()
  } catch {
    // 验证失败
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除角色名称为"' + row.roleName + '"的数据项?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteRole(row.id)
    ElMessage.success('删除成功')
    getList()
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

onMounted(() => {
  getList()
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.query-form {
  margin-bottom: 20px;
}
</style>