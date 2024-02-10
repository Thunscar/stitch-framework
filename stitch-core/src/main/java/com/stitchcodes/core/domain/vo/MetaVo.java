package com.stitchcodes.core.domain.vo;

/**
 * @Author: stitch
 * @Date: 2023/5/15 16:10
 * @Description: 路由展示信息
 */
public class MetaVo {

    /**
     * 路由展示信息
     */
    private String title;

    /**
     * 路由图标
     */
    private String icon;

    /**
     * 是否缓存
     */
    private boolean cache;

    /**
     * 是否是链接
     */
    private String link;

    public MetaVo() {
    }

    public MetaVo(String title, String icon, boolean cache, String link) {
        this.title = title;
        this.icon = icon;
        this.cache = cache;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
