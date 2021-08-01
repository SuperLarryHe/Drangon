package utils.TestManager;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.Reporter.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase{
    public static WebDriver driver;
//    public static ExtentTest test;
//    public static ExtentReports extent= ExtentManager.createReport();;
//    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

    //Method for adding logs passed from test cases
//    public void setExtentReportLog(String message) {
//        test.log(Status.INFO, message);//For extentTest HTML report
//
//    }

    public static WebDriver setUpBrowser() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\idea-space\\hydrgon\\src\\main\\resources\\webdrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.baidu.com");
        return driver;
    }

    public String takeScreenshot() {
        String fileName = getScreenName();
        String fileDir = System.getProperty("user.dir") + "/test-output/screenshot/";
        String screenFullName = fileDir + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File descFile = new File(screenFullName);
            FileUtils.copyFile(srcFile, descFile);
            System.out.println("***************");
            System.out.println("Screenshot:"+screenFullName);
            System.out.println("***************");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "../screenshot/"+fileName ;

    }

    public String getScreenName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String strDate = simpleDateFormat.format(date);
        System.out.println(strDate);
        return "Screenshot" + strDate + ".jpeg";
    }

}
