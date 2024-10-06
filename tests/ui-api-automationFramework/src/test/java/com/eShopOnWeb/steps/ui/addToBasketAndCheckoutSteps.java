package com.eShopOnWeb.steps.ui;

import com.eShopOnWeb.ui.pages.BasketPage;
import com.eShopOnWeb.ui.pages.CatalogPage;
import com.eShopOnWeb.ui.pages.CheckoutCompletePage;
import com.eShopOnWeb.ui.pages.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

/**
 * Created by Soniya Patel
 */

public class addToBasketAndCheckoutSteps {

    @And("I add product \"Roslyn Red Sheet\" to the basket")
    public void iAddProductRoslynRedSheetToTheBasket() {
        new CatalogPage().clickOnAddToBasket();
    }

    @Then("I verify the product name is {string}")
    public void iVerifyTheProductNameIs(String expectedProductText) {
        Assert.assertEquals(new BasketPage().getProductText(), expectedProductText, "product name is mismatched");
    }

    @And("I update the quantity to {string}")
    public void iUpdateTheQuantityTo(String qtyVal) {
        new BasketPage().enterQty(qtyVal);
    }

    @And("I click on the update button")
    public void iClickOnTheUpdateButton() {
        new BasketPage().clickOnUpdateButton();
    }

    @Then("I verify the total is {string}")
    public void iVerifyTheTotalIs(String expectedTotal) {
        Assert.assertEquals(new BasketPage().getTotalPrice(), expectedTotal, "Total is in incorrect");
    }

    @And("I click on the checkout button")
    public void iClickOnTheCheckoutButton() {
        new BasketPage().clickOnCheckoutButton();
    }

    @Then("Page title should be {string}")
    public void pageTitleShouldBe(String expectedPageTitle) {
        Assert.assertEquals(new CheckoutPage().getCheckoutPageTitle(), expectedPageTitle, "Incorrect page title");
    }

    @And("I click on the Pay Now button")
    public void iClickOnThePayNowButton() {
        new CheckoutPage().clickOnPayNowButton();
    }

    @Then("I verify the message {string}")
    public void iVerifyTheMessage(String expectedText) {
        Assert.assertEquals(new CheckoutCompletePage().getHeaderText(), expectedText, "Expected message is not displayed");
    }
}
