Feature: Swag Lab Test

  @jm
  Scenario: Error message shown when using incorrect log in credentials
   Given the user has navigated to the website
    When incorrect login details are entered
    Then the "Username and password do not match any user in this service" message is displayed
 
  Scenario Outline: Purchase an item and ensure confirmation message is displayed
   Given the user has logged into the application
    When a "<Item>" is added to my basket
     And the shopping cart is viewed
     And the item is purchased
    Then the "Thank you for your order!" message is displayed
     And the user logs out
     
    Examples: 
     | Item                     |
     | Sauce Labs Backpack      |
     | Sauce Labs Bolt T-Shirt  |
     | Sauce Labs Fleece Jacket |
     
     
  Scenario Outline: Ensure item price is correct before purchase
   Given the user has logged into the application
    When a "<Item>" is added to my basket
     And the shopping cart is viewed
     And the item price is stored
     And the checkout information is completed
    Then the correct item price is displayed
     And the user logs out
     
    Examples: 
     | Item                     |
     | Sauce Labs Backpack      |
     | Sauce Labs Bolt T-Shirt  |
     | Sauce Labs Fleece Jacket |