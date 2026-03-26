<template>
  <div class="dashboard">
    <el-row :gutter="20" class="stats-row">
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon blue">
              <el-icon><User /></el-icon>
            </div>
            <div class="stat-info">
              <h3>用户总数</h3>
              <p>{{ stats.userCount }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon green">
              <el-icon><Folder /></el-icon>
            </div>
            <div class="stat-info">
              <h3>项目总数</h3>
              <p>{{ stats.projectCount }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon orange">
              <el-icon><List /></el-icon>
            </div>
            <div class="stat-info">
              <h3>任务数量</h3>
              <p>{{ stats.taskCount }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon purple">
              <el-icon><Picture /></el-icon>
            </div>
            <div class="stat-info">
              <h3>漫画数量</h3>
              <p>{{ stats.comicCount }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <el-card title="系统状态">
          <el-descriptions :column="2">
            <el-descriptions-item label="系统名称">AI漫画生成系统</el-descriptions-item>
            <el-descriptions-item label="系统版本">v1.0.0</el-descriptions-item>
            <el-descriptions-item label="运行环境">Development</el-descriptions-item>
            <el-descriptions-item label="当前时间">{{ currentTime }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card title="快捷操作">
          <el-row :gutter="10">
            <el-col :span="8">
              <el-button type="primary" class="shortcut-btn" @click="$router.push('/project')">
                <el-icon><FolderAdd /></el-icon>
                <span>创建项目</span>
              </el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="success" class="shortcut-btn" @click="$router.push('/script')">
                <el-icon><DocumentAdd /></el-icon>
                <span>新建剧本</span>
              </el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="warning" class="shortcut-btn" @click="$router.push('/task')">
                <el-icon><VideoPlay /></el-icon>
                <span>查看任务</span>
              </el-button>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const stats = ref({
  userCount: 0,
  projectCount: 0,
  taskCount: 0,
  comicCount: 0
})

const currentTime = ref('')

const updateTime = () => {
  const now = new Date()
  currentTime.value = now.toLocaleString('zh-CN')
}

onMounted(() => {
  updateTime()
  setInterval(updateTime, 1000)
  
  stats.value = {
    userCount: 5,
    projectCount: 12,
    taskCount: 8,
    comicCount: 25
  }
})
</script>

<style scoped>
.dashboard {
  padding: 0;
}

.stats-row {
  margin-bottom: 20px;
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
  width: 60px;
  height: 60px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 28px;
}

.stat-icon.blue {
  background: linear-gradient(135deg, #409eff, #66b1ff);
}

.stat-icon.green {
  background: linear-gradient(135deg, #67c23a, #85ce61);
}

.stat-icon.orange {
  background: linear-gradient(135deg, #e6a23c, #ebb563);
}

.stat-icon.purple {
  background: linear-gradient(135deg, #909399, #a6a9ad);
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
  font-size: 28px;
  font-weight: bold;
}

.shortcut-btn {
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 5px;
}

.shortcut-btn :deep(.el-icon) {
  font-size: 24px;
}
</style>