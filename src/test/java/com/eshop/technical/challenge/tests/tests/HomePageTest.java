package com.eshop.technical.challenge.tests.tests;

import com.eshop.technical.challenge.helper.Constants;
import com.eshop.technical.challenge.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyBrandImageIsDisplayed() {
        Assert.assertTrue(homePage.isBrandImageIconDisplayed(), "Brand Image is not displayed on the Home Page ");
    }
    @Test
    public void verifySearchItem(){
        homePage.filterItems(prop.getProperty("brand"), prop.getProperty("type"));
        Assert.assertEquals(homePage.getFilteredItemName(), Constants.BRAND_CUP_TEXT);
    }



}
