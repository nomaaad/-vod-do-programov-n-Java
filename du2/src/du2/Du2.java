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
        double []x = new double[20];
        double []y = new double[20];
        double []z = new double[20];
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
                        x[j]=(Double.parseDouble(items[i]));
                    }
                    if (i==1){
                        y[j]=(Double.parseDouble(items[i]));
                    }
                    if (i==2){
                        z[j]=(Double.parseDouble(items[i]));
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
        
        //System.out.println(IDW1b(x, y, z, 200, 220, 2));
        System.out.println(IDW1b(x, y, z, 200, 220, 2));
        
        PrintWriter writer;
        try {
            writer = new PrintWriter(args[1]);
            for(int i=1; i<20; i++){
                if(i%100==0){
                    writer.println(z[i-1]);
                }
                else{    
                    writer.print(z[i-1]+",");
                }
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Du2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static double IDW1b(double []xd, double []yd, double []zd, double x, double y, double al){
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
    double z;
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
}
