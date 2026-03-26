<template>
  <el-dialog
    :title="type === 'add' ? '新增配置' : '编辑配置'"
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
      <el-form-item label="配置键" prop="configKey">
        <el-input v-model="form.configKey" placeholder="请输入配置键" :disabled="type === 'edit'" />
      </el-form-item>
      <el-form-item label="配置值" prop="configValue">
        <el-input v-model="form.configValue" type="textarea" placeholder="请输入配置值" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="form.description" type="textarea" placeholder="请输入描述" />
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
import { createSystemConfig, updateSystemConfig } from '@/api/system'

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
  configKey: '',
  configValue: '',
  description: ''
})

const rules = {
  configKey: [{ required: true, message: '请输入配置键', trigger: 'blur' }],
  configValue: [{ required: true, message: '请输入配置值', trigger: 'blur' }]
}

watch(() => props.data, (val) => {
  if (val) {
    Object.assign(form, val)
  } else {
    resetForm()
  }
}, { immediate: true })

const resetForm = () => {
  form.configKey = ''
  form.configValue = ''
  form.description = ''
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
      await createSystemConfig(form)
      ElMessage.success('新增成功')
    } else {
      await updateSystemConfig(props.data.id, form)
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
