import { Given, When, Then } from '@cucumber/cucumber';
import { commonContext } from '../setup/global';
import { page } from '../setup/world'
import { Userlogin } from '../page-objects/login-page';
import { Userlogout } from '../page-objects/logout-page';



Given('the user is logged into the application', async () => {
    const userlogin = new Userlogin(page);
    await userlogin.verifyOnLoginPage(process.env.BASE_URL!);
    await userlogin.login(process.env.EMAIL!, process.env.PASSWORD!);
    await userlogin.verifySuccessfulLogin(process.env.EMAIL!);
    commonContext.logger.info("Login Successfully");
});

When('the user clicks the logout button', async () => {
    const userlogout = new Userlogout(page);
    await userlogout.logout();
    commonContext.logger.info("Logout Clicked");
});

Then('the user should be logged out', async () => {
    const userlogout = new Userlogout(page);
    await userlogout.verifySuccessfulLogout();
    commonContext.logger.info("Logout Successfully");
});
