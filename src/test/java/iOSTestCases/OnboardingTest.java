package iOSTestCases;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class OnboardingTest {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("platformVersion", "14.5");
        caps.setCapability("deviceName", "iPhone 12");
        caps.setCapability("bundleId", "com.roundglass.voyager-stage");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
    }


    @Test
    public void clickAppButton(){

    }

    @AfterTest
    public void tearDown(){
        if(null!=driver)
            driver.quit();
    }

}
