@ios
Feature: Test iOS application

  Scenario: Verify application alert on iPhone 8
    Given I have a iPhone 8 device
    When I show the alert
    Then I verify the alert shows this alert is so cool.
    And I accept the alert

  Scenario: Verify application alert on iPhone 12
    Given I have a iPhone 12 device
    When I show the alert
    Then I verify the alert shows this alert is so cool.
    And I accept the alert