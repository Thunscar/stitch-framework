package com.stitchcodes.core.service;

import com.stitchcodes.core.domain.SysPost;

import java.util.List;

/**
 * @author chenwei
 * @description 针对表【sys_post(岗位信息表)】的数据库操作Service
 * @createDate 2023-04-28 13:20:02
 */
public interface SysPostService {

    /**
     * 查询岗位
     *
     * @param sysPost 岗位信息
     * @return
     */
    List<SysPost> selectSysPost(SysPost sysPost);

    /**
     * 批量删除岗位
     *
     * @param postIds 岗位ID
     * @return
     */
    int deletePostByIds(Long[] postIds);

    /**
     * 创建岗位
     *
     * @param sysPost 岗位信息
     * @return
     */
    int createSysPost(SysPost sysPost);

    /**
     * 检查岗位编码唯一性
     *
     * @param sysPost 岗位信息
     * @return
     */
    void checkPostCodeUnique(SysPost sysPost);

    /**
     * 更新岗位信息
     *
     * @param sysPost 更新岗位信息
     * @return
     */
    int updateSysPost(SysPost sysPost);

    /**
     * 更新岗位ID查找岗位
     * @param postId 岗位ID
     * @return
     */
    SysPost selectSysPostById(Long postId);


}
