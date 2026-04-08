package ru.netology.testing.uiautomator.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MainPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // ID нужных для тестов элементов
    private String inputId = "ru.netology.testing.uiautomator:id/userInput";
    private String changeButtonId = "ru.netology.testing.uiautomator:id/buttonChange";
    private String activityButtonId = "ru.netology.testing.uiautomator:id/buttonActivity";
    private String resultTextId = "ru.netology.testing.uiautomator:id/text";

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void clearInput() {
        MobileElement input = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(inputId))
        );
        input.clear();
    }

    public void clickChangeButton() {
        MobileElement button = (MobileElement) driver.findElement(By.id(changeButtonId));
        button.click();
    }

    public String getInputText() {
        MobileElement input = (MobileElement) driver.findElement(By.id(inputId));
        return input.getText();
    }

    public void enterText(String text) {
        MobileElement input = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(inputId))
        );
        input.click();
        input.sendKeys(text);
    }

    public void clickActivityButton() {
        MobileElement button = (MobileElement) wait.until(
                ExpectedConditions.elementToBeClickable(By.id(activityButtonId))
        );
        button.click();
    }

    public String getResultText() {
        MobileElement result = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(resultTextId))
        );
        return result.getText();
    }
}