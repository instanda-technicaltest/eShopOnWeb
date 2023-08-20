package stepdefs;

import common.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.PageObjectManager;

import java.io.IOException;

public class ApplicationStepDefs {
    private final PageObjectManager pageObjectManager;

    public ApplicationStepDefs() {
        pageObjectManager = new PageObjectManager(new TestContext());
    }
    private final GeneralStepDefs generalStepDefs=new GeneralStepDefs();

    @Given("user navigate to the eShopOnWeb site")
    public void user_navigate_to_the_e_shop_on_web_site() throws IOException {
        pageObjectManager.homePage.navigateToSite(generalStepDefs.getURL());

    }
    @Given("user login with {string} and {string}")
    public void user_login_with_and(String string, String string2) throws IOException {
        pageObjectManager.homePage.LoginToEShop();
    }

    @When("user select the {string} to Add to Basket")
    public void user_select_the_to_add_to_basket(String productName) {
        pageObjectManager.homePage.selectItem(productName);
    }

    @Then("user validate the shopping cart values")
    public void userValidateTheShoppingCartValues() {
        pageObjectManager.basketPage.verifyTotalPrice();
    }

    @And("user validate the checkout page")
    public void userValidateTheCheckoutPage() {
        pageObjectManager.checkOutPage.verifyCheckoutPrice();
    }

    @When("user proceed to checkout")
    public void user_proceed_to_checkout() {
        pageObjectManager.basketPage.checkOut();
    }

    @Then("the order is placed successfully")
    public void the_order_is_placed_successfully() {
        pageObjectManager.basketPage.thankYouPage();
    }

    @And("user select continue shopping")
    public void userSelectContinueShopping() {
        pageObjectManager.basketPage.continueShopping();

    }
}
