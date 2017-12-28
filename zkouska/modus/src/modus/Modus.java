/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Honza
 */
public class Modus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // input array
        int []arr = {1000,-1,0,1,1,2,2,3,5,3,1,4,3,6,4,4,6,7,12,3,5,6,4,7,8,5,9,5,4,2,3,1,6,42,7,9};
        
        // create dictionary with digits as keys and their count as values
        HashMap<Integer,Integer> numCount = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int c = arr[i];
            Integer val = numCount.get(c);
            if(val != null){
                numCount.put(c, val + 1);
            }else{
                numCount.put(c,1);
            }
        }

        // find mode or multiple modes
        int []modes = new int[arr.length];
        int mode = Collections.max(numCount.entrySet(), Map.Entry.comparingByValue()).getKey(); // find mode
        int j = 0;
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()){ // check for multiple modes
            if (Objects.equals(entry.getValue(), numCount.get(mode))){
                modes[j] = entry.getKey();
                j++;
            }
        }
        
        // print final mode or inform that array is multi-modal and print modes
        if (j == 1){
            System.out.format("Modus je %d \n", mode);
        }  else{ 
            System.out.println("Pole je multimodální");
            for(int i = 0; i < j; i++){
                System.out.format("%d. modus je %d \n", i+1, modes[i]);
            }
        }
    }
}
