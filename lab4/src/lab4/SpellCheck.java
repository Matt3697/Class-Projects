package lab4;

import java.io.File;
import java.io.FileInputStream;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TST;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.setIn;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @authors Matthew Sagen and James Mitchell 
 * 4/18/2017
 * 
 * C:\Users\guita\Documents\NetBeansProjects\words.txt C:\Users\guita\Documents\NetBeansProjects\mydoc.txt
 */
public class SpellCheck {
    private static String[] words, dictionary;
    private static TST tree = new TST();
    private static Scanner scanner = new Scanner(System.in);
   
    public static void main(String[] args) {    
        
        if(args.length > 0){//reads in the two files from command line.
            FileInputStream first = null;
            FileInputStream second = null;
            InputStream normal = null;
            
            try{
                first = new FileInputStream(new File(args[0]));
                System.setIn(first);
                dictionary = In.readStrings();
                second = new FileInputStream(new File(args[1]));
                System.setIn(second);
                words = In.readStrings(); 
            }
            catch(Exception ex){
                System.err.println(ex);
            } 
        }
        fill(dictionary);
        check(words);
    }
    public static void fill(String[] dictionary){//Stores all of the words in dictionary into a hashtable. 
        for(int i = 0; i < dictionary.length; i++){
            tree.put(dictionary[i], i);
        }
    }
    
    public static void check(String[] words){
        String newWord;
        int iter = 0;
        System.out.println();
        for(int i = 0; i < words.length; i++){
            System.out.print(words[i] + " ");
        }
        for (String word : words) { //loop through every word to check if it is in the dicitonary
            if (!tree.contains(word)) {
                System.out.println();
                newWord = printOptions(word);
                words[iter] = newWord;
                System.out.println("You chose: " + words[iter]);
            }
            iter ++;
        }
        saveFile(words);
    }
    
    public static String printOptions(String word){
        System.out.println(word + ": Did you mean:");
        String temp = tree.longestPrefixOf(word);
        Iterable<String> newWords = tree.keysWithPrefix(temp);
        ArrayList<String> options = new ArrayList<>();
        int count = 0;
        for(String w : newWords){
            System.out.println((count)+ ": " + w);
            options.add(w);
            count++;
        }
        int decision = scanner.nextInt();
        while(decision > count - 1 || decision < 0){
            System.out.println("Invalid Option. Try again.");
            decision = scanner.nextInt();
        }
        return options.get(decision);
    }
    public static void saveFile(String[] words){
       try{
           PrintWriter writer = new PrintWriter("mydoc-checked.txt", "UTF-8");
           for(int i = 0; i < words.length; i++){
               writer.write(words[i]);
               writer.print(" ");
           }
           writer.close();
        } catch (IOException e) {
           System.out.println("Error: " + e);
        }
       
    }
}
