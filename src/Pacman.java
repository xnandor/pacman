import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Pacman extends GameObject implements KeyListener {

    boolean isSuper = false;
    boolean isLiving = true;
    int lives = 2;
	
    public boolean isSuperPacman() {
	return isSuper;
    }
	
    public boolean isAlive() {
	return isLiving;
    }
    
    public boolean isOutOfLives() {
        if(lives > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public void loseLife() {
        lives--;
    }
    
    public void kill() {
	isLiving = false;
    }
	
    public void powerUp() {
	isSuper = true;
    }
	
    public int getX() {
	return boundingBox.getX();
    }
	
    public int getY() {
	return boundingBox.getY();
    }
	
    public void moveUp(){
	boundingBox.setLocation(boundingBox.getX(), boundingBox.getY()-1);
    }
			
    public void moveLeft()
    {
	boundingBox.setLocation(boundingBox.getX()-1, boundingBox.getY());
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

