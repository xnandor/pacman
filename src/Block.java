import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Block extends GameObject implements KeyListener {

    int i = 0;
    int j = 0;

    ////// from (16, 6) - (31, 6)
    ////// from ( 0, 7) - (28, 7)
    // 1 - up wall and down wall (27,6)
    // 2 - down wall and down wall (28,6)

    public Block(int blockNumber, int gridX, int gridY) {
	super();
	blockNumber--;
	if (blockNumber < 16) {
	    i = 16+blockNumber;
	    j = 6;
	} else {
	    i = blockNumber - 17;
	    j = 7;
	}
	this.boundingBox = new Rectangle(12*gridX, 12*gridY, 12, 12);
    }

    public void draw(Graphics2D g) {
	drawSprite(g, 12, i, j);
	super.draw(g);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

