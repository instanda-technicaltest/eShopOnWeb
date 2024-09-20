package instandaGid.seleniumTest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locator for the checkout button  
    By checkoutButton = By.xpath("//a[@class='btn esh-basket-checkout' and @href='/Basket/Checkout']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        // Set an explicit wait of 20 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Method to place the order by clicking the checkout button
    public void clickCheckoutButton() {
        // Wait for the checkout button to be clickable
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        // Check if the element is visible and enabled
        if (isElementVisibleAndEnabled(checkoutBtn)) {
            // Scroll the element into view using JavaScript if needed
            scrollToElement(checkoutBtn);

            // Try clicking the element using normal click
            try {
                checkoutBtn.click();
                System.out.println("Clicked on checkout button.");
            } catch (Exception e) {
                System.out.println("Normal click failed, attempting to click using JavaScript.");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
            }
        } else {
            System.out.println("Checkout button is either not visible or not enabled.");
        }
    }

    // Helper method to scroll the element into view using JavaScript
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        System.out.println("Scrolled to the checkout button.");
    }

    // Helper method to check if the element is visible and enabled
    private boolean isElementVisibleAndEnabled(WebElement element) {
        boolean isVisible = element.isDisplayed();
        boolean isEnabled = element.isEnabled();

        // Log visibility and enablement status
        System.out.println("Element visibility (CSS): " + isVisible);
        System.out.println("Element enablement: " + isEnabled);

        return isVisible && isEnabled;
    }
}
