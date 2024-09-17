package eshop.TestAutomation.Test;

import PageObject.BasePages.*;
import eshop.TestAutomation.TestComponents.BaseTest;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sample extends BaseTest {

    //WebDriver driver;
    //CatalogPage catalogPage;
    LogInPage logInPage;
    BasketPage basketPage;
    UpdatePage updatepg;
    CheckOutPage cp;
    SuccessPage sp;
    //CheckOutPage checkOutPage;
    //@BeforeTest

    @Test(priority = 3)
    public void verifyLogin(){
        //logInPage = new LogInPage(driver);
        logInPage= catalogPage.clickloginButton();
       WebElement headerIcon = logInPage.getHeaderIcon();
        String headeertext = headerIcon.getText();
        Assert.assertEquals(headeertext,"Log in");
        logInPage.enterEmail("demouser@microsoft.com");
        logInPage.enterPassword("Pass@word1");
        logInPage.clickOnLogin();
        WebElement username = catalogPage.getIdentityUsername();
        String usernametext = username.getText();
        Assert.assertEquals(usernametext,"demouser@microsoft.com");
        System.out.println("login successful");
    }

    @Test(priority = 5)
    public void verifyLogOut(){
        verifyLogin();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        catalogPage.moveToLogoutMenu();
        WebElement logout = catalogPage.displayLogoutButton();
        Assert.assertTrue(logout.isDisplayed());
        System.out.println("logout button is displayed");
        catalogPage.clickLogoutButton();
        Assert.assertTrue(catalogPage.getIdentityUsername().isDisplayed());
        System.out.println("logout button is Successful");
    }

    @Test(priority = 4)
    public void verifyCatalogListDisplayed(){
     int totalItems = catalogPage.getNumberOfItemsdisplayedinCatalog();
     if(totalItems == 0){
         System.out.println("No Items are displayed in catalog");
     }
    }

    @Test(priority = 2)
    public void verifyAddToBasket() throws InterruptedException {
        String ProductName = "Prism White T-Shirt";
        verifyLogin();
        basketPage =catalogPage.AddItemToBasket(ProductName);
        String title = driver.getTitle();
        String ExpectedTitle = "Basket - Microsoft.eShopOnWeb";
        Assert.assertEquals(title,ExpectedTitle);
        System.out.println("User Is navigated to Basket Page");
        //basketPage = new BasketPage(driver);
        List<String> basketItemsList =basketPage.getItemsinBasket();
        System.out.println(basketItemsList);
        Assert.assertTrue(basketItemsList.contains(ProductName));
        System.out.println(ProductName+ "is present in the basket");
        basketPage.updateNumberOfItems("0");
        updatepg = basketPage.clickUpdate();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        //UpdatePage updatepg = new UpdatePage(driver);
        String validation = updatepg.getValidation();
        System.out.println(validation);
        Assert.assertTrue(validation.equalsIgnoreCase("Basket is empty."));
        System.out.println("The items is removed from basket");
    }

    @Test(priority = 1)
    public void verifyCheckout() throws InterruptedException {
        String ProductName = "Prism White T-Shirt";
        verifyLogin();
        basketPage=catalogPage.AddItemToBasket(ProductName);
        String title = driver.getTitle();
        String ExpectedTitle = "Basket - Microsoft.eShopOnWeb";
        Assert.assertEquals(title,ExpectedTitle);
        System.out.println("User Is navigated to Basket Page");
        //basketPage = new BasketPage(driver);
        cp =basketPage.clickCheckout();
        //CheckOutPage cp = new CheckOutPage(driver);
        String headervar = cp.getHeaderItemmessage();
        Assert.assertTrue(headervar.equalsIgnoreCase("Review"));
        System.out.println("User is navigated to checkout page");
        sp =cp.clickPayNow();
        //SuccessPage sp = new SuccessPage(driver);
        String successMessage = sp.getSuccessMessage();
        Assert.assertTrue(successMessage.equalsIgnoreCase("Thanks for your Order!"));
        System.out.println("User Is successful in placing the order");




    }


}
