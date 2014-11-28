import java.awt.*;

/**
 * Created by NateDolz on 11/4/2014.
 */
public class LevelSplash{

    //sprite info
    public int spriteOffset = 2;
    public int spriteOffsetBig = 0;

    //Animation Objects
    AnimationObj pacman = new AnimationObj(0,11,288,200,-1);
    AnimationObj RedGhost = new AnimationObj(4,7,312,200,-1);
    AnimationObj pacmanTL = new AnimationObj(8,6,0,176,1);
    AnimationObj pacmanTR = new AnimationObj(9,6,24,176,1);
    AnimationObj pacmanBL = new AnimationObj(10,6,0,200,1);
    AnimationObj pacmanBR = new AnimationObj(11,6,24,200,1);
    AnimationObj ScaredGhost = new AnimationObj(12,6,48,200,1);
    //time

    public double elapsedTime = 0;

    public LevelSplash(int lvl){}

    public void draw(Graphics2D g) {
        //draw all Animation Objects
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 336, 492);
        pacman.draw(g,spriteOffset);
        RedGhost.draw(g,0);
        if(RedGhost.boundingBox.getX() <= 0){
            ScaredGhost.draw(g,0);
            pacmanTL.draw(g,spriteOffsetBig);
            pacmanTR.draw(g,spriteOffsetBig);
            pacmanBL.draw(g,spriteOffsetBig);
            pacmanBR.draw(g,spriteOffsetBig);
        }
   }

   public void update(float dt){
       elapsedTime += dt;
       pacman.update(dt);
       RedGhost.update(dt);
       if(RedGhost.boundingBox.getX() <= 0) {
           ScaredGhost.update(dt);
           pacmanTL.update(dt);
           pacmanTR.update(dt);
           pacmanBL.update(dt);
           pacmanBR.update(dt);
           if (elapsedTime > 50) {
               elapsedTime = 0;
               if (spriteOffsetBig < -8) spriteOffsetBig = 0;
               else spriteOffsetBig -= 4;
           }
       }
       if (elapsedTime > 50) {
           elapsedTime = 0;
           if (spriteOffset < 0) spriteOffset = 2;
           else spriteOffset -=2;
       }
   }
}
