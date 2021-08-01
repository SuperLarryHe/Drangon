package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ExcelUtils.ExcelReader;
import utils.TestManager.BaseTest;

public class DataTest  extends BaseTest {



    @BeforeTest
    public void initExcel(){
        ExcelReader.getExcelFile(super.configReader.getExcelPath());
        ExcelReader.getExcelSheet("users");
    }

    @Test(dataProvider = "DataProvider",dataProviderClass=utils.ExcelUtils.DataProviderDemo.class)
    public void DataProviderShow(String data1,String user) {
        System.out.println("result：" + data1+":"+user);
    }

}
