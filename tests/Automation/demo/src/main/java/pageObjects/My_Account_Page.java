package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class My_Account_Page {


    //    pass driver to this class
    public My_Account_Page(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
//    ************************************************************************************
//      input = i_
//      button = b_
//      dropdown = ddi_
//      dropdown_option = ddo_
//      items =  it_
//      price = p_
//      total_price = tp_
//      quantity = q_
//      text = txt_
//    ************************************************************************************

    @FindBy(xpath = "//h2")
    public WebElement txt_Manage_Your_Account;


}
