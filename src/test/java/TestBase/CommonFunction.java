package TestBase;

import Common.Common;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;
import Common.AbsolutePath;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class CommonFunction extends Common {

    public static AbsolutePath absPath = new AbsolutePath("Appium_Automation");
    public static MobileDriver<WebElement> mobileDriver;


    public static String getConfigData(String key) throws FileNotFoundException, IOException {
        String filelocation = AbsolutePath.Path() + "src//test//java//config//config.properties";
        Properties prop = new Properties();
        prop.load(new FileInputStream(filelocation));
        Object val = prop.get(key);
        return val.toString();
    }

    public static MobileDriver<WebElement> setMobileDriver() throws IOException {

        if (getConfigData("Platform").equals("Android")) {
            mobileDriver = androidDriver;
            return mobileDriver;
        } else if (getConfigData("Platform").equals("iOS")) {
            mobileDriver = iOSDriver;
            return mobileDriver;
        } else {
            Assert.fail("Platform is not set");
            return null;
        }
    }

    public static int getRandomNumber() throws FileNotFoundException, IOException {
        int min = 5;
        int max = 10000;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

}
