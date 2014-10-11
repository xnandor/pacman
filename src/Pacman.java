import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Pacman extends GameObject implements KeyListener {

    boolean isSuper = false; 
    boolean isLiving = true; 
    int lives = 2;           
	
    public void moveUp(){
	boundingBox.setLocation((int)boundingBox.getX(), (int)boundingBox.getY()-1);
    }
			
    public void moveLeft()
    {
	boundingBox.setLocation((int)boundingBox.getX()-1, (int)boundingBox.getY());
    }
	
    public int extraLife()
    {
	int points = 10000;
	return points;
    }
	
    public void update(float dt) {

    }

    public void draw(Graphics2D g) {
	Color color = Color.green;
	g.setColor(color);
	g.draw(boundingBox);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

