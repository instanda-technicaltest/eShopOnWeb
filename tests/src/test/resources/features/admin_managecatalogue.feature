#Features to be tested
  #Admin can create and manage the catalogue
@admin
Feature: Admin page functionality

  Background:
    Given user is logged with admin "admin@microsoft.com" and password "Pass@word1"

  Scenario: Admin can create a new product
    When user hovers on drop down menu
    And user clicks on "Admin" button from menu
    Then user is on "Admin" page
    And user can logout from the application
    And user is on "Login" page
    And "admin@microsoft.com" do not appear in header








