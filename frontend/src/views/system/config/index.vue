<template>
  <div class="system-config">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>系统配置</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增配置
          </el-button>
        </div>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="配置键" prop="configKey">
          <el-input
            v-model="queryParams.configKey"
            placeholder="请输入配置键"
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
        :data="configList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="配置键" prop="configKey" />
        <el-table-column label="配置名称" prop="configName" />
        <el-table-column label="配置值" prop="configValue" show-overflow-tooltip />
        <el-table-column label="描述" prop="description" show-overflow-tooltip />
        <el-table-column label="创建时间" prop="createTime" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="配置信息" width="600px">
      <el-form ref="configForm" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="配置键" prop="configKey">
          <el-input v-model="form.configKey" placeholder="请输入配置键" :disabled="form.id !== null" />
        </el-form-item>
        <el-form-item label="配置名称" prop="configName">
          <el-input v-model="form.configName" placeholder="请输入配置名称" />
        </el-form-item>
        <el-form-item label="配置值" prop="configValue">
          <el-input v-model="form.configValue" type="textarea" :rows="4" placeholder="请输入配置值" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入描述" />
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

const loading = ref(false)
const dialogVisible = ref(false)
const configList = ref([])

const queryParams = reactive({
  configKey: ''
})

const form = reactive({
  id: null,
  configKey: '',
  configName: '',
  configValue: '',
  description: ''
})

const rules = reactive({
  configKey: [
    { required: true, message: '请输入配置键', trigger: 'blur' }
  ],
  configValue: [
    { required: true, message: '请输入配置值', trigger: 'blur' }
  ]
})

const queryForm = ref(null)
const configForm = ref(null)

const getList = () => {
  loading.value = true
  setTimeout(() => {
    configList.value = [
      { id: 1, configKey: 'ai.model', configName: 'AI模型配置', configValue: 'gpt-4', description: '漫画生成使用的AI模型', createTime: '2024-01-01' },
      { id: 2, configKey: 'comic.style', configName: '漫画风格', configValue: 'japanese', description: '默认漫画生成风格', createTime: '2024-01-01' },
      { id: 3, configKey: 'task.maxConcurrent', configName: '最大并发任务', configValue: '5', description: '系统最大并发任务数', createTime: '2024-01-01' },
      { id: 4, configKey: 'storage.path', configName: '存储路径', configValue: '/data/comics', description: '漫画文件存储路径', createTime: '2024-01-01' }
    ]
    loading.value = false
  }, 500)
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
  form.configKey = ''
  form.configName = ''
  form.configValue = ''
  form.description = ''
  dialogVisible.value = true
}

const handleEdit = (row) => {
  form.id = row.id
  form.configKey = row.configKey
  form.configName = row.configName
  form.configValue = row.configValue
  form.description = row.description
  dialogVisible.value = true
}

const submitForm = async () => {
  try {
    await configForm.value.validate()
    ElMessage.success(form.id ? '修改成功' : '新增成功')
    dialogVisible.value = false
    getList()
  } catch {
    // 验证失败
  }
}

const handleDelete = (row) => {
  ElMessageBox.confirm('是否确认删除配置项 "' + row.configKey + '"?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
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