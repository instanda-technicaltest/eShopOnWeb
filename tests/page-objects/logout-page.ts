import { Locator, Page } from '@playwright/test';
import Assert from '../step-definitions/Assertions/ui-assertions';

// Userlogout class handles the logout functionality and verification
export class Userlogout {
    readonly page: Page;
    readonly loginLink: Locator;
    readonly logoutLink: Locator;
    readonly assert: Assert;

    // Constructor initializes the page object and locators for the login and logout links
    constructor(page: Page) {
        this.page = page;
        this.assert = new Assert(this.page);
        this.loginLink = page.getByRole('link', { name: 'Login' });
        this.logoutLink = page.getByRole('link', { name: 'Log Out' });
    }

    // Method to log out the user
    async logout() {
        await this.logoutLink.click();
    }

  // Method to verify that the user has logged out successfully by checking if the login link is visible
    async verifySuccessfulLogout() {
        await this.assert.assertElementVisible(this.loginLink);
    }
}