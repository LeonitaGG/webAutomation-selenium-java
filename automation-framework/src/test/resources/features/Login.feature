@login @regression

Feature: Webdriver University - Validate login flow

  Background:
    Given I access the webdriver university login portal page

  Scenario Outline: Validate - Successful & Unsuccessful login
    When I enter a username <username>
    And I enter a password <password>
    And I click on the login button
    Then I should be presented with a login validation message <loginValidationMessage>
    Examples:
      | username  | password     | loginValidationMessage |
      | webdriver | webdriver123 | validation succeeded   |
      | webdriver | webdriver346 | validation failed      |
