package com.yxf.spiderstock.util;

import cn.hutool.core.date.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author yangxiangfeng
 * @date 2021/5/25 4:31 下午
 */
public class ExcelUtil {

    public static void main(String[] args) {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.createExcel();
    }

    public void createExcel() {
        HSSFWorkbook wb = new HSSFWorkbook();

        //首先创建字体样式
        HSSFFont font = boldFont(wb);

        //然后创建单元格样式style
        HSSFCellStyle style = wb.createCellStyle();
        // 将字体注入
        style.setFont(font);
        // 自动换行
        style.setWrapText(true);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 边框大小
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // 创建Sheet
        HSSFSheet sheet = wb.createSheet();
        // 设置每列宽度
//        sheet.setColumnWidth(0, 20 * 256);

        // 创建第一行表头
        HSSFRow row = sheet.createRow(0);
        row.setRowStyle(style);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("股票代码");
        cell = row.createCell(1);
        cell.setCellValue("股票名称");
        cell = row.createCell(2);
        cell.setCellValue("连板高度");
        cell = row.createCell(3);
        cell.setCellValue("涨停时间");
        cell = row.createCell(4);
        cell.setCellValue("最高换手");
        cell = row.createCell(5);
        cell.setCellValue("换手时间");
        cell = row.createCell(6);
        cell.setCellValue("60");
        cell = row.createCell(7);
        cell.setCellValue("70");
        cell = row.createCell(8);
        cell.setCellValue("80");
        cell = row.createCell(9);
        cell.setCellValue("龙虎榜");
        cell = row.createCell(10);
        cell.setCellValue("观察");
        cell.setCellStyle(style);

        // todo 数据数量
        int n = 0;
        for (int i = 1; i < n; i++) {
            row = sheet.createRow(i);
            // todo 输入每行数据

        }

        // 冻结表头
        sheet.createFreezePane(2, 1);

        // 输出
        try (FileOutputStream fos = new FileOutputStream(
                DateUtil.format(new Date(), "yyyyMMdd") + ".xls")){
            wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取粗宋体
     * @param wb
     * @return
     */
    private HSSFFont boldFont(HSSFWorkbook wb) {
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        return font;
    }


}
