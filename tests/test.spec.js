const { chromium } = require('playwright');
const { test, expect } = require('@playwright/test');

import { filterType, filterBrand, loginUser, loginAdmin } from './constants';

const URI = "http://localhost:5106";


test.beforeEach(async({page}) => {
    await page.goto(URI);
});

test.describe('Add single type of item to basket', () => {
    test('Add Item', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await expect(page.locator('body > div > header > div > article > section.col-lg-1.col-xs-12 > a > div.esh-basketstatus-badge')).toHaveText("1");
    });
});

test.describe('Add different items to basket', () => {
    test('Add Multiple Items', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.locator("xpath=/html/body/div/div/form/div/div[3]/section[1]/a").click();
        await AddItem(page, "/html/body/div/div/div[2]/div[4]/form/input[1]");
        await expect(page.locator('body > div > header > div > article > section.col-lg-1.col-xs-12 > a > div.esh-basketstatus-badge')).toHaveText("2");
    });
});

test.describe('Manipulate items in basket', () => {
    test('Remove an item from basket', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.locator('xpath=/html/body/div/div/form/div/article/div[1]/section[4]/input[2]').fill("0");
        await page.click('xpath=/html/body/div/div/form/div/div[3]/section[2]/button');
        await expect(page.getByText("Basket is empty.")).toBeVisible();
    });

    test('Increase quantity of basket', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.locator('xpath=/html/body/div/div/form/div/article/div[1]/section[4]/input[2]').fill("4");
        await page.click('xpath=/html/body/div/div/form/div/div[3]/section[2]/button');
        await expect(await page.locator("body > div > div > form > div > article > div:nth-child(1) > section:nth-child(4) > input.esh-basket-input")).toHaveValue("4");
    });
});

test.describe('Checkout without logging in', () => {
    test('Checkout not logged in', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.click("xpath=/html/body/div/div/form/div/div[3]/section[2]/a"); // checkout
        await expect(page.getByRole('heading', { name: 'Log in' })).toBeVisible();
    });
});

test.describe('Checkout Successfully with an order', () => {
    test('Checkout - Log in after filling basket', async({page}) => {
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.click("xpath=/html/body/div/div/form/div/div[3]/section[2]/a"); // checkout
        await LoginUserCheckOut(page, loginUser);
        await page.click("xpath=/html/body/div/div/form/div/div[3]/section[2]/input"); // pay now
        await expect(page.getByText("Thanks for your Order!")).toBeVisible();
    });

    test('Checkout - Log in before filling basket', async({page}) => {
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/section/div/a');
        await LoginUserHome(page, loginUser);
        await AddItem(page, "/html/body/div/div/div[2]/div[1]/form/input[1]");
        await page.click("xpath=/html/body/div/div/form/div/div[3]/section[2]/a"); // checkout        
        await page.click("xpath=/html/body/div/div/form/div/div[3]/section[2]/input"); // pay now
        await expect(page.getByText("Thanks for your Order!")).toBeVisible();
    });
});

/*
    highlights an issue with the display of the webpage if more than one order exists.
*/
test.describe('View Order History', () => {
    test.skip('view order', async({page}) => {      
        await LoginUserHome(page, loginUser);
        await page.getByText('demouser@microsoft.com').hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[1]/div');
        //await page.click('xpath=/html/body/div/div/div/article[2]/section[5]/a');
        await page.click('xpath=/html/body/div/div/div/article[5]/section[5]/a');
        await expect(await page.locator("xpath=/html/body/div/div/div/section[1]/article[2]/section[1]")).toHaveText("4");
    });
});

test.describe('Account Management', () => {
    test('Access Account Management', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText('demouser@microsoft.com').hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await expect(page.getByText('Manage your account')).toBeVisible();
    });

    test('Access Password Page', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText('demouser@microsoft.com').hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await page.click('xpath=/html/body/div/div/div/div[1]/ul/li[2]/a');
        await expect(page.getByText('Change password')).toBeVisible();
    });

    test('Access Profile Page', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText('demouser@microsoft.com').hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await page.click('xpath=/html/body/div/div/div/div[1]/ul/li[1]/a');
        await expect(page.getByRole('heading', { name: 'Profile' })).toBeVisible();
    });

    test('Access Twofactor Page', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText(loginUser).hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await page.click('xpath=/html/body/div/div/div/div[1]/ul/li[3]/a');
        await expect(page.getByRole('heading', { name: 'Two-factor authentication' })).toBeVisible();
    });

    test('Enable Authenticator', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText(loginUser).hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await page.click('xpath=/html/body/div/div/div/div[1]/ul/li[3]/a');
        await page.click('xpath=/html/body/div/div/div/div[2]/a[1]');
        await expect(page.getByRole('heading', { name: 'Enable authenticator' })).toBeVisible();
    });

    /*
        errors when clicking on send verification.
    */
    test.skip('Send verification email', async({page}) => {
        await LoginUserHome(page, loginUser);
        await page.getByText(loginUser).hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[2]/div');
        await page.click('xpath=/html/body/div/div/div/div[1]/ul/li[3]/a');
        await page.click('xpath=/html/body/div/div/div/div[2]/div/div/form/div[3]/button');
    });
});

test.describe('Unsuccessful Login Attempts', () => {
    test('Incorrect Password', async({page}) => {
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/section/div/a'); //login
        await page.locator("//*[@id='Input_Email']").fill("demouser@microsoft.com");
        await page.locator("//*[@id='Input_Password']").fill("rubbishPassword");
        await page.click('xpath=/html/body/div/div/div/div/section/form/div[5]/button');
        await expect(page.getByText('Invalid login attempt.')).toBeVisible();
    });

    test('Invalidate Email', async({page}) => {
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/section/div/a'); //login
        await page.locator("//*[@id='Input_Email']").fill("invalidemail");
        await page.locator("//*[@id='Input_Password']").fill("invalidemail");
        await expect(page.getByText('The Email field is not a valid e-mail address.')).toBeVisible();
    });

});

test.describe('Filters', () => {
    filterType.forEach(({ select, expected }) => {
        test(`filter by Type ${select}`, async({page}) => {
            await page.locator('xpath=/html/body/div/section[2]/div/form/label[2]/select').selectOption(select);
            await page.click('xpath=/html/body/div/section[2]/div/form/input');
            await expect(page.getByText(expected)).toBeVisible();
        });
    });
    
    filterBrand.forEach(({ select, expected }) => {
        test(`filter by Brand ${select}`, async({page}) => {
            await page.locator('xpath=/html/body/div/section[2]/div/form/label[1]/select').selectOption(select);
            await page.click('xpath=/html/body/div/section[2]/div/form/input');
            await expect(page.getByText(expected)).toBeVisible();
        });
    });  
});

test.describe('Login/Logout Functionality', () => {
    test('Logout Correctly', async({page}) => {
        await LoginUserHome(page, loginUser);
        await LogOutUser(page, loginUser);
        await expect(page.getByText('Login')).toBeVisible();
    });
});

test.describe('Admin accessibility', () => {
    test('Access Admin Homepage', async({page}) => {
        await LoginUserHome(page, loginAdmin);
        await page.getByText(loginAdmin).hover();
        await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[1]/div');
        await expect(page.getByText('Manage Product Catalog')).toBeVisible();
    });
});


// functionality
async function AddItem(page, item_ref) {
    await page.locator("xpath="+item_ref).click();
}

async function LoginUserHome(page, user) {
    await page.click('xpath=/html/body/div/header/div/article/section[2]/div/section/div/a');
    await page.locator("//*[@id='Input_Email']").fill(user);
    await page.locator("//*[@id='Input_Password']").fill("Pass@word1");
    await page.click('xpath=/html/body/div/div/div/div/section/form/div[5]/button');
}

async function LoginUserCheckOut(page, user) {
    await page.locator("//*[@id='Input_Email']").fill(user);
    await page.locator("//*[@id='Input_Password']").fill("Pass@word1");
    await page.click('xpath=/html/body/div/div/div/div/section/form/div[5]/button');
}

async function LogOutUser(page, user) {
    await page.getByText(user).hover();
    await page.click('xpath=/html/body/div/header/div/article/section[2]/div/form/section[2]/a[3]/div');
}