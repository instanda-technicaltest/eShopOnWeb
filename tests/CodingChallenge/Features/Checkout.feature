Feature: User Checkout



Scenario: Confirm a user can perfrom end to end Checkout successfully
	Given I am on the HomePage
	And I select 3 items I like
	When I select my shopping cart
	And select Check out
	And I login with a valid username "demouser@microsoft.com" and password "Pass@word1"
	And I select Pay now 
	Then my order should be processed Successfully

@authenticated
Scenario: Confirm a user can Checkout successfully
	Given I am on the HomePage
	And I select 5 items I like
	When I select my shopping cart
	And select Check out
	And I select Pay now 
	Then my order should be processed Successfully