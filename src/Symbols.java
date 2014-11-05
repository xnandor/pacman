import java.awt.*;
import java.awt.Rectangle;

/**
 * Created by natedolz on 11/2/14.
 */
public class Symbols extends GameObject {

    String message;
    public Alignment alignment;

    public enum Alignment { LEFT_JUSTIFIED, RIGHT_JUSTIFIED };

    public Symbols(String message, int x, int y) {
        super();
        this.alignment = Alignment.LEFT_JUSTIFIED;
        Dimension size = boundingBox.getSize();
        this.boundingBox = new Rectangle(new Point(x , y), size);
        this.message = message.toLowerCase();
    }

    public void draw(Graphics2D g) {
        int size = 12;
        int m, n = 0;
        int offset = 0;
        if (alignment == Alignment.RIGHT_JUSTIFIED) {
            offset = size*message.length();
        }
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            switch (c) {
            case 'a': m=1;  n=2; break;
            case 'b': m=2;  n=2; break;
            case 'c': m=3;  n=2; break;
            case 'd': m=4;  n=2; break;
            case 'e': m=5;  n=2; break;
            case 'f': m=6;  n=2; break;
            case 'g': m=7;  n=2; break;
            case 'h': m=8;  n=2; break;
            case 'i': m=9;  n=2; break;
            case 'j': m=10; n=2; break;
            case 'k': m=11; n=2; break;
            case 'l': m=12; n=2; break;
            case 'm': m=13; n=2; break;
            case 'n': m=14; n=2; break;
            case 'o': m=15; n=2; break;
            case 'p': m=16; n=2; break;
            case 'q': m=17; n=2; break;
            case 'r': m=18; n=2; break;
            case 's': m=19; n=2; break;
            case 't': m=20; n=2; break;
            case 'u': m=21; n=2; break;
            case 'v': m=22; n=2; break;
            case 'w': m=23; n=2; break;
            case 'x': m=24; n=2; break;
            case 'y': m=25; n=2; break;
            case 'z': m=26; n=2; break;
            case '0': m=16; n=1; break;
            case '1': m=17; n=1; break;
            case '2': m=18; n=1; break;
            case '3': m=19; n=1; break;
            case '4': m=20; n=1; break;
            case '5': m=21; n=1; break;
            case '6': m=22; n=1; break;
            case '7': m=23; n=1; break;
            case '8': m=24; n=1; break;
            case '9': m=25; n=1; break;
            default:
                m=1;
                n=27;
                break;
            }

            offset += size;
            switch (alignment) {
            case LEFT_JUSTIFIED:
                drawSprite(g, size, m, n, offset-12, 0);
                break;
            case RIGHT_JUSTIFIED:
                int wordLength = size*message.length();
                drawSprite(g, size, m, n, offset-2*wordLength-12, 0);
                break;
            default:

                break;
            }

        }
    }
}
