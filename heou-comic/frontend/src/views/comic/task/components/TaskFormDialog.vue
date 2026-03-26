<template>
  <el-dialog
    :title="type === 'add' ? '新增任务' : '编辑任务'"
    v-model="visible"
    width="500px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item label="任务名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入任务名称" />
      </el-form-item>
      <el-form-item label="任务类型" prop="type">
        <el-select v-model="form.type" placeholder="请选择类型">
          <el-option label="漫画生成" :value="0" />
          <el-option label="选题更新" :value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="关联项目" prop="projectId" v-if="form.type === 0">
        <el-select v-model="form.projectId" placeholder="请选择项目" clearable>
          <el-option
            v-for="project in projectList"
            :key="project.id"
            :label="project.name"
            :value="project.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitting">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, watch, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { createComicTask, updateComicTask } from '@/api/comicTask'
import { getProjectList } from '@/api/project'

const props = defineProps<{
  visible: boolean
  type: 'add' | 'edit'
  data: any
}>()

const emit = defineEmits(['update:visible', 'success'])

const visible = computed({
  get: () => props.visible,
  set: (val) => emit('update:visible', val)
})

const formRef = ref()
const submitting = ref(false)
const projectList = ref([])

const form = reactive({
  name: '',
  type: 0,
  projectId: null as number | null
})

const rules = {
  name: [{ required: true, message: '请输入任务名称', trigger: 'blur' }],
  type: [{ required: true, message: '请选择任务类型', trigger: 'change' }]
}

watch(() => props.data, (val) => {
  if (val) {
    Object.assign(form, val)
  } else {
    resetForm()
  }
}, { immediate: true })

const resetForm = () => {
  form.name = ''
  form.type = 0
  form.projectId = null
}

const handleClose = () => {
  formRef.value?.resetFields()
  resetForm()
}

const fetchProjects = async () => {
  try {
    const res = await getProjectList({ page: 1, size: 1000 })
    projectList.value = res.list
  } catch (error) {
    console.error(error)
  }
}

const handleSubmit = async () => {
  const valid = await formRef.value?.validate().catch(() => false)
  if (!valid) return

  submitting.value = true
  try {
    if (props.type === 'add') {
      await createComicTask(form)
      ElMessage.success('新增成功')
    } else {
      await updateComicTask(props.data.id, form)
      ElMessage.success('编辑成功')
    }
    visible.value = false
    emit('success')
  } catch (error) {
    console.error(error)
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  fetchProjects()
})
</script>
