package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import helper.UserInteractions;

public class EShopWebReviewPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final By payNowButtonBy = By.xpath("//input[@class='btn esh-basket-checkout']");
	private static final By thankYouLabelBy = By.xpath("//h1[text()='Thanks for your Order!']");
	


	public EShopWebReviewPage(WebDriver driver) {
		super(driver);
	}

	public void validateNameinCart(String product) throws Exception {
		By by =By.xpath(productNameLabel.replace("%s%",product));
		String actualNAme = getElement(by, "Product Name Label").getText();
		CompareString(actualNAme, product, "Product Name label in cart");
	}

	
	public void clickPayNowButton() throws InterruptedException {
		
//		Thread.sleep(100);
		WebElement element = driver.findElement(payNowButtonBy);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element); 
			js.executeScript("arguments[0].click();", element);
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
	}

	public void validateThankyouMessage() throws Exception {
		
		String actualHeading = getElement(thankYouLabelBy, "Product Name Label").getText();
		CompareString(actualHeading, "Thanks for your Order!", "Thankyou label");
	}

	
		
	
	
}
