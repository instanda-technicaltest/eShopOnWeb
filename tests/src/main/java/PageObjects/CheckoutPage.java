package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@class='btn esh-basket-checkout']")
    private WebElement payBtn;
    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 25), this);
    }

    public void payNow(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", payBtn);
    }

}
