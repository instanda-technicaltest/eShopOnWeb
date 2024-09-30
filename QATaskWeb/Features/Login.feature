Feature: Login

As a user on the eshopOnWeb
I want to login
So that I can do some shopping


Background:
    Given user navigates to eShopOnWeb
    Then eShopOnWeb page displayed successfully
    When user clicks login button
    Then Login page displayed


@tag1
Scenario: To user can login with valid credentials
    When user enters email address "admin@microsoft.com "
    And user enters password "Pass@word1"
    And user ticks remember me checkbox
    And user clicks Log in
    Then user should login successfully


@tag
Scenario Outline: To validate user is unable to login with  invalid credentials
    When user enters email address "<Email>"
    And user enters password "<Password>"
    And user ticks remember me checkbox
    And user clicks Log in
    Then invalid message should displayed "<Messages>"

Examples:
    | LoginType       | Email               | Password   | Messages                                   |
    | InvalidEmail    | adminmicrosoft.com  | Pass@word1 | Email field is not a valid e-mail address. |
    | InvalidPassword | admin@microsoft.com | password1  | Invalid login attempt.                     |
    | EmptyEmail      |                     | Pass@word1 | The Email field is required.               |
    | EmptyPassword   | admin@microsoft.com |            | The Password field is required.            |

	