import java.awt.Rectangle;
import java.awt.Graphics2D;

public class SuperDot extends GameObject {
    int spriteOffset = 0;
    int movingPhase = -1;

    public SuperDot(int gridX, int gridY) {
        super();
        this.boundingBox = new Rectangle(12*gridX+4, 12*gridY+4, 4, 4);
    }


    public void update(float dt) {
        movingPhase++;
        if (movingPhase%6 < 2) {
            spriteOffset=0;
        }
        if (movingPhase%6 > 2) {
            spriteOffset=5;
        }
    }

    public void draw(Graphics2D g) {
        drawSprite(g, 12, 20+spriteOffset, 0, -4, -4);
        super.draw(g);
    }

}
