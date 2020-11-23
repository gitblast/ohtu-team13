Feature: User can add an URL with valid header and URL

    Scenario: User can add URL with non-empty header and URL
    Given: Database is initialized
    When: URL "www.google.fi" and header "google" are entered
    Then: Database contains entered data
