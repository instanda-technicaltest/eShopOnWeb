Feature: Product Filter Field and Result validations
  This file filters product page with different combinations and validate the filtered products with the expected set of products.

  Background: eShopOnWeb application is hosted in sytem.
    Given User navigates to site eShopOnWeb

  Scenario Outline: User Filters products with <Brand> and <Type> values and validate results.
    When user fetch testData for <ScenarioName> from "webFeatures"
    And user filters product page
    Then user should Validate count of products displayed in label
    And user should Validate name of the product displayed 
    And user should Validate price of the product displayed
    And user should close browser

    Examples: 
      | Brand | Type  | ScenarioName                 |
      | Other | Mug   | "FilterValidation_Other_Mug" |
      | All   | Sheet | "FilterValidation_All_Sheet" |

  @Last
  Scenario: User Filters products with dotnet and Mug values values and validate results.
    When user fetch testData for "FilterValidation_DotNet_Mug" from "webFeatures"
    And user filters product page
    Then user should Validate count of products displayed in label
    And user should Validate name of the product displayed
    And user should Validate price of the product displayed
    And user should close browser
