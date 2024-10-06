package com.eShopOnWeb.ui.pages;

import com.eShopOnWeb.ui.Utility.UiUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Soniya Patel
 */
public class CheckoutPage extends UiUtils {

    private static final Logger log = LogManager.getLogger(CheckoutPage.class);

    @FindBy(css = "input[value='[ Pay Now ]']")
    private WebElement payNowButton;


    public void clickOnPayNowButton() {
        mouseHoverToElementAndClick(payNowButton);
        log.info("Click on PayNow button " + payNowButton.toString());
    }

    public String getCheckoutPageTitle() {
        log.info("Getting page title: ");
        return getPageTitle();
    }
}
