import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Dot extends GameObject {

    public Dot(int gridX, int gridY) {
	super();
	this.boundingBox = new Rectangle(12*gridX+4, 12*gridY+4, 4, 4);
    }


    public void update(float dt) {

    }

    public void draw(Graphics2D g) {
	drawSprite(g, 12, 17, 0, -4, -4);
	super.draw(g);
    }

}
