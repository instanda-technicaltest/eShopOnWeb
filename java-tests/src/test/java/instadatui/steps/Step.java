package instadatui.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import pages.*;


public class Step {
    private static final Logger log= Logger.getLogger(Step.class);
    static {
        // Configure Log4j only once in a static block
        PropertyConfigurator.configure("src/test/java/resources/feature/log4j.properties");
        log.info("Log4j initialized in static block of StepDefinitions.");
    }
    MainPage mp=new MainPage();
    LoginPage lp=new LoginPage();
    TShirtPage tp=new TShirtPage();
    CheckoutPage cp=new CheckoutPage();
    SuccessPage sp=new SuccessPage();
    @Given("^I open the website$")
    public void iOpenTheWebsite() {
      mp.gettitle();
      log.info("Website open successfully");
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        mp.clickOnLoginButton();
        log.info("Login button clicked successfully");
    }

    @And("^I enter the email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iEnterTheEmailAndPassword(String email, String password) throws Throwable {
        lp.enterValidCredentials(email,password);
        log.info("Valid credentials has been entered");
    }

    @And("^I click on Log in button$")
    public void iClickOnLogInButton() {
        lp.clickOnLoginButton();
        log.info("Login button clicked successfully");
    }

    @Given("^I select T-Shirt from dropdown menu$")
    public void iSelectTShirtFromDropdownMenu() {
      mp.selectTshirtFromDropDown();
      mp.clickonFilterButton();
      log.info("Product has been selected");
    }

    @When("^I add \\.NET FOUNDATION SWEATSHIRT into cart$")
    public void iAddNETFOUNDATIONSWEATSHIRTIntoCart() {
        tp.clickOnAddToBasketButton();
        log.info("Product added to cart");
    }

    @And("^I make quantity as \"([^\"]*)\"$")
    public void iMakeQuantityAs(String quantity) throws Throwable {
        cp.quantitySelection(quantity);
        log.info("Quantity has been set");
    }

    @And("^I click on update button and verify the amount$")
    public void iClickOnUpdateButtonAndVerifyTheAmount() throws InterruptedException {
        Thread.sleep(5000);
        cp.clickonupdateButton();
        String actual=cp.pricecheck();
        double filterednumber=Double.parseDouble(actual.replaceAll("[^\\d.]",""));
        double Expected=12.00;
        Assert.assertEquals("Price assertion fails",Expected,filterednumber,0.0001);
        log.info("Amount verification done");
    }

    @And("^I click On checkout button$")
    public void iClickOnCheckoutButton() throws InterruptedException {
        cp.clickonCheckOutButton();
        log.info("Click on checkout button");
    }

    @And("^I click on Pay Now Button$")
    public void iClickOnPayNowButton() throws InterruptedException {
        cp.clickonPayButton();
        log.info("CLick on pay button");
    }

    @Then("^I verify the Success message$")
    public void iVerifyTheSuccessMessage() {
       String actual= sp.verifyMessage();
       String expected="Thanks for your Order!";
       Assert.assertEquals("Success message verification failed",expected,actual);
       log.info("Add to cart message verified");
    }
}
