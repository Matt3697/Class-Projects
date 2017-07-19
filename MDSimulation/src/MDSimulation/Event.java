
package MDSimulation;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Event implements Comparable<Event> {
    
    private double time;
    public Particle a,b;
    private int countA,countB;
   
    public Event(double t,Particle a,Particle b){
        
        this.a = a;
        this.b = b;
        this.time = t; 
        if (a != null){
            countA = a.getCollisionCount();
            
        }
            else{
            countA = -1;
        }
        if (b != null){
            countB = b.getCollisionCount();
            }
            else{
                countB = -1;
            }
    } 
    //Returns time since start of simulation
    public double getTime(){
        return time;
    }
    //returns the first particle
    public Particle getParticle1(){
        return a;   
    }
    //returns the second particle
    public Particle getParticle2(){
        return b;
    }
   
    public boolean wasSuperveningEvent(){
        return true;
    }
    

    public boolean isValid() {
         if (a != null && a.getCollisionCount() != countA){
             return false;
         }
            if (b != null && b.getCollisionCount() != countB){
                return false;
            }
            return true;
    }

    @Override
    public int compareTo(Event a) {
       return Double.compare(this.time,a.time);
    }
}
