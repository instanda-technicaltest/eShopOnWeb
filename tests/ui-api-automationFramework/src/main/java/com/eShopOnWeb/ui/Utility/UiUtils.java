package com.eShopOnWeb.ui.Utility;

import com.eShopOnWeb.ui.browserfactory.ManageBrowser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Soniya Patel
 */


public class UiUtils  extends ManageBrowser {
    /*UiUtils Class extends to ManageBrowser for the driver to finding locators
     *All common methods are fixed in the utility Class.
     */


    /**
     * This method will click on element
     */

    public void clickOnElement(WebElement element) {
        element.click();
    }

    /**
     * This method will get text from element
     */

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    /**
     * This method will send text on element
     */

    public void sendTextToElement(WebElement element, String str) {
        element.sendKeys(str);
    }


    /**
     * This method will clear text on element
     */
    public void clearTextOnElement(WebElement element) {
        element.clear();
    }

    /**
     * This method will return title of page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }


    /**
     * This method will use to hover mouse on element and click
     */

    public void mouseHoverToElementAndClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    /**
     * This method will use to scroll to view on element and click
     */
    public static void scrollToViewAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
    public static void scrollToViewAndSendKeys(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Take screenshot in Byte Format
     */
    public static byte[] getScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

