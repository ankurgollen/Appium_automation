package TestBase;

import Utilities.appium.MobileWaitFunction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;


import java.io.IOException;
import java.net.URL;

public class TestBase extends CommonFunction{

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;

    @BeforeSuite
    public static void setUp() throws IOException {
        mobileDriver = setMobileDriver();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, getConfigData("DeviceName"));
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, getConfigData("AutomationName"));
		if(getConfigData("Environment").equals("Prod"))
			cap.setCapability("appPackage", getConfigData("ProdAppPackage"));
		else{
			cap.setCapability("appPackage", getConfigData("DevAppPackage"));
		}
		cap.setCapability("appActivity", getConfigData("AppActivity"));
        cap.setCapability("noReset", false);
        if (getConfigData("Platform").equals("Android"))
            mobileDriver = new AndroidDriver<>(new URL(getConfigData("appURL")), cap);
        else
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, getConfigData("PlatformVersion"));
            cap.setCapability("bundleId", getConfigData("BundleID"));
            mobileDriver = new IOSDriver<>(new URL(getConfigData("appURL")), cap);
        MobileWaitFunction.implicitWait(60);
    }

}
