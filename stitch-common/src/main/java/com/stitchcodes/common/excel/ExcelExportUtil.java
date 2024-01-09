package com.stitchcodes.common.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author: stitch
 * @Date: 2023/5/1 22:01
 * @Description:
 */
public class ExcelExportUtil {

    /**
     * @param sheetName 工作表的名字
     * @param column    列名
     * @param data      需要导出的数据    ( map的键定义为列的名字 一定要和column中的列明保持一致  )
     * @param response
     */
    public static void exportExcel(String sheetName, List<String> column, List<Map<String, Object>> data, HttpServletRequest request, HttpServletResponse response) {
        //创建工作薄
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        // 表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < column.size(); i++) {
            headRow.createCell(i).setCellValue(column.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int x = 0; x < column.size(); x++) {
                dataRow.createCell(x).setCellValue(data.get(i).get(column.get(x)) == null ? "" : data.get(i).get(column.get(x)).toString());
            }
        }

        response.setContentType("application/vnd.ms-excel");

        try {
            //获取浏览器名称
            String agent = request.getHeader("user-agent");
            String filename = sheetName + ".xls";
            //不同浏览器需要对文件名做特殊处理
            if (agent.contains("Firefox")) { // 火狐浏览器
                filename = "=?UTF-8?B?"
                        + new BASE64Encoder().encode(filename.getBytes("utf-8"))
                        + "?=";
                filename = filename.replaceAll("\r\n", "");
            } else { // IE及其他浏览器
                filename = URLEncoder.encode(filename, "utf-8");
                filename = filename.replace("+", " ");
            }
            //推送浏览器
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            hssfWorkbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
