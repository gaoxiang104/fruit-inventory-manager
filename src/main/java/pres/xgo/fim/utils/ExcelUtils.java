package pres.xgo.fim.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    private ExcelUtils() {
    }

    /**
     * 将上传的文件，转换成 Workbook
     *
     * @param filePath
     * @return
     */
    public static Workbook getWorkBook(String filePath) throws Exception {

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("上传的文件错误，无法获取文件名");
        }
        FileInputStream fis = new FileInputStream(new File(filePath));
        // 通过文件流创建工作簿对象
        Workbook workbook = WorkbookFactory.create(fis);
        return workbook;
    }

    /**
     * 获取Excel单元格值，并转换成string
     *
     * @param cell
     * @param pattern
     * @return
     */
    public static String getCellValue(Cell cell, String pattern) {
        String temp = "";
        if (cell == null) {
            return temp;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        cell.setCellType(CellType.STRING);
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    DateFormat df = new SimpleDateFormat(pattern);
                    return df.format(date);
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case FORMULA:
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();

            default:
                return temp;
        }
    }

    /**
     * 获取Excel单元格值，并转换成string
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        return getCellValue(cell, null);
    }
}
