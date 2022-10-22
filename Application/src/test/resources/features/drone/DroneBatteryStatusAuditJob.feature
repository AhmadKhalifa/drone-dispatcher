Feature: Periodic job for logging all drones' battery level

  Background:
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D-441-02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D-441-03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | DELIVERED  |
      | 6 | D6 | D-441-06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |

  Scenario: Job iteration
    Given current time is "10:30:00"
    When battery audit job starts
    Then the following logs should be saved in battery audit history
      | # | Drone ID | Battery capacity | Checked at |
      | 1 | D1       | 94               | 10:30:00   |
      | 2 | D2       | 21               | 10:30:00   |
      | 3 | D3       | 52               | 10:30:00   |
      | 4 | D4       | 12               | 10:30:00   |
      | 5 | D5       | 60               | 10:30:00   |
      | 6 | D6       | 84               | 10:30:00   |
