drop table if exists sys_config;
create table sys_config
(
    config_id    bigint auto_increment comment '参数主键'
        primary key,
    config_name  varchar(100)                       null comment '参数名称',
    config_key   varchar(100)                       null comment '参数键',
    config_value varchar(500)                       null comment '参数值',
    config_type  char     default 'N'               null comment '参数类型是否内置(Y内置N非内置)',
    create_user  varchar(100)                       null comment '创建人',
    create_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user  varchar(100)                       null comment '更新人',
    update_time  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete    char     default '0'               null comment '是否删除(0未删除1已删除)',
    remark       varchar(500)                       null comment '备注'
)
    comment '系统配置表';

drop table if exists sys_dept;
create table sys_dept
(
    dept_id     bigint auto_increment comment '主键'
        primary key,
    parent_id   bigint           null comment '父级ID',
    ancestors   varchar(100)     null comment '祖级列表',
    dept_name   varchar(100)     null comment '部门名称',
    order_num   int  default 0   null comment '显示顺序',
    leader      varchar(100)     null comment '负责人',
    phone       varchar(20)      null comment '联系电话',
    email       varchar(50)      null comment '邮箱',
    status      char default '0' null comment '部门状态',
    is_delete   char default '0' null comment '是否删除(0未删除1已删除)',
    create_user varchar(100)     null comment '创建人',
    create_time datetime         null comment '创建时间',
    update_user varchar(100)     null comment '更新人',
    update_time datetime         null comment '更新时间'
)
    comment '部门表';

drop table if exists sys_dict_data;
create table sys_dict_data
(
    dict_code   bigint auto_increment comment '字典编码'
        primary key,
    dict_sort   int                                null comment '字典排序',
    dict_label  varchar(100)                       null comment '字典标签',
    dict_value  varchar(100)                       null comment '字典键值',
    dict_type   varchar(100)                       null comment '字典类型',
    css_class   varchar(100)                       null comment '样式属性',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   char     default '0'               null comment '是否删除(0未删除1已删除)',
    remark      varchar(500)                       null comment '备注'
);

drop table if exists sys_dict_type;
create table sys_dict_type
(
    dict_id     bigint auto_increment comment '字典ID'
        primary key,
    dict_name   varchar(100)                       null comment '字典名称',
    dict_type   varchar(100)                       null comment '字典类型',
    is_system   char     default '0'               null comment '是否系统内置(0非内置1内置)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   char     default '0'               null comment '是否删除(0未删除1已删除)',
    remark      varchar(500)                       null comment '备注'
)
    comment '字典类型表';

drop table if exists sys_login_info;
create table sys_login_info
(
    info_id        bigint auto_increment comment '信息ID'
        primary key,
    user_name      varchar(100)                       null comment '用户账号',
    ipaddr         varchar(128)                       null comment '登录IP',
    login_location varchar(300)                       null comment '登录地点',
    browser        varchar(100)                       null comment '登录浏览器',
    os             varchar(50)                        null comment '操作系统',
    operation      varchar(50)                        null comment '操作类型',
    status         char     default '0'               null comment '状态(0成功1失败)',
    msg            varchar(300)                       null comment '提示消息',
    login_time     datetime default CURRENT_TIMESTAMP null comment '登录时间'
);

drop table if exists sys_menu;
create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(100)                       null comment '菜单名称',
    parent_id   bigint                             null,
    order_num   int                                null comment '显示顺序',
    path        varchar(200)                       null comment '路由地址',
    component   varchar(300)                       null comment '组件地址',
    query       varchar(300)                       null comment '路由参数',
    is_frame    char     default '0'               null comment '是否外链(0否1是)',
    is_cache    char     default '0'               null comment '是否缓存(0缓存1不缓存)',
    menu_type   char                               null comment '菜单类型(M菜单B按钮)',
    visible     char     default '1'               null comment '是否展示(0不展示1展示)',
    status      char     default '0'               null comment '状态',
    perms       varchar(100)                       null comment '权限',
    icon        varchar(100)                       null comment '图标名称',
    is_delete   char     default '0'               null comment '是否删除(0未删除1已删除)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '系统菜单表';


drop table if exists sys_notice;
create table sys_notice
(
    notice_id      bigint auto_increment comment '通知ID'
        primary key,
    notice_title   varchar(100)                       not null comment '通知标题',
    notice_type    char                               not null comment '通知类型(0通知1公告)',
    notice_content longblob                           null comment '通知内容',
    status         char     default '0'               null comment '状态',
    create_user    varchar(100)                       null comment '创建人',
    create_time    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user    varchar(100)                       null comment '更新人',
    update_time    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark         varchar(500)                       null comment '备注'
);

drop table if exists sys_operate_log;
create table sys_operate_log
(
    log_id           bigint auto_increment comment '日志主键'
        primary key,
    title            varchar(100)                       null comment '模块标题',
    business_type    char     default '0'               null comment '业务类型(0其他1新增2修改3删除4导入5导出)',
    method           varchar(200)                       null comment '方法名称',
    request_method   varchar(10)                        null comment '请求方式',
    operate_type     char     default '0'               null comment '操作类型(0其他1后台用户2手机端用户)',
    operate_user     varchar(100)                       null comment '操作人',
    dept_name        varchar(100)                       null comment '部门名称',
    operate_url      varchar(100)                       null comment '操作URL',
    operate_ip       varchar(128)                       null comment '操作IP',
    operate_location varchar(300)                       null comment '操作位置',
    operate_param    varchar(2000)                      null comment '操作参数',
    json_result      varchar(2000)                      null comment '返回结果',
    status           char     default '0'               null comment '状态(0正常1异常)',
    error_msg        varchar(300)                       null comment '错误消息',
    operate_time     datetime default CURRENT_TIMESTAMP null comment '操作时间',
    cost_time        bigint                             null comment '执行耗时'
)
    comment '系统操作日志表';

drop table if exists sys_post;
create table sys_post
(
    post_id     bigint auto_increment comment '岗位ID'
        primary key,
    post_code   varchar(100)                       null comment '岗位编码',
    post_name   varchar(100)                       null comment '岗位名称',
    post_sort   int      default 0                 null comment '显示排序',
    status      char     default '0'               null comment '状态(0正常1停用)',
    is_delete   char     default '0'               null comment '是否删除(0未删除1已删除)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '岗位信息表';


drop table if exists sys_role_dept;
create table sys_role_dept
(
    role_id bigint auto_increment comment '角色ID',
    dept_id bigint not null comment '部门ID',
    primary key (role_id, dept_id)
)
    comment '角色部门关联表';


drop table if exists sys_role;
create table sys_role
(
    role_id     bigint auto_increment comment '角色ID'
        primary key,
    role_name   varchar(100)                       null comment '角色名称',
    role_key    varchar(100)                       null comment '角色权限字符串',
    role_sort   int      default 0                 null comment '显示顺序',
    data_scope  char     default '0'               null comment '数据范围(0全部数据权限1自定义数据权限2本部门数据权限3本部门以下数据权限4本人数据权限)',
    status      char     default '0'               null comment '状态(0正常1停用)',
    is_delete   char     default '0'               null comment '是否删除(0存在1已删除)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '角色信息表';


drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
)
    comment '角色菜单表';

drop table if exists sys_user;
create table sys_user
(
    user_id     bigint auto_increment comment '用户ID'
        primary key,
    dept_id     bigint                             null comment '部门ID',
    user_name   varchar(100)                       null comment '用户账号',
    nick_name   varchar(100)                       null comment '用户昵称',
    user_type   varchar(2)                         null comment '用户类型(00系统用户)',
    email       varchar(50)                        null comment '邮箱',
    phone       varchar(20)                        null comment '电话号码',
    sex         char                               null comment '用户性别(0男1女2未知)',
    avatar      varchar(100)                       null comment '头像',
    password    varchar(100)                       null comment '密码',
    status      char     default '0'               null comment '账号状态(0正常1停用)',
    is_delete   char     default '0'               null comment '是否删除',
    login_ip    varchar(128)                       null comment '最后登录IP',
    login_time  datetime                           null comment '最后登录时间',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '系统用户表';

drop table if exists sys_user_post;
create table sys_user_post
(
    user_id bigint not null comment '用户ID',
    post_id bigint not null comment '岗位ID',
    primary key (user_id, post_id)
)
    comment '用户岗位关联表';

drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户角色关联表';


INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (1, '系统管理', 0, 1, '/system', '', '', '0', '1', 'M', '1', '0', '', 'f-work', '0', '', SYSDATE(), 'stitch', SYSDATE(), '系统管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (2, '权限管理', 0, 2, '/perms', '', '', '0', '1', 'M', '1', '0', '', 'f-library', '0', '', SYSDATE(), 'stitch', SYSDATE(), '权限管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (3, '系统监控', 0, 3, '/monitor', '', '', '0', '1', 'M', '1', '0', '', 'f-monitor', '0', '', SYSDATE(), 'stitch', SYSDATE(), '系统监控目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (101, '菜单管理', 1, 1, '/system/menus', '/system/menu/index.vue', '', '0', '1', 'M', '1', '0', 'sys:menu:list', 'f-knowledge', '0', '', SYSDATE(), 'stitch', SYSDATE(), '菜单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (102, '参数管理', 1, 2, '/system/config', '/system/config/index.vue', '', '0', '1', 'M', '1', '0', 'sys:config:list', 'f-clue', '0', '', SYSDATE(), 'stitch', SYSDATE(), '参数管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (103, '字典管理', 1, 3, '/system/dict', '/system/dict/index.vue', '', '0', '1', 'M', '1', '0', 'sys:dict:list', 'f-doc1', '0', '', SYSDATE(), 'stitch', SYSDATE(), '字典管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (104, '文件管理', 1, 4, '/system/file', '', '', '0', '1', 'M', '1', '0', 'sys:file:list', 'f-doc', '0', '', SYSDATE(), 'stitch', SYSDATE(), '文件管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (105, '消息管理', 1, 5, '/system/message', '', '', '0', '1', 'M', '1', '0', 'sys:message:list', 'f-Notification', '0', '', SYSDATE(), 'stitch', SYSDATE(), '消息管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (106, '用户管理', 2, 1, '/perms/user', '', '', '0', '1', 'M', '1', '0', 'sys:user:list', 'f-client', '0', '', SYSDATE(), 'stitch', SYSDATE(), '用户管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (107, '角色管理', 2, 2, '/perms/role', '', '', '0', '1', 'M', '1', '0', 'sys:role:list', 'f-Eye', '0', '', SYSDATE(), 'stitch', SYSDATE(), '角色管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (108, '部门管理', 2, 3, '/perms/dept', '', '', '0', '1', 'M', '1', '0', 'sys:dept:list', 'f-org', '0', '', SYSDATE(), 'stitch', SYSDATE(), '部门管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (109, '岗位管理', 2, 4, '/perms/post', '', '', '0', '1', 'M', '1', '0', 'sys:post:list', 'f-YX-C', '0', '', SYSDATE(), 'stitch', SYSDATE(), '岗位管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (110, '系统日志', 3, 1, '/monitor/log', '', '', '0', '1', 'M', '1', '0', '', 'f-statistic', '0', '', SYSDATE(), 'stitch', SYSDATE(), '系统日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (111, '登录日志', 110, 1, '/monitor/log/loginlog', '', '', '0', '1', 'M', '1', '0', 'sys:loginlog:list', 'f-survey', '0', '', SYSDATE(), 'stitch', SYSDATE(), '登录日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (112, '操作日志', 110, 2, '/monitor/log/operlog', '', '', '0', '1', 'M', '1', '0', 'sys:operlog:list', 'f-clue-pool', '0', '', SYSDATE(), 'stitch', SYSDATE(), '操作日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (201, '在线用户', 3, 2, '/monitor/online', '', '', '0', '1', 'M', '1', '0', 'sys:online:list', 'f-clue-pool', '0', '', SYSDATE(), 'stitch', SYSDATE(), '在线用户统计');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (113, '查询', 101, 1, '', '', '', null, null, 'B', null,'0', 'sys:menu:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (114, '新增', 101, 2, '', '', '', null, null, 'B', null,'0', 'sys:menu:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (115, '修改', 101, 3, '', '', '', null, null, 'B', null, '0', 'sys:menu:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (116, '删除', 101, 4, '', '', '', null, null, 'B', null, '0', 'sys:menu:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (117, '查询', 102, 1, '', '', '', null, null, 'B', null, '0', 'sys:config:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (118, '新增', 102, 2, '', '', '', null, null, 'B', null, '0', 'sys:config:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (119, '修改', 102, 3, '', '', '', null, null, 'B', null, '0', 'sys:config:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (120, '删除', 102, 4, '', '', '', null, null, 'B', null, '0', 'sys:config:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (121, '导出', 102, 5, '', '', '', null, null, 'B', null, '0', 'sys:config:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (122, '刷新缓存', 102, 6, '', '', '', null, null, 'B', null, '0', 'sys:config:refresh', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '刷新缓存按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (123, '查询', 103, 1, '', '', '', null, null, 'B', null, '0', 'sys:dict:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (124, '新增', 103, 2, '', '', '', null, null, 'B', null, '0', 'sys:dict:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (125, '修改', 103, 3, '', '', '', null, null, 'B', null, '0', 'sys:dict:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (126, '删除', 103, 4, '', '', '', null, null, 'B', null, '0', 'sys:dict:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (127, '刷新缓存', 103, 5, '', '', '', null, null, 'B', null, '0', 'sys:dict:refresh', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '刷新缓存按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (128, '字典配置', 103, 6, '', '', '', null, null, 'B', null, '0', 'sys:dict:config', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '字典配置按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (129, '查询', 106, 1, '', '', '', null, null, 'B', null, '0', 'sys:user:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (130, '新增', 106, 2, '', '', '', null, null, 'B', null, '0', 'sys:user:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (131, '修改', 106, 3, '', '', '', null, null, 'B', null, '0', 'sys:user:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (132, '删除', 106, 4, '', '', '', null, null, 'B', null, '0', 'sys:user:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (133, '导入', 106, 5, '', '', '', null, null, 'B', null, '0', 'sys:user:import', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导入按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (134, '导出', 106, 6, '', '', '', null, null, 'B', null, '0', 'sys:user:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (135, '重置密码', 106, 7, '', '', '', null, null, 'B', null, '0', 'sys:user:resetPassword', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '重置密码按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (136, '账号解锁', 106, 8, '', '', '', null, null, 'B', null, '0', 'sys:user:unlock', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '账号解锁按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (137, '分配角色', 106, 9, '', '', '', null, null, 'B', null, '0', 'sys:user:allocatedRole', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '分配角色按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (138, '查询', 107, 1, '', '', '', null, null, 'B', null, '0', 'sys:role:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (139, '新增', 107, 2, '', '', '', null, null, 'B', null, '0', 'sys:role:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (140, '修改', 107, 3, '', '', '', null, null, 'B', null, '0', 'sys:role:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (141, '删除', 107, 4, '', '', '', null, null, 'B', null, '0', 'sys:role:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (142, '导出', 107, 5, '', '', '', null, null, 'B', null, '0', 'sys:role:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (143, '数据权限', 107, 6, '', '', '', null, null, 'B', null, '0', 'sys:role:dataScope', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '数据权限按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (144, '分配用户', 107, 7, '', '', '', null, null, 'B', null, '0', 'sys:role:allocatedUser', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '分配用户按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (145, '查询', 108, 1, '', '', '', null, null, 'B', null, '0', 'sys:dept:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (146, '新增', 108, 2, '', '', '', null, null, 'B', null, '0', 'sys:dept:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (147, '修改', 108, 3, '', '', '', null, null, 'B', null, '0', 'sys:dept:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (148, '删除', 108, 4, '', '', '', null, null, 'B', null, '0', 'sys:dept:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (149, '查询', 109, 1, '', '', '', null, null, 'B', null, '0', 'sys:post:query', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '查询按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (150, '新增', 109, 2, '', '', '', null, null, 'B', null, '0', 'sys:post:create', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '新增按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (151, '修改', 109, 3, '', '', '', null, null, 'B', null, '0', 'sys:post:update', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '修改按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (152, '删除', 109, 4, '', '', '', null, null, 'B', null, '0', 'sys:post:delete', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '删除按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (153, '导出', 109, 5, '', '', '', null, null, 'B', null, '0', 'sys:post:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (154, '导出', 111, 1, '', '', '', null, null, 'B', null, '0', 'sys:loginlog:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (155, '导出', 112, 1, '', '', '', null, null, 'B', null, '0', 'sys:operlog:export', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '导出按钮');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (156, '强制离线', 201, 1, '', '', '', null, null, 'B', null, '0', 'sys:online:offline', '', '0', 'stitch', SYSDATE(), 'stitch',SYSDATE(), '强制用户离线');

insert into sys_post
values (1, 'GM', '总经理', 1, '0', '0', 'stitch', SYSDATE(), '', SYSDATE(), '');
insert into sys_post
values (2, 'PM', '项目经理', 2, '0', '0', 'stitch', SYSDATE(), '', SYSDATE(), '');
insert into sys_post
values (3, 'DE', '开发工程师', 3, '0', '0', 'stitch', SYSDATE(), '', SYSDATE(), '');
insert into sys_post
values (4, 'TE', '测试工程师', 4, '0', '0', 'stitch', SYSDATE(), '', SYSDATE(), '');
insert into sys_post
values (5, 'OE', '运维工程师', 5, '0', '0', 'stitch', SYSDATE(), '', SYSDATE(), '');

INSERT INTO sys_user
VALUES (1, 100, 'stitch', 'stitch', '00', '486484645@qq.com', '18888888888', '0',
        'https://blog.stitchcodes.com/images/navcode-avatar.jpg',
        '$2a$10$XxEpyZxUF7okn/dQOjhdmeJB7A8Mu.aMo4x7BOduXbhNe5YiWE8Ke', '0', '0', '127.0.0.1', SYSDATE(), 'stitch',
        SYSDATE(), 'stitch', SYSDATE(), '系统管理员');
INSERT INTO sys_user
VALUES (2, 101, 'common', 'cccccc', '11', '123', '123', '1', null,
        '$2a$10$XxEpyZxUF7okn/dQOjhdmeJB7A8Mu.aMo4x7BOduXbhNe5YiWE8Ke', '0', '0', '127.0.0.1', SYSDATE(), 'stitch',
        SYSDATE(), 'stitch', SYSDATE(), '123');