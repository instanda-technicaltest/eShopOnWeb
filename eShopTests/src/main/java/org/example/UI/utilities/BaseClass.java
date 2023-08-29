package org.example.UI.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    public BaseClass(WebDriver driver, WebDriverWait wait) {

        BaseClass.driver = driver;
        BaseClass.wait = wait;
    }

    protected void waitUntilElementClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitUntilElementVisible(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementAndClick(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    protected void waitForElementAndEnterValue(WebElement element, String value) {

        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
    }

    protected void dropDownFields(WebElement element, String value) {
        Select selectElement = new Select(element);
        selectElement.selectByVisibleText(value);
    }

    protected void javaScriptExecutorAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

}
