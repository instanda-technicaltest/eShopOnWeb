# Please write some Automated Tests!

INSTANDA TASK NOTES

Programming Language: Java

I used Selenium WebDriver along with Java. For testing, I used TestNG annotations. I have created an eShop class to write my test cases. Also, I used Page Object Model by creating a separate page (Pages.java) for all locators. Normally, we keep locators in the related  page class (Home Page, Login Page etc.), however, I wanted to use only one class for locators for this task.

Used Tools/Libraries: 
Selenium WebDriver, ChromeDriver,  WebElement, Page Factory, FindBy annotation, Java, Faker Class, TestNG Assert, @BeforeTest, @AfterTest, @Test annotations

Tested Functionalities: Register (Positive and Negative Scenarios), Login (Positive and Negative Scenarios)
I have tried to see if the application gives expected warnings correctly.

Expected Warnings:
	The Email field is not a valid e-mail address.
	The Email field is required.
	Username 'demouser@microsoft.com' is already taken.
	The Password must be at least 6 and at max 100 characters long.
	Passwords must have at least one non alphanumeric character.
	Passwords must have at least one lowercase ('a'-'z').
	Passwords must have at least one uppercase ('A'-'Z').

BUGS:
Register Functionality
1-	When I try an invalid email as shown below, the application accepts it and navigates to the account page. A valid email address should be entered.

instanda@gmail
abc@123
.@gmail
1$2
