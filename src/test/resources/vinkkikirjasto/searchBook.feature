@ignore
Feature: User can search a book with valid author, title or ISBN

    @ignore
    Scenario: User can search and find a book with a valid author
        Given Books database is initialized and author "Arto Paasilinna", title "Sotahevonen", pages 52 and published 1991 are entered
        When User selects Author and enters "Arto Paasilinna"
        Then Page shows author "Arto Paasilinna" book