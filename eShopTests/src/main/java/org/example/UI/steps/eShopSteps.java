package org.example.UI.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.UI.pages.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.UI.pages.eShopPages;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class eShopSteps {
    WebDriver driver = Hooks.driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    eShopPages loginPage = new eShopPages(driver, wait);
    public static final String PAGE_TITLE = "eShop On Web";
    public static final String PAGE_HEADER = "Thanks for your Order!";

    @Given("User is on eShop landing page")
    public void userIsOnEShopLandingPage() {
        assertEquals("Page Title does not match", PAGE_TITLE, loginPage.getPageTitle());
    }

    @When("User clicks on LOGIN button")
    public void userClicksOnLOGINButton() {
        loginPage.clickOnLogin();
    }

    @And("User enters {string}, {string}")
    public void userEnters(String email, String password) {
        loginPage.setEmail(email);
        loginPage.setPassword(password);
    }

    @And("User clicks on Log in button")
    public void userClicksOnLogInButton() {
        loginPage.clickOnLoginButton();
    }

    @Then("User is on eShop home page")
    public void userIsOnEShopHomePage() {
        assertEquals("Page Title does not match", PAGE_TITLE, loginPage.getPageTitle());

    }

    @Given("User selects {string}, {string}")
    public void userSelects(String brand, String type) {
        loginPage.selectBrand(brand);
        loginPage.selectType(type);
    }

    @And("User clicks on search button")
    public void userClicksOnSearchButton() {
        loginPage.clickOnSendButton();
    }

    @When("User select the {string}")
    public void userSelectThe(String productName) {
        loginPage.addToCart(productName);
    }

    @And("User checkouts the order")
    public void userCheckoutsTheOrder() {
        loginPage.clickOnCheckOutButton();
    }

    @And("User is ready to pay for the order")
    public void userIsReadyToPayForTheOrder() {
        loginPage.clickOnPayNowButton();
    }

    @Then("User should see the success message")
    public void userShouldSeeTheSuccessMessage() {
        assertEquals("Page Header does not match ", PAGE_HEADER, loginPage.getHeader());
    }

}
