<template>
  <el-dialog
    :title="type === 'add' ? '新增场景' : '编辑场景'"
    v-model="visible"
    width="500px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="90px"
    >
      <el-form-item label="场景名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入场景名称" />
      </el-form-item>
      <el-form-item label="场景定位" prop="position">
        <el-input v-model="form.position" placeholder="请输入场景定位" />
      </el-form-item>
      <el-form-item label="场景图片" prop="imageUrl">
        <el-input v-model="form.imageUrl" placeholder="请输入图片URL" />
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
import { createScene, updateScene } from '@/api/scene'

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
  position: '',
  imageUrl: '',
  description: ''
})

const rules = {
  name: [{ required: true, message: '请输入场景名称', trigger: 'blur' }]
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
  form.position = ''
  form.imageUrl = ''
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
      await createScene(form)
      ElMessage.success('新增成功')
    } else {
      await updateScene(props.data.id, form)
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
