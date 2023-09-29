package flows;


import org.openqa.selenium.WebDriver;
import helper.Data;
import pages.EShopWeb;
import pages.EShopWebBasketPage;
import pages.EShopWebCheckOutPage;
import pages.EShopWebReviewPage;


public class ProductSelectionFlow    {
	WebDriver driver ;

	public ProductSelectionFlow(WebDriver driver) {
		this.driver = driver;

	}
	static int basketCount=0;
	static double totalCartPrice = 0.00;
	Data data = new Data();
	
	String[] RequiredProducts = data.getData("RequiredProducts").split(",");
	String[] RequiredQuantities = data.getData("RequiredQuantities").split(",");
	String [] RequiredPrices = data.getData("RequiredProductPrices").split(",");


	public void filterProducts() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.filterBrand(data.getData("BrandOption"));
		page.filterType(data.getData("TypeOption"));
		page.clickFilterButton();
	}

	public void validateFilteredProducts() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.validateCountInLabel(data.getData("FilteredCount"));
		page.validateNameOfFilteredProducts(data.getData("FilteredProducts"));
		page.validatePriceOfFilteredProduct(data.getData("FilteredProducts"),data.getData("FilteredProductPricess"));
	}


	public void addProductToCart(int i) throws Exception {
		EShopWeb productPage = new EShopWeb(driver);
		String[] RequiredProducts = data.getData("RequiredProducts").split(",");
		productPage.clickAddToBasket(RequiredProducts[i]);
		basketCount = basketCount+1;
		productPage.validateCountInBasket(basketCount);
	}
	public void validateAddedProductDetails(int i) throws Exception {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		basketPage.validateNameinCart(RequiredProducts[i]);
		basketPage.validatePriceinCart(RequiredProducts[i],RequiredPrices[i]);
		basketPage.validateQuantityinCart(RequiredProducts[i],1);
		double price = Float.parseFloat(RequiredPrices[i]);
		basketPage.validateTotalPriceOfProductinCart(RequiredProducts[i],price*1);
		basketPage.validateTotalCartPrice(totalCartPrice+price);

	}

	public void setQuantityForProduct(int i) throws Exception {
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

	public void validateUpdatedProductDetails(int i) throws Exception {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		double price = Float.parseFloat(RequiredPrices[i]);
		int quantity = Integer.parseInt(RequiredQuantities[i]);
		basketPage.validateTotalPriceOfProductinCart(RequiredProducts[i],price*quantity);
		basketPage.validateTotalCartPrice(totalCartPrice);
		basketPage.clickOnContinueShoping(i);
	}

	public void checkOutProducts() throws Exception {
		EShopWeb productPage = new EShopWeb(driver);
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		productPage.clickBasketIcon();
		basketPage.clickCheckOutButton();
	}

	public void loginToApplication() throws Exception {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.inputEmailID(data.getData("loginEmail"));
		checkOutPage.inputPassword(data.getData("loginPassword"));
		checkOutPage.clickLoginButton();
	}

	public void validateProductsInReview() throws Exception {
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

	public void payNowAndValidatePurchase() throws Exception {
		EShopWebBasketPage basketPage = new EShopWebBasketPage(driver);
		EShopWebReviewPage reviewPage = new EShopWebReviewPage(driver);

		basketPage.validateTotalCartPrice(totalCartPrice);
		reviewPage.clickPayNowButton();
		reviewPage.validateThankyouMessage();

	}																		
}







