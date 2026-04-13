<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">菜单管理</h2>
    </div>
    
    <div class="card-container">
      <div class="search-bar">
        <el-input v-model="searchForm.menuName" placeholder="菜单名称" clearable style="width: 200px;" />
        <el-input v-model="searchForm.menuCode" placeholder="菜单编码" clearable style="width: 200px;" />
        <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
        <el-button :icon="Plus" @click="handleAdd">新增</el-button>
      </div>
      
      <el-table :data="tableData" style="width: 100%" v-loading="loading" row-key="id" default-expand-all>
        <el-table-column prop="menuName" label="菜单名称" />
        <el-table-column prop="menuCode" label="菜单编码" />
        <el-table-column prop="path" label="路径" />
        <el-table-column prop="icon" label="图标" width="100">
          <template #default="{ row }">
            <el-icon v-if="row.icon"><component :is="row.icon" /></el-icon>
          </template>
        </el-table-column>
        <el-table-column prop="sortOrder" label="排序" width="80" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'" size="small">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="上级菜单" prop="parentId">
          <el-tree-select
            v-model="form.parentId"
            :data="menuTree"
            :props="{ label: 'menuName', value: 'id' }"
            check-strictly
            placeholder="请选择上级菜单"
            style="width: 100%;"
          />
        </el-form-item>
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="form.menuName" />
        </el-form-item>
        <el-form-item label="菜单编码" prop="menuCode">
          <el-input v-model="form.menuCode" />
        </el-form-item>
        <el-form-item label="路径" prop="path">
          <el-input v-model="form.path" />
        </el-form-item>
        <el-form-item label="组件" prop="component">
          <el-input v-model="form.component" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" />
        </el-form-item>
        <el-form-item label="排序" prop="sortOrder">
          <el-input-number v-model="form.sortOrder" :min="0" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :value="1">启用</el-radio>
            <el-radio :value="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus } from '@element-plus/icons-vue'
import { getMenuList, getMenuTree, createMenu, updateMenu, deleteMenu } from '@/api'

const loading = ref(false)
const tableData = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const formRef = ref(null)
const allMenus = ref([])

const searchForm = reactive({
  menuName: '',
  menuCode: ''
})

const form = reactive({
  id: null,
  parentId: 0,
  menuName: '',
  menuCode: '',
  path: '',
  component: '',
  icon: '',
  sortOrder: 0,
  menuType: 1,
  visible: 1,
  status: 1
})

const rules = {
  menuName: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }],
  menuCode: [{ required: true, message: '请输入菜单编码', trigger: 'blur' }]
}

const menuTree = computed(() => {
  const result = [{ id: 0, menuName: '根菜单', children: [] }]
  const map = {}
  
  allMenus.value.forEach(menu => {
    map[menu.id] = { ...menu, children: [] }
  })
  
  allMenus.value.forEach(menu => {
    if (menu.parentId === 0) {
      result[0].children.push(map[menu.id])
    } else if (map[menu.parentId]) {
      map[menu.parentId].children.push(map[menu.id])
    }
  })
  
  return result
})

const loadData = async () => {
  loading.value = true
  try {
    const res = await getMenuList(searchForm)
    tableData.value = res.data
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const loadMenuTree = async () => {
  try {
    const res = await getMenuTree()
    allMenus.value = res.data
  } catch (error) {
    console.error(error)
  }
}

const handleSearch = () => {
  loadData()
}

const handleAdd = () => {
  isEdit.value = false
  dialogTitle.value = '新增菜单'
  Object.assign(form, {
    id: null,
    parentId: 0,
    menuName: '',
    menuCode: '',
    path: '',
    component: '',
    icon: '',
    sortOrder: 0,
    menuType: 1,
    visible: 1,
    status: 1
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  dialogTitle.value = '编辑菜单'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确定要删除该菜单吗？', '提示', {
      type: 'warning'
    })
    await deleteMenu(row.id)
    ElMessage.success('删除成功')
    loadData()
    loadMenuTree()
  } catch (error) {
    if (error !== 'cancel') {
      console.error(error)
    }
  }
}

const handleSubmit = async () => {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return
  
  try {
    if (isEdit.value) {
      await updateMenu(form)
      ElMessage.success('修改成功')
    } else {
      await createMenu(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    loadData()
    loadMenuTree()
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
  loadMenuTree()
})
</script>
