package Managers;

import PageObjects.CartPage;
import PageObjects.CatalogPage;
import PageObjects.LoginPage;
import PageObjects.CheckoutPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver webDriver;
    private LoginPage loginPage;
    private CatalogPage catalogPage;

    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public CatalogPage catalogPage() {
        if (catalogPage == null) catalogPage = new CatalogPage(webDriver);
        return catalogPage;
    }

    public CartPage cartPage() {
        if (cartPage == null) cartPage = new CartPage(webDriver);
        return cartPage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) loginPage = new LoginPage(webDriver);
        return loginPage;
    }

    public CheckoutPage reviewPage() {
        if (checkoutPage == null) checkoutPage = new CheckoutPage(webDriver);
        return checkoutPage;
    }
}
