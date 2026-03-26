<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">任务管理</h2>
    </div>
    
    <div class="card-container">
      <el-tabs v-model="activeTab" @tab-change="handleTabChange">
        <el-tab-pane label="漫画任务" name="comic">
          <div class="search-bar">
            <el-input v-model="searchForm.taskName" placeholder="任务名称" clearable style="width: 200px;" />
            <el-select v-model="searchForm.status" placeholder="状态" clearable style="width: 150px;">
              <el-option label="待执行" :value="0" />
              <el-option label="执行中" :value="1" />
              <el-option label="已完成" :value="2" />
              <el-option label="已停止" :value="3" />
            </el-select>
            <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          </div>
          
          <el-table :data="tableData" style="width: 100%" v-loading="loading">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="taskName" label="任务名称" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="{ row }">
                <el-tag :type="statusMap[row.status]?.type" size="small">
                  {{ statusMap[row.status]?.label }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="progress" label="进度" width="150">
              <template #default="{ row }">
                <el-progress :percentage="row.progress" :status="row.status === 2 ? 'success' : ''" />
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="180" />
            <el-table-column label="操作" width="200" fixed="right">
              <template #default="{ row }">
                <el-button type="primary" link @click="handleEdit(row)">修改</el-button>
                <el-button v-if="row.status === 1" type="warning" link @click="handleStop(row)">停止</el-button>
                <el-button v-if="row.status === 0 || row.status === 3" type="success" link @click="handleStart(row)">启动</el-button>
                <el-button type="danger" link @click="handleDelete(row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        
        <el-tab-pane label="选题定时任务" name="topic">
          <el-table :data="topicTasks" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="taskName" label="任务名称" />
            <el-table-column prop="cronExpression" label="Cron表达式" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="{ row }">
                <el-tag :type="row.status === 1 ? 'success' : 'danger'" size="small">
                  {{ row.status === 1 ? '运行中' : '已停止' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="lastExecuteTime" label="上次执行" width="180" />
            <el-table-column label="操作" width="200">
              <template #default="{ row }">
                <el-button type="primary" link @click="handleEditScheduledTask(row)">修改</el-button>
                <el-button v-if="row.status === 1" type="warning" link @click="handleStopScheduledTask(row)">停止</el-button>
                <el-button v-if="row.status === 0" type="success" link @click="handleStartScheduledTask(row)">启动</el-button>
                <el-button type="danger" link @click="handleDeleteScheduledTask(row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      
      <div class="pagination-container" v-if="activeTab === 'comic'">
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
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import { getTaskList, updateTask, deleteTask, stopTask, startTask } from '@/api'

const loading = ref(false)
const tableData = ref([])
const topicTasks = ref([
  { id: 1, taskName: '每日热门主题更新', cronExpression: '0 0 6 * * ?', status: 1, lastExecuteTime: '2024-01-15 06:00:00' },
  { id: 2, taskName: '每周热门主题更新', cronExpression: '0 0 6 ? * MON', status: 1, lastExecuteTime: '2024-01-15 06:00:00' }
])
const activeTab = ref('comic')

const statusMap = {
  0: { label: '待执行', type: 'info' },
  1: { label: '执行中', type: 'primary' },
  2: { label: '已完成', type: 'success' },
  3: { label: '已停止', type: 'warning' }
}

const searchForm = reactive({
  taskName: '',
  status: null
})

const pagination = reactive({
  current: 1,
  size: 10,
  total: 0
})

const loadData = async () => {
  loading.value = true
  try {
    const res = await getTaskList({
      ...searchForm,
      taskType: 1,
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

const handleTabChange = (tab) => {
  if (tab === 'comic') {
    loadData()
  }
}

const handleSearch = () => {
  pagination.current = 1
  loadData()
}

const handleEdit = (row) => {
  ElMessage.info('任务修改功能开发中...')
}

const handleStop = async (row) => {
  try {
    await stopTask(row.id)
    ElMessage.success('任务已停止')
    loadData()
  } catch (error) {
    console.error(error)
  }
}

const handleStart = async (row) => {
  try {
    await startTask(row.id)
    ElMessage.success('任务已启动')
    loadData()
  } catch (error) {
    console.error(error)
  }
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确定要删除该任务吗？', '提示', {
      type: 'warning'
    })
    await deleteTask(row.id)
    ElMessage.success('删除成功')
    loadData()
  } catch (error) {
    if (error !== 'cancel') {
      console.error(error)
    }
  }
}

const handleEditScheduledTask = (row) => {
  ElMessage.info('定时任务修改功能开发中...')
}

const handleStopScheduledTask = (row) => {
  row.status = 0
  ElMessage.success('定时任务已停止')
}

const handleStartScheduledTask = (row) => {
  row.status = 1
  ElMessage.success('定时任务已启动')
}

const handleDeleteScheduledTask = async (row) => {
  try {
    await ElMessageBox.confirm('确定要删除该定时任务吗？', '提示', {
      type: 'warning'
    })
    const index = topicTasks.value.findIndex(t => t.id === row.id)
    if (index > -1) {
      topicTasks.value.splice(index, 1)
    }
    ElMessage.success('删除成功')
  } catch (error) {
    if (error !== 'cancel') {
      console.error(error)
    }
  }
}

onMounted(() => {
  loadData()
})
</script>
