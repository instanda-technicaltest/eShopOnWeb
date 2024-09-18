Feature: E-Shop API

@ApiTest
Scenario Outline: Confirm Valid Authenication Request
	Given I make a valid login request to the Api "<username>" "<password>"
	Then the request should be successful

	Examples: 
	| username               | password   |
	| demouser@microsoft.com | Pass@word1 |
	| admin@microsoft.com    | Pass@word1 |

@ApiTest
Scenario Outline: Confirm Invalid Authenication Request
	Given I make a invalid login request to the Api "<username>" "<password>"
	Then the request should be not successful

	Examples: 
	| username               | password   |
	| demouser@microsoft.com | invalid    |
	| invalidEmail           | Pass@word1 |  