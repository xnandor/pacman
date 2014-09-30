import java.awt.Graphics2D;

public class Dot extends GameObject {
	
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

}
