create table if not exists sys_config
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

create table if not exists sys_dept
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
    status      char default '0' null comment '部门状态(0正常1停用)',
    is_delete   char default '0' null comment '是否删除(0未删除1已删除)',
    create_user varchar(100)     null comment '创建人',
    create_time datetime         null comment '创建时间',
    update_user varchar(100)     null comment '更新人',
    update_time datetime         null comment '更新时间'
)
    comment '部门表';

create table if not exists sys_dict_data
(
    dict_code   bigint auto_increment comment '字典编码'
        primary key,
    dict_sort   int                                null comment '字典排序',
    dict_label  varchar(100)                       null comment '字典标签',
    dict_value  varchar(100)                       null comment '字典键值',
    dict_type   varchar(100)                       null comment '字典类型',
    css_class   varchar(100)                       null comment '样式属性',
    list_class  varchar(100)                       null comment '列表回显样式',
    is_default  char     default '0'               null comment '是否默认(0非默认1默认)',
    status      char     default '0'               null comment '状态(0正常1停用)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
);

create table if not exists sys_dict_type
(
    dict_id     bigint auto_increment comment '字典ID'
        primary key,
    dict_name   varchar(100)                       null comment '字典名称',
    dict_type   varchar(100)                       null comment '字典类型',
    status      char     default '0'               null comment '状态(0正常1停用)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '字典类型表';

create table if not exists sys_login_info
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

create table if not exists sys_menu
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
    status      char     default '0'               null comment '状态(0正常1停用)',
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

create table if not exists sys_notice
(
    notice_id      bigint auto_increment comment '通知ID'
        primary key,
    notice_title   varchar(100)                       not null comment '通知标题',
    notice_type    char                               not null comment '通知类型(0通知1公告)',
    notice_content longblob                           null comment '通知内容',
    status         char     default '0'               null comment '状态(0正常1关闭)',
    create_user    varchar(100)                       null comment '创建人',
    create_time    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user    varchar(100)                       null comment '更新人',
    update_time    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    remark         varchar(500)                       null comment '备注'
);

create table if not exists sys_operate_log
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

create table if not exists sys_post
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

create table if not exists sys_role_dept
(
    role_id bigint auto_increment comment '角色ID',
    dept_id bigint not null comment '部门ID',
    primary key (role_id,dept_id)
)
    comment '角色部门关联表';

create table if not exists sys_role
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

create table if not exists sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key(role_id, menu_id)
)
    comment '角色菜单表';

create table if not exists sys_user
(
    user_id     bigint auto_increment comment '用户ID'
        primary key,
    dept_id     bigint                             null comment '部门ID',
    user_name   varchar(100)                       null comment '用户账号',
    nike_name   varchar(100)                       null comment '用户昵称',
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

create table if not exists sys_user_post
(
    user_id bigint not null comment '用户ID'
        primary key,
    post_id bigint not null comment '岗位ID',
    constraint sys_user_post_pk2
        unique (post_id)
)
    comment '用户岗位关联表';

create table if not exists sys_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id,role_id)
)
    comment '用户角色关联表';

