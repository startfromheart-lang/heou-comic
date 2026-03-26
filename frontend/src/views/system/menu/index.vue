<template>
  <div class="menu-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>菜单管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增菜单
          </el-button>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="menuList"
        border
        stripe
        row-key="id"
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="菜单名称" prop="menuName" />
        <el-table-column label="图标" prop="icon" width="80">
          <template #default="scope">
            <el-icon><component :is="scope.row.icon" /></el-icon>
          </template>
        </el-table-column>
        <el-table-column label="路由路径" prop="path" />
        <el-table-column label="组件路径" prop="component" />
        <el-table-column label="排序" prop="sort" width="80" />
        <el-table-column label="状态" prop="status" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '显示' : '隐藏' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="handleAddChild(scope.row)">新增子菜单</el-button>
            <el-button link type="success" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="菜单信息" width="600px">
      <el-form ref="menuForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="上级菜单">
          <el-tree-select
            v-model="form.parentId"
            :data="menuTree"
            :props="treeProps"
            placeholder="请选择上级菜单"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="form.menuName" placeholder="请输入菜单名称" />
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="form.icon" placeholder="请输入图标名称" />
        </el-form-item>
        <el-form-item label="路由路径">
          <el-input v-model="form.path" placeholder="请输入路由路径" />
        </el-form-item>
        <el-form-item label="组件路径">
          <el-input v-model="form.component" placeholder="请输入组件路径" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.sort" :min="0" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">显示</el-radio>
            <el-radio :label="0">隐藏</el-radio>
          </el-radio-group>
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
import { ElMessage, ElMessageBox } from 'element-plus'
import { getMenuList, addMenu, updateMenu, deleteMenu } from '../../../api/menu'

const loading = ref(false)
const dialogVisible = ref(false)
const menuList = ref([])
const menuTree = ref([])

const form = reactive({
  id: null,
  parentId: 0,
  menuName: '',
  icon: '',
  path: '',
  component: '',
  sort: 0,
  status: 1
})

const rules = reactive({
  menuName: [
    { required: true, message: '请输入菜单名称', trigger: 'blur' }
  ]
})

const treeProps = {
  value: 'id',
  label: 'menuName',
  children: 'children'
}

const menuForm = ref(null)

const getList = async () => {
  loading.value = true
  try {
    const res = await getMenuList()
    menuList.value = res.data
    menuTree.value = [{ id: 0, menuName: '顶级菜单', children: res.data }]
  } finally {
    loading.value = false
  }
}

const handleAdd = () => {
  form.id = null
  form.parentId = 0
  form.menuName = ''
  form.icon = ''
  form.path = ''
  form.component = ''
  form.sort = 0
  form.status = 1
  dialogVisible.value = true
}

const handleAddChild = (row) => {
  form.id = null
  form.parentId = row.id
  form.menuName = ''
  form.icon = ''
  form.path = ''
  form.component = ''
  form.sort = 0
  form.status = 1
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.id = row.id
  form.parentId = row.parentId || 0
  form.menuName = row.menuName
  form.icon = row.icon
  form.path = row.path
  form.component = row.component
  form.sort = row.sort
  form.status = row.status
  dialogVisible.value = true
}

const submitForm = async () => {
  try {
    await menuForm.value.validate()
    if (form.id) {
      await updateMenu(form)
      ElMessage.success('修改成功')
    } else {
      await addMenu(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    getList()
  } catch {
    // 验证失败
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除菜单名称为"' + row.menuName + '"的数据项?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteMenu(row.id)
      ElMessage.success('删除成功')
      getList()
    } catch (err) {
      ElMessage.error(err.response?.data?.msg || '删除失败')
    }
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
</style>