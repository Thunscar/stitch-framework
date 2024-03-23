INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time,
                        update_user, update_time, is_delete, remark)
VALUES (1, '账号登录-验证码开关', 'sys.account.captchaEnabled', 'false', '1', 'stitch', SYSDATE(), 'stitch', SYSDATE(),
        '0', '是否开启验证码功能（true开启，false关闭）');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time,
                        update_user, update_time, is_delete, remark)
VALUES (2, '账号登录-IP黑名单', 'sys.login.IpBlackList', NULL, '1', 'stitch', SYSDATE(), '', SYSDATE(), '0',
        '登录IP黑名单列表(多个IP通过\',\'分隔开)');
INSERT INTO sys_config (config_id, config_name, config_key, config_value, config_type, create_user, create_time,
                        update_user, update_time, is_delete, remark)
VALUES (3, '账号登录-初始密码', 'sys.login.initPassword', '001010', '1', 'stitch', SYSDATE(), '', SYSDATE(), '0',
        '新用户初始密码与重置密码');