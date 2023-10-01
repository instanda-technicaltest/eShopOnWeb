package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    public Pages() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "Register as a new user")
    public WebElement registerLinkInLoginPage;

    @FindBy(id = "Input_ConfirmPassword")
    public WebElement confirmPassword;

    @FindBy(id = "Input_Email-error")
    public WebElement wrongEmailWarning;

    @FindBy(id = "Input_Password-error")
    public WebElement wrongPasswordWarning;

    @FindBy(xpath = "//div[@class='text-danger validation-summary-errors']")
    public WebElement passwordValidationMessage;

    @FindBy(id = "Input_ConfirmPassword")
    public WebElement confirmPasswordWarning;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement registerButtonInRegisterPage;

    @FindBy(id = "Next")
    public WebElement nextPageLink;

    @FindBy(id = "Previous")
    public WebElement previousPageLink;

    @FindBy(id = "CatalogModel_BrandFilterApplied")
    public WebElement brandFilter;

    @FindBy(id = "CatalogModel_TypesFilterApplied")
    public WebElement typeFilter;

    @FindBy(className = "esh-pager-item")
    public WebElement pager;

    @FindBy(xpath = "//a[@class='esh-identity-name esh-identity-name--upper']")
    public WebElement loginButton;

    @FindBy(id = "Input_Email")
    public WebElement email;

    @FindBy(id = "Input_Password")
    public WebElement password;

    @FindBy(xpath = "//div[@class='esh-identity-name']")
    public WebElement userName;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginSubmit;

    @FindBy(xpath = "(//input[@class='esh-catalog-button'])[1]")
    public WebElement firstItemAdd;

    @FindBy(xpath = "(//div[@class='esh-catalog-name'])[1]")
    public WebElement firstItemName;

    @FindBy(className = "//div[@class='esh-catalog-name']")
    public List<WebElement> productNames;

    @FindBy(className = "esh-catalog-send")
    public WebElement filterSearch;

    @FindBy(xpath = "(//div[@class='esh-catalog-price'])[1]")
    public WebElement firstItemPrice;

    @FindBy(xpath = "(//input[@class='esh-catalog-button'])[2]")
    public WebElement secondItemAdd;

    @FindBy(xpath = "(//input[@class='esh-catalog-button'])[1]")
    public WebElement secondItemName;

    @FindBy(xpath = "(//div[@class='esh-catalog-price'])[2]")
    public WebElement secondItemPrice;

    @FindBy(xpath = "//div[@class='esh-basketstatus-image']")
    public WebElement addToBasket;

    @FindBy(xpath = "(//section[@class='esh-basket-item esh-basket-item--middle col-xs-3'])[2]")
    public WebElement firstAddedItemNameInBasketPage;

    @FindBy(xpath = "(//section[@class='esh-basket-item esh-basket-item--middle col-xs-3'])[1]")
    public WebElement secondAddedItemNameInBasketPage;

    @FindBy(xpath = "(//section[@class='esh-basket-item esh-basket-item--middle col-xs-2'])[2]")
    public WebElement firstAddedItemPriceInBasketPage;

    @FindBy(xpath = "(//section[@class='esh-basket-item esh-basket-item--middle col-xs-2'])[1]")
    public WebElement secondAddedItemPriceInBasketPage;

    @FindBy(xpath = "//section[@class='esh-basket-item esh-basket-item--mark col-xs-2']")
    public WebElement totalPriceInBasketPage;

    @FindBy(className = "btn esh-basket-checkout text-white")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='text-danger validation-summary-errors']")
    public WebElement invalidLoginWarning;

    @FindBy(linkText = "Forgot your password?")
    public WebElement forgotPasswordLink;

    @FindBy(id = "Input_Email")
    public WebElement emailForgotPassword;

    @FindBy(className = "w-100 btn btn-lg btn-primary")
    public WebElement resetPassword;

    @FindBy(className = "esh-app-footer-text hidden-xs")
    public WebElement footerText;

    public void goToRegisterPage() {
        loginButton.click();
        registerLinkInLoginPage.click();
    }
}