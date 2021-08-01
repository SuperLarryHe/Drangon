package testcases;


import org.testng.Assert;
import org.testng.annotations.*;
import pageSteps.BaiduPg;
//import utils.Listeners.TestListener;
import utils.TestManager.BaseTest;
//import utils.TestManager.TestBase;


//@Listeners(TestListener.class)
public class BaiduTC extends BaseTest {
    BaiduPg page = new BaiduPg(BaseTest.initBrowser());


    @Test(groups = "apple")
    public void searchBaidu() {
        page.inputSearchTest();
        page.clickSearchButton();
        Assert.fail();
    }

    @Test(groups = "pie")
    public void baiduBaike() {
        page.clickBaiduIcon();
        page.getInputText();

    }

    @Test
    public void baiduFuc(){

        reportLog("baiduFunc1()");
        page.baiduFunc1();
        reportLog("baiduFunc2()");
        page.baiduFunc2();
    }
}
