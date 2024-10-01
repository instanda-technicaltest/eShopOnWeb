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
public class LoginPage extends Utility {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@id='Input_Email']")
   private WebElement emailField;
    @FindBy(xpath = "//input[@id='Input_Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Input_RememberMe']")
    private WebElement rememberMeCheckbox;
    @FindBy (xpath = "//a[contains(text(),'Forgot your password?')]")
    private WebElement forgotYourPasswordLink;

    @FindBy(xpath = "//a[contains(text(),'Register as a new user')]")
    private WebElement newUserRegistrationLink;
    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement loginButton;


    public boolean isEmailFieldDisplayed() {
        log.info("Verify email field is displayed " + emailField.isDisplayed());
        return emailField.isDisplayed();
    }
    public boolean isPasswordFieldDisplayed() {
        log.info("Verify password field is displayed " + passwordField.isDisplayed());
        return passwordField.isDisplayed();
    }
    public boolean isRememberMeCheckboxDisplayed() {
        log.info("Verify remember me checkbox is displayed " + rememberMeCheckbox.isDisplayed());
        return rememberMeCheckbox.isDisplayed();
    }
    public boolean isForgotYourPasswordLinkDisplayed() {
        log.info("Verify forgot your password link is displayed " + forgotYourPasswordLink.isDisplayed());
        return forgotYourPasswordLink.isDisplayed();
    }
    public boolean isNewUserRegistrationLinkDisplayed() {
        log.info("Verify new user registration link is displayed " + newUserRegistrationLink.isDisplayed());
        return newUserRegistrationLink.isDisplayed();
    }
    public boolean isLoginButtonDisplayed() {
        log.info("Verify login button is displayed " + loginButton.isDisplayed());
        return loginButton.isDisplayed();
    }
    public void enterEmailId(String email) {
        log.info("Enter email id " + email + " in the email field");
        sendTextToElement(emailField, email);
    }
    public void enterPassword(String password) {
        log.info("Enter password " + password + " in the password field");
        sendTextToElement(passwordField, password);
    }
    public void clickOnRememberMeCheckbox() {
        log.info("Click on remember me checkbox");
        clickOnElement(rememberMeCheckbox);
    }
    public void clickOnForgotYourPasswordLink() {
        log.info("Click on forgot your password link");
        clickOnElement(forgotYourPasswordLink);
    }
    public void clickOnNewUserRegistrationLink() {
        log.info("Click on new user registration link");
        clickOnElement(newUserRegistrationLink);
    }
    public void clickOnLoginButton() {
        log.info("Click on login button");
        clickOnElement(loginButton);
    }



}
