package com.bar.common.poi;

import com.bar.common.exception.BusinessException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * excle工具类
 */
public class ExcelUtils {

    protected static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 导出临时文件
     *
     * @param response
     * @param request
     * @param fileName
     * @throws Exception
     */
    public static void exportTempFile(HttpServletResponse response, HttpServletRequest request, String fileName)
            throws Exception {
        // 文件名乱码
        String folder = System.getProperty("java.io.tmpdir");
        InputStream inStream = new FileInputStream(folder + File.separator + fileName);
        final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = null;
        if (userAgent != null && userAgent.indexOf("Firefox") != -1) {
            // firefox
            finalFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } else {
            // others
            finalFileName = URLEncoder.encode(fileName, "UTF-8");
        }
        // 输出Excel文件
        response.reset();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + finalFileName + ".xls");
        response.setContentType("application/msexcel");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void export(HttpServletResponse response, HttpServletRequest request, String excelFileName,
                              List<List<String>> excelList) throws Exception {
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet(excelFileName);


        for (int i = 0; i < excelList.size(); i++) {
            HSSFRow row2 = sheet.createRow(i);
            // 创建单元格并设置单元格内容
            List<String> col = excelList.get(i);
            for (int j = 0; j < col.size(); j++) {
                HSSFCell cell = row2.createCell(j);
                cell.setCellValue(col.get(j));
            }
        }
        // 文件名乱码
        final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = null;
        if (userAgent != null && userAgent.indexOf("Firefox") != -1) {
            // firefox
            finalFileName = new String(excelFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else {
            // others
            finalFileName = URLEncoder.encode(excelFileName, "UTF-8");
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + finalFileName + ".xls");
        response.setContentType("application/msexcel");

        wb.write(output);
        output.close();
        return;
    }

    public static void createExcel(HttpServletResponse response, HttpServletRequest request, String excelFileName,
                                   List<List<String>> excelList) throws Exception {
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("Sheet1");
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象

        for (int i = 0; i < excelList.size(); i++) {
            HSSFRow row2 = sheet.createRow(i);
            // 创建单元格并设置单元格内容
            List<String> col = excelList.get(i);
            for (int j = 0; j < col.size(); j++) {
                HSSFCell cell = row2.createCell(j);
                cell.setCellValue(col.get(j));
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                if (j != 7&&j<11) {
                    style.setFillForegroundColor(HSSFColor.YELLOW.index);
                    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cell.setCellStyle(style);
                }
            }
        }


        // 文件名乱码
        final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = null;
        if (userAgent != null && userAgent.indexOf("Firefox") != -1) {
            // firefox
            finalFileName = new String(excelFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else {
            // others
            finalFileName = URLEncoder.encode(excelFileName, "UTF-8");
        }
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + finalFileName + ".xls");
        response.setContentType("application/msexcel");

        wb.write(output);
        output.close();
    }

    public static void downLoadFile(HttpServletResponse response, HttpServletRequest request, String filePth) {
        try {
            // 得到当前路径
            // StringfilePath=request.getSession().getServletContext().getRealPath(File.separator);
            File temFile = new File(filePth);

            // 处理文件名得位置（若服务器为linux和windows的处理方法不同）
            String fileName = filePth.substring(filePth.lastIndexOf(File.separator) + 1);
            final String userAgent = request.getHeader("USER-AGENT");
            if (userAgent != null && userAgent.indexOf("Firefox") != -1) {
                // firefox
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                // others
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }

            // 设置头文件，名称和内容的编码不同，否则会出现乱码。
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName);
            response.setContentType("application/msexcel");
//			response.setContentType("application/vnd.ms-excel;charset=utf-8");
            OutputStream ot = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(temFile));
            BufferedOutputStream bos = new BufferedOutputStream(ot);
            byte[] buffer = new byte[4096];
            int length = 0;
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.close();
            bis.close();
            ot.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<List<String>> importExcel(MultipartFile excel) throws IOException, InvalidFormatException {
        List<List<String>> excelList = new ArrayList<>();
        if (excel.isEmpty()) {
            throw new BusinessException("传人文件不能为空!");
        }
//		boolean isExcel2003 = excel.getOriginalFilename().matches("^.+\\.(?i)(xls)$");
        Workbook book = WorkbookFactory.create(excel.getInputStream());
//		Workbook book;
//		if (isExcel2003) {
//			book = new HSSFWorkbook(excel.getInputStream());
//		} else {
//			book = new XSSFWorkbook(excel.getInputStream());
//		}
        Sheet sheet = book.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        int temp=0;
        for (int i = 0; i < lastRowNum + 1; i++) {
            List<String> excelRow = new ArrayList<>();
            Row row = sheet.getRow(i);
            if(i==0){
                temp=row.getLastCellNum();
            }
            if (row == null) {
                continue;
            }
            for (int j = 0; j < temp; j++) {
                Cell keyCell = row.getCell(j);
                excelRow.add(keyCell == null ? "" : getValue(keyCell));
            }
            excelList.add(excelRow);
        }
        return excelList;
    }

    private static String getValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                double value = cell.getNumericCellValue();
                long intValue = (long) value;
                cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                break;
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
        }
        return cellValue;
    }

    /**
     * 当数据量大了分多个工作薄导出，初始导出5万条
     */
    public static void batchExport(HttpServletResponse response, HttpServletRequest request, String excelFileName,
                                   List<List<String>> excelList) throws Exception {
        // 内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。
        Workbook wb = new SXSSFWorkbook(1000); // 关键语句
        Sheet sheet = null; // 工作表对象
        Row nRow = null; // 行对象

        int rowNo = 0; // 总行号
        int pageRowNo = 0; // 页行号

        for (int i = 0; i < excelList.size() - 1; i++) {
            // 打印50000条后切换到下个工作表，可根据需要自行拓展，2百万，3百万...数据一样操作，只要不超过1048576就可以
            if (rowNo % 50000 == 0) {
                sheet = wb.createSheet("我的第" + (rowNo / 50000) + "个工作簿");// 建立新的sheet对象
                sheet = wb.getSheetAt(rowNo / 50000); // 动态指定当前的工作表
                nRow = sheet.createRow(0);
                // 创建单元格并设置表头
                List<String> firstRow = excelList.get(0);
                for (int j = 0; j < firstRow.size(); j++) {
                    nRow.createCell(j).setCellValue(firstRow.get(j));
                }
                pageRowNo = 1; // 每当新建了工作表就将当前工作表的行号重置为0
            }
            rowNo++;
            nRow = sheet.createRow(pageRowNo++); // 新建行对象
            // 创建单元格并设置单元格内容
            List<String> col = excelList.get(i + 1);
            for (int j = 0; j < col.size(); j++) {
                nRow.createCell(j).setCellValue(col.get(j));
            }
        }

        // 文件名乱码
        final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = null;
        if (userAgent != null && userAgent.indexOf("Firefox") != -1) {
            // firefox
            finalFileName = new String(excelFileName.getBytes("UTF-8"), "ISO-8859-1");
        } else {
            // others
            finalFileName = URLEncoder.encode(excelFileName, "UTF-8");
        }

        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + finalFileName + ".xlsx");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }


}
