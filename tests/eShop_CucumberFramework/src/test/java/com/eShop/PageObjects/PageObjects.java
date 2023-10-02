package com.eShop.PageObjects;


import eShop_BDDCucumber.eShop_CucumberFramework.CommonFunctions;
import eShop_BDDCucumber.eShop_CucumberFramework.DriverInvoke;
import groovyjarjarantlr.collections.List;
import io.cucumber.datatable.DataTable;
import io.cucumber.guice.ScenarioScoped;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import com.google.inject.Inject;

import static org.assertj.core.api.Assertions.*;

@Log4j2
@ScenarioScoped
public class PageObjects extends CommonFunctions {

	By linkLogin = By.xpath("//a[contains(text(),'Login')]");
	By linkRegisterUser = By.linkText("Register as a new user");
	By txtEmail = By.id("Input_Email");
	By txtPassword = By.id("Input_Password");
	By txtConfirmPassword = By.id("Input_ConfirmPassword");
	By eleUser = By.xpath("//div[@class='esh-identity-name']");
	By selType = By.id("CatalogModel_TypesFilterApplied");
	By btnRegister = By.xpath("//button[text()='Register']");
	By btnLogIn = By.xpath("//button[text()='Log in']");
	By btnLogOut = By.xpath("//div[text()='Log Out']");
	By eleInvalidLogin = By.xpath("//li[contains(text(),'Invalid login attempt')]");
	By eleTypeResult = By.xpath("//div[contains(@class,'esh-catalog-item col')]");
	By eleProductCount = By.xpath("(//span[@class='esh-pager-item'])[1]");
	By btnSearch = By.className("esh-catalog-send");
	By linkCS = By.xpath("//a[contains(text(),'Continue Shopping')]");
	By eleCart = By.className("esh-basketstatus-badge");
	@Inject
    public PageObjects(DriverInvoke context){
        super.context = context;
    }
	String strRegEmail = generateRandomEmail(21);
    String strRegPwd = "ATester@123";
	
	public void clickOnLoginLink(){
        clickElement(linkLogin);
        context.getScenario().log("Clicked on linkLogin");
        log.debug("Clicked on linkLogin");
    }
	
	public void validatePageTitleToCheckPageIsNavigated(String titleText){
        assertThat(getTitle()).as("Page Title Contains").contains(titleText);
        log.debug("Validated page title as : " + titleText);
    }
	
	public void clickOnRegisterLink(){
        clickElement(linkRegisterUser);
        context.getScenario().log("Clicked on linkRegister");
        log.debug("Clicked on linkRegister");
    }
	
	public void EnterRegisterDetails(){
		setElement(txtEmail,strRegEmail);
		setElement(txtPassword,strRegPwd);
		setElement(txtConfirmPassword,strRegPwd);
		context.getScenario().log("Register Details");
        log.debug("Register Details EmailID" + strRegEmail + "Password" + strRegPwd);
    }
	
	public void clickOnRegister(){
		clickElement(btnRegister);
        context.getScenario().log("Clicked on Register");
        log.debug("Clicked on Register");
    }
	
	public void clickOnLogout(){
		HoverMouse(eleUser);
		clickElement(btnLogOut);
        context.getScenario().log("Clicked on LogOut");
        log.debug("Clicked on LogOut");
    }
	
	public void RegisteredUserLogin(){
		clickElement(linkLogin);
		setElement(txtEmail,strRegEmail);
		setElement(txtPassword,strRegPwd);
		clickElement(btnLogIn);
		context.getScenario().log("Register User Login");
        log.debug("Register Login EmailID" + strRegEmail + "Password" + strRegPwd);
    }
	
	public void UserLogin(String strEmail, String strPwd){
		clickElement(linkLogin);
		setElement(txtEmail,strEmail);
		setElement(txtPassword,strPwd);
		clickElement(btnLogIn);
		context.getScenario().log("User Details");
        log.debug("User Details EmailID" + strEmail + "Password" + strPwd);
    }
	
	public void LoginFailed(){
		boolean blnLogin = validateElementIsDisplayed(eleInvalidLogin);
		assertTrue("Verify the Invalid Login error", blnLogin);
		context.getScenario().log("Invalid Login");
        log.debug("Invalid login is displayed");
    }
	
	public void Search(String strText){
		selectElement(selType,strText);
		clickElement(btnSearch);
		context.getScenario().log("search products");
        log.debug("search product"+ strText);
		
    }
	
	public void SearchResult(String strText){
		int count = context.getDriver().findElements(eleTypeResult).size();
		for(int i=1; i<=count; i++) {
			By elePath = By.xpath("(//div[contains(@class,'esh-catalog-item col')]["+i+"])//div[@class='esh-catalog-name']/span");
			String strValue = getText(elePath);
			String strValueLC = strValue.toLowerCase();
			String strTextLC = strValue.toLowerCase();
			assertThat(strValueLC).contains(strTextLC);
		}
		context.getScenario().log("all search product is resulted");
        log.debug("all search product"+ strText + "is resulted");	
    }
	
	public void ProductsCount(){
		int count = context.getDriver().findElements(eleTypeResult).size();
		String strValue = getText(eleProductCount);
		assertThat(strValue).contains(count + " products");
		context.getScenario().log("all search product count is displayed");
        log.debug("all search product count is displayed");	
    }
	

	public void AddingProduct(DataTable table) {
		
		java.util.List<Map<String,String>> rows = table.asMaps(String.class, String.class);
	    
	    for (Map<String, String> columns : rows) {
	    	String strProduct = columns.get("Product");
	    	String strProductRS = strProduct.replaceAll("^\"|\"$", "");
	    	System.out.print(strProductRS);
	    	By btnAddBasket = By.xpath("//span[text()='"+strProductRS+"']/parent::div/parent::form/input[@class='esh-catalog-button']");
	    	clickElement(btnAddBasket);
	    	By txtCount = By.xpath("(//section[text()='"+strProductRS+"']/following::input[@class='esh-basket-input'])[1]");
	    	setElement(txtCount,columns.get("count"));
	    	clickElement(linkCS);
	        
	    }
	    
		context.getScenario().log("add all products to basket");
        log.debug("add all products to basket");
	}
	
	public void ProductsCountInCart() {
		String strValue = getText(eleCart);
		assertThat(strValue).contains("3");
		context.getScenario().log("all search product count is displayed");
        log.debug("all search product count is displayed");
	}
}
