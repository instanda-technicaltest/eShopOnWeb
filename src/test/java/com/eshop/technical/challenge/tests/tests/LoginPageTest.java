package com.eshop.technical.challenge.tests.tests;

import com.eshop.technical.challenge.helper.Constants;
import com.eshop.technical.challenge.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void openLoginPage() {
        loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue(loginPage.verifyLoginPageTitleIsDisplayed(), "Login Page title is not displayed ");
    }

    @Test(priority = 2)
    public void loginAsUser() {
        loginPage.userLoginToEShop(prop.getProperty("userName"), prop.getProperty("userPassword"));
    }

    @Test
    public void verifyInValidLoginErrors() {
        openLoginPage();
        loginPage.userLoginToEShop("test@email.com", "test");
        Assert.assertEquals(loginPage.getInvalidLoginErrors(), Constants.INVALID_LOGIN_ERROR);
    }
}
