@Screen
Feature: User should be able to add a new screen
@ScreenPositive @Positive
    Scenario: User should be able to add a new screen
      Given User enters valids email
      And User enters valids password
      When User clicks on Sign In button
      And User Enter Screens Page
      And User clicks Create Screen Button
      And User enters Save ID
      And User enters Screen Name
      And User selects Program Vertical
      And User selects Emergency Program
      And User selects Screen Type LED
      And User Enter Tag
      And User selects Event Planning
      And User selects Routings
      And User selects Ratio
      And User selects Organization
      And User selects Advanced
      And User selects Sync Settings
      And User clicks Position and Size
      And User selects Display Screen
      And User clicks Lift & Learn
      When User clicks Save Button
      Then User should be able to add a new screen

  @ScreenNegative
  Scenario:User should not create screen without required data
  Given User enters valids email
  And User enters valids password
  When User clicks on Sign In button
  And User Enter Screens Page
  And User clicks Create Screen Button
  And User enters Screen Name
  And User selects Program Vertical
  And User selects Emergency Program
  And User selects Screen Type LED
  And User Enter Tag
  And User selects Event Planning
  And User selects Routings
  And User selects Ratio
  And User selects Organization
  And User selects Advanced
  And User selects Sync Settings
  And User clicks Position and Size
  And User selects Display Screen
  And User clicks Lift & Learn
  When User clicks Save Button
  Then User should not be able to add a new screen














