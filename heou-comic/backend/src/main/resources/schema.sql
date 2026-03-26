-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(20),
    status INTEGER DEFAULT 1,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 角色表
CREATE TABLE IF NOT EXISTS sys_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    path VARCHAR(100),
    component VARCHAR(100),
    icon VARCHAR(50),
    sort INTEGER DEFAULT 0,
    parent_id INTEGER,
    permission VARCHAR(100),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0,
    FOREIGN KEY (parent_id) REFERENCES sys_menu(id)
);

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS sys_user_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES sys_user(id),
    FOREIGN KEY (role_id) REFERENCES sys_role(id)
);

-- 角色菜单关联表
CREATE TABLE IF NOT EXISTS sys_role_menu (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_id INTEGER NOT NULL,
    menu_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES sys_role(id),
    FOREIGN KEY (menu_id) REFERENCES sys_menu(id)
);

-- 项目表
CREATE TABLE IF NOT EXISTS material_project (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    script_id INTEGER,
    status INTEGER DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 剧本表
CREATE TABLE IF NOT EXISTS material_script (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    content TEXT,
    description TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 演员角色表
CREATE TABLE IF NOT EXISTS material_actor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(100),
    personality VARCHAR(200),
    image_url VARCHAR(200),
    description TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 场景表
CREATE TABLE IF NOT EXISTS material_scene (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL,
    position VARCHAR(100),
    image_url VARCHAR(200),
    description TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 主题表
CREATE TABLE IF NOT EXISTS comic_topic (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    type VARCHAR(20),
    status INTEGER DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 漫画任务表
CREATE TABLE IF NOT EXISTS comic_task (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100) NOT NULL,
    project_id INTEGER,
    type INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    progress VARCHAR(20),
    result TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 漫画表
CREATE TABLE IF NOT EXISTS comic_comic (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(200) NOT NULL,
    project_id INTEGER,
    content TEXT,
    html_url VARCHAR(200),
    chapter_count INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 系统配置表
CREATE TABLE IF NOT EXISTS sys_config (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    config_key VARCHAR(100) NOT NULL UNIQUE,
    config_value TEXT,
    description TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 初始化数据
INSERT OR IGNORE INTO sys_user (id, username, password, nickname, status) VALUES 
(1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EO', '超级管理员', 1);

INSERT OR IGNORE INTO sys_role (id, name, code, description) VALUES 
(1, '超级管理员', 'super_admin', '拥有所有权限'),
(2, '普通用户', 'user', '普通用户权限');

INSERT OR IGNORE INTO sys_user_role (id, user_id, role_id) VALUES 
(1, 1, 1);

INSERT OR IGNORE INTO sys_menu (id, name, title, path, component, icon, sort, parent_id) VALUES 
(1, 'Basic', '基础管理', '/basic', NULL, 'Setting', 1, NULL),
(2, 'User', '用户管理', '/basic/user', '/basic/user/index', 'UserFilled', 1, 1),
(3, 'Role', '角色管理', '/basic/role', '/basic/role/index', 'User', 2, 1),
(4, 'Menu', '菜单管理', '/basic/menu', '/basic/menu/index', 'Menu', 3, 1),
(5, 'Material', '素材管理', '/material', NULL, 'FolderOpened', 2, NULL),
(6, 'Project', '项目管理', '/material/project', '/material/project/index', 'Folder', 1, 5),
(7, 'Script', '剧本管理', '/material/script', '/material/script/index', 'Document', 2, 5),
(8, 'Actor', '演员角色管理', '/material/actor', '/material/actor/index', 'Avatar', 3, 5),
(9, 'Scene', '场景管理', '/material/scene', '/material/scene/index', 'Picture', 4, 5),
(10, 'Comic', '漫画生成', '/comic', NULL, 'MagicStick', 3, NULL),
(11, 'Topic', '主题管理', '/comic/topic', '/comic/topic/index', 'Star', 1, 10),
(12, 'ComicTask', '任务管理', '/comic/task', '/comic/task/index', 'List', 2, 10),
(13, 'ComicList', '漫画管理', '/comic/list', '/comic/list/index', 'Reading', 3, 10),
(14, 'System', '系统管理', '/system', NULL, 'Tools', 4, NULL),
(15, 'Status', '系统状态', '/system/status', '/system/status/index', 'InfoFilled', 1, 14),
(16, 'Config', '系统配置', '/system/config', '/system/config/index', 'Setting', 2, 14),
(17, 'Guide', '使用说明', '/system/guide', '/system/guide/index', 'QuestionFilled', 3, 14);

-- 为超级管理员分配所有菜单
INSERT OR IGNORE INTO sys_role_menu (role_id, menu_id) 
SELECT 1, id FROM sys_menu;
