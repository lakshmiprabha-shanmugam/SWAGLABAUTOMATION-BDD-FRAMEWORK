Feature: US04 - Login with Locked-Out User
  As a system
  I want to prevent locked-out users from logging in
  So that unauthorized or restricted accounts cannot access the application

  # AC-01: Locked-out user sees specific error message
  Scenario: AC-01 - Locked out user sees error message on login attempt
    Given the user is on the Swag Labs login page
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Sorry, this user has been locked out"

  # AC-02: Error persists and access is denied when locked user tries again
  Scenario: AC-02 - Error persists and access denied on repeated login attempt
    Given the user is on the Swag Labs login page
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed
    And the user should remain on the login page
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Sorry, this user has been locked out"
    And the user should remain on the login page
