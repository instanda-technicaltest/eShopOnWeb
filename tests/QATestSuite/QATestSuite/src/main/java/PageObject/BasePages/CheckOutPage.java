package PageObject.BasePages;

import PageFactory.AbstractPageComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractPageComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   @FindBy(tagName = "h1")
   WebElement headericon;

    @FindBy(css = "input.btn.esh-basket-checkout")
    WebElement paynow;


    public String getHeaderItemmessage(){
        waitForElementstoappear(headericon,10);
        return headericon.getText();

    }

    public SuccessPage clickPayNow() throws InterruptedException {
        scrollbyheight();
        waitForElementstoappear(paynow,10);
        waitForElementtoBeClickable(paynow,10);
        Thread.sleep(500);
        paynow.click();
        System.out.println("Checkout is clicked");
        SuccessPage sp = new SuccessPage(driver);
        return sp;
    }

}
