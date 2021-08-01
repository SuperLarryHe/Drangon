package testcases;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;
import utils.TestManager.BaseClass;
import utils.TestManager.BaseTest;

//@Listeners(TestListener.class)
public class Pear extends BaseTest {

    Apple a=new Apple();

    @Test
    public void test1() {
        reportLog("func1()");
//        a.func1();
    }

    @Test
    public void test2(){

        a.func2();
        a.func3();
    }

    @Test
    public void test3(){

        a.func4();
        Assert.assertEquals(1,2);
    }

}
