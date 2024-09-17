package eshop.TestAutomation.Test;

import PageObject.BasePages.*;
import eshop.TestAutomation.TestComponents.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest extends BaseTest {
    LogInPage lp;
    BasketPage basketPage;
    UpdatePage updatepg;
    CheckOutPage cp;
    SuccessPage sp;
    public static Logger log = LogManager.getLogger(SubmitOrderTest.class.getName());

    @Test(dataProvider ="getTestData")
    public void verifyAddToBasket(HashMap<String,String> input) throws InterruptedException {
        String ProductName =input.get("product");
        lp= catalogPage.clickloginButton();
        lp.enterEmail(input.get("email"));
        lp.enterPassword(input.get("password"));
        lp.clickOnLogin();
        basketPage =catalogPage.AddItemToBasket(ProductName);
        String title = driver.getTitle();
        String ExpectedTitle = "Basket - Microsoft.eShopOnWeb";
        Assert.assertEquals(title,ExpectedTitle);
        log.info("User Is navigated to Basket Page");
        List<String> basketItemsList =basketPage.getItemsinBasket();
        log.info(basketItemsList);
        Assert.assertTrue(basketItemsList.contains(ProductName));
        log.info(ProductName+ "is present in the basket");
        basketPage.updateNumberOfItems("0");
        updatepg = basketPage.clickUpdate();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        String validation = updatepg.getValidation();
        log.info(validation);
        Assert.assertTrue(validation.equalsIgnoreCase("Basket is empty."));
        log.info("The items is removed from basket");
    }

    @Test
    public void verifyCheckout() throws InterruptedException {
        String ProductName = prop.getProperty("product");
        String password = prop.getProperty("password");
        String email = prop.getProperty("email");
        lp= catalogPage.clickloginButton();
        lp.enterEmail(email);
        lp.enterPassword(password);
        lp.clickOnLogin();
        basketPage=catalogPage.AddItemToBasket(ProductName);
        String title = driver.getTitle();
        String ExpectedTitle = "Basket - Microsoft.eShopOnWeb";
        Assert.assertEquals(title,ExpectedTitle);
        log.info("User Is navigated to Basket Page");
        cp =basketPage.clickCheckout();
        String headervar = cp.getHeaderItemmessage();
        Assert.assertTrue(headervar.equalsIgnoreCase("Review"));
        log.info("User is navigated to checkout page");
        sp =cp.clickPayNow();
        String successMessage = sp.getSuccessMessage();
        Assert.assertTrue(successMessage.equalsIgnoreCase("Thanks for your Order!"));
        log.info("User Is successful in placing the order");

    }

    @DataProvider
    public Object[][] getTestData() throws IOException {
       List<HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\main\\java\\DataFiles\\SubmitOrder.json");
       return new Object[][] {{data.get(0)}};

    }



}
