package tests;


import org.testng.annotations.Test;
import flows.ProductSelectionFlow;

public class AddToCartAndCheckOutE2ETest extends BaseConfig  {
	


	@Test
	public void filterAndCompareProductList() {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.filterProducts();
		productFlow.validateFilteredProducts();

	}

	@Test(dependsOnMethods="filterAndCompareProductList")
	public void AddProductsToCart()  {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		String[] RequiredProducts = data.getData("RequiredProducts").split(",");
		for(int i=0;i<RequiredProducts.length;i++) {
			productFlow.addProductToCart(i); 
			productFlow.validateAddedProductDetails(i);
			productFlow.setQuantityForProduct(i);
			productFlow.validateUpdatedProductDetails(i);
		}
	}

	@Test(dependsOnMethods="AddProductsToCart")
	public void checkoutProducts()  {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.checkOutProducts();
		productFlow.loginToApplicationFromCheckOut();
		productFlow.validateProductsInReview();
		productFlow.payNowAndValidatePurchase();
	}

	@Test(dependsOnMethods="checkoutProducts")
	public void validateOrderCreation()  {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.openMyOrders();
		productFlow.ValidateOrderCreation();
	}





}







