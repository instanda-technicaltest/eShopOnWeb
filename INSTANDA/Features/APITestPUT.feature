Feature: APITestPUT
  As a user
  I want to validate the API response for PUT Call

@tag1
Scenario: Validate PUT API to update catalog items
	Given I have the API endpoint "catalog-items"
    And I have the updated catalog item data with id 1
      | name                       | description               | price | pictureUri             | catalogTypeId | catalogBrandId |
      | ".NET Bot Black Hoodie"     | ".NET Bot Black Hoodie"   | 20.0  | /images/products/1.png | 2             | 2              |
	When I send a PUT request to the API with this data
	Then the response status code should be 200
    And the response should contain "success"


