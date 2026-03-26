<template>
  <el-dialog
    title="分配菜单"
    v-model="visible"
    width="400px"
    @open="fetchMenus"
  >
    <el-tree
      ref="treeRef"
      :data="menuTree"
      show-checkbox
      node-key="id"
      :props="{ label: 'title', children: 'children' }"
      default-expand-all
    />
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit" :loading="submitting">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { getMenuTree } from '@/api/menu'
import { getRoleMenus, assignRoleMenus } from '@/api/role'

const props = defineProps<{
  visible: boolean
  roleId: number
}>()

const emit = defineEmits(['update:visible'])

const visible = computed({
  get: () => props.visible,
  set: (val) => emit('update:visible', val)
})

const treeRef = ref()
const menuTree = ref([])
const submitting = ref(false)

const fetchMenus = async () => {
  try {
    const res = await getMenuTree()
    menuTree.value = res
    const roleMenus = await getRoleMenus(props.roleId)
    const menuIds = roleMenus.map((m: any) => m.id)
    treeRef.value?.setCheckedKeys(menuIds)
  } catch (error) {
    console.error(error)
  }
}

const handleSubmit = async () => {
  const checkedKeys = treeRef.value?.getCheckedKeys()
  const halfCheckedKeys = treeRef.value?.getHalfCheckedKeys()
  const menuIds = [...checkedKeys, ...halfCheckedKeys]

  submitting.value = true
  try {
    await assignRoleMenus(props.roleId, menuIds)
    ElMessage.success('分配成功')
    visible.value = false
  } catch (error) {
    console.error(error)
  } finally {
    submitting.value = false
  }
}
</script>
