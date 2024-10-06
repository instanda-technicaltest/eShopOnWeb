package com.eShopOnWeb.ui.pages;

import com.eShopOnWeb.ui.Utility.UiUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Soniya Patel
 */
public class BasketPage extends UiUtils {

    private static final Logger log = LogManager.getLogger(BasketPage.class);

    @FindBy(css = ".esh-basket-item.col-xs-3")
    private WebElement productText;

    @FindBy(css = ".esh-basket-input")
    private WebElement qty;

    @FindBy(name = "updatebutton")
    private WebElement updateButton;

    @FindBy(css = " .esh-basket-item--mark.col-xs-2:nth-child(2)")
    private WebElement totalPrice;

    @FindBy(css = "a[class='btn esh-basket-checkout']")
    private WebElement checkoutButton;

    public String getProductText() {
        log.info("Getting product text: " + productText.toString());
        return getTextFromElement(productText);
    }

    public void enterQty(String qtyVal) {
        clearTextOnElement(qty);
        sendTextToElement(qty, qtyVal);
        log.info("Enter Quantity " + qty + qty.toString());
    }

    public void clickOnUpdateButton() {
        mouseHoverToElementAndClick(updateButton);
        log.info("Click on update button " + updateButton.toString());
    }

    public String getTotalPrice() {
        log.info("Getting total price: " + totalPrice.toString());
        return getTextFromElement(totalPrice);
    }

    public void clickOnCheckoutButton() {
        mouseHoverToElementAndClick(checkoutButton);
        log.info("Click on checkout button " + checkoutButton.toString());
    }
}

