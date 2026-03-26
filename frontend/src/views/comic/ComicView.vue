<template>
  <div>
    <div class="page-header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="page-title">{{ comic.comicName }}</span>
        </template>
      </el-page-header>
    </div>
    
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="card-container">
          <h3 style="margin-bottom: 20px;">章节列表</h3>
          <el-menu :default-active="String(activeChapter)" @select="handleChapterSelect">
            <el-menu-item v-for="chapter in chapters" :key="chapter.id" :index="String(chapter.id)">
              <el-icon><Document /></el-icon>
              <span>{{ chapter.chapterName }}</span>
            </el-menu-item>
          </el-menu>
        </div>
      </el-col>
      <el-col :span="18">
        <div class="card-container">
          <div v-if="currentChapter">
            <h3 style="margin-bottom: 20px;">{{ currentChapter.chapterName }}</h3>
            <div class="chapter-content">
              <p>{{ currentChapter.content }}</p>
            </div>
            <div style="margin-top: 20px; text-align: center;">
              <el-button :disabled="!prevChapter" @click="handlePrev">上一章</el-button>
              <el-button :disabled="!nextChapter" @click="handleNext">下一章</el-button>
            </div>
          </div>
          <el-empty v-else description="请选择章节" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getComicById, getChapterById } from '@/api'
import { Document } from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()

const comic = ref({})
const chapters = ref([])
const activeChapter = ref(null)
const currentChapter = ref(null)

const currentIndex = computed(() => {
  return chapters.value.findIndex(c => c.id === Number(activeChapter.value))
})

const prevChapter = computed(() => {
  if (currentIndex.value > 0) {
    return chapters.value[currentIndex.value - 1]
  }
  return null
})

const nextChapter = computed(() => {
  if (currentIndex.value < chapters.value.length - 1) {
    return chapters.value[currentIndex.value + 1]
  }
  return null
})

const goBack = () => {
  router.push('/comic/manage')
}

const handleChapterSelect = async (id) => {
  activeChapter.value = id
  try {
    const res = await getChapterById(id)
    currentChapter.value = res.data
  } catch (error) {
    console.error(error)
  }
}

const handlePrev = () => {
  if (prevChapter.value) {
    handleChapterSelect(String(prevChapter.value.id))
  }
}

const handleNext = () => {
  if (nextChapter.value) {
    handleChapterSelect(String(nextChapter.value.id))
  }
}

const loadData = async () => {
  try {
    const res = await getComicById(route.params.id)
    comic.value = res.data.comic
    chapters.value = res.data.chapters
    if (chapters.value.length > 0) {
      handleChapterSelect(String(chapters.value[0].id))
    }
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.chapter-content {
  line-height: 2;
  color: #303133;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 8px;
  min-height: 400px;
}
</style>
