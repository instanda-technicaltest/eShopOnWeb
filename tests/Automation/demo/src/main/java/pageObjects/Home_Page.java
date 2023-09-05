package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {
    //    pass driver to this class
    public Home_Page(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }
//    ************************************************************************************
//      input = i_
//      button = b_
//      dropdown = ddi_
//      dropdown option = ddo_
//      basket value = bk_
//    ************************************************************************************

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    public WebElement b_Login;

    @FindBy(xpath = "//div[@class='esh-identity-name']")
    public WebElement txt_User;
//    Brand DropDown
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']")
    public WebElement ddi_Brand;
// Brand Dropdown Options
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='All']")
    public WebElement ddo_Brand_All;
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='.NET']")
    public WebElement ddo_Brand_NET;
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='Azure']")
    public WebElement ddo_Brand_Azure;
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='Other']")
    public WebElement ddo_Brand_Others;
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='SQL Server']")
    public WebElement ddo_Brand_SQL_Server;
    @FindBy(xpath = "//select[@id='CatalogModel_BrandFilterApplied']/option[text()='Visual Studio']")
    public WebElement ddo_Brand_Visual_Studio;

// Type Drop Down
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']")
    public WebElement ddi_Type;

// Type Drop Down options
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='All']")
    public WebElement ddo_Type_All;
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='Mug']")
    public WebElement ddo_Type_Mug;
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='Sheet']")
    public WebElement ddo_Type_Sheet;
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='T-Shirt']")
    public WebElement ddo_Type_TShirt;
    @FindBy(xpath = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='USB Memory Stick']")
    public WebElement ddo_Type_USB_Memory_Stick;

//    Arrow button
    @FindBy(xpath = "//input[@class='esh-catalog-send']")
    public WebElement b_Arrow;

// Add to product button
    @FindBy(xpath = "//input[@value='[ ADD TO BASKET ]']")
    public WebElement b_Add_To_Basket;

// basket value
    @FindBy(xpath = "//div[@class='esh-basketstatus-badge']")
    public WebElement bk_Basket_Value;

    @FindBy(xpath = "//div[text()='My orders']")
    public WebElement ddo_My_Orders;

    @FindBy(xpath = "//div[text()='My account']")
    public WebElement ddo_My_Account;

    @FindBy(xpath = "//div[text()='Log Out']")
    public WebElement ddo_Logout;

}
