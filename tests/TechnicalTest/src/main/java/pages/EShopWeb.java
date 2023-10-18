package pages;


import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	private static final String productImage ="//span[text()='%s%']//ancestor::form/img";
	private static final String productPrice ="//span[text()='.NET Bot Black Sweatshirt']//ancestor::form//div[@class='esh-catalog-price']/span";
	private static final String product ="//span[text()='%s%']";

	public EShopWeb(WebDriver driver) {
		super(driver);
	}
	
	public void validatePriceOfProduct(String name, double price) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String expected = (formatter.format(price));
		
		By by = By.xpath(productPrice.replace("%s%", name));
		String actPrice = getElement(by, "Price of Product "+name).getText();
		CompareString(actPrice, expected, "Price of Product "+name);
	}
	
	public void validateProductInFilteredProducts(String name) {
		By by = By.xpath(product.replace("%s%", name));
		getElement(by, "Image of Product "+name, 10);
	}
	
	public void validateImageOfFilteredProduct(String name, String pictureUri) {
		By by = By.xpath(productImage.replace("%s%", name));
		String pic = getElement(by, "Image of Product "+name).getAttribute("src");
		CompareString(pic, "https://localhost:5001"+pictureUri, "Image of Product "+name);
	}

	public void filterBrand(String option)  {
		click(brandFilterBy, "Brand Filter");
		By by = By.xpath(brandFilterOptionString.replace("%s%", option));
//		By by = By.xpath(brandFilterOptionString);//.replace("%s%", option));
		
		click(by, "For brand Filter, "+option+" option", 10);	
	}

	public void filterType(String option)  {
		click(typeFilterBy,"Type Filter");
		By by = By.xpath(TypeFilterOptionString.replace("%s%", option));
		click(by, "For type Filter, "+option+" option", 10);		
	}

	public void clickFilterButton()  {
		click(filterButtonBy, "Filter Button", 10);
	}

	public void validateCountInLabel(String filterCount)  {
		String label = getElement(filteredCountLabelBy,"Filtered Count Label").getText();
		String[] labels = label.split(" ");
		CompareString(labels[1],filterCount,"Filtered count");
	}



	public void compareResultAgeLabelText(String expectedAge)  {
		String actualAge = getElement(brandFilterBy,"Result Name Label").getText();
		CompareString(actualAge,expectedAge,"Result Age");
	}





	public void validateNameOfFilteredProducts(String data)  {
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
		CompareStringInArray(actualList,expectedList.toUpperCase(),"Names of filtered products");
	}

	

	public void validatePriceOfFilteredProduct(String productList, String priceList)  {
		
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
			CompareStringInArray(actualprice,prices[i],"Price of filtered product");
		}	
	}
	
	public void clickAddToBasket(String product)  {

		By addToBasketBy = By.xpath(addToBasketByString.replace("%s%", product));
		jsClick(addToBasketBy,"Add to basket button",10);
		
	}

	

	public void validateCountInBasket(int i)  {
		String actualCount = getElement(basketCountBy,"Basket Count").getText();
		CompareString(actualCount,i+"","Basket Count");	
	}
	
	public void clickBasketIcon()  {
		try {
	
		Thread.sleep(100);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		Thread.sleep(100);
		}catch(Exception e) {
			
		}
	}

	

	

	
	
	

}
