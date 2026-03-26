<template>
  <el-dialog
    title="分配角色"
    v-model="visible"
    width="400px"
    @open="fetchRoles"
  >
    <el-checkbox-group v-model="selectedRoles">
      <el-checkbox
        v-for="role in roleList"
        :key="role.id"
        :label="role.id"
      >
        {{ role.name }}
      </el-checkbox>
    </el-checkbox-group>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitting">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { getRoleList } from '@/api/role'
import { getUserRoles, assignUserRoles } from '@/api/user'

const props = defineProps<{
  visible: boolean
  userId: number
}>()

const emit = defineEmits(['update:visible'])

const visible = computed({
  get: () => props.visible,
  set: (val) => emit('update:visible', val)
})

const roleList = ref([])
const selectedRoles = ref<number[]>([])
const submitting = ref(false)

const fetchRoles = async () => {
  try {
    const res = await getRoleList({ page: 1, size: 1000 })
    roleList.value = res.list
    const userRoles = await getUserRoles(props.userId)
    selectedRoles.value = userRoles.map((r: any) => r.id)
  } catch (error) {
    console.error(error)
  }
}

const handleSubmit = async () => {
  submitting.value = true
  try {
    await assignUserRoles(props.userId, selectedRoles.value)
    ElMessage.success('分配成功')
    visible.value = false
  } catch (error) {
    console.error(error)
  } finally {
    submitting.value = false
  }
}
</script>
