package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import Utilities.TestContext;
import PageObjects.CatalogPage;
import org.testng.Assert;

public class CatalogSteps {

    TestContext testContext;
    CatalogPage catalogPage;

    public CatalogSteps(TestContext context) {
        testContext = context;
        catalogPage = testContext.getPageObjectManager().catalogPage();
    }


    @Given("I am on the Catalog page")
    public void i_am_on_the_catalog_page() {
        String title = catalogPage.getTitle();
        Assert.assertEquals(title, "Catalog - Microsoft.eShopOnWeb");
    }

    @Then("I should see a list of products")
    public void i_should_see_a_list_of_products() {
        boolean isProductListDisplayed = catalogPage.isProductListDisplayed();
        Assert.assertTrue(isProductListDisplayed);
    }

    @When("I click on Add to basket for a product")
    public void i_click_on_add_to_basket_for_a_product() {
        catalogPage.addToBasket();
    }

    @Then("the product should be added to my cart")
    public void the_product_is_added_to_basket() {
        int basketItemCount = catalogPage.basketItemCount();
        Assert.assertEquals(basketItemCount, 1);
    }

    @When("I select the brand {string} from the dropdown")
    public void i_select_the_brand_from_the_dropdown(String brand) {
        catalogPage.selectBrandName(brand);
    }

    @And("I select the type {string} from the dropdown")
    public void i_select_the_product_from_the_dropdown(String productType) {
        catalogPage.selectProductType(productType);
    }

    @And("I click on the Search button")
    public void i_click_on_the_search_button() {
        catalogPage.submitFilter();
    }

    @Then("I should see a list of filtered product")
    public void i_should_see_a_list_of_filtered_product() {
        String catalogueName = catalogPage.isFilteredProductDisplayed();
        Assert.assertEquals(catalogueName, ".NET BLACK & WHITE MUG");
    }
}
