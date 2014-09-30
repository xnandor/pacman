import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;

public class Ghost extends GameObject {
    
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
    
}

