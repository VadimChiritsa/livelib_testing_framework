Feature: SearchFeature
  The feature deals with the search functionality of the application

  Scenario: Global search on the main page
    Given I get book from properties
    When I input book to the global search field
    Then I should see book on the page

  Scenario: Search on the author page
    Given I navigate to the author page
    When I input "Stephen Edwin King" to the search field by author
    Then I should see "Stephen Edwin King" on the author page

  Scenario Outline: Search on the review page
    Given I navigate to the review page
    When I input "<name of book>" to the search field by review
    Then I should see "<name of book>" on the review page
    Examples:
      | name of book            |
      | The Green Mile          |
      | Бойцовский клуб         |
      | Краткая история времени |

  Scenario: Search in the field of quotes by keyword
    Given I get book name from properties
    And I go to quotes page
    When I enter into the search field book
    Then I see all quotes connected to this book om the page

  Scenario: Search in the field of groups by keyword
    Given I get group name from properties
    And I go to groups page
    When I enter into the search field group
    Then I see all groups connected to this book om the page

  Scenario: Search in the field of selects by keyword
    Given I get selection name from properties
    And I go to selections page
    When I enter into the search field selection
    Then I see all selections connected to this book om the page