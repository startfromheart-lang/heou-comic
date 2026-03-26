package com.heou.comic.controller;

import com.heou.comic.common.Result;
import com.heou.comic.entity.SystemConfig;
import com.heou.comic.mapper.*;
import com.heou.comic.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ComicTaskMapper comicTaskMapper;

    @Autowired
    private SystemConfigService systemConfigService;

    @Value("${system.name}")
    private String systemName;

    @Value("${system.version}")
    private String systemVersion;

    @GetMapping("/status")
    public Result<Map<String, Object>> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("name", systemName);
        status.put("version", systemVersion);
        status.put("notStartedCount", projectMapper.countByStatus(0));
        status.put("inProgressCount", projectMapper.countByStatus(1));
        status.put("interruptedCount", projectMapper.countByStatus(2));
        status.put("completedCount", projectMapper.countByStatus(3));
        status.put("runningTaskCount", comicTaskMapper.countRunningTasks());
        return Result.success(status);
    }

    @GetMapping("/config")
    public Result<List<SystemConfig>> getConfigs() {
        List<SystemConfig> configs = systemConfigService.list();
        return Result.success(configs);
    }

    @PostMapping("/config")
    public Result<Void> createConfig(@RequestBody SystemConfig config) {
        systemConfigService.save(config);
        return Result.success();
    }

    @PutMapping("/config/{id}")
    public Result<Void> updateConfig(@PathVariable Long id, @RequestBody SystemConfig config) {
        config.setId(id);
        systemConfigService.updateById(config);
        return Result.success();
    }

    @DeleteMapping("/config/{id}")
    public Result<Void> deleteConfig(@PathVariable Long id) {
        systemConfigService.removeById(id);
        return Result.success();
    }

    @GetMapping("/guide")
    public Result<String> getGuide() {
        String guide = "# AI漫画生成系统使用说明\n\n" +
                "## 1. 系统概述\n" +
                "本系统是一个基于AI技术的漫画生成平台，支持剧本管理、演员角色管理、场景管理和自动化漫画生成。\n\n" +
                "## 2. 基础管理\n" +
                "- 用户管理：管理系统用户，分配角色权限\n" +
                "- 角色管理：定义系统角色，分配菜单权限\n" +
                "- 菜单管理：配置系统菜单结构\n\n" +
                "## 3. 素材管理\n" +
                "- 项目管理：创建漫画项目，关联剧本、演员和场景\n" +
                "- 剧本管理：编写和管理漫画剧本\n" +
                "- 演员角色管理：定义漫画角色形象和性格\n" +
                "- 场景管理：定义漫画场景\n\n" +
                "## 4. 漫画生成\n" +
                "- 主题管理：查看热门选题，生成剧本\n" +
                "- 任务管理：管理漫画生成任务\n" +
                "- 漫画管理：浏览生成的漫画作品\n\n" +
                "## 5. 系统管理\n" +
                "- 系统状态：查看系统运行状态\n" +
                "- 系统配置：管理系统配置参数\n" +
                "- 使用说明：查看系统使用帮助\n";
        return Result.success(guide);
    }
}
