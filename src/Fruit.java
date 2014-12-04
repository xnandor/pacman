import java.awt.*;

/**
 * Created by natedolz on 11/28/14.
 */
public class Fruit extends GameObject{
    Room room;
    int type;
    int points;
    double creationTime = 0;
    boolean exists;

    public Fruit (Room room, int type){
        this.room = room;
        this.type = type;
        switch (type){
            case 1: points = 100;
                break;
            case 2: points = 300;
                break;
            case 3: points = 500;
                break;
            case 4: points = 700;
                break;
            case 5: points = 1000;
                break;
            case 6: points = 2000;
                break;
            case 7: points = 3000;
                break;
            case 8: points = 5000;
                break;
        }
        exists = true;
        this.boundingBox = new Rectangle((13*12)+8, 20*12+1, 11, 11);
    }

    public void draw(Graphics2D g){
        drawSprite(g,24,type - 1,5,-5,-5);
    }

    public void update(float dt){
        creationTime+= dt;
        if(creationTime >= 20000) exists = false;
    }
}
