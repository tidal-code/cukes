Feature: Cucumber Global Hooks

  @p-t
  Scenario: Scenario test one
    When I open google
    Then Then I can see google homepage

  @p-t @p-t2
  Scenario: Scenario test two failing scenario
    When I open bing homepage
    Then Then I can see bing homepage

  @p-t
  Scenario: Scenario test three
    When I open google
    Then this is a skipped test

  @p-t
  Scenario: Scenario test four failing scenario
    When I open google
    Then Then I can see bing homepage with assertion error

  @p-t
  Scenario: Scenario test five failing locator
    When I open google
    Then Then I cannot find input locator

  @p-t
  Scenario: Scenario test six failing assert throws
    When I open google
    Then Assertion error is thrown

  @p-t
  Scenario: Scenario test seven failing assert junit type
    When I open google
    Then Assertion error Junit type is thrown

  @p-t
  Scenario: Scenario test eight failing assert junit type
    When I open google
    Then Runtime Exception is thrown

  @p-t
  Scenario: Scenario test nine failing expected condition
    When I open google
    Then Expected condition error is thrown