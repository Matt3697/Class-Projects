package tsp;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Matthew Sagen
 */
class Greedy {

    
    
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private Stopwatch watch;
    private EdgeWeightedGraph graph;
    private Edge e;
    private HashSet<Integer> visited;
    
    public Greedy(int[][] coordinates, EdgeWeightedGraph graph, Edge e){
       this.e = e;
       this.watch = new Stopwatch();
       visited = new HashSet<Integer>();
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
        
        for(Edge e : graph.adj(vertex)){
            edges.add(e);
        }
        Collections.sort(edges);
        for(int i = 0; i < visited.size() ; i++){
            traverse(graph, e.other(vertex), path);
        }
    }
    public void printStats(ArrayList<Integer> path){
        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i) + " -> ");
        }
        System.out.println();
        System.out.println("Total Cost: " + path.size());
        System.out.println("Time to Find: " + watch.elapsedTime());
    }
}