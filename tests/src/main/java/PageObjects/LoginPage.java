package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    @FindBy(xpath = "//a[@href='/Identity/Account/Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='Input_Email']")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id='Input_Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitLoginForm;

    @FindBy(xpath = "//div[@class='esh-identity-name']")
    private WebElement identityName;

    @FindBy(xpath = "//*[contains(text(),'Invalid login attempt.')]")
    private WebElement admin;


    public void fillCredentials(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    public void clickloginButton() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", loginButton);
    }

    public void submitLoginForm(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", submitLoginForm);
    }

    public void isIdentityShown(){
        identityName.isDisplayed();
    }

    public boolean isInvalidLogin(){
        List<WebElement> l= driver.findElements(By.xpath("//*[contains(text(),'Invalid login attempt.')]"));
         return !l.isEmpty();
    }

    public void manageProducts(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", identityName);
        executor.executeScript("arguments[0].click();", admin);
    }

    public String getTitle(){
        return this.driver.getTitle();
    }

}
