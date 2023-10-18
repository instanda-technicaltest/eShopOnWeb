package tests;


import org.testng.annotations.Test;

import dataTypes.CatalogItem;
import flows.ProductSelectionFlow;
import flows.RestAssuredFlows;

public class ProductDetailsValidationIntegerationTesting extends BaseConfig  {
	public static CatalogItem item =null;
	

	@Test
	public void fetchProductDetailsUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-items/1");
		apiFLows.sendRequest("Get");
		item = apiFLows.validateCatalogItemsInResponse();
		System.out.println(item.getCatalogBrandId());
		System.out.println(item.getCatalogTypeId());
		System.out.println(item.getName());
	}
	
	@Test (dependsOnMethods="fetchProductDetailsUsingAPI")
	public void getCatalogBrandUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-brands");
		apiFLows.sendRequest("Get");
		String brandName = apiFLows.getBrandName(item.getCatalogBrandId()-1);
		data.setValue("BrandOption",brandName);
	}
	
	@Test (dependsOnMethods="getCatalogBrandUsingAPI")
	public void getCatalogTypeUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-types");
		apiFLows.sendRequest("Get");
		String typeName = apiFLows.getTypeName(item.getCatalogTypeId()-1);
		data.setValue("TypeOption",typeName);
	}
	
	@Test (dependsOnMethods="getCatalogTypeUsingAPI")
	public void filterAndCompareProductList() {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.filterProducts();
		productFlow.validateItemInFilteredProducts(item);

	}

}







