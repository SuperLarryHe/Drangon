package testcases;


import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class Apple {

    private static Logger logger = Logger.getLogger(Apple.class);





    @Test
    public void func1(ITestContext context) {
        System.out.println("----This is func1---");
        System.out.println(context.getName());

    }

    @BeforeMethod
    public void beforeMethod(Method method){
        String methodName=method.getName();
        System.out.println(methodName);
    }


    public void func2() {
        System.out.println("----This is func2---");
    }


    public void func3() {
        System.out.println("----This is func3---");
    }

    public void func4() {
        System.out.println("----This is func4---");
    }

}
