package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;

public class TShirtPage extends Utilities {
    public TShirtPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath="/html/body/div/div/div[2]/div[1]/form/input[1]")
    WebElement tshirtaddtobasket;

    public void clickOnAddToBasketButton(){
        Actions action =new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Wait<WebDriver> wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(tshirtaddtobasket));
        tshirtaddtobasket.click();
    }
}
