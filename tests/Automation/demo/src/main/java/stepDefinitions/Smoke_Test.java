package stepDefinitions;

import commonFunctions.CommonFunctions;
import io.cucumber.java.en.And;
import org.junit.Assert;
import pageObjects.Home_Page;
import pageObjects.My_Account_Page;
import pageObjects.My_Order_Page;

public class Smoke_Test extends CommonFunctions {

    // Create object for page elements and pass the driver to access those elements
    Home_Page homePage = new Home_Page(driver);
    My_Account_Page myAccountPage = new My_Account_Page(driver);
    My_Order_Page my_order_page = new My_Order_Page(driver);


    @And("from identity drop down and click on My Account and redirected to My Account page")
    public void fromIdentityDropDownAndClickOnMyAccountAndRedirectedToMyAccountPage() throws Exception{
        homePage.txt_User.click();
        waitForElement(homePage.ddo_My_Account,30);
        homePage.ddo_My_Account.click();
    }

    @And("validate user successfully landed on  My Account Page")
    public void validateUserSuccessfullyLandedOnMyAccountPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("account"));

        myAccountPage.txt_Manage_Your_Account.isDisplayed();
    }

    @And("from identity drop down and click on My Order and redirected to My Order Page")
    public void fromIdentityDropDownAndClickOnMyOrderAndRedirectedToMyOrderPage() {
        homePage.txt_User.click();
        waitForElement(homePage.ddo_My_Account,30);
        homePage.ddo_My_Orders.click();
    }

    @And("validate user successfully landed on My Orders Page")
    public void validateUserSuccessfullyLandedOnMyOrdersPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("order"));

        my_order_page.txt_My_Order_History.isDisplayed();
    }

    @And("from identity drop down and click on Logout and redirected to Home page")
    public void fromIdentityDropDownAndClickOnLogoutAndRedirectedToHomePage() {
        homePage.txt_User.click();
        waitForElement(homePage.ddo_My_Account,30);
        homePage.ddo_Logout.click();
    }

    @And("validate user has successfully logout and landed on Home page")
    public void validateUserHasSuccessfullyLogoutAndLandedOnHomePage() {
        homePage.b_Login.isDisplayed();
    }
}
