import { When, Then } from '@cucumber/cucumber';
import { commonContext } from '../setup/global';
import { page } from '../setup/world'; // Assuming you have page in world setup
import { UserShoppingJourney } from '../page-objects/user-shopping-journey-page';
import { Userlogout } from '../page-objects/logout-page';

// Step 1: Selecting the brand and type from the dropdown filters
When('the user selects {string} brand and {string} type', async (brand: string, type: string) => {

    const usershoppingjourney = new UserShoppingJourney(page);
    await usershoppingjourney.selectBrandAndType(brand, type);
    commonContext.logger.info("Brand: " + brand + " and Type: " + type + " filtered");
});

// Step 2: Adding the product to the cart
When('the user adds {string} to the cart', async (product: string) => {

    const usershoppingjourney = new UserShoppingJourney(page);
    await usershoppingjourney.addProduct(product);
    commonContext.logger.info("Product " + product + " Added");
});

// Step 3: Proceeding to checkout
When('the user proceeds to checkout', async () => {

    const usershoppingjourney = new UserShoppingJourney(page);
    await usershoppingjourney.checkout();
    commonContext.logger.info("Checkout clicked");
});

// Step 4: Clicking the "Pay Now" button
When('the user clicks the "Pay Now" button', async () => {
    const usershoppingjourney = new UserShoppingJourney(page);
    await usershoppingjourney.payNow();
    commonContext.logger.info("Paynow Clicked");
});

// Step 5: Verifying the order confirmation message
Then('the user should see the order confirmation message', async () => {
    const usershoppingjourney = new UserShoppingJourney(page);
    await usershoppingjourney.validateConfirmationMessage();
    commonContext.logger.info("Confirmation message validated");
});

// Step 6: Logging out
Then('the user logs out', async () => {
    const userlogout = new Userlogout(page);
    await userlogout.logout();
    commonContext.logger.info("Logout Clicked");
    await userlogout.verifySuccessfulLogout();
    commonContext.logger.info("Logout Successfully");
});
