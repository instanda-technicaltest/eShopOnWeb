package instandaGid.seleniumTest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By firstProduct = By.xpath("//input[contains(@value, 'ADD TO BASKET')]");
    By basketLink = By.xpath("//img[@src='/images/cart.png']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time to 20 seconds
    }

    // Add first product to basket
    public void addFirstProductToBasket() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product); // Fallback with JS click
    }

    // Navigate to basket
    public void goToBasket() {
        WebElement basket = wait.until(ExpectedConditions.elementToBeClickable(basketLink));
        basket.click();
    }
}
