/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg439;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;


/**
 *
 * @author Matthew Sagen
 * Exercise: 4.3.9
 * Pages 631 and 526
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    
    
    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj =(Bag<Edge>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Edge>();
        }
        
    }
    public EdgeWeightedGraph(In in){//Modify this constructor to read an edge-weighted graph from input stream.
        this(in.readInt());
        int E = in.readInt();
        Edge e;
        EdgeWeightedGraph graph;
        int[][] coordinates = new int[E][2];
        for(int i = 0; i < E; i++){
            int v = in.readInt();
            int w = in.readInt();
            coordinates[i][0] = v;
            coordinates[i][1] = w;
        }
        for(int i = 0; i< E; i++){
            for(int j = 0; i < E; i++){
                e = new Edge(j, i, euclid(coordinates, i, j));
                graph.addEdge(e);
            }
        }
    }
     public double euclid(int[][] coordinates, int i, int j){
        double eudist = Math.sqrt((Math.pow((coordinates[i][1] - coordinates[i][0]), 2)) 
                        + (Math.pow((coordinates[i+1][1] - coordinates[i+1][0]), 2)));
        return eudist;
        
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    
    
}
