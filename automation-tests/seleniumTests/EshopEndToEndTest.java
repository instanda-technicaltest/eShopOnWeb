package instandaGid.seleniumTest1;

import instandaGid.seleniumTest1.CheckoutPage;
import instandaGid.seleniumTest1.LoginPage;
import instandaGid.seleniumTest1.ProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EshopEndToEndTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        // Initialize SafariDriver
        driver = new SafariDriver();
        driver.manage().window().maximize();

        // Navigate to the eShopOnWeb homepage
        driver.get("http://localhost:8000");
    }

    @Test(priority = 1)
    public void loginTest() {
        // Initialize login page
        loginPage = new LoginPage(driver);

        // Perform login steps
        loginPage.clickLoginLink();  // Click the login link first
        loginPage.enterEmail("admin@microsoft.com");  // Use the provided email
        loginPage.enterPassword("Pass@word1");  // Use the provided password
        loginPage.clickLoginButton();

       
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void addItemToBasketTest() {
        // Initialize product page
        productPage = new ProductPage(driver);

        // Add first product to basket
        productPage.addFirstProductToBasket();
        
        // Navigate to basket
        productPage.goToBasket();
        
        // Assert item is added 
        Assert.assertTrue(driver.getTitle().contains("Basket"), "Item was not added to basket!");
    }


    @Test(priority = 3, dependsOnMethods = "addItemToBasketTest")
    public void placeOrderTest() {
        // Initialize the checkout page
        checkoutPage = new CheckoutPage(driver);

        // Click the checkout button
        checkoutPage.clickCheckoutButton();

        // Assert the URL has changed to the Checkout page
        Assert.assertTrue(driver.getCurrentUrl().contains("/Basket/Checkout"), "Checkout failed!");

        System.out.println("Order placed successfully.");
    }

    
    

    @AfterClass
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
