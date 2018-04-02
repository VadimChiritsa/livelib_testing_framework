package livelib.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriter {

    private static Logger LOGGER = LogManager.getLogger(ExcelWriter.class);

    public static <T> void generateExcelDocument(String fileName, ArrayList<T> data) {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook();
            LOGGER.debug("Workbook was created");
            Sheet sheet = workbook.createSheet();
            LOGGER.debug("Sheet was created");
            List<String> fieldNames = getFieldNamesForClass(data.get(0).getClass());
            int rowCount = 0;
            int columnCount = 0;
            Row row = sheet.createRow(rowCount++);
            LOGGER.debug("Row for column names was created");
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldName);
            }
            Class<? extends Object> classz = data.get(0).getClass();
            for (T t : data) {
                row = sheet.createRow(rowCount++);
                LOGGER.debug("Row for book '" + ((Book) t).getName() + "' was created");
                columnCount = 0;
                for (String fieldName : fieldNames) {
                    Cell cell = row.createCell(columnCount);
                    LOGGER.debug("Cell for field " + fieldName + " of book '" + ((Book) t).getName() + "' was created");
                    setCellValue(classz, fieldName, cell, t);
                    LOGGER.debug("Cell for field " + fieldName + " of book '" + ((Book) t).getName() + "' was filled");
                    columnCount++;
                }
            }
            writeExcelDocument(fileName, workbook);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static <T> void setCellValue(Class classz, String fieldName, Cell cell, T t) throws Exception {
        Method method = null;
        try {
            method = classz.getMethod("get" + capitalize(fieldName));
        } catch (NoSuchMethodException nme) {
            method = classz.getMethod("get" + fieldName);
        }
        Object value = method.invoke(t, (Object[]) null);
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Long) {
                cell.setCellValue((Long) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            }
        }
        LOGGER.debug("Value was set to cell " + cell.getAddress().toString());
    }

    private static void writeExcelDocument(String filename, XSSFWorkbook workbook) {
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            LOGGER.debug("File " + filename + " was created");
            workbook.write(fos);
            LOGGER.debug("Workbook was written to file " + filename);
            fos.flush();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private static List<String> getFieldNamesForClass(Class<?> clazz) throws Exception {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            fieldNames.add(f.getName());
        }
        LOGGER.debug("Field names were received");
        return fieldNames;
    }

    private static String capitalize(String s) {
        return s.length() == 0 ? s : s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
