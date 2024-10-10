package com.eshop.technical.challenge.pages;

import com.eshop.technical.challenge.helper.Variables;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HomePage {

    private Page page;

    private String brandImage = "Shop On Web";

    private String loginBtn = "a:text('Login')";

    private String addToBasketBtn = ".esh-catalog-button";
    private String productName = ".esh-catalog-name";
    private String brandDropdown = "#CatalogModel_BrandFilterApplied";
    private String typeDropdown = "#CatalogModel_TypesFilterApplied";
    private String search = ".esh-catalog-send";

    private String filteredItem = "div[class='esh-catalog-name'] span";


    public HomePage(Page page) {
        this.page = page;
    }

    public LoginPage navigateToLoginPage() {
        page.click(loginBtn);
        return new LoginPage(page);
    }

    public boolean isBrandImageIconDisplayed() {
        System.out.println(page.getByAltText(brandImage).textContent());
        return page.getByAltText(brandImage).isVisible();
    }

    public void selectProductToAddToBasket(String product) {

        for (int i = 0; i < page.locator(productName).count(); i++) {
            if (page.locator(productName).nth(i).textContent().trim().equalsIgnoreCase(product)) {
                Variables.productName = page.locator(productName).nth(i).textContent();
                page.locator(addToBasketBtn).nth(i).click();
                break;
            }
        }
    }

    public void filterItems(String brand, String type) {
        page.click(brandDropdown);
        page.selectOption(brandDropdown, brand);
        page.click(typeDropdown);
        page.selectOption(typeDropdown,type);
        page.click(search);
    }

    public String getFilteredItemName() {
        return page.textContent(filteredItem).trim();
    }
}

