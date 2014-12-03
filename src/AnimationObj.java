import java.awt.*;

/**
 * Created by natedolz on 11/28/14.
 */
public class AnimationObj extends GameObject {
    public int velocity = 0;
    int speed;

    public AnimationObj(int spriteX, int spriteY, int X, int Y, int vel){
        speed = 6;
        spriteI = spriteX;
        spriteJ = spriteY;
        velocity = vel;
        this.boundingBox = new Rectangle(X, Y, 11, 11);
    }

    public AnimationObj(int spriteX, int spriteY, int X, int Y, int vel, int speed) {
        this.speed = speed;
        spriteI = spriteX;
        spriteJ = spriteY;
        velocity = vel;
        this.boundingBox = new Rectangle(X, Y, 11, 11);
    }

    public void draw(Graphics2D g, int Offset) {
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        drawSprite(g, 24, spriteI+Offset,spriteJ, -5, -5);
        super.draw(g);
    }

    public void update(float dt){
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        boundingBox.setLocation((int)x+velocity*speed,(int)y);
    }
}
