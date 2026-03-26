<template>
  <div class="system-status">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>系统状态</span>
        </div>
      </template>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="系统名称">{{ status.name }}</el-descriptions-item>
        <el-descriptions-item label="系统版本">{{ status.version }}</el-descriptions-item>
        <el-descriptions-item label="未开始项目">{{ status.notStartedCount }}</el-descriptions-item>
        <el-descriptions-item label="进行中项目">{{ status.inProgressCount }}</el-descriptions-item>
        <el-descriptions-item label="已中断项目">{{ status.interruptedCount }}</el-descriptions-item>
        <el-descriptions-item label="已完结项目">{{ status.completedCount }}</el-descriptions-item>
        <el-descriptions-item label="运行中任务" :span="2">
          <el-tag type="success" size="large">{{ status.runningTaskCount }}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getSystemStatus } from '@/api/system'

const status = ref({
  name: '',
  version: '',
  notStartedCount: 0,
  inProgressCount: 0,
  interruptedCount: 0,
  completedCount: 0,
  runningTaskCount: 0
})

const fetchStatus = async () => {
  try {
    const res = await getSystemStatus()
    status.value = res
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  fetchStatus()
})
</script>

<style scoped>
.system-status {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
