package utils.TestManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.BrowserManager.ManageBrowser;
import utils.ConfigReader.ConfigFileReader;
import utils.Reporter.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class BaseTest {
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> extentTest;

    public static ConfigFileReader configReader = new ConfigFileReader();

    private static Logger logger = Logger.getLogger(BaseTest.class);

    private static WebDriver driver;


    public static WebDriver initBrowser() {
        ManageBrowser broserManager = new ManageBrowser();
        driver = broserManager.setWebDriver(configReader.getDriverPath(), configReader.getUrl());
        logger.info("Invoke ManagerBrowser.setWebDriver() to Init web driver");
        return driver;
    }


    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        logger.info("-beforeSuite method start--:" + context.getName());
        logger.info("-before suite-init the Extent Report path");
        extent = ExtentManager.createReport(configReader.getHtmlReportPath());
        logger.info("-before suite-init the Extent Test");
        extentTest = new ThreadLocal<ExtentTest>();

        logger.info("---Init Excel---");
    }

    @AfterSuite
    public void afterSuite(ITestContext context) {
        logger.info("-afterSuite method start--:" + context.getName());
        extent.flush();
        logger.info("Flush extent report");
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) {
        logger.info("-beforeMethod start--,the test case method name is:" + result.getMethod());

        String testName = result.getMethod().getMethodName();
        logger.info("--before method start, the test case name is:" + testName);

        ExtentTest test = extent.createTest(result.getTestClass().getName() + "->"
                + result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test Start:" + testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("The test case[" + testName + "] is PASS");
            extentTest.get().log(Status.PASS, "The test case[" + testName + "] is PASS");
        }

        if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("The test case[" + testName + "] is SKIP");
            extentTest.get().log(Status.SKIP, "The test case[" + testName + "] is SKIP");
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("The test case[" + testName + "] is FAIL");
            extentTest.get().log(Status.FAIL, "The test case[" + testName + "] is FAIL");

            String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
            extentTest.get().fail("<details><summary><b><font color=red>" +
                    "Exception Occured,click to see details:" + "</font></b></summary>"
                    + exceptionMsg.replace(",", "<br>") + "</details> \n");

//            begain to take screenshot
            if ((configReader.getIfTakeScreenshot()).equalsIgnoreCase("yes")) {
                String screenFullPath = takeScreenshot();
                logger.info("The screenshot full path is:" + screenFullPath);

                try {

                    extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenFullPath).build());
                } catch (Exception e) {
                    extentTest.get().fail("Test Failed,can't attach screenshot");
                }
            }
//            end to take screenshot

            String logText = "<b>Test Method " + result.getMethod().getMethodName() + " FAILED</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            extentTest.get().log(Status.FAIL, m);
        }
    }

    public void reportLog(String message) {
        extentTest.get().log(Status.INFO, "[" + message + "]" + "start");
    }

    public String takeScreenshot() {
        String fileName = getScreenName();
        String fileDir = System.getProperty("user.dir") + "/test-output/screenshot/";
        String screenFullName = fileDir + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File descFile = new File(screenFullName);
            FileUtils.copyFile(srcFile, descFile);
            logger.info("The screenshot full name is:" + screenFullName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "../../screenshot/" + fileName;

    }

    public String getScreenName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String strDate = simpleDateFormat.format(date);
        System.out.println(strDate);
        logger.info("The screenshot simple name is:" + strDate);
        return "Screenshot" + strDate + ".jpeg";
    }
}
