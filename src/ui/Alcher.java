package ui;

import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;





import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Alcher extends JFrame {

	/**
	 * @author Atix
	 */
	
	private static final long serialVersionUID = 1L;
	
	public boolean run = true;
	private static Robot mainBot;
	private int mouseClicks = 0;
	
    JFrame frame = new JFrame();
    JButton start = new JButton("Start alching");
    JButton stop = new JButton("Stop");
    JLabel timer = new JLabel("Press start");
    JLabel timer2 = new JLabel("");
    
    
    public void clickMouse() {
       mainBot.mousePress(InputEvent.BUTTON1_MASK);
       mainBot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    
    public void beginBot() {
        repaint();
        try {
          Thread.sleep(1000);
      } catch (InterruptedException e) {
          
          e.printStackTrace();
      }
        while(run == true) {
            
             final long rng = getRandomInteger(2088, 1013);  
             
             Thread t = new Thread(new Runnable() {
         	    public void run() {
         	    	timer(rng);
         	    }
         	 });
         	 t.start();
         	 
             timer.setText("Mouseclicks: " + mouseClicks + ", next click: " + 
            		 new Double((double)rng/1000) + "s");
             try{Thread.sleep(rng);}catch(InterruptedException ie){}
             clickMouse();
             mouseClicks = mouseClicks + 1;
             if (run != false) {
            	 System.out.println("Mouse Clicked #" + mouseClicks);
                 timer.setText("Mouseclicks: " + mouseClicks);
             } else {
            	 timer.setText("Stopped");
             }
             
          
        }
       
    }
    
    
    public Alcher() {
        
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(260, 120);
        frame.setTitle("EzAlcher");
        frame.setVisible(true);
        start.setBounds(20, 36, 115, 25);
        stop.setBounds(137, 36, 90, 25);
        timer.setBounds(20, 4, 249, 12);
        timer2.setBounds(20, 20, 249, 12);
        
        frame.add(start);
        frame.add(stop);
        frame.add(timer);
        frame.add(timer2);
        
       thehandler handler = new thehandler();
       
       start.addActionListener(handler);
       stop.addActionListener(handler);
      
    }
    
    public void timer(long time) {
    	long rng = time;
    	while(run == true && rng >= 0) {
            rng = rng - 10;
            timer2.setText(new Double((double)rng/1000) + "s");
            try{Thread.sleep(10);}catch(InterruptedException ie){}         
       }
    }
    
    public static void main(String args[]) {
        
        try {
           mainBot = new Robot();
        } catch(Exception e) {
           System.out.println("Error: Autoclicker failed to start!");
        }
        
       new Alcher();
     }
     
     private class thehandler implements ActionListener{
	     public void actionPerformed(ActionEvent evt){
	    	 
	         if(evt.getSource() == start){
	             System.out.println("Starting");
	             timer.setText("Starting");
	             mouseClicks = 0;
	             run = true;
	             try{
	                 Thread.sleep(1000);
	             }catch(InterruptedException ie){};
	             
	             Thread t = new Thread(new Runnable() {
	            	    public void run() {
	            	    	beginBot();
	            	    }
	            	 });
	            	 t.start();       
	         }
	         
	         if(evt.getSource() == stop){
	             //System.out.println("Stopping in 1 click");
	             timer.setText("Stopping in 1 click");
	             timer2.setText("");
	             run = false;
	         }
	           
	     }
     }
	
}
