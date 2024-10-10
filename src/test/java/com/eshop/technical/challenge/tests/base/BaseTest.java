package com.eshop.technical.challenge.tests.base;

import com.eshop.technical.challenge.pages.*;
import com.eshop.technical.challenge.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected BasketPage basketPage;
    protected CheckoutPage checkoutPage;
    protected OrderCompletionPage orderCompletionPage;


    @BeforeTest
    public void setUp() {
        pf = new PlaywrightFactory();
        prop = pf.initProp();
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);
        basketPage = new BasketPage(page);
        checkoutPage = new CheckoutPage(page);
        orderCompletionPage = new OrderCompletionPage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }
}


