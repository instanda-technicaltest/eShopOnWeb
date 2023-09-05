package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Success_Page {
    //    pass driver to this class
    public Success_Page(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
//    ************************************************************************************
//      input = i_
//      button = b_
//      dropdown = ddi_
//      dropdown option = ddo_
//      text = txt_
//    ************************************************************************************

    @FindBy(xpath = "//h1")
    public WebElement txt_Message;


}
