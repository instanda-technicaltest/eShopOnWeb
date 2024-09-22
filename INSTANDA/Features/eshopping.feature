Feature: eshopping

This is to verify successful shoppping of an item

@eshopping
Scenario: verify successful eshopping
	Given successfully login to the eshopping site
	When I add an item to the basket
	Then I click checkout button
	Then I click paynow button
	Then I should see order success message
	Then I logout from the site

