<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">系统概览</h2>
    </div>
    
    <el-row :gutter="20" style="margin-bottom: 20px;">
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

    <el-row :gutter="20">
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px; display: flex; align-items: center; gap: 10px;">
            <el-icon color="#667eea"><Star /></el-icon>
            今日热门选题
          </h3>
          <el-table :data="dailyTopics" style="width: 100%">
            <el-table-column prop="topicTitle" label="选题标题" />
            <el-table-column prop="heatValue" label="热度" width="100">
              <template #default="{ row }">
                <el-tag type="danger" size="small">{{ row.heatValue }}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="card-container">
          <h3 style="margin-bottom: 20px; display: flex; align-items: center; gap: 10px;">
            <el-icon color="#f5576c"><TrendCharts /></el-icon>
            本周热门选题
          </h3>
          <el-table :data="weeklyTopics" style="width: 100%">
            <el-table-column prop="topicTitle" label="选题标题" />
            <el-table-column prop="heatValue" label="热度" width="100">
              <template #default="{ row }">
                <el-tag type="warning" size="small">{{ row.heatValue }}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getSystemStatus, getHotTopics } from '@/api'
import { Folder, Loading, Cpu, CircleCheck, Star, TrendCharts } from '@element-plus/icons-vue'

const status = ref({})
const dailyTopics = ref([])
const weeklyTopics = ref([])

const loadData = async () => {
  try {
    const [statusRes, hotRes] = await Promise.all([
      getSystemStatus(),
      getHotTopics()
    ])
    status.value = statusRes.data
    dailyTopics.value = hotRes.data.daily || []
    weeklyTopics.value = hotRes.data.weekly || []
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>
