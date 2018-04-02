Feature: RegistrationFeature
  The feature deals with the registration functionality of the application

  Scenario: Register with incorrect email
    Given I get password from properties
    And I click registration button
    And I enter incorrect email as "login" and password
    And I click register button
    And I close alert
    And I close registration form
    Then I shouldn't see user menu or email popup

  Scenario: Register with correct email
    Given I get password from properties
    And I generate random email
    And I click registration button
    And I enter email and password
    And I click register button
    Then I should see user menu or email popup