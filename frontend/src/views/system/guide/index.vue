<template>
  <div class="system-guide">
    <el-card>
      <template #header>
        <span>系统使用说明</span>
      </template>

      <el-steps direction="vertical" :active="6">
        <el-step title="系统概述">
          <template #description>
            <p>AI漫画生成系统是一个基于人工智能的漫画创作平台，支持从剧本创作到漫画生成的全流程管理。</p>
            <p>系统主要功能模块包括：基础管理、素材管理、漫画生成和系统管理四大模块。</p>
          </template>
        </el-step>

        <el-step title="快速开始">
          <template #description>
            <el-alert title="第一步：创建项目" type="info" class="guide-alert">
              <p>在「素材管理」→「项目管理」中点击「新增项目」，填写项目基本信息。</p>
            </el-alert>
            <el-alert title="第二步：准备素材" type="success" class="guide-alert">
              <p>创建或导入剧本、演员角色和场景素材。</p>
            </el-alert>
            <el-alert title="第三步：生成漫画" type="warning" class="guide-alert">
              <p>在项目详情页点击「生成漫画」按钮，等待AI生成完成。</p>
            </el-alert>
          </template>
        </el-step>

        <el-step title="基础管理模块">
          <template #description>
            <el-descriptions :column="1" border size="small">
              <el-descriptions-item label="用户管理">
                管理系统用户账号，支持增删改查操作
              </el-descriptions-item>
              <el-descriptions-item label="角色管理">
                管理系统角色权限，配置不同角色的菜单访问权限
              </el-descriptions-item>
              <el-descriptions-item label="菜单管理">
                配置系统菜单结构，支持动态调整菜单显示
              </el-descriptions-item>
            </el-descriptions>
          </template>
        </el-step>

        <el-step title="素材管理模块">
          <template #description>
            <el-descriptions :column="1" border size="small">
              <el-descriptions-item label="项目管理">
                漫画项目的核心管理入口，每个项目包含完整的漫画生成资料
              </el-descriptions-item>
              <el-descriptions-item label="剧本管理">
                创作和管理漫画剧本，剧本是漫画生成的基础
              </el-descriptions-item>
              <el-descriptions-item label="演员角色">
                定义漫画中的人物角色，包括角色名称、定位、性格和形象图片
              </el-descriptions-item>
              <el-descriptions-item label="场景管理">
                管理漫画场景素材，包括场景名称、定位和场景图片
              </el-descriptions-item>
            </el-descriptions>
          </template>
        </el-step>

        <el-step title="漫画生成模块">
          <template #description>
            <el-descriptions :column="1" border size="small">
              <el-descriptions-item label="主题管理">
                查看今日和本周热门选题，可基于热门主题直接生成剧本
              </el-descriptions-item>
              <el-descriptions-item label="任务管理">
                监控漫画生成任务和选题定时任务的执行状态，支持任务启停
              </el-descriptions-item>
              <el-descriptions-item label="漫画管理">
                浏览已生成的漫画作品，支持按章节在线阅读
              </el-descriptions-item>
            </el-descriptions>
          </template>
        </el-step>

        <el-step title="系统管理模块">
          <template #description>
            <el-descriptions :column="1" border size="small">
              <el-descriptions-item label="系统状态">
                查看系统运行状态、项目统计和任务执行情况
              </el-descriptions-item>
              <el-descriptions-item label="系统配置">
                配置系统参数，如AI模型选择、并发任务数限制等
              </el-descriptions-item>
              <el-descriptions-item label="使用说明">
                查看系统使用帮助文档
              </el-descriptions-item>
            </el-descriptions>
          </template>
        </el-step>

        <el-step title="注意事项">
          <template #description>
            <el-alert title="素材引用限制" type="warning" class="guide-alert">
              <p>已被项目引用的剧本、角色和场景不能直接删除，需先解除引用关系。</p>
            </el-alert>
            <el-alert title="超级管理员保护" type="error" class="guide-alert">
              <p>系统保护最后一个超级管理员用户不被删除，确保系统可正常管理。</p>
            </el-alert>
            <el-alert title="权限配置建议" type="info" class="guide-alert">
              <p>合理配置用户角色权限，遵循最小权限原则，确保系统安全。</p>
            </el-alert>
          </template>
        </el-step>
      </el-steps>
    </el-card>
  </div>
</template>

<script setup>
</script>

<style scoped>
.system-guide {
  padding: 0;
}

.guide-alert {
  margin-bottom: 15px;
}

:deep(.el-step__description) {
  padding-bottom: 20px;
}
</style>