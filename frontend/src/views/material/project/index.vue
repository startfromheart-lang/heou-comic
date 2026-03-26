<template>
  <div class="project-management">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>项目管理</span>
          <el-button type="primary" @click="handleAdd">
            <el-icon><Plus /></el-icon>
            新增项目
          </el-button>
        </div>
      </template>

      <el-form :model="queryParams" ref="queryForm" :inline="true" class="query-form">
        <el-form-item label="项目名称" prop="projectName">
          <el-input
            v-model="queryParams.projectName"
            placeholder="请输入项目名称"
            clearable
            style="width: 200px"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" clearable placeholder="请选择状态">
            <el-option label="未开始" :value="0" />
            <el-option label="进行中" :value="1" />
            <el-option label="已中断" :value="2" />
            <el-option label="已完结" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="loading"
        :data="projectList"
        border
        stripe
        style="width: 100%; margin-top: 20px;"
      >
        <el-table-column label="序号" type="index" width="60" />
        <el-table-column label="项目名称" prop="projectName" />
        <el-table-column label="描述" prop="description" show-overflow-tooltip />
        <el-table-column label="状态" prop="status">
          <template #default="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" />
        <el-table-column label="操作" width="250">
          <template #default="scope">
            <el-button link type="primary" @click="handleView(scope.row)">查看</el-button>
            <el-button link type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button link type="success" @click="handleGenerate(scope.row)">生成漫画</el-button>
            <el-button link type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="项目信息" width="600px">
      <el-form ref="projectForm" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="4" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">未开始</el-radio>
            <el-radio :label="1">进行中</el-radio>
            <el-radio :label="2">已中断</el-radio>
            <el-radio :label="3">已完结</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="viewDialogVisible" title="项目详情" width="800px">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="项目名称">{{ form.projectName }}</el-descriptions-item>
        <el-descriptions-item label="状态">{{ getStatusText(form.status) }}</el-descriptions-item>
        <el-descriptions-item label="描述" :span="2">{{ form.description }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ form.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间">{{ form.updateTime }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { getProjectList, addProject, updateProject, deleteProject, getProjectDetail, generateComic } from '../../../api/project';

const loading = ref(false);
const dialogVisible = ref(false);
const viewDialogVisible = ref(false);
const projectList = ref([]);

const queryParams = reactive({
  projectName: '',
  status: null
});

const form = reactive({
  id: null,
  projectName: '',
  description: '',
  status: 0
});

const rules = reactive({
  projectName: [
    { required: true, message: '请输入项目名称', trigger: 'blur' }
  ]
});

const queryForm = ref(null);
const projectForm = ref(null);

const getList = async () => {
  loading.value = true;
  try {
    const res = await getProjectList(queryParams);
    projectList.value = res.data;
  } finally {
    loading.value = false;
  }
};

const handleQuery = () => {
  getList();
};

const resetQuery = () => {
  queryForm.value.resetFields();
  getList();
};

const handleAdd = () => {
  form.id = null;
  form.projectName = '';
  form.description = '';
  form.status = 0;
  dialogVisible.value = true;
};

const handleEdit = (row) => {
  form.id = row.id;
  form.projectName = row.projectName;
  form.description = row.description;
  form.status = row.status;
  dialogVisible.value = true;
};

const handleView = (row) => {
  form.id = row.id;
  form.projectName = row.projectName;
  form.description = row.description;
  form.status = row.status;
  form.createTime = row.createTime;
  form.updateTime = row.updateTime;
  viewDialogVisible.value = true;
};

const handleGenerate = (row) => {
  ElMessage.success(`已为项目 "${row.projectName}" 发起漫画生成任务`);
};

const submitForm = async () => {
  try {
    await projectForm.value.validate();
    if (form.id) {
      await updateProject(form);
      ElMessage.success('修改成功');
    } else {
      await addProject(form);
      ElMessage.success('新增成功');
    }
    dialogVisible.value = false;
    getList();
  } catch {
    // 验证失败
  }
};

const handleDelete = (row) => {
  ElMessageBox.confirm(`是否确认删除项目名称为 "${row.projectName}" 的数据项？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await deleteProject(row.id);
    ElMessage.success('删除成功');
    getList();
  }).catch(() => {
    ElMessage.info('已取消删除');
  });
};

const getStatusType = (status) => {
  const types = ['info', 'primary', 'warning', 'success'];
  return types[status] || 'info';
};

const getStatusText = (status) => {
  const texts = ['未开始', '进行中', '已中断', '已完结'];
  return texts[status] || '未知';
};

onMounted(() => {
  getList();
});
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.query-form {
  margin-bottom: 20px;
}
</style>