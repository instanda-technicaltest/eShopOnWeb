Feature: End User Test

  @E2E @All
  Scenario: User place an order(e2e)
    Given as a User, Login Enter URL "eShopOnWeb_URL" and redirected to home page
    When  click on login button and redirected to login page
    And   login page loaded, Enter email as "user_email" and password as "user_password"
    And   click on Login button
    And   validate user has successfully logged in
    And   on home page, select Brand as ".NET" and select Type as "Mug"
    And   click on Arrow button to filter the result
    And   check number of products available on the page
    And   click on Add to Basket button and redirected to Basket page
    And   check basket icon updated with quantity
    And   check product available in the basket
    And   change existing quantity and click on update button
    And   check basket icon updated with quantity
    And   click on Continue Shopping button and redirected to home page
    And   click on Add to Basket button and redirected to Basket page
    And   check basket icon is updated with quantity
    And   check multiple products available in the basket
    And   validate total amount
    And   click on Checkout button and redirected to checkout page
    And   under Review section, Validate Product, Price, Quantity and Price
    And   click on Pay Now button and redirected to success page
    And   check message has displayed
    """
    Thanks for your Order!
    """
