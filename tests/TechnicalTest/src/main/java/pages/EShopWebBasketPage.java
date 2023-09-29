package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import helper.UserInteractions;

public class EShopWebBasketPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final String productPriceLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][1]";
	private static final String productQuantityLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][2]";
	private static final String productTotalPriceLabel = "//section[contains(@class,'col-xs-3')][text()='%s%']//parent::div/section[contains(@class,'col-xs-2')][3]";
	private static final By totalCartPriceBy = By.xpath("//article[@class='esh-basket-items row']/section[contains(@class,'mark col-xs-2')]");
	private static final By updateButtonBy = By.xpath("//button[@name='updatebutton']");
	private static final By conitnueShopppingButton = By.xpath("//a[@class='btn esh-basket-checkout text-white']");
	private static final By checkOutButtonBy = By.xpath("//a[@class='btn esh-basket-checkout']");


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
		String actualPrice = getElement(by, "Product Price Label").getText();
		CompareString(actualPrice, price, "Product Name label in cart");				
	}

	public void validateQuantityinCart(String Product, int quantity) throws Exception {
		
		By by =By.xpath(productQuantityLabel.replace("%s%",Product));
		String actualQuantity = getElement(by, "Product Quantity Label").getText();
		CompareString(actualQuantity, quantity+"", "Product Name label in cart");
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
		By by =By.xpath(productQuantityLabel.replace("%s%",product));
		WebElement element = driver.findElement(by);
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		}catch(Exception e) {
			System.out.println(e.getClass());
		}
		Thread.sleep(100);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(quantity);

	}

	public void clickUpdateButton(int count) throws Exception {
		
		WebElement element = driver.findElement(updateButtonBy);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element); 
			js.executeScript("arguments[0].click();", element);
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
	}

	public void clickOnContinueShoping(int count) throws Exception {
		// TODO Auto-generated method stub
		
		WebElement element = driver.findElement(conitnueShopppingButton);
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		js.executeScript("arguments[0].style.border='2px solid red'", element); 
		js.executeScript("arguments[0].click();", element);
		}catch(Exception e) {
			System.out.println(e.getClass());
		}
	
	}
	public void clickCheckOutButton() throws InterruptedException {
//		Thread.sleep(100);
		WebElement element = driver.findElement(checkOutButtonBy);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element); 
			js.executeScript("arguments[0].click();", element);
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
	}
		
	
	
}
