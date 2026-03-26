# AI漫画生成系统

一个基于AI技术的漫画生成平台，支持剧本管理、演员角色管理、场景管理和自动化漫画生成。

## 技术栈

### 前端
- Vue 3 + TypeScript
- Vite
- Element Plus
- Pinia
- Vue Router
- Axios

### 后端
- JDK 17
- Spring Boot 3.2
- Spring Security
- MyBatis Plus
- SQLite
- JWT

## 项目结构

```
heou-comic/
├── frontend/          # 前端项目
│   ├── src/
│   │   ├── api/      # API接口
│   │   ├── assets/   # 静态资源
│   │   ├── components/ # 公共组件
│   │   ├── layouts/  # 布局组件
│   │   ├── router/   # 路由配置
│   │   ├── stores/   # Pinia状态管理
│   │   ├── utils/    # 工具函数
│   │   └── views/    # 页面视图
│   ├── package.json
│   └── vite.config.ts
├── backend/           # 后端项目
│   ├── src/main/java/com/heou/comic/
│   │   ├── config/   # 配置类
│   │   ├── controller/ # 控制器
│   │   ├── entity/   # 实体类
│   │   ├── mapper/   # 数据访问层
│   │   ├── service/  # 业务逻辑层
│   │   └── security/ # 安全配置
│   ├── src/main/resources/
│   │   └── schema.sql # 数据库初始化脚本
│   └── pom.xml
└── docker/           # Docker配置
    ├── Dockerfile-backend
    ├── Dockerfile-frontend
    ├── docker-compose.yml
    └── nginx.conf
```

## 功能模块

### 基础管理模块
- 用户管理：用户新增、修改、删除、查询、分配角色
- 角色管理：角色新增、修改、删除、查询、分配菜单
- 菜单管理：菜单新增、修改、删除、查询

### 素材管理模块
- 项目管理：项目新增、修改、删除、查询、生成漫画
- 剧本管理：剧本新增、修改、删除、查询
- 演员角色管理：演员角色新增、修改、删除、查询
- 场景管理：场景新增、修改、删除、查询

### 漫画生成模块
- 主题管理：今日热门、本周热门选题管理
- 任务管理：漫画任务管理、启动、停止
- 漫画管理：漫画列表、浏览

### 系统管理模块
- 系统状态：展示系统名称、项目统计、运行任务数
- 系统配置：配置增删改查
- 使用说明：系统使用帮助

## 快速开始

### 本地开发

#### 后端启动
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### 前端启动
```bash
cd frontend
npm install
npm run dev
```

访问 http://localhost:3000

默认账号：admin
默认密码：admin

### Docker部署

```bash
cd docker
docker-compose up -d
```

访问 http://localhost

## 约束条件

1. 不可以删除被角色或用户在权限中引用的菜单
2. 不可以删除被用户在权限中引用的角色
3. 不可以删除最后一个超级管理员用户
4. 不可以删除被项目引用的剧本、演员角色、场景

## 数据库配置

系统使用SQLite数据库，所有配置信息存储在数据库中。数据库文件默认存储在 `data/heou_comic.db`。

## 系统配置

所有配置信息都存储在 `sys_config` 表中，包括：
- AI模型配置
- 外部API配置
- 系统参数配置

## 许可证

MIT
