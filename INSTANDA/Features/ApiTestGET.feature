Feature: API Testing
  As a user
  I want to validate the API response for GET Call

  Scenario: Validate GET API Response
    Given I have the API endpoint "catalog-brands"
    When I send a GET request to the endpoint
    Then the response status code should be 200
    And the response should contain "catalogBrands"
