package eShop_BDDCucumber.eShop_CucumberFramework;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;

@Config.Sources("classpath:CoreConfig.properties")
public interface CoreConfig extends Config {

    @Key("browser.app.url") @DefaultValue("")
    String browserAppURL();

    @Key("browser.implicit.timeout")
    Integer browserImplicitWaitTimeOut();

    @Key("browser.explicit.timeout")
    Integer browserExplicitWaitTimeOut();

    @Key("browser.close") @DefaultValue("true")
    boolean closeBrowser();
    
    @Key("selenium.grid.url")
    String seleniumGridURL();

}