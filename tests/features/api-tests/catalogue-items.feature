@APIRegression
Feature: As a user I expect to Create,Update,Retrieve Catalog Item, with negative testing coverage for create catalog item.

  @create-catalog-item
  Scenario: Create a new catalog item
    Given I send a POST request to "/catalog-items" with valid data
    Then the status code should be "201"
    And the response should contain the newly created item

  @update-catalog-item
  Scenario: Update an existing catalog item
    Given I send a PUT request to "/catalog-items" with updated data
    Then the status code should be "200"
    And the response should contain the updated item details

  @get-catalog-item
  Scenario: Retrieve a catalog item
    Given I send a GET request to "/catalog-items" with catalogBrandId "1" and catalogTypeId "2"
    Then the status code returned should be "200"
    And the response should contain catalog items and should not be empty

  @negative-create-catalog-item-400
  Scenario: Fail to create a catalog item due to missing required data
    Given I send a POST request to "/catalog-items" with missing data
    Then the status code returned should be "400"

  @negative-create-catalog-item-500
  Scenario: Fail to create a catalog item due to missing required data
    Given I send a POST request to "/catalog-items" with invalid data
    Then the status code returned should be "500"


