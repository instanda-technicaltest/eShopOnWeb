package StepDefinitions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import Utilities.TestContext;
import org.testng.Assert;
import PageObjects.CartPage;

public class CartSteps {

    TestContext testContext;
    CartPage cartPage;

    public CartSteps(TestContext context) {
        testContext = context;
        cartPage = testContext.getPageObjectManager().cartPage();
    }


    @Then("I see the cart Page")
    public void i_am_on_the_catalog_page() {
        String title = cartPage.getTitle();
        Assert.assertEquals(title,"Basket - Microsoft.eShopOnWeb");
    }

    @And("I can update the quantity of the product added to {string}")
    public void i_can_update_the_quantity(String quantity) {
       cartPage.updateQuantity(2);
    }

    @And("I can checkout")
    public void i_can_checkout(){
        cartPage.checkout();
    }


}
