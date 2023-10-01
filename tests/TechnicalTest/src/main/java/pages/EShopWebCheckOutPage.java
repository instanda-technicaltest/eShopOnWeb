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

	
	
	
}
