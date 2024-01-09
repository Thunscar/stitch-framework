package com.stitchcodes.common.utils.page;

import com.github.pagehelper.PageHelper;
import com.stitchcodes.common.utils.SqlUtils;

/**
 * @Author: stitch
 * @Date: 2023/4/30 14:59
 * @Description:
 */
public class PageUtil {

    /**
     * 开启分页
     */
    public static void startPage() {
        PageDomain pageDomain = PageDomain.buildByRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }


}
