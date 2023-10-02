package com.eShop.stepdefs;

import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import eShop_BDDCucumber.eShop_CucumberFramework.DriverInvoke;
import com.eShop.PageObjects.PageObjects;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	@Inject
	DriverInvoke context;

    @Inject
    PageObjects commonPageObjects;

    @Given("I Open the Browser")
    public void i_open_the_browser() {
        context.invokeDriver();
    }

    @And("I Navigate to the URL")
    public void i_navigate_to_the_url() throws InterruptedException {
        context.navigateBrowser(context.getCoreConfig().browserAppURL());
    }
    
    @When("I click on Login")
    public void i_click_on_login() {
    	commonPageObjects.clickOnLoginLink();
    }

    @Then("Login page is displayed")
    public void login_page_is_displayed() {
    	commonPageObjects.validatePageTitleToCheckPageIsNavigated("Log in");
    }
    
    @When("I click on Register as a new user")
    public void i_click_on_register_as_a_new_user() {
    	commonPageObjects.clickOnRegisterLink();
    }
    
    @And("I enter email and password")
    public void i_enter_email_and_password() {
    	commonPageObjects.EnterRegisterDetails();
    }
    
    @And("I click Register")
    public void i_click_register() {
    	commonPageObjects.clickOnRegister();
    }
    
    @Then("I login to eShopOnWeb Page")
    public void i_login_to_eShopOnWeb_page() {
    	commonPageObjects.validatePageTitleToCheckPageIsNavigated("eShopOnWeb");
    }
    
    @And("I logout")
    public void i_logout() {
    	commonPageObjects.clickOnLogout();
    }
    
    @When("I login back with registered user")
    public void i_login_back_with_registered_user() {
    	commonPageObjects.RegisteredUserLogin();
    }
    
    @When("I enter {string} and {string}")
    public void i_enter_and(String strEmail, String strPwd) {
    	commonPageObjects.UserLogin(strEmail,strPwd);
    }
    
    @And("I enter invalid {string} or {string}")
    public void I_enter_invalid_or(String strEmail,String strPwd) {
    	commonPageObjects.UserLogin(strEmail,strPwd);
    }
    
    @Then("Login failed error is displayed")
    public void login_failed_error_is_displayed() {
    	commonPageObjects.LoginFailed();
    }
    
    @When("I select {string} and click search")
    public void i_select_and_click_search(String strType) {
    	commonPageObjects.Search(strType);
    }
    
    @Then("all {string} search products are resulted")
    public void all_search_products_are_resulted(String strType) {
    	commonPageObjects.SearchResult(strType);
    }
    
    @And("verify the all products count")
    public void verify_the_all_products_count() {
    	commonPageObjects.ProductsCount();
    }
    
    @When("I add below products to basket")
    public void i_add_below_products_to_basket(DataTable table) {
    	commonPageObjects.AddingProduct(table);
    }
    
    @Then("verify all products count in cart")
    public void verify_the_all_products_count_in_cart() {
    	commonPageObjects.ProductsCountInCart();
    }
    
}
