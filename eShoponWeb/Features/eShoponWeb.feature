Feature: eshoponWeb

Simple calculator for adding **two** numbers

Link to a feature: [Calculator](eShoponWeb/Features/Calculator.feature)
***Further read***: **[Learn more about how to generate Living Documentation](https://docs.specflow.org/projects/specflow-livingdoc/en/latest/LivingDocGenerator/Generating-Documentation.html)**

@PositiveLoginScenario
Scenario: LogintoeShoponWeb page
	Given user is on landing page
	And user enter clicks on login button
	When user enter emailid and password
	Then user should be able to login and navigate on home page

    @NegativeLoginScenario-1
Scenario: Enter Incorrect username/password
	Given user is on landing page
	And user enter clicks on login button
	When user enter Incorrect emailid or password
	Then user should not be able to login and get error message - Invaid credentials

    @NegativeLoginScenario-2
Scenario: Do not enter username or password
	Given user is on landing page
	And user enter clicks on login button
	When user does not enter emailid or password
	Then user should not be able to login and get error message 