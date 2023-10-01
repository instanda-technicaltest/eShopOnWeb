package pages;


import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import helper.UserInteractions;

public class EShopWeb extends UserInteractions{

	private static final By FilterdProductLabelsBy = By.xpath("//div[@class='esh-catalog-name']");
	private static final By brandFilterBy = By.id("CatalogModel_BrandFilterApplied");
	private static final By typeFilterBy = By.id("CatalogModel_TypesFilterApplied");
	private static final By filterButtonBy = By.xpath("//input[@class='esh-catalog-send']");
	private static final By filteredCountLabelBy = By.xpath("//span[@class='esh-pager-item']");
	private static final By basketCountBy = By.xpath("//div[@class='esh-basketstatus-badge']");

	private static final String brandFilterOptionString = "//select[@id='CatalogModel_BrandFilterApplied']/option[contains(text(),'%s%')]";
	private static final String TypeFilterOptionString = "//select[@id='CatalogModel_TypesFilterApplied']/option[text()='%s%']";
	private static final String addToBasketByString = "//div[@class='esh-catalog-name']/span[text()='%s%']//parent::div//parent::form/input";
	private static final String FilterdProductPriceLabelsBy = "//div[@class='esh-catalog-name']/span[text()='%s%']//parent::div//following-sibling::div/span";
	
	

	public EShopWeb(WebDriver driver) {
		super(driver);
	}

	public void filterBrand(String option) throws Exception {
		click(brandFilterBy, "Brand Filter");
		By by = By.xpath(brandFilterOptionString.replace("%s%", option));
		click(by, "For brand Filter, "+option+" option", 10);	
	}

	public void filterType(String option) throws Exception {
		click(typeFilterBy,"Type Filter");
		By by = By.xpath(TypeFilterOptionString.replace("%s%", option));
		click(by, "For type Filter, "+option+" option", 10);		
	}

	public void clickFilterButton() throws Exception {
		click(filterButtonBy, "Filter Button", 10);
	}

	public void validateCountInLabel(String filterCount) throws Exception {
		String label = getElement(filteredCountLabelBy,"Filtered Count Label").getText();
		String[] labels = label.split(" ");
		CompareString(labels[1],filterCount,"Filtered count");
	}



	public void compareResultAgeLabelText(String expectedAge) throws Exception {
		String actualAge = getElement(brandFilterBy,"Result Name Label").getText();
		CompareString(actualAge,expectedAge,"Result Age");
	}





	public void validateNameOfFilteredProducts(String data) throws Exception {
		// TODO Auto-generated method stub

		List<WebElement> elements =  getElements(FilterdProductLabelsBy);
		String[] products = new String[elements.size()];
		for(int i=0;i<elements.size();i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", elements.get(i));
			js.executeScript("arguments[0].style.border='2px solid red'", elements.get(i)); 
			js.executeScript("arguments[0].style.border='0px solid red'", elements.get(i)); 
			
			products[i]=elements.get(i).getText();
		}
		Arrays.sort(products);
		String actualList="";
		for(int i=0;i<products.length;i++) {
			if(i == 0)
				actualList=products[i];
			else
				actualList=actualList+", "+products[i];		
		}

		String[] expected = data.split(",");
		Arrays.sort(expected);

		String expectedList="";
		for(int i=0;i<expected.length;i++) {
			if(i == 0)
				expectedList=expected[i];
			else
				expectedList=expectedList+", "+expected[i];		
		}
		CompareString(actualList,expectedList.toUpperCase(),"Names of filtered products");
	}

	

	public void validatePriceOfFilteredProduct(String productList, String priceList) throws Exception {
		
		String[] products = productList.split(",");
		String[] prices = priceList.split(",");
		for(int i=0;i<products.length;i++) {
			By by = By.xpath(FilterdProductPriceLabelsBy.replace("%s%", products[i]));
			WebElement element = getElement(by,"price label of "+products[i]);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element);
			js.executeScript("arguments[0].style.border='0px solid red'", element); 
			
			String actualprice = element.getText();
			CompareString(actualprice,prices[i],"Price of filtered product");
		}	
	}
	
	public void clickAddToBasket(String product) throws Exception {

		By addToBasketBy = By.xpath(addToBasketByString.replace("%s%", product));
		jsClick(addToBasketBy,"Add to basket button",10);
		
	}

	

	public void validateCountInBasket(int i) throws Exception {
		String actualCount = getElement(basketCountBy,"Basket Count").getText();
		CompareString(actualCount,i+"","Basket Count");	
	}
	
	public void clickBasketIcon() throws Exception {
		Thread.sleep(100);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		Thread.sleep(100);
	}
	
	

}
