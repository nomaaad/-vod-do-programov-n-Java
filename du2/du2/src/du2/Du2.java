/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GRID INTERPOLATION USING IDW
 * @author nomad
 * @author jethro
 */
public class Du2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // nacteni parametru rozliseni a expenentu
        int resX = 100; // rozliseni ve smeru x
        int resY = 100; // rozliseni ve smeru y
        double alfa = 2; // exponent
        int fileArg = 4; // index argumentu vstupnich dat
        
        // nacteni argumentu
        try{
            if (args[0].equals("-p") && args[2].equals("-g")){
                alfa = Double.parseDouble(args[1]);
                String [] XxY = args[3].split("x");
                resX = Integer.parseInt(XxY[0]);
                resY = Integer.parseInt(XxY[1]);
            } else if (args[0].equals("-g") && args[2].equals("-p")){
                alfa = Double.parseDouble(args[3]);
                String [] XxY = args[1].split("x");
                resX = Integer.parseInt(XxY[0]);
                resY = Integer.parseInt(XxY[1]);
            } else if (args[0].equals("-g") || args[0].equals("-p")){
                fileArg = 2;
                if (args[0].equals("-g")){
                    String [] XxY = args[1].split("x");
                    resX = Integer.parseInt(XxY[0]);
                    resY = Integer.parseInt(XxY[1]);
                } else{
                    alfa = Double.parseDouble(args[1]);
                }
            } else{
                fileArg = 0;
            }
        } catch(NumberFormatException ex){
            System.err.print("Incorrect format of argument");
            System.exit(1);
        } catch(ArrayIndexOutOfBoundsException ex){
            System.err.print("No arguments given");
            System.exit(1);
        }
        
        // nacteni vstupniho souboru do pole s prvky poli dle souradnice/hodnoty
        double [][]arrOfArr = {};
        try{
            arrOfArr = loadData(args[fileArg]);
        } catch(ArrayIndexOutOfBoundsException ex){
            System.err.print("No input file given");
            System.exit(1);
        }
        
        // pole definujici souradnice mrize
        double []xx = getGrid(arrOfArr[0], resX);
        double []yy = getGrid(arrOfArr[1], resY);
        
        // interpolace a zapis mrize vyslednych hodnot do souboru
        try{
            writeData(args[fileArg+1], arrOfArr[0], arrOfArr[1], arrOfArr[2], xx, yy, alfa, resX, resY);
        } catch(ArrayIndexOutOfBoundsException ex){
            System.err.print("No output file given");
            System.exit(1);
        }
    }
    /** 
     * Interpolacni metoda IDW v 1 bodu.
     * Interpoluje hodnotu bodu se souradnicemi [x, y] na zaklade mnoziny 
     * vstupnich bodu se souradnicemi [xd, yd] a hodnotou zd. Exponent al
     * urcuje charakter vysledneho "povrchu".
     * Vice informaci o metode:
     * https://en.wikipedia.org/wiki/Inverse_distance_weighting
     * 
     * @param xd souradnice x vstupnich bodu    
     * @param yd souradnice y vstupnich bodu  
     * @param zd hodnota vstupnich bodu    
     * @param x  souradnice x interpolovaneho bodu
     * @param y  souradnice y interpolovaneho bodu
     * @param al exponent interpolacni metody IDW
     * @return vysledna interpolovana hodnota
     */ 
    public static double IDW1p(double []xd, double []yd, double []zd, double x, double y, double al){       
        // inicializace promennych
        int nd = xd.length; // delka vstupnich poli
        double r; // vzdalenost
        double lamSum = 0; // suma vah
        double ziSum = 0; // suma vazenych vzdalenosti
        
        // vypocet sumy vah
        for (int i = 0; i < nd; i++){
            r = Math.sqrt(Math.pow(x-xd[i],2)+Math.pow(y-yd[i],2)); // vypocet vzdalenosti
            if (r == 0){
                return zd[i]; // hledana hodnota je v jednom ze vstupnich bodu
            } else{
                lamSum += 1/Math.pow(r,al); // nacitani sumy vah
                ziSum += zd[i]*((1/Math.pow(r,al))); // nacitani sumy vazenych vzdalenosti
            }
        }
        return ziSum/lamSum;
    }
    /** 
     * Vrati Maximum.
     * Vrati maximalni hodnotu pole.
     * 
     * @param input pole souradnic vstupnich bodu x nebo y
     * @return maximalni hodnota pole
     */ 
    public static double getMax(double[] input){   
        double max = input[0]; 
        for(int i = 1; i < input.length; i++){ 
            if(input[i] > max){ 
                max = input[i]; 
            } 
        } 
        return max;
    }
    /** 
     * Vrati Minimum.
     * Vrati minimalni hodnotu pole.
     * 
     * @param input pole souradnic vstupnich bodu x nebo y
     * @return minimalni hodnota pole
     */   
    public static double getMin(double[] input){  
        double min = input[0]; 
        for(int i = 1; i < input.length; i++){ 
            if(input[i] < min){ 
                min = input[i]; 
            } 
        } 
        return min; 
    }
    /** 
     * Definice mrize.
     * Vrati pole souradnic bunek ve smeru jedne ze souradnicovych os na zaklade 
     * prislusneho rozmeru mrize a extremnich hodnot pole.
     * 
     * @param arr pole souradnic vstupnich bodu x nebo y
     * @param res celociselny pocet bunek ve smeru x nebo y
     * @return pole souradnic bunek ve smeru jedne osy 
     */
    public static double[] getGrid(double[] arr, int res){ 
        double []grid = new double[res];
        double diff = getMax(arr)-getMin(arr);
        grid[0] = getMin(arr);
        for(int i = 1; i < res; i++){
            if(i == 0){
            } else{
                grid[i] = grid[0]+((i*1.0/res)*diff);
            }
        }
        return grid;
    }
    /** 
     * Nacte data.
     * Nacte data ze souboru s cestou danou parametrem text. Prevede radky 
     * souboru do textoveho pole, ktere nasledne roztridi do pole s prvky 
     * numerickych poli dle souradnic x, y a hodnoty.
     * 
     * @param text cesta k textovovemu souboru
     * @return pole s prvky numerickych poli dle souradnic x, y a hodnoty
     */
    public static double[][] loadData(String text){
        // inicializace pracovnich promennych
        String []stringArr = {}; // textove pole s prvky dle radku vstupniho souboru
        int n = 20; // pocet bodu ve vstupnim souboru
        
        // nacteni vstupniho souboru do textoveho pole
        try {
            BufferedReader br = new BufferedReader(new FileReader(text));
            String line;
            List<String> list = new ArrayList<>();
            while((line = br.readLine()) != null){
                list.add(line);
            }
            stringArr = list.toArray(new String[0]);
            n = Integer.parseInt(stringArr[0]);
        } catch (FileNotFoundException ex) {
            System.err.format("File %s not found",text);
            System.exit(1);
        } catch (IOException ex) {
            System.err.print("Error while reading a line in input file");
            System.exit(1);
        } catch (NumberFormatException ex){
            System.err.print("First line in input file must state number of following lines");
            System.exit(1);
        }
        
        // inicializace prvku vystupniho pole
        double []xd = new double[n]; // pole souradnic x
        double []yd = new double[n]; // pole souradnic y
        double []zd = new double[n]; // pole hodnot
        
        // parsovani a trideni textoveho pole do ciselnych poli dle souradnice/hodnoty
        try{
            for (int j = 1; j <= n; j++){
                String [] items;
                String line = stringArr[j];
                items = line.split(",");
                xd[j-1] = (Double.parseDouble(items[0]));
                yd[j-1] = (Double.parseDouble(items[1]));
                zd[j-1] = (Double.parseDouble(items[2]));
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            System.err.print("Missing line(s) in input file");
            System.exit(1);
        } catch (NumberFormatException ex){
            System.err.print("NaN in input file");
            System.exit(1);
        }
        
        // poskladani vystupniho pole a jeho vraceni
        double [][]arrOfArr = {xd,yd,zd};
        return arrOfArr;
    }
    /** 
     * Zapise data.
     * Provede interpolaci v bunkach mrizky definovane vstupnimi poli xx a yy 
     * funkce IDW1p a zapise interpolovane hodnoty do souboru s cestou danou 
     * parametrem text.
     * 
     * @param text cesta k souboru, do ktereho budou zapsany hodnoty     
     * @param xd   souradnice x vstupnich bodu    
     * @param yd   souradnice y vstupnich bodu  
     * @param zd   hodnota vstupnich bodu   
     * @param xx   pole souradnic bunek ve smeru osy x   
     * @param yy   pole souradnic bunek ve smeru osy y
     * @param alfa exponent interpolacni metody IDW
     * @param resX rozliseni na ose x
     * @param resY rozliseni na ose y
     */
    public static void writeData(String text, double []xd, double[]yd, double[]zd, double []xx, double []yy, double alfa, int resX, int resY){
        PrintWriter writer;
        try {
            writer = new PrintWriter(text);
            for(int j = 0; j<resY; j++){
                for(int i = 0; i<resX; i++){
                    writer.print(Math.round(IDW1p(xd, yd, zd, xx[i], yy[j], alfa)*100)/100.0+",");
                }
                writer.println();
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Du2.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}