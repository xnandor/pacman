import java.awt.*;

/**
 * Created by NateDolz on 11/4/2014.
 */
public class LevelSplash extends GameObject {

    int level;

    public LevelSplash(int lvl){
        level = lvl;
    }

    public void draw(Graphics2D g) {
        //draw background
        g.setColor(Color.black);
        g.fillRect(0, 0, 336, 492);
        //draw message1
        int[] msg = {3,15,14,7,18,1,20,21,12,1,20,9,15,14,19};
        int x = 86;
        for (int i = 0; i < msg.length; i++) {
            drawSprite(g, 12, msg[i], 2, x, 12);
            x = x + 12;
        }
        //draw message 2
        int[] msg1 = {25,15,21};
        x = 84;
        for (int i = 0; i < msg1.length; i++) {
            drawSprite(g, 12, msg1[i], 2, x, 30);
            x = x + 12;
        }
        int[] msg2 = {2,5,1,20};
        x = x + 12;
        for (int i = 0; i < msg2.length; i++) {
            drawSprite(g, 12, msg2[i], 2, x, 30);
            x = x + 12;
        }
        int[] msg3 = {12,5,22,5,12};
        x = x + 12;
        for (int i = 0; i < msg3.length; i++) {
            drawSprite(g, 12, msg3[i], 2, x, 30);
            x = x + 12;
        }
        int[] msg4 = {level - 1};
        x = x + 12;
        for (int i = 0; i < msg4.length; i++) {
            drawSprite(g, 12, msg4[i], 0, x, 30);
            x = x + 12;
        }
        //draw message 3
        int[] nl = {14,5,24,20};
        x = 108;
        for (int i = 0; i <  nl.length; i++) {
            drawSprite(g, 12, nl[i], 2, x, 400);
            x = x + 12;
        }
        int[] lvl = {12,5,22,5,12};
        x = x+12;
        for (int i = 0; i <  lvl.length; i++) {
            drawSprite(g, 12, lvl[i], 2, x, 400);
            x = x + 12;
        }
        int[] nl1 = {level};
        x = 154;
        for (int i = 0; i < nl1.length; i++) {
            drawSprite(g, 12, nl1[i], 0, x, 418);
            x = x + 12;
        }
        //draw cherry
        drawSprite(g, 24, 0, 5, 144, 216);
   }
}
