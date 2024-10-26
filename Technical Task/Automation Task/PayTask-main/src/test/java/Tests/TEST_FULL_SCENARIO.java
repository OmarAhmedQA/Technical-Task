package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.Magento_Page;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyValue;
import io.qameta.allure.*;









@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

@Epic("Pay mob Assessment")
@Severity(SeverityLevel.CRITICAL)

public class TEST_FULL_SCENARIO
{

    @BeforeTest
    public void setup() throws IOException
    {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("environment", "Browser");
        LogsUtils.info(System.getProperty("browser"));
        setupDriver(browser);
        LogsUtils.info(browser + " driver is opened");
        getDriver().get(getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("Page is redirected to the URL");

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void AddingCust()
    {
        new Magento_Page(getDriver())
                .clickCreateAccUpper()
                .filling()
                .clickCreateAccLower()
                .MSGassertionAndGoHome()
                .AddingItem1()
                .AddingItem2();

    }


    @AfterTest
    public void quit() {
        quitDriver();
    }
}
