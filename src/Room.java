import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Room {

    ArrayList<GameObject> scene = new ArrayList<GameObject>();
    int numLives = 3;
    long score = 0L;
    int y = 0; //DEMO...DELETE LATER
    int frame = 0; //DEMO...DELETE LATER
       
    public Room(int level) {
    	
    }

    public void update(float dt) {
	y = (int)(100*Math.sin((double)this.frame/10)) + 200; //DEMO...DELETE LATER
	frame++; //DEMO...DELETE LATER
    }

    public void draw(Graphics2D g) {
	g.setColor(Color.black); //DEMO...DELETE LATER
	g.fillRect(0 , 0 , 280 , 400); //DEMO...DELETE LATER
	g.setColor(Color.red); //DEMO...DELETE LATER
	g.drawLine(0, y, 280, y); //DEMO...DELETE LATER
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

