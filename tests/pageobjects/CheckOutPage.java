package pageobjects;

import common.TestContext;
import org.junit.Assert;
import org.openqa.selenium.By;

public class CheckOutPage extends BasePage{
    private TestContext testContext;
    public CheckOutPage(TestContext context) {
        testContext = context;
    }
    By eShopOnWebLogo = By.xpath("//img[@alt='eShop On Web']");
    By Total = By.xpath("//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']");


    public void verifyCheckoutPrice() {
        waitForElementVisibility(eShopOnWebLogo);
        String expectedPrice = testContext.getPrice();
        String actualPrice = getElement(Total).getText().replace("$", "").trim();
        Assert.assertTrue(expectedPrice.equalsIgnoreCase(actualPrice));
    }


}
