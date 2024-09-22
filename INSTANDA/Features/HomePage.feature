Feature: HomePage

Background:
	Given The eshop URL
	When I pass it on browser and hit enter

@HomePage
Scenario: Verify eshop homepage load fine	
	Then the page should load fine

Scenario Outline: verify login fine
	When I click Lgoin button
	Then I enter "<email>" in Emil field
	Then I enter "<password>" in passord field
	Then login should be success
	Then I should logout from the site

Examples:
      | email                      | password      |
      | demouser@microsoft.com     | Pass@word1    |
     

