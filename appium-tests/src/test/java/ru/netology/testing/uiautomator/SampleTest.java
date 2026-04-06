package ru.netology.testing.uiautomator;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

    @Before
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appActivity", ".MainActivity");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("newCommandTimeout", 3600);

        driver = new AndroidDriver(this.getUrl(), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testWhitespaceOnlyDoesNotChangeText() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        MobileElement inputField = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("ru.netology.testing.uiautomator:id/userInput")
                )
        );
        inputField.clear();

        MobileElement changeButton = (MobileElement) driver.findElement(
                By.id("ru.netology.testing.uiautomator:id/buttonChange")
        );
        changeButton.click();

        MobileElement textView = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("ru.netology.testing.uiautomator:id/userInput")
                )
        );

        Assert.assertEquals("Текст не должен измениться при вводе только пробелов",
                "Type something…",
                textView.getText());
    }

    @Test
    public void testTextAppearsInNewActivity() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String testText = "Hello Appium!";


        MobileElement inputField = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("ru.netology.testing.uiautomator:id/userInput")
                )
        );
        inputField.isDisplayed();
        inputField.click();
        inputField.sendKeys(testText);

        MobileElement openButton = (MobileElement) wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("ru.netology.testing.uiautomator:id/buttonActivity")
                )
        );
        openButton.isDisplayed();
        openButton.click();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        MobileElement resultText = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("ru.netology.testing.uiautomator:id/text")
                )
        );
        resultText.isDisplayed();

        Assert.assertEquals(testText, resultText.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}