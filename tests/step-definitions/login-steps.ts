import { Given, When, Then } from '@cucumber/cucumber';
import { commonContext } from '../setup/global';
import { page } from '../setup/world'
import { Userlogin } from '../page-objects/login-page';


Given('the user is on the login page', async () => {
    const userlogin = new Userlogin(page);
    await userlogin.verifyOnLoginPage(process.env.BASE_URL!);
    commonContext.logger.info("Verified user is on login page");
});

When('the user logs in with valid credentials', async () => {
    const userlogin = new Userlogin(page);
    await userlogin.login(process.env.EMAIL!, process.env.PASSWORD!);
    commonContext.logger.info("Valid credentials entered successfully");
});

Then('the user should be redirected to home page', async () => {
    const userlogin = new Userlogin(page);
    await userlogin.verifySuccessfulLogin(process.env.EMAIL!);
    commonContext.logger.info("Login Successfully");
});
