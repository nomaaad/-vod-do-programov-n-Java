/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IDW
 * @author nomad
 * @author jethro
 */
public class Cv10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double []x = new double[21];
        double []y = new double[21];
        double []z = new double[21];
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
                    System.out.println(items[i]);
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
        PrintWriter writer;
        try {
            writer = new PrintWriter(args[1]);
            for(int i=0; i<20; i++){
                if(i%99==0){
                    writer.println(z[i]);
                }
                else{    
                    writer.print(z[i]+",");
                }
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cv10.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
