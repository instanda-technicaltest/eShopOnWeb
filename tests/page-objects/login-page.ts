import { Locator, Page } from '@playwright/test';
import Assert from '../step-definitions/Assertions/assert';

// Userlogin class represents the login page functionality for automated testing
export class Userlogin {
    readonly page: Page;
    readonly email: Locator;
    readonly password: Locator;
    readonly loginLink: Locator;
    readonly loginButton: Locator;
    readonly loggedInIndicator: Locator;
    readonly assert: Assert;

    // Constructor initializes the page object and locators for the login elements
    constructor(page: Page) {
        this.page = page;
        this.assert = new Assert(this.page);
        this.loginLink = page.getByRole('link', { name: 'Login' });
        this.email = page.getByLabel('Email');
        this.password = page.getByLabel('Password');
        this.loginButton = page.getByRole('button', { name: 'Log in' });
        this.loggedInIndicator = page.locator('#logoutForm')
    }

    // Method to log in using the provided email and password
    // Checks if the login link is visible before proceeding
    async login(email: string, password: string) {
        const isLoginLinkVisible = await this.loginLink.isVisible();
        if (isLoginLinkVisible) {
            await this.loginLink.click();
            await this.email.fill(email);
            await this.password.fill(password);
            await this.loginButton.click();
        }
    }

    // Method to verify that the user is on the login page by asserting the URL
    async verifyOnLoginPage(baseUrl: string) {
        await this.assert.assertURL(baseUrl);
    }

    // Method to verify successful login by checking if the email appears on the logout form
    async verifySuccessfulLogin(email: string) {
        await this.assert.assertText(this.loggedInIndicator, email);
    }
}