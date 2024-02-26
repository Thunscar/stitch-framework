package com.stitchcodes.core.mapper;


import com.stitchcodes.core.domain.SysPost;

import java.util.List;

/**
 * @author stitch
 * @description 针对表【sys_post(岗位信息表)】的数据库操作Mapper
 * @createDate 2023-04-29 14:12:11
 */
public interface SysPostMapper {

    /**
     * 新增岗位
     *
     * @param sysPost 岗位信息
     * @return 结果
     */
    int insertPost(SysPost sysPost);

    /**
     * 删除岗位
     *
     * @param postId 岗位ID
     * @return 结果
     */
    int deletePost(Long[] postIds);

    /**
     * 更新岗位
     *
     * @param sysPost 岗位信息
     * @return 结果
     */
    int updatePost(SysPost sysPost);

    /**
     * 查询岗位信息
     *
     * @param sysPost 系统岗位信息
     * @return 结果
     */
    List<SysPost> selectPostList(SysPost sysPost);

    /**
     * 检查岗位编码唯一性
     * @param sysPost 岗位信息
     * @return
     */
    SysPost checkPostCodeUnique(SysPost sysPost);


    /**
     * 根据岗位ID查找岗位
     * @param postId 岗位ID
     * @return
     */
    SysPost selectSysPostById(Long postId);

    /**
     * 查询用户岗位
     * @param userId 用户id
     * @return
     */
    List<SysPost> selectSysPostByUserId(Long userId);

}




