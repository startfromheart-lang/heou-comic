<template>
  <div class="task-management">
    <el-card>
      <template #header>
        <span>任务管理</span>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="漫画任务列表" name="comic">
          <el-table
            v-loading="loading"
            :data="comicTaskList"
            border
            stripe
            style="width: 100%; margin-top: 20px;"
          >
            <el-table-column label="序号" type="index" width="60" />
            <el-table-column label="任务名称" prop="taskName" />
            <el-table-column label="关联项目" prop="projectName" />
            <el-table-column label="任务类型" prop="taskType">
              <template #default="scope">
                <el-tag>{{ scope.row.taskType === 0 ? '生成漫画' : '其他' }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="状态" prop="status">
              <template #default="scope">
                <el-tag :type="getStatusType(scope.row.status)">
                  {{ getStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="进度" prop="progress">
              <template #default="scope">
                <el-progress :percentage="scope.row.progress" :stroke-width="10" />
              </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime" />
            <el-table-column label="操作" width="250">
              <template #default="scope">
                <el-button link type="primary" @click="handleStart(scope.row)" :disabled="scope.row.status === 1">启动</el-button>
                <el-button link type="warning" @click="handleStop(scope.row)" :disabled="scope.row.status !== 1">停止</el-button>
                <el-button link type="success" @click="handleRestart(scope.row)">重启</el-button>
                <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="选题定时任务列表" name="theme">
          <el-table
            v-loading="loading"
            :data="themeTaskList"
            border
            stripe
            style="width: 100%; margin-top: 20px;"
          >
            <el-table-column label="序号" type="index" width="60" />
            <el-table-column label="任务名称" prop="taskName" />
            <el-table-column label="Cron表达式" prop="cron" />
            <el-table-column label="状态" prop="status">
              <template #default="scope">
                <el-tag :type="scope.row.status === 1 ? 'success' : 'info'">
                  {{ scope.row.status === 1 ? '运行中' : '已停止' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="上次执行时间" prop="lastExecuteTime" />
            <el-table-column label="操作" width="250">
              <template #default="scope">
                <el-button link type="primary" @click="handleStartTheme(scope.row)" :disabled="scope.row.status === 1">启动</el-button>
                <el-button link type="warning" @click="handleStopTheme(scope.row)" :disabled="scope.row.status !== 1">停止</el-button>
                <el-button link type="success" @click="handleEditTheme(scope.row)">编辑</el-button>
                <el-button link type="danger" @click="handleDeleteTheme(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const loading = ref(false)
const activeTab = ref('comic')

const comicTaskList = ref([
  { id: 1, taskName: '漫画生成任务-001', projectName: '测试项目1', taskType: 0, status: 1, progress: 65, createTime: '2024-01-15 10:30:00' },
  { id: 2, taskName: '漫画生成任务-002', projectName: '测试项目2', taskType: 0, status: 0, progress: 0, createTime: '2024-01-15 11:00:00' },
  { id: 3, taskName: '漫画生成任务-003', projectName: '测试项目3', taskType: 0, status: 2, progress: 100, createTime: '2024-01-14 09:00:00' }
])

const themeTaskList = ref([
  { id: 1, taskName: '今日热门选题更新', cron: '0 0 0 * * ?', status: 1, lastExecuteTime: '2024-01-15 00:00:00' },
  { id: 2, taskName: '本周热门选题更新', cron: '0 0 0 ? * MON', status: 1, lastExecuteTime: '2024-01-15 00:00:00' }
])

const getStatusType = (status) => {
  const types = ['info', 'primary', 'success', 'warning', 'danger']
  return types[status] || 'info'
}

const getStatusText = (status) => {
  const texts = ['待启动', '运行中', '已完成', '已停止', '失败']
  return texts[status] || '未知'
}

const handleStart = (row) => {
  ElMessage.success(`任务 "${row.taskName}" 已启动`)
  row.status = 1
}

const handleStop = (row) => {
  ElMessage.success(`任务 "${row.taskName}" 已停止`)
  row.status = 3
}

const handleRestart = (row) => {
  ElMessage.success(`任务 "${row.taskName}" 重启中`)
  row.status = 1
  row.progress = 0
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`是否确认删除任务 "${row.taskName}"？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const index = comicTaskList.value.findIndex(item => item.id === row.id)
    if (index > -1) {
      comicTaskList.value.splice(index, 1)
    }
    ElMessage.success('删除成功')
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

const handleStartTheme = (row) => {
  ElMessage.success(`选题任务 "${row.taskName}" 已启动`)
  row.status = 1
}

const handleStopTheme = (row) => {
  ElMessage.success(`选题任务 "${row.taskName}" 已停止`)
  row.status = 0
}

const handleEditTheme = (row) => {
  ElMessage.info('编辑功能开发中')
}

const handleDeleteTheme = (row) => {
  ElMessageBox.confirm(`是否确认删除选题任务 "${row.taskName}"？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const index = themeTaskList.value.findIndex(item => item.id === row.id)
    if (index > -1) {
      themeTaskList.value.splice(index, 1)
    }
    ElMessage.success('删除成功')
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

onMounted(() => {
})
</script>

<style scoped>
</style>