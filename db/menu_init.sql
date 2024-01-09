create table stitch.sys_menu
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
    menu_type   char                               null comment '菜单类型(M目录C菜单F按钮)',
    visible     char     default '1'               null comment '是否展示(0不展示1展示)',
    status      char     default '0'               null comment '状态(0正常1停用)',
    perms       varchar(100)                       null comment '权限',
    icon        varchar(100)                       null comment '图标名称',
    is_delete   char     default '0'               null comment '是否删除(0未删除1已删除)',
    create_user varchar(100)                       null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_user varchar(100)                       null comment '更新人',
    update_time datetime                           null on update CURRENT_TIMESTAMP comment '更新时间',
    remark      varchar(500)                       null comment '备注'
)
    comment '系统菜单表';

