package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eShop extends Driver {

    Pages locators = new Pages();
    String validEmail = "demouser@microsoft.com";
    String validPassword = "Pass@word1";

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://localhost:5001");
    }

    @Test
    public void registerPositiveTest() {

        locators.goToRegisterPage();
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();

        Assert.assertTrue(locators.email.isDisplayed());
        Assert.assertTrue(locators.password.isDisplayed());
        Assert.assertTrue(locators.confirmPassword.isDisplayed());
        Assert.assertTrue(locators.registerButtonInRegisterPage.isDisplayed());

        locators.email.sendKeys(email);
        locators.password.sendKeys(validPassword);
        locators.confirmPassword.sendKeys(validPassword);
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals(email, locators.userName.getText());
    }

    @Test
    public void registeredEmailTest() {

        locators.goToRegisterPage();

        // Using previously registered email
        // Working as expected
        locators.email.sendKeys(validEmail);
        locators.password.sendKeys(validPassword);
        locators.confirmPassword.sendKeys(validPassword);
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals("Username '" + validEmail + "' is already taken.",
                locators.passwordValidationMessage.getText());
    }

    @Test
    public void invalidEmailTest() {

        // BUG!!! The application accepts some invalid emails ("instanda@gmail",
        // "abc@123", ".@gmail", "1$2")

        locators.goToRegisterPage();

        String expectedEmailWarning = "The Email field is not a valid e-mail address.";
        String[] invalidEmail = { "instanda", "@instanda", "instanda@gmail..com", "instanda@gmail", "@instanda",
                "@gmail.com", "123",
                "abc@123", ".@gmail", "1@2", };

        for (String email : invalidEmail) {
            locators.email.clear();
            locators.email.sendKeys(email);
            locators.password.click();
            Assert.assertEquals(expectedEmailWarning, locators.wrongEmailWarning.getText());
        }
    }

    @Test
    public void emptyEmailTest() {
        // Working as expected
        locators.goToRegisterPage();
        locators.email.sendKeys("");
        locators.password.sendKeys(validPassword);
        locators.confirmPassword.sendKeys(validPassword);
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals("The Email field is required.", locators.wrongEmailWarning.getText());
    }

    @Test
    public void invalidPasswordTest() {

        locators.goToRegisterPage();
        locators.email.sendKeys(validEmail);

        // Using invalid password-less than 6 characters--> a1C%B
        // Working as expected
        locators.password.sendKeys("a1C%B");
        locators.confirmPassword.sendKeys("a1C%B");
        Assert.assertEquals("The Password must be at least 6 and at max 100 characters long.",
                locators.wrongPasswordWarning.getText());
    }

    @Test
    public void noUpperCasePassword() {
        // Using invalid password- no Uppercase letter--> 1@erpt
        // Working as expected
        locators.goToRegisterPage();
        locators.email.sendKeys(validEmail);
        locators.password.sendKeys("1@erpt");
        locators.confirmPassword.sendKeys("1@erpt");
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals("Passwords must have at least one uppercase ('A'-'Z').",
                locators.passwordValidationMessage.getText());
    }

    @Test
    public void noLowerCasePassword() {

        // Using invalid password- no Lowercase letter--> 1@ERPT
        // Working as expected
        locators.goToRegisterPage();
        locators.email.sendKeys(validEmail);
        locators.password.sendKeys("1@ERPT");
        locators.confirmPassword.sendKeys("1@ERPT");
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals("Passwords must have at least one lowercase ('a'-'z').",
                locators.passwordValidationMessage.getText());

    }

    @Test
    public void noNonAlphanumericPassword() {

        // Using invalid password-no non-alphanumeric character--> 1aERPt
        // Working as expected
        locators.goToRegisterPage();
        locators.email.sendKeys(validEmail);
        locators.password.sendKeys("1aERPt");
        locators.confirmPassword.sendKeys("1aERPt");
        locators.registerButtonInRegisterPage.click();
        Assert.assertEquals("Passwords must have at least one non alphanumeric character.",
                locators.passwordValidationMessage.getText());
    }

    @Test
    public void loginPositiveTestAndBasketTest() {

        // Asserting that Login button is displayed
        Assert.assertTrue(locators.loginButton.isDisplayed());

        String loginButtonText = locators.loginButton.getText();

        // Asserting that login text is shown as expected
        Assert.assertEquals("LOGIN", loginButtonText);

        // Asserting that the next page link navigates to the next page
        locators.nextPageLink.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("pageId=1"));

        //// Asserting that the previous page link navigates to the previous page
        locators.previousPageLink.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("pageId=0"));

        locators.loginButton.click();

        locators.email.sendKeys(validEmail);
        locators.password.sendKeys(validPassword);

        locators.loginSubmit.click();

        // Asserting username: Username should be correctly displayed after the user is
        // logged in
        Assert.assertEquals("demouser@microsoft.com", locators.userName.getText());
    }

    @Test
    public void loginNegativeScenario() {

        // Working as expected

        String expectedWarningMessage = "Invalid login attempt.";
        locators.loginButton.click();
        // Asserting invalid Login warning message with different username&password
        // combinations
        // Invalid email and password
        locators.email.sendKeys("Invalid123@microsoft.com");
        locators.password.sendKeys("InvalidPassword");
        locators.loginSubmit.click();
        Assert.assertEquals(expectedWarningMessage, locators.invalidLoginWarning.getText());

        // Valid email and invalid password
        locators.email.clear();
        locators.password.clear();
        locators.email.sendKeys(validEmail);
        locators.password.sendKeys("InvalidPassword");
        locators.loginSubmit.click();
        Assert.assertEquals(expectedWarningMessage, locators.invalidLoginWarning.getText());

        // Invalid email and valid password
        locators.email.clear();
        locators.password.clear();
        locators.email.sendKeys("invalid@microsoft.com");
        locators.password.sendKeys(validPassword);
        locators.loginSubmit.click();
        Assert.assertEquals(expectedWarningMessage, locators.invalidLoginWarning.getText());
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }
}
