package stepdefs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestSample {
    WebDriver driver;

    public ExtentReports extent;
    public ExtentTest extentTest;
    public ExtentSparkReporter spark;

    @BeforeClass
    public void setReport() {
        spark = new ExtentSparkReporter("target/Spark.html");
        spark.config().setEncoding("utf-8");
        spark.config().setDocumentTitle("Automation Testing Report");
        spark.config().setReportName("My Testing Report");
        spark.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.setSystemInfo("Organization", "Club333");
        extent.setSystemInfo("Browser", "Google Chrome");
        extent.attachReporter(spark);

        extent.createTest("MyFirstTest")
                .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");

        System.out.println("User.dir:"+System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver","D:\\idea-space\\hydrgon\\src\\main\\resources\\webdrivers\\chromedriver.exe");

        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public  void afterClass() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        extent.flush();
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("-----Before Test-----");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("-----After Test-----");
    }

    @Test(description = "This is TC1")
    public void test1() {
        System.out.println("---Test 1---");
        driver.get("https://www.baidu.com");
        String title=driver.getTitle();
        System.out.println("---The title is:"+title);
        extentTest=extent.createTest("TestName=TC1","Open Chrome browser and navigate to baidu.com");
        extentTest.log(Status.PASS,"TC2 is PASSED");
    }

    @Test(description = "This is TC2")
    public void test2() {
        System.out.println("---Test 2---");
        extentTest=extent.createTest("TestName=TC2","TestDesc=description");
        extentTest.log(Status.PASS,"TC2 is PASSED");
    }

    @Test(description = "This is TC3")
    public void test3() {
        System.out.println("---Test 3---");
        extentTest=extent.createTest("TestName=TC3","TestDesc=description");
        extentTest.log(Status.FAIL,"TC3 is FAILED");
    }

    @Test(description = "This is TC4")
    public void test4() {
        System.out.println("---Test 4---");
    }
}
