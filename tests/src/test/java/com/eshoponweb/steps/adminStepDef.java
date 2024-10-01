package com.eshoponweb.steps;

import com.eshoponweb.pages.AdminPage;
import com.eshoponweb.pages.HomePage;
import com.eshoponweb.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class adminStepDef {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    AdminPage adminPage = new AdminPage();
    @Given("user is logged with admin {string} and password {string}")
    public void userIsLoggedWithAdminAndPassword(String arg0, String arg1) {
        homePage.clickOnLoginLink();
        loginPage.enterEmailId(arg0);
        loginPage.enterPassword(arg1);
        loginPage.clickOnLoginButton();
    }

    @When("user hovers on drop down menu")
    public void userHoversOnDropDownMenu() {
        homePage.hoverOnDropDownMenu();
    }
    @And("user clicks on {string} button from menu")
    public void userClicksOnButtonFromMenu(String menuItem) {
        System.out.println("User clicks on "+menuItem+" button from menu");
      homePage.clickOnMenuItem(menuItem);
    }
    @Then("user is on {string} page")
    public void userIsOnPage(String pageName)
    {homePage.verifyUserIsOnGivenPage(pageName);
    }

    @And("user can logout from the application")
    public void userCanLogoutFromTheApplication() {
        adminPage.clickOnLogoutButton();

    }

    @And("{string} do not appear in header")
    public void doNotAppearInHeader(String userId)
    {
        Assert.assertFalse(homePage.verifyUserIsLoggedIn(userId),"User is still logged in");
    }
}
