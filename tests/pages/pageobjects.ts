import { Page, Locator, expect } from "@playwright/test";
import { pages } from "../config";

export class PageObjects {
  // HOMEPAGE & COMMON ELEMENTS
  public page: Page;
  public headerLogo: Locator;
  public headerLogin: Locator;
  public headerCartImg: Locator;
  public heroImg: Locator;
  public headerEmail: Locator;

  public filterBar: Locator;
  public filterBrand: Locator;
  public filterType: Locator;
  public filterBtn: Locator;

  public previousPageBtn: Locator;
  public nextPageBtn: Locator;

  public productContainer: Locator;
  public productImg: Locator;
  public productAtcButton: Locator;
  public productTitle: Locator;
  public productPrice: Locator;

  // PURCHASE FLOW ELEMENTS
  public continueShoppingBtn: Locator;
  public updateCartBtn: Locator;
  public checkoutBtn: Locator;
  public payNowBtn: Locator;
  public quantityInput: Locator;
  public totalPriceTitle: Locator;
  public totalPriceAmount: Locator;
  public thankYouMessage: Locator;

  // LOGIN ELEMENTS
  public loginTitle: Locator;
  public loginEmailInput: Locator;
  public loginPasswordInput: Locator;
  public loginButton: Locator;
  public loginErrorMessage: Locator;
  public forgotPasswordLink: Locator;
  public registerLink: Locator;

  constructor(page: Page) {
    this.page = page;

    // HOMEPAGE & COMMON ELEMENTS
    this.headerLogo = page.getByRole("link", { name: "eShop On Web" });
    this.headerLogin = page.getByRole("link", { name: "Login" });
    this.headerCartImg = page.locator(".esh-basketstatus");
    this.heroImg = page.locator(".esh-catalog-hero");
    this.headerEmail = page
      .locator("#logoutForm section")
      .filter({ hasText: "demouser@microsoft.com" });
    this.filterBar = page.locator(".esh-catalog-filters");
    this.filterBrand = page.getByLabel("All .NET Azure Other SQL");
    this.filterType = page.getByLabel("All Mug Sheet T-Shirt USB");
    this.filterBtn = page.getByRole("button", { name: "Submit" });
    this.previousPageBtn = page.locator(".esh-pager-item-left").first();
    this.nextPageBtn = page.locator(".esh-pager-item-right").first();

    this.productContainer = page.getByText("[ ADD TO BASKET ]");
    this.productImg = page.locator("form").getByRole("img");
    this.productAtcButton = page
      .locator("form")
      .filter({ hasText: "[ ADD TO BASKET ]" })
      .getByRole("button");
    this.productTitle = page.locator(".esh-catalog-name");
    this.productPrice = page.locator(".esh-catalog-price");

    // PURCHASE FLOW ELEMENTS
    this.continueShoppingBtn = page.getByRole("link", {
      name: "[ Continue Shopping ]",
    });
    this.updateCartBtn = page.getByRole("button", { name: "[ Update ]" });
    this.checkoutBtn = page.getByRole("link", { name: "[ Checkout ]" });
    this.payNowBtn = page.getByRole("button", { name: "Pay Now" });
    this.quantityInput = page.locator(".esh-basket-input");
    this.totalPriceTitle = page.getByText("Total");
    this.totalPriceAmount = page.getByText("$").nth(2);
    this.thankYouMessage = page.getByRole("heading", {
      name: "Thanks for your Order!",
    });

    // LOGIN ELEMENTS
    this.loginTitle = page.getByRole("heading", { name: "Log in" });
    this.loginEmailInput = page.getByRole("textbox", { name: "Email" });
    this.loginPasswordInput = page.getByRole("textbox", { name: "Password" });
    this.loginButton = page.getByRole("button", { name: "Log in" });
    this.loginErrorMessage = page.locator("#Input_Email-error");
    this.forgotPasswordLink = page.getByRole("link", {
      name: "Forgot your password?",
    });
    this.registerLink = page.getByRole("link", { name: "Register" });
  }

  async HomePageSmoke() {
    await this.page.goto(pages.home);
    await expect(this.headerLogo).toBeVisible();
    await expect(this.headerLogin).toBeVisible();
    await expect(this.headerCartImg).toBeVisible();
    await expect(this.heroImg).toBeVisible();
    await expect(this.filterBar).toBeVisible();
    await expect(this.filterBrand).toBeVisible();
    await expect(this.filterType).toBeVisible();
    await expect(this.filterBtn).toBeVisible();
    await expect(this.productContainer.first()).toBeVisible();
    await expect(this.productImg.first()).toBeVisible();
    await expect(this.productAtcButton.first()).toBeVisible();
    await expect(this.productTitle.first()).toBeVisible();
    await expect(this.productPrice.first()).toBeVisible();
  }

  async HomePageAPISmoke() {
    const response = await this.page.request.get(pages.home);
    expect(response.status()).toBe(200);
    expect(response.status()).not.toBe(400);
  }

  async LoginAPISmoke() {
    const response = await this.page.request.get(pages.login);
    expect(response.status()).toBe(200);
    expect(response.status()).not.toBe(400);
  }

  async BasketAPISmoke() {
    const response = await this.page.request.get(pages.basket);
    expect(response.status()).toBe(200);
    expect(response.status()).not.toBe(400);
  }

  async nextProductPage() {
    await this.nextPageBtn.click();
    await expect(this.page).toHaveURL(/pageId=1/);
    await expect(this.headerLogo).toBeVisible();
    await expect(this.headerLogin).toBeVisible();
    await expect(this.headerCartImg).toBeVisible();
    await expect(this.heroImg).toBeVisible();
  }

  async productCheck() {
    await this.page.goto(pages.home);

    await expect(this.productAtcButton).toHaveCount(10);
    await expect(this.productImg).toHaveCount(10);
    await expect(this.productTitle).toHaveCount(10);
    await expect(this.productPrice).toHaveCount(10);

    await this.nextProductPage();

    await expect(this.productAtcButton).toHaveCount(2);
    await expect(this.productImg).toHaveCount(2);
    await expect(this.productTitle).toHaveCount(2);
    await expect(this.productPrice).toHaveCount(2);
  }

  async addToCart() {
    await this.page.goto(pages.home);
    await this.productAtcButton.first().click();
    await expect(this.page.url()).toContain("Basket");
    await expect(this.headerLogo).toBeVisible();
    await expect(this.headerLogin).toBeVisible();
    await expect(this.headerCartImg).toBeVisible();
    await expect(this.heroImg).toBeVisible();
  }

  async completePurchase() {
    await this.payNowBtn.click();
    await expect(this.thankYouMessage).toBeVisible();
  }

  async cartSmoke() {
    await this.page.goto(pages.home);
    await this.productAtcButton.first().click();
    await expect(this.page.url()).toContain("Basket");
    await expect(this.headerLogo).toBeVisible();
    await expect(this.headerLogin).toBeVisible();
    await expect(this.headerCartImg).toBeVisible();
    await expect(this.heroImg).toBeVisible();
    await expect(this.quantityInput).toBeVisible();
    await expect(this.totalPriceTitle).toBeVisible();
    await expect(this.totalPriceAmount).toBeVisible();
    await expect(this.continueShoppingBtn).toBeVisible();
    await expect(this.updateCartBtn).toBeVisible();
    await expect(this.checkoutBtn).toBeVisible();
  }

  async loginSmoke() {
    await this.page.goto(pages.login);
    await expect(this.loginTitle).toBeVisible();
    await expect(this.loginEmailInput).toBeVisible();
    await expect(this.loginPasswordInput).toBeVisible();
    await expect(this.loginButton).toBeVisible();
    await expect(this.forgotPasswordLink).toBeVisible();
    await expect(this.registerLink).toBeVisible();
  }

  async happyLogin(email: string, password: string) {
    await expect(this.loginTitle).toBeVisible();
    await this.loginEmailInput.fill(email);
    await this.loginPasswordInput.fill(password);
    await this.loginButton.click();
    await expect(this.headerEmail).toBeVisible();
  }

  async unhappyLogin(email: string, password: string) {
    await expect(this.loginTitle).toBeVisible();
    await this.loginEmailInput.fill(email);
    await this.loginPasswordInput.fill(password);
    await this.loginButton.click();
    await expect(this.loginErrorMessage).toBeVisible();
  }
}
