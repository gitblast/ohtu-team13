Feature: User can add a book with valid author and title
    
    Scenario: User can add a book with non-empty data
        Given Books database is initialized
        When Author "Arto Paasilinna", title "Sotahevonen", pages 52 and published 1991 are entered
        Then Database contains entered book

    Scenario: User can't add a book without valid information
        Given Books database is initialized
        When empty author and empty title with 52 pages and published 1991 are entered
        Then Service will return false value

    Scenario: User can't add a book without author's name
        Given Books database is initialized
        When empty author and "Sotahevonen" with 52 pages and published 1991 are entered
        Then Service will return false value

    Scenario: User can't add a book without title
        Given Books database is initialized
        When "Arto Paasilinna" and empty title with 52 pages and published 1991 are entered
        Then Service will return false value