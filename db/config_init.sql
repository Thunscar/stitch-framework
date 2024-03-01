INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (1, '账号登录-验证码开关', 'sys.account.captchaEnabled', 'false', '1', 'stitch', '2023-05-01 18:30:37', 'stitch', '2023-05-21 17:53:50', '0', '是否开启验证码功能（true开启，false关闭）');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (2, '账号登录-IP黑名单', 'sys.login.IpBlackList', NULL, '1', 'stitch', '2023-05-01 18:30:37', '', '2023-05-21 15:35:00', '0', '登录IP黑名单列表(多个IP通过\',\'分隔开)');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time, update_user, update_time, is_delete, remark) VALUES (3, '账号登录-初始密码', 'sys.login.initPassword', '001010', '1', 'stitch', '2023-05-01 18:30:37', '', '2023-05-21 15:35:00', '0', '新用户初始密码与重置密码');