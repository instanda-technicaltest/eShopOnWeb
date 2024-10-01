package com.eshoponweb.steps;

import com.eshoponweb.pages.HomePage;
import com.eshoponweb.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class loginStepDef {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Given("I am on the {string} page")
    public void iAmOnThePage(String pageName)
    {         homePage.clickOnLoginLink();
        homePage.verifyUserIsOnGivenPage(pageName);
    }

    @Then("I should see the email field")
    public void iShouldSeeTheEmailField() {
        loginPage.isEmailFieldDisplayed();

    }

    @And("I should see the password field")
    public void iShouldSeeThePasswordField() {
       loginPage.isPasswordFieldDisplayed();
    }


    @And("I should see the Login Link")
    public void iShouldSeeTheLoginLink() {
        loginPage.isLoginButtonDisplayed();
    }

    @And("I should see the remember me checkbox")
    public void iShouldSeeTheRememberMeCheckbox() {
        loginPage.isRememberMeCheckboxDisplayed();
    }

    @And("I should see the forgot your password link")
    public void iShouldSeeTheForgotYourPasswordLink() {
        loginPage.isForgotYourPasswordLinkDisplayed();
    }

    @And("I should see the new user registration link")
    public void iShouldSeeTheNewUserRegistrationLink() {
       loginPage.isNewUserRegistrationLinkDisplayed();
    }

    @When("I enter the {string} and {string}")
    public void iEnterTheAnd(String username, String password) {
        loginPage.enterEmailId(username);
        loginPage.enterPassword(password);
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
        loginPage.clickOnLoginButton();
    }

    @Then("I should see the {string} in the header")
    public void iShouldSeeTheInTheHeader(String email) {
        homePage.verifyUserIsLoggedIn(email);
    }

    @Given("user is logged in {string} and {string}")
    public void userIsLoggedInAnd(String arg0, String arg1) {
    }
}
