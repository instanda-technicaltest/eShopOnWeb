Feature: Login tests
  As a User, I can login to eShopOnWeb

  Scenario Outline: Login as non admin user to eShopOnWeb using valid email and password
    Given I am on the Catalog page
    When I navigate to login page
    And I input "<EMAIL>" and "<PASSWORD>" to login page
    And I click on login button
    Then I am logged in successfully

    Examples:
      | EMAIL                  | PASSWORD   |
      | demouser@microsoft.com | Pass@word1 |

  Scenario Outline: Login Failure if a non admin user try to login to eShopOnWeb using in-valid email and password
    Given I am on the Catalog page
    When I navigate to login page
    And I input "<EMAIL>" and "<PASSWORD>" to login page
    And I click on login button
    Then validation error is shown

    Examples:
      | EMAIL              | PASSWORD   |
      | fail@microsoft.com | Pass@word1 |

  Scenario Outline: Login as admin user to eShopOnWeb using valid email and password
    Given I am on the Catalog page
    When I navigate to login page
    And I input "<EMAIL>" and "<PASSWORD>" to login page
    And I click on login button
    Then I am logged in successfully
    Examples:
      | EMAIL               | PASSWORD   |
      | admin@microsoft.com | Pass@word1 |
