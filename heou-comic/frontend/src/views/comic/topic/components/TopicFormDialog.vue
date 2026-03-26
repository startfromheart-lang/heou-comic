<template>
  <el-dialog
    :title="type === 'add' ? '新增主题' : '编辑主题'"
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
      <el-form-item label="主题标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入主题标题" />
      </el-form-item>
      <el-form-item label="主题类型" prop="type">
        <el-select v-model="form.type" placeholder="请选择类型">
          <el-option label="今日热门" value="daily" />
          <el-option label="本周热门" value="weekly" />
        </el-select>
      </el-form-item>
      <el-form-item label="主题内容" prop="content">
        <el-input v-model="form.content" type="textarea" :rows="5" placeholder="请输入主题内容" />
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
import { createTopic, updateTopic } from '@/api/topic'

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
  title: '',
  type: 'daily',
  content: ''
})

const rules = {
  title: [{ required: true, message: '请输入主题标题', trigger: 'blur' }],
  type: [{ required: true, message: '请选择主题类型', trigger: 'change' }]
}

watch(() => props.data, (val) => {
  if (val) {
    Object.assign(form, val)
  } else {
    resetForm()
  }
}, { immediate: true })

const resetForm = () => {
  form.title = ''
  form.type = 'daily'
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
      await createTopic(form)
      ElMessage.success('新增成功')
    } else {
      await updateTopic(props.data.id, form)
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
