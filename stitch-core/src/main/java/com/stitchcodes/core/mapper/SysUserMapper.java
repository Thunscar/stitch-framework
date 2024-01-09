package com.stitchcodes.core.mapper;

import com.stitchcodes.core.domain.SysUser;

import java.util.List;

/**
 * @author chenwei
 * @description 针对表【sys_user(系统用户表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 * @Entity generator.domain.SysUser
 */
public interface SysUserMapper {

    /**
     * 条件查询用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 条件查询已分配角色的用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectAllotedUserList(SysUser sysUser);

    /**
     * 条件查询未分配角色的用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectUnallotedUserList(SysUser sysUser);

    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @return 结果
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    SysUser selectUserById(Long userId);

    /**
     * 检查用户名唯一性
     *
     * @param userName 用户名
     * @return 结果
     */
    SysUser checkUserNameUnique(String userName);

    /**
     * 检查电话号码唯一性
     *
     * @param phone 电话
     * @return 结果
     */
    SysUser checkPhoneUnique(String phone);

    /**
     * 检查邮箱唯一性
     *
     * @param email 邮箱
     * @return 结果
     */
    SysUser checkEmailUnique(String email);

    /**
     * 新增用户
     *
     * @param sysUser 用户信息
     * @return 新增数据条数
     */
    int insertUser(SysUser sysUser);

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUser(SysUser sysUser);

    /**
     * 更新用户状态
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUserStatus(SysUser sysUser);

    /**
     * 更新用户头像
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUserAvatar(SysUser sysUser);

    /**
     * 更新用户密码
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUserPassword(SysUser sysUser);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 更新数据条数
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds 用户ID数据
     * @return 更新数据条数
     */
    int deleteUserByIds(Long[] userIds);

}




