Feature: Smoke Test
    check all pages

    @Smoke @All
    Scenario: User able to login successfully and go to different pages
    Given as a User, Login Enter URL "eShopOnWeb_URL" and redirected to home page
    When  click on login button and redirected to login page
    And   login page loaded, Enter email as "user_email" and password as "user_password"
    And   click on Login button
    And   validate user has successfully logged in
    And   from identity drop down and click on My Account and redirected to My Account page
    And   validate user successfully landed on  My Account Page
    And   from identity drop down and click on My Order and redirected to My Order Page
    And   validate user successfully landed on My Orders Page
    And   from identity drop down and click on Logout and redirected to Home page
    And   validate user has successfully logout and landed on Home page
