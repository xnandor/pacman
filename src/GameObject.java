import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class GameObject {

    public static BufferedImage spriteMap;
    static {
	try {
	    spriteMap = ImageIO.read(new File("./media/spritemap-alpha.png")); //Frames to animate
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
    public Rectangle boundingBox = new Rectangle();
    public int spriteI = 0;
    public int spriteJ= 0;

    public GameObject() {

    }

    public boolean intersects(GameObject other) {
	Rectangle otherRect = other.boundingBox;
	Rectangle rect = this.boundingBox;
    	return rect.intersects(otherRect);
    }

    public void update(float dt) {
	
    }

    public void draw(Graphics2D g) {
	if (PacmanGame.DEBUG) {
	    g.setColor(Color.red);
	    g.draw(boundingBox);
	}
    }

    public void drawSprite(Graphics2D g,int size, int i, int j) {
	int offset = 4;
	double x = boundingBox.getX();
	double y = boundingBox.getY();
	g.clipRect((int)x, (int)y, size, size);
	g.drawImage(GameObject.spriteMap, new AffineTransform(1f , 0f , 0f , 1f , x-offset-(i*size), y-offset-1-(j*size)), null);
	g.setClip(new Rectangle(-PacmanGame.WIDTH, -PacmanGame.HEIGHT, PacmanGame.WIDTH*2, PacmanGame.HEIGHT*2));
    }

    public void drawSprite(Graphics2D g,int size, int i, int j, int offsetX, int offsetY) {
	int offset = 4;
	double x = boundingBox.getX();
	double y = boundingBox.getY();
	g.clipRect((int)x+offsetX, (int)y+offsetY, size, size);
	g.drawImage(GameObject.spriteMap, new AffineTransform(1f , 0f , 0f , 1f , x-offset-(i*size)+offsetX, y-offset-1-(j*size)+offsetY), null);
	g.setClip(new Rectangle(-PacmanGame.WIDTH, -PacmanGame.HEIGHT, PacmanGame.WIDTH*2, PacmanGame.HEIGHT*2));
    }


}


