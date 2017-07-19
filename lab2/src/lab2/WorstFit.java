package lab2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 *
 * @author Matthew Sagen and James Mitchell
 * Input file location: C:\Users\guita\Documents\NetBeansProjects\lab2\src\lab2\input18.txt
 */
public class WorstFit {
    public static int[] input;
    public static MaxPQ pq = new MaxPQ();
    
    public static void main(String[] args) {
       
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
        fill(input);
        
    }
    public static void fill(int[] input){
        int id = 0;
        double totalSize = 0; 
        Disk a = new Disk(id);//creates a new instance of Disk;
        pq.insert(a);
        for(int i = 0; i < input.length; i++){
            totalSize +=  input[i];
            boolean wasFound = false;
            Iterator<Disk> iterator = pq.iterator();
            while(iterator.hasNext()){
                Disk d = iterator.next();
                if(input[i] < d.getSize()){
                    wasFound = true; 
                    d.store(input[i]);
                    break;
                }  
            }
            if(!wasFound){
                id++;
                Disk b = new Disk(id); 
                pq.insert(b);
                b.store(input[i]);
            }
      
        }
        totalSize = totalSize / 1000000;
        printInfo(totalSize, pq, input.length);
    }

    public static void printInfo(double size, MaxPQ<Disk> pq, int totalFiles){
        System.out.println("file sizes sum = " + size + " GB");
        System.out.println("total disks = " + pq.size());
        Iterator<Disk> newIterator = pq.iterator();
  
        if(totalFiles < 100) {  
            for(int i = 0; i < totalFiles; i++) { 
                if(newIterator.hasNext()){
                    Disk check = newIterator.next(); 
                    System.out.print("  " + check.getId() + "  " + check.getSize() + ": ");
                    check.getContents();
                    System.out.println();
                }
            }
        }
    }

    WorstFit(int[] input) {//takes in the parameters from integerSorter
       this.input = input;
       fill(input);
       
    }
}
