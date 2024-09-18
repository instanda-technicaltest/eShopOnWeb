Feature: User Login


Scenario Outline: Successful Login
	Given I Navigate to the login page
	When I login with a valid username "<username>" and password "<password>"
	Then I should be logged in successfully "<username>"

	Examples: 
	| username               | password   |
	| demouser@microsoft.com | Pass@word1 |
	| admin@microsoft.com    | Pass@word1 |


Scenario Outline: Unsuccessful Login
	Given I Navigate to the login page
	When I login with an invalid username "<username>" and password "<password>"
	Then I should not be logged in "<username>"
	And I should be presented with an error message "<errorMessage>"

	Examples: 
	| username               | password    | errorMessage                                   |
	| demouser@microsoft.com | invalidPass | Invalid login attempt.                         |
	| invalidemail           | Pass@word1  | The Email field is not a valid e-mail address. |  