Feature: Register Flow Test Suite

  Scenario: Register Page can be accessed from the Home Page
    Given HomePage is displayed
    When registerLink from Header menu is clicked
    Then the current url contains "route=account/register" keyword

    Scenario: The Account Page URL is opened when the registration is successful
      Given "/" endpoint is accessed
      And registerLink from Header menu is clicked
      When the register form is populated with valid random data
      Then the current url contains "route=account/success" keyword
