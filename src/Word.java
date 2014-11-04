import java.awt.*;

/**
 * Created by natedolz on 11/2/14.
 */
public class Word extends GameObject {

    int[] word;
    int xOFF;
    int yOFF;

    public Word(int[] str, int x, int y){
        word = str;
        xOFF = x;
        yOFF = y;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i< word.length; i++){
            drawSprite(g,12,word[i],2,xOFF,yOFF);
            xOFF = xOFF + 12;
        }
    }

}
