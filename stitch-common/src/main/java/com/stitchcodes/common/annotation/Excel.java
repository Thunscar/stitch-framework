package com.stitchcodes.common.annotation;

import com.stitchcodes.common.excel.ExcelHandlerAdapter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.*;
import java.math.BigDecimal;

/**
 * @Author: stitch
 * @Date: 2023/5/1 20:58
 * @Description: 自定义导出为Excel注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Excel {

    int sort() default 0;

    String name() default "";

    String dateFormat() default "yyyy-MM-dd";

    String dictType() default "";

    String readConvertExp() default "";

    String separator() default ",";

    int scale() default -1;

    int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    double height() default 14;

    double width() default 16;

    String suffix() default "";

    String defaultValue() default "";

    String prompt() default "";

    String[] combo() default {};

    boolean needMerge() default false;

    boolean isExport() default true;

    String targetAttr() default "";

    boolean isStatistics() default false;

    ColumnType cellType() default ColumnType.STRING;

    IndexedColors headerBackgroundColor() default IndexedColors.GREY_50_PERCENT;

    IndexedColors headerColor() default IndexedColors.WHITE;

    IndexedColors backgroundColor() default IndexedColors.WHITE;

    IndexedColors color() default IndexedColors.BLACK;

    HorizontalAlignment align() default HorizontalAlignment.CENTER;

    Class<?> handler() default ExcelHandlerAdapter.class;

    String[] args() default {};

    Type type() default Type.ALL;

    enum Type {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    enum ColumnType {

        NUMERIC(0), STRING(1), IMAGE(2);

        private final int value;

        ColumnType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
