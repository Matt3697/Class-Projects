/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Matthew Sagen
 * Prim
 */
class TwiceAround {
    private ArrayList<Integer> edges = new ArrayList<Integer>();
    private Stopwatch watch;
    private double[][] coords;
    private PrimMST tree;
    private EdgeWeightedGraph graph;
    private HashSet visited;
    private Edge e;
    public TwiceAround(int[][] coordinates){
       
       
    }
    
    public void mst(int[][] coordinates){
       
    }
    public void traverse(EdgeWeightedGraph graph, int vertex, ArrayList<Integer> path){//traverse the tree recursively
        //base cases
        if(visited.contains(vertex)){
            return;
        }
        if(vertex > graph.V()){
            return;
        }
        if(visited.size() >= graph.V()){
            return; 
        }
        Iterable<Edge> next = graph.adj(vertex);
        path.add(vertex);
        visited.add(vertex);
        tree.edges();
        for(Edge e : tree.edges()){
            e.add(e);
        }
      
        Collections.sort(edges);
        for(int i = 0; i < visited.size() ; i++){
            traverse(graph, e.other(vertex), path);
        }
    }
    
}
