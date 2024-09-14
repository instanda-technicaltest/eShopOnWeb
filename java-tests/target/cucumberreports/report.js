$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("operations.feature");
formatter.feature({
  "line": 1,
  "name": "full Operation",
  "description": "",
  "id": "full-operation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1568761500,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "login",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "I open the website",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I click on login button",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "I enter the email \"admin@microsoft.com\" and password \"Pass@word1\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I click on Log in button",
  "keyword": "And "
});
formatter.match({
  "location": "Step.iOpenTheWebsite()"
});
formatter.result({
  "duration": 221519200,
  "status": "passed"
});
formatter.match({
  "location": "Step.iClickOnLoginButton()"
});
formatter.result({
  "duration": 76861401,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "admin@microsoft.com",
      "offset": 19
    },
    {
      "val": "Pass@word1",
      "offset": 54
    }
  ],
  "location": "Step.iEnterTheEmailAndPassword(String,String)"
});
formatter.result({
  "duration": 205894100,
  "status": "passed"
});
formatter.match({
  "location": "Step.iClickOnLogInButton()"
});
formatter.result({
  "duration": 99460000,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Journey of product to cart",
  "description": "",
  "id": "full-operation;journey-of-product-to-cart",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I select T-Shirt from dropdown menu",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I add .NET FOUNDATION SWEATSHIRT into cart",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "I make quantity as \"1\"",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I click on update button and verify the amount",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "I click On checkout button",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "I click on Pay Now Button",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I verify the Success message",
  "keyword": "Then "
});
formatter.match({
  "location": "Step.iSelectTShirtFromDropdownMenu()"
});
formatter.result({
  "duration": 146010401,
  "status": "passed"
});
formatter.match({
  "location": "Step.iAddNETFOUNDATIONSWEATSHIRTIntoCart()"
});
formatter.result({
  "duration": 106656199,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 20
    }
  ],
  "location": "Step.iMakeQuantityAs(String)"
});
formatter.result({
  "duration": 101204799,
  "status": "passed"
});
formatter.match({
  "location": "Step.iClickOnUpdateButtonAndVerifyTheAmount()"
});
formatter.result({
  "duration": 10160484000,
  "status": "passed"
});
formatter.match({
  "location": "Step.iClickOnCheckoutButton()"
});
formatter.result({
  "duration": 5658944999,
  "status": "passed"
});
formatter.match({
  "location": "Step.iClickOnPayNowButton()"
});
formatter.result({
  "duration": 5637390500,
  "status": "passed"
});
formatter.match({
  "location": "Step.iVerifyTheSuccessMessage()"
});
formatter.result({
  "duration": 75806200,
  "status": "passed"
});
formatter.after({
  "duration": 160359500,
  "status": "passed"
});
});