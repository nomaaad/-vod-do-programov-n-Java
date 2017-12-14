# Dom�c� �kol 2 - IDW


P�edm�t: **�vod do programov�n�**, Autor: **Bc. Jan �erm�k**, Obor: **1. NKARTGD**, Rok: **2017**



## Popis

Neinteraktivn� program, kter� interpoluje vstupn� data metodou
 inverzn� v�en� vzd�lenosti (IDW). Interpolace umo��uje odhadnout vliv bod� s nam��enou na jejich okol�. Program vezme jako argumenty vstupn� a 
v�stupn� soubor a spo��t� interpolaci vstupn�ch dat v m��ov�ch bodech s�t� s rozli�en�m dle argumentu `-g`. 
V�stupn� s� m� rozm�ry, dle bod� s minim�ln�mi
 respektive maxim�ln�mi sou�adnicemi. Interpola�n� medoda lze ovlivnit argumentem exponentu `-p`. V p��pad� zad�n� nekorektn�ch vstup� program vyp�e popis chyby a skon�� s chybou.

### Vstup

Program nejd��ve vezme parametr `-p <cislo>`, kter� pou�ije jako exponent 
ve v�hov� funkci, nebo `-g <sirka>x<vyska>`, kter� ud�v� po�et ��dk� a sloupc� v�stupn� m��ky. Vstupn� i v�stupn� soubor je ve form�tu CSV a obsahuje desetinn� ��sla (s desetinnou 
te�kou) odd�len� ��rkami. Vstupn� soubor obsahuje na prvn�m ��dku po�et ��dk�
 nam��en�ch dat a dal�� ��dky v�dy obsahuj� x-ovou sou�adnici, y-ovou sou�adnici
a nam��enou hodnotu na t�chto sou�adnic�ch.

### V�stup

V�stupn� soubor obsahuje definovan� po�et ��dk� a sloupc�, kter� p�edstavuj� 
interpolovan� hodnoty v m��ov�ch bodech. Hodnoty jsou zaokrouhlen� na dv� desetinn� m�sta.


### Funkce

`IDW1p` - **Interpola�n� metoda IDW v 1 bodu**
- Interpoluje hodnotu bodu se sou�adnicemi *[x, y]* na z�klad� mno�iny vstupn�ch bod� se sou�adnicemi *[xd, yd]* a hodnotou *zd*. Exponent *al* ur�uje charakter v�sledn�ho "povrchu". V�ce informac� o metod�: https://en.wikipedia.org/wiki/Inverse_distance_weighting
- **parametry**: pole *xd*, pole *yd*, pole *zd*, *x*, *y*, *al*
- **vystup**: interpolovan� hodnota *zi*

`getMax` - **Vr�t� maximum**
- Vr�t� maxim�ln� hodnotu pole.
- **parametry**: pole *input*
- **vystup**: maxim�ln� hodnota *max*

`getMin` - **Vr�t� minimum**
- Vr�t� minim�ln� hodnotu pole.
- **parametry**: pole *input*
- **vystup**: minim�ln� hodnota *min*

`getGrid` - **Definice m��e**
- Vr�t� pole sou�adnic ve sm�ru jedn� ze sou�adnicov�ch os na z�klad� p��slu�n�ho rozm�ru m��e dan� parametrem *res* a extr�mn�ch hodnot vstupn�ho pole *arr*.
- **parametry**: pole *arr*, *res*
- **vystup**: sou�adnice bun�k jedn� strany m��e *grid*

`loadData` - **Na�te data**
- Na�te data ze souboru s cestou danou parametrem *text* a zap�e jednotliv� ��dky do textov�ho pole *stringArr*, kter� vr�t�.
- **parametry**: �et�zec *text*
- **vystup**: textov� pole s prvky dle ��dku textov�ho souboru *stringArr*

`writeData` - **Zap�e data**
- Provede interpolaci v bu�k�ch m��ky definovan� vstupn�mi poli *xx* a *yy* (s d�lkou *resX* a *resY*) dle funkce `IDW1p` a zap�e interpolovan� hodnoty do souboru s cestou danou parametrem *text*
- **parametry**: �et�zec *text*, pole *xd*, pole *yd*, pole *zd*, pole *xx*, pole *yy*, *alfa*, *resX*, *resY*
