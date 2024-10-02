@UIRegression
Feature: Feature: As a user I expect to test log out functionality of eshoponweb application.

   @Logout
   Scenario: Successfully log out
      Given the user is logged into the application
      When the user clicks the logout button
      Then the user should be logged out
