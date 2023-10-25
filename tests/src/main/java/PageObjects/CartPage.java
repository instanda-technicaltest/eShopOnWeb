package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CartPage extends BasePage {
    @FindBy(xpath = "//input[@class='esh-basket-input']")
    private WebElement quantityField;
    @FindBy(xpath = "//button[@class='btn esh-basket-checkout']")
    private WebElement updateBtn;

    @FindBy(xpath = "//a[@href='/Basket/Checkout']")
    private WebElement checkoutButton;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 25), this);
    }

    public void updateQuantity(int quantity){
        quantityField.clear();
        quantityField.sendKeys("0");
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", updateBtn);
    }

    public void checkout(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", checkoutButton);
    }
}
