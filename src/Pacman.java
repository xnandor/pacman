import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Pacman extends GameObject implements KeyListener {

    double totalTime = 0;
    double elapsedChompTime = 0;
    int spriteIOffset = 0;
    int velX = 0;
    int velY = 0;

    public Pacman() {
	super();
	this.boundingBox = new Rectangle((13*12)+8, 26*12+1, 8, 8);
    }

    public void update(float dt) {
	double x = boundingBox.getX();
	double y = boundingBox.getY();
	elapsedChompTime += dt;
	boundingBox.setLocation( (int)(x+velX*2), (int)(y+velY*2) );
	if (elapsedChompTime > 200) {
	    elapsedChompTime = 0;
	    if (spriteIOffset == 0) spriteIOffset = 2;
	    else spriteIOffset = 0;
	}
	if (Math.abs(velX) > Math.abs(velY)) { //Going left or right
	    if (velX > 0) {  //Right
		spriteI = 12; spriteJ= 7;
	    } else {  //Left
		spriteI = 0; spriteJ= 11;
	    }
	} else {        //Going up or down
	    if (velY > 0) {  //Down
		spriteI = 13; spriteJ= 7;		
	    } else {  //Up
		spriteI = 1; spriteJ= 11;			
	    }
	}
	if (velX == 0 && velY == 0) {
	    spriteI = 0;
	    spriteJ = 8;
	    spriteIOffset = 0;
	}
    }

    public void draw(Graphics2D g) {
	double x = boundingBox.getX();
	double y = boundingBox.getY();
	drawSprite(g, 24, spriteI+spriteIOffset,spriteJ, -7, -7);
	super.draw(g);
    }

    public void keyPressed(KeyEvent e) {
	int code = e.getKeyCode();
	if (e.VK_LEFT == code) {
	    velX = -1;
	    velY = 0;
	}
	if (e.VK_RIGHT == code) {
	    velX = 1;
	    velY = 0;
	}
	if (e.VK_UP == code) {
	    velX = 0;
	    velY = -1;
	}
	if (e.VK_DOWN == code) {
	    velX = 0;
	    velY = 1;
	}
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

