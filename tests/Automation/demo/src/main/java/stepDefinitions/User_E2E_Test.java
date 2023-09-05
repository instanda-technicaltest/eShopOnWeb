package stepDefinitions;

import commonFunctions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageObjects.*;

public class User_E2E_Test extends CommonFunctions {

    // Create object for page elements and pass the driver to access those elements
    Home_Page homePage = new Home_Page(driver);
    Login_Page loginPage = new Login_Page(driver);
    Basket_Page basketPage = new Basket_Page(driver);
    Update_Page updatePage = new Update_Page(driver);
    Checkout_Page checkoutPage = new Checkout_Page(driver);
    Success_Page successPage = new Success_Page(driver);

    // GLobal variables used in the class
    String u_email, s_basket_Icon;
    int no_Products_OnPage, i_basket_Icon, products_In_Basket,total_Products_In_Basket;

    @Given("as a User, Login Enter URL {string} and redirected to home page")
    public void asAUserLoginEnterURLAndRedirectedToHomePage(String url) throws Exception{
        driver.get(readConfigFile(url));
    }

    @When("click on login button and redirected to login page")
    public void clickOnLoginButtonAndRedirectedToLoginPage(){
        waitForElement(homePage.b_Login,60);
        homePage.b_Login.click();
    }

    @When("login page loaded, Enter email as {string} and password as {string}")
    public void loginPageLoadedEnterEmailAsAndPasswordAs(String email, String password)throws Exception {
        waitForElement(loginPage.i_Email, 60);
        u_email = readConfigFile(email);
        loginPage.i_Email.sendKeys(u_email);
        loginPage.i_Password.sendKeys(readConfigFile(password));
    }

    @And("click on Login button")
    public void clickOnLoginButton() {
        loginPage.b_Login.click();
    }

    @And("validate user has successfully logged in")
    public void validateUserHasSuccessfullyLoggedIn() {
        Assert.assertEquals( u_email,homePage.txt_User.getText());
        s_basket_Icon = homePage.bk_Basket_Value.getText();
    }

    @And("on home page, select Brand as {string} and select Type as {string}")
    public void onHomePageSelectBrandAsAndSelectTypeAs(String brand, String type) {
        homePage.ddi_Brand.click();
        waitForElement(homePage.ddo_Brand_NET, 30);
        if (brand.contains("All")||brand.contains("all"))
        {
            homePage.ddo_Brand_All.click();
        }else if (brand.contains(".")||brand.contains("net")||brand.contains("NET")||brand.contains("Net"))
        {
            homePage.ddo_Brand_NET.click();
        } else if (brand.contains("Azure")||brand.contains("AZURE")||brand.contains("azure")) {
            homePage.ddo_Brand_Azure.click();
        } else if (brand.contains("SQL")||brand.contains("sql")) {
            homePage.ddo_Brand_SQL_Server.click();
        } else if (brand.contains("Other")||brand.contains("other")) {
            homePage.ddo_Brand_Others.click();
        }else if (brand.contains("Visual")||brand.contains("visual")) {
            homePage.ddo_Brand_Visual_Studio.click();
        }

        homePage.ddi_Type.click();
        waitForElement(homePage.ddo_Type_Mug,30);

        if(type.contains("All")||type.contains("all"))
        {
            homePage.ddo_Type_All.click();
        } else if (type.contains("Mug")||type.contains("mug")) {
            homePage.ddo_Type_Mug.click();
        }else if (type.contains("Sheet")||type.contains("sheet")) {
            homePage.ddo_Type_Sheet.click();
        }else if (type.contains("Shirt")||type.contains("shirt")) {
            homePage.ddo_Type_TShirt.click();
        }else if (type.contains("usb")||type.contains("USB")) {
            homePage.ddo_Type_USB_Memory_Stick.click();
        }

    }

    @And("click on Arrow button to filter the result")
    public void clickOnArrowButtonToFilterTheResult() {
        homePage.b_Arrow.click();
    }

    @And("check number of products available on the page")
    public void checkNumberOfProductsAvailableOnThePage() {
        no_Products_OnPage = driver.findElements(By.xpath("//input[@value='[ ADD TO BASKET ]']")).size();

    }

    @And("click on Add to Basket button and redirected to Basket page")
    public void clickOnAddToBasketButtonAndRedirectedToBasketPage() throws Exception{
        if (!driver.findElements(By.xpath("//input[@value='[ ADD TO BASKET ]']")).isEmpty())
        {
            jsScroll(homePage.b_Add_To_Basket);
            homePage.b_Add_To_Basket.click();
        }
    }

    @And("check basket icon updated with quantity")
    public void checkBasketIconUpdatedWithQuantity() {
        String bk_Basket = homePage.bk_Basket_Value.getText();
        Assert.assertNotEquals(bk_Basket, s_basket_Icon);
        i_basket_Icon = Integer.parseInt(bk_Basket);
    }

    @And("check product available in the basket")
    public void checkProductAvailableInTheBasket() {
        products_In_Basket = driver.findElements(By.xpath("//section[contains(@class,'col-lg-3')]/parent::div/parent::article")).size();
        Assert.assertTrue(products_In_Basket >0);
    }

    @And("change existing quantity and click on update button")
    public void changeExistingQuantityAndClickOnUpdateButton() throws Exception{
        jsScroll(basketPage.q_Quantity);
        String s_Current_Quantity = basketPage.q_Quantity.getAttribute("value");
        int i_Current_Quantity = Integer.parseInt(s_Current_Quantity);
        i_Current_Quantity++;
        s_Current_Quantity = String.valueOf(i_Current_Quantity);

        basketPage.q_Quantity.clear();
        basketPage.q_Quantity.sendKeys(s_Current_Quantity);

        jsScroll(basketPage.b_Update);
        basketPage.b_Update.click();
    }

    @And("click on Continue Shopping button and redirected to home page")
    public void clickOnContinueShoppingButtonAndRedirectedToHomePage() throws Exception{
        jsScroll(basketPage.b_Continue_Shopping);
        basketPage.b_Continue_Shopping.click();
        jsScroll(homePage.ddi_Brand);
    }

    @And("select Brand as {string} and select Type as {string}")
    public void selectBrandAsAndSelectTypeAs(String brand, String type) {
        homePage.ddi_Brand.click();
        waitForElement(homePage.ddo_Brand_NET, 30);
        if (brand.contains("All")||brand.contains("all"))
        {
            homePage.ddo_Brand_All.click();
        }else if (brand.contains(".")||brand.contains("net")||brand.contains("NET")||brand.contains("Net"))
        {
            homePage.ddo_Brand_NET.click();
        } else if (brand.contains("Azure")||brand.contains("AZURE")||brand.contains("azure")) {
            homePage.ddo_Brand_Azure.click();
        } else if (brand.contains("SQL")||brand.contains("sql")) {
            homePage.ddo_Brand_SQL_Server.click();
        } else if (brand.contains("Other")||brand.contains("other")) {
            homePage.ddo_Brand_Others.click();
        }else if (brand.contains("Visual")||brand.contains("visual")) {
            homePage.ddo_Brand_Visual_Studio.click();
        }

        homePage.ddi_Type.click();
        waitForElement(homePage.ddo_Type_Mug,30);

        if(type.contains("All")||type.contains("all"))
        {
            homePage.ddo_Type_All.click();
        } else if (type.contains("Mug")||type.contains("mug")) {
            homePage.ddo_Type_Mug.click();
        }else if (type.contains("Sheet")||type.contains("sheet")) {
            homePage.ddo_Type_Sheet.click();
        }else if (type.contains("Shirt")||type.contains("shirt")) {
            homePage.ddo_Type_TShirt.click();
        }else if (type.contains("usb")||type.contains("USB")) {
            homePage.ddo_Type_USB_Memory_Stick.click();
        }

    }

    @And("check basket icon is updated with quantity")
    public void checkBasketIconIsUpdatedWithQuantity() {
        s_basket_Icon = String.valueOf(i_basket_Icon);
        String bk_basket = homePage.bk_Basket_Value.getText();

        Assert.assertNotEquals(s_basket_Icon,bk_basket);
    }

    @And("check multiple products available in the basket")
    public void checkMultipleProductsAvailableInTheBasket() {
        total_Products_In_Basket = driver.findElements(By.xpath("//section[contains(@class,'col-lg-3')]/parent::div/parent::article")).size();

    }

    @And("validate total amount")
    public void validateTotalAmount() {
        basketPage.tv_Total.isDisplayed();
    }

    @And("click on Checkout button and redirected to checkout page")
    public void clickOnCheckoutButtonAndRedirectedToCheckoutPage() throws Exception{
        jsScroll(basketPage.b_Checkout);
        basketPage.b_Checkout.click();
    }

    @And("under Review section, Validate Product, Price, Quantity and Price")
    public void underReviewSectionValidateProductPriceQuantityAndPrice() throws Exception{
        int article = driver.findElements(By.xpath("//section[contains(@class,'col-lg-3')]/parent::div/parent::article")).size();
        for(int i=1;i<=article;i++)
        {
            jsScroll(driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article)["+i+"]")));
            driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article)["+i+"]")).isDisplayed();
            driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[2])["+i+"]")).isDisplayed();
            driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[3])["+i+"]")).isDisplayed();

            jsScroll(driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[4])["+i+"]")));
            driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[4])["+i+"]")).isDisplayed();
            driver.findElement(By.xpath("(//section[contains(@class,'col-lg-3')]/parent::div/parent::article//section[5])["+i+"]")).isDisplayed();

        }
    }

    @And("click on Pay Now button and redirected to success page")
    public void clickOnPayNowButtonAndRedirectedToSuccessPage() throws Exception{
        jsScroll(checkoutPage.b_Pay_Now);
        checkoutPage.b_Pay_Now.click();
    }

    @And("check message has displayed")
    public void checkMessageHasDisplayed(String docString) {
        String s_Message = successPage.txt_Message.getText();
        Assert.assertEquals(docString,s_Message);
    }
}
