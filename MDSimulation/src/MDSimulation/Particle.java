package MDSimulation;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Matthew Sagen and Jimmy Mitchell
 */
public class Particle {
    private double rx,ry,vx,vy,mass,radius,timeX,timeY,distancex,distancey,
            velocityx,velocityy,velPos;
    private int count;
    private final Color color;
    private Particle b;
    
    
    public Particle(double rx, double ry, double vx,double vy, double radius,double mass, Color color){
     this.rx = rx;
     this.ry = ry;
     this.vx = vx;
     this.vy = vy;
     this.radius = radius;
     this.mass = mass;
     this.count = count;
     this.color = color;
     this.timeX = timeX;
     this.timeY = timeY;
     StdDraw.setPenColor(color);
     
    }
    //returns time until collision with verticle wall.
    public double collidesX(){
        if(vx > 0){
            timeX = (radius-rx)/vx;
        }
        if(vx < 0){
            timeX = (radius - rx)/vx;
        }
        else{
            timeX = Double.POSITIVE_INFINITY;
        }
        return timeX;
    }
    //returns time until collision with horizanlte wall.
    public double collidesY(){
        if(vy > 0){
            timeY = (2-radius-ry)/vy;
        }
        if(vy < 0){
            timeY = (radius - ry)/vy;
        }
        else{
            timeY = Double.POSITIVE_INFINITY;
        }
        return timeY;
    }
    public double collides(Particle b){
        if(this == b){
            return Double.POSITIVE_INFINITY;
        }
        distancex  = b.rx - this.rx;
        distancey  = b.ry - this.ry;
        velocityx = b.vx - this.vx;
        velocityy = b.vy - this.vy;
        velPos = (distancex*velocityx) + (distancey*velocityy);
        if (velPos > 0){
            return Double.POSITIVE_INFINITY;
        }
        double velSquare = velocityx*velocityx + velocityy*velocityy;
        double posSquare = distancex*distancex + distancey*distancey;
        double sigma = this.radius + b.radius;
        double d = (velPos*velPos) - velSquare * (posSquare - sigma*sigma);
        if (posSquare < sigma*sigma){
            StdOut.println("overlapping particles");
        }
        if (d < 0){
            return Double.POSITIVE_INFINITY;
        }
        return -(velPos + Math.sqrt(d)) / velSquare;
    }
    public void bounceX(){
        vx = -vx;
        count++;
       
    }
    public void bounceY(){
        vy = -vy;
        count++;
    }
    public void bounce(Particle b){
       distancex  = b.rx - this.rx;
       distancey  = b.ry - this.ry;
       velocityx = b.vx - this.vx;
       velocityy = b.vy - this.vy;
       velPos = distancex*velocityx + distancey*velocityy;            
       double distance = this.radius + b.radius;   
       double mag= 2 * this.mass * b.mass * velPos / ((this.mass + b.mass) * distance);
       double forcex = mag * distancex / distance;
       double forcey = mag * distancey / distance;
       this.vx += forcex / this.mass;
       this.vy += forcey / this.mass;
       b.vx -= forcex / b.mass;
       b.vy -= forcey / b.mass;
       this.count++;
       b.count++;
    }
    public double kE(){//calculates kinetic energy
        return 0.5 * mass * (vx*vx + vy*vy);
    }
    public int getCollisionCount(){
        return count;
    }
    public double getXLocation(){
        return rx;
    }
    public double getYLocation(){
        return ry;
    }
    public double getRadius(){
        return radius;
    }
    public void move(double time){
       
        rx += vx * time;
        ry += vy * time;
    }
    public void draw(){
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(getXLocation(),getYLocation(),getRadius());
    }
    
}
