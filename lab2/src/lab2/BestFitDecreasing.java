package lab2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 *
 * @author Matthew Sagen and James Mitchell
 */
public class BestFitDecreasing {
    
    private static RedBlackBST tree;
    
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
        
        tree = new RedBlackBST();
        Disk first = new Disk(1);
        tree.put(first.getSize(),first);
        double size = 0;
        int totalFiles = input.length;
        for(int i = 0; i < input.length; i++){
            // find a disk in the tree that contains the closest space >= the input at i 
            ceil(input[i]);
            size += input[i];
       }
        size = size / 1000000;
        printInfo(size, tree, input.length);
    }

    // finds a disk that contains closest space >= x (if it doesn’t find a disk with enough space, 
    // then it returns a new disk) and adds the data to the disk
    public static Disk ceil(int x) {
       // find the key in the bst tree via the ceiling method that corresponds to this x 
        Integer temp = (Integer) tree.ceiling(Integer.valueOf(x));
        if(temp == null ){  // handle if the key is null
            Disk q = new Disk(tree.size()+1);
	    q.store(x);
            tree.put(q.getSize(),q);
            return q;
        }
        else {  // handle if the key is not null
            Disk d = (Disk) tree.get(temp);
            d.store(x);
            tree.delete(temp);
            tree.put(d.getSize(), d);
            return d;
          }
        
    }
    
     public static void printInfo(double size, RedBlackBST tree, int totalFiles){
        System.out.println("file sizes sum = " + size + " GB");
        System.out.println("total disks = " + tree.size());
        Iterable<Integer> iter = (Iterable<Integer>) tree.keys();
        if(totalFiles < 100) {  
            for(Integer i : iter) { 
                    Disk check = (Disk) tree.get(i);
                    System.out.print("  " + check.getId() + "  " + check.getSize() + ": ");
                    check.getContents();
                    System.out.println();
            }
        }
    }
}
