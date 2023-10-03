package tests;

import org.testng.annotations.Test;
import flows.ProductSelectionFlow;

public class AddToCartAndCheckOutE2ETest extends BaseConfig  {

	@Test(priority =1)
	public void filterAndCompareProductList() throws Exception {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.filterProducts();
		productFlow.validateFilteredProducts();
	}

	@Test(priority =2)
	public void AddProductsToCart() throws Exception {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		ProductSelectionFlow basketFlow = new ProductSelectionFlow(driver);

		String[] RequiredProducts = data.getData("RequiredProducts").split(",");
		for(int i=0;i<RequiredProducts.length;i++) {
			productFlow.addProductToCart(i); 
			basketFlow.validateAddedProductDetails(i);
			basketFlow.setQuantityForProduct(i);
			basketFlow.validateUpdatedProductDetails(i);
		}
	}

	@Test(priority =3)
	public void checkoutProducts() throws Exception {
		ProductSelectionFlow basketFlow = new ProductSelectionFlow(driver);
		ProductSelectionFlow checkOutFlow = new ProductSelectionFlow(driver);
		ProductSelectionFlow reviewFlow = new ProductSelectionFlow(driver);

		basketFlow.checkOutProducts();
		checkOutFlow.loginToApplicationFromCheckOut();
		reviewFlow.validateProductsInReview();
		reviewFlow.payNowAndValidatePurchase();
	}

	@Test(priority =4)
	public void validateOrderCreation() throws Exception {
		ProductSelectionFlow checkOutFlow = new ProductSelectionFlow(driver);

		checkOutFlow.openMyOrders();
		checkOutFlow.ValidateOrderCreation();
	}





}







