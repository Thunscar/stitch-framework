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
    business_type    char     default '0'               null comment '业务类型(0其他1新增2修改3删除)',
    method           varchar(50)                        null comment '方法名称',
    request_method   varchar(10)                        null comment '请求方式',
    operate_type     char     default '0'               null comment '操作类型(0其他1后台用户2手机端用户)',
    operate_user     varchar(100)                       null comment '操作人',
    dept_name        varchar(100)                       null comment '部门名称',
    operate_url      varchar(100)                       null comment '操作URL',
    operate_ip       varchar(128)                       null comment '操作IP',
    operate_location varchar(300)                       null comment '操作位置',
    operate_param    varchar(200)                       null comment '操作参数',
    json_result      varchar(300)                       null comment '返回结果',
    status           char     default '0'               null comment '状态(0正常1异常)',
    error_msg        varchar(300)                       null comment '错误消息',
    operate_time     datetime default CURRENT_TIMESTAMP null comment '操作时间'
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


INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (1, '账号登录-验证码开关', 'sys.account.captchaEnabled', 'false', '1', 'stitch', '2023-05-01 18:30:37', 'stitch', '2023-05-21 17:53:50', '0', '是否开启验证码功能（true开启，false关闭）');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (2, '账号登录-IP黑名单', 'sys.login.IpBlackList', NULL, '1', 'stitch', '2023-05-01 18:30:37', '', '2023-05-21 15:35:00', '0', '登录IP黑名单列表(多个IP通过\',\'分隔开)');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (3, '账号登录-初始密码', 'sys.login.initPassword', '001010', '1', 'stitch', '2023-05-01 18:30:37', '', '2023-05-21 15:35:00', '0', '新用户初始密码与重置密码');

INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (100, 0, '0', '总公司', 0, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (101, 100, '0,100', '深圳总公司', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (102, 100, '0,100', '武汉分公司', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (106, 101, '0,100,101', '销售部门', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (107, 101, '0,100,101', '财务部门', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (103, 102, '0,100,102', '研发部门', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (104, 102, '0,100,102', '测试部门', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete, create_user, create_time, update_user, update_time) VALUES (105, 102, '0,100,102', '运维部门', 3, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', '2023-05-01 00:00:00', null, null);

insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark)values (1,1,'正常','0','normal_status','primary','stitch',sysdate(),'',sysdate(),'0','系统默认状态:正常');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (2,2,'停用','1','normal_status','warning','stitch',sysdate(),'',sysdate(),'0','系统默认状态:停用');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (3,1,'内置','1','normal_system','primary','stitch',sysdate(),'',sysdate(),'0','字典是否内置:内置');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (4,2,'非内置','0','normal_system','warning','stitch',sysdate(),'',sysdate(),'0','字典是否内置:非内置');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (5,1,'正常','0','menu_status','primary','stitch',sysdate(),'',sysdate(),'0','菜单状态:正常');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (6,2,'停用','1','menu_status','warning','stitch',sysdate(),'',sysdate(),'0','菜单状态:停用');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (7,1,'男','0','user_gender','primary','stitch',sysdate(),'',sysdate(),'0','用户性别:男');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (8,2,'女','1','user_gender','warning','stitch',sysdate(),'',sysdate(),'0','用户性别:女');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (9,3,'未知','2','user_gender','danger','stitch',sysdate(),'',sysdate(),'0','用户性别:未知');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (10,1,'正常','0','user_status','primary','stitch',sysdate(),'',sysdate(),'0','用户状态:正常');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (11,2,'停用','1','user_status','warning','stitch',sysdate(),'',sysdate(),'0','用户状态:停用');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (12,1,'正常','0','role_status','primary','stitch',sysdate(),'',sysdate(),'0','角色状态:正常');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (13,2,'停用','1','role_status','warning','stitch',sysdate(),'',sysdate(),'0','角色状态:停用');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (14,1,'正常','0','post_status','primary','stitch',sysdate(),'',sysdate(),'0','岗位状态:正常');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, create_user, create_time, update_user, update_time, is_delete, remark) values (15,2,'停用','1','post_status','warning','stitch',sysdate(),'',sysdate(),'0','岗位状态:停用');

insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (1,'用户性别','user_gender','1','stitch',sysdate(),'',sysdate(),'用户性别列表','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (2,'用户状态','user_status','1','stitch',sysdate(),'',sysdate(),'用户状态列表','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (3,'菜单可用状态','menu_status','1','stitch',sysdate(),'',sysdate(),'菜单可用状态列表','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (4,'系统通用状态','normal_status','1','stitch',sysdate(),'',SYSDATE(),'系统通用状态列表','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (5,'字典是否内置','normal_system','1','stitch',sysdate(),'',SYSDATE(),'字典是否内置','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (6,'角色状态','role_status','1','stitch',sysdate(),'',SYSDATE(),'角色状态列表','0');
insert into sys_dict_type (dict_id, dict_name, dict_type, is_system, create_user, create_time, update_user, update_time, remark, is_delete) values (7,'岗位状态','post_status','1','stitch',sysdate(),'',SYSDATE(),'岗位状态列表','0');

INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (1, '系统管理', 0, 1, '/system', '', '', '0', '1', 'M', '1', '0', '', 'f-work', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '系统管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (2, '权限管理', 0, 2, '/perms', '', '', '0', '1', 'M', '1', '0', '', 'f-library', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '权限管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (3, '系统监控', 0, 3, '/monitor', '', '', '0', '1', 'M', '1', '0', '', 'f-monitor', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '系统监控目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (101, '菜单管理', 1, 1, '/system/menus', '/system/menu/index.vue', '', '0', '1', 'M', '1', '0', '', 'f-knowledge', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 13:38:01', '菜单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (102, '参数管理', 1, 2, '/system/config', '/system/config/index.vue', '', '0', '1', 'M', '1', '0', '', 'f-clue', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 13:29:58', '参数管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (103, '字典管理', 1, 3, '/system/dict', '/system/dict/index.vue', '', '0', '1', 'M', '1', '0', '', 'f-doc1', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '字典管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (104, '文件管理', 1, 4, '/system/file', '', '', '0', '1', 'M', '1', '0', '', 'f-doc', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '文件管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (105, '消息管理', 1, 5, '/system/message', '', '', '0', '1', 'M', '1', '0', '', 'f-Notification', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '消息管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (106, '用户管理', 2, 1, '/perms/user', '', '', '0', '1', 'M', '1', '0', '', 'f-client', '0', '', '2024-01-11 03:02:36', 'admin', '2024-02-11 21:54:07', '用户管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (107, '角色管理', 2, 2, '/perms/role', '', '', '0', '1', 'M', '1', '0', '', 'f-Eye', '0', '', '2024-01-11 03:02:36', 'admin', '2024-02-11 21:54:11', '角色管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (108, '部门管理', 2, 3, '/perms/dept', '', '', '0', '1', 'M', '1', '0', '', 'f-org', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '部门管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (109, '岗位管理', 2, 4, '/perms/post', '', '', '0', '1', 'M', '1', '0', '', 'f-YX-C', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '岗位管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (110, '系统日志', 3, 1, '/monitor/log', '', '', '0', '1', 'M', '1', '0', '', 'f-statistic', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '系统日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (111, '登录日志', 110, 1, '/monitor/log/loginlog', '', '', '0', '1', 'M', '1', '0', '', 'f-survey', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '登录日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (112, '操作日志', 110, 2, '/monitor/log/operlog', '', '', '0', '1', 'M', '1', '0', '', 'f-clue-pool', '0', '', '2024-01-11 03:02:36', 'admin', '2024-01-22 12:06:41', '操作日志菜单');

insert into sys_post values (1, 'GM', '总经理', 1, '0', '0', 'stitch', sysdate(), '', sysdate(), '');
insert into sys_post values (2, 'PM', '项目经理', 2, '0', '0', 'stitch', sysdate(), '', sysdate(), '');
insert into sys_post values (3, 'DE', '开发工程师', 3, '0', '0', 'stitch', sysdate(), '', sysdate(), '');
insert into sys_post values (4, 'TE', '测试工程师', 4, '0', '0', 'stitch', sysdate(), '', sysdate(), '');
insert into sys_post values (5, 'OE', '运维工程师', 5, '0', '0', 'stitch', sysdate(), '', sysdate(), '');

INSERT INTO sys_user VALUES (1, 100, 'stitch', 'stitch', '00', '486484645@qq.com', '18888888888', '0', 'https://blog.stitchcodes.com/images/navcode-avatar.jpg', '$2a$10$XxEpyZxUF7okn/dQOjhdmeJB7A8Mu.aMo4x7BOduXbhNe5YiWE8Ke', '0', '0', '127.0.0.1', '2024-02-29 15:23:45', 'stitch', '2024-01-11 00:38:43', 'stitch', '2024-02-29 15:23:44', '系统管理员');
INSERT INTO sys_user VALUES (2, 101, 'common', 'cccccc', '11', '123', '123', '1', null, '$2a$10$MCcK4I58qplKWYexg8M6lOh8ybGq3VoRKoq8n/jeCvX8ZBvFbIawC', '0', '0', '127.0.0.1', '2024-02-10 17:32:38', 'stitch', '2024-01-22 16:53:34', 'stitch', '2024-02-11 20:56:36', '123');


