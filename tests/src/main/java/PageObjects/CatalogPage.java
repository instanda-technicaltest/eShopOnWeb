package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class CatalogPage extends BasePage {
    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 25), this);
    }


    @FindBy(xpath = "//*[@alt='eShop On Web']")
    private WebElement eShopLogo;
    @FindBy(xpath = "//*[@class='esh-identity-name esh-identity-name--upper']")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@class='esh-catalog-items row']")
    private WebElement productList;
    @FindBy(xpath = "//a[@class='esh-basketstatus']")
    private WebElement basket;
    @FindBy(id = "CatalogModel_BrandFilterApplied")
    private WebElement selectBrandName;
    @FindBy(id = "CatalogModel_TypesFilterApplied")
    private WebElement selectProductType;
    @FindBy(xpath = "//input[@class='esh-catalog-send']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@class='esh-catalog-button']")
    private WebElement addToBasketButton;

    @FindBy(xpath = "//div[@class='esh-basketstatus-badge']")
    private WebElement basketItemCount;

    @FindBy(xpath = "//div[@class='esh-catalog-name']")
    private WebElement catalogueItemName;


    public boolean isProductListDisplayed() {
      return productList.isDisplayed();
    }
    public void addToBasket() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", addToBasketButton);
   }

    public int basketItemCount(){
        return Integer.parseInt(basketItemCount.getText());
    }

    public void selectBrandName(String brandName) {
        Select drpDownForBrand = new Select(selectBrandName);
        drpDownForBrand.selectByVisibleText(brandName);
    }

    public void selectProductType(String productType)
    {
        Select drpDownForBrand = new Select(selectProductType);
        drpDownForBrand.selectByVisibleText(productType);
    }

    public void submitFilter() {
        searchButton.click();
    }

    public String  isFilteredProductDisplayed() {
       return catalogueItemName.getText();
    }
}
