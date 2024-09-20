package instandaGid.seleniumTest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    // Locators
    By loginLink = By.xpath("//a[@href='/Identity/Account/Login']");  // Locate the "Login" link
    By emailField = By.id("Input_Email");  // Locate the email field
    By passwordField = By.id("Input_Password");  // Locate the password field
    By loginButton = By.xpath("//button[text()='Log in']");  // Locate the login button

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click the "Login" link
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // Methods to interact with the login form
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Method to perform the entire login flow
    public void login(String email, String password) {
        clickLoginLink();  // Click the login link first
        enterEmail(email);  // Enter email once the login page loads
        enterPassword(password);  // Enter password
        clickLoginButton();  // Click the login button to submit
    }
}
