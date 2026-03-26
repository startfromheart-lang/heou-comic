-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    status INTEGER DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    role_code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    status INTEGER DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    menu_name VARCHAR(50) NOT NULL,
    parent_id INTEGER DEFAULT 0,
    menu_type INTEGER DEFAULT 1,
    path VARCHAR(200),
    component VARCHAR(200),
    permission VARCHAR(200),
    icon VARCHAR(100),
    sort INTEGER DEFAULT 0,
    status INTEGER DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

-- 角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    role_id INTEGER NOT NULL,
    menu_id INTEGER NOT NULL,
    PRIMARY KEY (role_id, menu_id)
);

-- 系统配置表
CREATE TABLE IF NOT EXISTS sys_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value TEXT NOT NULL,
    config_name VARCHAR(100),
    description VARCHAR(200),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 项目表
CREATE TABLE IF NOT EXISTS comic_project (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_name VARCHAR(100) NOT NULL,
    description TEXT,
    script_id INTEGER,
    status INTEGER DEFAULT 0,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 剧本表
CREATE TABLE IF NOT EXISTS comic_script (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    script_name VARCHAR(100) NOT NULL,
    content TEXT,
    status INTEGER DEFAULT 1,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 角色表（演员角色）
CREATE TABLE IF NOT EXISTS comic_character (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    character_name VARCHAR(50) NOT NULL,
    positioning VARCHAR(200),
    personality VARCHAR(200),
    image_url VARCHAR(500),
    status INTEGER DEFAULT 1,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 场景表
CREATE TABLE IF NOT EXISTS comic_scene (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    scene_name VARCHAR(100) NOT NULL,
    positioning VARCHAR(200),
    image_url VARCHAR(500),
    status INTEGER DEFAULT 1,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 项目角色关联表
CREATE TABLE IF NOT EXISTS project_character (
    project_id INTEGER NOT NULL,
    character_id INTEGER NOT NULL,
    PRIMARY KEY (project_id, character_id)
);

-- 项目场景关联表
CREATE TABLE IF NOT EXISTS project_scene (
    project_id INTEGER NOT NULL,
    scene_id INTEGER NOT NULL,
    PRIMARY KEY (project_id, scene_id)
);

-- 主题表
CREATE TABLE IF NOT EXISTS comic_theme (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    theme_name VARCHAR(100) NOT NULL,
    description TEXT,
    hot_type INTEGER DEFAULT 0,
    source VARCHAR(100),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 任务表
CREATE TABLE IF NOT EXISTS comic_task (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    task_name VARCHAR(100) NOT NULL,
    task_type INTEGER DEFAULT 0,
    project_id INTEGER,
    status INTEGER DEFAULT 0,
    progress INTEGER DEFAULT 0,
    result TEXT,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 漫画表
CREATE TABLE IF NOT EXISTS comic_book (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    comic_name VARCHAR(100) NOT NULL,
    project_id INTEGER,
    chapter_count INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    creator_id INTEGER,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 漫画章节表
CREATE TABLE IF NOT EXISTS comic_chapter (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    comic_id INTEGER NOT NULL,
    chapter_name VARCHAR(100) NOT NULL,
    chapter_order INTEGER NOT NULL,
    content TEXT,
    html_content TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 初始化超级管理员用户（密码：admin123）
INSERT OR IGNORE INTO sys_user (username, password, real_name, status) 
VALUES ('admin', '$2a$10$jw4fGSinTGbrogRrfKRmKOW.SRsAqDYRavloHmAeFT33QWBCo0wP2', '超级管理员', 1);

-- 初始化超级管理员角色
INSERT OR IGNORE INTO sys_role (role_name, role_code, description, status) 
VALUES ('超级管理员', 'SUPER_ADMIN', '拥有所有权限', 1);

-- 初始化用户角色关联
INSERT OR IGNORE INTO sys_user_role (user_id, role_id) VALUES (1, 1);