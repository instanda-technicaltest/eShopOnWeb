Feature: Placing an order

A feature file for placing an order

@uat
Scenario: Purchase two T-shirts from eShopOnWeb online retail store
	Given eShopOnWeb application available at "https://localhost:44315/"
	When I click login
	Then I should land on Log in page
	When I enter valid user credentials
	And I click Login button
	Then I should land on shopping items page
	When I filter shopping items for T-Shirt type
	And click on "ADD TO BASKET" button
	Then I should see shopping cart page with T-Shirt item
	When I change the item count to 2
	And click on "UPDATE" button
	Then I should see shopping cart page with 2 T-Shirt items
	When click on "CHECKOUT" button
	Then I should see cart review page T-Shirt items
	When click on "PAY NOW" button
	Then I should see "Thanks for your Order!" confirmation message
