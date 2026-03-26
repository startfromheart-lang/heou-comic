<template>
  <div>
    <div class="page-header">
      <h2 class="page-title">系统使用说明</h2>
    </div>
    
    <div class="card-container">
      <div class="markdown-content" v-html="renderedContent"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { getHelp } from '@/api'

const content = ref('')

const renderedContent = computed(() => {
  return content.value
    .replace(/^### (.*$)/gm, '<h3>$1</h3>')
    .replace(/^## (.*$)/gm, '<h2>$1</h2>')
    .replace(/^# (.*$)/gm, '<h1>$1</h1>')
    .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
    .replace(/- (.*$)/gm, '<li>$1</li>')
    .replace(/^\d+\. (.*$)/gm, '<li>$1</li>')
    .replace(/\n\n/g, '</p><p>')
    .replace(/\n/g, '<br>')
})

const loadData = async () => {
  try {
    const res = await getHelp()
    content.value = res.data
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.markdown-content {
  line-height: 1.8;
  color: #303133;
}

.markdown-content h1 {
  font-size: 28px;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 2px solid #667eea;
}

.markdown-content h2 {
  font-size: 22px;
  margin: 25px 0 15px;
  color: #409EFF;
}

.markdown-content h3 {
  font-size: 18px;
  margin: 20px 0 10px;
  color: #67C23A;
}

.markdown-content li {
  margin: 8px 0;
  padding-left: 20px;
}

.markdown-content strong {
  color: #E6A23C;
}
</style>
