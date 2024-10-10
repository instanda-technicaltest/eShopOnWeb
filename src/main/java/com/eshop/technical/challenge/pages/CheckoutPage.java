package com.eshop.technical.challenge.pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private Page page;
    private String paynowBtn = "[type='submit'].esh-basket-checkout";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void payNow() {
        page.click(paynowBtn);
    }
}
