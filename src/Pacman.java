import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Pacman extends GameObject implements KeyListener {

	boolean isSuper = false;
	boolean isLiving = true;
	
	public boolean isSuperPacman() {
		return isSuper;
	}
	
	public boolean isAlive() {
		return isLiving;
	}
	
	public void kill() {
		isLiving = false;
	}
	
	public void powerUp() {
		isSuper = true;
	}
			
	public void moveLeft()
	{
		
		xPos--;
	}
	
	public int extraLife()
	{
		int points = 10000;
		return points;
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

