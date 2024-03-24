package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysUser;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_user(系统用户表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysUserService {

    /**
     * 查询用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 查询已分配角色的用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectAllocatedUsers(SysUser sysUser);

    /**
     * 查询未分配角色的用户列表
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    List<SysUser> selectUnAllocatedUsers(SysUser sysUser);

    /**
     * 通过用户名查询用户
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
     * 通过用户名查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    String selectUserRoleGroup(String userName);

    /**
     * 通过用户名搜索用户岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    String selectUserPostGroup(String userName);

    /**
     * 检查用户名唯一性
     *
     * @param sysUser 用户信息
     */
    void checkUserNameUnique(SysUser sysUser);

    /**
     * 检查用户是否允许操作
     *
     * @param sysUser 用户信息
     */
    void checkUserAllowed(SysUser sysUser);

    /**
     * 检查用户数据权限
     *
     * @param userId 用户ID
     */
    void checkUserDataScope(Long userId);

    /**
     * 新增用户
     *
     * @param sysUser 用户信息
     * @return 是否新增成功
     */
    int insertUser(SysUser sysUser);

    /**
     * 注册用户
     *
     * @param sysUser 用户信息
     * @return 是否注册成功
     */
    boolean registerUser(SysUser sysUser);

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 是否更新成功
     */
    int updateUser(SysUser sysUser);

    /**
     * 用户授权角色
     *
     * @param userId 用户ID
     * @param roles  用户角色
     */
    void insertUserAuth(Long userId, Long[] roles);

    /**
     * 更新用户状态
     *
     * @param sysUser 用户信息
     * @return 跟新数据条数
     */
    int updateUserStatus(SysUser sysUser);

    /**
     * 更新用户信息
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUserProfile(SysUser sysUser);

    /**
     * 更新用户头像
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int updateUserAvatar(SysUser sysUser);

    /**
     * 重置用户密码
     *
     * @param sysUser 用户信息
     * @return 更新数据条数
     */
    int resetUserPassword(SysUser sysUser);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 更新数据条数
     */
    int resetUserPassword(String userName, String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 删除数据条数
     */
    int deleteUserById(Long userId);

    /**
     * 通过用户ID批量删除用户
     *
     * @param userIds 用户ID
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 导入用户信息
     *
     * @param sysUsers        用户信息列表
     * @param isUpdateSupport 若数据库中存在是否支持更新
     * @param operateName     操作人
     * @return 结果
     */
    String importUser(List<SysUser> sysUsers, boolean isUpdateSupport, String operateName);


    /**
     * 获取用户信息，屏蔽密码等信息
     *
     * @param userId 用户ID
     * @return
     */
    SysUser selectSafeUser(Long userId);

    /**
     * 分配角色
     * @param userId 角色ID
     * @param roleIds 角色ID列表
     * @return
     */
    int allocateRoles(Long userId, Long[] roleIds);

    /**
     * 取消分配角色
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return
     */
    int cancelAllocateRoles(Long userId, Long[] roleIds);


    /**
     * 清除用户登录缓存
     * @param userName 用户名
     */
    void clearUserLoginCache(String userName);



}
