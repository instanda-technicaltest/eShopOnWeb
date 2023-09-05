package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Update_Page {

    //    pass driver to this class
    public Update_Page(WebDriver driver)
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
//    ************************************************************************************

    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article")
    public WebElement it_Article;

    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[2]")
    public WebElement n_Name;
    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[3]")
    public WebElement p_Price;

    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[5]")
    public WebElement tp_Total_Quantity_Price;

    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article//input[@min='0']")
    public WebElement q_Quantity;

    @FindBy(xpath = "//section[contains(@class,'col-lg-3')]/parent::div/parent::article//input[contains(@name,'].Quantity')]")
    public WebElement q_Quantity_Value;

    @FindBy(xpath = "//section[contains(text(),'Total')]/parent::article/following-sibling::article//section[2]")
    public WebElement tv_Total;

    @FindBy(xpath = "//a[contains(text(),'[ Continue Shopping ]')]")
    public WebElement b_Continue_Shopping;

    @FindBy(xpath = "//a[contains(text(),'[ Checkout ]')]")
    public WebElement b_Checkout;

    @FindBy(xpath = "//button[contains(text(),'[ Update ]')]")
    public WebElement b_Update;
}
