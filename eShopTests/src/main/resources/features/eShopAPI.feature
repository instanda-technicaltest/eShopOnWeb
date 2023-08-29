@regression
Feature: API Tests for eShop website

  Background: Generate Authorization Token
    Given User authenticates with "demouser@microsoft.com", "Pass@word1"

  Scenario: Send get request for catalog brands
    When User sends get request for catalog brands api
    Then User receives 200 status

  Scenario: Send post request for catalog items
    When User sends post request for catalog items api
    Then User receives 200 status