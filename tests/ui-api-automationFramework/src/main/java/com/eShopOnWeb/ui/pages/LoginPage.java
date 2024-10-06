package com.eShopOnWeb.ui.pages;

import com.eShopOnWeb.ui.Utility.UiUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Soniya Patel
 */

public class LoginPage extends UiUtils {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "Input_Email")
    private WebElement emailField;

    @FindBy(id = "Input_Password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "div[class='text-danger validation-summary-errors'] ul li")
    private WebElement errorMessage;


    public void enterEmail(String email) {
        sendTextToElement(emailField, email);
        log.info("Entering email " + email + " to email field : " + emailField.toString());
    }

    public void enterPassword(String password) {
        sendTextToElement(passwordField, password);
        log.info("Entering password " + password + " to password field : " + passwordField.toString());
    }

    public void clickOnLogin() {
        clickOnElement(loginButton);
        log.info("Click on login button " + loginButton.toString());
    }

    public String getErrorMessage() {
        log.info("Getting error message: " + errorMessage.toString());
        return getTextFromElement(errorMessage);
    }

}
