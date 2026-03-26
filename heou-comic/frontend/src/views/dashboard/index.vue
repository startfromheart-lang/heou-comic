<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-title">项目总数</div>
            <div class="stat-value">{{ stats.projectCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-title">剧本总数</div>
            <div class="stat-value">{{ stats.scriptCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-title">漫画总数</div>
            <div class="stat-value">{{ stats.comicCount }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div class="stat-item">
            <div class="stat-title">运行中任务</div>
            <div class="stat-value">{{ stats.runningTaskCount }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="mt-20">
      <el-col :span="12">
        <el-card title="项目状态分布">
          <div ref="projectChart" style="height: 300px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card title="最近活动">
          <el-timeline>
            <el-timeline-item
              v-for="(activity, index) in activities"
              :key="index"
              :timestamp="activity.time"
            >
              {{ activity.content }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getDashboardStats } from '@/api/dashboard'

const stats = ref({
  projectCount: 0,
  scriptCount: 0,
  comicCount: 0,
  runningTaskCount: 0
})

const activities = ref([
  { content: '系统初始化完成', time: '2024-01-01 00:00:00' }
])

const projectChart = ref()

const fetchStats = async () => {
  try {
    const data = await getDashboardStats()
    stats.value = data
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  fetchStats()
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.stat-item {
  text-align: center;
}

.stat-title {
  font-size: 14px;
  color: #999;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 32px;
  font-weight: bold;
  color: #409EFF;
}

.mt-20 {
  margin-top: 20px;
}
</style>
