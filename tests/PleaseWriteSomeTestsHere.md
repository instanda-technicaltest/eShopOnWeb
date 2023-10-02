# Please write some Automated Tests!

INSTANDA TASK NOTES

Programming Language: Java

Test Type: Functional Test (UI Test) for Register and Login Functionality. 

I used Selenium WebDriver along with Java and Maven. As a testing framework, I used TestNG. I have created an EShop class to write my test cases. Also, I used Page Object Model by creating a separate page (Pages.java) for all locators. Normally, we keep locators in the related  page class (Home Page, Login Page etc.), however, I wanted to use only one class for locators for this task.

Used Tools/Libraries: 

Selenium WebDriver, ChromeDriver,  WebElement, WebDriverManager, Maven, Page Factory, FindBy annotation, Java, Java Faker Class, TestNG Assertion, TestNG @BeforeMethod, @AfterMethod, @Test annotations.

Tested Functionalities: Register Functionality (Positive and Negative Scenarios), Login (Positive and Negative Scenarios)

I have tried to see if the Register and Login funcitonalities are working as expected and the application is giving expected warnings correctly. I found some issues related to the register functionality as shown below: 

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

1-	When I try an invalid email address as shown below, the application accepts them and navigates to the account page. This will result to create users without a valid email address. 

instanda@gmail

abc@123

.@gmail

1$2

I have inserted a note into each test on EShop.java class to show the functionality is working as expected. For failing tests, I have also inserted a note showing the issue (e.g. //BUG) 

