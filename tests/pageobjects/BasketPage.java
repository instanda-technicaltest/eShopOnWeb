package pageobjects;

import common.TestContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketPage extends BasePage{
    private TestContext testContext;
    public BasketPage(TestContext context) {
        testContext = context;
    }

    By eShopOnWebLogo = By.xpath("//img[@alt='eShop On Web']");
    By Total = By.xpath("//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']");
    By ItemsInBasket = By.xpath("//*[@class='esh-basket-input']");
    By UpdateButton = By.xpath("//*[@name='updatebutton']");
    By ContinueShopping = By.xpath("//a[@class='btn esh-basket-checkout text-white']");
    By CheckOutButton = By.xpath("//a[@class='btn esh-basket-checkout']");
    By PayNowButton = By.xpath("//input[@class='btn esh-basket-checkout']");
    By ThankYouPageTitle = By.xpath("//h1[text()='Thanks for your Order!']");

    public void verifyTotalPrice() {
        waitForElementVisibility(eShopOnWebLogo);
        String expectedPrice = testContext.getPrice();
        String actualPrice = getElement(Total).getText().replace("$", "").trim();
        Assert.assertTrue(expectedPrice.equalsIgnoreCase(actualPrice));
    }

    public void resetBasket(){
        waitForElementVisibility(ItemsInBasket);
        List<WebElement> items= getAllElements(ItemsInBasket);
        for(WebElement elm: items){
            waitForVisibilityOfElement(elm);
            //elm.click();
            elm.clear();
            elm.sendKeys("0");
        }
        clickUsingJS(UpdateButton);
        clickUsingJS(ContinueShopping);
    }

    public void checkOut(){
        waitForElementVisibility(eShopOnWebLogo);
        clickUsingJS(CheckOutButton);
        waitForElementVisibility(eShopOnWebLogo);
        clickUsingJS(PayNowButton);
    }

    public void thankYouPage(){
        waitForElementVisibility(ThankYouPageTitle);
    }

    public void continueShopping(){
        waitForElementVisibility(eShopOnWebLogo);
        clickUsingJS(ContinueShopping);
        waitForElementVisibility(eShopOnWebLogo);
    }


}
