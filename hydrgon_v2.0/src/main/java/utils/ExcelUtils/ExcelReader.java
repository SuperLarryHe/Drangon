package utils.ExcelUtils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    private static XSSFSheet sheet;
    private static XSSFWorkbook book;
//    private static String path = "C:\\Users\\Bishop\\Downloads\\Sample_TestData.xlsx";

    private static Logger logger = Logger.getLogger(ExcelReader.class);


    public static XSSFWorkbook getExcelFile(String excelPath) {

        // Open the Excel file
        try {
            FileInputStream excelFile = new FileInputStream(excelPath);

            book = new XSSFWorkbook(excelFile);
            logger.info("Fetch excel file,the excel file path is:" + excelPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("FileNotFoundException,the excel file is NOT FOUND at:" + excelPath);
        } catch (IOException e) {
            logger.error("IOException,the excel file is NOT FOUND at:" + excelPath);
            e.printStackTrace();
        }
        return book;
    }

    public static XSSFSheet getExcelSheet(String sheetName) {
        sheet = book.getSheet(sheetName);
        return sheet;
    }

    public static int findRowIndexByTestCaseName(String testName) {
        int lastRowNum = sheet.getLastRowNum();
        int rowIndex = -1;

        for (int i = 0; i <= lastRowNum; i++) {
            if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testName)) {
                rowIndex = i;
                logger.info("Find test case:" + "[" + testName + "]" + "at row:" + rowIndex);
                return rowIndex;
            }

        }
        logger.info("Find test case:" + testName + "at row:" + rowIndex);
        return rowIndex;
    }

    public int findColumnIndexByHeader(String headerName) {
        int firstHeaderCell = sheet.getRow(0).getFirstCellNum();
        int lastHeaderCell = sheet.getRow(0).getLastCellNum();
        int headerColIndex = -1;

        for (int i = firstHeaderCell; i < lastHeaderCell; i++) {
            if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(headerName)) {
                headerColIndex = i;
                System.out.println("Current Column Index=" + headerColIndex);

            }

        }
        if (headerColIndex == -1) {
            logger.error("The excel column index is NOT FOUND,please check the header in excel.");

        } else {
            logger.info("Find" + "[" + headerName + "]" + "at column:" + headerColIndex);
        }
        return headerColIndex;
    }


    public static String getExcelCellData(String sheetName, String testCaseName, int columnOfTestData) {

        int rowNum = findRowIndexByTestCaseName(testCaseName);
        String strCellData = book.getSheet(sheetName).getRow(rowNum).getCell(columnOfTestData).getStringCellValue();
        logger.info("Test data in excel cell is:" + strCellData);
        return strCellData;
    }


    public static String getTestDataByKey(String sheetName, String testCaseName, int columnOfTestData, String key) {

        String jsonString = getExcelCellData(sheetName, testCaseName, columnOfTestData);
        String jsonStringLowerCase = jsonString.toLowerCase();
        JsonObject jsonObject = JsonParser.parseString(jsonStringLowerCase).getAsJsonObject();
        System.out.println(jsonObject.toString());
        String data = jsonObject.get(key).getAsString();
        logger.info("Find test data at Excel Sheet:" + sheetName + ",test case is:"
                + testCaseName + ", test data in column:" + columnOfTestData + ", key is:" + key);
        logger.info("Test data in excel cell by key is:" + data);
        return data;
    }

}
