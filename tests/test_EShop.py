import pytest
from playwright.sync_api import sync_playwright


class TestEshop:
    Username = "demouser@microsoft.com"
    Password = "Pass@word1"

    @pytest.fixture(autouse=True)
    def setup_teardown(self):
        """Setup and teardown for all tests."""
        self.playwright = sync_playwright().start()
        self.browser = self.playwright.chromium.launch(headless=False)  # Change to True for headless mode
        self.page = self.browser.new_page()

        yield

        # Teardown
        self.page.close()
        self.browser.close()
        self.playwright.stop()

    def test_functionality(self):
        self.launch_home_page()
        self.navigate_to_login()
        self.login(TestEshop.Username, TestEshop.Password)
        self.add_products_to_basket()
        self.review_and_checkout()
        self.verify_order_success()
        self.check_my_orders()
        self.logout()

    def test_filter_functionality(self):
        self.launch_home_page()
        self.apply_filter()

    def test_mobile_number(self):
        self.launch_home_page()
        self.navigate_to_login()
        self.login(TestEshop.Username, TestEshop.Password)
        self.mobile_number()

    def launch_home_page(self):
        self.page.goto("http://localhost:5106")
        assert "Catalog - Microsoft.eShopOnWeb" in self.page.title()

    def navigate_to_login(self):
        self.page.click("text=Login")

    def login(self, username, password):
        try:
            self.page.fill("input[type='email']", username)
            self.page.fill("input[type='password']", password)
            self.page.click("button[type='submit']")
        except Exception as e:
            pytest.fail(f"Login failed: {e}")

    def add_products_to_basket(self):
        try:
            self.page.click("//div[@class='esh-catalog-items row']/div[3]//input[@type='submit']")
            self.page.fill("input[type='number']", "2")  # Update quantity if needed
            self.page.click("text=[ Update ]")
        except Exception as e:
            pytest.fail(f"Adding products to basket failed: {e}")

    def review_and_checkout(self):
        try:
            self.page.click("a[class='esh-basketstatus']")
            assert "Basket - Microsoft.eShopOnWeb" in self.page.title()
            self.page.click("text=[ Checkout ]")
            self.page.click("text=Pay Now")
        except Exception as e:
            pytest.fail(f"Review and checkout failed: {e}")

    def verify_order_success(self):
        try:
            assert "Checkout Complete - Microsoft.eShopOnWeb" in self.page.title()
        except Exception as e:
            pytest.fail(f"Order verification failed: {e}")

    def check_my_orders(self):
        self.page.click("div.esh-identity-name")
        self.page.click("text=My Orders")

    def logout(self):
        # self.page.click()
        self.page.click("text=Log out")

    def apply_filter(self):
        try:
            self.page.select_option("select[id = 'CatalogModel_BrandFilterApplied']", label=".NET")
            self.page.select_option("select[id='CatalogModel_TypesFilterApplied']", label="T-Shirt")
            self.page.click("input[class='esh-catalog-send']")
        except Exception as e:
            pytest.fail(f"Applying filter failed: {e}")

    def mobile_number(self):
        try:
            self.page.click("text=My Account")
            self.page.get_by_label("Phone number").fill("0123456789423")
            self.page.click("button[type='submit']")
        except Exception as e:
            pytest.fail(f"Updating mobile number failed: {e}")







