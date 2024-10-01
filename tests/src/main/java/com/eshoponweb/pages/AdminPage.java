package com.eshoponweb.pages;

import com.eshoponweb.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class AdminPage extends Utility {
    private static final Logger log = LogManager.getLogger(AdminPage.class);

    @FindBy(xpath = "(//a[contains(text(),'Logout')])")
    private WebElement logoutLink;

    public void clickOnLogoutButton() {
        log.info("Click on logout button");
        clickOnElement(logoutLink);
    }

}
