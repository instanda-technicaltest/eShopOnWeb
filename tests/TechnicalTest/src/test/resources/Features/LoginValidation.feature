Feature: Login validations
  This file try positve and negative scenarios of Login, capture error messages and compare it to expected values.
  
  Rule: Web Application Testing

  Background: eShopOnWeb application is hosted in sytem.
    Given User navigates to site eShopOnWeb
    And user fetch testData for "LoginCredentials" from "webFeatures"
    And user navigate to Login page

  Example: User try to login without username and password.
    When user click on login button
    Then user should get email not present error messages
    And user should get password not present error messages
    And user should close browser

  Example: User try to login without username.
    When User provide password
    And user click on login button
    Then user should get email not present error messages
    And user should close browser

  Example: User try to login without password.
    When User provide email
    And user click on login button
    Then user should get password not present error messages
    And user should close browser

  Example: User try to login with invalid emailID.
    When User provide invalid email
    And User provide password
    And user click on login button
    Then user should get invalid login attempt error messages
    And user should close browser

  Example: User try to login with invalid password.
    When User provide email
    And User provide invalid password
    And user click on login button
    Then user should get invalid login attempt error messages
    And user should close browser

  Example: User try to login with correct username and password.
    When User provide email
    And User provide password
    And user click on login button
    Then User should be logged in
    And user should close browser
    
 Rule: API Testing 
 Background: API login Validations
  
 Example: Post -  Authenticate using valid credentials

Given user fetch testData for "Authenticate_UsingValid_Credentials" from "apiFeatures"
When user builds API request
And user send request
Then user authenticate should be sucessfull
And status coded should be asserted

@Last
Example: Post -  Authenticate using invalid credentials

Given user fetch testData for "Authenticate_UsingInvalid_Credentials" from "apiFeatures"
When user builds API request
And user send request
Then user authenticate should not be sucessfull
And status coded should be asserted
