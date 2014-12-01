import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.AffineTransform;

public class Ghost extends GameObject {
    Room room;
    String color;
    double totalTime = 0;
    //time since last movement
    double elapsedMovingTime = 0;
    int movingPhase = -1;
    int spriteOffset = 0;
    //time since last desired direction change
    double elapsedDirectionTime = 0;
    //Velocities
    int velX = 0;
    int velY = 0;
    //Desired velocity is used to make sure pacman still turns if key is pressed early.
    int desiredVelX = 0;
    int desiredVelY = 0;

    public Ghost(Room room, String color) {
        super();
        this.color = color;
        if(color.equals("red")) {
            this.boundingBox = new Rectangle((13*12)+8, 14*12+1, 11, 11);
            spriteI = 4; spriteJ = 7;
        } else if(color.equals("pink")) {
            this.boundingBox = new Rectangle((13*12)+8, 17*12+1, 11, 11);
            spriteI = 2; spriteJ = 9;
        } else if(color.equals("blue")) {
            this.boundingBox = new Rectangle((11*12)+8, 17*12+1, 11, 11);
            spriteI = 14; spriteJ = 9;
        } else {
            this.boundingBox = new Rectangle((15*12)+8, 17*12+1, 11, 11);
            spriteI = 6; spriteJ = 10;
        }
        this.room = room;
    }

    public void update(float dt) {
        double ranNum = Math.random()*4;
        if(ranNum < 1.0) {
            desiredVelX = -1;
            desiredVelY = 0;
        } else if(ranNum < 2.0) {
            desiredVelX = 1;
            desiredVelY = 0;
        } else if(ranNum < 3.0) {
            desiredVelX = 0;
            desiredVelY = -1;
        } else {
            desiredVelX = 0;
            desiredVelY = 1;
        }
        int speed = 4;
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        elapsedMovingTime += dt;
        elapsedDirectionTime += dt;
        //Move to next location
        Rectangle nextLocation = new Rectangle(boundingBox);
        Rectangle nextDesiredLocation = new Rectangle(boundingBox);
        nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        nextDesiredLocation.setLocation( (int)(x+desiredVelX*speed), (int)(y+desiredVelY*speed) );
        if (room.isLocationFree(nextDesiredLocation)) {
            elapsedDirectionTime = 0;
            velX = desiredVelX;
            velY = desiredVelY;
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        } else if(room.isLocationFree(nextLocation)) {
            elapsedDirectionTime = 0;
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        }
        //Screen Wrap
        if (x > PacmanGame.WIDTH) {
            boundingBox.setLocation( -10, (int)y );
        }
        if (x < -10) {
            boundingBox.setLocation( PacmanGame.WIDTH, (int)y );
        }
        //Reset desired direction
        if (elapsedDirectionTime > 2500) {
            elapsedDirectionTime = 0;
            desiredVelX = velX;
            desiredVelY = velY;
        }
        //Animate Direction
        if ((Math.abs(velX) > Math.abs(velY))) { //Going left or right
            if (velX > 0) {  //Right
                if(color.equals("red")) {
                    spriteI = 0;
                    spriteJ= 7;
                } else if(color.equals("pink")) {
                    spriteI = 0;
                    spriteJ= 9;
                } else if(color.equals("blue")) {
                    spriteI = 8;
                    spriteJ= 9;
                } else {
                    spriteI = 0;
                    spriteJ= 10;
                }
            } else {  //Left
                if(color.equals("red")) {
                    spriteI = 4;
                    spriteJ= 7;
                } else if(color.equals("pink")) {
                    spriteI = 4;
                    spriteJ= 9;
                } else if(color.equals("blue")) {
                    spriteI = 12;
                    spriteJ= 9;
                } else {
                    spriteI = 4;
                    spriteJ= 10;
                }
            }
        } else {        //Going up or down
            if (velY > 0) {  //Down
                if(color.equals("red")) {
                    spriteI = 2;
                    spriteJ= 7;
                } else if(color.equals("pink")) {
                    spriteI = 2;
                    spriteJ= 9;
                } else if(color.equals("blue")) {
                    spriteI = 10;
                    spriteJ= 9;
                } else {
                    spriteI = 2;
                    spriteJ= 10;
                }
            } else {  //Up
                if (color.equals("red")) {
                    spriteI = 6;
                    spriteJ= 7;
                } else if(color.equals("pink")) {
                    spriteI = 6;
                    spriteJ= 9;
                } else if(color.equals("blue")) {
                    spriteI = 14;
                    spriteJ= 9;
                } else {
                    spriteI = 6;
                    spriteJ= 10;
                }
            }
        }
        //Animate Chomp
        if (elapsedMovingTime > 50) {
            elapsedMovingTime = 0;
            movingPhase++;
            if (movingPhase%2 == 0) {
                spriteOffset=0;
            }
            if (movingPhase%2 == 1) {
                spriteOffset=1;
            }
        }
    }

    public void draw(Graphics2D g) {
        drawSprite(g, 24, spriteI+spriteOffset,spriteJ, -5, -5);
        super.draw(g);
    }

}

