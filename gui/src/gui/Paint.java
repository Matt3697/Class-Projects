package swing


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Paint extends JFrame
	Paint()
	{
	   super("Paint Program");
	   setSize(800, 500);
	   PaintPanel pp = new PaintPanel();
	   getContentPane().add(pp, BorderLayout.CENTER);
	   getContentPane().add(new ButtonPanel(pp), BorderLayout.WEST);
	   setVisible(true);
	   
	}
   	public static void main(String [] args)
	{
	   new Paint();    
	   
	   
	}

	
}
