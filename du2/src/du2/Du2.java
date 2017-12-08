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
 * IDW
 * @author nomad
 * @author jethro
 */
public class Du2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int resX = 100;
        int resY = 100;
        double alfa = 2;
        try{
            String [] XxY = args[3].split("x");
            resX = Integer.parseInt(XxY[0]);
            resY = Integer.parseInt(XxY[1]);
            alfa = Double.parseDouble(args[2]);
        } catch(NumberFormatException ex){
            System.err.print("Incorrect format of argument");
            System.exit(1);
        } 
            
        String tFile = args[0];
        String []fileArr = loadT(tFile);
        
        int n = 20;
        try{        
            n = Integer.parseInt(fileArr[0]);
        } catch(NumberFormatException ex){
            System.err.print("First line must state number of following lines");
            System.exit(1);
        }
        
//        System.out.println("n: "+n);
                
        double []xd = new double[n];
        double []yd = new double[n];
        double []zd = new double[n];
        
        try{
            for (int j=1; j<=n; j++){
                String [] items;
                String line = fileArr[j];
                items = line.split(",");
                for (int i=0; i<items.length; i++){
                    Double.parseDouble(items[i]);
                    if (items.length!=3){
                        System.err.print("Incorrect number of value(s) in line");
                        System.exit(1);
                    }
                    if (i==0){
                        xd[j-1]=(Double.parseDouble(items[i]));
//                        System.out.println("x: "+items[i]);
                    }
                    if (i==1){
                        yd[j-1]=(Double.parseDouble(items[i]));
//                        System.out.println("y: "+items[i]);
                    }
                    if (i==2){
                        zd[j-1]=(Double.parseDouble(items[i]));
//                        System.out.println("z: "+items[i]);
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
        
        double []xx = getGrid(xd, resX);
        double []yy = getGrid(yd, resY);
        
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
     * 
     * 
     * @param   xd     
     * @param   yd     
     * @param   zd   
     * @param   x
     * @param   y
     * @param   al
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
        for (int i=0; i<nd; i++){
            lambda[i]=lam[i]/lamSum;
        }
    
    //
        for (int i=0; i<nd; i++){
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
    public static String[] loadT(String file){
    /** 
     * 
     * 
     */
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            List<String> list = new ArrayList<>();
            while((line = br.readLine()) != null){
                list.add(line);
            }

            String[] stringArr = list.toArray(new String[0]);
            return stringArr;
        } catch (FileNotFoundException ex) {
            System.err.format("File %s not found",file);
            System.exit(1);
        } catch (IOException ex) {
            System.err.print("Error while reading a line");
            System.exit(1);
        }
        return null;
    }
}