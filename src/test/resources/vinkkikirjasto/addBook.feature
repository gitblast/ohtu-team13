Feature: User can add a book with valid author and title
    
    Scenario: User can add a book with non-empty author and title
        Given Books Database is initialized
        When Author "Arto Paasilinna" and title "Sotahevonen" are entered
        Then Database contains entered book