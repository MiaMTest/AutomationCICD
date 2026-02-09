Feature: login error validation

  @ErrorValidation
  Scenario Outline: Invalid username validation error
    Given I landed on Ecommcerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username         | password  |
      | mmia@hotmail.com | Mia12345! |
