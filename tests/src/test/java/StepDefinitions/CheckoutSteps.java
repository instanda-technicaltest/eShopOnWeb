package StepDefinitions;

import PageObjects.BasePage;
import PageObjects.CheckoutPage;
import Utilities.TestContext;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class CheckoutSteps {
    TestContext testContext;
    CheckoutPage checkoutPage;
    BasePage basePage;
    public CheckoutSteps(TestContext context) {
        testContext = context;
        checkoutPage = testContext.getPageObjectManager().reviewPage();
    }


    @And("I see review Page")
    public void i_can_update_the_quantity() {
        String title = checkoutPage.getTitle();
        Assert.assertEquals(title, "Checkout - Microsoft.eShopOnWeb");
    }

    @And("I make payment")
    public void i_can_make_payment(){
        checkoutPage.payNow();
        String title = checkoutPage.getTitle();
        Assert.assertEquals(title, "Checkout Complete - Microsoft.eShopOnWeb");
    }

}