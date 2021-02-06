Feature: Testing the Github users API

  Scenario: Querying about myself
    When I query Github for username "BackpackTrainer"
    Then I get results
      | Name     | Bill Fairfield      |
      | Location | Crawfordsville, IN  |
      | Company  | Fairfield & Company |
