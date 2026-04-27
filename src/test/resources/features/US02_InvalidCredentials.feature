Feature: US02 - Login Failure with Invalid Credentials
  As a user
  I want to see a clear error message when I enter incorrect credentials
  So that I know my login attempt has failed and can try again

  # AC-01: Error message displayed when invalid credentials are entered
  Scenario: AC-01 - Error message shown for invalid username and password
    Given the user is on the Swag Labs login page
    When the user enters username "invalid_user" and password "wrong_password"
    And the user clicks the login button
    Then an error message should be displayed
    And the error message should contain "Username and password do not match any user in this system"

  # AC-02: User remains on login page and password field is cleared after failure
  Scenario: AC-02 - User stays on login page and password field is cleared
    Given the user is on the Swag Labs login page
    When the user enters username "invalid_user" and password "wrong_password"
    And the user clicks the login button
    Then the user should remain on the login page
    And the password field should be cleared

  # AC-03: User can correct credentials and login successfully after failure
  Scenario: AC-03 - User successfully logs in after correcting invalid credentials
    Given the user is on the Swag Labs login page
    When the user enters username "invalid_user" and password "wrong_password"
    And the user clicks the login button
    Then an error message should be displayed
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page
