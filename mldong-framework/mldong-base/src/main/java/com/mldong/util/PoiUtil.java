package com.mldong.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.log.Log;
import com.mldong.base.PageParam;
import com.mldong.excel.IExcelColumnHandler;
import com.mldong.excel.IMyExcelExportServer;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 简单导入导出工具类
 * @author mldong
 * @date 2024/6/3
 */
public class PoiUtil {
    private PoiUtil() {}
    private static final Log log = Log.get();

    /**
     * 使用流的方式导出大数量的excel
     * @param excelName 要导出的文件名称，如Users.xlsx
     * @param excelExportServer 要导出的数据集合
     * @param param 查询参数
     */
    public static void exportExcelBigWithStream(String excelName, IMyExcelExportServer excelExportServer, PageParam<?> param){
        try {
            HttpServletResponse response = HttpServletUtil.getResponse();
            String fileName = URLEncoder.encode(excelName, CharsetUtil.UTF_8);
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            ExportParams exportParams = new ExportParams();
            exportParams.setDictHandler(SpringUtil.getBean(IExcelDictHandler.class));
            List<ExcelExportEntity> excelParams = new ArrayList<>();
            Map<String, IExcelColumnHandler> excelColumnHandlerMap = SpringUtil.getBeansOfType(IExcelColumnHandler.class);
            param.getExcelColumns().forEach(excelExportEntity -> {
                String handlerType = excelExportEntity.getHandlerType();
                if(StrUtil.isNotEmpty(handlerType)) {
                    IExcelColumnHandler excelColumnHandler = excelColumnHandlerMap.get(handlerType);
                    if(excelColumnHandler!=null) {
                        excelColumnHandler.handle(excelExportEntity);
                    }
                }
                excelParams.add(excelExportEntity);
            });
            Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, excelParams, new IExcelExportServer() {
                @Override
                public List<Object> selectListForExcelExport(Object o, int i) {
                    return excelExportServer.selectListForExcelExport(o, i);
                }
            }, param);
            setSizeColumn(workbook.getSheetAt(0));
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            log.error(">>> 导出数据异常：{}", e.getMessage());
        }
    }
    // 自适应宽度(中文支持)
    private static void setSizeColumn(Sheet sheet) {
        int maxColumn = sheet.getRow(sheet.getLastRowNum()).getPhysicalNumberOfCells();
        for (int columnNum = 0; columnNum <= maxColumn; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256 + 5;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }
}
