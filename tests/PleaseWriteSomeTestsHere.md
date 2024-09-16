# Please write some Automated Tests!
# Disclaimer: I am unable to run locally due to work security, so I implemented tests based on visual of how I would expect the application to run. 

Test Approach 
Login Functionality: - Valid login credentials allowing the user to access the page 
                     -  Invalid login credentials displays an error

Add to Cart: Items can be added to cart and total cart prices updates correctly and automatically 

Product Search/Filter: Users can search for products or filter products based on brands or type and the correct results are visible

Checkout Process: Complete a purchase, display and verify confirmation page.

Order History: Check that users can view their order history after a purchase 

Tool we are using: Playwright as it mimiics uses actions and checks that the web application works as intended. 

Programming language: Java, Cucumber BDD Playwright and NUnit. 

Type of test: Functional Testing - Login feature with valid and invalid credentaials (Negative testing - incorrect email or password and an error message appears)
                                 - Add to Cart (Negative testing - attempt to checkout with an empty cart and user cannot proceed)

Edge Cases to think off: - Large bulk of orders (what happens when you add a large amount of items to the basket?)
                         - Multiple Sessions (will the cart still update with the right amount when accessed from different browsing sessions?) 

Think of non-functional testings as well such as will the website still perform well when multiple users are checking out at the same time? Think of security as well card payments and login forms. 