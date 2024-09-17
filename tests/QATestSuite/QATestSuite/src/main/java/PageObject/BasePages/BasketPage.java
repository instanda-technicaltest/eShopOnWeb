package PageObject.BasePages;

import PageFactory.AbstractPageComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class BasketPage extends AbstractPageComponent {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(BasketPage.class.getName());
    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//article[@class = 'esh-basket-items row']/div[1]/section[2]")
    List<WebElement> basketItemName;
    By Itemname = By.xpath("//article[@class = 'esh-basket-items row']/div[1]/section[2]");


    @FindBy(xpath = "//article[@class = 'esh-basket-items row']/div[1]/section[4]/input[2]")
    //@FindBy(css = ".esh-basket-input")
    WebElement basketItemNumber;


    @FindBy(xpath = "//a[contains(text(),'Checkout')]")
    WebElement checkoutButton;

    @FindBy(name = "updatebutton")
    WebElement updateButton;


    public List<String> getItemsinBasket() {
        List<WebElement> basketItems = waitForListElementstoAppear(Itemname, 10);
        List<String> basketitems = basketItems.stream().map(s -> s.getText()).collect(Collectors.toList());
        return basketitems;
    }

    public void updateNumberOfItems(String number) {
        scrollElemnentToView(basketItemNumber);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                basketItemNumber, "value", "0");

    }

    public UpdatePage clickUpdate() throws InterruptedException {
        scrollbyheight();
        waitForElementtoBeClickable(updateButton, 10);
        Thread.sleep(500);
        updateButton.click();
        UpdatePage up = new UpdatePage(driver);
        return up;
    }

    public CheckOutPage clickCheckout() throws InterruptedException {
        scrollbyheight();
        waitForElementtoBeClickable(checkoutButton, 10);
        Thread.sleep(500);
        checkoutButton.click();
        log.info("Checkout button clicked");
        CheckOutPage cp = new CheckOutPage(driver);
        return cp;


    }
}
