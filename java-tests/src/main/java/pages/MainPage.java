package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utilities;

public class MainPage extends Utilities {
    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginButton;

    @CacheLookup
    @FindBy(css="#CatalogModel_TypesFilterApplied")
    WebElement dropdown;

    @CacheLookup
    @FindBy(css="body > div > section.esh-catalog-filters > div > form > input")
    WebElement filterButton;

    public void gettitle(){
       String s= driver.getTitle();
        System.out.println(s);

    }

    public void clickOnLoginButton(){
        loginButton.click();

    }

    public void selectTshirtFromDropDown(){
        Select select=new Select(dropdown);
        select.selectByValue("2");
    }

    public void clickonFilterButton(){
        filterButton.click();
    }


}
