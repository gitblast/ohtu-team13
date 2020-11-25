Feature: User can add an URL with valid header and URL

    Scenario: User can add URL with non-empty header and URL
        Given Database is initialized
        When  URL "www.google.fi" and header "google" are entered
        Then  Database contains entered data

    Scenario: User can not add an URL with empty header
            Given Database is initialized
            When URL "www.google.fi" and empty header are entered
            Then Service will return value false

    Scenario: User can not add an header with empty URL
            Given Database is initialized
            When Header "google" and empty URL are entered
            Then Service will return value false
