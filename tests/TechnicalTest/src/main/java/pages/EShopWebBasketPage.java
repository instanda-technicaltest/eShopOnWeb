package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helper.UserInteractions;

public class EShopWebBasketPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final String productPriceLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][1]";
	private static final String productQuantityLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][2]/input[2]";
	private static final String productTotalPriceLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][3]";
	private static final By totalCartPriceBy = By.xpath("//article[@class='esh-basket-items row']/section[contains(@class,'mark col-xs-2')]");
	private static final By updateButtonBy = By.xpath("//button[@name='updatebutton']");
	private static final By conitnueShopppingButton = By.xpath("//a[@class='btn esh-basket-checkout text-white']");
	private static final By checkOutButtonBy = By.xpath("//a[@class='btn esh-basket-checkout']");
	private static final String productQuantityField = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][2]";


	public EShopWebBasketPage(WebDriver driver) {
		super(driver);
	}

	public void validateNameinCart(String product) throws Exception {
		By by =By.xpath(productNameLabel.replace("%s%",product));
		String actualNAme = getElement(by, "Product Name Label").getText();
		CompareString(actualNAme, product, "Product Name label in cart");
	}

	public void validatePriceinCart(String Product, String price) throws Exception {
		
		By by =By.xpath(productPriceLabel.replace("%s%",Product));
		String actualPrice = getElement(by, "Product Price Label").getText().replace("$ ", "");
		CompareString(actualPrice, price, "Product Price label in cart");				
	}

	public void validateQuantityinCart(String Product, int quantity) throws Exception {
		
		By by =By.xpath(productQuantityLabel.replace("%s%",Product));
		String actualQuantity = getElement(by, "Product Quantity Label").getAttribute("value");
		CompareString(actualQuantity, quantity+"", "Product Quantity label in cart");
	}

	public void validateTotalPriceOfProductinCart(String Product, double expectedPrice) throws Exception {
		By by =By.xpath(productTotalPriceLabel.replace("%s%",Product));
		String actualTotalPrice = getElement(by, "Product Total Price Label").getText();
		CompareString(actualTotalPrice, expectedPrice+"", "Product Totla Price label in cart");	
	}

	public void validateTotalCartPrice(double totalCartPrice) throws Exception {
		String actualTotalPrice = getElement(totalCartPriceBy, "Total Cart Price Label").getText();
		CompareString(actualTotalPrice, totalCartPrice+"", "Product Total Price label in cart");	
	}

	public void setQuantitiesOfProduct(String product, String quantity, int count) throws Exception {
		By by =By.xpath(productQuantityField.replace("%s%",product));
		jsInputKeys(quantity,by,"Required Quantity of Product",10);
	}

	public void clickUpdateButton(int count) throws Exception {
		jsClick(updateButtonBy,"Update button",10);
	}

	public void clickOnContinueShoping(int count) throws Exception {
		jsClick(conitnueShopppingButton,"Continue shopping button",10);
	
	}
	public void clickCheckOutButton() throws InterruptedException, IOException {
		jsClick(checkOutButtonBy,"Checkout button",10);
	}
		
	
	
}
