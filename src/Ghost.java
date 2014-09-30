import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Ghost extends GameObject implements KeyListener {
    
    boolean isLiving = true;
    boolean isEatable = false;
    
    public boolean isAlive() {
        return isLiving;
    }
    
    public void kill() {
        isLiving = false;
    }
    
    public void eatable() {
        isEatable = true;
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

