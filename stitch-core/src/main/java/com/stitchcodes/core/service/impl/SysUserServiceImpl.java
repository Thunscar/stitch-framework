package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.annotation.DataScope;
import com.stitchcodes.common.constant.UserConstants;
import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.CollectionUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.SpringUtils;
import com.stitchcodes.common.utils.encode.StitchPasswordEncoder;
import com.stitchcodes.core.domain.SysUser;
import com.stitchcodes.core.mapper.SysUserMapper;
import com.stitchcodes.core.service.SysUserService;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_user(系统用户表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
@EnableAspectJAutoProxy(exposeProxy = true)
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private StitchPasswordEncoder passwordEncoder;

    @Override
    @DataScope(deptAlias = "d")
    public List<SysUser> selectUserList(SysUser sysUser) {
        return userMapper.selectUserList(sysUser);
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser sysUser) {
        return userMapper.selectAllocatedUserList(sysUser);
    }

    @Override
    public List<SysUser> selectUnAllocatedList(SysUser sysUser) {
        return userMapper.selectUnallocatedUserList(sysUser);
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public String selectUserRoleGroup(String userName) {
        return null;
    }

    @Override
    public String selectUserPostGroup(String userName) {
        return null;
    }

    @Override
    public void checkUserNameUnique(SysUser sysUser) {
        Long userId = ObjectUtils.isNull(sysUser.getUserId()) ? -1L:sysUser.getUserId();
        SysUser queryUser = userMapper.checkUserNameUnique(sysUser.getUserName());
        if (ObjectUtils.isNotNull(queryUser) && queryUser.getUserId().longValue() != userId.longValue()) {
            throw new StitchException("用户名已存在");
        }
    }

    @Override
    public void checkUserAllowed(SysUser sysUser) {

    }

    @Override
    public void checkUserDataScope(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        List<SysUser> sysUsers = SpringUtils.getAopProxy(this).selectUserList(sysUser);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new StitchException("您没有访问该用户信息的权限");
        }
    }

    @Override
    public boolean insertUser(SysUser sysUser) {
        //用户密码加密
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        //设置非系统用户
        sysUser.setUserType(UserConstants.NON_SYSTEM_USER);
        return userMapper.insertUser(sysUser) > 0;
    }

    @Override
    public boolean registerUser(SysUser sysUser) {
        return false;
    }

    @Override
    public boolean updateUser(SysUser sysUser) {
        return userMapper.updateUser(sysUser) > 0;
    }

    @Override
    public void insertUserAuth(Long userId, Long[] roles) {

    }

    @Override
    public int updateUserStatus(SysUser sysUser) {
        return 0;
    }

    @Override
    public int updateUserProfile(SysUser sysUser) {
        return userMapper.updateUser(sysUser);
    }

    @Override
    public int updateUserAvatar(SysUser sysUser) {
        return 0;
    }

    @Override
    public int resetUserPassword(SysUser sysUser) {
        return userMapper.updateUserPassword(sysUser);
    }

    @Override
    public int resetUserPassword(String userName, String password) {
        return 0;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public boolean deleteUserByIds(Long[] userIds) {
        //需要同步删除用户关联信息
        return userMapper.deleteUserByIds(userIds) > 0;
    }

    @Override
    public String importUser(List<SysUser> sysUsers, boolean isUpdateSupport, String operateName) {
        return null;
    }
}




