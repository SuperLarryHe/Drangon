package utils.TestManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Reporter.ExtentManager;

import java.lang.reflect.Method;


public class BaseClass {
    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
//    public static ExtentReports extent= ExtentManager.createReport();

//    @BeforeSuite
//    public void before() {
//        extent = ExtentManager.createReport();
//        System.out.println("---before suite extent:"+extent.toString()+"----");
//    }


//    @BeforeMethod
//    public void setUp(Method method) throws Exception {
//        test = extent.createTest(method.getName());
//
//    }
//
//    @AfterSuite
//    public void tearDownSuite() {
//        // reporter.endReport();
//        extent.flush();
//    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        extentTest.get().log(Status.INFO, "--after method log---");
    }

    public void reportLog(String message) {
        extentTest.get().log(Status.INFO, message);//For extentTest HTML report
    }
//    @AfterTest
//    public void afterTest(){
//        System.out.println("---after test---");
//    }
}
