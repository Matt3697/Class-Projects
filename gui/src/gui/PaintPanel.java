import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class PaintPanel extends JPanel
{
	int startX, flag, startY, endX, endY, width, height;
	private BufferedImage paintImage = new BufferedImage(300,300, BufferedImage.TYPE_3BYTE_BGR);
	
	public PaintPanel()
	{
	   startX = startY =0;
	   endX=100;
	   endY=100;
	   flag = 0;
	   width = 0;
	   height = 0;
	  
       addMouseListener(new MouseComp());
 	}

 	public void clear()
 	{
 	    repaint();
 	}
 	@Override
    public void paintComponent(Graphics g)
    {  
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         if(grid == null){
            int w = this.getWidth();
            int h = this.getHeight();
            grid = (BufferedImage)(this.createImage(w,h));
            gc = grid.createGraphics();
            
         }
         g2.drawImage(grid, null, 0, 0);
         
     }
    	BufferedImage grid;
    	Graphics2D gc;
    	
    public void updatePaint(){
    	Graphics g = paintImage.createGraphics();
    	g.dispose();
    	repaint();
    }
    public void save() throws IOException{
    	ImageIO.write(paintImage, "PNG", new File("filename.png"));
    	repaint();
    }
    public void load() throws IOException{
    	paintImage = ImageIO.read(new File("filename.png"));
    	repaint();
    }
    public void setFlag(int flag){
    	this.flag = flag;
    }
	
    public void drawing(int width, int height)
    {
    	
        Graphics2D g2 = (Graphics2D)getGraphics();
        if(flag == 0){
        	clear();
        }
        if(flag == 1){
            g2.fillRect(startX, startY, width, height);
        }
        else if(flag == 2){
        	g2.fillOval(startX, startY, width, height);
        }
        else if(flag == 3){
        	g2.drawRect(startX, startY, width, height);
        }
        else if(flag == 4){
        	g2.drawOval(startX, startY, width, height);
        }
        else if(flag == 5){
        	g2.drawLine(startX, startY, endX, endY);
        }
        
    }
    
	public class MouseComp implements MouseListener
	{
	   public void mouseClicked(MouseEvent e) {
		   
	   }
       public void mouseEntered(MouseEvent e) {
    	   System.out.println("("+e.getX() + "," + e.getY() + ")");
       }    
       public void mousePressed(MouseEvent e) 
       {
          startX = e.getX();
	      startY = e.getY();
	      
	      System.out.println("Mouse Pressed at: " + "("+ startX + "," + startY + ")");
	      
	   }
	   
       public void mouseReleased(MouseEvent e){
    	   endX = e.getX();
    	   endY = e.getY();
    	   System.out.println("Mouse Released at: " + "("+ endX + "," + endY + ")");
    	   int width = Math.abs(endX - startX);
    	   int height = Math.abs(endY - startY);
    	   if(flag ==5){
    		   drawing(0,0);
    	   }
    	   else{

    	   if(startX > endX){ //right to left
    	   	endX = startX;
    	   	startX = e.getX();
    	   }
    	   if(startY > endY){
    	   	endY = startY;
    	   	startY = e.getY();
    	   }
    	   drawing(width, height);
    	   }
    	   
       } 
       public void mouseExited(MouseEvent evt){
    	   System.out.println("(" + evt.getX() + "," + evt.getY() + ")");
       }
       
	}
}
