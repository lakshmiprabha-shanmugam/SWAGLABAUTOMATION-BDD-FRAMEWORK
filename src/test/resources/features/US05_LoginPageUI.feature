Feature: US05 - Login Page UI and Accessibility
  As a user
  I want the login page to be clearly laid out and accessible
  So that I can easily find and use the login form on any device

  # AC-01: All required form elements are present
  Scenario: AC-01 - Login page displays username field, password field, and login button
    Given the user is on the Swag Labs login page
    Then the username field should be visible
    And the password field should be visible
    And the login button should be visible

  # AC-02: Password field masks entered characters
  Scenario: AC-02 - Password field masks entered characters
    Given the user is on the Swag Labs login page
    Then the password field should be of type "password"

  # AC-03: Form is usable on desktop and mobile viewports
  Scenario: AC-03 - Login form is responsive and usable on desktop viewport
    Given the user is on the Swag Labs login page
    Then the username field should be visible
    And the password field should be visible
    And the login button should be visible
    When the browser is resized to mobile viewport
    Then the username field should be visible
    And the password field should be visible
    And the login button should be visible

  # AC-04: Placeholder text visible in both input fields before typing
  Scenario: AC-04 - Placeholder text is visible in both input fields
    Given the user is on the Swag Labs login page
    Then the username field should have placeholder "Username"
    And the password field should have placeholder "Password"

  # AC-05: Form can be submitted using the Enter key
  Scenario: AC-05 - User can submit the login form using the Enter key
    Given the user is on the Swag Labs login page
    When the user enters username "standard_user" and password "secret_sauce"
    And the user submits the form using the Enter key
    Then the user should be redirected to the inventory page
