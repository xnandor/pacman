import java.awt.*;

/**
 * Created by natedolz on 11/2/14.
 */
public class Number extends GameObject {

    int[] num;
    int xOFF;
    int yOFF;

    public Number(int[] Sprite, int x,int y){
        num = Sprite;
        xOFF = x;
        yOFF = y;
    }

    public void draw(Graphics2D g){
        for(int i =0; i<num.length;i++){
            drawSprite(g,12,num[i],0,xOFF,yOFF);
            xOFF = xOFF + 12;
        }
    }
}
