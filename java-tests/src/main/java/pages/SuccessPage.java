package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;

public class SuccessPage extends Utilities {
    public SuccessPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//*[text()='Thanks for your Order!']")
    WebElement successMessage;

    public String verifyMessage(){
        return successMessage.getText();
    }
}
