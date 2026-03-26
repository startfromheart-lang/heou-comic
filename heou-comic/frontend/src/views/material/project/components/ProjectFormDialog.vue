<template>
  <el-dialog
    :title="type === 'add' ? '新增项目' : '编辑项目'"
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
      <el-form-item label="项目名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入项目名称" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="form.description" type="textarea" placeholder="请输入描述" />
      </el-form-item>
      <el-form-item label="剧本" prop="scriptId">
        <el-select v-model="form.scriptId" placeholder="请选择剧本" clearable>
          <el-option
            v-for="script in scriptList"
            :key="script.id"
            :label="script.name"
            :value="script.id"
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
import { createProject, updateProject } from '@/api/project'
import { getScriptList } from '@/api/script'

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
const scriptList = ref([])

const form = reactive({
  name: '',
  description: '',
  scriptId: null as number | null
})

const rules = {
  name: [{ required: true, message: '请输入项目名称', trigger: 'blur' }]
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
  form.description = ''
  form.scriptId = null
}

const handleClose = () => {
  formRef.value?.resetFields()
  resetForm()
}

const fetchScripts = async () => {
  try {
    const res = await getScriptList({ page: 1, size: 1000 })
    scriptList.value = res.list
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
      await createProject(form)
      ElMessage.success('新增成功')
    } else {
      await updateProject(props.data.id, form)
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
  fetchScripts()
})
</script>
