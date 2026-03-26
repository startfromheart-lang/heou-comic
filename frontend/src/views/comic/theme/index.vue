<template>
  <div class="theme-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>主题管理</span>
          <el-button type="primary" @click="handleRefresh">
            <el-icon><Refresh /></el-icon>
            刷新主题
          </el-button>
        </div>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="今日热门选题" name="today">
          <el-card>
            <el-row :gutter="20">
              <el-col :span="8" v-for="theme in todayThemes" :key="theme.id">
                <el-card class="theme-card" shadow="hover">
                  <template #header>
                    <div class="theme-title">{{ theme.name }}</div>
                  </template>
                  <p class="theme-desc">{{ theme.description }}</p>
                  <div class="theme-actions">
                    <el-button size="small" @click="handleEdit(theme)">编辑</el-button>
                    <el-button size="small" type="primary" @click="handleGenerateScript(theme)">生成剧本</el-button>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-tab-pane>
        <el-tab-pane label="本周热门选题" name="week">
          <el-card>
            <el-row :gutter="20">
              <el-col :span="8" v-for="theme in weekThemes" :key="theme.id">
                <el-card class="theme-card" shadow="hover">
                  <template #header>
                    <div class="theme-title">{{ theme.name }}</div>
                  </template>
                  <p class="theme-desc">{{ theme.description }}</p>
                  <div class="theme-actions">
                    <el-button size="small" @click="handleEdit(theme)">编辑</el-button>
                    <el-button size="small" type="primary" @click="handleGenerateScript(theme)">生成剧本</el-button>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <el-dialog v-model="dialogVisible" title="编辑主题" width="600px">
      <el-form ref="themeForm" :model="form" label-width="80px">
        <el-form-item label="主题名称">
          <el-input v-model="form.name" placeholder="请输入主题名称" />
        </el-form-item>
        <el-form-item label="主题描述">
          <el-input v-model="form.description" type="textarea" :rows="4" placeholder="请输入主题描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const activeTab = ref('today')
const dialogVisible = ref(false)

const todayThemes = ref([
  { id: 1, name: '都市修仙', description: '现代都市背景下的修仙故事' },
  { id: 2, name: '穿越异世', description: '主角穿越到异世界展开冒险' },
  { id: 3, name: '校园恋爱', description: '青春校园爱情故事' }
])

const weekThemes = ref([
  { id: 4, name: '末日生存', description: '末日废土下的生存挑战' },
  { id: 5, name: '星际探险', description: '宇宙星际间的探险旅程' },
  { id: 6, name: '武侠江湖', description: '古典武侠世界的恩怨情仇' }
])

const form = reactive({
  id: null,
  name: '',
  description: ''
})

const themeForm = ref(null)

const handleRefresh = () => {
  ElMessage.success('主题列表已刷新')
}

const handleEdit = (row) => {
  form.id = row.id
  form.name = row.name
  form.description = row.description
  dialogVisible.value = true
}

const handleGenerateScript = (row) => {
  ElMessage.success(`已为主题 "${row.name}" 发起剧本生成任务`)
}

const submitForm = () => {
  ElMessage.success('修改成功')
  dialogVisible.value = false
}

onMounted(() => {
})
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.theme-card {
  margin-bottom: 20px;
}

.theme-title {
  font-weight: bold;
  color: #303133;
}

.theme-desc {
  color: #606266;
  min-height: 60px;
}

.theme-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>