package flows;


import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataTypes.CatalogItem;
import helper.Data;
import pages.EShopWeb;
import pages.EShopWebBasketPage;
import pages.EShopWebCheckOutPage;
import pages.EShopWebReviewPage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;


public class ProductSelectionFlow    {
	WebDriver driver ;
	private static int basketCount=0;
	private static double totalCartPrice = 0.00;
	private String checkOutTime = "";
	Data data = new Data();
	
	
	public ProductSelectionFlow(WebDriver driver) {
		this.driver = driver;
	
	}

	
	public ProductSelectionFlow() {
		// TODO Auto-generated constructor stub
	}


	String[] RequiredProducts = data.getData("RequiredProducts").split(",");
	String[] RequiredQuantities = data.getData("RequiredQuantities").split(",");
	String [] RequiredPrices = data.getData("RequiredProductPrices").split(",");

	public static void clearStaticValues() {
		basketCount=0;
		totalCartPrice = 0.00;
	}

	public void filterProducts()  {
		EShopWeb page = new EShopWeb(driver);
		page.filterBrand(data.getData("BrandOption"));
		page.filterType(data.getData("TypeOption"));
		page.clickFilterButton();
	}

	public void validateFilteredProducts()  {
		EShopWeb page = new EShopWeb(driver);
		page.validateCountInLabel(data.getData("FilteredCount"));
		page.validateNameOfFilteredProducts(data.getData("FilteredProducts"));
//		page.validateNameOfFilteredProducts(data.getData("FilteredProducts"));
		page.validatePriceOfFilteredProduct(data.getData("FilteredProducts"),data.getData("FilteredProductPricess"));
	}

	public void validateItemInFilteredProducts(CatalogItem item)  {
		EShopWeb page = new EShopWeb(driver);
		page.validateProductInFilteredProducts(item.getName());
		page.validateImageOfFilteredProduct(item.getName(),item.getPictureUri());
		page.validatePriceOfProduct(item.getName(),item.getPrice());
	}

	public void addProductToCart(int i)  {
		EShopWeb productPage = new EShopWeb(driver);
		String[] RequiredProducts = data.getData("RequiredProducts").split(",");
		productPage.clickAddToBasket(RequiredProducts[i]);
		basketCount = basketCount+1;
		productPage.validateCountInBasket(basketCount);
	}
	
	public void validateAddedProductDetails(int i)  {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		basketPage.validateNameinCart(RequiredProducts[i]);
		basketPage.validatePriceinCart(RequiredProducts[i],RequiredPrices[i]);
		basketPage.validateQuantityinCart(RequiredProducts[i],1);
		double price = Float.parseFloat(RequiredPrices[i]);
		basketPage.validateTotalPriceOfProductinCart(RequiredProducts[i],price*1);
		basketPage.validateTotalCartPrice(totalCartPrice+price);

	}

	public void setQuantityForProduct(int i)  {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		EShopWeb productPage = new EShopWeb(driver);
		double price = Float.parseFloat(RequiredPrices[i]);
		
		basketPage.setQuantitiesOfProduct(RequiredProducts[i],RequiredQuantities[i],i);
		basketPage.clickUpdateButton(i);
		int quantity = Integer.parseInt(RequiredQuantities[i]);
		basketCount = basketCount+quantity-1;
		productPage.validateCountInBasket(basketCount);
		totalCartPrice= totalCartPrice + price*quantity;

	}

	public void validateUpdatedProductDetails(int i)  {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		double price = Float.parseFloat(RequiredPrices[i]);
		int quantity = Integer.parseInt(RequiredQuantities[i]);
		basketPage.validateTotalPriceOfProductinCart(RequiredProducts[i],price*quantity);
		basketPage.validateTotalCartPrice(totalCartPrice);
		basketPage.clickOnContinueShoping(i);
	}

	public void checkOutProducts()  {
		EShopWeb productPage = new EShopWeb(driver);
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		productPage.clickBasketIcon();
		basketPage.clickCheckOutButton();
	}

	public void loginToApplicationFromCheckOut()  {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.inputEmailID(data.getData("loginEmail"));
		checkOutPage.inputPassword(data.getData("loginPassword"));
		checkOutPage.clickLoginButton();
	}
	
	public void loginToApplication()  {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.clickLoginIcon();
		checkOutPage.inputEmailID(data.getData("loginEmail"));
		checkOutPage.inputPassword(data.getData("loginPassword"));
		checkOutPage.clickLoginButton();
	}
	
	public void openMyOrders()  {
	
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.hoverOverUserIcon();
	}
	
	public void openAdminPage() {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.hoverOverUserIconAndSelectAdmin();
		checkOutPage.checkCreateButton();
	}
	
	public void logout() {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.adminLogout();
		try {
		checkOutPage.logout();
		}catch(Exception e) {
			System.out.println("Logout unsucessfull");
		}
	}
	
	public void compareItemInAdminList(CatalogItem item) {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		try {
		List<WebElement> elements = checkOutPage.valiateNewItemDetails(item.getName());
		checkOutPage.CompareString(elements.get(4).getText(), item.getName(), "Item name");	
		}catch(NullPointerException e) {
			driver.navigate().refresh();
			try {
			List<WebElement> elements = checkOutPage.valiateNewItemDetails(item.getName());
			checkOutPage.CompareString(elements.get(4).getText(), item.getName(), "Item name");	
			}catch(NullPointerException e1)
			{checkOutPage.failTestInReportAndContinue(item.getName()+" should be listed in Admin Page",item.getName()+" was not listed in Admin Page",checkOutPage.takeSnapShot());
			}
		}
	}
	
	public void ValidateOrderCreation()  {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);

		int count = checkOutPage.getOrderCount();
		String[] details =checkOutPage.getOrderDetails(count);
		String time = checkOutTime;
		String[] expectedDetails = getExpectedDetails(count, time);
		checkOutPage.validateOrderDetails(details,expectedDetails);
	}
	

	private String getCurrentTime() {

        LocalDateTime myObj = LocalDateTime.now();
        System.out.println(myObj.toString());
        String time = myObj.toString();
        try {
        time = time.replace(".", "_");
        String[] dates = time.split("_");
        	String	date = dates[0];
        			date= date.replace("T", " ");
        			date = date.replace("-", "/");
        			String timePart[]=date.split(" ");
        			String order[] = timePart[0].split("/");
        			int month=Integer.parseInt(order[1]);
        			try {
        				System.out.println(order[2]);
        			int day = Integer.parseInt(order[2]);
        			
        			
        			date = month+"/"+day+"/"+order[0]+" "+timePart[3];
}catch(Exception e) {
        				
        			}
//        String strDate = formatter.format(myObj);
        return date;
        }catch(Exception e) {
        	
        }
        return "";
	}

	private String[] getExpectedDetails(int count, String time) {
		String[] expectedDetails=new String[4];
		expectedDetails[0]= count+"";
		expectedDetails[1]= time+"";
		NumberFormat formatter = new DecimalFormat("#0.00");
		String expected = (formatter.format(totalCartPrice));
		expectedDetails[2]= expected;
		expectedDetails[3]= "Pending";
		
		return expectedDetails;
	}

	public void validateProductsInReview()  {
		EShopWeb productPage = new EShopWeb(driver);
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);

		for(int i=0;i<RequiredProducts.length;i++) {  	 	 
			productPage.validateCountInBasket(basketCount);
			basketPage.validateNameinCart(RequiredProducts[i]);
			basketPage.validatePriceinCart(RequiredProducts[i],RequiredPrices[i]);
			int quantity = Integer.parseInt(RequiredQuantities[i]);
			double price = Double.parseDouble(RequiredPrices[i]);
			basketPage.validateQuantityinCart(RequiredProducts[i],quantity);
			basketPage.validateTotalPriceOfProductinCart(RequiredProducts[i],price*quantity);
		}
	}

	public void payNowAndValidatePurchase()  {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		EShopWebReviewPage reviewPage = new EShopWebReviewPage(driver);

		basketPage.validateTotalCartPrice(totalCartPrice);
		reviewPage.clickPayNowButton();
		setCheckOutTime();
		reviewPage.validateThankyouMessage();

	}
	
	public void setCheckOutTime() {
		checkOutTime = getCurrentTime();
	}




																			
}







