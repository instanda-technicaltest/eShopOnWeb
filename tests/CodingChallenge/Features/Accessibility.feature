Feature: Accessibility
 
    
  Scenario: Pages should be compliant to Accessibility rules
      Given I am on the HomePage
      And I scan the page for accessibility violations 
      Then I expect there to be no violations