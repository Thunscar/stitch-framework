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



insert into sys_menu values (1,'系统管理',0,1,'/system','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'系统管理目录');
insert into sys_menu values (2,'权限管理',0,2,'/perms','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'权限管理目录');
insert into sys_menu values (3,'系统监控',0,3,'/monitor','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'系统监控目录');

insert into sys_menu values (101,'菜单管理',1,1,'/system/menu','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'菜单管理菜单');
insert into sys_menu values (102,'参数管理',1,2,'/system/config','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'参数管理菜单');
insert into sys_menu values (103,'字典管理',1,3,'/system/dict','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'字典管理菜单');
insert into sys_menu values (104,'文件管理',1,4,'/system/file','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'文件管理菜单');
insert into sys_menu values (105,'消息管理',1,5,'/system/message','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'消息管理菜单');

insert into sys_menu values (106,'用户管理',2,1,'/perms/user','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'用户管理菜单');
insert into sys_menu values (107,'角色管理',2,2,'/perms/role','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'角色管理菜单');
insert into sys_menu values (108,'部门管理',2,3,'/perms/dept','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'部门管理菜单');
insert into sys_menu values (109,'岗位管理',2,4,'/perms/post','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'岗位管理菜单');

insert into sys_menu values (110,'系统日志',3,1,'/monitor/log','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'系统日志菜单');
insert into sys_menu values (111,'登录日志',110,1,'/monitor/log/loginlog','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'登录日志菜单');
insert into sys_menu values (112,'操作日志',110,2,'/monitor/log/operlog','','','0','0','M','1','0','','','0','',sysdate(),'',sysdate(),'操作日志菜单');


