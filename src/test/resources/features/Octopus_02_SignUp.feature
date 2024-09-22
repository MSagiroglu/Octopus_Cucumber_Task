@Signup
Feature: Signup to Octopus

  @SignupPositive @Positive
  Scenario: User can sign up to Octopus with valid credentials
    Given user clicks on the Sign Up button in Home page
    When user enters Full Name into the Full Name field
    And user enters Email Address into the Email Address field
    And user enters Password into the Password field
    And user clicks on the reCAPTCHA checkbox
    And user clicks on the I accept KVKK, Clarification Text checkbox
    And user clicks on the Sign Up button
    And user enters verification code

  #  And user should be redirected to the confirmation page with a successful sign-up message


  @SignupNegative
  Scenario:User cannot become a member without filling in any fields.
    Given user clicks on the Sign Up button in Home page
    When The user clicks the sign up button without filling in any fields.
    Then The user should see |isim gerekli| message  under Full Name field
    Then The user should see |E-posta gerekli| message under Email Address field
    Then The user should see |Şifre gerekli| and |Şifre gereksinimleri karşılamıyor| messagesunder Password field
    Then The user should see |Şifre doğrulaması gerekiyor| message under Confirm Password field
    Then The user should see |captcha gerekli bir alan| message under reCAPTCHA field
    Then The user should see |Şartları kabul etmeniz gerekmektedir| message under I accept KVKK, Clarification Text field

  @SignupNegative
  Scenario: User should enter at least 3 character in Ad - Soyad field
    Given user clicks on the Sign Up button in Home page
    When user enters less than three character into the Full Name field
    Then User should see this message (İsim en az 3 karakter uzunluğunda olmalı) message  under Full Name field

  @SignupNegative
  Scenario Outline: User should not signup with invalid email address
    Given user clicks on the Sign Up button in Home page
    When user enters "<invalid email address>" into the Email Address field
    Then User should see this message (E-posta geçerli formatta olmalı) message  under Email Address field
    Examples:
      | invalid email address |
      | test.com              |
      | test@com              |
      | test.com.tr           |
      | test                  |
      | test@                 |
@SignupNegative
  Scenario: User should not signup with invalid OTP code
    Given user clicks on the Sign Up button in Home page
    Given user clicks on the Sign Up button in Home page
    When user enters Full Name into the Full Name field
    And user enters Email Address into the Email Address field
    And user enters Password into the Password field
    And user clicks on the reCAPTCHA checkbox
    And user clicks on the I accept KVKK, Clarification Text checkbox
    And user clicks on the Sign Up button
    When user enters invalid verification code
    Then User should see this message (Hata) message
