package Utilities.appium;


import TestBase.CommonFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;


import static java.util.concurrent.TimeUnit.SECONDS;

public class MobileWaitFunction extends CommonFunction {

    public static void implicitWait(int seconds) {

        mobileDriver.manage().timeouts().implicitlyWait(seconds, SECONDS);

    }
    public static void suspendExecution(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void waitForElementToBEDisplayed(WebElement element, int seconds) {

        wait = new WebDriverWait(mobileDriver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static void waitForElementToBEClickable(WebElement element, int seconds) {

        wait = new WebDriverWait(mobileDriver, seconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static WebElement fluentWait(final By locator, int total, int pooling) {

        waitFluent = new FluentWait<WebDriver>(mobileDriver)
                .withTimeout(Duration.ofSeconds(total))
                .pollingEvery(Duration.ofSeconds(pooling))
                .ignoring(NoSuchElementException.class);

        WebElement element = waitFluent.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver d) {
                return d.findElement(locator);
            }
        });

        return element;

    }



}
