package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriversupport.DriverManager;

import java.util.List;

public class BasePage {

    public WebDriver driver;
    private static final long TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    public WebElement getElement(By locator) {
        WebElement element = null;
        int attempts = 0;
        while (attempts < 3) {
            try {
                element = driver.findElement(locator);
                break;
            } catch (StaleElementReferenceException e) {
                Assert.fail("Unable to get element:" + e.getMessage());
            } catch (NoSuchElementException e) {
                Assert.fail("Unable to locate element:" + e.getMessage());
            }
            attempts++;
        }
        return element;
    }

    public void waitForElementVisibility(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {

            Assert.fail("Element was not visible" + e.getMessage());
        }
    }

    public void waitForVisibilityOfElement(WebElement element) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (Exception e) {

                Assert.fail("Element was not visible" + e.getMessage());
            }
            attempts++;
        }
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, TIMEOUT).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void clickElement(By locator) {
        waitForElementVisibility(locator);
        waitForVisibilityOfElement(getElement(locator));
        getElement(locator).click();
    }

    public void enterValues(By locator, String values) {
        driver.findElement(locator).sendKeys(values);
    }

    public List<WebElement> getAllElements(By locator) {
        return driver.findElements(locator);
    }


    public void clickUsingJS(By locator) {
        waitForElementVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(locator));
    }
}
