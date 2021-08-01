package pageObjects;

import org.openqa.selenium.By;


public interface IBaiduPage {

    By inputBox_baidu = By.id("kw");
    By button_submit = By.id("su");
    By icon_BaiduMainIcon = By.xpath("//map[@name=\"mp\"]/area");

    }
