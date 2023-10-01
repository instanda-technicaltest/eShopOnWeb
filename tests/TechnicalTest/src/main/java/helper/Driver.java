package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
	
	public static WebDriver driver;
	 
	 public WebDriver openWebPage(String link) { 
		 try {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//Driver//chromedriver.exe");
			ChromeOptions handlingSSL = new ChromeOptions();
			handlingSSL.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(handlingSSL);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(link);
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
			return driver;
	 }
	 
}
