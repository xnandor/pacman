import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Dot extends GameObject implements KeyListener{
	
    boolean exists = true;
    
    public boolean doesExist() {
		return exists;
	}
	
	public void consume() {
		exists = false;
	}
	
	public void update(float dt) {

    }

    public void draw(Graphics2D g) {

    }
    
    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
