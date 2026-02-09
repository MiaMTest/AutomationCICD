Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommcerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples:
      | username        | password  | productName     | countryName |
      | mia@hotmail.com | Mia12345! | ADIDAS ORIGINAL | India       |
