package utils.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;

    public static String getReportName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String strDate = simpleDateFormat.format(date);
        System.out.println(strDate);
        return "TestingReport" + strDate + ".html";
    }

    public static ExtentReports createReport(String htmlReportPath) {

        String fileName = getReportName();
        System.out.println("---------report path------ " + htmlReportPath);
        new File(htmlReportPath).mkdirs();
        String reportFullName = htmlReportPath + fileName;

        spark = new ExtentSparkReporter(reportFullName);
        spark.config().setEncoding("utf-8");
        spark.config().setDocumentTitle("Automation Testing Report");
        spark.config().setReportName("My Testing Report");
        spark.config().setTheme(Theme.DARK);


        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "Club333");
        extent.setSystemInfo("Browser", "Google Chrome");
        extent.attachReporter(spark);
        System.out.println("---Extent Report extent:" + extent.toString() + "----");
        return extent;
    }

}
