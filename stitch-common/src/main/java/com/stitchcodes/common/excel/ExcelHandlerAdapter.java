package com.stitchcodes.common.excel;

/**
 * @Author: stitch
 * @Date: 2023/5/1 21:33
 * @Description:
 */
public interface ExcelHandlerAdapter {

    /**
     * 格式化Excel数据
     *
     * @param value 单元格数据值
     * @param args  excel注解的参数值
     * @return 格式化后的值
     */
    Object format(Object value, String[] args);
}
