package AndroidTestCases;
import TestBase.CommonFunction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OnboardingTest extends CommonFunction {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("platformVersion", "11");
//        caps.setCapability("deviceName", "2fcf4a3c5b03");
        caps.setCapability("appPackage", "glass.round.voyager");
        caps.setCapability("appActivity","glass.round.voyager.base.wrapper.MainActivity");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, getConfigData("DeviceName"));
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
    }


    @Test
    public void clickAppButton(){
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button[2]").click();


    }

    @AfterTest
    public void tearDown(){
        if(null!=driver)
            driver.quit();
    }

}
