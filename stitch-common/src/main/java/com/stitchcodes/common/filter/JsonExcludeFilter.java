package com.stitchcodes.common.filter;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

/**
 * @Author: stitch
 * @Date: 2024/3/27 00:38
 * @Description: JSON排除敏感字段
 */
public class JsonExcludeFilter extends SimplePropertyPreFilter {

    /**
     * 添加Json排除字段
     * @param excludes 需要排除的敏感字段
     * @return
     */
    public JsonExcludeFilter addExcludes(String... excludes) {
        for (String exclude : excludes) {
            this.getExcludes().add(exclude);
        }
        return this;
    }
}
