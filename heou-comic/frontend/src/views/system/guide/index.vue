<template>
  <div class="system-guide">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>系统使用说明</span>
        </div>
      </template>

      <div class="guide-content" v-html="renderedGuide"></div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { getSystemGuide } from '@/api/system'
import { marked } from 'marked'

const guide = ref('')

const renderedGuide = computed(() => {
  return marked(guide.value)
})

const fetchGuide = async () => {
  try {
    const res = await getSystemGuide()
    guide.value = res
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  fetchGuide()
})
</script>

<style scoped>
.system-guide {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.guide-content {
  line-height: 1.8;
}

.guide-content :deep(h1) {
  font-size: 24px;
  margin-bottom: 20px;
}

.guide-content :deep(h2) {
  font-size: 20px;
  margin-top: 30px;
  margin-bottom: 15px;
}

.guide-content :deep(p) {
  margin-bottom: 10px;
}

.guide-content :deep(ul) {
  margin-left: 20px;
  margin-bottom: 15px;
}

.guide-content :deep(li) {
  margin-bottom: 5px;
}
</style>
