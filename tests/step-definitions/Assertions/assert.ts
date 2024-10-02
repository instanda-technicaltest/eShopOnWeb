import { expect, Locator, Page } from "@playwright/test";

export default class Assert {

    constructor(private page: Page) { }

    async assertTitle(title: string) {
        await expect(this.page).toHaveTitle(title);
    }

    async assertTitleContains(title: string) {
        const pageTitle = await this.page.title();
        expect(pageTitle).toContain(title);
    }

    async assertURL(url: string) {
        await expect(this.page).toHaveURL(url);
    }

    async assertURLContains(title: string) {
        const pageURL = this.page.url();
        expect(pageURL).toContain(title);
    }

    async assertValue(field: Locator, value: string) {
        await expect(field).toHaveValue(value);
    }

    async assertText(field: Locator, value: string) {
        await expect(field).toContainText(value);
    }


    async assertTextHidden(text: string) {
        await expect(this.page.getByText(text)).toBeHidden();
    }

    async assertElementVisible(webElement: Locator) {
        await expect(webElement).toBeVisible();
    }

    async assertElementNotVisible(webElement: Locator) {
        await expect(webElement).toBeHidden();
    }

    async assertString(actual: string, expected: string) {
        expect(actual).toEqual(expected);
    }

    async assertNotNull(actual: string) {
        expect(actual).not.toBe(null);
    }

    async assertElementDisable(webElement: Locator) {
        await expect(webElement).toBeDisabled();
    }

    async assertElementEnable(webElement: Locator) {
        await expect(webElement).toBeEnabled();
    }
    async assertElementNotEmpty(webElement: Locator) {
        await expect(webElement).not.toBeEmpty();
    }
}