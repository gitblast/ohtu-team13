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
- Tieto sovellukseen tallennettujen kirjojen määrästä
- Tieto sovellukseen tallennettujen URLien määrästä
- Linkki kirjojen lisäämisnäkymään
- Linkki URLien lisäämisnäkymään
- Linkki tallennettujen kirjojen selausnäkymään
- Linkki tallennettujen URLien selausnäkymään

#### Kirjan lisääminen

Etusivulla paina Lisää kirja -nappia. Lisäysnäkymässä voit painaa Takaisin-nappia päästäksesi takaisin etusivulle.
Lisäysnäkymässä voit syöttää kirjan tiedoiksi kirjan kirjailijan nimen, kirjan nimen, kirjan julkaisuvuoden ja kirjan sivumäärän. Yhtäkään kenttää ei saa jättää tyhjäksi, ja julkaisuvuoden ja sivumäärän tulee olla kokonaislukuja. Sivun alaosassa on Lisää uusi kirja -nappi, jota painamalla kirja lisätään tietokantaan ja sinut ohjataan takaisin etusivulle, jos kenttien täyttämisessä ei ollut ongelmia.

#### URLin lisääminen

Etusivulla paina Lisää URL -nappia. Lisäysnäkymässä voit painaa Takaisin-nappia päästäksesi takaisin etusivulle.
Lisäysnäkymässä voit syöttää URLin tiedoiksi sen otsikon ja URLin itsensä. Kumpaakaan kenttää ei tule jättää tyhjäksi. Sivun alaosassa on Lisää uusi URL -nappi, jota painamalla URL lisätään tietokantaan ja sinut ohjataan takaisin etusivulle, jos kenttien täyttämisessä ei ollut ongelmia.

#### Kirjojen selaaminen

Etusivulla paina Tallennetut kirjat -nappia. Selausnäkymässä voit painaa Takaisin-nappia päästäksesi takaisin etusivulle.
Kirjojen tiedot on listattuna niin, että yhdellä rivillä on yhden kirjan tiedot, järjestyksessä: Kirjailija, nimi, julkaisuvuosi, sivumäärä ja ISBN.

Hakutuloksia voi suodattaa tietyn kentän perusteella valitsemalla suodatin vetolaatikosta, ja kirjottamalla hakukenttään etsittävä avainsana.

#### URLien selaaminen

Etusivulla paina Tallennetut URLit -nappia. Selausnäkymässä voit painaa Takaisin-nappia päästäksesi takaisin etusivulle.
URLien tiedot on listattuna niin, että yhdellä rivillä on yhden URLin tiedot, ensiksi otsikko ja toiseksi itse URL. Jokaisen URLin jälkeen on myös Kopioi-nappi, jonka avulla URLin voi kopioida koneen leikepöydälle ja sieltä kätevästi liittää esimerkiksi selaimella osoitekenttään. Kun kopioit URLin onnistuneesti, sivun yläosaan ilmestyy teksti "URL kopioitu leikepöydälle!" varmitukseksi siitä, että kopiointi onnistui.

Hakutuloksia voi suodattaa tietyn kentän perusteella valitsemalla suodatin vetolaatikosta, ja kirjottamalla hakukenttään etsittävä avainsana.

