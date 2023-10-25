#Feature: Testing a REST API with Karate
#
#  Scenario: Testing valid GET endpoint
#    Given url 'https://localhost:5001/api/v1/Catalog/items'
#    When method GET
#    Then status 200