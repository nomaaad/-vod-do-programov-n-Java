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
import java.util.stream.DoubleStream;

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
        try{
            String [] XxY = args[3].split("x");
            resX = Integer.parseInt(XxY[0]);
            resY = Integer.parseInt(XxY[1]);
            alfa = Double.parseDouble(args[2]);
        } catch(NumberFormatException ex){
            System.err.print("Incorrect format of argument");
            System.exit(1);
        } 
        
        // nacteni vstupniho souboru do textoveho pole s prvky dle jednotlivych radku
        String []stringArr = {};
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            List<String> list = new ArrayList<>();
            while((line = br.readLine()) != null){
                list.add(line);
            }

            stringArr = list.toArray(new String[0]);
        } catch (FileNotFoundException ex) {
            System.err.format("File %s not found",args[0]);
            System.exit(1);
        } catch (IOException ex) {
            System.err.print("Error while reading a line");
            System.exit(1);
        }
        
        // urceni poctu radek vstupniho souboru
        int n = 20;
        try{        
            n = Integer.parseInt(stringArr[0]);
        } catch(NumberFormatException ex){
            System.err.print("First line must state number of following lines");
            System.exit(1);
        }
        
        // inicializace poli vstupnich souradnic a hodnot       
        double []xd = new double[n]; // pole souradnic x
        double []yd = new double[n]; // pole souradnic y
        double []zd = new double[n]; // pole hodnot
        
        // trideni a prevod souradnic a hodnot vstupniho souboru s osetrenim
        try{
            for (int j=1; j<=n; j++){
                String [] items;
                String line = stringArr[j];
                items = line.split(",");
                for (int i=0; i<items.length; i++){
                    Double.parseDouble(items[i]);
                    if (items.length!=3){
                        System.err.print("Incorrect number of value(s) in line");
                        System.exit(1);
                    }
                    if (i==0){
                        xd[j-1]=(Double.parseDouble(items[i]));
                    }
                    if (i==1){
                        yd[j-1]=(Double.parseDouble(items[i]));
                    }
                    if (i==2){
                        zd[j-1]=(Double.parseDouble(items[i]));
                    }
                }
            }
        } catch(ArrayIndexOutOfBoundsException ex){
            System.err.print("Missing line(s) in input file");
            System.exit(1);
        } catch(NumberFormatException ex){
            System.err.print("NaN in input file");
            System.exit(1);
        }
        
        // pole definujici souradnice mrize
        double []xx = getGrid(xd, resX);
        double []yy = getGrid(yd, resY);
        
        // interpolace a zapis mrize vyslednych hodnot do souboru
        PrintWriter writer;
        try {
            writer = new PrintWriter(args[1]);
            for(int j=0; j<resY; j++){
                for(int i=0; i<resX; i++){
                    writer.print(Math.round(IDW1p(xd, yd, zd, xx[i], yy[j], alfa)*100)/100.0+",");
                }
                writer.println();
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Du2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static double IDW1p(double []xd, double []yd, double []zd, double x, double y, double al){
    /** 
     * Interpolacni metoda IDW v 1 bodu.
     * Interpoluje hodnotu bodu se souradnicemi [x, y] na zaklade mnoziny 
     * vstupnich bodu se souradnicemi [xd, yd] a hodnotou zd. Exponent al
     * urcije charakter ziskaneho "povrchu".
     * Vice informaci o metode:
     * https://en.wikipedia.org/wiki/Inverse_distance_weighting
     * 
     * @param   xd     
     * @param   yd     
     * @param   zd   
     * @param   x
     * @param   y
     * @param   al
     */        
        // inicializace promennych
        int nd=xd.length; // delka vstupnich poli
        double r; // vzdalenost
        double []lam = new double[nd]; // pole vah
        double []lambda = new double[nd]; // pole vah se sumou 1
        double []zi = new double[nd]; // pole vazenych hodnot
        
        for (int i=1; i<nd; i++){
            r=Math.sqrt(Math.pow(x-xd[i],2)+Math.pow(y-yd[i],2)); // vypocet vzdalenosti
            if (r==0){
                return zd[i]; // hledana hodnota je v jedno ze vstupnich bodu
            }
            else{
                lam[i]=1/Math.pow(r,al); // vypocet vah
            }
        }
    
        // vypocet vah se sumou 1
        double lamSum = DoubleStream.of(lam).sum();
        for (int i=0; i<nd; i++){
            lambda[i]=lam[i]/lamSum;
        }
    
        // vazeni hodnot
        for (int i=0; i<nd; i++){
            zi[i]=zd[i]*lambda[i];
        }
        // vysledna interpolovana hodnota
        return DoubleStream.of(zi).sum();
    }
    public static double getMax(double[] input){
    /** 
     * Get Maximum.
     * Vrati maximalni hodnotu pole.
     * 
     * @param  arr  pole souradnic vstupnich bodu x nebo y.
     */    
        double max = input[0]; 
        for(int i=1; i<input.length; i++){ 
            if(input[i] > max){ 
                max = input[i]; 
            } 
        } 
        return max;
    }
    public static double getMin(double[] input){ 
    /** 
     * Get Minimum.
     * Vrati minimalni hodnotu pole.
     * 
     * @param  arr  pole souradnic vstupnich bodu x nebo y.
     */    
        double min = input[0]; 
        for(int i=1; i<input.length; i++){ 
            if(input[i] < min){ 
                min = input[i]; 
            } 
        } 
        return min; 
    }
    public static double[] getGrid(double[] arr, int res){ 
    /** 
     * Get Grid.
     * Vrati pole souradnic bunek ve smeru osy x nebo y na zaklade 
     * hodnoty rozliseni a extremnich hodnot pole.
     * 
     * @param  arr  pole souradnic vstupnich bodu x nebo y
     * @param  res  celociselny pocet bunek ve smeru x nebo y
     */ 
        double []grid = new double[res];
        double cell = (getMax(arr)-getMin(arr))/res;
        for(int i=0; i<res; i++){
            if(i==0){
                grid[0]=getMin(arr);
            }
            else{
                grid[i]=grid[i-1]+cell;
            }
        }    
        return grid;
    }
}