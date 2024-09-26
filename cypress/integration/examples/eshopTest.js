/// <reference types="cypress" />
   

describe('eShop Test Scenario', () => {

    before(function() {
        cy.log('Running setup before all tests');

        // Visiting the eShop URL
        cy.visit('http://localhost:5106/');
        cy.url().should('include', 'localhost:5106'); // Ensure correct URL

        // Load fixture data and bind it to `this` context
        cy.fixture('example').then(function(data) {
            this.data = data; // Assign fixture data to `this.data`
        });
    });

    it('Should log in, add items to the cart, and complete the checkout', function() { 
        cy.log('Starting test: login, add to cart, and checkout');

        // Clicking on identity/login button
        cy.get('.esh-identity-name').click();

        // Verifying that the login form appears
        cy.get('h2').should('be.visible');

        // Entering email from fixture data
        cy.get('#Input_Email').type(this.data.email);
        cy.get('#Input_Email').should('have.value', this.data.email); // Verify the entered email

        // Entering password from fixture data
        cy.get('#Input_Password').type(this.data.password);
        cy.get('#Input_Password').should('have.value', this.data.password); // Verify the entered password

        // Checking the "Remember Me" checkbox
        cy.get('#Input_RememberMe').check().should('be.checked');

        // Verifying "Forgot your password" link is visible
        cy.get('a[href="/Identity/Account/ForgotPassword"]')
            .should('be.visible')
            .and('contain.text', 'Forgot your password');

        // Submitting the login form
        cy.get('button[type="submit"]').click();
        
        // Verifying that login was successful (based on application behavior)

        // Adding .NET Black & White Mug to the cart
        cy.get(':nth-child(2) > form > .esh-catalog-button').click();

        // Verifying the mug is added to the cart
        cy.get('.esh-basket-item.esh-basket-item--middle.col-xs-3').should('be.visible');
        cy.contains('[ Continue Shopping ]').click(); // Click to continue shopping

        // Adding Prism White T-Shirt to the cart
        cy.get(':nth-child(3) > form > .esh-catalog-button').click();

        // Verifying that the total is correct: $8.50 + $12.00 = $20.50
        cy.get(':nth-child(2) > .esh-basket-item--mark')
            .invoke('text')
            .then((text) => {
                const totalFloat = parseFloat(text.replace('$', '').trim()); // Convert the text price to a float
                expect(totalFloat).to.equal(20.50); // Verify the total price is $20.50
            });

        // Proceeding to checkout
        cy.contains('[ Checkout ]').click();

        // Verifying the Review page
        cy.get('h1')
        .should('be.visible')
        .and('have.text', 'Review');

        // Clicking Pay Now
        cy.contains('[ Pay Now ]').click();

        // Verifying the order confirmation page
        cy.get('h1').should('have.text', 'Thanks for your Order!');
    });

    // After hook to clean up after all tests
    after(() => {
        cy.clearCookies(); // Clears all cookies
        cy.clearLocalStorage(); // Clears local storage
        cy.log('All tests completed, cleanup performed');
    });

});
