package com.eShopOnWeb.ui.pages;

import com.eShopOnWeb.ui.Utility.UiUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Soniya Patel
 */
public class CatalogPage extends UiUtils {
    private static final Logger log = LogManager.getLogger(CatalogPage.class);

    @FindBy(css = ".esh-identity-name.esh-identity-name--upper")
    private WebElement loginLink;
    @FindBy(xpath = "//span[text()='Roslyn Red Sheet']/ancestor::form//input[@type='submit']")
    private WebElement addToBasket;

    @FindBy(css = "div.esh-identity-name")
    private WebElement emailText;

    public void clickLogin() {
        clickOnElement(loginLink);
        log.info("Click on login link " + loginLink.toString());
    }

    public void clickOnAddToBasket() {
        mouseHoverToElementAndClick(addToBasket);
        log.info("Clicking on add to basket button : " + addToBasket.toString());
    }

    public String getDisplayedEmail() {
        log.info("Getting email text: " + emailText.toString());
        return getTextFromElement(emailText);
    }
}
