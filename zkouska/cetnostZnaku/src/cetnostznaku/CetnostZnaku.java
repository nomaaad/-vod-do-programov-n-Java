/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cetnostznaku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nomad
 */
public class CetnostZnaku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // load input text file to string
        String s = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while((line = br.readLine()) != null){
                s += line;
            }
        } catch (FileNotFoundException ex) {
            System.err.format("File %s not found",args[0]);
            System.exit(1);
        } catch (IOException ex) {
            System.err.print("Error while reading a line in input file");
            System.exit(1);
        }
        
        // create dictionary with characters in string as keys and count as values
        HashMap<Character,Integer> charCount = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Integer val = charCount.get(c);
            if(val != null){
                charCount.put(c, val + 1);
            }else{
                charCount.put(c,1);
            }
        }
        
        // print dictionary to output text file
        String out = args[1];
        PrintWriter writer;
        try {
            writer = new PrintWriter(out);
            writer.println("Character : Count");
            charCount.forEach((Character k,Integer v)->{
                writer.println(k + " : " + v);
            });
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CetnostZnaku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
