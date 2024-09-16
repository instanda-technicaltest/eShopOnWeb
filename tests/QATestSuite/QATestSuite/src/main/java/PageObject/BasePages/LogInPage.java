package PageObject.BasePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PageFactory.AbstractPageComponent;

public class LogInPage extends AbstractPageComponent {
    WebDriver driver;

    public LogInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "Input_Email")
    public WebElement Input_Email;

    @FindBy(id = "Input_Password")
    WebElement Input_Password;

    @FindBy(xpath ="//button[@type='submit']")
    WebElement Button_Login;

@FindBy(tagName = "h2")
    WebElement Header_LogInIcon;

public void enterEmail(String email) {
    Input_Email.sendKeys(email);
    System.out.println("Email entered");
}



public WebElement getHeaderIcon(){
   return waitForElementstoappear(Header_LogInIcon,10);
}




    public void enterPassword(String s) {
    Input_Password.sendKeys(s);
    System.out.println("Password entered");
    }

    public void clickOnLogin() {
    Button_Login.click();
    System.out.println("Login clicked");
    }
}
