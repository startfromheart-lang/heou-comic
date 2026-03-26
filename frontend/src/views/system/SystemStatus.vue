<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">系统状态</h2>
    </div>
    
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
            <el-icon size="28"><Folder /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ status.totalProjects || 0 }}</div>
            <div class="stat-label">项目总数</div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);">
            <el-icon size="28"><Loading /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ status.runningProjects || 0 }}</div>
            <div class="stat-label">进行中项目</div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
            <el-icon size="28"><Cpu /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ status.runningTasks || 0 }}</div>
            <div class="stat-label">运行中任务</div>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card">
          <div class="stat-icon" style="background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);">
            <el-icon size="28"><CircleCheck /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ status.completedProjects || 0 }}</div>
            <div class="stat-label">已完成项目</div>
          </div>
        </div>
      </el-col>
    </el-row>
    
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px;">项目状态分布</h3>
          <el-progress :percentage="getPercentage(status.pendingProjects, status.totalProjects)" :format="() => `未开始: ${status.pendingProjects || 0}`" style="margin-bottom: 15px;" />
          <el-progress :percentage="getPercentage(status.runningProjects, status.totalProjects)" status="success" :format="() => `进行中: ${status.runningProjects || 0}`" style="margin-bottom: 15px;" />
          <el-progress :percentage="getPercentage(status.pausedProjects, status.totalProjects)" status="warning" :format="() => `已中断: ${status.pausedProjects || 0}`" style="margin-bottom: 15px;" />
          <el-progress :percentage="getPercentage(status.completedProjects, status.totalProjects)" :format="() => `已完结: ${status.completedProjects || 0}`" />
        </div>
      </el-col>
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px;">系统信息</h3>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="系统名称">{{ status.systemName }}</el-descriptions-item>
            <el-descriptions-item label="系统版本">v1.0.0</el-descriptions-item>
            <el-descriptions-item label="技术栈">Vue3 + SpringBoot + SQLite</el-descriptions-item>
            <el-descriptions-item label="运行状态">
              <el-tag type="success">正常运行</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getSystemStatus } from '@/api'
import { Folder, Loading, Cpu, CircleCheck } from '@element-plus/icons-vue'

const status = ref({})

const getPercentage = (value, total) => {
  if (!total || total === 0) return 0
  return Math.round((value / total) * 100)
}

const loadData = async () => {
  try {
    const res = await getSystemStatus()
    status.value = res.data
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>
