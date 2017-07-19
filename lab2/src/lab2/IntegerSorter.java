/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import edu.princeton.cs.algs4.In;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Matthew Sagen and James Mitchell
 */
public class IntegerSorter {
    
    public static void main(String[] args){
        if (args.length > 0) { //reads in data from standard input. 
            FileInputStream is = null;
            try {
                is = new FileInputStream(new File(args[0]));
            } catch (Exception ex) {
                System.err.println(ex);
            }
            System.setIn(is);
        }
        int[] input = In.readInts();
        
        reOrganize(input);
        WorstFit a = new WorstFit(input);
    }
    
    public static void reOrganize(int[] input){ 
        int j;
        boolean flag = true;// set flag to true for first iteration
        int temp;   
        while (flag){
            flag= false;
            for( j=0;  j < input.length -1;  j++ ){
                if(input[j] < input[j+1]){
                    temp = input[j];//swap elements
                    input[j] = input[j+1];
                    input[j+1] = temp;
                    flag = true;//set flag back to true if swap occured.
                } 
            } 
        }
    }
    
}
