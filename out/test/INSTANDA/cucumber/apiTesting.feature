Feature: Validate the user journey using API

  @Smoke
  Scenario: verify get all catalogBrands
    When user execute getCatalogBrands
    Then the response code is "200"

  @Smoke
  Scenario: verify get specific catalogBrands
    When user execute getCatalogItem by id "1"
    Then the response code is "200"

  @Smoke
  Scenario: verify user creates new category item
    Given user acquire the token
    When user execute postCatalogItem with id "100"
    Then the response code is "200"
