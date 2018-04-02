Feature: LoginFeature
  The feature deals with the login functionality of the application

  Scenario: Login with correct username and incorrect password
    Given I click sign in button
    And I enter username as "testname" and password as "password"
    And I click login button
    And I close alert about incorrect data
    And I close sign in form
    Then I shouldn't see the user profile

  Scenario: Login with incorrect username and correct password
    Given I click sign in button
    And I enter username as "name" and password as "testpassword"
    And I click login button
    And I close alert about incorrect data
    And I close sign in form
    Then I shouldn't see the user profile

  Scenario: Login with correct login and correct password
    Given I click sign in button
    And I enter username as "testname" and password as "testpassword"
    And I click login button
    Then I should see the user profile