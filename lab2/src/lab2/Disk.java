package lab2;

import edu.princeton.cs.algs4.MaxPQ;
import java.util.ArrayList;

/**
 *
 * @author Matthew Sagen and James Mitchell
 * This class is representative of a 1GB storage disk.
 */
public class Disk implements Comparable<Disk> { 
    private int maxSize;
    private int idNumber;
    private ArrayList<Integer> fileSizes;
  
    public Disk(int idNumber){
        this.fileSizes = new ArrayList<Integer>();
        this.maxSize = 1000000;//sets the maximum size to 1,000,000kb = 1GB
        this.idNumber = idNumber;
    }
    public boolean hasSpace(int num){
        if(maxSize >= num){//checks to see if there is enough space on the disk to add files to it.               
            return true;
        }
        return false;//returns false otherwise.
    }
    public void store(int a){
        if(hasSpace(a)){
            fileSizes.add(a);
            maxSize -= a;
        }
    }
    public int getSize(){
        return maxSize;
    }
	// either return a String that is a concatenation of the file sizes, i.e. “300 400 500”
	// or do a print(300 ) and print(400 ) then print(500 ), and remove getContents() from the print method in the printStats() method
    public  int getContents(){
        int content;
        for(int i = 0; i < fileSizes.size(); i++){
            content = fileSizes.get(i);
            System.out.print(" " + content);
        }
	return 0;
    }
    public int getId(){
        return idNumber;
    }
 
    @Override
    public int compareTo(Disk a) {
        return Integer.compare(getSize(),a.getSize());
    } 
}
