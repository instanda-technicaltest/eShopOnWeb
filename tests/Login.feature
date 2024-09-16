 @EShopOnWebLogin @Regression
 Feature: Log in to eShopOnWeb

@login1
Scenario: Valid Login 
Given I navigate to EShopOnWeb homepage 
And  I am logged in with a valid username and password
Then I am on the EshopOnWeb dashboard

@login2
Scenario: Invalid Login 
Given I navigate to EShopOnWeb homepage
And I enter in "<email" and "<password>" on the log in page 
Then I verify warning message "Incorrect Password" is displayed

Examples: 
| email                   | password    |
| demouser@microsoft.com  |tester4Eshop |


