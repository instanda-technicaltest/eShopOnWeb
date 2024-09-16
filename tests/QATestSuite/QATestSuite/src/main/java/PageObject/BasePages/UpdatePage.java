package PageObject.BasePages;

import PageFactory.AbstractPageComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdatePage extends AbstractPageComponent {
    WebDriver driver;

    public UpdatePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css= "h3.esh-catalog-items")
    WebElement validation;

    public String getValidation(){
        return (waitForElementstoappear(validation, 10).getText());
    }
}
