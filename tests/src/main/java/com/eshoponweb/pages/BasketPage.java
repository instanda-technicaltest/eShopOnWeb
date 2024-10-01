package com.eshoponweb.pages;

import com.eshoponweb.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class BasketPage extends Utility {
    HomePage homePage;
    private static final Logger log = LogManager.getLogger(BasketPage.class);

    @FindBy (xpath = "//section[contains(@class,'esh-basket-item esh-basket-item--middle col-xs-3')]")
    private List<WebElement> productNames;

    @FindBy(xpath = "//article[@class='esh-basket-items row']/div/section[3]")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//article[@class='esh-basket-items row']/div/section[4]")
    private List<WebElement> productQty;

    @FindBy(xpath = "//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']")
    private WebElement total;

    @FindBy(xpath = "//a[@class='btn esh-basket-checkout text-white']")
    private WebElement continueShoppingButton;

    @FindBy(xpath="//button[@name='updatebutton']")
    private WebElement updateButton;

    @FindBy(xpath="//div[@class='esh-basketstatus-badge']")
    private WebElement basketBadge;

    public List getProductNames() {
       log.info("Get Product Names from the Basket Page "+getStringList(productNames) );
        return getStringList(productNames);
    }

    public List <Double> getProductPrices() {
        List <Double> prices = new ArrayList<>();
        log.info("Get Product Prices from the Basket Page "+getStringList(productPrices));
        for (WebElement element : productPrices) {
            // Parse the price (removing the "$" symbol) and format it to 2 decimal places
            Double formattedPrice = Double.parseDouble(element.getText().substring(1));
            prices.add(formattedPrice);
        }
        return prices;
    }

    public int getBasketQuantity() {
        log.info("Get Basket Quantity from the Basket Page "+basketBadge.getText());
        return Integer.parseInt(basketBadge.getText());
    }

    public double getTotal() {
        log.info("Get Total from the Basket Page "+Double.parseDouble(total.getText().substring(1)));
        return Double.parseDouble(total.getText().substring(1));
    }



}
