<template>
  <el-dialog
    :title="type === 'add' ? '新增菜单' : '编辑菜单'"
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
      <el-form-item label="菜单名称" prop="title">
        <el-input v-model="form.title" placeholder="请输入菜单名称" />
      </el-form-item>
      <el-form-item label="路由路径" prop="path">
        <el-input v-model="form.path" placeholder="请输入路由路径" />
      </el-form-item>
      <el-form-item label="组件路径" prop="component">
        <el-input v-model="form.component" placeholder="请输入组件路径，如：/basic/user/index" />
      </el-form-item>
      <el-form-item label="图标" prop="icon">
        <el-input v-model="form.icon" placeholder="请输入图标名称" />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="form.sort" :min="0" />
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
import { createMenu, updateMenu } from '@/api/menu'

const props = defineProps<{
  visible: boolean
  type: 'add' | 'edit'
  data: any
  parentId: number | null
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
  path: '',
  component: '',
  icon: '',
  sort: 0,
  parentId: null as number | null
})

const rules = {
  title: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }],
  path: [{ required: true, message: '请输入路由路径', trigger: 'blur' }]
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
  form.path = ''
  form.component = ''
  form.icon = ''
  form.sort = 0
  form.parentId = props.parentId
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
      await createMenu(form)
      ElMessage.success('新增成功')
    } else {
      await updateMenu(props.data.id, form)
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
