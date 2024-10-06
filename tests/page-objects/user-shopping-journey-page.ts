import { Locator, Page } from '@playwright/test';
import Assert from '../step-definitions/Assertions/ui-assertions';

/**
 * This class represents the entire shopping journey for a user on eshoponweb application.
 * It encapsulates actions such as filtering products by brand and type, adding products to the cart, checking out and validating the final order.
 */

export class UserShoppingJourney {
    readonly page: Page;
    readonly brandDropdown: Locator;
    readonly typeDropDown: Locator;
    readonly submitButton: Locator;
    readonly assert: Assert;
    readonly checkoutLink: Locator;
    readonly payNowButton: Locator;
    readonly reviewHeading: Locator;
    readonly spinButton: Locator;
    readonly confirmationMessage: Locator;

    /**
    * The constructor initializes the page and locators used for the user journey.
    * @param page - the page object provided by Playwright to interact with the browser.
    */
    constructor(page: Page) {
        this.page = page;
        this.assert = new Assert(this.page);
        this.brandDropdown = page.locator('#CatalogModel_BrandFilterApplied');
        this.typeDropDown = page.locator('#CatalogModel_TypesFilterApplied');
        this.submitButton = page.getByRole('button', { name: 'Submit' });
        this.checkoutLink = page.getByRole('link', { name: '[ Checkout ]' });
        this.payNowButton = page.getByRole('button', { name: '[ Pay Now ]' });
        this.reviewHeading = page.locator('div[class="container"] h1');
        this.spinButton = page.getByRole('spinbutton');
        this.confirmationMessage = page.locator('div[class="container"] h1');
    }

    /**
    * This function selects a product brand and type from the dropdowns, then submits the selection.
    * @param brand - the brand of the product (e.g., '.NET')
    * @param type - the type of the product (e.g., 'T-Shirt')
    */
    async selectBrandAndType(brand: string, type: string) {
        await this.page.selectOption('#CatalogModel_BrandFilterApplied', { label: brand });
        await this.page.selectOption('#CatalogModel_TypesFilterApplied', { label: type });
        await this.submitButton.click();
    }

    /**
    * Adds a product to the cart based on the product name passed as an argument.
    * The function also asserts that the cart count is updated correctly.
    * @param product - the product to add to the basket (e.g., '.NET Foundation Sheet')
    */
    async addProduct(product: string) {
        await this.page.locator('form').filter({ hasText: '[ ADD TO BASKET ] ' + product }).getByRole('button').click();

        // Assert that the cart count (represented by the spin button) has updated to 1
        let cartCount = 1;
        await this.assert.assertValue(this.spinButton, cartCount.toString());
    }

    /**
     * Proceeds to checkout by clicking the checkout link, and asserts that the user is on the review page.
     */
    async checkout() {
        let expectedHeadingafterCheckout = 'Review';
        await this.checkoutLink.click();

        // Assert that the page heading matches the expected value ('Review')
        await this.assert.assertText(this.reviewHeading, expectedHeadingafterCheckout);
    }

    /**
     * Clicks the "Pay Now" button to complete the order.
     */
    async payNow() {
        await this.payNowButton.click();

    }

      /**
     * Validates that the confirmation message appears after a successful order.
     */
    async validateConfirmationMessage() {
        let expectedConfirmationMessage = 'Thanks for your Order!'
        await this.assert.assertText(this.confirmationMessage, expectedConfirmationMessage)
    }
}