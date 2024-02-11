package com.stitchcodes.core.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author stitch
 * @description 针对表【sys_user_post(用户岗位关联表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 * @Entity generator.domain.SysUserPost
 */
public interface SysUserPostMapper {
    /**
     * 保存用户岗位关联关系
     *
     * @param userId  用户ID
     * @param postIds 岗位ID数组
     * @return
     */
    int insertUserPost(@Param("userId") Long userId, @Param("postIds") Long[] postIds);

    /**
     * 移除用户的岗位关联信息
     * @param userIds 用户ID数组
     * @return
     */
    int removeByUserIds(@Param("userIds") Long[] userIds);
}




