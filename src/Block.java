import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Block extends GameObject {

    boolean isGhostAccessible = false;
    // WALL SPRITE LOCATIONS
    //   - start (16, 6) end (31, 6)
    //   - and start ( 0, 7) end (28, 7)
    //
    //  GENERAL
    //   - all blocks are 12x12 pixels
    //
    //  BLOCK FORMAT by blockNumber
    //
    // 0 - empty
    // 1 - double line, top right corner
    // 2 - double line, top left corner
    // 3 - double line, right wall
    // 4 - double line, left wall
    // 5 - double line, bottom right corner
    // 6 - double line, bottom left corner
    // 7 - double line, split line, bottom right corner
    // 8 - double line, split line, bottom left corner
    // 9 - double line, split line, top right corner
    // 10- double line, split line, top left corner
    // 12- double line, top wall
    // 13- double line, bottom wall
    // 15- single line, bottom wall
    // 22- single line, top wall
    // 26- single line, left wall
    // 27- single line, right wall
    // 30- double line, squared, top right corner
    // 31- double line, squared, top left corner
    // 32- double line, squared, bottom right corner
    // 33- double line, squared, bottom left corner
    // 36- single line, top left corner, inner
    // 37- single line, top right corner, inner
    // 38- single line, bottom left corner, inner
    // 39- single line, bottom right corner, inner
    // 40- single line, top right corner, outer
    // 41- single line, top left corner, outer
    // 42- single line, bottom right corner, outer
    // 43- single line, bottom left corner, outer
    //
    // 100 - dot
    // 200 - super dot
    public Block(int blockNumber, int gridX, int gridY) {
        super();
        if(blockNumber == -1) {
            isGhostAccessible = true;
        }
        blockNumber--;
        if (blockNumber < 16) {
            spriteI = 16+blockNumber;
            spriteJ = 6;
        } else {
            spriteI = blockNumber - 17;
            spriteJ = 7;
        }
        this.boundingBox = new Rectangle(12*gridX, 12*gridY, 12, 12);
    }

    public void draw(Graphics2D g) {
        drawSprite(g, 12, spriteI, spriteJ);
        super.draw(g);
    }
    
    public boolean isGhostAccessible() {
        return isGhostAccessible;
    }
}

