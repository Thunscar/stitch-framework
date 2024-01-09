INSERT INTO sys_role (role_id, role_name, role_key, role_sort, data_scope,status, is_delete, create_user, create_time, update_user, update_time, remark) VALUES (1,'管理员','admin',0,'1','0','0','admin','2023-05-01 00:00:00','',null,'系统管理员');

INSERT INTO sys_user_role (user_id, role_id) VALUES (1,1)