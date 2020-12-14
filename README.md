# Lukuvinkit (ohtu-team13)

![Github Actions](https://github.com/gitblast/ohtu-team13/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/gitblast/ohtu-team13/branch/main/graph/badge.svg?token=MZPE729U0Q)](https://codecov.io/gh/gitblast/ohtu-team13)

## Linkit

- [Backlog](https://github.com/gitblast/ohtu-team13/projects/1)
- [Definition of Done](docs/DOD.md)
- [Testikattavuusraportti](https://codecov.io/gh/gitblast/ohtu-team13)

## Käyttöohje


### Komentorivikomennot 

Projektin buildaus:

`./gradlew build`

Projektin käynnistäminen komentoriviltä:

`./gradlew run`

Testien ajaminen:

`./gradlew test`

Checkstylen ajaminen:

`./gradlew checkstyleMain`

Jar generointi (kohteeseen build/libs/Vinkkikirjasto-all.jar):

`./gradlew shadowJar`

### Sovelluksen käyttö

#### Etusivu

Etusivulla näkyvät seuraavat toiminnot:
- Tieto sovellukseen tallennettujen kirjojen, URLien ja elokuvien määrästä
- Linkki kirjojen lisäämisnäkymään
- Linkki URLien lisäämisnäkymään
- Linkki elokuvien lisäämisnäkymään
- Linkki kirjanmerkkien selausnäkymään

#### Kirjan lisääminen

Etusivulla paina Add Book -nappia. Lisäysnäkymässä voit painaa Return-nappia päästäksesi takaisin etusivulle.
Lisäysnäkymässä voit syöttää kirjan tiedoiksi kirjan kirjailijan nimen, kirjan nimen, kirjan julkaisuvuoden ja kirjan sivumäärän. Riittää, että syötät kirjan nimen. Jos julkaisuvuosi ja sivumäärä eivät ole kokonaislukuja, ne jätetään huomiotta. Sivun alaosassa on Add a new book -nappi, jota painamalla kirja lisätään tietokantaan ja sinut ohjataan takaisin etusivulle, jos kenttien täyttämisessä ei ollut ongelmia.

#### URLin lisääminen

Etusivulla paina Add URL -nappia. Lisäysnäkymässä voit painaa Return-nappia päästäksesi takaisin etusivulle.
Lisäysnäkymässä voit syöttää URLin tiedoiksi sen otsikon ja URLin itsensä. Kumpaakaan kenttää ei tule jättää tyhjäksi. Sivun alaosassa on Add a new URL -nappi, jota painamalla URL lisätään tietokantaan ja sinut ohjataan takaisin etusivulle, jos kenttien täyttämisessä ei ollut ongelmia.

#### Elokuvan lisääminen

Etusivulla paina Add Movie -nappia. Lisäysnäkymässä voit painaa Return-nappia päästäksesi takaisin etusivulle.
Lisäysnäkymässä voit syöttää elokuvan tiedoiksi sen nimen, ohjaajan nimen, julkaisuvuoden ja keston minuutteina. Julkaisuvuoden ja keston tulee olla kokonaislukuja, tai ne jätetään huomiotta. Pelkän otsikon lisääminen riittää elokuvan tallentamiseen. Sivun alaosassa on Add a new movie -nappi, jota painamalla elokuva lisätään tietokantaan ja sinut ohjataan takaisin etusivulle, jos kenttien täyttämisessä ei ollut ongelmia.

#### Kirjanmerkkien selailu

Etusivulla paina List all bookmarks -nappia. Selausnäkymässä voit painaa Return-nappia päästäksesi takaisin etusivulle. Sivulla on hakupalkki, jonka avulla voi suodattaa näkemiään kirjanmerkkejä, ja lista kaikista kirjanmerkeistä, joille suodattimet pätevät. Kirjanmerkkejä voi suodattaa ensimmäisestä pudotusvalikosta niiden tyypin perusteella niin, että voi näyttää kaikentyyppiset kirjanmerkit, tai vain kirjat, URLit tai elokuvat. Oikeanpuoleisesta pudotusvalikosta voi rajoittaa hakua eri kirjanmerkkityyppien ominaisuuksien perusteella. Jos on valittu näytettäväksi kaikki kirjanmerkkityypit, hakua voi rajata vain niiden nimekkeen perusteella. Kirjoja voi rajata myös kirjailijan ja ISBN:n perusteella ja elokuvia voi rajata myös ohjaajan perusteella. Kun jokin suodatin on valittu, hakukenttään voi kirjoittaa hakuehdon.
Selailunäkymässä voi myös kopioida URLeja ja kirjojen ISBN:iä leikepöydälle painamalla URLin tai kirjan Copy-nappia.

#### Kirjanmerkkien muokkaaminen ja poistaminen

Selailunäkymässä paina haluamasi kirjanmerkin kohdalta Edit-nappia. Muokkausnäkymässä voit painaa Return-nappia päästäksesi takaisin selailunäkymään. Voit tehdä muutoksia kirjanmerkin kenttiin. Kun painat Submit changes -nappia, tekemäsi muutokset tallentuvat ja pääset takaisin selausnäkymään. Voit poistaa kirjanmerkin painamalla Delete -nappia kenttien alla. Painaessasi sitä sovellus näyttää sinulle vielä ponnahdusikkunan, jossa voit varmistaa haluatko poistaa kirjanmerkin.
