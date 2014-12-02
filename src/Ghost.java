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
    double totalTime = 0;
    //time since last movement
    double elapsedMovingTime = 0;
    int movingPhase = -1;
    int ghostPhase = -1;
    int spriteOffset = 0;
    int ghostOffsetX = 0;
    int ghostOffsetY = 0;
    //time since last desired direction change
    double exitTime = 0;
    //Velocities
    int velX = 0;
    int velY = 0;
    int desiredVelX = 0;
    int desiredVelY = 0;
    int coordX = 0;
    int coordY = 0;
    int targetX = 0;
    int targetY = 0;
    int modeTimer = 0;
    int modeSwitches = 0;
    int eatableTime = 0;
    boolean isEatable = false;
    boolean isEaten = false;
    boolean wasEaten = false;
    boolean justAte = false;
    boolean isOutOfBox = false;
    boolean hasPassed1 = false;
    boolean hasPassed2 = false;
    boolean isGlobal = false;
    // true = chase, false = scatter
    boolean chaseOrScatter = false;
    Random rand = new Random();
    
    public Ghost(Room room, String color) {
        super();
        this.color = color;
        if(color.equals("red")) {
            this.boundingBox = new Rectangle((13*12)+8, 14*12+1, 11, 11);
            spriteI = 4; spriteJ = 7;
            coordX = 0; coordY = -36;
            targetX = 30; targetY = -72;
            velX = -1; velY = 0;
            isOutOfBox = true;
        } else if(color.equals("pink")) {
            this.boundingBox = new Rectangle((13*12)+8, 17*12+1, 11, 11);
            spriteI = 2; spriteJ = 9;
            coordX = 0; coordY = -27;
            targetX = -30; targetY = -72;
            velX = 0; velY = 1;
        } else if(color.equals("blue")) {
            this.boundingBox = new Rectangle((11*12)+8, 17*12+1, 11, 11);
            spriteI = 14; spriteJ = 9;
            coordX = -5; coordY = -27;
            targetX = 30; targetY = 25;
            velX = 0; velY = -1;
        } else {
            this.boundingBox = new Rectangle((15*12)+8, 17*12+1, 11, 11);
            spriteI = 6; spriteJ = 10;
            coordX = 6; coordY = -27;
            targetX = -30; targetY = 25;
            velX = 0; velY = -1;
        }
        this.room = room;
    }
    
    public void update(float dt) {
        double x = boundingBox.getX();
        double y = boundingBox.getY();
        elapsedMovingTime += dt;
        eatableTime += dt;
        if(isEatable == false) {
            modeTimer += dt;
        }
        chaseOrScatter = this.getGhostMode();
        this.getTarget();
        if(isOutOfBox == true) {
            if(isEaten == true) {
                if(coordX == targetX && coordY == targetY) {
                    isEaten = false;
                    isOutOfBox = false;
                    exitTime = 0;
                }
            }
            exitTime += dt;
            //Move to next location
            Rectangle nextLocation = new Rectangle(boundingBox);
            if(exitTime > 300) {
                this.getNextLocation(nextLocation,x,y);
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
            nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            this.updateCoordinates();
        } else {
            this.boxBehavior(x,y);
        }
        if (x > PacmanGame.WIDTH) {
            boundingBox.setLocation( -10, (int)y );
            coordX += -88;
        }
        if (x < -10) {
            boundingBox.setLocation( PacmanGame.WIDTH, (int)y );
            coordX += 88;
        }
        //Animate Direction
        if(isEaten == false) {
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
        } else {
            if ((Math.abs(velX) > Math.abs(velY))) { //Going left or right
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
        
        //Animate Ghost movement
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
        if(eatableTime > 6500 && isEatable == true) {
            isEatable = false;
            wasEaten = false;
        } else if(eatableTime > 4000 && isEatable == true) {
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
    
    public void draw(Graphics2D g) {
        if(isEatable == true) {
            drawSprite(g, 24, 12+spriteOffset+ghostOffsetX,6+ghostOffsetY, -5, -5);
        } else if(justAte == true) {
            drawSprite(g, 24, 7+room.numOfGhostsEaten(), 7, -5, -5);
            justAte = false;
        } else {
            drawSprite(g, 24, spriteI+spriteOffset,spriteJ, -5, -5);
        }
        super.draw(g);
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
    }
    
    public int getCoordinateX() {
        return coordX;
    }
    
    public int getCoordinateY() {
        return coordY;
    }
    
    public void getNextLocation(Rectangle nextLoc, double x, double y) {
        double minPathLength = 10000;
        int tempXDiff = 0;
        int tempYDiff = 0;
        double rn;
        
        if(isEatable == true) {
            ArrayList<String> directions = new ArrayList<String>();
            if(velX == 1) {
                directions = this.getPossibleDirections(nextLoc, x, y, "right");
            } else if(velX == -1) {
                directions = this.getPossibleDirections(nextLoc, x, y, "left");
            } else if(velY == 1) {
                directions = this.getPossibleDirections(nextLoc, x, y, "down");
            } else if(velY == -1) {
                directions = this.getPossibleDirections(nextLoc, x, y, "up");
            }
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
        } else {
            if(velX == 1) {
                nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("right",minPathLength);
                }
                nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("down",minPathLength);
                }
                nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("up",minPathLength);
                }
            } else if(velX == -1) {
                nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("left",minPathLength);
                }
                nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("down",minPathLength);
                }
                nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("up",minPathLength);
                }
            } else if(velY == 1) {
                nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("down",minPathLength);
                }
                nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("left",minPathLength);
                }
                nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("right",minPathLength);
                }
            } else if(velY == -1) {
                nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("up",minPathLength);
                }
                nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("left",minPathLength);
                }
                nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
                if(room.isLocationFree(nextLoc,isEaten)) {
                    minPathLength = this.attemptDirection("right",minPathLength);
                }
            }
        }
    }
    
    public double attemptDirection(String dir, double minPathLength) {
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
    
    public boolean attemptLeft(Rectangle nextLoc, double x, double y) {
        nextLoc.setLocation( (int)(x-1*speed), (int)(y+0*speed) );
        if(room.isLocationFree(nextLoc,isEaten)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean attemptRight(Rectangle nextLoc, double x, double y) {
        nextLoc.setLocation( (int)(x+1*speed), (int)(y+0*speed) );
        if(room.isLocationFree(nextLoc,isEaten)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean attemptUp(Rectangle nextLoc, double x, double y) {
        nextLoc.setLocation( (int)(x+0*speed), (int)(y-1*speed) );
        if(room.isLocationFree(nextLoc,isEaten)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean attemptDown(Rectangle nextLoc, double x, double y) {
        nextLoc.setLocation( (int)(x+0*speed), (int)(y+1*speed) );
        if(room.isLocationFree(nextLoc,isEaten)) {
            return true;
        } else {
            return false;
        }
    }
    
    public ArrayList<String> getPossibleDirections(Rectangle nextLoc, double x, double y, String dir) {
        ArrayList<String> directions = new ArrayList<String>();
        if(dir.equals("up")) {
            if(this.attemptUp(nextLoc, x, y) == true) {
                directions.add("up");
            }
            if(this.attemptLeft(nextLoc, x, y) == true) {
                directions.add("left");
            }
            if(this.attemptRight(nextLoc, x, y) == true) {
                directions.add("right");
            }
        } else if(dir.equals("down")) {
            if(this.attemptDown(nextLoc, x, y) == true) {
                directions.add("down");
            }
            if(this.attemptLeft(nextLoc, x, y) == true) {
                directions.add("left");
            }
            if(this.attemptRight(nextLoc, x, y) == true) {
                directions.add("right");
            }
        } else if(dir.equals("left")) {
            if(this.attemptUp(nextLoc, x, y) == true) {
                directions.add("up");
            }
            if(this.attemptDown(nextLoc, x, y) == true) {
                directions.add("down");
            }
            if(this.attemptLeft(nextLoc, x, y) == true) {
                directions.add("left");
            }
        } else if(dir.equals("right")) {
            if(this.attemptUp(nextLoc, x, y) == true) {
                directions.add("up");
            }
            if(this.attemptDown(nextLoc, x, y) == true) {
                directions.add("down");
            }
            if(this.attemptRight(nextLoc, x, y) == true) {
                directions.add("right");
            }
        }
        return directions;
    }
    
    public void boxBehavior(double x, double y) {
        if(this.canLeaveBox()) {
            if(coordX > 0) {
                velX = -1; velY = 0;
            } else if(coordX < 0) {
                velX = 1; velY = 0;
            } else if(coordY > -36) {
                velX = 0; velY = -1;
            }
            Rectangle nextLocation = new Rectangle(boundingBox);
            nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            this.updateCoordinates();
            if(coordX == 0 && coordY == -36) {
                isOutOfBox = true;
            }
        } else {
            if(coordY == -30) {
                velY = 1;
            } else if(coordY == -25) {
                velY = -1;
            }
            Rectangle nextLocation = new Rectangle(boundingBox);
            nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
            this.updateCoordinates();
        }
    }
    
    public void eatenBehavior(double x, double y) {
        Rectangle nextLocation = new Rectangle(boundingBox);
        this.getNextLocation(nextLocation,x,y);
        velX = desiredVelX;
        velY = desiredVelY;
        nextLocation.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        boundingBox.setLocation( (int)(x+velX*speed), (int)(y+velY*speed) );
        this.updateCoordinates();
    }
    
    public boolean canLeaveBox() {
        if(color.equals("red")) {
            return true;
        }
        if(room.getDotTimer() < 5000) {
            if(isGlobal == false) {
                int score = room.getScore();
                if(color.equals("pink")) {
                    return true;
                } else if(color.equals("blue")) {
                    if(score > 300) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if(score > 900) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if(room.getGlobalCount() == 7 && color.equals("pink")) {
                    isGlobal = false;
                } else if(room.getGlobalCount() == 17 && color.equals("blue")) {
                    isGlobal = false;
                } else if(room.getGlobalCount() == 32 && color.equals("orange")) {
                    isGlobal = false;
                }
                return false;
            }
        } else {
            int num = room.numOfGhostsInBox();
            if(num == 3 && color.equals("pink")) {
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else if(num == 2 && color.equals("blue")) {
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else if(num == 1 && color.equals("orange")) {
                if(isOutOfBox == true) {
                    room.setDotTimer(0);
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean getBoxStatus() {
        return isOutOfBox;
    }
    
    public void eat() {
        isEaten = true;
        isEatable = false;
        wasEaten = true;
        justAte = true;
    }
    
    public void eatable() {
        if(isEaten == false) {
            isEatable = true;
            ghostOffsetX = 0;
            ghostOffsetY = 0;
            eatableTime = 0;
            velX *= -1; velY *= -1;
        }
        wasEaten = false;
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
    
    public boolean getGhostMode() {
        if(modeSwitches == 0 || modeSwitches == 2) {
            if(modeTimer >= 7000) {
                modeTimer = 0;
                velX *= -1; velY *= -1;
                modeSwitches++;
                return true;
            } else {
                return false;
            }
        } else if(modeSwitches == 4 || modeSwitches == 6) {
            if(modeTimer >= 5000) {
                modeTimer = 0;
                velX *= -1; velY *= -1;
                modeSwitches++;
                return true;
            } else {
                return false;
            }
        } else if(modeSwitches == 1 || modeSwitches == 3 || modeSwitches == 5) {
            if(modeTimer >= 20000) {
                modeTimer = 0;
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
    
    public void getTarget() {
        if(isEaten == true) {
            if(color.equals("red") || color.equals("pink")) {
                targetX = 0; targetY = -27;
            } else if(color.equals("blue")) {
                targetX = -5; targetY = -27;
            } else {
                targetX = 6; targetY = -27;
            }
        } else if(chaseOrScatter == false) {
            if(color == "red") {
                targetX = 30; targetY = -72;
            } else if(color == "pink") {
                targetX = -30; targetY = -72;
            } else if(color == "blue") {
                targetX = 30; targetY = 25;
            } else {
                targetX = -30; targetY = 25;
            }
        } else {
            if(color == "red") {
                targetX = room.getPacmanX(); targetY = room.getPacmanY();
            } else if(color == "pink") {
                if(velX == 1) {
                    targetX = room.getPacmanX() + 4; targetY = room.getPacmanY();
                } else if(velX == -1) {
                    targetX = room.getPacmanX() - 4; targetY = room.getPacmanY();
                } else if(velY == 1) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY() + 4;
                } else if(velY == -1) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY() - 4;
                }
            } else if(color == "blue") {
                int distanceX = 0;
                int distanceY = 0;
                if(velX == 1) {
                    distanceX = room.getPacmanX() - room.getPinkX() + 2;
                    distanceY = room.getPacmanY() - room.getPinkY();
                } else if(velX == -1) {
                    distanceX = room.getPacmanX() - room.getPinkX() - 2;
                    distanceY = room.getPacmanY() - room.getPinkY();
                } else if(velY == 1) {
                    distanceX = room.getPacmanX() - room.getPinkX();
                    distanceY = room.getPacmanY() - room.getPinkY() + 2;
                } else if(velY == -1) {
                    distanceX = room.getPacmanX() - room.getPinkX();
                    distanceY = room.getPacmanY() - room.getPinkY() - 2;
                }
                targetX = room.getPacmanX() + distanceX; targetY = room.getPacmanY() + distanceY;
            } else {
                if(Math.abs(this.getCoordinateX() - room.getPacmanX()) +
                   Math.abs(this.getCoordinateY() - room.getPacmanY()) > 8) {
                    targetX = room.getPacmanX(); targetY = room.getPacmanY();
                } else {
                    targetX = -30; targetY = 25;
                }
            }
        }
    }
    
    public String getColor() {
        return color;
    }
    
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
        modeTimer = 0;
        modeSwitches = 0;
        exitTime = 0;
        isGlobal = true;
        isEatable = false;
        isEaten = false;
        wasEaten = false;
        room.setDotTimer(0);
    }
}

