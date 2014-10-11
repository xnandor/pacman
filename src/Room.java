import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Room implements KeyListener {

    ArrayList<GameObject> scene = new ArrayList<GameObject>();
    Pacman currentPacman;
    int numLives = 3;
    long score = 0L;
    int y = 0; //for debug
    int frame = 0; //for debug

    public Room(int level) {
    	currentPacman = new Pacman();
    }

    public void update(float dt) {
	currentPacman.update(dt);

	if (PacmanGame.DEBUG) {
	    y = (int)(100*Math.sin((double)this.frame/10)) + 200;
	    frame++;
	}
    }

    public void draw(Graphics2D g) {
	//Setup background color
	g.setColor(Color.black);
	//Fill background
	g.fillRect(0 , 0 , 280 , 400);
	currentPacman.draw(g);

	if (PacmanGame.DEBUG) {
	    g.setColor(Color.white); //DEMO...DELETE LATER
	    g.drawLine(0, y, 280, y); //DEMO...DELETE LATER
	}
    }

    public void keyPressed(KeyEvent e) {
	currentPacman.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

