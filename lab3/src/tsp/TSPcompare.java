/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import java.io.File;
import java.io.FileInputStream;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Matthew Sagen
 * 4/7/2017
 */
public class TSPcompare {
    
    
    public static void main(String[] args) {
        int input = 0;
        StdDraw.setCanvasSize(500, 500);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.blue);
        StdDraw.setScale(0.00, 100.00);
        
       if (args.length > 0) { //reads in data from standard input. 
             try {
                input = Integer.parseInt(args[0]);
                System.out.print(input);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        int[][] coordinates = new int[input][2];
        ArrayList<Integer> path = new ArrayList<Integer>();
        EdgeWeightedGraph graph = new EdgeWeightedGraph(input);
        Edge e = new Edge(0,0,0);
        StdDraw.text(50, 95, "Greedy Method");
        StdDraw.show();
        createGraph(input, coordinates, graph, e);
        Greedy a = new Greedy(coordinates, graph, e);
        int vertex = 0,vertex1X = 0,vertex1Y = 0,vertex2X = 0,vertex2Y = 0;
        a.traverse(graph, vertex, path);
        if(path.size() >= input){
            System.out.println("Error: There does not exist a solution.");
        }
        else{
            for(int i = 0; i < path.size(); i++){
                vertex1X = coordinates[path.get(i)][0];
                vertex1Y = coordinates[path.get(i)][1];
                
                if(i == path.size()- 1){
                    vertex2X = coordinates[path.get(i+1)][0];
                    vertex2Y = coordinates[path.get(i+1)][1];
                }
                 StdDraw.pause(1000);
                 StdDraw.line(vertex1X,vertex1Y,vertex2X,vertex2Y);
            }
           a.printStats(path);
        }
        
        StdDraw.setPenColor(Color.green);
        StdDraw.text(50, 85, "Twice-Around-The-Tree Method");
        StdDraw.show();
        TwiceAround b = new TwiceAround(coordinates); 
    }
    public static void createGraph(int input, int[][] coordinates, EdgeWeightedGraph graph, Edge e){
        int x = 0;
        int y = 0;
        for(int i = 0; i < input; i++){  
            x = (int) (Math.random() * 100);
            y = (int) (Math.random() * 100);
            coordinates[i][0] = x;
            coordinates[i][1] = y;     
            String text = String.valueOf(i);
            StdDraw.text(coordinates[i][0], coordinates[i][1], text);
            StdDraw.show();
           
        }
        
        for(int i = 0; i < input; i++){
            for(int j = i + 1; j < input; j++){
                e = new Edge(j, i, euclid(coordinates, i, j));
                graph.addEdge(e);
            }
        }
    }
    public static double euclid(int[][] coordinates, int i, int j){
        double eudist = Math.sqrt((Math.pow((coordinates[i][1] - coordinates[i][0]), 2)) 
                        + (Math.pow((coordinates[i+1][1] - coordinates[i+1][0]), 2)));
        return eudist;
        
    }
}
