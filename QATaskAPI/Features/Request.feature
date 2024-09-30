Feature: Request

A short summary of the feature

@tag1
Scenario: To verify user can authenticate and make a POST and DELETE request
    Given I authenticate with valid credentials
    And I construct "POST" for "/api/catalog-items"
    When I set header "Authorization" to "Bearer {token}"
    And I pass body "{"catalogBrandId": 4,"catalogTypeId": 2,"description": "love this call","name": "thhds","pictureUri": "string","pictureBase64": "string","pictureName": "string","price": 0}"
    When I submit a request
    And I extract the "id" from response into "externalId"
    Then I expect statuscode to be "201"
    Given I authenticate with valid credentials
    And I construct "DELETE" for "/api/catalog-items/{externalId}"
    When I set header "Authorization" to "Bearer {token}"
    When I submit a request
    Then I expect statuscode to be "200"
  

Scenario: To validate user can Get catelog-brands
    Given I construct "GET" for "/api/catalog-brands"
    When I submit a request
    Then I expect statuscode to be "200"
    And I expect response content to be "{"catalogBrands":[{"id":1,"name":"Azure"},{"id":2,"name":".NET"},{"id":3,"name":"Visual Studio"},{"id":4,"name":"SQL Server"},{"id":5,"name":"Other"}]}"
	

Scenario: Validate user can Get catalog items with id
    Given I authenticate with valid credentials
    And I construct "POST" for "/api/catalog-items"
    When I set header "Authorization" to "Bearer {token}"
    And I pass body "{"catalogBrandId": 4,"catalogTypeId": 2,"description": "love this call","name": "tggvds","pictureUri": "string","pictureBase64": "string","pictureName": "string","price": 0}"
    When I submit a request
    And I extract the "id" from response into "externalId"
    Then I expect statuscode to be "201"
    Given I construct "GET" for "/api/catalog-items/{externalId}"
    When I submit a request
    And I extract the "id" from response into "externalId"
    Then I expect statuscode to be "200"
    Given I construct "DELETE" for "/api/catalog-items/{externalId}"
    When I set header "Authorization" to "Bearer {token}"
    When I submit a request
    Then I expect statuscode to be "200"
 

Scenario: To validate user can Get catalog items
    Given I construct "GET" for "/api/catalog-items?pageSize=6&pageIndex=4&catalogBrandId=7&catalogTypeId=8"
    When I submit a request
    Then I expect statuscode to be "200"
    And I expect response content to be "{"catalogItems":[],"pageCount":0}"


Scenario: To validate user can Get catalog-types
    Given I construct "GET" for "/api/catalog-brands"
    When I submit a request
    Then I expect statuscode to be "200"
    And I expect response content to be "{"catalogBrands":[{"id":1,"name":"Azure"},{"id":2,"name":".NET"},{"id":3,"name":"Visual Studio"},{"id":4,"name":"SQL Server"},{"id":5,"name":"Other"}]}"
	
	
Scenario: To validate user can update catalog-items
    Given I authenticate with valid credentials
    And I construct "POST" for "/api/catalog-items"
    When I set header "Authorization" to "Bearer {token}"
    And I pass body "{"catalogBrandId": 4,"catalogTypeId": 2,"description": "love this call","name": "tdhhhhhhhhbnbns","pictureUri": "string","pictureBase64": "string","pictureName": "string","price": 0}"
    When I submit a request
    Then I expect statuscode to be "201"
    Given I construct "PUT" for "/api/catalog-items"
    When I set header "Authorization" to "Bearer {token}"
    When I pass body "{"catalogBrandId": 4,"catalogTypeId": 5,"description": "love this call","name": "Technical","pictureUri": "string","pictureBase64": "string","pictureName": "string","price": 21.21}"
    And I submit a request
    And I extract the "id" from response into "externalId"
    Then I expect statuscode to be "200"
    Given I construct "DELETE" for "/api/catalog-items/{externalId}"
    When I set header "Authorization" to "Bearer {token}"
    When I submit a request
    Then I expect statuscode to be "200"
