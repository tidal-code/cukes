@regression @z-result
Feature: Search Interface Feature

  This is a comment section. You can add whatever you like here.

  Background:
    Given I open the search interface

  @regression @Sprint-1 @release-1 @JD-T1 @JD-T2
  Scenario Outline: Search should give results for search Term 'Selenium'
    When I type the search term 'Selenium'
    Then I should see the relevant result for 'Selenium'
    Examples:
    |name |
    |one |
    | two |

  @release-1 @JD-T3
  Scenario: Search should give results for search Term 'Cucumber'
    When I type the search term 'Cucumber'
    Then I should see the relevant result for 'Cucumber'
    Then Assertion error is thrown

@sprint-2 @JD-T4 #@JD-T25
Scenario Outline: Search should give results for search Terms '<SearchTerm>'
    When I type the search term '<SearchTerm>'
    Then I should see the relevant result for '<Result>'
    Examples:
      | SearchTerm | Result   |
      | Cucumber   | Cucumber |
#      | Selenium   | Selenium |