package com.eshop.technical.challenge.pages;

import com.microsoft.playwright.Page;

public class BasketPage {
    private Page page;

    private String checkoutBtn = ".esh-basket-checkout";
    public BasketPage(Page page) {
        this.page = page;
    }

    public void checkOutBasket() {
        page.locator(checkoutBtn).last().click();
    }

}
