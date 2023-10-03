package steps;


import helper.Data;
import helper.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EShopWeb;
import pages.EShopWebCheckOutPage;
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
	
	@And ("user navigate to Login page")
	public void navigateToLoginPage() throws Exception {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.clickLoginIcon();
	}
	
	 @Then ("user should get email not present error messages")
	 public void emailNotPresentErrorMessages() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.validateEmailErrorFormLabel();
			checkOutPage.validateEmailErrorFieldLabel();
	 }
	 
	 @Then ("user should get password not present error messages")
	 public void passwordNotPresentErrorMessages() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.validatePasswordErrorFormLabel();
			checkOutPage.validatePasswordErrorFieldLabel();
	 }
	
	 @When ("user click on login button")
	 public void clickLoginButtons() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.clickLoginButton();
	 }
	 	
	 @When ("User provide password")
	 public void inputPassword() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.inputPassword(data.getData("loginPassword"));
	 }
	 
	 @When ("User provide email")
	 public void inputEmail() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.inputEmailID(data.getData("loginEmail"));
	 }
	 @When ("User provide invalid email")
	 public void inputInvalidEmail() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.inputEmailID("DemoUser@gmail.com");
	 }
	 
	 @Then ("user should get invalid login attempt error messages")
	 public void invalidLoginErrorMessages() throws Exception {
		EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
		checkOutPage.validateInvalidLoginErrorFormLabel();		
}
	 
	 @And ("User provide invalid password")
	 public void inputInvalidPassword() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.inputPassword("errorpwd");
	 }
	 
	 @Then ("User should be logged in")
	 public void validateUserLoggedIn() throws Exception {
		 EShopWebCheckOutPage checkOutPage = new EShopWebCheckOutPage(driver);
			checkOutPage.validateLogin(data.getData("loginEmail"));
	 }
} 

