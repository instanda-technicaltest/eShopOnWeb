package pageobjects;

import common.TestContext;
import org.openqa.selenium.By;
import util.PropertyReaders;

import java.io.IOException;

public class HomePage extends BasePage{
    private TestContext testContext;

    public HomePage(TestContext context) {
        testContext = context;
    }

    By eShopOnWebLogo = By.xpath("//img[@alt='eShop On Web']");
    By LoginButton = By.xpath("//a[@class='esh-identity-name esh-identity-name--upper']");
    By LoginHeader = By.xpath("//h2[text()='Log in']");
    By EmailTextBox = By.id("Input_Email");
    By PasswordTextBox = By.id("Input_Password");
    By LoginSubmit = By.xpath("//button[@type='submit']");
    By CartCount = By.xpath("//div[@class='esh-basketstatus-badge']");



    public void navigateToSite( String url) {
        driver.get(url);
        waitForPageToLoad();
        waitForElementVisibility(eShopOnWebLogo);
    }

    public void LoginToEShop() throws IOException {
        clickElement(LoginButton);
        waitForElementVisibility(LoginHeader);
        enterValues(EmailTextBox, PropertyReaders.getInstance().readProperty("Email"));
        enterValues(PasswordTextBox, PropertyReaders.getInstance().readProperty("Password"));
        clickElement(LoginSubmit);
        resetCart();
    }

    public void selectItem( String name){
        waitForElementVisibility(eShopOnWebLogo);
        By AddToCartButton = By.xpath("//span[text()='"+name+"']/../../input[@type='submit']");
        By getPrice= By.xpath("//span[text()='"+name+"']/../following-sibling::div/span");
        testContext.setPrice(getElement(getPrice).getText());
        System.out.println("PriceSet:"+getElement(getPrice).getText());
        clickUsingJS(AddToCartButton);
    }

    public void resetCart(){
        int currentCount = Integer.parseInt(getElement(CartCount).getText());
        if(currentCount>0){
            clickElement(CartCount);
            new PageObjectManager(testContext).basketPage.resetBasket();
        }
    }


}
