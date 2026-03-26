<template>
  <div class="comic-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>漫画管理</span>
        </div>
      </template>

      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="漫画标题">
          <el-input v-model="searchForm.title" placeholder="请输入漫画标题" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-row :gutter="20">
        <el-col :span="6" v-for="item in tableData" :key="item.id">
          <el-card class="comic-card" shadow="hover">
            <div class="comic-cover">
              <el-image :src="item.cover || '/default-cover.png'" fit="cover" />
            </div>
            <div class="comic-info">
              <h4>{{ item.title }}</h4>
              <p class="comic-meta">章节数: {{ item.chapterCount }}</p>
              <p class="comic-time">{{ item.createTime }}</p>
            </div>
            <div class="comic-actions">
              <el-button type="primary" size="small" @click="handleView(item)">浏览</el-button>
              <el-button type="danger" size="small" @click="handleDelete(item)">删除</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-pagination
        v-model:current-page="pagination.page"
        v-model:page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        class="pagination"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getComicList, deleteComic } from '@/api/comic'

const loading = ref(false)
const tableData = ref([])

const searchForm = reactive({
  title: ''
})

const pagination = reactive({
  page: 1,
  size: 12,
  total: 0
})

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getComicList({
      ...searchForm,
      page: pagination.page,
      size: pagination.size
    })
    tableData.value = res.list
    pagination.total = res.total
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  pagination.page = 1
  fetchData()
}

const handleReset = () => {
  searchForm.title = ''
  handleSearch()
}

const handleView = (row: any) => {
  window.open(row.htmlUrl || '#', '_blank')
}

const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认删除该漫画吗？', '提示', {
      type: 'warning'
    })
    await deleteComic(row.id)
    ElMessage.success('删除成功')
    fetchData()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(error.message || '删除失败')
    }
  }
}

const handleSizeChange = (val: number) => {
  pagination.size = val
  fetchData()
}

const handleCurrentChange = (val: number) => {
  pagination.page = val
  fetchData()
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.comic-management {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-form {
  margin-bottom: 20px;
}

.comic-card {
  margin-bottom: 20px;
}

.comic-cover {
  height: 200px;
  overflow: hidden;
}

.comic-cover .el-image {
  width: 100%;
  height: 100%;
}

.comic-info {
  padding: 10px 0;
}

.comic-info h4 {
  margin: 0 0 10px 0;
  font-size: 16px;
}

.comic-meta {
  color: #666;
  font-size: 14px;
  margin: 5px 0;
}

.comic-time {
  color: #999;
  font-size: 12px;
  margin: 5px 0;
}

.comic-actions {
  display: flex;
  justify-content: space-between;
}

.pagination {
  margin-top: 20px;
  justify-content: flex-end;
}
</style>
