# Domácí úkol 2 - IDW


Předmět: **Úvod do programování**, Autor: **Bc. Jan Čermák**, Obor: **1. NKARTGD**, Rok: **2017**



## Popis

Neinteraktivní program, který interpoluje vstupní data metodou inverzní vážené vzdálenosti (IDW). Interpolace umožňuje odhadnout vliv bodů s naměřenou na jejich okolí. Program vezme jako argumenty vstupní a výstupní soubor a spočítá interpolaci vstupních dat v mřížových bodech sítě s rozměry dle argumentu `-g`. Velikost výstupní sítě je dané dle bodů s minimálními resp. maximálními souřadnicemi. Interpolační metoda lze ovlivnit argumentem exponentu `-p`. V případě zadání nekorektních vstupů program vypíše popis chyby a skončí s chybou.

### Vstup

Program nejdříve vezme parametr `-p <cislo>`, které použije jako exponent ve váhové funkci, nebo `-g <sirka>x<vyska>`, které udává počet řádků a sloupců výstupní mřížky. Vstupní i výstupní soubor je ve formátu CSV a obsahuje desetinná čísla (s desetinnou tečkou) oddělená čárkami. Vstupní soubor obsahuje na prvním řádku počet řádků naměřených dat a další řádky vždy obsahují x-ovou souřadnici, y-ovou souřadnici a naměřenou hodnotu na těchto souřadnicích.

### Výstup

Výstupní soubor obsahuje definovaný počet řádků a sloupců, které představují interpolované hodnoty v mřížových bodech. Hodnoty jsou zaokrouhlené na dvě desetinná místa.


### Funkce

`IDW1p` - **Interpolační metoda IDW v 1 bodu**
- Interpoluje hodnotu bodu se souřadnicemi *[x, y]* na základě množiny vstupních bodů se souřadnicemi *[xd, yd]* a hodnotou *zd*. Exponent *al* určuje charakter výsledného "povrchu". Více informací o metodě: https://en.wikipedia.org/wiki/Inverse_distance_weighting
- **parametry**: pole *xd*, pole *yd*, pole *zd*, *x*, *y*, *al*
- **výstup**: interpolovaná hodnota *zi*

`getMax` - **Vrátí maximum**
- Vrátí maximální hodnotu pole.
- **parametry**: pole *input*
- **výstup**: maximální hodnota *max*

`getMin` - **Vrátí minimum**
- Vrátí minimální hodnotu pole.
- **parametry**: pole *input*
- **výstup**: minimální hodnota *min*

`getGrid` - **Definice mříže**
- Vrátí pole souřadnic ve směru jedné ze souřadnicových os na základě příslušného rozměru mříže dané parametrem *res* a extrémních hodnot vstupního pole *arr*.
- **parametry**: pole *arr*, *res*
- **výstup**: souřadnice buněk jedné strany mříže *grid*

`loadData` - **Načte data**
- Načte data ze souboru s cestou danou parametrem *text* a zapíše jednotlivé řádky do textového pole *stringArr*, které vrátí.
- **parametry**: řetězec *text*
- **výstup**: textové pole s prvky dle řádku textového souboru *stringArr*

`writeData` - **Zapíše data**
- Provede interpolaci v buňkách mřížky definované vstupními poli *xx* a *yy* (s délkou *resX* a *resY*) dle funkce `IDW1p` a zapíše interpolované hodnoty do souboru s cestou danou parametrem *text*
- **parametry**: řetězec *text*, pole *xd*, pole *yd*, pole *zd*, pole *xx*, pole *yy*, *alfa*, *resX*, *resY*
