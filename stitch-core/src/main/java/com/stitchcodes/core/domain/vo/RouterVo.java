package com.stitchcodes.core.domain.vo;

import java.util.List;

/**
 * @Author: stitch
 * @Date: 2023/5/15 15:59
 * @Description: 路由视图
 */
public class RouterVo {

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 组件参数
     */
    private String query;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 是否展示
     */
    private boolean visible;

    /**
     * 永远展示嵌套子路由
     */
    private boolean alwaysShow;

    /**
     * 子路由
     */
    private List<RouterVo> children;

    /**
     * 路由详细信息
     */
    private MetaVo meta;

    public RouterVo(String name, String path, String component, String query, String redirect, boolean visible, boolean alwaysShow, List<RouterVo> children, MetaVo meta) {
        this.name = name;
        this.path = path;
        this.component = component;
        this.query = query;
        this.redirect = redirect;
        this.visible = visible;
        this.alwaysShow = alwaysShow;
        this.children = children;
        this.meta = meta;
    }

    public List<RouterVo> getChildren() {
        return children;
    }

    public void setChildren(List<RouterVo> children) {
        this.children = children;
    }

    public RouterVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public MetaVo getMeta() {
        return meta;
    }

    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }
}
