Feature: Searchproduct

Search specific product

@Searchproduct
Scenario: search specific product function working fine
	Given Load the home page
	When I select product filter
	Then I select specific product
	Then It should show result
