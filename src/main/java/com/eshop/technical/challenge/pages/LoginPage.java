package com.eshop.technical.challenge.pages;

import com.eshop.technical.challenge.helper.Constants;
import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    private String loginPageTitle = "Log in";

    private String email ="#Input_Email";
    private String password = "#Input_Password";
    private String loginBtn = "[type='submit']";
    private String invalidLoginErrors = ".validation-summary-errors";


    public LoginPage(Page page) {
        this.page = page;
    }

    public boolean verifyLoginPageTitleIsDisplayed() {
        return page.getByText(loginPageTitle).first().textContent().equalsIgnoreCase(Constants.LOGIN_PAGE_TITLE);
    }

    public void userLoginToEShop(String userEmail, String userPassword) {
    page.fill(email, userEmail);
    page.fill(password, userPassword);
    page.click(loginBtn);
    }

    public String getInvalidLoginErrors() {
        return page.textContent(invalidLoginErrors).trim();
    }
}
