import com.microsoft.playwright.Page;
import io.cucumber.java.en*;
import org.junit.Assert;
import { Given, Then, setDefaultTimeout, When } from '@cucumber/cucumber';

setDefaultTimeout(global.CUCUMBER_TIMEOUT);
public  class addToBasketsteps; 

Given('I am on the product page'), async () => {
await global.page.navigate("https//www.eshoponweb.co.uk/dashboard")
};

And('I click on the Add to Basket button'), async() => {
await addToBasketPage.clickButton(locators.addtoBasketButton);
};

Then('The selected items is added to basket'), async () => {
await global.page.locators('#productid').first().waitFor;
await addToBasketPage.updateBasket(shoppingBasketUpdate);
  const [textContent] = await global.page.locator(locators.productalertSuccessMessage);
  expect(textContent, `Success message ${message} not found`).to.contain(message);
};

