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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

/**
 * IDW
 * @author nomad
 * @author jethro
 */
public class Du2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int res = 100;
        double alfa = 2;
        double []xd = new double[20];
        double []yd = new double[20];
        double []zd = new double[20];
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            int j=0;
            while ((line = br.readLine())!=null){
                String [] items;
                items = line.split(",");
                //if (args[0].equals("-n")){
                //    System.out.println();
                //}
                //if(j==0){
                //    continue;
                //}
                for (int i=0; i<items.length; i++){
                    //System.out.println(items[i]);
                    Double.parseDouble(items[i]);
                    if (i==0){
                        xd[j]=(Double.parseDouble(items[i]));
                    }
                    if (i==1){
                        yd[j]=(Double.parseDouble(items[i]));
                    }
                    if (i==2){
                        zd[j]=(Double.parseDouble(items[i]));
                    }
                }
                j++;
            }
        } catch (FileNotFoundException ex) {
            System.err.format("File %s not found",args[0]);
            System.exit(1);
        } catch (IOException ex) {
            System.err.print("Error while reading a line");
            System.exit(1);
        }
        
        double []xx = getGrid(xd, res);
        double []yy = getGrid(yd, res);
        
        PrintWriter writer;
        try {
            writer = new PrintWriter(args[1]);
            for(int i=0; i<res; i++){
                for(int j=0; j<res; j++){
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
     * 
     * 
     * @param   lat     pole zemepisnych sirek
     * @param   lon     pole zemepisnych delek
     * @param   scale   celociselne meritkove cislo
     * @param   R       desetinne cislo polomeru Zeme
     */        
    
        int nd=xd.length;
        double r;
        double []lam = new double[nd];
        double []lambda = new double[nd];
        double []zi = new double[nd];
    
        for (int i=1; i<nd; i++){
            r=Math.sqrt(Math.pow(x-xd[i],2)+Math.pow(y-yd[i],2));
            if (r==0){
                return zd[i];
            }
            else{
                lam[i]=1/Math.pow(r,al);
            }
        }
    
    //
        double lamSum = DoubleStream.of(lam).sum();
        for (int i=0; i<lam.length; i++){
            lambda[i]=lam[i]/lamSum;
        }
    
    //
        for (int i=0; i<lam.length; i++){
            zi[i]=zd[i]*lambda[i];
        }
        return DoubleStream.of(zi).sum();
    }
    public static double getMax(double[] input){
    /** 
     * 
     * 
     */    
        double max = input[0]; 
        for(int i=1; i<input.length; i++){ 
            if(input[i] > max){ 
                max = input[i]; 
            } 
        } 
        return max;
    }
    public static double getMin(double[] arr){ 
    /** 
     * 
     * 
     */    
        double min = arr[0]; 
        for(int i=1; i<arr.length; i++){ 
            if(arr[i] < min){ 
                min = arr[i]; 
            } 
        } 
        return min; 
    }
    public static double[] getGrid(double[] arr, int res){ 
    /** 
     * 
     * 
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
