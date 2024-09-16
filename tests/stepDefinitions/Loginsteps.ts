import com.microsoft.playwright.Page;
import io.cucumber.java.en*;
import org.junit.Assert;
import { Given, Then, setDefaultTimeout, When } from '@cucumber/cucumber';

setDefaultTimeout(global.CUCUMBER_TIMEOUT);
const UserCreds = JSON.parse(process.env.eShopOnWeb_USER as string);
public  class Loginsteps;

Given('I navigate to EShopOnWeb homepage'), async () => {
await global.page.navigate("https//www.eshoponweb.co.uk");
};

And('I am logged in with "eShopOnWeb_USER"'), async () => {
const{ username } = JSON.parse(process.env.SCOPUS_USER as string);
await loginInPage.clickButton(locators.login);
};

Then('I am on the EshopOnWeb dashboard'), async () => {
await global.page.navigate("https//www.eshoponweb.co.uk/dashboard")
};

And('I enter in {string} and {string} on the log in page'), async () => {
await loginInPage.enterUserEmail(username);
await loginInPage.enterUserPassword(password);
};

Then('I verify warning message "Incorrect Password" is displayed'), async () => {
    const actualMsg = await loginInPage.getWarnignMsg();
    expect(actualMsg && actualMsg.includes(expectMsg), `Cannot find the expected warning message: ${expectMsg}`).to.equal(
      true,
    );
  };
