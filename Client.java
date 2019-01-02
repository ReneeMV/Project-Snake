/**
 * 5/24/18
 * @author Renee Veit
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

public class Client {

    
    public static void initializeJFrame(JFrame frame){
        
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setTitle("SNAKE");
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        
        
    }
   public static void main(String[] args) {
       //create window, panel, and dim objects
       
    	JFrame window = new JFrame();
    	
           
        //set size / background of panel
    	//panelOne.setPreferredSize(dim);
    	//panelOne.setBackground(Color.red);
        
        //add panel to window + properties
        GameBoard gb = new GameBoard();
    	window.add(gb);
        window.setLayout(new GridBagLayout());
        window.add(gb, new GridBagConstraints());
        
        initializeJFrame(window);
        
	}
    
   
}