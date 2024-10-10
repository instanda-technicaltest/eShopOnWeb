package com.eshop.technical.challenge.tests.tests;

import com.eshop.technical.challenge.helper.Constants;
import com.eshop.technical.challenge.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {
    @Test(description = "End to End test")
    public void endToEndFlow() {
        homePage.navigateToLoginPage();
        loginPage.userLoginToEShop(prop.getProperty("userName"), prop.getProperty("userPassword"));
        homePage.selectProductToAddToBasket(prop.getProperty("productName"));
        basketPage.checkOutBasket();
        checkoutPage.payNow();
        Assert.assertEquals(orderCompletionPage.getOderThankyouMessage(), Constants.THANK_YOU_MESSAGE, "Thankyou message is not correct");
    }
}
