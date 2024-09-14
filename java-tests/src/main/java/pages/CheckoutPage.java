package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class CheckoutPage extends Utilities {
    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(css="body > div > div > form > div > article:nth-child(2) > div:nth-child(1) > section:nth-child(4) > input.esh-basket-input")
    WebElement quantityBox;

    @CacheLookup
    @FindBy(xpath = "/html/body/div/div/form/div/div[3]/section[2]/button")
    WebElement updateButton;

    @CacheLookup
    @FindBy(xpath = "//div[@class='container']//article[2]//section[2]")
    WebElement total;

    @CacheLookup
    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    @CacheLookup
    @FindBy(xpath="//div[@class='row']//section[2]//input")
    WebElement paynowButton;

    public void quantitySelection(String quantity){

        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",quantityBox);
        System.out.println(quantity);
        quantityBox.clear();
        quantityBox.sendKeys("\""+quantity+"\"");
    }

    public void clickonupdateButton() throws InterruptedException {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4)); // 10 seconds timeout
        wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        Thread.sleep(5000);
        updateButton.click();

    }

    public String pricecheck(){
        return total.getText();
    }

    public void clickonCheckOutButton() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",checkoutButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        Thread.sleep(500);
        checkoutButton.click();
    }
    public void clickonPayButton() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",paynowButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4)); // 10 seconds timeout
        wait.until(ExpectedConditions.elementToBeClickable(paynowButton));
        Thread.sleep(500);
        paynowButton.click();
    }
}
