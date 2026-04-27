Feature: US03 - Login with Empty Fields Validation
  As a user
  I want to be notified when I leave the username or password field blank
  So that I understand what is required to log in

  # AC-01: No credentials entered - Username is required
  Scenario: AC-01 - Error shown when both fields are empty
    Given the user is on the Swag Labs login page
    When the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Username is required"

  # AC-02: Only username entered - Password is required
  Scenario: AC-02 - Error shown when only username is provided
    Given the user is on the Swag Labs login page
    When the user enters only username "standard_user"
    And the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Password is required"

  # AC-03: Only password entered - Username is required
  Scenario: AC-03 - Error shown when only password is provided
    Given the user is on the Swag Labs login page
    When the user enters only password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Username is required"
