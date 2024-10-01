package com.eshoponweb.steps;

import com.eshoponweb.pages.BasketPage;
import com.eshoponweb.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class DemoUserStepDef {
    HomePage homePage = new HomePage();
    BasketPage basketPage = new BasketPage();
    static String firstProductName;
    static String firstProductPrice;
    SoftAssert softAssert = new SoftAssert();


    @Given("the user visits eShopOnWeb website")
    public void theUserVisitsEShopOnWebWebsite() {
    }

    @Then("Logo is present on the home page")
    public void logoIsPresentOnTheHomePage() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on the home page");
    }

    @And("Atleast one product is displayed on the home page")
    public void atleastOneProductIsDisplayedOnTheHomePage() {
        Assert.assertTrue(homePage.verifyAtleastOneProductIsDisplayed(), "No product is displayed on the home page");
    }

    @And("Product image, name, price, add to basket button are displayed on the home page")
    public void productImageNamePriceAddToBasketButtonAreDisplayedOnTheHomePage() {
        Assert.assertTrue(homePage.verifyProductDetails(), "Product details are not displayed");

    }

    @And("Login link is displayed on the home page")
    public void loginLinkIsDisplayedOnTheHomePage() {
        Assert.assertTrue(homePage.loginLinkIsDisplayed(), "Login link is not displayed");
    }

    @When("the user clicks on Add to basket button for first product")
    public void theUserClicksOnAddToBasketButtonForFirstProduct() {
        firstProductName = homePage.getFitstProductName();
        firstProductPrice = homePage.getProductPrices().get(0).toString();
        System.out.println(homePage.getProductPrices().get(0));
        System.out.println("First product name is: " + homePage.getProductNames());
        homePage.addFirstProductToCart();
    }

    @Then("the user is on the {string} page")
    public void theUserIsOnThePage(String pageName) {
       homePage.verifyUserIsOnGivenPage(pageName);
    }

    @And("basket in header displays {int}")
    public void basketInHeaderDisplays(int basketQty) {
        Assert.assertEquals(basketPage.getBasketQuantity(), basketQty, "Basket Quantity is not matching");

    }

    @And("the product added to the basket is displayed with image, name, price, quantity, total price")
    public void theProductAddedToTheBasketIsDisplayedWithImageNamePriceQuantityTotalPrice() {
        softAssert.assertEquals(firstProductName, basketPage.getProductNames().get(0), "Product Name is not matching");
        softAssert.assertEquals(firstProductPrice, String.format("%.2f", basketPage.getProductPrices().get(0)), "Product Price is not matching");
        softAssert.assertEquals(basketPage.getBasketQuantity(), 1, "Basket Quantity is not matching");
        softAssert.assertEquals(basketPage.getTotal(), basketPage.getProductPrices().get(0), "Total Price is not matching");
        softAssert.assertAll();

    }

    @When("the user clicks on Login link")
    public void theUserClicksOnLoginLink() {
    }

    @And("Login form is displayed with email, password, remember me, login button")
    public void loginFormIsDisplayedWithEmailPasswordRememberMeLoginButton() {
    }

    @And("Register link is displayed on the Login page")
    public void registerLinkIsDisplayedOnTheLoginPage() {

    }
}
