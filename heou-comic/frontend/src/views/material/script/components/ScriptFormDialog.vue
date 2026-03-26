<template>
  <el-dialog
    :title="type === 'add' ? '新增剧本' : '编辑剧本'"
    v-model="visible"
    width="600px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item label="剧本名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入剧本名称" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="form.description" type="textarea" placeholder="请输入描述" />
      </el-form-item>
      <el-form-item label="剧本内容" prop="content">
        <el-input v-model="form.content" type="textarea" :rows="10" placeholder="请输入剧本内容" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitting">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { createScript, updateScript } from '@/api/script'

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

const form = reactive({
  name: '',
  description: '',
  content: ''
})

const rules = {
  name: [{ required: true, message: '请输入剧本名称', trigger: 'blur' }]
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
  form.content = ''
}

const handleClose = () => {
  formRef.value?.resetFields()
  resetForm()
}

const handleSubmit = async () => {
  const valid = await formRef.value?.validate().catch(() => false)
  if (!valid) return

  submitting.value = true
  try {
    if (props.type === 'add') {
      await createScript(form)
      ElMessage.success('新增成功')
    } else {
      await updateScript(props.data.id, form)
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
</script>
