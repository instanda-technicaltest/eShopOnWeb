package PageObject.BasePages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import PageFactory.AbstractPageComponent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogPage extends AbstractPageComponent {
    WebDriver driver;
    public CatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    //@FindBy(xpath = "//a[@href = '/Identity/Account/Login']")


    @FindBy(id = "Next")
    WebElement nextButton;

    By cataloglistid = By.cssSelector(".esh-catalog-items div form");
    By catalogListItems = By.xpath("//div[@class ='esh-catalog-items row']/div");
    By catalogItemName = By.cssSelector(".esh-catalog-name");
    By addToBasket = By.cssSelector("input.esh-catalog-button");



 public List<WebElement> getCatalogList(){
   return waitForListElementstoAppear(cataloglistid,10);
 }

 public WebElement getNextButton(){
     return waitForElementstoappear(nextButton,10);
 }

 public void clickNextButton(){
    getNextButton().click();
    System.out.println("Next button clicked");
 }

 public int getNumberOfItemsdisplayedinCatalog(){
     List<WebElement>productlist = getCatalogList();
     int totalitems = productlist.size();
     try{
         while (getNextButton().isEnabled()) {
             clickNextButton();
             List<WebElement> cataloglist1 = getCatalogList();
             totalitems += cataloglist1.size();

         }
     } catch(ElementClickInterceptedException e) {
         System.out.println("The total list for catalog items= " +totalitems);
     }
     return totalitems;


 }
public BasketPage AddItemToBasket(String ProductName) throws InterruptedException {
    boolean flag = false;
    try {
        do {
            List<WebElement> productlist = waitForListElementstoAppear(catalogListItems,10);
            WebElement product = productlist.stream().filter(a -> a.findElement(catalogItemName).getText().equalsIgnoreCase(ProductName)).findFirst()
                    .orElse(null);
            if (product == null) {
                flag = true;
                clickNextButton();
                continue;
            } else {
                assert product != null;
                WebElement Add = findElementparentscope(product,addToBasket);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                scrollElemnentToView(Add);
                waitForElementtoBeClickable(Add,10);
                Thread.sleep(500);
                moveToElement(Add);
                Add.click();
                System.out.println("Click on Add to Basket button");
                flag = false;
            }
        } while (flag);
    }catch(ElementClickInterceptedException e){
        System.out.println("The product is not present in the catalog");
    }
    BasketPage bp = new BasketPage(driver);
    return bp;


}





}
