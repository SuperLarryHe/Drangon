package utils.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.LogFactory.CommLog;
import utils.Reporter.ExtentManager;
import utils.TestManager.BaseClass;
import utils.TestManager.TestBase;

import java.util.Arrays;

//public class TestListener extends TestBase implements ITestListener {
public class TestListener extends BaseClass implements ITestListener {
        private static ExtentReports extent= ExtentManager.createReport("");
//    private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
//    static ExtentReports extent;
//    static ThreadLocal<ExtentTest> extentTest;


    public void onStart(ITestContext context) {
        CommLog.ADD_LOG.info("-onStart Listner start--Method:" + context.getName());
        System.out.println("onStart method started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
        CommLog.ADD_LOG.info("-onFinish Listner start--Method:" + context.getName());
        extent.flush();
    }

    public void onTestStart(ITestResult result) {
        CommLog.ADD_LOG.info("-onTestStart Listner start--Method:" + result.getName());

        String testName = result.getName();
        System.out.println("New Test Started" + testName);
        ExtentTest test = extent.createTest(result.getTestClass().getName() + "->"
                + result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test Start:" + testName);
    }

    public void onTestSuccess(ITestResult result) {
        CommLog.ADD_LOG.info("-onTestSuccess Listner start--Method:" + result.getName());
        System.out.println("onTestSuccess Method" + result.getName());
        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " SUCCESS</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }


    public void onTestSkipped(ITestResult result) {
        CommLog.ADD_LOG.info("-onTestSkiiped Listner start--Method:" + result.getName());

        System.out.println("onTestSkipped Method" + result.getName());
        String methodName = result.getMethod().getMethodName();

        String logText = "<b>Test Method " + result.getMethod().getMethodName() + " SKIPPED</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }

    public void onTestFailure(ITestResult result) {
        CommLog.ADD_LOG.info("-onTestFailure start--Method:" + result.getName());

        System.out.println("onTestFailure Method:" + result.getName());

/*

        String exceptionMsg= Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>"+
                "Exception Occured,click to see details:"+"</font></b></summary>"
                +exceptionMsg.replace(",","<br>")+"</details> \n");
        String screenFullPath=takeScreenshot();
        System.out.println("======on failure====:"+screenFullPath);

        try{
//            extentTest.get().fail("Test Failed");
            extentTest.get().fail("<b><font color=red>"+"Screenshot of failure"+"</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenFullPath).build());
        }catch(Exception e){
            extentTest.get().fail("Test Failed,can't attach screenshot");
        }

        String logText="<b>Test Method "+result.getMethod().getMethodName()+" FAILED</b>";
        Markup m= MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL,m);
*/


    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage" + result.getName());
    }
}
