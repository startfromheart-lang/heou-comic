<template>
  <div class="topic-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>主题管理</span>
          <el-button type="primary" @click="handleAdd">新增主题</el-button>
        </div>
      </template>

      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="主题类型">
          <el-select v-model="searchForm.type" placeholder="请选择类型" clearable>
            <el-option label="今日热门" value="daily" />
            <el-option label="本周热门" value="weekly" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column type="index" width="50" />
        <el-table-column prop="title" label="主题标题" />
        <el-table-column prop="type" label="类型">
          <template #default="{ row }">
            <el-tag :type="row.type === 'daily' ? 'success' : 'warning'">
              {{ row.type === 'daily' ? '今日热门' : '本周热门' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="内容" show-overflow-tooltip />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column label="操作" width="250">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="success" size="small" @click="handleGenerate(row)">生成剧本</el-button>
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        v-model:current-page="pagination.page"
        v-model:page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        class="pagination"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-card>

    <topic-form-dialog
      v-model:visible="dialogVisible"
      :type="dialogType"
      :data="currentRow"
      @success="handleSearch"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getTopicList, deleteTopic, generateScript } from '@/api/topic'
import TopicFormDialog from './components/TopicFormDialog.vue'

const loading = ref(false)
const tableData = ref([])
const dialogVisible = ref(false)
const dialogType = ref<'add' | 'edit'>('add')
const currentRow = ref<any>(null)

const searchForm = reactive({
  type: ''
})

const pagination = reactive({
  page: 1,
  size: 10,
  total: 0
})

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getTopicList({
      ...searchForm,
      page: pagination.page,
      size: pagination.size
    })
    tableData.value = res.list
    pagination.total = res.total
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  pagination.page = 1
  fetchData()
}

const handleReset = () => {
  searchForm.type = ''
  handleSearch()
}

const handleAdd = () => {
  dialogType.value = 'add'
  currentRow.value = null
  dialogVisible.value = true
}

const handleEdit = (row: any) => {
  dialogType.value = 'edit'
  currentRow.value = row
  dialogVisible.value = true
}

const handleGenerate = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认根据该主题生成剧本吗？', '提示', {
      type: 'info'
    })
    await generateScript(row.id)
    ElMessage.success('剧本生成任务已启动')
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '生成失败')
    }
  }
}

const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认删除该主题吗？', '提示', {
      type: 'warning'
    })
    await deleteTopic(row.id)
    ElMessage.success('删除成功')
    fetchData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '删除失败')
    }
  }
}

const handleSizeChange = (val: number) => {
  pagination.size = val
  fetchData()
}

const handleCurrentChange = (val: number) => {
  pagination.page = val
  fetchData()
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.topic-management {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-form {
  margin-bottom: 20px;
}

.pagination {
  margin-top: 20px;
  justify-content: flex-end;
}
</style>
