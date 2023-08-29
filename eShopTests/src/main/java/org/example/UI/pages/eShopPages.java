package org.example.UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.UI.utilities.BaseClass;

import java.util.List;


public class eShopPages extends BaseClass{

    public eShopPages(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[contains(@alt,'eShop On Web')]")
    WebElement title;
    @FindBy(xpath = "//a[contains(@class,'esh-identity-name')]")
    WebElement login;

    @FindBy(xpath = "//input[@id='Input_Email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='Input_Password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@class='esh-catalog-send']")
    WebElement sendButton;

    @FindBy(xpath = "//a[@class='btn esh-basket-checkout']")
    WebElement checkOutButton;


    @FindBy(xpath = "//input[@class='btn esh-basket-checkout']")
    WebElement payNowButton;

    @FindBy(xpath = "//h1")
    WebElement header;

    @FindBy(xpath = "//*[@id='CatalogModel_BrandFilterApplied']")
    WebElement brandDp;

    @FindBy(xpath = "//*[@id='CatalogModel_TypesFilterApplied']")
    WebElement typeDp;

    @FindBy(xpath = "//div[@class='esh-catalog-name']//parent::form//child::input[1]")
    WebElement addBasket;

    Actions actions = new Actions(driver);

    public String getPageTitle() {
        waitUntilElementVisible(title);
        return title.getAttribute("alt");
    }

    public void clickOnLogin() {
        waitForElementAndClick(login);
    }

    public void setEmail(String username) {
        waitForElementAndEnterValue(email, username);
    }

    public void setPassword(String passwordVal) {
        waitForElementAndEnterValue(password, passwordVal);
    }

    public void clickOnLoginButton() {
        waitForElementAndClick(loginButton);
    }

    public void selectBrand(String brand) {
        dropDownFields(brandDp, brand);
    }

    public void selectType(String type) {
        dropDownFields(typeDp, type);
    }

    public void clickOnSendButton() {
        waitForElementAndClick(sendButton);
    }

    public void addToCart(String product) {
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='esh-catalog-item col-md-4']/form//div[1]/span"));
        for (WebElement webElement : elementList) {
            if (webElement.getText().equals(product)) {
                javaScriptExecutorAndClick(addBasket);
                break;
            }
        }
    }

    public void clickOnCheckOutButton() {
        javaScriptExecutorAndClick(checkOutButton);
    }

    public void clickOnPayNowButton() {
        javaScriptExecutorAndClick(payNowButton);
    }

    public String getHeader() {
        waitUntilElementVisible(header);
        return header.getText();
    }

}
