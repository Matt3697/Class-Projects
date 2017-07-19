package swing

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel extends JPanel
{
    PaintPanel pp;

    ButtonPanel(PaintPanel pp)
    {
       this.pp = pp;
       JButton clear = new JButton("Clear");
       JButton filledRect = new JButton("Filled Rectangle");
       JButton filledOv = new JButton("Filled Oval");
       JButton emptyRect = new JButton("Empty Rectangle");
       JButton emptyOv = new JButton("Empty Oval");
       JButton lineDraw = new JButton("Line Drawing");
       filledRect.addActionListener(new ButtonListener());
       filledOv.addActionListener(new ButtonListener());
       emptyRect.addActionListener(new ButtonListener());
       emptyOv.addActionListener(new ButtonListener());
       lineDraw.addActionListener(new ButtonListener());
       clear.addActionListener(new ButtonListener());
       setLayout(new GridLayout(10, 1));
       setBackground(Color.BLUE);
       add(clear);//adds the buttons to the frame
       add(filledRect);
       add(filledOv);
       add(emptyRect);
       add(emptyOv);
       add(lineDraw);
       
    }

	public class ButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent ae)
	   {
	       String command = ae.getActionCommand();
	       switch(command){
	       case "Clear":
	    	   pp.clear();  
	    	   break;
	       case "Filled Rectangle":
	    	   pp.setFlag(1);
	    	   break;
	       case "Filled Oval":
	    	   pp.setFlag(2);
	    	   break;
	       case "Empty Rectangle":
	    	   pp.setFlag(3);
	    	   break;
	       case "Empty Oval":
	    	   pp.setFlag(4);
	    	   break;
	       case "Line Drawing":
	    	   pp.setFlag(5);
	    	   break;
	    	   }
	       }
	   }
	}