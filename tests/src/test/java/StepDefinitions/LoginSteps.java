
package StepDefinitions;

import PageObjects.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import Utilities.TestContext;
import org.testng.Assert;

public class LoginSteps {


    TestContext testContext;
    LoginPage loginPage;

    public LoginSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().loginPage();
    }


    @And("I navigate to login page")
    public void i_navigate_to_login_page() {
        loginPage.clickloginButton();
    }

    @And("I input {string} and {string} to login page")
    public void i_input_username_and_password(String username, String password) {
        loginPage.fillCredentials(username,password);
    }

    @And("I click on login button")
    public void i_click_on_login_button(){
        loginPage.submitLoginForm();
    }

    @Then("I am logged in successfully")
    public void i_can_checkout(){
        loginPage.isIdentityShown();
    }

    @Then("validation error is shown")
    public void validation_error_shown(){
        Assert.assertTrue(loginPage.isInvalidLogin());
    }

    @And("I click on manage products")
    public void i_can_manage_products(){
        loginPage.manageProducts();
    }

    @Then("I can see manage product page")
    public void i_can_see_manage_product_page() {
        String title = loginPage.getTitle();
        Assert.assertEquals(title, "eShopOnWeb Admin: Manage Product Catalog");
    }


}
