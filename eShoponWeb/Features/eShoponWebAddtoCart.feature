Feature: Add to Cart selected products

Add to Cart all desired products from dropdown eShoponWeb site


@AddtoCartScenario1
Scenario: Add to cart all filtered products
	Given user is on home page
	And user selects products from dropdown like brand= .NET and type= mug
	When user clicks on Add to cart for filtered products 
	Then User is able to see 1 product

    @AddtoCartScenario2
Scenario: Verify that all filtered products can be added to cart
	Given user is on home page
	And user selects products from dropdown like brand= Azure and type= Shirt
	When user clicks on Arrow button beside dropdowns 
	Then User is able to add product to cart

    @AddtoCartScenario3
Scenario: Verify on home Page when user click my Account , is able to see correct account details
	Given user is on home page
	And user selects dropdown where user mail id is mentioned 
	When user clicks on My Account
	Then User is able to check All Account details