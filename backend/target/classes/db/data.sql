-- 初始化超级管理员
INSERT INTO sys_user (username, password, nickname, email, phone, status, is_admin) VALUES
('admin', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '超级管理员', 'admin@heou.com', '13800000000', 1, 1);

-- 初始化普通用户
INSERT INTO sys_user (username, password, nickname, email, phone, status, is_admin) VALUES
('zhangsan', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '张三', 'zhangsan@heou.com', '13800000001', 1, 0),
('lisi', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '李四', 'lisi@heou.com', '13800000002', 1, 0),
('wangwu', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '王五', 'wangwu@heou.com', '13800000003', 1, 0),
('zhaoliu', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '赵六', 'zhaoliu@heou.com', '13800000004', 1, 0),
('sunqi', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '孙七', 'sunqi@heou.com', '13800000005', 1, 0),
('zhouba', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '周八', 'zhouba@heou.com', '13800000006', 1, 0),
('wujiu', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '吴九', 'wujiu@heou.com', '13800000007', 1, 0),
('zhengshi', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '郑十', 'zhengshi@heou.com', '13800000008', 1, 0),
('qianyi', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '钱一', 'qianyi@heou.com', '13800000009', 1, 0),
('chener', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '陈二', 'chener@heou.com', '13800000010', 1, 0),
('user11', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户11', 'user11@heou.com', '13800000011', 1, 0),
('user12', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户12', 'user12@heou.com', '13800000012', 1, 0),
('user13', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户13', 'user13@heou.com', '13800000013', 1, 0),
('user14', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户14', 'user14@heou.com', '13800000014', 1, 0),
('user15', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户15', 'user15@heou.com', '13800000015', 1, 0),
('user16', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户16', 'user16@heou.com', '13800000016', 1, 0),
('user17', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户17', 'user17@heou.com', '13800000017', 1, 0),
('user18', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户18', 'user18@heou.com', '13800000018', 1, 0),
('user19', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户19', 'user19@heou.com', '13800000019', 1, 0),
('user20', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户20', 'user20@heou.com', '13800000020', 1, 0),
('user21', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户21', 'user21@heou.com', '13800000021', 1, 0),
('user22', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户22', 'user22@heou.com', '13800000022', 1, 0),
('user23', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户23', 'user23@heou.com', '13800000023', 1, 0),
('user24', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户24', 'user24@heou.com', '13800000024', 1, 0),
('user25', '$2a$10$4Kjf.R3sl9kwBsCaznDXUOKLnJLk96w/Gj2lJafbvB5NHGo3o3Mxq', '用户25', 'user25@heou.com', '13800000025', 1, 0);

-- 初始化角色
INSERT INTO sys_role (role_name, role_code, description, status) VALUES
('超级管理员', 'SUPER_ADMIN', '拥有系统所有权限', 1),
('项目经理', 'PROJECT_MANAGER', '负责项目管理', 1),
('编剧', 'SCRIPT_WRITER', '负责剧本编写', 1),
('美术设计', 'ART_DESIGNER', '负责美术设计', 1),
('运营人员', 'OPERATOR', '负责运营管理', 1),
('普通用户', 'USER', '普通用户权限', 1),
('审核员', 'REVIEWER', '负责内容审核', 1),
('数据分析师', 'ANALYST', '负责数据分析', 1);

-- 初始化菜单
INSERT INTO sys_menu (parent_id, menu_name, menu_code, path, component, icon, sort_order, menu_type, visible, status) VALUES
(0, '基础管理', 'BASE_MANAGE', '/base', 'Layout', 'Setting', 1, 1, 1, 1),
(1, '用户管理', 'USER_MANAGE', '/base/user', 'base/UserManage', 'User', 1, 1, 1, 1),
(1, '角色管理', 'ROLE_MANAGE', '/base/role', 'base/RoleManage', 'UserFilled', 2, 1, 1, 1),
(1, '菜单管理', 'MENU_MANAGE', '/base/menu', 'base/MenuManage', 'Menu', 3, 1, 1, 1),
(0, '素材管理', 'MATERIAL_MANAGE', '/material', 'Layout', 'Files', 2, 1, 1, 1),
(5, '项目管理', 'PROJECT_MANAGE', '/material/project', 'material/ProjectManage', 'Folder', 1, 1, 1, 1),
(5, '剧本管理', 'SCRIPT_MANAGE', '/material/script', 'material/ScriptManage', 'Document', 2, 1, 1, 1),
(5, '演员角色管理', 'ACTOR_MANAGE', '/material/actor', 'material/ActorManage', 'Avatar', 3, 1, 1, 1),
(5, '场景管理', 'SCENE_MANAGE', '/material/scene', 'material/SceneManage', 'PictureFilled', 4, 1, 1, 1),
(0, '系统管理', 'SYSTEM_MANAGE', '/system', 'Layout', 'Tools', 3, 1, 1, 1),
(10, '系统状态', 'SYSTEM_STATUS', '/system/status', 'system/SystemStatus', 'Monitor', 1, 1, 1, 1),
(10, '系统配置', 'SYSTEM_CONFIG', '/system/config', 'system/SystemConfig', 'Setting', 2, 1, 1, 1),
(10, '使用说明', 'SYSTEM_HELP', '/system/help', 'system/SystemHelp', 'QuestionFilled', 3, 1, 1, 1),
(0, '漫画生成', 'COMIC_GENERATE', '/comic', 'Layout', 'MagicStick', 4, 1, 1, 1),
(14, '主题管理', 'TOPIC_MANAGE', '/comic/topic', 'comic/TopicManage', 'Star', 1, 1, 1, 1),
(14, '任务管理', 'TASK_MANAGE', '/comic/task', 'comic/TaskManage', 'List', 2, 1, 1, 1),
(14, '漫画管理', 'COMIC_MANAGE', '/comic/manage', 'comic/ComicManage', 'Picture', 3, 1, 1, 1),
(0, '财务管理', 'FINANCE_MANAGE', '/finance', 'Layout', 'Wallet', 5, 1, 1, 1),
(18, '消费记录', 'CONSUME_RECORD', '/finance/consume', 'finance/ConsumeRecord', 'Tickets', 1, 1, 1, 1),
(18, '充值记录', 'RECHARGE_RECORD', '/finance/recharge', 'finance/RechargeRecord', 'WalletFilled', 2, 1, 1, 1);

-- 初始化用户角色关联
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- 初始化角色菜单关联（超级管理员拥有所有菜单权限）
INSERT INTO sys_role_menu (role_id, menu_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
(1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20);

-- 初始化系统配置
INSERT INTO sys_config (config_key, config_value, config_name, description) VALUES
('SYSTEM_NAME', 'HEOU漫画生成系统', '系统名称', '系统的显示名称'),
('SYSTEM_VERSION', '1.0.0', '系统版本', '当前系统版本号'),
('AI_MODEL', 'gpt-4', 'AI模型', '使用的AI模型'),
('MAX_CONCURRENT_TASKS', '5', '最大并发任务数', '系统允许的最大并发任务数'),
('IMAGE_SIZE', '1024x1024', '图片尺寸', '生成漫画图片的默认尺寸'),
('STORAGE_PATH', '/data/comics', '存储路径', '漫画文件存储路径'),
('API_TIMEOUT', '30000', 'API超时时间', '外部API调用的超时时间（毫秒）'),
('MAX_RETRY_COUNT', '3', '最大重试次数', '任务失败后的最大重试次数'),
('TOPIC_UPDATE_TIME', '06:00', '主题更新时间', '每日主题更新时间'),
('WEEKLY_UPDATE_DAY', '1', '周主题更新日', '每周主题更新日（1-7）');

-- 初始化项目数据
INSERT INTO project (project_name, description, cover_image, status, current_step, total_steps, auto_mode, create_user_id) VALUES
('都市传说', '一个关于都市传说的悬疑漫画，讲述主角调查城市中神秘事件的故事。', '/images/project1.jpg', 1, 3, 10, 0, 2),
('星际冒险', '科幻冒险漫画，主角驾驶飞船探索未知星系的冒险故事。', '/images/project2.jpg', 1, 5, 12, 0, 3),
('校园恋曲', '青春校园恋爱漫画，讲述高中生之间纯真的爱情故事。', '/images/project3.jpg', 2, 8, 8, 1, 4),
('武林风云', '武侠漫画，讲述少年侠客闯荡江湖的传奇故事。', '/images/project4.jpg', 0, 1, 15, 0, 5),
('机甲战士', '机甲战斗漫画，未来世界中人类驾驶机甲保卫地球。', '/images/project5.jpg', 1, 2, 10, 0, 6),
('异世界转生', '异世界转生漫画，主角意外穿越到魔法世界的冒险故事。', '/images/project6.jpg', 3, 10, 20, 1, 7),
('侦探日记', '推理侦探漫画，天才侦探破解各种离奇案件的故事。', '/images/project7.jpg', 1, 4, 12, 0, 8),
('美食之旅', '美食漫画，主角环游世界寻找美食的故事。', '/images/project8.jpg', 0, 1, 8, 0, 9),
('运动少年', '运动漫画，讲述少年足球队追逐梦想的故事。', '/images/project9.jpg', 1, 6, 10, 0, 10),
('魔法学院', '魔法校园漫画，讲述魔法学院学生们的奇幻冒险。', '/images/project10.jpg', 1, 3, 15, 0, 11),
('末日求生', '末日生存漫画，人类在末日世界中求生存的故事。', '/images/project11.jpg', 2, 7, 12, 0, 12),
('古风言情', '古风言情漫画，古代宫廷中的爱恨情仇故事。', '/images/project12.jpg', 0, 1, 10, 0, 13),
('超级英雄', '超级英雄漫画，普通人获得超能力后拯救世界的故事。', '/images/project13.jpg', 1, 2, 8, 0, 14),
('灵异事件', '灵异恐怖漫画，讲述各种超自然灵异事件的故事。', '/images/project14.jpg', 1, 4, 10, 0, 15),
('职场风云', '职场漫画，讲述职场新人的成长与奋斗故事。', '/images/project15.jpg', 0, 1, 6, 0, 16),
('宠物日记', '萌宠漫画，讲述可爱宠物们的日常生活故事。', '/images/project16.jpg', 1, 5, 8, 0, 17),
('历史传奇', '历史漫画，讲述历史名人的传奇故事。', '/images/project17.jpg', 1, 3, 12, 0, 18),
('音乐梦想', '音乐漫画，讲述年轻人追逐音乐梦想的故事。', '/images/project18.jpg', 0, 1, 10, 0, 19),
('游戏世界', '游戏漫画，主角进入游戏世界的冒险故事。', '/images/project19.jpg', 1, 2, 15, 0, 20),
('奇幻森林', '奇幻冒险漫画，讲述森林中精灵与人类的友谊故事。', '/images/project20.jpg', 1, 4, 10, 0, 21),
('都市爱情', '都市言情漫画，讲述都市白领的爱情故事。', '/images/project21.jpg', 0, 1, 8, 0, 22),
('科幻未来', '科幻漫画，讲述未来科技世界的故事。', '/images/project22.jpg', 1, 6, 12, 0, 23),
('武侠江湖', '武侠漫画，讲述江湖侠客的恩怨情仇。', '/images/project23.jpg', 1, 3, 10, 0, 24),
('校园日常', '校园搞笑漫画，讲述学生们的搞笑日常。', '/images/project24.jpg', 0, 1, 6, 0, 25),
('奇幻冒险', '奇幻冒险漫画，讲述主角探索神秘世界的故事。', '/images/project25.jpg', 1, 2, 8, 0, 2);

-- 初始化剧本数据
INSERT INTO script (script_name, content, chapter_count, description, status, create_user_id) VALUES
('都市传说第一章', '主角小明是一名普通的上班族，某天晚上加班回家时，发现了一个神秘的电话亭...', 1, '都市传说开篇，主角发现神秘电话亭', 1, 3),
('都市传说第二章', '小明进入电话亭后，发现自己来到了一个平行世界，这里的一切都似曾相识却又完全不同...', 2, '主角进入平行世界', 1, 3),
('星际冒险序章', '2150年，人类已经掌握了星际旅行技术。年轻的飞行员艾拉即将开始她的第一次星际探险...', 1, '星际冒险开篇', 1, 4),
('星际冒险第一章', '艾拉的飞船在航行中遇到了神秘的空间风暴，被迫降落在一个未知的星球上...', 2, '飞船遇险降落未知星球', 1, 4),
('校园恋曲第一章', '新学期开始，转学生小雨来到了新的学校，她的到来改变了许多人的命运...', 1, '校园恋曲开篇', 1, 5),
('校园恋曲第二章', '小雨逐渐融入班级，与班长陆阳之间的感情也在悄然萌芽...', 2, '主角感情萌芽', 1, 5),
('武林风云序章', '江湖传言，失传已久的武林秘籍重现人间，各路英雄豪杰纷纷出动...', 1, '武林风云开篇', 1, 6),
('武林风云第一章', '少年剑客林风偶然得到秘籍残卷，从此踏上了江湖之路...', 2, '主角获得秘籍残卷', 1, 6),
('机甲战士第一章', '外星入侵者突然出现，人类最后的希望寄托在新型机甲上...', 1, '机甲战士开篇', 1, 7),
('机甲战士第二章', '年轻的机甲驾驶员小杰展现出惊人的天赋，成为人类反击的关键...', 2, '主角展现驾驶天赋', 1, 7),
('异世界转生序章', '普通上班族意外身亡后，醒来发现自己转生到了一个魔法世界...', 1, '异世界转生开篇', 1, 8),
('异世界转生第一章', '主角发现自己拥有了特殊能力，开始了在异世界的冒险...', 2, '主角发现特殊能力', 1, 8),
('侦探日记第一章', '天才侦探李明接到一封神秘的委托信，信中描述了一个离奇的失踪案...', 1, '侦探日记开篇', 1, 9),
('侦探日记第二章', '李明深入调查，发现案件背后隐藏着一个巨大的阴谋...', 2, '案件调查深入', 1, 9),
('美食之旅第一章', '美食家小陈踏上了环游世界寻找美食的旅程，第一站是日本...', 1, '美食之旅开篇', 1, 10),
('美食之旅第二章', '在日本品尝了各种美食后，小陈决定前往下一个目的地意大利...', 2, '日本美食之旅结束', 1, 10),
('运动少年第一章', '热爱足球的少年小强加入了学校足球队，开始了追逐梦想的旅程...', 1, '运动少年开篇', 1, 11),
('运动少年第二章', '小强在训练中遇到了困难，但在队友的帮助下逐渐进步...', 2, '主角训练进步', 1, 11),
('魔法学院第一章', '魔法学院迎来了一批新生，其中有一个没有魔法天赋的少年...', 1, '魔法学院开篇', 1, 12),
('魔法学院第二章', '没有天赋的少年发现了一种古老的魔法，从此命运改变...', 2, '主角发现古老魔法', 1, 12),
('末日求生第一章', '末日降临，幸存者们在废墟中艰难求生...', 1, '末日求生开篇', 1, 13),
('末日求生第二章', '主角小刚带领一群幸存者建立了庇护所，开始了新的生活...', 2, '建立庇护所', 1, 13),
('古风言情第一章', '古代宫廷中，公主与侍卫之间产生了禁忌的感情...', 1, '古风言情开篇', 1, 14),
('古风言情第二章', '公主与侍卫的感情被发现，两人面临着艰难的抉择...', 2, '感情被发现', 1, 14),
('超级英雄第一章', '普通大学生意外获得了超能力，从此开始了超级英雄的生涯...', 1, '超级英雄开篇', 1, 15);

-- 初始化演员角色数据
INSERT INTO actor (actor_name, positioning, personality, image_url, description, status, create_user_id) VALUES
('小明', '主角', '勇敢、正义、善良，有时有些冲动', '/images/actor1.jpg', '都市传说的男主角，普通上班族', 1, 3),
('艾拉', '主角', '聪明、冷静、有冒险精神', '/images/actor2.jpg', '星际冒险的女主角，年轻飞行员', 1, 4),
('小雨', '主角', '温柔、善良、有些内向', '/images/actor3.jpg', '校园恋曲的女主角，转学生', 1, 5),
('陆阳', '主角', '阳光、开朗、有责任心', '/images/actor4.jpg', '校园恋曲的男主角，班长', 1, 5),
('林风', '主角', '正义、坚毅、有侠义心肠', '/images/actor5.jpg', '武林风云的男主角，少年剑客', 1, 6),
('小杰', '主角', '热血、勇敢、有正义感', '/images/actor6.jpg', '机甲战士的男主角，机甲驾驶员', 1, 7),
('李明', '主角', '聪明、冷静、观察力强', '/images/actor7.jpg', '侦探日记的男主角，天才侦探', 1, 9),
('小陈', '主角', '热爱美食、开朗、有冒险精神', '/images/actor8.jpg', '美食之旅的男主角，美食家', 1, 10),
('小强', '主角', '热血、坚韧、永不放弃', '/images/actor9.jpg', '运动少年的男主角，足球少年', 1, 11),
('小刚', '主角', '坚强、有领导力、保护欲强', '/images/actor10.jpg', '末日求生的男主角，幸存者领袖', 1, 13),
('公主', '主角', '美丽、善良、勇敢追求爱情', '/images/actor11.jpg', '古风言情的女主角，公主', 1, 14),
('侍卫', '主角', '忠诚、勇敢、深爱公主', '/images/actor12.jpg', '古风言情的男主角，宫廷侍卫', 1, 14),
('大学生', '主角', '普通、善良、有正义感', '/images/actor13.jpg', '超级英雄的主角，普通大学生', 1, 15),
('神秘人', '配角', '神秘、智慧、目的不明', '/images/actor14.jpg', '都市传说中的神秘角色', 1, 3),
('外星人', '反派', '冷酷、无情、侵略性强', '/images/actor15.jpg', '机甲战士的反派角色', 1, 7),
('黑帮老大', '反派', '阴险、狡诈、残忍', '/images/actor16.jpg', '侦探日记的反派角色', 1, 9),
('老师', '配角', '和蔼、智慧、关心学生', '/images/actor17.jpg', '校园恋曲的配角，班主任', 1, 5),
('师父', '配角', '严厉、慈祥、武功高强', '/images/actor18.jpg', '武林风云的配角，主角师父', 1, 6),
('队友', '配角', '热血、团结、互相支持', '/images/actor19.jpg', '运动少年的配角，足球队员', 1, 11),
('幸存者', '配角', '坚强、团结、互相帮助', '/images/actor20.jpg', '末日求生的配角，幸存者', 1, 13);

-- 初始化场景数据
INSERT INTO scene (scene_name, positioning, image_url, description, status, create_user_id) VALUES
('神秘电话亭', '都市传说', '/images/scene1.jpg', '夜晚街头的神秘电话亭，散发着诡异的光芒', 1, 3),
('平行世界街道', '都市传说', '/images/scene2.jpg', '平行世界的街道，似曾相识却又完全不同', 1, 3),
('宇宙飞船内部', '星际冒险', '/images/scene3.jpg', '高科技宇宙飞船的驾驶舱', 1, 4),
('未知星球', '星际冒险', '/images/scene4.jpg', '神秘的未知星球，有着奇异的植物和地貌', 1, 4),
('高中教室', '校园恋曲', '/images/scene5.jpg', '阳光明媚的高中教室', 1, 5),
('学校天台', '校园恋曲', '/images/scene6.jpg', '黄昏时分的学校天台，可以看到美丽的夕阳', 1, 5),
('古代客栈', '武林风云', '/images/scene7.jpg', '江湖中的客栈，各路英雄豪杰聚集之地', 1, 6),
('竹林', '武林风云', '/images/scene8.jpg', '幽静的竹林，适合练武修行', 1, 6),
('机甲驾驶舱', '机甲战士', '/images/scene9.jpg', '高科技机甲的驾驶舱', 1, 7),
('战场废墟', '机甲战士', '/images/scene10.jpg', '被战火摧毁的城市废墟', 1, 7),
('魔法学院', '魔法学院', '/images/scene11.jpg', '宏伟的魔法学院建筑', 1, 12),
('末日废墟', '末日求生', '/images/scene12.jpg', '末日后的城市废墟', 1, 13),
('古代宫殿', '古风言情', '/images/scene13.jpg', '宏伟的古代宫殿', 1, 14),
('现代城市', '超级英雄', '/images/scene14.jpg', '现代都市的夜景', 1, 15),
('日本街道', '美食之旅', '/images/scene15.jpg', '日本的传统街道，两旁是各种美食店', 1, 10),
('足球场', '运动少年', '/images/scene16.jpg', '阳光明媚的足球场', 1, 11),
('侦探事务所', '侦探日记', '/images/scene17.jpg', '充满神秘气息的侦探事务所', 1, 9),
('异世界森林', '异世界转生', '/images/scene18.jpg', '魔法世界中的神秘森林', 1, 8),
('意大利餐厅', '美食之旅', '/images/scene19.jpg', '意大利的传统餐厅', 1, 10),
('庇护所', '末日求生', '/images/scene20.jpg', '幸存者建立的庇护所', 1, 13);

-- 初始化主题数据
INSERT INTO topic (topic_title, topic_content, topic_type, heat_value, source, status) VALUES
('都市悬疑', '关于城市中神秘事件的悬疑故事，充满悬念和惊悚元素', 1, 9850, '热门榜单', 1),
('星际冒险', '太空探索和星际冒险的故事，展现宇宙的神秘与壮美', 1, 8720, '热门榜单', 1),
('校园恋爱', '青春校园恋爱故事，纯真美好的爱情令人向往', 1, 9200, '热门榜单', 1),
('武侠江湖', '江湖侠客的恩怨情仇，武侠精神的传承', 1, 7800, '热门榜单', 1),
('机甲战斗', '人类驾驶机甲保卫地球的故事，热血刺激', 1, 6500, '热门榜单', 1),
('异世界转生', '穿越到异世界的冒险故事，奇幻与冒险的结合', 1, 8900, '热门榜单', 1),
('侦探推理', '破解各种离奇案件的推理故事，烧脑刺激', 1, 7200, '热门榜单', 1),
('美食探索', '环游世界寻找美食的故事，美食与旅行的结合', 1, 5800, '热门榜单', 1),
('运动竞技', '追逐体育梦想的热血故事，展现拼搏精神', 1, 6200, '热门榜单', 1),
('魔法学院', '魔法学院中的奇幻冒险，魔法与友情的结合', 1, 7500, '热门榜单', 1),
('末日生存', '末日世界中求生存的故事，展现人类的坚韧', 2, 6800, '周榜', 1),
('古风言情', '古代宫廷中的爱恨情仇，唯美浪漫', 2, 8100, '周榜', 1),
('超级英雄', '普通人获得超能力后拯救世界的故事', 2, 7300, '周榜', 1),
('灵异恐怖', '各种超自然灵异事件的故事，惊悚刺激', 2, 5600, '周榜', 1),
('职场成长', '职场新人的成长与奋斗故事，贴近生活', 2, 4900, '周榜', 1);

-- 初始化任务数据
INSERT INTO task (task_name, task_type, project_id, topic_id, status, progress, create_user_id) VALUES
('都市传说漫画生成', 1, 1, 1, 1, 35, 2),
('星际冒险漫画生成', 1, 2, 2, 1, 50, 3),
('校园恋曲漫画生成', 1, 3, 3, 2, 100, 4),
('武林风云漫画生成', 1, 4, 4, 0, 0, 5),
('机甲战士漫画生成', 1, 5, 5, 1, 20, 6),
('异世界转生漫画生成', 1, 6, 6, 3, 80, 7),
('侦探日记漫画生成', 1, 7, 7, 1, 40, 8),
('美食之旅漫画生成', 1, 8, 8, 0, 0, 9),
('运动少年漫画生成', 1, 9, 9, 1, 60, 10),
('魔法学院漫画生成', 1, 10, 10, 1, 30, 11),
('主题更新任务', 2, NULL, NULL, 0, 0, 1),
('每日热门主题获取', 2, NULL, NULL, 0, 0, 1);

-- 初始化漫画数据
INSERT INTO comic (comic_name, project_id, description, cover_image, chapter_count, status, create_user_id) VALUES
('都市传说', 1, '一个关于都市传说的悬疑漫画', '/images/comic1.jpg', 5, 1, 2),
('星际冒险', 2, '科幻冒险漫画', '/images/comic2.jpg', 8, 1, 3),
('校园恋曲', 3, '青春校园恋爱漫画', '/images/comic3.jpg', 12, 2, 4),
('武林风云', 4, '武侠漫画', '/images/comic4.jpg', 3, 1, 5),
('机甲战士', 5, '机甲战斗漫画', '/images/comic5.jpg', 6, 1, 6),
('异世界转生', 6, '异世界转生漫画', '/images/comic6.jpg', 15, 2, 7),
('侦探日记', 7, '推理侦探漫画', '/images/comic7.jpg', 4, 1, 8),
('美食之旅', 8, '美食漫画', '/images/comic8.jpg', 2, 1, 9),
('运动少年', 9, '运动漫画', '/images/comic9.jpg', 7, 1, 10),
('魔法学院', 10, '魔法校园漫画', '/images/comic10.jpg', 5, 1, 11);

-- 初始化漫画章节数据
INSERT INTO comic_chapter (comic_id, chapter_name, chapter_order, content, html_content, image_count, status) VALUES
(1, '神秘电话亭', 1, '主角小明发现神秘电话亭的故事', '<html><body>第一章内容</body></html>', 10, 1),
(1, '平行世界', 2, '小明进入平行世界的故事', '<html><body>第二章内容</body></html>', 12, 1),
(1, '真相', 3, '揭开真相的故事', '<html><body>第三章内容</body></html>', 8, 1),
(2, '启程', 1, '艾拉开始星际冒险', '<html><body>第一章内容</body></html>', 15, 1),
(2, '未知星球', 2, '降落未知星球', '<html><body>第二章内容</body></html>', 18, 1),
(3, '转学生', 1, '小雨转学到新学校', '<html><body>第一章内容</body></html>', 10, 1),
(3, '相遇', 2, '小雨与陆阳相遇', '<html><body>第二章内容</body></html>', 12, 1),
(3, '心动', 3, '两人之间产生感情', '<html><body>第三章内容</body></html>', 14, 1),
(4, '江湖传说', 1, '武林秘籍重现江湖', '<html><body>第一章内容</body></html>', 16, 1),
(5, '入侵', 1, '外星人入侵地球', '<html><body>第一章内容</body></html>', 20, 1);

-- 初始化定时任务数据
INSERT INTO scheduled_task (task_name, task_type, cron_expression, task_params, status) VALUES
('每日热门主题更新', 'TOPIC_DAILY', '0 0 6 * * ?', '{"type": "daily"}', 1),
('每周热门主题更新', 'TOPIC_WEEKLY', '0 0 6 ? * MON', '{"type": "weekly"}', 1),
('任务状态检查', 'TASK_CHECK', '0 */5 * * * ?', '{}', 1);
