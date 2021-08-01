package utils.ConfigReader;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private String propertyRelativePath = "/src/main/resources/config/Configuration.properties";

    private static Logger logger = Logger.getLogger(ConfigFileReader.class);

    public ConfigFileReader() {
        BufferedReader reader;
        String propertyFullPath = System.getProperty("user.dir") + propertyRelativePath;
        logger.info("Find properties file at:" + propertyFullPath);
        try {

            reader = new BufferedReader(new FileReader(propertyFullPath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                logger.error("Configuration properties file NOT FOUND at:" + propertyFullPath);
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("Configuration properties file NOT FOUND at:" + propertyFullPath);
            throw new RuntimeException("Configuration.properties not found at " + propertyFullPath);
        }
    }


    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");

        if (driverPath != null && driverPath.length() != 0) {
            logger.info("Web Driver path is found from properties file at:" + driverPath);
            return System.getProperty("user.dir") + driverPath;
        } else {
            logger.error("Web Driver path NOT found from properties file at:" + driverPath + "please check your properties file");
            throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
        }
    }

    public String getExcelPath() {
        String excelPath = properties.getProperty("excelPath");
        if (excelPath != null && excelPath.length() != 0) {
            logger.info("excel data file find at:" + excelPath);
            return System.getProperty("user.dir") + excelPath;
        } else {
            logger.error("excel data file NOT found,please check your properties file");
            throw new RuntimeException("excelPath not specified in the Configuration.properties file.");
        }
    }

    public String getExcelSheetName() {
        String excelSheetName = properties.getProperty("excelSheetName");
        if (excelSheetName != null && excelSheetName.length() != 0) {
            logger.info("excel sheet name is:" + excelSheetName);
            return excelSheetName;
        } else {
            logger.error("please check your excel sheet name from properties file");
            throw new RuntimeException("excelSheetName not specified in the Configuration.properties file.");
        }
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null && implicitlyWait.length() != 0) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null && url.length() != 0) {
            logger.info("the url find from properties file is:" + url);
            return url;
        } else {
            logger.error("url is not find ,please check your properties file");
            throw new RuntimeException("url not specified in the Configuration.properties file.");
        }
    }

    public String getHtmlReportPath() {
        String htmlReportPath = properties.getProperty("htmlReportPath");

        if (htmlReportPath != null && htmlReportPath.length() != 0) {
            logger.info("Extent html report path is found from properties file" + htmlReportPath);
            return System.getProperty("user.dir") + htmlReportPath;
        } else {
            logger.error("Extent html report path NOT found from properties file at:" + htmlReportPath + "please check your properties file");
            throw new RuntimeException("Extent html report path not specified in the Configuration.properties file.");
        }
    }


    public String getScreenshotPath() {
        String screenshotPath = properties.getProperty("screenshotPath");

        if (screenshotPath != null && screenshotPath.length() != 0) {
            logger.info("Screenshot path is found from properties file" + screenshotPath);
            return System.getProperty("user.dir") + screenshotPath;
        } else {
            logger.error("Screenshot path  NOT found from properties file at:" + screenshotPath + "please check your properties file");
            throw new RuntimeException("Screenshot path not specified in the Configuration.properties file.");
        }
    }

    public String getExcelReportPath() {
        String excelReportPath = properties.getProperty("excelReportPath");

        if (excelReportPath != null && excelReportPath.length() != 0) {
            logger.info("Screenshot path is found from properties file" + excelReportPath);
            return System.getProperty("user.dir") + excelReportPath;
        } else {
            logger.error("Excel report path  NOT found from properties file at:" + excelReportPath + "please check your properties file");
            throw new RuntimeException("Excel report path  not specified in the Configuration.properties file.");
        }
    }

    public String getIfTakeScreenshot() {
        String ifTakeScreenshot = properties.getProperty("ifTakeScreenshot");

        if (ifTakeScreenshot != null && ifTakeScreenshot.length() != 0) {
            logger.info("The swith of take screenshot properties file is:" + ifTakeScreenshot);
            return ifTakeScreenshot;
        } else {
            logger.error("The swith of take screenshot properties file is NULL or worng please check your properties file."
                    + "check this property:" + ifTakeScreenshot);
            throw new RuntimeException("The swith of take screenshot properties file is NULL or worng in properties file.");
        }
    }
}