INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (100, 0, '0', '总公司', 0, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', SYSDATE(), 'stitch',
        SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (101, 100, '0,100', '深圳总公司', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', SYSDATE(),
        'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (102, 100, '0,100', '武汉分公司', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch', SYSDATE(),
        'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (106, 101, '0,100,101', '销售部门', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch',
        SYSDATE(), 'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (107, 101, '0,100,101', '财务部门', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch',
        SYSDATE(), 'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (103, 102, '0,100,102', '研发部门', 1, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch',
        SYSDATE(), 'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (104, 102, '0,100,102', '测试部门', 2, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch',
        SYSDATE(), 'stitch', SYSDATE());
INSERT INTO sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, is_delete,
                      create_user, create_time, update_user, update_time)
VALUES (105, 102, '0,100,102', '运维部门', 3, 'stitch', '18888888888', '888888888@qq.com', '0', '0', 'stitch',
        SYSDATE(), 'stitch', SYSDATE());
