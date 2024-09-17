package eshop.TestAutomation.Test;

import PageObject.BasePages.LogInPage;
import eshop.TestAutomation.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class loginTest extends BaseTest {
    LogInPage logInPage;
    @Test
    public void verifyLogin(){
        String password = prop.getProperty("password");
        String email = prop.getProperty("email");
        logInPage= catalogPage.clickloginButton();
        WebElement headerIcon = logInPage.getHeaderIcon();
        String headeertext = headerIcon.getText();
        Assert.assertEquals(headeertext,"Log in");
        logInPage.enterEmail(email);
        logInPage.enterPassword(password);
        logInPage.clickOnLogin();
        WebElement username = catalogPage.getIdentityUsername();
        String usernametext = username.getText();
        Assert.assertEquals(usernametext,"demouser@microsoft.com");
        System.out.println("login successful");
    }
    @Test
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
}
