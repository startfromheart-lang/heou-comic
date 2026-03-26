<template>
  <div class="comic-management">
    <el-card>
      <template #header>
        <span>漫画管理</span>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="漫画名称" prop="comicName">
          <el-input
            v-model="queryParams.comicName"
            placeholder="请输入漫画名称"
            clearable
            style="width: 200px"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" clearable placeholder="请选择状态">
            <el-option label="未开始" :value="0" />
            <el-option label="生成中" :value="1" />
            <el-option label="已完成" :value="2" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="loading"
        :data="comicList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="漫画名称" prop="comicName" />
        <el-table-column label="关联项目" prop="projectName" />
        <el-table-column label="章节数" prop="chapterCount" />
        <el-table-column label="状态" prop="status">
          <template #default="scope">
            <el-tag :type="getComicStatusType(scope.row.status)">
              {{ getComicStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="handleView(scope.row)">浏览</el-button>
            <el-button link type="success" @click="handleDownload(scope.row)">下载</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="viewDialogVisible" title="漫画浏览" width="900px">
      <div class="comic-viewer">
        <el-steps :active="currentChapter" finish-status="success" simple>
          <el-step
            v-for="chapter in chapters"
            :key="chapter.id"
            :title="chapter.name"
            @click.native="currentChapter = chapter.id"
          />
        </el-steps>
        <div class="chapter-content" v-html="currentContent">
        </div>
        <div class="chapter-nav">
          <el-button :disabled="currentChapter === 0" @click="prevChapter">上一章</el-button>
          <span>第 {{ currentChapter + 1 }} / {{ chapters.length }} 章</span>
          <el-button :disabled="currentChapter === chapters.length - 1" @click="nextChapter">下一章</el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const loading = ref(false)
const viewDialogVisible = ref(false)
const comicList = ref([])

const queryParams = reactive({
  comicName: '',
  status: null
})

const queryForm = ref(null)

const chapters = ref([
  { id: 0, name: '第一章：觉醒' },
  { id: 1, name: '第二章：初遇' },
  { id: 2, name: '第三章：试炼' }
])

const currentChapter = ref(0)
const currentContent = ref('<h2>第一章：觉醒</h2><p>在一个遥远的村庄里，主人公开始了他的冒险之旅...</p><p><img src="https://picsum.photos/600/400?random=1" alt="漫画插图" style="max-width: 100%;"></p>')

const getList = () => {
  loading.value = true
  setTimeout(() => {
    comicList.value = [
      { id: 1, comicName: '修仙之路', projectName: '修仙项目', chapterCount: 12, status: 2, createTime: '2024-01-10' },
      { id: 2, comicName: '星际迷航', projectName: '星际项目', chapterCount: 8, status: 1, createTime: '2024-01-12' },
      { id: 3, comicName: '校园恋歌', projectName: '校园项目', chapterCount: 5, status: 0, createTime: '2024-01-14' }
    ]
    loading.value = false
  }, 500)
}

const handleQuery = () => {
  getList()
}

const resetQuery = () => {
  queryForm.value.resetFields()
  getList()
}

const handleView = (row) => {
  viewDialogVisible.value = true
}

const handleDownload = (row) => {
  ElMessage.success(`正在下载漫画 "${row.comicName}"`)
}

const getComicStatusType = (status) => {
  const types = ['info', 'primary', 'success']
  return types[status] || 'info'
}

const getComicStatusText = (status) => {
  const texts = ['未开始', '生成中', '已完成']
  return texts[status] || '未知'
}

const prevChapter = () => {
  if (currentChapter.value > 0) {
    currentChapter.value--
    updateContent()
  }
}

const nextChapter = () => {
  if (currentChapter.value < chapters.value.length - 1) {
    currentChapter.value++
    updateContent()
  }
}

const updateContent = () => {
  const contents = [
    '<h2>第一章：觉醒</h2><p>在一个遥远的村庄里，主人公开始了他的冒险之旅...</p><p><img src="https://picsum.photos/600/400?random=1" alt="漫画插图" style="max-width: 100%;"></p>',
    '<h2>第二章：初遇</h2><p>在村庄的边缘，主人公遇到了他的第一个伙伴...</p><p><img src="https://picsum.photos/600/400?random=2" alt="漫画插图" style="max-width: 100%;"></p>',
    '<h2>第三章：试炼</h2><p>主人公和他的伙伴们来到了神秘的试炼之地...</p><p><img src="https://picsum.photos/600/400?random=3" alt="漫画插图" style="max-width: 100%;"></p>'
  ]
  currentContent.value = contents[currentChapter.value]
}

onMounted(() => {
  getList()
})
</script>

<style scoped>
.query-form {
  margin-bottom: 20px;
}

.comic-viewer {
  padding: 20px;
}

.chapter-content {
  min-height: 400px;
  margin: 20px 0;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  line-height: 1.8;
}

.chapter-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 20px;
  border-top: 1px solid #ebeef5;
}
</style>