Feature: eShoponWebPaymentPage

User navigation on Update,ContinueShopping and|Paynow button and respective flows

@Update button flow
Scenario: LogintoeShoponWeb page
	Given user is on Payment page
	When user clicks on Update button
	Then user should be able to update quantity or make any changes to selected product

    @Continue Shopping button flow
Scenario: LogintoeShoponWeb page
	Given user is on Payment page
	When user clicks on Continue Shopping button
	Then user should be able to land on home page and continue shopping


    @PayNow button flow
Scenario: LogintoeShoponWeb page
	Given user is on Payment page
	When user clicks on PayNow button
	Then user should be able make payment and see thanks message and orders placed

