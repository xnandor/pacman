import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Pacman extends GameObject implements KeyListener {

	boolean isSuper = false;
	boolean isLiving = true;
	int xPos;
	int yPos;
	
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
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void moveUp(){
		yPos ++;
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

