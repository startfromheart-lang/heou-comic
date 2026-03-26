<template>
  <div class="system-status">
    <el-card>
      <template #header>
        <span>系统状态</span>
      </template>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="系统名称">AI漫画生成系统</el-descriptions-item>
        <el-descriptions-item label="系统版本">v1.0.0</el-descriptions-item>
        <el-descriptions-item label="运行环境">Production</el-descriptions-item>
        <el-descriptions-item label="当前时间">{{ currentTime }}</el-descriptions-item>
      </el-descriptions>

      <el-divider content-position="left">项目统计</el-divider>
      
      <el-row :gutter="20">
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon info">
                <el-icon><Document /></el-icon>
              </div>
              <div class="stat-info">
                <h3>未开始</h3>
                <p>{{ projectStats.notStarted }}</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon primary">
                <el-icon><Loading /></el-icon>
              </div>
              <div class="stat-info">
                <h3>进行中</h3>
                <p>{{ projectStats.inProgress }}</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon warning">
                <el-icon><Warning /></el-icon>
              </div>
              <div class="stat-info">
                <h3>已中断</h3>
                <p>{{ projectStats.interrupted }}</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="stat-card">
            <div class="stat-content">
              <div class="stat-icon success">
                <el-icon><SuccessFilled /></el-icon>
              </div>
              <div class="stat-info">
                <h3>已完结</h3>
                <p>{{ projectStats.completed }}</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-divider content-position="left">任务统计</el-divider>
      
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card>
            <template #header>正在运行的任务</template>
            <el-table :data="runningTasks" border stripe>
              <el-table-column label="任务名称" prop="taskName" />
              <el-table-column label="任务类型" prop="taskType">
                <template #default="scope">
                  <el-tag>{{ scope.row.taskType }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="进度">
                <template #default="scope">
                  <el-progress :percentage="scope.row.progress" :stroke-width="8" />
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <template #header>系统资源使用</template>
            <el-descriptions :column="1">
              <el-descriptions-item label="CPU使用率">
                <el-progress :percentage="45" :stroke-width="10" status="success" />
              </el-descriptions-item>
              <el-descriptions-item label="内存使用率">
                <el-progress :percentage="62" :stroke-width="10" status="primary" />
              </el-descriptions-item>
              <el-descriptions-item label="磁盘使用率">
                <el-progress :percentage="38" :stroke-width="10" status="success" />
              </el-descriptions-item>
            </el-descriptions>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const currentTime = ref('')

const projectStats = ref({
  notStarted: 5,
  inProgress: 8,
  interrupted: 2,
  completed: 15
})

const runningTasks = ref([
  { taskName: '漫画生成任务-001', taskType: '漫画生成', progress: 65 },
  { taskName: '今日热门选题更新', taskType: '定时任务', progress: 100 },
  { taskName: '漫画生成任务-003', taskType: '漫画生成', progress: 30 }
])

const updateTime = () => {
  const now = new Date()
  currentTime.value = now.toLocaleString('zh-CN')
}

onMounted(() => {
  updateTime()
  setInterval(updateTime, 1000)
})
</script>

<style scoped>
.system-status {
  padding: 0;
}

.stat-card {
  border-radius: 8px;
  overflow: hidden;
}

.stat-content {
  display: flex;
  align-items: center;
  gap: 15px;
}

.stat-icon {
  width: 50px;
  height: 50px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 24px;
}

.stat-icon.info {
  background: linear-gradient(135deg, #909399, #a6a9ad);
}

.stat-icon.primary {
  background: linear-gradient(135deg, #409eff, #66b1ff);
}

.stat-icon.warning {
  background: linear-gradient(135deg, #e6a23c, #ebb563);
}

.stat-icon.success {
  background: linear-gradient(135deg, #67c23a, #85ce61);
}

.stat-info h3 {
  margin: 0;
  color: #909399;
  font-size: 14px;
  font-weight: normal;
}

.stat-info p {
  margin: 5px 0 0;
  color: #303133;
  font-size: 24px;
  font-weight: bold;
}
</style>