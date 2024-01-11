package com.stitchcodes.core.service.impl;

import com.stitchcodes.common.exception.StitchException;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.core.domain.SysPost;
import com.stitchcodes.core.mapper.SysPostMapper;
import com.stitchcodes.core.service.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenwei
 * @description 针对表【sys_post(岗位信息表)】的数据库操作Service实现
 * @createDate 2023-04-28 13:20:02
 */
@Service
public class SysPostServiceImpl implements SysPostService {

    @Resource
    private SysPostMapper postMapper;


    @Override
    public List<SysPost> selectSysPost(SysPost sysPost) {
        return postMapper.selectPostList(sysPost);
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        return postMapper.deletePost(postIds);
    }

    @Override
    public int createSysPost(SysPost sysPost) {
        return postMapper.insertPost(sysPost);
    }

    @Override
    public void checkPostCodeUnique(SysPost sysPost) {
        SysPost post = postMapper.checkPostCodeUnique(sysPost);
        if (ObjectUtils.isNotNull(post) && post.getPostId().longValue() != sysPost.getPostId()) {
            throw new StitchException("岗位编码已存在");
        }
    }

    @Override
    public int updateSysPost(SysPost sysPost) {
        return postMapper.updatePost(sysPost);
    }

    @Override
    public SysPost selectSysPostById(Long postId) {
        return postMapper.selectSysPostById(postId);
    }
}




