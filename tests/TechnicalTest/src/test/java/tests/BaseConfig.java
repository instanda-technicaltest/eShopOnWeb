package tests;


import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


import helper.Data;
import helper.Reporter;

public class BaseConfig {
	WebDriver driver;
	Data data = new Data();
	SoftAssert softAssert = new SoftAssert();
	

	
	@BeforeClass
	public void setUp() {
		data.setData("EShopOnWebAddToCartAndCheckOut");	
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//Driver//chromedriver.exe");
		ChromeOptions handlingSSL = new ChromeOptions();
		handlingSSL.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(handlingSSL);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(data.getData("lauchURL"));
		
		String className = this.getClass().getName();
		 Reporter.createReportFile(className);
		
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		Reporter.addScenarioToReport(method.getName());
	}
	
	@AfterMethod
	public void afterMethod() {
		Reporter.addStepsToReport();
		 Reporter.details.clear();
	}
	

	@AfterClass
	public void tearDown() {
		 Reporter.writeResults(); 	
		driver.close();
	}
	
}
