Feature: Load medications to a specific drone

  Background:
    Given the following drones are registered
      | # | ID | Serial number | Model          | Weight limit | Battery capacity | State      |
      | 1 | D1 | D-441-01      | LIGHT_WEIGHT   | 100          | 94               | IDLE       |
      | 2 | D2 | D-441-02      | MIDDLE_WEIGHT  | 200          | 21               | LOADING    |
      | 3 | D3 | D-441-03      | MIDDLE_WEIGHT  | 300          | 52               | LOADED     |
      | 4 | D4 | D-441-04      | CRUISER_WEIGHT | 400          | 12               | DELIVERING |
      | 5 | D5 | D-441-05      | HEAVY_WEIGHT   | 500          | 60               | IDLE  |
      | 6 | D6 | D-441-06      | CRUISER_WEIGHT | 350          | 84               | RETURNING  |
    And the following medications are registered
      | # | ID | Name              | Serial number    | Model          | Weight | Image ID |
      | 1 | M1 | Aminophylline     | M_432_512_547_01 | LIGHT_WEIGHT   | 25     | I1       |
      | 2 | M2 | Amyl              | M_432_512_547_02 | MIDDLE_WEIGHT  | 30     | I2       |
      | 3 | M3 | Calcium Gluconate | M_432_512_547_03 | MIDDLE_WEIGHT  | 100    | I3       |
      | 4 | M4 | Chlorpheniramine  | M_432_512_547_04 | CRUISER_WEIGHT | 40     | I4       |
      | 5 | M5 | Digoxin           | M_432_512_547_05 | HEAVY_WEIGHT   | 120    | I5       |

  Scenario: 1. Load medications to an existing drone (medications weight <= drone max weight)
    When user requests to load the following medications on drone "D5"
      | Medication ID | Quantity |
      | M1            | 2        |
      | M2            | 3        |
      | M3            | 1        |
    Then drone "D5" state should be "LOADING" then "LOADED"
    And drone "D5" is loaded with the following medications
      | Medication ID | Quantity |
      | M1            | 2        |
      | M2            | 3        |
      | M3            | 1        |

  Scenario: 2. Load medications to an existing drone (medications weight > drone max weight)
    When user requests to load the following medications on drone "D1"
      | Medication ID | Quantity |
      | M1            | 2        |
      | M2            | 3        |
      | M3            | 1        |
    Then an error is returned with status code 400 with a message "This drone can't carry these medications"


  Scenario: 3. Load medications to a non-existing drone
    When user requests to load the following medications on drone "D8"
      | Medication ID | Quantity |
      | M1            | 2        |
      | M2            | 3        |
      | M3            | 1        |
    Then an error is returned with status code 404 with a message "Drone not found"


  Scenario: 3. Load medications that don't exist
    When user requests to load the following medications on drone "D8"
      | Medication ID | Quantity |
      | M1            | 2        |
      | M8            | 3        |
      | M3            | 1        |
    Then an error is returned with status code 404 with a message "Medication not found"
