package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;


import helper.Data;

public class BaseTest {
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
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
}
