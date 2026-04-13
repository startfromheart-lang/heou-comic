<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">主题管理</h2>
    </div>
    
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px; display: flex; align-items: center; gap: 10px;">
            <el-icon color="#f56c6c"><Star /></el-icon>
            今日热门选题
          </h3>
          <el-table :data="dailyTopics" style="width: 100%">
            <el-table-column prop="topicTitle" label="选题标题" />
            <el-table-column prop="heatValue" label="热度" width="100">
              <template #default="{ row }">
                <el-tag type="danger" size="small">{{ row.heatValue }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="{ row }">
                <el-button type="primary" link size="small" @click="handleEditTopic(row)">编辑</el-button>
                <el-button type="success" link size="small" @click="handleGenerateScript(row)">生成剧本</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px; display: flex; align-items: center; gap: 10px;">
            <el-icon color="#e6a23c"><TrendCharts /></el-icon>
            本周热门选题
          </h3>
          <el-table :data="weeklyTopics" style="width: 100%">
            <el-table-column prop="topicTitle" label="选题标题" />
            <el-table-column prop="heatValue" label="热度" width="100">
              <template #default="{ row }">
                <el-tag type="warning" size="small">{{ row.heatValue }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="{ row }">
                <el-button type="primary" link size="small" @click="handleEditTopic(row)">编辑</el-button>
                <el-button type="success" link size="small" @click="handleGenerateScript(row)">生成剧本</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
    
    <div class="card-container">
      <div class="search-bar">
        <el-input v-model="searchForm.topicTitle" placeholder="选题标题" clearable style="width: 200px;" />
        <el-select v-model="searchForm.topicType" placeholder="类型" clearable style="width: 150px;">
          <el-option label="今日热门" :value="1" />
          <el-option label="本周热门" :value="2" />
        </el-select>
        <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
        <el-button :icon="Plus" @click="handleAdd">新增</el-button>
      </div>
      
      <el-table :data="tableData" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="topicTitle" label="选题标题" />
        <el-table-column prop="topicType" label="类型" width="100">
          <template #default="{ row }">
            <el-tag :type="row.topicType === 1 ? 'danger' : 'warning'" size="small">
              {{ row.topicType === 1 ? '今日' : '本周' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="heatValue" label="热度" width="100" />
        <el-table-column prop="source" label="来源" width="100" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'" size="small">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" link @click="handleEdit(row)">编辑</el-button>
            <el-button type="success" link @click="handleGenerateScript(row)">生成剧本</el-button>
            <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <div class="pagination-container">
        <el-pagination
          v-model:current-page="pagination.current"
          v-model:page-size="pagination.size"
          :total="pagination.total"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="loadData"
          @current-change="loadData"
        />
      </div>
    </div>
    
    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="选题标题" prop="topicTitle">
          <el-input v-model="form.topicTitle" />
        </el-form-item>
        <el-form-item label="选题内容" prop="topicContent">
          <el-input v-model="form.topicContent" type="textarea" :rows="4" />
        </el-form-item>
        <el-form-item label="类型" prop="topicType">
          <el-radio-group v-model="form.topicType">
            <el-radio :value="1">今日热门</el-radio>
            <el-radio :value="2">本周热门</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="热度值" prop="heatValue">
          <el-input-number v-model="form.heatValue" :min="0" />
        </el-form-item>
        <el-form-item label="来源" prop="source">
          <el-input v-model="form.source" />
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
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Plus, Star, TrendCharts } from '@element-plus/icons-vue'
import { getTopicList, getHotTopics, createTopic, updateTopic, deleteTopic } from '@/api'

const loading = ref(false)
const tableData = ref([])
const dailyTopics = ref([])
const weeklyTopics = ref([])
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)
const formRef = ref(null)

const searchForm = reactive({
  topicTitle: '',
  topicType: null
})

const pagination = reactive({
  current: 1,
  size: 10,
  total: 0
})

const form = reactive({
  id: null,
  topicTitle: '',
  topicContent: '',
  topicType: 1,
  heatValue: 0,
  source: '',
  status: 1
})

const rules = {
  topicTitle: [{ required: true, message: '请输入选题标题', trigger: 'blur' }]
}

const loadData = async () => {
  loading.value = true
  try {
    const res = await getTopicList({
      ...searchForm,
      current: pagination.current,
      size: pagination.size
    })
    tableData.value = res.data.records
    pagination.total = res.data.total
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const loadHotTopics = async () => {
  try {
    const res = await getHotTopics()
    dailyTopics.value = res.data.daily || []
    weeklyTopics.value = res.data.weekly || []
  } catch (error) {
    console.error(error)
  }
}

const handleSearch = () => {
  pagination.current = 1
  loadData()
}

const handleAdd = () => {
  isEdit.value = false
  dialogTitle.value = '新增选题'
  Object.assign(form, {
    id: null,
    topicTitle: '',
    topicContent: '',
    topicType: 1,
    heatValue: 0,
    source: '',
    status: 1
  })
  dialogVisible.value = true
}

const handleEdit = (row) => {
  isEdit.value = true
  dialogTitle.value = '编辑选题'
  Object.assign(form, row)
  dialogVisible.value = true
}

const handleEditTopic = (row) => {
  handleEdit(row)
}

const handleGenerateScript = (row) => {
  ElMessage.info(`正在为"${row.topicTitle}"生成剧本...`)
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确定要删除该选题吗？', '提示', {
      type: 'warning'
    })
    await deleteTopic(row.id)
    ElMessage.success('删除成功')
    loadData()
    loadHotTopics()
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
      await updateTopic(form)
      ElMessage.success('修改成功')
    } else {
      await createTopic(form)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    loadData()
    loadHotTopics()
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
  loadHotTopics()
})
</script>
