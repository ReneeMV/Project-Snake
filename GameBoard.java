import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

/**
 * Snake Game
 * Mr. Maroney Period 4
 * @author Renee Veit, Juliana Guerra, Senpai Horowitz
 * June 2nd, 2018
 */

//gameBoard is the combination of SnakePanel and the Food class
public class GameBoard extends JPanel implements ActionListener{
	//JPanel is where the game takes place
	//ActionListener controls all of the obj's actions
	//ActionListener is implemented in the JPanel
    
   ArrayList<RectangleObj> snake;
   RectangleObj food;
   Image bgImage;
   ArrayList <Integer> indicator;
    
   //initial positions
	int xSnake = 50;
	int ySnake  = 50;
	int xFood = 25;
	int yFood = 25;
    
	int pWidth = 990;
	int pHeight = 990;
	int wdth = 100;
	int hgt = 100;
    
	Timer clock;
    
	int score = 0;
	int i;
	int j;
	int x;
	int compare;
	int compare2;

	boolean collision;
	boolean bounds;
    
	public GameBoard(){ //constructor
    	//properties of JPanel
    	Dimension dim = new Dimension(pWidth, pHeight);
   	 
    	this.setPreferredSize(dim);
    	this.setBackground(Color.yellow);
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setFocusable(true);
   	 
    	this.addKeyListener(new KeyHandler());
   	 
    	//initializing rect objs
    	snake = new ArrayList<RectangleObj>();
    	snake.add(new RectangleObj(300, 300, 50, 50, Color.green));
   	 
    	food = new RectangleObj(500,500, 25,25, Color.red);
   	 
    	i = 0;
    	indicator = new ArrayList<Integer>();
    	indicator.add(0);
   	 
    	clock = new Timer(100, this);
    	clock.start();
        
        MediaTracker mt = new MediaTracker(this);
        bgImage = Toolkit.getDefaultToolkit().getImage("http://hillswestroundup.com/wp-content/uploads/2013/03/IMG_0978.jpg");
        mt.addImage(bgImage, 0);
        mt.addImage(bgImage, 0);
        
        try{
            mt.waitForAll();
            
        }catch (InterruptedException e){
            System.out.println("error" + e);
        }
        
	}

	//calls the render method in the rectangle obj
	private void drawRectangles(Graphics g){
   	while( i < snake.size())
   	{
       	snake.get(i).render(g);
       	i++;
   	}
   	i = 0;
   	food.render(g);
	}
    
	//certain operations are handled by paintComponent
	//such as drawing objs and collisions
	//I;m not 100% sure what paintComponent does

 	@Override
	public void paintComponent(Graphics g){
   	 
    	super.paintComponent(g);
    	drawRectangles(g);
        g.drawImage(bgImage, 0, 0, this);
   	 
    	
   	 
    	if (collision){
        	g.drawString("Score = " + score, 500, 100);
        	Font stringFont = new Font( "SansSerif", Font.PLAIN, 30 );
        	score++;
    	} else {
        	g.drawString("Score = " + score, 500, 100);
       	 
    	}
   	 
    	
	 
       	 
   	 
   	 
	}
    
   
	@Override
	public void actionPerformed(ActionEvent ae) {
    	repaint();
    	/*
    	keeping track of the changes in the snake's
    	current position, and redrawing the snake
    	to make it look like it's moving
    	*/
   	 
    	int x = snake.size() - 1;
     	if(isCollision(snake.get(0), food))
    	{
        	i = indicator.size() - 1;
        	if(indicator.get(i) == 1)
            	{
                	snake.add(new RectangleObj(snake.get(x).getX() - 60,snake.get(x).getY(),50,50, Color.green));
            	}
            	else if(indicator.get(i) == 2)
            	{
                	snake.add(new RectangleObj(snake.get(x).getX() + 60,snake.get(x).getY(),50,50, Color.green));
            	}
            	else if(indicator.get(i) == 3)
            	{
                	snake.add(new RectangleObj(snake.get(x).getX(),snake.get(x).getY() + 60,50,50, Color.green));
            	}
            	else if(indicator.get(i) == 4)
            	{
                	snake.add(new RectangleObj(snake.get(x).getX(),snake.get(x).getY() - 60,50,50, Color.green));
            	}
       	 
        	int indicatorVar = indicator.get(i);
        	indicator.add(indicatorVar);
        	food.setX((int) ((pWidth - 10) * Math.random()));
        	if (food.getX() <= 5  || food.getX() >= 980)
        	{
            	food.setX(6);
        	}
        	food.setY((int) ((pHeight - 10) * Math.random()));
        	if (food.getY() <= 5 || food.getY() >= 980)
        	{
            	food.setY(6);
        	}    
    	}
    
    	if (indicator.get(0) == 1)
    	{
        	snake.get(0).setX(snake.get(0).getX()+10);
        	i = indicator.size() - 1;
       	 
        	while( i > 0)
        	{
            	j = indicator.get(i -1);
            	compare = snake.get(i).getX() - snake.get(i - 1).getX();
            	compare2 = snake.get(i).getY() - snake.get(i - 1).getY();
            	if((j == 1 || j == 2) && compare2 == 0)
            	{
                	indicator.set(i, j);
            	}
            	else if((j == 3 || j == 4) && compare == 0)
            	{
                	indicator.set(i, j);
            	}
           	 
            	if (indicator.get(i) == 2)
            	{
                	snake.get(i).setX(snake.get(i).getX()-10);
            	}
            	else if(indicator.get(i) == 3)
            	{
                	snake.get(i).setY(snake.get(i).getY()-10);
            	}
            	else if(indicator.get(i) == 4)
            	{
                	snake.get(i).setY(snake.get(i).getY()+10);
            	}
            	else
            	{
                	snake.get(i).setX(snake.get(i).getX()+10);
            	}
            	i--;
        	}
        	i = 0;
    	}
    	else if(indicator.get(0) == 2)
    	{
        	snake.get(0).setX(snake.get(0).getX()-10);
        	i = indicator.size() - 1;
        	while( i > 0)
        	{
            	j = indicator.get(i -1);
            	compare = snake.get(i).getX() - snake.get(i - 1).getX();
            	compare2 = snake.get(i).getY() - snake.get(i - 1).getY();
            	if((j == 1 || j == 2) && compare2 == 0)
            	{
                	indicator.set(i, j);
            	}
            	else if((j == 3 || j == 4) && compare == 0)
            	{
                	indicator.set(i, j);
            	}
           	 
            	if (indicator.get(i) == 2)
            	{
                	snake.get(i).setX(snake.get(i).getX()-10);
            	}
            	else if(indicator.get(i) == 3)
            	{
                	snake.get(i).setY(snake.get(i).getY()-10);
            	}
            	else if(indicator.get(i) == 4)
            	{
                	snake.get(i).setY(snake.get(i).getY()+10);
            	}
            	else
            	{
                	snake.get(i).setX(snake.get(i).getX()+10);
            	}
            	i--;
        	}
        	i = 0;
    	}
    	else if(indicator.get(0) == 3)
    	{
        	snake.get(0).setY(snake.get(0).getY()-10);
        	i = indicator.size() - 1;
        	while( i > 0)
        	{
            	j = indicator.get(i -1);
            	compare = snake.get(i).getX() - snake.get(i - 1).getX();
            	compare2 = snake.get(i).getY() - snake.get(i - 1).getY();
            	if((j == 1 || j == 2) && compare2 == 0)
            	{
                	indicator.set(i, j);
            	}
            	else if((j == 3 || j == 4) && compare == 0)
            	{
                	indicator.set(i, j);
            	}
           	 
            	if (indicator.get(i) == 2)
            	{
                	snake.get(i).setX(snake.get(i).getX()-10);
            	}
            	else if(indicator.get(i) == 3)
            	{
                	snake.get(i).setY(snake.get(i).getY()-10);
            	}
            	else if(indicator.get(i) == 4)
            	{
                	snake.get(i).setY(snake.get(i).getY()+10);
            	}
            	else
            	{
                	snake.get(i).setX(snake.get(i).getX()+10);
            	}
            	i--;
        	}
        	i = 0;
    	}
    	else if (indicator.get(0) == 4)
    	{
        	snake.get(0).setY(snake.get(0).getY()+10);
        	i = indicator.size() - 1;
        	while( i > 0)
        	{
            	j = indicator.get(i -1);
            	compare = snake.get(i).getX() - snake.get(i - 1).getX();
            	compare2 = snake.get(i).getY() - snake.get(i - 1).getY();
            	if((j == 1 || j == 2) && compare2 == 0)
            	{
                	indicator.set(i, j);
            	}
            	else if((j == 3 || j == 4) && compare == 0)
            	{
                	indicator.set(i, j);
            	}
           	 
            	if (indicator.get(i) == 2)
            	{
                	snake.get(i).setX(snake.get(i).getX()-10);
            	}
            	else if(indicator.get(i) == 3)
            	{
                	snake.get(i).setY(snake.get(i).getY()-10);
            	}
            	else if(indicator.get(i) == 4)
            	{
                	snake.get(i).setY(snake.get(i).getY()+10);
            	}
            	else
            	{
                	snake.get(i).setX(snake.get(i).getX()+10);
            	}
            	i--;
        	}
        	i = 0;
    	}
    	if (snake.get(0).getX() >= 940 || snake.get(0).getX() <= 0)
    	{
        	clock.stop();
    	}
    	else if (snake.get(0).getY() >= 940 || snake.get(0).getY() <= 0)
    	{
        	clock.stop();
    	}
	 
	}

    
    
    
	public class KeyHandler extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent e) {
            	 
        	// get the code that belongs to each key on the keyboard
        	String key = KeyEvent.getKeyText(e.getKeyCode());
   	 
        	//the right arrow key moves the snake forward
        	i = 1;
        	if(snake.get(0).getX() >= 940 || snake.get(0).getX() <= 0)
        	{
            	key = "";
        	}
        	else if (snake.get(0).getY() >= 940 || snake.get(0).getY() <= 0)
        	{
            	key = "";
        	}
        	if(key.equals("Right")){ // Right arrow key
           	 
            	indicator.set(0, 1);
          	 
        	}
        	//the left arrow key moves the snake in the opposite direction
        	if(key.equals("Left")) // Left arrow key
        	{   
            	indicator.set(0, 2);
    	 
        	}
        	//the up arrow key moves the snake upwards
        	if(key.equals("Up")) // Up arrow key
        	{  
            	indicator.set(0, 3);
    	 
        	}
        	//the down arrow key moves the snake in the opposite direction
        	if(key.equals("Down")) // Down arrow key
        	{   
            	indicator.set(0, 4);
    	 
        	}
	 
        	// System.out.println(e + " keypress");
    	}
    	//end keypressed
	}
	 
	private boolean isCollision(RectangleObj snake, RectangleObj food)
	{
    	collision = snake.getBounds().intersects(food.getBounds());
    	return collision;
	 
	}


}





