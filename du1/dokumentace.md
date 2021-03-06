# Domácí úkol 1 - Zobrazení

Předmět: **Úvod do programování**, Autor: **Bc. Jan Čermák**, Obor: **1. NKARTGD**, Rok: **2017**

### Popis
Program automatizuje výpočet souřadnic válcových zobrazení. Je užitečný zejména při 
konstrukci souřadnicové sítě. Pro jedno z kódovaných válcových zobrazení vypíše 
vzdálenost rovnoběžek na svislé ose a poledníků na vodorovné ose. Uživatel může
zvolit vlastní měřítko a poloměr Země. Následně je možný přepočet zeměpisných 
souřadnic bodu dle parametrů zvolených v předchozích krocích.

### Vstup
Program je interaktivní. Nejprve se zeptá uživatele na použité zobrazení,
odpovědí bude jedno písmeno: 
1. `L` - Lambertovo zobrazení
2. `A` - Marinovo zobrazení 
3. `B` - Braunovo zobrazení 
4. `M` - Mercatorovo zobrazení 
5. `E` - Behrmannovo zobrazení

Následně je uživatel vyzván k zadání celočíselného měřítkového čísla a poloměru Země 
v km, pokud uživatel zadá `0`, je použita výchozí hodnota 6371,11 km. Po výpisu 
souřadnic se program bude dotazovat na zeměpisnou šířku a délku bodu, dokud uživatel 
nezadá (`0`,`0`). V takovém případě program skončí. Pokud uživatel zadá nekorektní 
vstup, je uživatel poučen a vyzván k opakovanému zadání.

### Výstup
Po zadání první řady parametrů program vypíše `Rovnobezky:` a následně seznam 
vzdáleností na svislé ose od -90 po 90 stupňů. Poté vypíše `Poledniky:` a seznam 
vzdáleností na vodorovné ose od -180 po 180 stupňů. Krok poledníků a rovnoběžek je 
10 stupňů. Veškeré vypisované vzdálenosti se vypisují v centimetrech s přesností na 
milimetry. Vzdálenosti, které překročí 1 metr jsou nahrazeny pomlčkou.
Po zadání zeměpisné šířky a délky bodu je vypsáno `Rovnobezky:` a vzdálenost bodu 
od osy y a následně `Poledniky:` a vzdálenost bodu od osy x.

### Funkce

`readChar` 
- Vstup jednoho znaku z klávesnice.

`readInt` 
- Vstup celočíselné hodnoty z klávesnice.

`readDouble` 
- Vstup desetinného čísla z klávesnice.

`prepinac`
- Spouští jednotlivé funkce zobrazení dle zadaného kódu zobrazení.
- Je založena na podmínkové konstrukci `switch`.
- Vstupem jsou parametry zobrazení (zeměpisné šířky a délky, měřítkové číslo,
poloměr Země) a znak kódující zobrazení.
- Funkce nevrací ani nevypisuje.

`lambert`
- Přepočítá zeměpisné souřadnice do rovinných dle zobrazovacích rovnic Lambertova
zobrazení, měřítkového čísla a poloměru Země. Výsledné souřadnice vypisuje do 
řádku. Souřadnice přesahující délku 100 cm jsou nahrazeny pomlčkou.
- Zobrazovací rovnice: *x = R.v*, *y = R.sin(u)*
- Vstup: pole *v*, pole *u*, celočíselné měřítko, desetinný *R* 

`marin`
- Přepočítá zeměpisné souřadnice do rovinných dle zobrazovacích rovnic Lambertova
zobrazení, měřítkového čísla a poloměru Země. Výsledné souřadnice vypisuje do 
řádku. Souřadnice přesahující délku 100 cm jsou nahrazeny pomlčkou.
- Zobrazovací rovnice: *x = R.v*, *y = R.u*
- Vstup: pole *v*, pole *u*, celočíselné měřítko, desetinný *R*

`braun`
- Přepočítá zeměpisné souřadnice do rovinných dle zobrazovacích rovnic Lambertova
zobrazení, měřítkového čísla a poloměru Země. Výsledné souřadnice vypisuje do 
řádku. Souřadnice přesahující délku 100 cm jsou nahrazeny pomlčkou.
- Zobrazovací rovnice: *x = R.v*, *y = 2.R.tg(u/2)*
- Vstup: pole *v*, pole *u*, celočíselné měřítko, desetinný *R* 

`mercator`
- Přepočítá zeměpisné souřadnice do rovinných dle zobrazovacích rovnic Lambertova
zobrazení, měřítkového čísla a poloměru Země. Výsledné souřadnice vypisuje do 
řádku. Souřadnice přesahující délku 100 cm jsou nahrazeny pomlčkou. Zobrazení 
neumožňuje zobrazit póly, při pokusu o výpočet souradnice polu je vypsáno: 
"pol nelze zobrazit"
- Zobrazovací rovnice: *x = R.v*, *y = R.ln(cotg(d/2))*
- Vstup: pole *v*, pole *u*, celočíselné měřítko, desetinný *R* 

`behrmann`
- Přepočítá zeměpisné souřadnice do rovinných dle zobrazovacích rovnic Lambertova
zobrazení, měřítkového čísla a poloměru Země. Výsledné souřadnice vypisuje do 
řádku. Souřadnice přesahující délku 100 cm jsou nahrazeny pomlčkou.
- Lambertovo zobrazení pro sečnou rovnoběžku u0 = 30°
- Zobrazovací rovnice: *x = R.v.cos(u0)*, *y = R.sin(u).1/cos(u0)*
- Vstup: pole *v*, pole *u*, celočíselné měřítko, desetinný *R* 

*Zkratky: v - zeměpisná délka, u - zeměpisná šířka, d - deklinace, R - poloměr Země*
