package com.heou.comic.controller;

import com.heou.comic.common.PageResult;
import com.heou.comic.common.Result;
import com.heou.comic.entity.SysConfig;
import com.heou.comic.service.ProjectService;
import com.heou.comic.service.SysConfigService;
import com.heou.comic.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemController {

    private final SysConfigService sysConfigService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public SystemController(SysConfigService sysConfigService, ProjectService projectService, TaskService taskService) {
        this.sysConfigService = sysConfigService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/status")
    public Result<Map<String, Object>> getStatus() {
        Map<String, Object> status = new HashMap<>();
        SysConfig systemName = sysConfigService.getByKey("SYSTEM_NAME");
        status.put("systemName", systemName != null ? systemName.getConfigValue() : "HEOU漫画生成系统");
        status.put("totalProjects", projectService.count());
        status.put("pendingProjects", projectService.countByStatus(0));
        status.put("runningProjects", projectService.countByStatus(1));
        status.put("pausedProjects", projectService.countByStatus(2));
        status.put("completedProjects", projectService.countByStatus(3));
        status.put("runningTasks", taskService.countRunning());
        return Result.success(status);
    }

    @GetMapping("/config/list")
    public Result<PageResult<SysConfig>> configList(
            @RequestParam(required = false) String configKey,
            @RequestParam(required = false) String configName,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        List<SysConfig> list = sysConfigService.list(configKey, configName);
        int start = (int) ((current - 1) * size);
        int end = Math.min(start + size.intValue(), list.size());
        List<SysConfig> pageList = list.subList(start, end);
        PageResult<SysConfig> pageResult = PageResult.of(pageList, (long) list.size(), size, current);
        return Result.success(pageResult);
    }

    @GetMapping("/config/{id}")
    public Result<SysConfig> getConfigById(@PathVariable Long id) {
        return Result.success(sysConfigService.getById(id));
    }

    @PostMapping("/config")
    public Result<Void> saveConfig(@RequestBody SysConfig config) {
        sysConfigService.save(config);
        return Result.success();
    }

    @PutMapping("/config")
    public Result<Void> updateConfig(@RequestBody SysConfig config) {
        sysConfigService.update(config);
        return Result.success();
    }

    @DeleteMapping("/config/{id}")
    public Result<Void> deleteConfig(@PathVariable Long id) {
        sysConfigService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/help")
    public Result<String> getHelp() {
        String helpContent = """
            # HEOU漫画生成系统使用说明
            
            ## 1. 系统概述
            HEOU漫画生成系统是一个基于AI技术的漫画创作平台，支持从选题、剧本、角色设计到漫画生成的全流程自动化创作。
            
            ## 2. 功能模块
            
            ### 2.1 基础管理
            - **用户管理**：管理系统用户，包括新增、修改、删除、查询用户
            - **角色管理**：管理系统角色，分配菜单权限
            - **菜单管理**：管理系统菜单结构
            
            ### 2.2 素材管理
            - **项目管理**：创建和管理漫画项目，定义项目步骤和素材
            - **剧本管理**：编写和管理漫画剧本
            - **演员角色管理**：定义漫画中的角色形象和性格
            - **场景管理**：定义漫画中的场景
            
            ### 2.3 系统管理
            - **系统状态**：查看系统运行状态和统计数据
            - **系统配置**：配置系统参数
            - **使用说明**：查看系统使用帮助
            
            ### 2.4 漫画生成
            - **主题管理**：查看热门选题，生成剧本
            - **任务管理**：管理漫画生成任务
            - **漫画管理**：浏览已生成的漫画
            
            ## 3. 使用流程
            
            1. **创建项目**：在素材管理中创建新项目，定义项目名称、描述和步骤
            2. **准备素材**：编写剧本、定义角色和场景
            3. **关联素材**：将剧本、角色、场景关联到项目步骤
            4. **启动生成**：在项目详情页点击生成按钮，创建生成任务
            5. **查看进度**：在任务管理中查看任务进度
            6. **浏览漫画**：生成完成后，在漫画管理中浏览
            
            ## 4. 注意事项
            
            - 请确保项目素材完整后再启动生成任务
            - 系统支持自动模式和手动模式，自动模式会连续执行所有步骤
            - 如遇问题，请联系系统管理员
            """;
        return Result.success(helpContent);
    }
}
