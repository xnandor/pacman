import java.awt.*;

/**
 * Created by natedolz on 11/2/14.
 */
public class Symbols extends GameObject {

    int[] symbolI;
    int[] symbolJ;
    int xOFF;
    int yOFF;
    int Size;

    public Symbols(int size, int[] strI, int[] strJ, int x, int y){
        symbolI = strI;
        symbolJ = strJ;
        xOFF = x;
        yOFF = y;
        Size = size;
    }

    public void draw(Graphics2D g){
        for(int i = 0; i< symbolI.length; i++){
            drawSprite(g,Size,symbolI[i],symbolJ[i],xOFF,yOFF);
            xOFF = xOFF + Size;
        }
    }

}
