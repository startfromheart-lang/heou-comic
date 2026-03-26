<template>
  <div class="scene-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>场景管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增场景
          </el-button>
        </div>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="场景名称" prop="sceneName">
          <el-input
            v-model="queryParams.sceneName"
            placeholder="请输入场景名称"
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
        :data="sceneList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="场景名称" prop="sceneName" />
        <el-table-column label="场景定位" prop="positioning" show-overflow-tooltip />
        <el-table-column label="状态" prop="status">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '启用' : '停用' }}
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

    <el-dialog v-model="dialogVisible" title="场景信息" width="600px">
      <el-form ref="sceneForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="场景名称" prop="sceneName">
          <el-input v-model="form.sceneName" placeholder="请输入场景名称" />
        </el-form-item>
        <el-form-item label="场景定位" prop="positioning">
          <el-input v-model="form.positioning" type="textarea" :rows="3" placeholder="请输入场景定位" />
        </el-form-item>
        <el-form-item label="场景图片" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入图片URL" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
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
import { getSceneList, addScene, updateScene, deleteScene } from '../../../api/scene'

const loading = ref(false)
const dialogVisible = ref(false)
const sceneList = ref([])

const queryParams = reactive({
  sceneName: ''
})

const form = reactive({
  id: null,
  sceneName: '',
  positioning: '',
  imageUrl: '',
  status: 1
})

const rules = reactive({
  sceneName: [
    { required: true, message: '请输入场景名称', trigger: 'blur' }
  ]
})

const queryForm = ref(null)
const sceneForm = ref(null)

const getList = async () => {
  loading.value = true
  try {
    const res = await getSceneList(queryParams)
    sceneList.value = res.data
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
  form.sceneName = ''
  form.positioning = ''
  form.imageUrl = ''
  form.status = 1
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.id = row.id
  form.sceneName = row.sceneName
  form.positioning = row.positioning
  form.imageUrl = row.imageUrl
  form.status = row.status
  dialogVisible.value = true
}

const submitForm = async () => {
  try {
    await sceneForm.value.validate()
    if (form.id) {
      await updateScene(form)
      ElMessage.success('修改成功')
    } else {
      await addScene(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    getList()
  } catch {
    // 验证失败
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除场景名称为"' + row.sceneName + '"的数据项?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteScene(row.id)
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