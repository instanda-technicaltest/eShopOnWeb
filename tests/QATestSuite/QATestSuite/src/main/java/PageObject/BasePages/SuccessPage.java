package PageObject.BasePages;

import PageFactory.AbstractPageComponent;
import com.sun.jna.WString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage extends AbstractPageComponent {
    WebDriver driver;
    public SuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(tagName = "h1")
    WebElement successMessage;

    public String getSuccessMessage() {
        waitForElementstoappear(successMessage,10);
        return successMessage.getText();
    }
}
