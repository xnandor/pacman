import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Pacman extends GameObject implements KeyListener {

    Room room;
    int lives = 3;
    double totalTime = 0;
    //time since last chomp
    double elapsedChompTime = 0;
    int chompPhase = -1;
    int spriteOffset = 0;
    //time since last desired direction change
    double elapsedDirectionTime = 0;
    //Velocities
    int velX = 0;
    int velY = 0;
    //Desired velocity is used to make sure pacman still turns if key is pressed early.
    int desiredVelX = 0;
    int desiredVelY = 0;
    int coordX = 0;
    int coordY = 0;
    boolean moving = false;

    public Pacman(Room room) {
        super();
        this.boundingBox = new Rectangle((13*12)+8, 26*12+1, 11, 11);
        this.room = room;
    }

    public void update(float dt) {
        updateAudio();
        int speed = 4;
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        elapsedChompTime += dt;
        elapsedDirectionTime += dt;
        //Move to next location
        Rectangle nextLocation = new Rectangle(boundingBox);
        Rectangle nextDesiredLocation = new Rectangle(boundingBox);
        nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        nextDesiredLocation.setLocation( (int)(x+desiredVelX*speed), (int)(y+desiredVelY*speed) );
        if (room.isLocationFree(nextDesiredLocation)) {
            elapsedDirectionTime = 0;
            moving = true;
            velX = desiredVelX;
            velY = desiredVelY;
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            this.updateCoordinates();
        } else if(room.isLocationFree(nextLocation)) {
            moving = true;
            elapsedDirectionTime = 0;
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            this.updateCoordinates();
        } else {
            moving = false;
        }
        //Screen Wrap
        if (x > PacmanGame.WIDTH) {
            boundingBox.setLocation( -10, (int)y );
            coordX += -88;
        }
        if (x < -10) {
            boundingBox.setLocation( PacmanGame.WIDTH, (int)y );
            coordX += 88;
        }
        //Reset desired direction
        if (elapsedDirectionTime > 2500) {
            elapsedDirectionTime = 0;
            desiredVelX = velX;
            desiredVelY = velY;
        }
        //Animate Direction
        if (chompPhase%3 != 2) {
            if ((Math.abs(velX) > Math.abs(velY))) { //Going left or right
                if (velX > 0) {  //Right
                    spriteI = 12;
                    spriteJ= 7;
                } else {  //Left
                    spriteI = 0;
                    spriteJ= 11;
                }
            } else {        //Going up or down
                if (velY > 0) {  //Down
                    spriteI = 13;
                    spriteJ= 7;
                } else {  //Up
                    spriteI = 1;
                    spriteJ= 11;
                }
            }
        }
        //Animate Chomp
        if (elapsedChompTime > 50 && moving) {
            elapsedChompTime = 0;
            chompPhase++;
            if (chompPhase%3 == 0) {
                spriteOffset=0;
            }
            if (chompPhase%3 == 1) {
                spriteOffset=2;
            }
            if (chompPhase%3 == 2) {
                spriteOffset=0;
                spriteI = 0;
                spriteJ = 8;
            }
        }
        if (velX == 0 && velY == 0) {
            spriteOffset = 0;
            spriteI = 0; spriteJ = 8;

        }

    }

    public void draw(Graphics2D g) {
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        drawSprite(g, 24, spriteI+spriteOffset,spriteJ, -5, -5);
        super.draw(g);
    }

    public void updateAudio() {
        // CHOMP HAS 15800 SAMPLES
        if (!AudioPlayer.CHOMP.isPlaying()) {
            AudioPlayer.CHOMP.play();
            AudioPlayer.CHOMP.loop(1986, 14862);
        }
    }

    public void die() {
        velX = 0;
        velY = 0;
        desiredVelX = 0;
        desiredVelY = 0;
        coordX = 0;
        coordY = 0;
        this.boundingBox = new Rectangle((13*12)+8, 26*12+1, 11, 11);
    }
    
    public void updateCoordinates() {
        if ((Math.abs(velX) > Math.abs(velY))) { //Going left or right
            if (velX > 0) {  //Right
                coordX++;
            } else if (velX < 0) {  //Left
                coordX--;
            }
        } else {        //Going up or down
            if (velY > 0) {  //Down
                coordY++;
            } else if (velY < 0) {  //Up
                coordY--;
            }
        }
        //System.out.println("Coordinates: " + coordX + " and " + coordY);
    }
    
    public int getCoordinateX() {
        return coordX;
    }
    
    public int getCoordinateY() {
        return coordY;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (e.VK_LEFT == code) {
            desiredVelX = -1;
            desiredVelY = 0;
        }
        if (e.VK_RIGHT == code) {
            desiredVelX = 1;
            desiredVelY = 0;
        }
        if (e.VK_UP == code) {
            desiredVelX = 0;
            desiredVelY = -1;
        }
        if (e.VK_DOWN == code) {
            desiredVelX = 0;
            desiredVelY = 1;
        }
        //suicide switch for testing live tracking
        if (e.VK_DELETE == code){
            room.numLives--;
            AudioPlayer.stopAll();
            AudioPlayer.DEATH.play();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

}

