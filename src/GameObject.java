import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class GameObject {

    ArrayList<BufferedImage> images = new ArrayList<BufferedImage>(); //Frames to animate
    Rectangle boundingBox = new Rectangle();

    public boolean intersects(GameObject other) {
    	return true;
    }

    public void update(float dt) {
	
    }

    public void draw(Graphics2D g) {

    }

}


