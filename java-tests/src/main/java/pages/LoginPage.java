package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utilities;

public class LoginPage extends Utilities {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(css ="#Input_Email")
    WebElement emailTab;

    @CacheLookup
    @FindBy(css="#Input_Password")
    WebElement passwordTab;

    @CacheLookup
    @FindBy(xpath = "/html/body/div/div/div/div/section/form/div[5]/button")
    WebElement Log_inbutton;

    public void enterValidCredentials(String email,String password){
        emailTab.sendKeys(email);
        passwordTab.sendKeys(password);
    }

    public void clickOnLoginButton(){
        Log_inbutton.click();
    }
}
