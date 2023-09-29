package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import helper.UserInteractions;

public class EShopWebCheckOutPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final By checkOutButtonBy = By.xpath("//a[@class='btn esh-basket-checkout']");
	private static final By mailIDFieldBy = By.xpath("//input[@type='email']");
	private static final By passwordFieldBy = By.xpath("//input[@type='password']");
	private static final By loginButtonBy = By.xpath("//button[@type='submit']");


	public EShopWebCheckOutPage(WebDriver driver) {
		super(driver);
	}

	public void validateNameinCart(String product) throws Exception {
		By by =By.xpath(productNameLabel.replace("%s%",product));
		String actualNAme = getElement(by, "Product Name Label").getText();
		CompareString(actualNAme, product, "Product Name label in cart");
	}

	
	public void clickCheckOutButton() throws InterruptedException {
		
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

	public void inputEmailID(String mailID) throws Exception {
		inputKeys(mailID, mailIDFieldBy, "Mail ID field", 10);	
	}

	public void inputPassword(String password) throws Exception {
		inputKeys(password, passwordFieldBy, "Password field", 10);
	}

	public void clickLoginButton() throws Exception {
		click(loginButtonBy," Login Button",10);
		
	}
		
	
	
}
