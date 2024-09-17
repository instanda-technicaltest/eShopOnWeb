package PageFactory;

import PageObject.BasePages.LogInPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AbstractPageComponent {
    WebDriver driver;
    public static Logger log = LogManager.getLogger(AbstractPageComponent.class.getName());
    public AbstractPageComponent(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//img[@src ='/images/brand.png']")
    WebElement brandImage;


    @FindBy(css=".esh-identity-name")
    WebElement loginButton;

    @FindBy(xpath = "//form[@id= 'logoutForm']/section[1]/div")
    WebElement logoutmenu;

    @FindBy(xpath = "//form[@id= 'logoutForm']/section[2]/a[3]/div")
    WebElement logoutbutton;

    public WebElement getBrandImage(){
        return waitForElementstoappear(brandImage,10);

    }

    public LogInPage clickloginButton(){
        loginButton.click();
        System.out.println("Login button clicked");
        LogInPage lp = new LogInPage(driver);
        return lp;
    }


    public WebElement getIdentityUsername(){
        return waitForElementstoappear(loginButton,10);
    }

    public void moveToLogoutMenu(){
        moveToElement(this.logoutmenu);
        log.info("Cursor moved to logout menu");

    }
    public WebElement displayLogoutButton(){
        return waitForElementstoappear(logoutbutton,10);

    }
    public void clickLogoutButton(){
        moveToElement(this.logoutbutton);
        logoutbutton.click();
      log.info("logout button is clicked");
    }



    public WebElement waitForElementstoappear(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


    }
    public WebElement waitForElementstoappear(WebElement element, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, time);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForListElementstoAppear(By locator, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public List<WebElement> waitForListElementstoAppear(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(driver, time);
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void moveToElement(WebElement target){
        Actions action = new Actions(driver);
        action.moveToElement(target).build().perform();
    }

    public WebElement waitForElementtoBeClickable(WebElement element, int time)
    {
         WebDriverWait wait = new WebDriverWait(driver, time);
         return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement findElementparentscope(WebElement parent, By childLocator){
        return parent.findElement(childLocator);
    }

    public void scrollElemnentToView(WebElement focuselemnt){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", focuselemnt);
    }
    public void scrollbyheight(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void goToUrl(String url){
        driver.get(url);
    }

}

