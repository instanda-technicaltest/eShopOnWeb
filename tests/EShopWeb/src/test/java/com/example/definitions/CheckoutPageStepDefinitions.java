package com.example.definitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckoutPageStepDefinitions {

    public static WebDriver driver;
    private static WebDriverWait webDriverWait;

    private static float CHECKOUT_AMOUNT;

    public CheckoutPageStepDefinitions(){
        this.driver = Hooks.driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @And("User select quantity as {string}")
    public void userSelectQuantityAs(String number) {
        CHECKOUT_AMOUNT = Float.valueOf(driver.findElement(By.xpath("(//section[@class='esh-basket-item esh-basket-item--middle col-xs-2'])[1]")).getText().split(" ")[1]);
        driver.findElement(By.className("esh-basket-input")).clear();
        driver.findElement(By.className("esh-basket-input")).sendKeys(number);
    }

    @And("User validates the total price of the product")
    public void userValidatesTheTotalPriceOfTheProduct() {
        driver.findElement(By.name("updatebutton")).click();
        float totalAmount = Float.valueOf(driver.findElement(By.xpath("//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']")).getText().split(" ")[1]);
        Assert.assertEquals(totalAmount,(CHECKOUT_AMOUNT * 2));
    }

    @And("User click on checkout button")
    public void userClickOnCheckoutButton() {
        driver.findElement(By.xpath("//a[text()='[ Checkout ]']")).click();
    }

    @And("User validates the total price of the product on pay now page")
    public void userValidatesTheTotalPriceOfTheProductOnPayNowPage() {
        float totalAmount = Float.valueOf(driver.findElement(By.xpath("//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']")).getText().split(" ")[1]);
        Assert.assertEquals(totalAmount,(CHECKOUT_AMOUNT * 2));
    }

    @And("User click on Pay now button")
    public void userClickOnPayNowButton() {
        driver.findElement(By.xpath("//input[@value='[ Pay Now ]']")).click();
    }

    @And("User validates {string} message")
    public void userValidatesMessage(String message) {
        String actualMessage = driver.findElement(By.xpath("//div[@class='esh-app-wrapper']/div[@class='container']/h1")).getText();
        Assert.assertEquals(actualMessage,message);
    }
}
