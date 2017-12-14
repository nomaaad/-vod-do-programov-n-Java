# Domácí úkol 2 - IDW


Pøedmìt: **Úvod do programování**, Autor: **Bc. Jan Èermák**, Obor: **1. NKARTGD**, Rok: **2017**



## Popis

Neinteraktivní program, kterı interpoluje vstupní data metodou
 inverzní váené vzdálenosti (IDW). Interpolace umoòuje odhadnout vliv bodù s namìøenou na jejich okolí. Program vezme jako argumenty vstupní a 
vıstupní soubor a spoèítá interpolaci vstupních dat v møíovıch bodech sítì s rozlišením dle argumentu `-g`. 
Vıstupní sí má rozmìry, dle bodù s minimálními
 respektive maximálními souøadnicemi. Interpolaèní medoda lze ovlivnit argumentem exponentu `-p`. V pøípadì zadání nekorektních vstupù program vypíše popis chyby a skonèí s chybou.

### Vstup

Program nejdøíve vezme parametr `-p <cislo>`, které pouije jako exponent 
ve váhové funkci, nebo `-g <sirka>x<vyska>`, které udává poèet øádkù a sloupcù vıstupní møíky. Vstupní i vıstupní soubor je ve formátu CSV a obsahuje desetinná èísla (s desetinnou 
teèkou) oddìlená èárkami. Vstupní soubor obsahuje na prvním øádku poèet øádkù
 namìøenıch dat a další øádky vdy obsahují x-ovou souøadnici, y-ovou souøadnici
a namìøenou hodnotu na tìchto souøadnicích.

### Vıstup

Vıstupní soubor obsahuje definovanı poèet øádkù a sloupcù, které pøedstavují 
interpolované hodnoty v møíovıch bodech. Hodnoty jsou zaokrouhlené na dvì desetinná místa.


### Funkce

`IDW1p` - **Interpolaèní metoda IDW v 1 bodu**
- Interpoluje hodnotu bodu se souøadnicemi *[x, y]* na základì mnoiny vstupních bodù se souøadnicemi *[xd, yd]* a hodnotou *zd*. Exponent *al* urèuje charakter vısledného "povrchu". Více informací o metodì: https://en.wikipedia.org/wiki/Inverse_distance_weighting
- **parametry**: pole *xd*, pole *yd*, pole *zd*, *x*, *y*, *al*
- **vystup**: interpolovaná hodnota *zi*

`getMax` - **Vrátí maximum**
- Vrátí maximální hodnotu pole.
- **parametry**: pole *input*
- **vystup**: maximální hodnota *max*

`getMin` - **Vrátí minimum**
- Vrátí minimální hodnotu pole.
- **parametry**: pole *input*
- **vystup**: minimální hodnota *min*

`getGrid` - **Definice møíe**
- Vrátí pole souøadnic ve smìru jedné ze souøadnicovıch os na základì pøíslušného rozmìru møíe dané parametrem *res* a extrémních hodnot vstupního pole *arr*.
- **parametry**: pole *arr*, *res*
- **vystup**: souøadnice bunìk jedné strany møíe *grid*

`loadData` - **Naète data**
- Naète data ze souboru s cestou danou parametrem *text* a zapíše jednotlivé øádky do textového pole *stringArr*, které vrátí.
- **parametry**: øetìzec *text*
- **vystup**: textové pole s prvky dle øádku textového souboru *stringArr*

`writeData` - **Zapíše data**
- Provede interpolaci v buòkách møíky definované vstupními poli *xx* a *yy* (s délkou *resX* a *resY*) dle funkce `IDW1p` a zapíše interpolované hodnoty do souboru s cestou danou parametrem *text*
- **parametry**: øetìzec *text*, pole *xd*, pole *yd*, pole *zd*, pole *xx*, pole *yy*, *alfa*, *resX*, *resY*
