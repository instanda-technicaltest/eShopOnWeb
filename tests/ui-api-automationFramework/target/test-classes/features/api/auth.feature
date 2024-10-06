@authApi
Feature: Authentication API
  As a user
  I want to authenticate using the API
  So that I can access protected resources

  @api
  Scenario: Verify that user should authenticate successfully with valid API credentials
    Given authenticate api is running
    When I send a POST request to with username "demouser@microsoft.com" and password "Pass@word1"
    Then the response status code should be 200
    And the response result should be true
    And the response should contain a valid token

  @api
  Scenario: Verify that user authentication fails with invalid API credentials
    Given authenticate api is running
    When I send a POST request to with username "test@microsoft.com" and password "test@pwd"
    Then the response status code should be 200
    And the response result should be false
    And the response should contain empty token
