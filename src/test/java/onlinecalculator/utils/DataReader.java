package onlinecalculator.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private final Sheet excelWSheet;

    public DataReader(String sheetPath, String sheetName) throws IOException {
        this.excelWSheet = createExcelSheet(sheetPath, sheetName);
    }

    /**
     * Create an Excel worksheet
     *
     * @param sheetPath Path to Excel file
     * @param sheetName Name of the worksheet
     * @return Sheet object
     * @throws IOException Throws exception when the file doesn't exist
     */
    private Sheet createExcelSheet(String sheetPath, String sheetName) throws IOException {
        FileInputStream excelFile = new FileInputStream(sheetPath);
        Workbook excelWBook = WorkbookFactory.create(excelFile);

        // Check if the sheet with the specified name exists
        Sheet sheet = excelWBook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet with name '" + sheetName + "' not found in the workbook");
        }

        return sheet;
    }

    /**
     * Get the number of worksheet rows and cells and iterate through them, reading a data.
     *
     * @return Object[][] array with data
     */
    public Object[][] readExcel() {
        int lastRowNum = excelWSheet.getLastRowNum();
        int lastCellNum = excelWSheet.getRow(0).getLastCellNum();

        List<Integer> nonEmptyRows = new ArrayList<>();
        List<Integer> nonEmptyCells = new ArrayList<>();

        // Find non-empty rows
        for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
            Row row = excelWSheet.getRow(rowNum);
            if (row != null && !isEmptyRow(row)) {
                nonEmptyRows.add(rowNum);
            }
        }

        // Find non-empty cells
        for (int cellNum = 0; cellNum < lastCellNum; cellNum++) {
            boolean hasData = false;
            for (int rowNum : nonEmptyRows) {
                Row row = excelWSheet.getRow(rowNum);
                Cell cell = row.getCell(cellNum);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    hasData = true;
                    break;
                }
            }
            if (hasData) {
                nonEmptyCells.add(cellNum);
            }
        }

        // Process only non-empty rows and cells
        List<Object[]> dataList = new ArrayList<>();
        for (int rowNum : nonEmptyRows) {
            Row row = excelWSheet.getRow(rowNum);
            List<Object> rowData = new ArrayList<>();

            for (int cellNum : nonEmptyCells) {
                Cell cell = row.getCell(cellNum);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    try {
                        if (cell.getCellType() == CellType.STRING) {
                            rowData.add(cell.getStringCellValue());
                        } else {
                            rowData.add(cell.getNumericCellValue());
                        }
                    } catch (Exception ignored) {
                        // Handle the exception if needed
                    }
                }
            }

            dataList.add(rowData.toArray());
        }

        // Convert the List<Object[]> to Object[][] and return
        return dataList.toArray(new Object[0][0]);
    }

    private boolean isEmptyRow(Row row) {
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

}
