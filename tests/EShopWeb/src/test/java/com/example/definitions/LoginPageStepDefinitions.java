package com.example.definitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPageStepDefinitions {
    public static WebDriver driver;
    private static WebDriverWait webDriverWait;

    public LoginPageStepDefinitions(){
        this.driver = Hooks.driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @And("User enters username as {string}")
    public void userEntersUsernameAs(String username) {
        driver.findElement(By.id("Input_Email")).sendKeys(username);
    }

    @And("User enters password as {string}")
    public void userEntersPasswordAs(String password) {
        driver.findElement(By.id("Input_Password")).sendKeys(password);
    }

    @And("User clicks on login button on login page")
    public void userClicksOnLoginButtonOnLoginPage() {
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }
}
