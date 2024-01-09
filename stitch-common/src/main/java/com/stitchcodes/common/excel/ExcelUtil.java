package com.stitchcodes.common.excel;

import com.stitchcodes.common.annotation.Excel;
import com.stitchcodes.common.enums.ResultEnum;
import com.stitchcodes.common.utils.ConvertUtils;
import com.stitchcodes.common.utils.LogUtils;
import com.stitchcodes.common.utils.ObjectUtils;
import com.stitchcodes.common.utils.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.stitchcodes.common.constant.OperateConstants.EXPORT_EXCEL;

/**
 * @Author: stitch
 * @Date: 2023/5/2 14:17
 * @Description: 导出excel工具类
 */
public class ExcelUtil<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    private Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void exportExcel(String sheetName, List<T> data, HttpServletResponse response) throws IOException {
        List<Object[]> fields = new ArrayList<>();
        List<Field> tempFields = new ArrayList<>();
        //获取类型所有属性
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        //获取父类属性
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        //遍历属性
        for (Field tempField : tempFields) {
            //存在注解
            if (tempField.isAnnotationPresent(Excel.class)) {
                Excel excel = tempField.getAnnotation(Excel.class);
                if (excel != null) {
                    tempField.setAccessible(true);
                    fields.add(new Object[]{tempField, excel});
                }
            }
        }
        fields = fields.stream().sorted(Comparator.comparing(objs -> ((Excel) objs[1]).sort())).collect(Collectors.toList());
        exportExcel(sheetName, fields, data, response);
    }

    public void exportExcel(String sheetName, List<Object[]> fields, List<T> data, HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = null;
        try {
            //创建工作簿
            wb = new HSSFWorkbook();
            HSSFCellStyle headStyle = wb.createCellStyle();
            HSSFFont headFont = wb.createFont();
            headFont.setBold(true);
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            headStyle.setFont(headFont);
            //创建sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            //创建表头
            HSSFRow headRow = sheet.createRow(0);
            HSSFCell indexCell = headRow.createCell(0);
            indexCell.setCellStyle(headStyle);
            indexCell.setCellValue("序号");
            for (int i = 0; i < fields.size(); i++) {
                Excel excel = (Excel) fields.get(i)[1];
                String cellValue = StringUtils.isEmpty(excel.readConvertExp()) ? excel.name() : excel.name() + excel.readConvertExp();
                HSSFCell cell = headRow.createCell(i + 1);
                cell.setCellStyle(headStyle);
                cell.setCellValue(cellValue);
                sheet.setColumnWidth(i + 1, (int) excel.width() * 256);
            }
            //写入List数据
            for (int i = 0; i < data.size(); i++) {
                HSSFRow dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(i + 1);
                for (int j = 0; j < fields.size(); j++) {
                    Field field = (Field) fields.get(j)[0];
                    Excel excel = (Excel) fields.get(j)[1];
                    Object value = null;
                    try {
                        Method method = clazz.getMethod(getFieldValueMethod(field.getName()));
                        value = method.invoke(data.get(i));
                    } catch (Exception e) {
                        log.error("Failed to Obtain The Value Of List Data");
                    }
                    if (value != null) {
                        //样式字体
                        HSSFCell cell = dataRow.createCell(j + 1);
                        HSSFCellStyle cellStyle = wb.createCellStyle();
                        cellStyle.setAlignment(excel.align());
                        cellStyle.setFillBackgroundColor(excel.backgroundColor().getIndex());
                        cellStyle.setFillBackgroundColor(excel.headerBackgroundColor().getIndex());
                        cellStyle.setFillForegroundColor(excel.headerColor().getIndex());
                        HSSFFont cellFont = wb.createFont();
                        cellFont.setColor(excel.color().getIndex());
                        cellStyle.setFont(cellFont);
                        cell.setCellStyle(cellStyle);
                        //值处理
                        Excel.ColumnType columnType = excel.cellType();
                        if (columnType == Excel.ColumnType.NUMERIC) {
                            cell.setCellValue(ConvertUtils.toInt(value));
                        } else if (columnType == Excel.ColumnType.STRING) {
                            String cellValue = value.toString();
                            if (value instanceof Date) {
                                SimpleDateFormat sdf = new SimpleDateFormat(excel.dateFormat());
                                cellValue = sdf.format(value);
                            }
                            cell.setCellValue(ConvertUtils.toString(cellValue));
                        }
                    }
                }
            }
            //设置响应头
            response.setContentType("application/vnd.ms-excel");
            String filename = UUID.randomUUID() + ".xls";
            //推送浏览器
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            log.error(LogUtils.format(EXPORT_EXCEL, "sheetName:[{}],T:[{}]", ResultEnum.FAILED, "export excel data failed"), sheetName, clazz.getName(), e);
        } finally {
            if (ObjectUtils.isNotNull(wb)) {
                wb.close();
            }
        }

    }

    public String getFieldValueMethod(String fieldName) {
        StringBuilder sb = new StringBuilder("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }
}
