package MDSimulation;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @authors Matthew Sagen and James Mitchell
 */
public class CollisionSystem {
    private static Scanner scanner;
    private static ArrayList<Double> input = new ArrayList<Double>(); 
    private static ArrayList<Particle> partArray = new ArrayList<Particle>();
    private static double n, radius, rx, ry, vx, vy, mass, red,green,blue,t,hz;
    private static int limit = 10000;
    private static MinPQ<Event> priorityQ = new MinPQ<Event>();
    private static Event e;
    
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setScale(-2,+2);
        StdDraw.setCanvasSize(400, 400);
        StdDraw.enableDoubleBuffering();
        int counter = 1;
        hz = 0.5;
        
        //Opens file location
        try{
            scanner = new Scanner(new File("C:\\Users\\guita\\Documents\\NetBeansProjects\\input.txt"));
        }
        //throws error if the file can't be located.
        catch(Exception e){
            System.out.println("File not found.");
        }
        while(scanner.hasNextDouble()){
            input.add(scanner.nextDouble()); 
        }
        
        //stores the data from the file to their respective variables
        double n = input.get(0);//returns the number of particles
            for(int i = 0 + counter; i <= n; i++){  
                rx = input.get(counter);//starting x-coordinate of a particle
                ry = input.get(counter+1);//starting y-coordinate of a particle
                vx = input.get(counter+2);//starting velocity in the x direction
                vy = input.get(counter+3);//starting velocity in the y direction
                radius = input.get(counter+4);//mass of the particle
                mass = input.get(counter+5);//radius of the particle
                red = input.get(counter+6);//red color value 0-255
                green = input.get(counter+7);//green color value 0-255
                blue = input.get(counter+8);//blue color value 0-255  
                Color color = new Color((int)red,(int)green,(int)blue);
                counter += 9;
                partArray.add(new Particle (rx,ry,vx,vy,radius,mass,color));//creates new particle with given specifications
                }
        animate(partArray);
        
    }
    public static void animate(ArrayList<Particle> partArray){
        for (int i = 0; i < partArray.size(); i++) {
            predict(partArray.get(i), limit);
        }
        priorityQ.insert(new Event(0, null, null)); 
        while(!priorityQ.isEmpty()){
               e = priorityQ.delMin();
               if(!e.isValid()){
                   continue;
               }
               Particle a = e.a;
               Particle b = e.b;
               for(int i = 0; i < partArray.size(); i++){
                   partArray.get(i).move(e.getTime()-t); 
               }
                   t = e.getTime();
               
                   if(a != null && b != null){// particle collision
                        a.bounce(b);
                        
                       
                    }                   
                   else if (a != null && b == null){// particle-wall collision
                        a.bounceX();
                        
                        
                   }   
                   else if (a == null && b != null){
                        b.bounceY();
                        
                   } 
                   else if(a == null && b == null){
                        redraw(limit);
                   }  
               
               predict(a, limit);
               predict(b, limit);
               
           }
       
        }  
    public static void predict(Particle a, double limit){
        if(a == null){
            return;
        }
        for(int i = 0; i < partArray.size(); i++){
           
            double timeHit = a.collides(partArray.get(i));
            if (t + timeHit <= limit){
                priorityQ.insert(new Event(t + timeHit, a, partArray.get(i)));
               
            }
        }
        double tX = a.collidesX();
        double tY = a.collidesY();
        if(tX == 0){
            a.bounceX();
        }
        if(tY == 0){
            a.bounceY();
        }
        
        if (t + tX <= limit){
            priorityQ.insert(new Event(t + tX, a, null));
           
        }
        if (t + tY <= limit){
            priorityQ.insert(new Event(t + tY, null, a));
        }
    }
    private static void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < partArray.size(); i++) {
            partArray.get(i).draw();
            
        }
        
        StdDraw.show();
        StdDraw.pause(20);
        if (t < limit) {
            priorityQ.insert(new Event((t + 1.0 / hz), null, null));
        }
    }
}