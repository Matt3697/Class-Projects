package homework1;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 *
 * @Matthew Sagen
 * HomeWork 1: 1.4.15
 */

public class HomeWork1{
    
    public static void main(String[] args) {
        // TODO code application logic here
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
       
    }
    public static int count(int[] a){
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
	for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j ++){
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
