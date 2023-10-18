package tests;

import org.testng.annotations.Test;
import dataTypes.CatalogItem;
import flows.ProductSelectionFlow;
import flows.RestAssuredFlows;
import helper.Reporter;

public class AddUpdateAndDeleteItemsAsAdmin extends BaseConfig  {
	public static CatalogItem item =null;
	public String bearerToken ="";
	

	@Test
	public void AuthorizeAdminLogin() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/authenticate", data.getData("AuthBody"));
		apiFLows.sendRequest("Post");
		apiFLows.assertStatusCode(200);
		bearerToken = apiFLows.getBearerToken();
		System.out.println(bearerToken);
	}
	
	@Test (dependsOnMethods="AuthorizeAdminLogin")
	public void createNewItemInAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		String itemName = Reporter.currentDate;
		String reqBody=(data.getData("AddItemBody").replace("%s%", itemName));
		apiFLows.buildAPIRequest("/catalog-items",reqBody, bearerToken);
		apiFLows.sendRequest("Post", bearerToken);
		apiFLows.assertStatusCode(201);
		item = apiFLows.validateCatalogItemsInResponse();
	}
	
	
	@Test  (dependsOnMethods="createNewItemInAPI")
	public void validateNewItemAsAdmin() {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.loginToApplication();
		productFlow.openAdminPage();
		productFlow.compareItemInAdminList(item);
		productFlow.logout();
	}
	
	@Test (dependsOnMethods="validateNewItemAsAdmin")
	public void updateItemInAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		String itemName = Reporter.currentDate;
		System.out.println("Item Id "+item.getId());
		String reqBody=(data.getData("UpdateItemBody").replace("%s%", itemName).replace("%id%", (item.getId())+""));
		apiFLows.buildAPIRequest("/catalog-items",reqBody, bearerToken);
		apiFLows.sendRequest("Put", bearerToken);
		apiFLows.assertStatusCode(200);
		item = apiFLows.validateCatalogItemsInResponse();
		int itemID = item.getId();
	}
	
	@Test (dependsOnMethods="updateItemInAPI")
	public void fetchProductDetailsUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-items/"+item.getId());
		apiFLows.sendRequest("Get");
		apiFLows.assertStatusCode(200);
		item = apiFLows.validateCatalogItemsInResponse();

	}
	
	@Test  (dependsOnMethods="fetchProductDetailsUsingAPI")
	public void validateUpdatedItemAsAdmin() {
		ProductSelectionFlow productFlow = new ProductSelectionFlow(driver);
		productFlow.loginToApplication();
		productFlow.openAdminPage();
		productFlow.compareItemInAdminList(item);
		productFlow.logout();
	}
	
	@Test (dependsOnMethods="validateUpdatedItemAsAdmin")
	public void deleteItemUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-items/"+item.getId());
		apiFLows.sendRequest("Delete", bearerToken);
		apiFLows.assertStatusCode(200);
		apiFLows.validateDeletedStatus();
	}
	
	@Test (dependsOnMethods="deleteItemUsingAPI")
	public void ValidateDeletionUsingAPI() {
		RestAssuredFlows apiFLows = new RestAssuredFlows();
		apiFLows.buildAPIRequest("/catalog-items/"+item.getId());
		apiFLows.sendRequest("Get");
		apiFLows.assertStatusCode(404);
	}
}