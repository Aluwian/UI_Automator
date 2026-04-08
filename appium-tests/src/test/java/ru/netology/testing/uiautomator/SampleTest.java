package ru.netology.testing.uiautomator;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.*;
import ru.netology.testing.uiautomator.pages.MainPage;
import org.junit.Assert;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;
    private MainPage mainPage;

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

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        mainPage = new MainPage(driver);
    }

    @Test
    public void testWhitespaceOnlyDoesNotChangeText() {

        mainPage.clearInput();
        mainPage.clickChangeButton();

        Assert.assertEquals("Текст не должен измениться при вводе только пробелов",
                "Type something…",
                mainPage.getInputText());
    }

    @Test
    public void testTextAppearsInNewActivity() {

        String testText = "Hello Appium!";

        mainPage.enterText(testText);
        mainPage.clickActivityButton();

        String actualText = mainPage.getResultText();

        Assert.assertEquals(testText, actualText);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}