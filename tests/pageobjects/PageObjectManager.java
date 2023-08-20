package pageobjects;

import common.TestContext;

public class PageObjectManager {

    private TestContext testContext;
    public HomePage homePage;
    public BasketPage basketPage;
    public CheckOutPage checkOutPage;

    public PageObjectManager(TestContext context) {
        testContext = context;
        homePage = new HomePage(testContext);
        basketPage = new BasketPage(testContext);
        checkOutPage = new CheckOutPage(testContext);
    }
}
