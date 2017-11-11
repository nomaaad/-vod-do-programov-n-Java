# Dom�c� �kol 1 - Zobrazen�

P�edm�t: **�vod do programov�n�**, Autor: **Bc. Jan �erm�k**, Obor: **1. NKARTGD**, Rok: **2017**

## Popis
Program automatizuje v�po�et sou�adnic v�lcov�ch zobrazen�. Je u�ite�n� zejm�na p�i 
konstrukci sou�adnicov� s�t�. Pro jedno z k�dovan�ch v�lcov�ch zobrazen�, dle zadan�ho 
m���tka a polom�ru Zem� vyp�e vzd�lenost rovnob�ek na svisl� ose a poledn�k� na 
vodorovn� ose. N�sledn� je mo�n� p�epo�et zem�pisn�ch sou�adnic bodu dle parametr� 
zvolen�ch v p�edchoz�ch kroc�ch.

### Vstup
Program je interaktivn�. Nejprve se zept� u�ivatele na pou�it� zobrazen�,
odpov�d� bude jedno p�smeno: 
- `L` - Lambertovo zobrazen�
- `A` - Marinovo zobrazen� 
- `B` - Braunovo zobrazen� 
- `M` - Mercatorovo zobrazen� 
- `E` - Behrmannovo zobrazen� - Lambertovo zobrazen� pro se�nou rovnob�ku (u0) 30�
...- Zobrazovac� rovnice: *x = R.v.cos(u0)*, *y = r.(1+cos(u0)).tg(u/2)*

N�sledn� je u�ivatel vyzv�n k zad�n� celo��seln�ho m���tkov�ho ��sla a polom�ru Zem� 
v km, pokud u�ivatel zad� `0`, je pou�ita v�choz� hodnota 6371,11 km. Po v�pisu 
sou�adnic se program bude dot�zovat na zem�pisnou ���ku a d�lku bodu, dokud u�ivatel 
nezad� (`0`,`0`). V takov�m p��pad� program skon��. Pokud u�ivatel zad� nekorektn� 
vstup, je u�ivatel pou�en a vyzv�n k opakov�n�mu zad�n�.

### V�stup
Po zad�n� prvn� �ady parametr� program vyp�e `Rovnobezky:` a n�sledn� seznam 
vzd�lenost� na svisl� ose od -90 po 90 stup��. Pot� vyp�e `Poledniky:` a seznam 
vzd�lenost� na vodorovn� ose od -180 po 180 stup��. Krok poledn�k� a rovnob�ek je 
10 stup��. V�echny vypisovan� vzd�lenosti se vypisuj� v centimetrech s p�esnost� na 
milimetry. Vzd�lenosti, kter� p�ekro�� 1 metr jsou nahrazeny poml�kou.
Po zad�n� zem�pisn� ���ky a d�lky bodu je vyps�no `Rovnobezky:` a vzd�lenost bodu 
od osy y a n�sledn� `Poledniky:` a vzd�lenost bodu od osy x.

### Funkce

`readChar` 
- Vstup jednoho znaku z kl�vesnice.
`readInt` 
- Vstup celo��seln� hodnoty z kl�vesnice.
`readDouble` 
- Vstup desetinn�ho ��sla z kl�vesnice.

`prepinac`
- Spou�t� jednotliv� funkce zobrazen� dle zadan�ho k�du zobrazen�.
- Je zalo�ena na podm�nkov� konstrukci `switch`.
- Vstupem jsou parametry zobrazen� (zem�pisn� ���ky a d�lky, m���tkov� ��slo,
...polom�r Zem�) a znak k�duj�c� zobrazen�.
- Funkce nevrac� ani nevypisuje.

`lambert`
- P�epo��t� zem�pisn� sou�adnice do rovinn�ch dle zobrazovac�ch rovnic Lambertova
...zobrazen�, m���tkov�ho ��sla a polom�ru Zem�. V�sledn� sou�adnice vypisuje do 
...��dku. Sou�adnice p�esahuj�c� d�lku 100 cm jsou nahrazeny poml�nou.
- Vstup: zem�pisn� ���ky a d�lky, m���tkov� ��slo, polom�r Zem�

`marin`
- P�epo��t� zem�pisn� sou�adnice do rovinn�ch dle zobrazovac�ch rovnic Lambertova
...zobrazen�, m���tkov�ho ��sla a polom�ru Zem�. V�sledn� sou�adnice vypisuje do 
...��dku. Sou�adnice p�esahuj�c� d�lku 100 cm jsou nahrazeny poml�nou.
- Vstup: zem�pisn� ���ky a d�lky, m���tkov� ��slo, polom�r Zem�

`braun`
- P�epo��t� zem�pisn� sou�adnice do rovinn�ch dle zobrazovac�ch rovnic Lambertova
...zobrazen�, m���tkov�ho ��sla a polom�ru Zem�. V�sledn� sou�adnice vypisuje do 
...��dku. Sou�adnice p�esahuj�c� d�lku 100 cm jsou nahrazeny poml�nou.
- Vstup: zem�pisn� ���ky a d�lky, m���tkov� ��slo, polom�r Zem�

`mercator`
- P�epo��t� zem�pisn� sou�adnice do rovinn�ch dle zobrazovac�ch rovnic Lambertova
...zobrazen�, m���tkov�ho ��sla a polom�ru Zem�. V�sledn� sou�adnice vypisuje do 
...��dku. Sou�adnice p�esahuj�c� d�lku 100 cm jsou nahrazeny poml�nou. Zobrazen� 
...neumo��uje zobrazit p�ly, p�i pokusu o v�po�et souradnice polu je vyps�no: 
..."pol nelze zobrazit"
- Vstup: zem�pisn� ���ky a d�lky, m���tkov� ��slo, polom�r Zem�

`behrmann`
- P�epo��t� zem�pisn� sou�adnice do rovinn�ch dle zobrazovac�ch rovnic Lambertova
...zobrazen�, m���tkov�ho ��sla a polom�ru Zem�. V�sledn� sou�adnice vypisuje do 
...��dku. Sou�adnice p�esahuj�c� d�lku 100 cm jsou nahrazeny poml�nou.
- Vstup: zem�pisn� ���ky a d�lky, m���tkov� ��slo, polom�r Zem�