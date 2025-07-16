import { test } from "@playwright/test";
import { PageObjects } from "../pages/pageobjects";

test.describe("Simple Smoke Tests", () => {
  test("UI Smoke", async ({ page }) => {
    const pageObjects = new PageObjects(page);
    await pageObjects.HomePageSmoke();
    await pageObjects.productCheck();
    await pageObjects.cartSmoke();
    await pageObjects.loginSmoke();
  });

  test("API Smoke", async ({ page }) => {
    const pageObjects = new PageObjects(page);
    await pageObjects.HomePageAPISmoke();
    await pageObjects.LoginAPISmoke();
    await pageObjects.BasketAPISmoke();
  });
});

test.describe("Purchase Flow Tests", () => {
  test("Purchase Flow E2E", async ({ page }) => {
    const pageObjects = new PageObjects(page);

    await page.goto("http://localhost:5106/");
    await pageObjects.addToCart();
    await pageObjects.checkoutBtn.click();
    await pageObjects.happyLogin("demouser@microsoft.com", "Pass@word1");
    await pageObjects.completePurchase();
  });
});

test.describe("Login Tests", () => {
  test("Unhappy login", async ({ page }) => {
    const pageObjects = new PageObjects(page);

    await page.goto("http://localhost:5106/Identity/Account/Login");
    await pageObjects.unhappyLogin("notanemail", "notapassword");
  });

  test("Happy login", async ({ page }) => {
    const pageObjects = new PageObjects(page);

    await page.goto("http://localhost:5106/Identity/Account/Login");
    await pageObjects.happyLogin("demouser@microsoft.com", "Pass@word1");
  });
});
