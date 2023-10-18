package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helper.UserInteractions;
public class EShopWebReviewPage extends UserInteractions {

	private static final String productNameLabel = "//article[@class='esh-basket-items row']//section[contains(@class,'col-xs-3')][text()='%s%']";
	private static final By payNowButtonBy = By.xpath("//input[@class='btn esh-basket-checkout']");
	private static final By thankYouLabelBy = By.xpath("//h1[text()='Thanks for your Order!']");
	


	public EShopWebReviewPage(WebDriver driver) {
		super(driver);
	}

	public void validateNameinCart(String product)  {
		By by =By.xpath(productNameLabel.replace("%s%",product));
		String actualNAme = getElement(by, "Product Name Label").getText();
		CompareString(actualNAme, product, "Product Name label in cart");
	}

	
	public void clickPayNowButton()  {
		jsClick(payNowButtonBy,"Pay Now button",10);
	}

	public void validateThankyouMessage()  {		
		String actualHeading = getElement(thankYouLabelBy, "Product Name Label").getText();
		CompareString(actualHeading, "Thanks for your Order!", "Thankyou label");
	}

	

	
		
	
	
}
