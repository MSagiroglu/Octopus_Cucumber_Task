@Login
Feature: Sign In To Octopus Website
@LoginPositive @Positive
  Scenario: User should be able to sign in successfully
    Given User enters valids email
    And User enters valids password
    When User clicks on Sign In button
    Then User should be able to sign in successfully

  Scenario:User should not be able to sign in without credentials
    Given User clicks on Sign In button
    Then User should not be able to sign in


