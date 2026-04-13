<template>
  <div>
    <div class="page-header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="page-title">{{ project.projectName }}</span>
        </template>
      </el-page-header>
    </div>
    
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="card-container">
          <h3 style="margin-bottom: 20px;">项目信息</h3>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="项目名称">{{ project.projectName }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag :type="statusMap[project.status]?.type">
                {{ statusMap[project.status]?.label }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="当前步骤">{{ project.currentStep }} / {{ project.totalSteps }}</el-descriptions-item>
            <el-descriptions-item label="执行模式">
              <el-tag :type="project.autoMode === 1 ? 'success' : 'info'">
                {{ project.autoMode === 1 ? '自动' : '手动' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="描述" :span="2">{{ project.description }}</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{ project.createTime }}</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ project.updateTime }}</el-descriptions-item>
          </el-descriptions>
        </div>
        
        <div class="card-container" style="margin-top: 20px;">
          <h3 style="margin-bottom: 20px;">项目步骤</h3>
          <el-steps :active="project.currentStep" finish-status="success" align-center>
            <el-step v-for="i in project.totalSteps" :key="i" :title="`第${i}步`" />
          </el-steps>
          
          <div style="margin-top: 30px; text-align: center;">
            <el-button type="primary" :icon="MagicStick" @click="handleGenerate">
              发起漫画生成
            </el-button>
            <el-button :icon="Edit" @click="handleEditStep">编辑步骤素材</el-button>
          </div>
        </div>
      </el-col>
      
      <el-col :span="8">
        <div class="card-container">
          <h3 style="margin-bottom: 20px;">关联素材</h3>
          <el-tabs>
            <el-tab-pane label="剧本">
              <el-empty description="暂无关联剧本" />
            </el-tab-pane>
            <el-tab-pane label="演员">
              <el-empty description="暂无关联演员" />
            </el-tab-pane>
            <el-tab-pane label="场景">
              <el-empty description="暂无关联场景" />
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { MagicStick, Edit } from '@element-plus/icons-vue'
import { getProjectById } from '@/api'

const route = useRoute()
const router = useRouter()

const project = ref({})

const statusMap = {
  0: { label: '未开始', type: 'info' },
  1: { label: '进行中', type: 'primary' },
  2: { label: '已中断', type: 'warning' },
  3: { label: '已完结', type: 'success' }
}

const goBack = () => {
  router.push('/material/project')
}

const handleGenerate = () => {
  ElMessage.info('漫画生成功能开发中...')
}

const handleEditStep = () => {
  ElMessage.info('步骤编辑功能开发中...')
}

const loadData = async () => {
  try {
    const res = await getProjectById(route.params.id)
    project.value = res.data
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>
