package com.eShopOnWeb.ui.pages;

import com.eShopOnWeb.ui.Utility.UiUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Soniya Patel
 */
public class CheckoutCompletePage extends UiUtils {
    private static final Logger log = LogManager.getLogger(CheckoutCompletePage.class);

    @FindBy(xpath = "//h1")
    private WebElement headerText;

    public String getHeaderText() {
        log.info("Getting Header text: " + headerText.toString());
        return getTextFromElement(headerText);
    }
}
