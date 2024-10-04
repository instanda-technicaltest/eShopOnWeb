package com.example.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class EShopStepDefinitions {

    public static WebDriver driver;
    private static WebDriverWait webDriverWait;

    public EShopStepDefinitions(){
        this.driver = Hooks.driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Given("User is on EShop Home page {string}")
    public void userIsOnEShopHomePage(String url) {
        driver.get(url);
    }

    @Then("User clicks on login button")
    public void userClickOnLoginButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/Identity/Account/Login']"))));
        driver.findElement(By.xpath("//a[@href='/Identity/Account/Login']")).click();
    }

    @And("User validates the home page")
    public void userValidatesTheHomePage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[@alt='eShop On Web']"))));
    }

    @And("User selects {string} from type dropdown list")
    public void userSelectsFromTypeDropdownList(String name) {
        Select brandDropDown = new Select(driver.findElement(By.id("CatalogModel_TypesFilterApplied")));
        brandDropDown.selectByVisibleText(name);
    }

    @And("User selects {string} from brand dropdown list")
    public void userSelectsFromBrandDropdownList(String name) {
    Select brandDropDown = new Select(driver.findElement(By.id("CatalogModel_BrandFilterApplied")));
    brandDropDown.selectByVisibleText(name);
    }

    @And("User clicks on arrow button")
    public void userClicksOnArrowButton() {
        driver.findElement(By.className("esh-catalog-send")).click();
    }

    @And("User validates the filter results")
    public void userValidatesTheFilterResults() {
        int count = driver.findElements(By.xpath("//div[@class='esh-catalog-items row']/div")).size();
        Assert.assertEquals(count,1,"Filter is not applied properly");
    }

    @And("User clicks on Add to Basket button")
    public void userClicksOnAddToBasketButton() {
        driver.findElement(By.xpath("(//input[@class='esh-catalog-button'])[1]")).click();
    }

    @And("User validates product is added to the basket")
    public void userValidatesProductIsAddedToTheBasket() {
        int itemsCount = Integer.valueOf(driver.findElement(By.className("esh-basketstatus-badge")).getText().trim());
        Assert.assertEquals(itemsCount,1);
    }

    @And("User click on basket link")
    public void userClickOnBasketLink() {
        driver.findElement(By.className("esh-basketstatus")).click();
    }
}
