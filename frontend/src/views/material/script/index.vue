<template>
  <div class="script-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>剧本管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增剧本
          </el-button>
        </div>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="剧本名称" prop="scriptName">
          <el-input
            v-model="queryParams.scriptName"
            placeholder="请输入剧本名称"
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
        :data="scriptList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="剧本名称" prop="scriptName" />
        <el-table-column label="内容预览" prop="content" show-overflow-tooltip />
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

    <el-dialog v-model="dialogVisible" title="剧本信息" width="700px">
      <el-form ref="scriptForm" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="剧本名称" prop="scriptName">
          <el-input v-model="form.scriptName" placeholder="请输入剧本名称" />
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" type="textarea" :rows="10" placeholder="请输入剧本内容" />
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
import { getScriptList, addScript, updateScript, deleteScript } from '../../../api/script'

const loading = ref(false)
const dialogVisible = ref(false)
const scriptList = ref([])

const queryParams = reactive({
  scriptName: ''
})

const form = reactive({
  id: null,
  scriptName: '',
  content: '',
  status: 1
})

const rules = reactive({
  scriptName: [
    { required: true, message: '请输入剧本名称', trigger: 'blur' }
  ]
})

const queryForm = ref(null)
const scriptForm = ref(null)

const getList = async () => {
  loading.value = true
  try {
    const res = await getScriptList(queryParams)
    scriptList.value = res.data
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
  form.scriptName = ''
  form.content = ''
  form.status = 1
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.id = row.id
  form.scriptName = row.scriptName
  form.content = row.content
  form.status = row.status
  dialogVisible.value = true
}

const submitForm = async () => {
  try {
    await scriptForm.value.validate()
    if (form.id) {
      await updateScript(form)
      ElMessage.success('修改成功')
    } else {
      await addScript(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    getList()
  } catch {
    // 验证失败
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除剧本名称为"' + row.scriptName + '"的数据项?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteScript(row.id)
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