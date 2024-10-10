package com.eshop.technical.challenge.pages;

import com.microsoft.playwright.Page;

public class OrderCompletionPage {
    private Page page;

    private String thankyouMessage = "h1";

    public OrderCompletionPage(Page page) {
        this.page = page;
    }

    public String getOderThankyouMessage() {
        return page.locator(thankyouMessage).textContent().trim();
    }
}
