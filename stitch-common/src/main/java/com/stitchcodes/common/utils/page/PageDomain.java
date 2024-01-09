package com.stitchcodes.common.utils.page;

import com.stitchcodes.common.utils.ConvertUtils;
import com.stitchcodes.common.utils.ServletUtils;
import com.stitchcodes.common.utils.StringUtils;

import static com.stitchcodes.common.constant.RequestConstants.*;

/**
 * @Author: stitch
 * @Date: 2023/4/30 14:51
 * @Description:
 */
public class PageDomain {

    /**
     * 页码数量
     */
    private Integer pageNum;

    /**
     * 每页数据量
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排放方向（asc升序desc降序）
     */
    private String sort = "asc";

    /**
     * 是否需要分页合理化
     */
    private Boolean reasonable = true;

    public PageDomain() {
    }

    public PageDomain(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageDomain(Integer pageNum, Integer pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public PageDomain(Integer pageNum, Integer pageSize, String orderBy, String sort, Boolean reasonable) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.sort = sort;
        this.reasonable = reasonable;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderBy)) {
            return "";
        }
        return StringUtils.toUnderSourceCase(orderBy) + " " + sort;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getReasonable() {
        return reasonable;
    }

    public void setReasonable(Boolean reasonable) {
        this.reasonable = reasonable;
    }

    /**
     * 构建PageDomain
     *
     * @return pageDomain
     */
    public static PageDomain buildByRequest() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ConvertUtils.toInt(ServletUtils.getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(ConvertUtils.toInt(ServletUtils.getParameter(PAGE_SIZE), 10));
        pageDomain.setOrderBy(ConvertUtils.toString(ServletUtils.getParameter(ORDER_BY)));
        pageDomain.setSort(ConvertUtils.toString(ServletUtils.getParameter(SORT)));
        pageDomain.setReasonable(ConvertUtils.toBool(ServletUtils.getParameter(REASONABLE)));
        return pageDomain;
    }
}
