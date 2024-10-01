package com.eshoponweb.pages;

import com.eshoponweb.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class HomePage extends Utility {

    private static final Logger log = LogManager.getLogger(HomePage.class);

    @FindBy(xpath = "//img[@src='/images/brand.png']") //xpath brand logo
    private WebElement logo;

    @FindBy(xpath = "//span[@class='esh-pager-item']") //for text message
    private WebElement numberOfProducts;

    @FindBy(xpath = "//div[@class='esh-catalog-name']")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//img[@class='esh-catalog-thumbnail']")
    private List<WebElement> listOfProductImages;

    @FindBy(xpath = "//div[@class='esh-catalog-price']")
    private List<WebElement> listOfProductPrices;

    @FindBy(xpath = "//input[@class='esh-catalog-button']")
    private List<WebElement> listOfAddToCartButtons;

    @FindBy(xpath = "//a[@class='esh-identity-name esh-identity-name--upper']")
    private WebElement loginLinkElement;

    @FindBy(xpath = "//section[@class='esh-identity-drop']/a[3]/div")
    private WebElement logoutLink;

    @FindBy(xpath = "//form[@id='logoutForm']//section/div")
    private WebElement userID;

    @FindBy(xpath = "//section[@class='esh-identity-drop']/a[@class='esh-identity-item']")
    private List <WebElement> menuItems;

    @FindBy(xpath = "//div[contains('Log Out')]")
    private WebElement logOutButton;

    public boolean isLogoDisplayed() {
        log.info("Verify logo is displayed " + logo.isDisplayed());
        return logo.isDisplayed();
    }


    // Extracts number of products from "Showing 10 of 12 products - Page 1 - 2" text
    public int getNumberOfProductsFromText() {
        String regex = "Showing (\\d+) of (\\d+)"; // Regex to get the number of products
        String textNoOfProducts = getTextFromElement(numberOfProducts);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textNoOfProducts);

        if (matcher.find()) {
            int noOfProductsDisplayed = Integer.parseInt(matcher.group(1));
            log.info("Number of products displayed in the Text on HomePage: " + noOfProductsDisplayed);
            return noOfProductsDisplayed;
        } else {
            log.error("The string does not match the expected format.");
            throw new IllegalArgumentException("The string does not match the expected format.");
        }
    }

    // Get list of name products from the page
    public List getProductNames() {
        System.out.println("List of products: " + getStringList(listOfProducts));
        return getStringList(listOfProducts);
    }

    // get list of product prices from the page
    public List getProductPrices() {
        System.out.println("List of product prices: " + getStringList(listOfProductPrices));
        return getStringList(listOfProductPrices);
    }

    // get list of product images from the page
    public List getProductImages() {
        System.out.println("List of product images: " + getStringList(listOfProductImages));
        return getStringList(listOfProductImages);
    }

    // Logic to verify atleast one product is displayed
    public boolean verifyAtleastOneProductIsDisplayed() {
        int noOfProductsDisplayed = getNumberOfProductsFromText();
        int noOfProductsInList = getProductNames().size();

        if (noOfProductsDisplayed == noOfProductsInList && noOfProductsInList > 0 && noOfProductsInList <= 12) {
            log.info("Atleast one product is displayed and it matches the quantity displayed in text.");
            return true;
        } else {
            log.error("The number of products displayed does not match the number of products in the list.");
            return false; // No exception is thrown here, just a boolean result
        }
    }

    // logic for product details
    public boolean verifyProductDetails() {
        int noOfProductImages = getProductImages().size();
        int noOfProductsInList = getProductNames().size();
        int noOfProductsDisplayed = getNumberOfProductsFromText();
        int noOfProductPrices = getProductPrices().size();
        int noAddToCartButtons = getStringList(listOfAddToCartButtons).size();

        if (noOfProductsDisplayed == noOfProductsInList &&
                noOfProductsDisplayed == noOfProductPrices &&
                noOfProductsDisplayed == noOfProductImages &&
                noOfProductsDisplayed == noAddToCartButtons) {
            System.out.println("Product Image, Product Name, Product Prices, Add to cart button are displayed .");
            log.info("Product Image, Product Name, Product Prices, Add to cart button are displayed .");
            return true;
        } else {
            log.error("Some of product details are missing");
            return false;
        }
    }

    public boolean loginLinkIsDisplayed() {
        log.info("Login Link is displayed : " + loginLinkElement.toString());
        return loginLinkElement.isDisplayed();
    }
    public void clickOnLoginLink() {
        log.info("Clicking on Login Link : " + loginLinkElement.toString());
        clickOnElement(loginLinkElement);
    }

    public String getFitstProductName() {
        log.info("Getting first product name : " + listOfProducts.get(0).getText());
        String productName = listOfProducts.get(0).getText();
        return productName;
    }

    public void addFirstProductToCart() {
        boolean isBasketPageLoaded;
        log.info("Clicking on Add to Cart button for first product : " + getFitstProductName());
        clickOnElement(listOfAddToCartButtons.get(0));
    }

    public Boolean verifyUserIsOnGivenPage(String pageName) {
        log.info("verifying user is on " + pageName + " page");
        if (getCurrentUrl().contains(pageName)) {
            System.out.println(getCurrentUrl());
            System.out.println(pageName);
            log.info("User is on " + pageName + " page");
            return true;
        }
        else {
            log.error("User is not on " + pageName + " page");
            return false;
        }
    }
    public boolean verifyUserIsLoggedIn(String useremail) {
        log.info("Verify user is logged in successfully");
        if (userID.getText().equalsIgnoreCase(useremail)) {
            log.info("User is logged in successfully"+ userID.getText());
            return true;
        } else {
            log.error("User is is not displayed or user is not loggedin");
            return false;
        }
    }

    public void hoverOnDropDownMenu(){
        log.info("Hovering on drop down menu");
        mouseHoverToElement(userID);
    }

    public void getMenuItems(){
        log.info("Getting menu items");
        for (WebElement element : menuItems) {
            System.out.println(element);
        }
    }

    public void clickOnMenuItem(String menuItem){
        log.info("Clicking on menu item : " + menuItem);
        for (WebElement element : menuItems) {
            if (element.getText().equalsIgnoreCase(menuItem)) {
                clickOnElement(element);
                break;
            }
        }
    }


 }


