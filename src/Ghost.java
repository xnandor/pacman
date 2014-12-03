import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Ghost extends GameObject {
    Room room;
    String color;
    int speed = 4;
    int velX = 0;
    int velY = 0;
    int desiredVelX = 0;
    int desiredVelY = 0;
    int coordX = 0;
    int coordY = 0;
    int targetX = 0;
    int targetY = 0;
    int spriteOffset = 0;
    int ghostOffsetX = 0;
    int ghostOffsetY = 0;
    int modeSwitches = 0;
    int movingPhase = -1;
    int ghostPhase = -1;
    double modeTime = 0;
    double eatableTime = 0;
    double totalTime = 0;
    double elapsedMovingTime = 0;
    double exitTime = 0;
    boolean isOutOfBox = false;
    boolean isGlobal = false;
    boolean isEatable = false;
    boolean isEaten = false;
    boolean wasEaten = false;
    boolean justAte = false;
    boolean hasPassed1 = false;
    boolean hasPassed2 = false;
    boolean chaseOrScatter = false;
    Random rand = new Random();

    /* Constructor for Ghost object. Ghost has a color, 
    starting coordinates, bounding box, sprite values, 
    and scatter coordinates. */
    public Ghost(Room room, String color) {
        super();
        this.color = color;
        this.room = room;
        if(color.equals("red")) {   // Blinky
            this.boundingBox = new Rectangle((13*12)+8, 14*12+1, 11, 11);
            spriteI = 4; spriteJ = 7;
            coordX = 0; coordY = -36;
            targetX = 30; targetY = -72;
            velX = -1; velY = 0;
            isOutOfBox = true;
        } else if(color.equals("pink")) {   // Pinky
            this.boundingBox = new Rectangle((13*12)+8, 17*12+1, 11, 11);
            spriteI = 2; spriteJ = 9;
            coordX = 0; coordY = -27;
            targetX = -30; targetY = -72;
            velX = 0; velY = 1;
        } else if(color.equals("blue")) {   // Inky
            this.boundingBox = new Rectangle((11*12)+8, 17*12+1, 11, 11);
            spriteI = 14; spriteJ = 9;
            coordX = -5; coordY = -27;
            targetX = 30; targetY = 25;
            velX = 0; velY = -1;
        } else {    // Clyde
            this.boundingBox = new Rectangle((15*12)+8, 17*12+1, 11, 11);
            spriteI = 6; spriteJ = 10;
            coordX = 6; coordY = -27;
            targetX = -30; targetY = 25;
            velX = 0; velY = -1;
        }
    }

    // Update method; used for all GameObjects
    public void update(float dt) {
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        // Moving time determines phase of Ghost movement
        elapsedMovingTime += dt;
        
        /* If Ghost is in frightened mode (i.e. eatable), 
        increment eatable counter. If Ghost is in another
        mode besides eaten, increment mode timer. Mode timer 
        is for the chase and scatter modes. */
        chaseOrScatter = this.getGhostMode();
        if(isEatable == false) {
            modeTime += dt;
        } else if(isEaten == false) {
            eatableTime += dt;
        }
        // Gets the target coordinates for the Ghost
        this.getTarget();
        
        /* If the Ghost is outside the Ghost House (i.e. GH),
        then move to the next spot and update coordinates. If 
        the Ghost is inside the Ghost House, use boxBehavior()
        to move to the next spot and update coordinates. */
        if(isOutOfBox == true) {
            
            /* If Ghost is eaten and has returned to the box,
            set state of Ghost to inside the box and return to
            normal phase (meaning not a pair of eyes) */
            if(isEaten == true) {
                if(coordX == targetX && coordY == targetY) {
                    isEaten = false;
                    isOutOfBox = false;
                    exitTime = 0;
                }
            }
            
            // Time spent outside of Ghost House
            exitTime += dt;
            // Ensures that Ghost goes left then down when leaving the GH
            if(exitTime > 300) {
                // Sets desired velocities
                this.getNextLocation(x,y);
                velX = desiredVelX;
                velY = desiredVelY;
            } else {
                if(coordX == 0 && hasPassed1 == false) {
                    velX = -1; velY = 0;
                    hasPassed1 = true;
                } else if ((coordX == -14 || coordX == 13) && hasPassed2 == false) {
                    velX = 0; velY = 1;
                    hasPassed2 = true;
                }
            }
            this.updateCoordinates(x,y);
        } else {
            this.boxBehavior(x,y);
        }
        
        // Update coordinates if Ghost uses shortcut
        if (x > PacmanGame.WIDTH) {
            boundingBox.setLocation( -10, (int)y );
            coordX += -88;
        }
        if (x < -10) {
            boundingBox.setLocation( PacmanGame.WIDTH, (int)y );
            coordX += 88;
        }
        
        //Animate Direction
        if(isEaten == false) {  // Ghost is in standard phase
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
                    if(color.equals("red")) {
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
        } else {    // Ghost is in eaten phase (i.e. two eye phase)
            if ((Math.abs(velX) > Math.abs(velY))) {    //Going left or right
                if (velX > 0) {  //Right
                    spriteI = 8;
                } else {  //Left
                    spriteI = 12;
                }
            } else {        //Going up or down
                if (velY > 0) {  //Down
                    spriteI = 10;
                } else {  //Up
                    spriteI = 14;
                }
            }
            spriteJ = 10;
        }
        
        // Animate Ghost movement
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
        
        // Handles Ghost in frightened phase
        if(eatableTime > 6500 && isEatable == true) {   // Frightened phase is over
            isEatable = false;
            wasEaten = false;
        } else if(eatableTime > 4000 && isEatable == true) {    // Ghost is blinking in frightened phase
            ghostPhase++;
            if (ghostPhase%10 < 4) {
                ghostOffsetX=0;
                ghostOffsetY=0;
            }
            if (ghostPhase%10 > 4) {
                ghostOffsetX=-8;
                ghostOffsetY=5;
            }
        }
    }

    // Draw Ghost object
    public void draw(Graphics2D g) {
        if(isEatable == true) { // Ghost in frightened state
            drawSprite(g, 24, 12+spriteOffset+ghostOffsetX,6+ghostOffsetY, -5, -5);
        } else if(justAte == true) {    // Display score for eating ghost
            drawSprite(g, 24, 7+room.numOfGhostsEaten(), 7, -5, -5);
            justAte = false;
        } else {    // Draw standard Ghost
            drawSprite(g, 24, spriteI+spriteOffset,spriteJ, -5, -5);
        }
        super.draw(g);
    }

    public void updateCoordinates(double x, double y) {
        Rectangle nextLocation = new Rectangle(boundingBox);
        nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
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
    }
    
    // Set the desired velocities for the Ghost
    public void getNextLocation(double x, double y) {
        Rectangle nextLoc = new Rectangle(boundingBox);
        double minPathLength = 10000;
        int tempXDiff = 0;
        int tempYDiff = 0;
        double rn;
        
        if(isEatable == true) { // Ghost is in frightened mode and chooses direction pseudorandomly
            // Get list of possible directions
            ArrayList<String> directions = new ArrayList<String>();
            if(velX == 1) {
                directions = this.getPossibleDirections(x, y, "right");
            } else if(velX == -1) {
                directions = this.getPossibleDirections(x, y, "left");
            } else if(velY == 1) {
                directions = this.getPossibleDirections(x, y, "down");
            } else if(velY == -1) {
                directions = this.getPossibleDirections(x, y, "up");
            }
            
            // Use pseudorandom variable to determine direction
            rn = rand.nextDouble();
            for(int i = 0; i < directions.size(); i++) {
                double probUpper = (double) (i + 1) / (double) directions.size();
                double probLower = (double) i / (double) directions.size();
                if(rn >= probLower && rn <= probUpper) {
                    if(directions.get(i).equals("up")) {
                        desiredVelX = 0; desiredVelY = -1;
                    } else if(directions.get(i).equals("down")) {
                        desiredVelX = 0; desiredVelY = 1;
                    } else if(directions.get(i).equals("left")) {
                        desiredVelX = -1; desiredVelY = 0;
                    } else if(directions.get(i).equals("right")) {
                        desiredVelX = 1; desiredVelY = 0;
                    }
                    break;
                }
            }
        } else {    // Ghost is in standard phase
            if(velX != 1) { // Not moving right
                nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.getDirectionLength("left",minPathLength);
                }
            }
            if(velX != -1) { // Not moving left
                nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.getDirectionLength("right",minPathLength);
                }
            }
            if(velY != 1) {  // Not moving down
                nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.getDirectionLength("up",minPathLength);
                }
            }
            if(velY != -1) { // Not moving up
                nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.getDirectionLength("down",minPathLength);
                }
            }
        }
    }
    
    // Returns the path length if it is smaller than the minimum path provided
    // Also sets the desired velocities if path is smaller
    public double getDirectionLength(String dir, double minPathLength) {
        int xDiff = coordX - targetX;
        int yDiff = coordY - targetY;
        int tempDiff = 0;
        double pathLength = 0;
        
        if(dir.equals("up")) {
            tempDiff = yDiff-1;
            pathLength = Math.sqrt(xDiff*xDiff + tempDiff*tempDiff);
            if(minPathLength > pathLength) {
                desiredVelX = 0;
                desiredVelY = -1;
                minPathLength = pathLength;
            }
        } else if(dir.equals("down")) {
            tempDiff = yDiff+1;
            pathLength = Math.sqrt(xDiff*xDiff + tempDiff*tempDiff);
            if(minPathLength > pathLength) {
                desiredVelX = 0;
                desiredVelY = 1;
                minPathLength = pathLength;
            }
        } else if(dir.equals("left")) {
            tempDiff = xDiff-1;
            pathLength = Math.sqrt(tempDiff*tempDiff + yDiff*yDiff);
            if(minPathLength > pathLength) {
                desiredVelX = -1;
                desiredVelY = 0;
                minPathLength = pathLength;
            }
        } else if(dir.equals("right")) {
            tempDiff = xDiff+1;
            pathLength = Math.sqrt(tempDiff*tempDiff + yDiff*yDiff);
            if(minPathLength > pathLength) {
                desiredVelX = 1;
                desiredVelY = 0;
                minPathLength = pathLength;
            }
        }
        return minPathLength;
    }
    
    // Set direction when Ghost is in GH
    public void boxBehavior(double x, double y) {
        if(this.canLeaveBox()) {    // If Ghost can leave, guide Ghost towards exit
            if(coordX > 0) {
                velX = -1; velY = 0;
            } else if(coordX < 0) {
                velX = 1; velY = 0;
            } else if(coordY > -36) {
                velX = 0; velY = -1;
            }
            this.updateCoordinates(x,y);
            if(coordX == 0 && coordY == -36) {
                isOutOfBox = true;
            }
        } else {
            if(coordY == -30) {
                velY = 1;
            } else if(coordY == -25) {
                velY = -1;
            }
            this.updateCoordinates(x,y);
        }
    }
    
    // Return true is Ghost can leave GH
    public boolean canLeaveBox() {
        // Blinky can always leave
        if(color.equals("red")) {
            return true;
        }
        // Timer is reset when Pacman eats a dot
        if(room.getDotTimer() < 5000) { // When timer is less than five seconds, release Ghost based on score and color
            if(isGlobal == false) { // Not using global dot counter
                int score = room.getScore();
                if(color.equals("pink")) {  // Pinky can leave immediately
                    return true;
                } else if(color.equals("blue")) {   // Inky can leave at 300 points
                    if(score > 300) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if(score > 900) {   // Clyde can leave at 900 points
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {    // Using global dot counter
                if(room.getGlobalCount() == 7 && color.equals("pink")) {
                    isGlobal = false;
                } else if(room.getGlobalCount() == 17 && color.equals("blue")) {
                    isGlobal = false;
                } else if(room.getGlobalCount() == 32 && color.equals("orange")) {
                    isGlobal = false;
                }
                return false;
            }
        } else {    // When timer has exceeded five seconds, send out one ghost and reset timer
            int num = room.numOfGhostsInBox();
            if(num == 3 && color.equals("pink")) {  // Pinky gets 1st priority
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else if(num == 2 && color.equals("blue")) {   // Inky gets 2nd priority
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else if(num == 1 && color.equals("orange")) { // Clyde gets 3rd priority
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
    // Reset Ghost b/c Pacman died
    public void reset() {
        if(color.equals("red")) {
            this.boundingBox = new Rectangle((13*12)+8, 14*12+1, 11, 11);
            spriteI = 4; spriteJ = 7;
            coordX = 0; coordY = -36;
            targetX = 30; targetY = -72;
            velX = -1; velY = 0;
        } else if(color.equals("pink")) {
            this.boundingBox = new Rectangle((13*12)+8, 17*12+1, 11, 11);
            spriteI = 2; spriteJ = 9;
            coordX = 0; coordY = -27;
            targetX = -30; targetY = -72;
            velX = 0; velY = 1;
            isOutOfBox = false;
        } else if(color.equals("blue")) {
            this.boundingBox = new Rectangle((11*12)+8, 17*12+1, 11, 11);
            spriteI = 14; spriteJ = 9;
            coordX = -5; coordY = -27;
            targetX = 30; targetY = 25;
            velX = 0; velY = -1;
            isOutOfBox = false;
        } else {
            this.boundingBox = new Rectangle((15*12)+8, 17*12+1, 11, 11);
            spriteI = 6; spriteJ = 10;
            coordX = 6; coordY = -27;
            targetX = -30; targetY = 25;
            velX = 0; velY = -1;
            isOutOfBox = false;
        }
        modeTime = 0;
        modeSwitches = 0;
        exitTime = 0;
        isGlobal = true;
        isEatable = false;
        isEaten = false;
        wasEaten = false;
        room.setDotTimer(0);
    }
    
    // Set target coordinates based on state and color
    public void getTarget() {
        if(isEaten == true) {   // Ghost has been eaten and is returning to GH
            if(color.equals("red") || color.equals("pink")) {
                targetX = 0; targetY = -27;
            } else if(color.equals("blue")) {
                targetX = -5; targetY = -27;
            } else {
                targetX = 6; targetY = -27;
            }
        } else if(chaseOrScatter == false) {    // Ghost is in scatter mode and has determined corner
            if(color == "red") {
                targetX = 30; targetY = -72;
            } else if(color == "pink") {
                targetX = -30; targetY = -72;
            } else if(color == "blue") {
                targetX = 30; targetY = 25;
            } else {
                targetX = -30; targetY = 25;
            }
        } else {    // Ghost is in chase mode and has specific target
            if(color == "red") {    // Blinky targets Pacman
                targetX = room.getPacmanX(); targetY = room.getPacmanY();
            } else if(color == "pink") {    // Pinky targets four spaces in front of Pacman
                if(room.getPacmanVelX() == 1) {
                    targetX = room.getPacmanX() + 4*3; targetY = room.getPacmanY();
                } else if(room.getPacmanVelX() == -1) {
                    targetX = room.getPacmanX() - 4*3; targetY = room.getPacmanY();
                } else if(room.getPacmanVelY() == 1) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY() + 4*3;
                } else if(room.getPacmanVelY() == -1) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY() - 4*3;
                }
            } else if(color == "blue") {    // Inky targets spot based on vector between Pinky and Pacman
                int distanceX = 0;
                int distanceY = 0;
                if(room.getPacmanVelX() == 1) {
                    distanceX = room.getPacmanX() - room.getPinkX() + 2*3;
                    distanceY = room.getPacmanY() - room.getPinkY();
                } else if(room.getPacmanVelX() == -1) {
                    distanceX = room.getPacmanX() - room.getPinkX() - 2*3;
                    distanceY = room.getPacmanY() - room.getPinkY();
                } else if(room.getPacmanVelY() == 1) {
                    distanceX = room.getPacmanX() - room.getPinkX();
                    distanceY = room.getPacmanY() - room.getPinkY() + 2*3;
                } else if(room.getPacmanVelY() == -1) {
                    distanceX = room.getPacmanX() - room.getPinkX();
                    distanceY = room.getPacmanY() - room.getPinkY() - 2*3;
                }
                targetX = room.getPacmanX() + distanceX; targetY = room.getPacmanY() + distanceY;
            } else {    // Clyde targets Pacman unless he is within eight tiles, then he targets corner
                if((double) Math.abs(this.getCoordinateX() - room.getPacmanX()) * (28/88) +
                   (double) Math.abs(this.getCoordinateY() - room.getPacmanY()) * (30/84) > 8.0) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY();
                } else {
                    targetX = -30; targetY = 25;
                }
            }
        }
    }
    
    // Determines if Ghost is in chase or scatter mode based on timer
    // Chase = true, scatter = false
    // Reverse direction when changing modes
    public boolean getGhostMode() {
        if(modeSwitches == 0 || modeSwitches == 2) {
            if(modeTime >= 7000) {
                modeTime = 0;
                velX *= -1; velY *= -1;
                modeSwitches++;
                return true;
            } else {
                return false;
            }
        } else if(modeSwitches == 4 || modeSwitches == 6) {
            if(modeTime >= 5000) {
                modeTime = 0;
                velX *= -1; velY *= -1;
                modeSwitches++;
                return true;
            } else {
                return false;
            }
        } else if(modeSwitches == 1 || modeSwitches == 3 || modeSwitches == 5) {
            if(modeTime >= 20000) {
                modeTime = 0;
                velX *= -1; velY *= -1;
                modeSwitches++;
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    // Returns true if given direction is free, false if not free
    public boolean attemptDirection(String dir, double x, double y) {
        Rectangle nextLoc = new Rectangle(boundingBox);
        boolean isDirectionFree = false;
        if(dir.equals("up")) {
            nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
            if(room.isLocationFree(nextLoc,isEaten)) {
                isDirectionFree = true;
            } else {
                isDirectionFree = false;
            }
        } else if(dir.equals("down")) {
            nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
            if(room.isLocationFree(nextLoc,isEaten)) {
                isDirectionFree = true;
            } else {
                isDirectionFree = false;
            }
        } else if(dir.equals("left")) {
            nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
            if(room.isLocationFree(nextLoc,isEaten)) {
                isDirectionFree = true;
            } else {
                isDirectionFree = false;
            }
        } else if(dir.equals("right")) {
            nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
            if(room.isLocationFree(nextLoc,isEaten)) {
                isDirectionFree = true;
            } else {
                isDirectionFree = false;
            }
        }
        return isDirectionFree;
    }
    
    // Return list of possible directions based on current direction
    public ArrayList<String> getPossibleDirections(double x, double y, String dir) {
        Rectangle nextLoc = new Rectangle(boundingBox);
        ArrayList<String> directions = new ArrayList<String>();
        if(!dir.equals("up")) {
            if(this.attemptDirection("down", x, y) == true) {
                directions.add("down");
            }
        }
        if(!dir.equals("down")) {
            if(this.attemptDirection("up", x, y) == true) {
                directions.add("up");
            }
        }
        if(!dir.equals("left")) {
            if(this.attemptDirection("right", x, y) == true) {
                directions.add("right");
            }
        }
        if(!dir.equals("right")) {
            if(this.attemptDirection("left", x, y) == true) {
                directions.add("left");
            }
        }
        return directions;
    }
    
    // Eat Ghost (Pacman collision in frightened mode)
    public void eat() {
        isEaten = true;
        isEatable = false;
        wasEaten = true;
        justAte = true;
    }
    
    // Enter frightened mode (Ghost now eatable)
    public void eatable() {
        if(isEaten == false) {
            isEatable = true;
            ghostOffsetX = 0;
            ghostOffsetY = 0;
            eatableTime = 0;
            // Reverse direction
            velX *= -1; velY *= -1;
        }
        wasEaten = false;
    }
    
    public int getCoordinateX() {
        return coordX;
    }
    
    public int getCoordinateY() {
        return coordY;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean getBoxStatus() {
        return isOutOfBox;
    }
    
    public boolean isGhostEatable() {
        return isEatable;
    }
    
    public boolean isGhostEaten() {
        return isEaten;
    }
    
    public boolean wasGhostEaten() {
        return wasEaten;
    }
}