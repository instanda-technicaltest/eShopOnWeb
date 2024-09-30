Feature: ForgotPassword

As a user On eShopOnWe that forgot password
I want to reset my password
So that I make shopping on the websit

Background:
    Given user navigates to eShopOnWeb
    When user clicks login button
    Then Login page displayed

@tag1
Scenario Outline: To validate forgot password with valid and invalid email
    When user clicks forgot your password
    Then forgot your password page displayed
    When user enters email address "<Email>"
    And user clicks reset password
    Then message should displayed "<Message>"


Examples:
    | TestType | Email               | Message                                         |
    | Valid    | admin@microsoft.com | Please check your email to reset your password. |
    | Invalid  | adminmicrosoft.com  | The Email field is not a valid e-mail address.  |



