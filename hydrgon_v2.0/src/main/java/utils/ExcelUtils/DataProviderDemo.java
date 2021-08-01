package utils.ExcelUtils;

import org.testng.annotations.DataProvider;

public class DataProviderDemo {

    @DataProvider(name = "DataProvider")
    public Object[][] DataProviders() {

        String sex=ExcelReader.getTestDataByKey("users","tc2",3,"sex");
        String user=ExcelReader.getTestDataByKey("users","tc2",3,"user");

        Object[][] obj = new Object[][] { { sex,user }};
        return obj;
    }

}
