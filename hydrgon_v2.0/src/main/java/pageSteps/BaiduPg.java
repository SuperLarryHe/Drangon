package pageSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.IBaiduPage;
import java.util.Set;

public class BaiduPg implements IBaiduPage {
    WebDriver driver;

    public BaiduPg(WebDriver driver) {
        this.driver = driver;
    }

    public void inputSearchTest() {
        driver.findElement(inputBox_baidu).sendKeys("CHINA");
    }

    public void clickSearchButton() {
        driver.findElement(button_submit).click();
    }

    public void clickBaiduIcon() {
        driver.findElement(icon_BaiduMainIcon).click();
    }

    public void getInputText() {
        Set<String> Handles = driver.getWindowHandles();

        for (String handle : Handles) {

            driver.switchTo().window(handle);
        }
        String text = driver.findElement(By.id("kw")).getAttribute("value");
        System.out.println("----text---:" + text);
        if (text.equals("今日新鲜事")) {
            System.out.println("The input text is RIGHT:" + text);
        } else {
            System.out.println("The input text is WRONG:" + text);
        }
    }

    public void baiduFunc1() {
        System.out.println("---baidu func1---");
    }

    public void baiduFunc2() {
        System.out.println("---baidu func2---");
    }


}
