@ios
Feature: Test iOS application

  Scenario Outline: Verify application alert on device <device>
    Given I launch the app
    When I show the alert
    Then I verify the alert shows this alert is so cool.
    And I accept the alert

    Examples:
      | device |
      | 1      |
      | 2      |
      | 3      |
      | 4      |
      | 5      |