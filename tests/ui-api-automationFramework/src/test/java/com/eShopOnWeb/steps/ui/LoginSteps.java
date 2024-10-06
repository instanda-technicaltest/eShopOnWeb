package com.eShopOnWeb.steps.ui;

import com.eShopOnWeb.ui.pages.CatalogPage;
import com.eShopOnWeb.ui.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Created by Soniya Patel
 */


public class LoginSteps {
    @Given("I am on homepage")
    public void iAmOnHomepage() {
    }

    @When("I click on login link")
    public void iClickOnLoginLink() {
        new CatalogPage().clickLogin();
    }

    @And("I enter email {string}")
    public void iEnterEmail(String email) {
        new LoginPage().enterEmail(email);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        new LoginPage().enterPassword(password);
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        new LoginPage().clickOnLogin();
    }

    @Then("email {string} should be displayed")
    public void emailShouldBeDisplayed(String expectedEmail) {
        Assert.assertEquals(new CatalogPage().getDisplayedEmail(), expectedEmail, "Email doesn't match");
    }

    @Then("user should not login and {string} should display")
    public void userShouldNotLoginAndShouldDisplay(String expectedErrorMessage) {
        Assert.assertEquals(new LoginPage().getErrorMessage(), expectedErrorMessage, "Error message is not matched");
    }
}
