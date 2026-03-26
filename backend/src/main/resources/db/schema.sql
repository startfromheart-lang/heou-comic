-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    avatar VARCHAR(255),
    status INTEGER DEFAULT 1,
    is_admin INTEGER DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_name VARCHAR(50) NOT NULL,
    role_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    status INTEGER DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    parent_id INTEGER DEFAULT 0,
    menu_name VARCHAR(50) NOT NULL,
    menu_code VARCHAR(50) NOT NULL,
    path VARCHAR(255),
    component VARCHAR(255),
    icon VARCHAR(50),
    sort_order INTEGER DEFAULT 0,
    menu_type INTEGER DEFAULT 1,
    visible INTEGER DEFAULT 1,
    status INTEGER DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    menu_id INTEGER NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 系统配置表
CREATE TABLE IF NOT EXISTS sys_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value TEXT,
    config_name VARCHAR(100),
    description VARCHAR(255),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 项目表
CREATE TABLE IF NOT EXISTS project (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_name VARCHAR(100) NOT NULL,
    description TEXT,
    cover_image VARCHAR(255),
    status INTEGER DEFAULT 0,
    current_step INTEGER DEFAULT 1,
    total_steps INTEGER DEFAULT 0,
    auto_mode INTEGER DEFAULT 0,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 项目步骤表
CREATE TABLE IF NOT EXISTS project_step (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER NOT NULL,
    step_name VARCHAR(100) NOT NULL,
    step_order INTEGER NOT NULL,
    description TEXT,
    assignee_id INTEGER,
    status INTEGER DEFAULT 0,
    auto_execute INTEGER DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 剧本表
CREATE TABLE IF NOT EXISTS script (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    script_name VARCHAR(100) NOT NULL,
    content TEXT,
    chapter_count INTEGER DEFAULT 0,
    description TEXT,
    status INTEGER DEFAULT 1,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 演员角色表
CREATE TABLE IF NOT EXISTS actor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    actor_name VARCHAR(100) NOT NULL,
    positioning VARCHAR(255),
    personality TEXT,
    image_url VARCHAR(255),
    description TEXT,
    status INTEGER DEFAULT 1,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 场景表
CREATE TABLE IF NOT EXISTS scene (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    scene_name VARCHAR(100) NOT NULL,
    positioning VARCHAR(255),
    image_url VARCHAR(255),
    description TEXT,
    status INTEGER DEFAULT 1,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 项目步骤素材关联表
CREATE TABLE IF NOT EXISTS project_step_material (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_step_id INTEGER NOT NULL,
    material_type INTEGER NOT NULL,
    material_id INTEGER NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 主题表
CREATE TABLE IF NOT EXISTS topic (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    topic_title VARCHAR(255) NOT NULL,
    topic_content TEXT,
    topic_type INTEGER DEFAULT 1,
    heat_value INTEGER DEFAULT 0,
    source VARCHAR(100),
    status INTEGER DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 任务表
CREATE TABLE IF NOT EXISTS task (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    task_name VARCHAR(100) NOT NULL,
    task_type INTEGER NOT NULL,
    project_id INTEGER,
    topic_id INTEGER,
    status INTEGER DEFAULT 0,
    progress INTEGER DEFAULT 0,
    result TEXT,
    error_msg TEXT,
    start_time DATETIME,
    end_time DATETIME,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 漫画表
CREATE TABLE IF NOT EXISTS comic (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    comic_name VARCHAR(100) NOT NULL,
    project_id INTEGER,
    description TEXT,
    cover_image VARCHAR(255),
    chapter_count INTEGER DEFAULT 0,
    status INTEGER DEFAULT 1,
    create_user_id INTEGER,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 漫画章节表
CREATE TABLE IF NOT EXISTS comic_chapter (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    comic_id INTEGER NOT NULL,
    chapter_name VARCHAR(100) NOT NULL,
    chapter_order INTEGER NOT NULL,
    content TEXT,
    html_content TEXT,
    image_count INTEGER DEFAULT 0,
    status INTEGER DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 漫画图片表
CREATE TABLE IF NOT EXISTS comic_image (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    chapter_id INTEGER NOT NULL,
    image_url VARCHAR(255),
    image_order INTEGER NOT NULL,
    prompt TEXT,
    description TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 定时任务表
CREATE TABLE IF NOT EXISTS scheduled_task (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    task_name VARCHAR(100) NOT NULL,
    task_type VARCHAR(50) NOT NULL,
    cron_expression VARCHAR(100),
    task_params TEXT,
    status INTEGER DEFAULT 0,
    last_execute_time DATETIME,
    next_execute_time DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);
