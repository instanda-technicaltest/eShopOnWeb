package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {

//    pass driver to this class
    public Login_Page(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
//    ************************************************************************************
//      input = i_
//      button = b_

//    ************************************************************************************
    @FindBy(xpath = "//input[@id='Input_Email']")
    public WebElement i_Email;

    @FindBy(xpath = "//input[@id='Input_Password']")
    public WebElement i_Password;

    @FindBy(xpath = "//button[text()='Log in']")
    public WebElement b_Login;
}
