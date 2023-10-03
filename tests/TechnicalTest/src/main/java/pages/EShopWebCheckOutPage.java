package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helper.UserInteractions;

public class EShopWebCheckOutPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final By checkOutButtonBy = By.xpath("//a[@class='btn esh-basket-checkout']");
	private static final By mailIDFieldBy = By.xpath("//input[@type='email']");
	private static final By passwordFieldBy = By.xpath("//input[@type='password']");
	private static final By loginButtonBy = By.xpath("//button[@type='submit']");
	private static final By userIconBy = By.xpath("//form[@id='logoutForm']");
	private static final By myOrdersLinkBy = By.xpath("//a[@class='esh-identity-item']/div[contains(text(),'My orders')]");
	private static final By orderRowsBy = By.xpath("//article[@class='esh-orders-items row']");
	private static final By emailFormError = By.xpath("//div[@class='text-danger validation-summary-errors']//*[contains(text(),'Email')]");
	private static final By passwordFormError = By.xpath("//div[@class='text-danger validation-summary-errors']//*[contains(text(),'Password')]");
	private static final By invalidLoginFormError = By.xpath("//div[@class='text-danger validation-summary-errors']//*[contains(text(),'Invalid')]");
	private static final By emailFieldError = By.xpath("//span[@id='Input_Email-error']");
	private static final By passwordFieldError = By.xpath("//span[@id='Input_Password-error']");
	private static final By loggedInUserBy = By.xpath("//div[@class='esh-identity-name']");
	private static final String orderDetails = ("//article[@class='esh-orders-items row'][%s%]/section");
		

	public EShopWebCheckOutPage(WebDriver driver) {
		super(driver);
	}

	public void validateNameinCart(String product) throws Exception {
		By by =By.xpath(productNameLabel.replace("%s%",product));
		String actualNAme = getElement(by, "Product Name Label").getText();
		CompareString(actualNAme, product, "Product Name label in cart");
	}

	
	public void clickCheckOutButton() throws InterruptedException, IOException {
		jsClick(checkOutButtonBy,"Pay Now button",10);
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
	

//	private static final By orderRowsBy = By.xpath("//article[@class='esh-orders-items row']");
//	private static final By orderDetailsBy = By.xpath("//article[@class='esh-orders-items row'][11]/section[1]");
	
	public void hoverOverUserIcon() throws Exception {
		WebElement element = getElement(userIconBy, "User Icon", 10);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		click(myOrdersLinkBy,"My Orders link",10);
	}
	
	public int getOrderCount() throws IOException {
		List<WebElement> elements = getElements(orderRowsBy);
		return elements.size();	
	}
	
	public String[] getOrderDetails(int count) throws IOException {
		By by = By.xpath(orderDetails.replace("%s%", count+""));
		List<WebElement> elements = getElements(by);
		String[] details = new String[4];
		for (int i=0;i<4;i++) {
			details[i]=elements.get(i).getText();
		}
		return details;
		// TODO Auto-generated method stub
		
	}

	public void clickLoginIcon() throws Exception {
		By loginIconBy = By.xpath("//a[contains(text(),'Login')]");
		click(loginIconBy,"Login Icon",10);
		
		// TODO Auto-generated method stub
		
	}

	public void validateOrderDetails(String[] details, String[] expectedDetails) throws Exception {
		// TODO Auto-generated method stub
		CompareString(details[0], expectedDetails[0], "Order Number in Order Details");
//		CompareString(details[1].split(" PM ")[0], expectedDetails[1].replace("-", "/"), "Order Time in Order Details");
		CompareString(details[2], expectedDetails[2], "Order Amount in Order Details");
		CompareString(details[3].replace("$ ",""), expectedDetails[3], "Order Status in Order Details");
		
	}


	public void validateEmailErrorFormLabel() throws Exception {
		String actualText = getElement(emailFormError, "Email form error",10).getText();
		CompareString(actualText, "The Email field is required.","Email form error");
	}

	public void validateEmailErrorFieldLabel() throws Exception {
		String actualText = getElement(emailFieldError, "Email feild error",10).getText();
		CompareString(actualText, "The Email field is required.","Email feild error");
	}
	
	public void validatePasswordErrorFormLabel() throws Exception {
		String actualText = getElement(passwordFormError, "Password form error",10).getText();
		CompareString(actualText, "The Password field is required.","Password form error");
	}

	public void validatePasswordErrorFieldLabel() throws Exception {
		String actualText = getElement(passwordFieldError, "Password feild error",10).getText();
		CompareString(actualText, "The Password field is required.","Email feild error");
	}

	public void validateInvalidLoginErrorFormLabel() throws Exception {
		String actualText = getElement(invalidLoginFormError, "Invalid Login form error",10).getText();
		CompareString(actualText, "Invalid login attempt.","Invalid Login form error");
	}
	
	public void validateLogin(String userName) throws Exception {
		String actualText = getElement(loggedInUserBy, "Login User Icon",10).getText();
		CompareString(actualText, userName,"User Logged in");
		
	}
	
	
	
	
}
