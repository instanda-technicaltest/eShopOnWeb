@catalogBrandApi
Feature: CatalogBrand api

  @api
  Scenario: Verify catalog brands are retrieved successfully
    Given eShop CatalogBrand API is running
    When I send a GET request to the CatalogBrand endpoint
    Then I must receive a valid status code 200
    And The response content type should be "application/json"
    And The response body should contain the following catalog brands:
      | id | name          |
      | 1  | Azure         |
      | 2  | .NET          |
      | 3  | Visual Studio |
      | 4  | SQL Server    |
      | 5  | Other         |