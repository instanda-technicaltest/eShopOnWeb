package steps;


import helper.Data;
import helper.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.EShopWeb;
import io.cucumber.java.en.And;


public class eShopOnWebStepDefenitions extends Driver {

	Hooks ano = new Hooks();
	Driver driverHelp = new Driver();
	Data data = new Data();
	
	@Given ("User navigates to site eShopOnWeb")
	public void navigateToSite() throws Exception {
		driver = driverHelp.openWebPage("https://localhost:5001/");
	}
	
	@And ("user fetch testData for {string} from {string}")
	public void setUpData(String scenarioName, String sheet) {
		ano.setData(sheet,scenarioName);	
	}
	
	@And ("user fetch testData for {string}")
	public void setUpData(String scenarioName) {
		ano.setData(scenarioName);	
	}

	@And ("user filters product page")
	public void filterProductPage() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.filterBrand(data.getData("BrandOption"));
		page.filterType(data.getData("TypeOption"));
		page.clickFilterButton();
	}
	
	@Then ("user should Validate count of products displayed in label")
	public void validateProductCountInLabels() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.validateCountInLabel(data.getData("FilteredCount"));
	}
	
	@Then ("user should Validate name of the product displayed")
	public void validateFilteredProductNames() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.validateNameOfFilteredProducts(data.getData("FilteredProducts"));
	}
	@Then ("user should Validate price of the product displayed")
	public void validateFilteredProductsPrices() throws Exception {
		EShopWeb page = new EShopWeb(driver);
		page.validatePriceOfFilteredProduct(data.getData("FilteredProducts"),data.getData("FilteredProductPricess"));
	}
	
	@And ("user should close browser")
	public void closeBrowser() {
		driver.close();
	}
	

} 

