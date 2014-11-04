
import java.awt.*;

/**
 * Created by natedolz on 11/2/14.
 */
public class Menu extends GameObject{

    public Menu() {

    }

    public void draw(Graphics2D g) {
        //Draw Background
        g.setColor(Color.black);
        int[] PacmanI = {16, 1, 3, 13, 1, 14};
        g.fillRect(0, 0, 336, 492);
        int x = 120;
        //draw pacman title
        for (int i = 0; i < PacmanI.length; i++) {
            drawSprite(g, 12, PacmanI[i], 2, x, 5);
            x = x + 12;
        }
        //draw pacman running
        drawSprite(g, 24, 0, 11, 100, 216);
        //draw ghosts chasing
        drawSprite(g, 24, 4, 7, 130, 216);
        drawSprite(g, 24, 4, 9, 154, 216);
        drawSprite(g, 24, 12, 9, 178, 216);
        drawSprite(g, 24, 4, 10, 202, 216);
        //draw text
        int[] Word = {16, 18, 5, 19, 19};
        x = 56;
        for (int i = 0; i < Word.length; i++) {
            drawSprite(g, 12, Word[i], 2, x, 400);
            x = x + 12;
        }
        int[] Word1 = {5, 14, 20, 5, 18};
        x = x + 5;
        for (int i = 0; i < Word1.length; i++) {
            drawSprite(g, 12, Word1[i], 2, x, 400);
            x = x + 12;
        }
        int[] Word2 = {20, 15};
        x = x + 5;
        for (int i = 0; i < Word2.length; i++) {
            drawSprite(g, 12, Word2[i], 2, x, 400);
            x = x + 12;
        }
        int[] Word3 = {19, 20, 1, 18, 20};
        x = x + 5;
        for (int i = 0; i < Word3.length; i++) {
            drawSprite(g, 12, Word3[i], 2, x, 400);
            x = x + 12;
        }
        if (PacmanGame.DEBUG) {
            g.setColor(Color.white); //DEMO...DELETE LATER
            g.drawLine(0, 0, PacmanGame.WIDTH, 0); //DEMO...DELETE LATER
        }

    }
}

