package eShop_BDDCucumber.eShop_CucumberFramework;

import eShop_BDDCucumber.eShop_CucumberFramework.CoreConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface DriverManager {
    CoreConfig config = ConfigFactory.create(CoreConfig.class);
    public WebDriver getDriver();
}
