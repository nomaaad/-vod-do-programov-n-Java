# Domácí úkol 1 - Zobrazení

Pøedmìt: **Úvod do programování**, Autor: **Bc. Jan Èermák**, Obor: **1. NKARTGD**, Rok: **2017**

## Popis
Program automatizuje výpoèet souøadnic válcových zobrazení. Je užiteèný zejména pøi 
konstrukci souøadnicové sítì. Pro jedno z kódovaných válcových zobrazení, dle zadaného 
mìøítka a polomìru Zemì vypíše vzdálenost rovnobìžek na svislé ose a poledníkù na 
vodorovné ose. Následnì je možný pøepoèet zemìpisných souøadnic bodu dle parametrù 
zvolených v pøedchozích krocích.

### Vstup
Program je interaktivní. Nejprve se zeptá uživatele na použité zobrazení,
odpovìdí bude jedno písmeno: 
- `L` - Lambertovo zobrazení
- `A` - Marinovo zobrazení 
- `B` - Braunovo zobrazení 
- `M` - Mercatorovo zobrazení 
- `E` - Behrmannovo zobrazení - Lambertovo zobrazení pro seènou rovnobìžku (u0) 30°
...- Zobrazovací rovnice: *x = R.v.cos(u0)*, *y = r.(1+cos(u0)).tg(u/2)*

Následnì je uživatel vyzván k zadání celoèíselného mìøítkového èísla a polomìru Zemì 
v km, pokud uživatel zadá `0`, je použita výchozí hodnota 6371,11 km. Po výpisu 
souøadnic se program bude dotázovat na zemìpisnou šíøku a délku bodu, dokud uživatel 
nezadá (`0`,`0`). V takovém pøípadì program skonèí. Pokud uživatel zadá nekorektní 
vstup, je uživatel pouèen a vyzván k opakovánému zadání.

### Výstup
Po zadání první øady parametrù program vypíše `Rovnobezky:` a následnì seznam 
vzdáleností na svislé ose od -90 po 90 stupòù. Poté vypíše `Poledniky:` a seznam 
vzdáleností na vodorovné ose od -180 po 180 stupòù. Krok poledníkù a rovnobìžek je 
10 stupòù. Všechny vypisované vzdálenosti se vypisují v centimetrech s pøesností na 
milimetry. Vzdálenosti, které pøekroèí 1 metr jsou nahrazeny pomlèkou.
Po zadání zemìpisné šíøky a délky bodu je vypsáno `Rovnobezky:` a vzdálenost bodu 
od osy y a následnì `Poledniky:` a vzdálenost bodu od osy x.

### Funkce

`readChar` 
- Vstup jednoho znaku z klávesnice.
`readInt` 
- Vstup celoèíselné hodnoty z klávesnice.
`readDouble` 
- Vstup desetinného èísla z klávesnice.

`prepinac`
- Spouští jednotlivé funkce zobrazení dle zadaného kódu zobrazení.
- Je založena na podmínkové konstrukci `switch`.
- Vstupem jsou parametry zobrazení (zemìpisné šíøky a délky, mìøítkové èíslo,
...polomìr Zemì) a znak kódující zobrazení.
- Funkce nevrací ani nevypisuje.

`lambert`
- Pøepoèítá zemìpisné souøadnice do rovinných dle zobrazovacích rovnic Lambertova
...zobrazení, mìøítkového èísla a polomìru Zemì. Výsledné souøadnice vypisuje do 
...øádku. Souøadnice pøesahující délku 100 cm jsou nahrazeny pomlènou.
- Vstup: zemìpisné šíøky a délky, mìøítkové èíslo, polomìr Zemì

`marin`
- Pøepoèítá zemìpisné souøadnice do rovinných dle zobrazovacích rovnic Lambertova
...zobrazení, mìøítkového èísla a polomìru Zemì. Výsledné souøadnice vypisuje do 
...øádku. Souøadnice pøesahující délku 100 cm jsou nahrazeny pomlènou.
- Vstup: zemìpisné šíøky a délky, mìøítkové èíslo, polomìr Zemì

`braun`
- Pøepoèítá zemìpisné souøadnice do rovinných dle zobrazovacích rovnic Lambertova
...zobrazení, mìøítkového èísla a polomìru Zemì. Výsledné souøadnice vypisuje do 
...øádku. Souøadnice pøesahující délku 100 cm jsou nahrazeny pomlènou.
- Vstup: zemìpisné šíøky a délky, mìøítkové èíslo, polomìr Zemì

`mercator`
- Pøepoèítá zemìpisné souøadnice do rovinných dle zobrazovacích rovnic Lambertova
...zobrazení, mìøítkového èísla a polomìru Zemì. Výsledné souøadnice vypisuje do 
...øádku. Souøadnice pøesahující délku 100 cm jsou nahrazeny pomlènou. Zobrazení 
...neumožòuje zobrazit póly, pøi pokusu o výpoèet souradnice polu je vypsáno: 
..."pol nelze zobrazit"
- Vstup: zemìpisné šíøky a délky, mìøítkové èíslo, polomìr Zemì

`behrmann`
- Pøepoèítá zemìpisné souøadnice do rovinných dle zobrazovacích rovnic Lambertova
...zobrazení, mìøítkového èísla a polomìru Zemì. Výsledné souøadnice vypisuje do 
...øádku. Souøadnice pøesahující délku 100 cm jsou nahrazeny pomlènou.
- Vstup: zemìpisné šíøky a délky, mìøítkové èíslo, polomìr Zemì