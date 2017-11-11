/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Valcova zobrazeni
 * @author nomad
 * @author jethro
 */
public class Du1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Inicializce promennych
        char proj; // kod zobrazeni
        int scale; // meritkove cislo
        double ref; // polomer Zeme [km]
        double R; // polomer Zeme [cm]
        double []sy = new double[1]; // zemepisna sirka bodu
        double []sx = new double[1]; // zemepisna delka bodu
        
        // Vstup parametru z klavesnice a jejich osetreni nekonecnymi cykly
        while (true){
            System.out.print("Zadejte zobrazeni: ");
            proj = readChar();
            if (proj == 'L' || proj == 'A' || proj == 'B' || proj == 'M' || proj == 'E'){
                break;
            }
            System.out.println("Zadejte L pro Lambertovo, A pro Marinovo, B pro Braunovo, M pro Mercatorovo nebo E pro Behrmannovo zobrazeni.");
        }
        while (true){
            System.out.print("Zadejte meritko: ");
            scale = readInt();
            if (scale > 0){
                break;
            }
            System.out.println("Meritko musi byt vetsi nez nula.");
        }
        while (true){
            System.out.print("Zadejte polomer Zeme [km]: ");
            ref = readDouble();
            if (ref >= 0){
                if (ref==0){
                    ref=6371.11; // vychozi hodnota
                }
                R = ref*100000; // prevod na cm
                break;
            }
            System.out.println("Polomer Zeme nesmi byt zaporny. V pripade zadani nuly bude pouzita vychozi hodnota 6371,11 km.");
        }
        // Pole rovnobezek a poledniku s krokem 10 stupnu
        double []lat = {-90,-80,-70,-60,-50,-40,-30,-20,-10,0,10,20,30,40,50,60,70,80,90};
        double []lon = {-180,-170,-160,-150,-140,-130,-120,-110,-100,-90,-80,-70,-60,-50,-40,-30,-20,-10,
                        0,10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180};
                
        // Volani zvolene funkce zobrazeni
        prepinac(lat, lon, scale, R, proj);
        
        //Vypocet souradnic bodu
        System.out.println("");
        while (true){
            while (true){
                System.out.print("Zadejte zemepisnou sirku bodu: ");
                sy[0] = readDouble();
                if (sy[0] >= -90 && sy[0] <= 90){
                    break;
                }
                System.out.println("Zemepisna sirka musi byt v intervalu [-90; 90].");
            }
            while (true){
                System.out.print("Zadejte zemepisnou delku bodu: ");
                sx[0] = readDouble();
                if (sx[0] >= -180 && sx[0] <= 180){
                    break;
                }
                System.out.println("Zemepisna delka musi byt v intervalu [-180; 180].");
            }
            if (sx[0] == 0.0 && sy[0] == 0.0){
                break;
            }
            prepinac(sy, sx, scale, R, proj);
            System.out.println("");
        }
    }
    public static int readInt() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }
    
    public static double readDouble() throws IOException{
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return Double.parseDouble(reader.readLine());
    }
    
    public static char readChar() throws IOException{
        Scanner s= new Scanner(System.in);
        return  s.next().charAt(0);
    }
    public static void prepinac(double []lat, double []lon, int scale, double R, char proj){
    /**
     * Spousti jednotlive funkce zobrazeni dle zadaneho kodu zobrazeni.
     * 
     * @param   lat     pole zemepisnych sirek ve stupnich
     * @param   lon     pole zemepisnych delek ve stupnich
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     * @param   proj    znak kodujici zvolene zobrazeni
     */
        switch (proj) {
            case 'L':
                lambert(lat, lon, scale, R);
                break;
            case 'A':
                marin(lat, lon, scale, R);
                break;
            case 'B':
                braun(lat, lon, scale, R);
                break;
            case 'M':
                mercator(lat, lon, scale, R);
                break;
            case 'E':
                behrmann(lat, lon, scale, R);
                break;
        }
    }       
    public static void lambert(double []lat, double []lon, int scale, double R){
    /** 
     * Lambertovo zobrazeni.
     * Prepocita zemepisne souradnice do rovinnych dle zobrazovacich rovnic
     * Lambertova zobrazeni, meritkoveho cisla a polomeru Zeme. 
     * Vysledne souradnice vypisuje do radku.
     * Souradnice presahujici delku 100 cm jsou nahrazeny pomlckou.
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */
        // Vypocet a vypis rovnobezek
        double []y = new double[lat.length];
        System.out.print("Rovnobezky: ");
        for(int i=0; i<=lat.length-1; i++){
            y[i]=R*(Math.sin(lat[i]*Math.PI/180))*(1.0/scale);
            if (Math.abs(y[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", y[i]);
            }
        }    
        // Vypocet a vypis poledniku
        double []x = new double[lon.length];
        System.out.print("\nPoledniky: ");
        for(int i=0; i<=lon.length-1; i++){
            x[i]=R*(lon[i]*Math.PI/180)*(1.0/scale);
            if (Math.abs(x[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", x[i]);
            }
        }   
    }
    public static void marin(double []lat, double []lon, int scale, double R){
    /** 
     * Marinovo zobrazeni.
     * Prepocita zemepisne souradnice do rovinnych dle zobrazovacich rovnic
     * Marinova zobrazeni, meritkoveho cisla a polomeru Zeme. 
     * Vysledne souradnice vypisuje do radku.
     * Souradnice presahujici delku 100 cm jsou nahrazeny pomlckou.
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */
        // Vypocet a vypis rovnobezek
        double []y = new double[lat.length];
        System.out.print("Rovnobezky: ");
        for(int i=0; i<=lat.length-1; i++){
            y[i]=(1.0/scale)*R*(lat[i]*Math.PI/180);
            if (Math.abs(y[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", y[i]);
            }
        }    
        // Vypocet a vypis poledniku
        double []x = new double[lon.length];
        System.out.print("\nPoledniky: ");
        for(int i=0; i<=lon.length-1; i++){
            x[i]=(1.0/scale)*R*(lon[i]*Math.PI/180);
            if (Math.abs(x[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", x[i]);
            }
        }   
    }
    public static void braun(double []lat, double []lon, int scale, double R){
    /** 
     * Braunovo zobrazeni.
     * Prepocita zemepisne souradnice do rovinnych dle zobrazovacich rovnic
     * Braunova zobrazeni, meritkoveho cisla a polomeru Zeme. 
     * Vysledne souradnice vypisuje do radku.
     * Souradnice preshujici delku 100 cm jsou nahrazeny pomlckou.
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */
        // Vypocet a vypis rovnobezek
        double []y = new double[lat.length];
        System.out.print("Rovnobezky: ");
        for(int i=0; i<=lat.length-1; i++){
            y[i]=(1.0/scale)*2*R*(Math.tan((lat[i]/2)*Math.PI/180));
            if (Math.abs(y[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", y[i]);
            }
        }    
        // Vypocet a vypis poledniku
        double []x = new double[lon.length];
        System.out.print("\nPoledniky: ");
        for(int i=0; i<=lon.length-1; i++){
            x[i]=(1.0/scale)*R*(lon[i]*Math.PI/180);
            if (Math.abs(x[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", x[i]);
            }
        }   
    }
    public static void mercator(double []lat, double []lon, int scale, double R){
    /** 
     * Mercatorovo zobrazeni.
     * Prepocita zemepisne souradnice do rovinnych dle zobrazovacich rovnic
     * Mercatorova zobrazeni, meritkoveho cisla a polomeru Zeme. 
     * Vysledne souradnice vypisuje do radku.
     * Souradnice preshujici delku 100 cm jsou nahrazeny pomlckou.
     * Zobrazeni neumoznuje zobrazit poly, pri pokusu o vypocet souradnice polu 
     * je vypsano: "pol nelze zobrazit"
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */        
        // Vypocet a vypis rovnobezek
        double []y = new double[lat.length];
        System.out.print("Rovnobezky: ");
        for(int i=0; i<=lat.length-1; i++){
            if (lat[i] == 90 || lat[i] == -90){
                System.out.print("pol nelze zobrazit ");
                continue;
            }
            y[i]=(1.0/scale)*R*(Math.log(1.0/Math.tan((90-lat[i])/2*Math.PI/180)));
            if (Math.abs(y[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", y[i]);
            }
        }
        // Vypocet a vypis poledniku
        double []x = new double[lon.length];
        System.out.print("\nPoledniky: ");
        for(int i=0; i<=lon.length-1; i++){
            x[i]=(1.0/scale)*R*(lon[i]*Math.PI/180);
            if (Math.abs(x[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", x[i]);
            }
        }   
    }
    public static void behrmann(double []lat, double []lon, int scale, double R){
    /** 
     * Behrmannovo zobrazeni.
     * Prepocita zemepisne souradnice do rovinnych dle zobrazovacich rovnic
     * Behrmannova zobrazeni, meritkoveho cisla a polomeru Zeme. 
     * Vysledne souradnice vypisuje do radku.
     * Souradnice preshujici delku 100 cm jsou nahrazeny pomlckou.
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */
        // Vypocet a vypis rovnobezek
        double []y = new double[lat.length];
        System.out.print("Rovnobezky: ");
        for(int i=0; i<=lat.length-1; i++){
            y[i]=(1.0/scale)*R*Math.sin(lat[i]*Math.PI/180)*(1/Math.cos(30*Math.PI/180));
            if (Math.abs(y[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", y[i]);
            }
        }    
        // Vypocet a vypis poledniku
        double []x = new double[lon.length];
        System.out.print("\nPoledniky: ");
        for(int i=0; i<=lon.length-1; i++){
            x[i]=(1.0/scale)*R*(lon[i]*Math.PI/180)*Math.cos(30*Math.PI/180);
            if (Math.abs(x[i])>100){
                   System.out.print("- ");
            }
            else {
                System.out.format("%.1f ", x[i]);
            }
        }   
    }
}
