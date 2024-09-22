Feature: TotalProducts

A short summary of the feature

@totalproducts
Scenario: Check the total products available 
	Given Load the URL
	When the home page loads
	Then I should check the total number of products available
