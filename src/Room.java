import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Room implements KeyListener {

    public boolean paused = false;

    ArrayList<GameObject> scene = new ArrayList<GameObject>();
    Pacman pacman;
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Dot> dots = new ArrayList<Dot>();
    ArrayList<Fruit> fruits = new ArrayList<Fruit>();
    ArrayList<SuperDot> sdots = new ArrayList<SuperDot>();
    int[] board = {};
    int numLives;
    int level;
    boolean fruitAdded = false;
    int score;
    int highscore;
    int dotsEaten = 0;
    boolean fruit = false;
    int y = 0; //for debug
    int frame = 0; //for debug
    int globalCount = 0;
    int dotTimer = 0;
    boolean isGlobal = false;
    boolean ghostDead = false;
    double ghostDeadTimer = 0;

    public Room(int level, int prevscore, int lives, int HS) {
        score = prevscore;
        numLives = lives;
        highscore = HS;
        this.level = level;
        pacman = new Pacman(this);
        ghosts.add(new Ghost(this,"red"));
        ghosts.add(new Ghost(this,"pink"));
        ghosts.add(new Ghost(this,"blue"));
        ghosts.add(new Ghost(this,"orange"));
        //Block sprite theme information located in Block object)
        int[] board1 = {
            2, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 45, 44, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,  1,
            4,100,100,100,100,100,100,100,100,100,100,100,100, 27, 26,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 40,100, 41, 15, 15, 15, 40,100, 27, 26,100, 41, 15, 15, 15, 40,100, 41, 15, 15, 40,100,  3,
            4,200, 27,  0,  0, 26,100, 27,  0,  0,  0, 26,100, 27, 26,100, 27,  0,  0,  0, 26,100, 27,  0,  0, 26,200,  3,
            4,100, 43, 22, 22, 42,100, 43, 22, 22, 22, 42,100, 43, 42,100, 43, 22, 22, 22, 42,100, 43, 22, 22, 42,100,  3,
            4,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 40,100, 41, 40,100, 41, 15, 15, 15, 15, 15, 15, 40,100, 41, 40,100, 41, 15, 15, 40,100,  3,
            4,100, 43, 22, 22, 42,100, 27, 26,100, 43, 22, 22, 37, 36, 22, 22, 42,100, 27, 26,100, 43, 22, 22, 42,100,  3,
            4,100,100,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100,100,100,  3,
            6, 13, 13, 13, 13, 40,100, 27, 38, 15, 15, 40,  0, 27, 26,  0, 41, 15, 15, 39, 26,100, 41, 13, 13, 13, 13,  5,
            0,  0,  0,  0,  0,  4,100, 27, 36, 22, 22, 42,  0, 43, 42,  0, 43, 22, 22, 37, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0, 31, 13, 35,  -1,  -1, 34, 13, 30,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            12, 12, 12, 12, 12, 42,100, 43, 42,  0,  3,  0,  0,  0,  0,  0,  0,  4,  0, 43, 42,100, 43, 12, 12, 12, 12, 12,
            0,  0,  0,  0,  0,  0,100,  0,  0,  0,  3,  0,  0,  0,  0,  0,  0,  4,  0,  0,  0,100,  0,  0,  0,  0,  0,  0,
            13, 13, 13, 13, 13, 40,100, 41, 40,  0,  3,  0,  0,  0,  0,  0,  0,  4,  0, 41, 40,100, 41, 13, 13, 13, 13, 13,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0, 33, 12, 12, 12, 12, 12, 12, 32,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0, 41, 15, 15, 15, 15, 15, 15, 40,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            2, 12, 12, 12, 12, 42,100, 43, 42,  0, 43, 22, 22, 37, 36, 22, 22, 42,  0, 43, 42,100, 43, 12, 12, 12, 12,  1,
            4,100,100,100,100,100,100,100,100,100,100,100,100, 27, 26,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 40,100, 41, 15, 15, 15, 40,100, 27, 26,100, 41, 15, 15, 15, 40,100, 41, 15, 15, 40,100,  3,
            4,100, 43, 22, 37, 26,100, 43, 22, 22, 22, 42,100, 43, 42,100, 43, 22, 22, 22, 42,100, 27, 36, 22, 42,100,  3,
            4,200,100,100, 27, 26,100,100,100,100,100,100,100,  0, 0, 100,100,100,100,100,100,100, 27, 26,100,100,200,  3,
            8, 15, 40,100, 27, 26,100, 41, 40,100, 41, 15, 15, 15, 15, 15, 15, 40,100, 41, 40,100, 27, 26,100, 41, 15,  7,
            10, 22, 42,100, 43, 42,100, 27, 26,100, 43, 22, 22, 37, 36, 22, 22, 42,100, 27, 26,100, 43, 42,100, 43, 22,  9,
            4,100,100,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 15, 15, 39, 38, 15, 15, 40,100, 27, 26,100, 41, 15, 15, 39, 38, 15, 15, 15, 15, 40,100,  3,
            4,100, 43, 22, 22, 22, 22, 22, 22, 22, 22, 42,100, 43, 42,100, 43, 22, 22, 22, 22, 22, 22, 22, 22, 42,100,  3,
            4,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            6, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,  5,
        };
        board = board1;
        for (int i=0; i < board.length; i++) {
            int m = i%28;
            int n = i/28;
            if (board[i] != 0 && board[i] <= 64) {
                Block block = new Block(board[i], m, n+3);
                blocks.add(block);
            } else if (board[i] == 100) {
                Dot dot = new Dot(m, n+3);
                dots.add(dot);
            } else if (board[i] == 200) {
                SuperDot sdot = new SuperDot(m, n+3);
                sdots.add(sdot);
            }
        }
    }

    public void update(float dt) {
        if (!pacman.dead) {
            for (int i = 0; i < ghosts.size(); i++) {
                Ghost ghost = ghosts.get(i);
                if (ghost.justAte == true) {
                    ghostDead = true;
                }
            }
            if (!ghostDead) {
                pacman.update(dt);
                dotTimer += dt;
                for (int i = 0; i < ghosts.size(); i++) {
                    ghosts.get(i).update(dt);
                }
                for (int i = 0; i < sdots.size(); i++) {
                    sdots.get(i).update(dt);
                }
                for (int i = 0; i < fruits.size(); i++) {
                    Fruit fruit = fruits.get(i);
                    fruit.update(dt);
                    if (!fruit.exists) {
                        fruits.remove(i);
                    }
                }
                //adds fruit if 70 dots have been eaten
                if (dotsEaten >= 70) {
                    if (!fruitAdded) {
                        fruitAdded = true;
                        Fruit fr = new Fruit(this, level);
                        fruits.add(fr);
                    }
                }

                //CHECK FOR EATS
                Rectangle pacmanRect = pacman.boundingBox;
                for (int i = 0; i < dots.size(); i++) { //eat dots
                    Dot dot = dots.get(i);
                    if (pacmanRect.intersects(dot.boundingBox)) {
                        dotTimer = 0;
                        dotsEaten++;
                        dots.remove(i);
                        AudioPlayer.DOT.play();
                        //increments score by 10
                        score = score + 10;
                        if (isGlobal == true) {
                            globalCount += 1;
                        }
                    }
                }
                for (int i = 0; i < sdots.size(); i++) { //eat superdots
                    SuperDot sdot = sdots.get(i);
                    if (pacmanRect.intersects(sdot.boundingBox)) {
                        dotTimer = 0;
                        dotsEaten++;
                        sdots.remove(i);
                        AudioPlayer.DOT.play();
                        //increments score by 50
                        score = score + 50;
                        for (int j = 0; j < ghosts.size(); j++) {
                            ghosts.get(j).eatable();
                        }
                    }
                }
                for (int i = 0; i < fruits.size(); i++) { //eat fruits
                    Fruit fruit = fruits.get(i);
                    if (pacmanRect.intersects(fruit.boundingBox)) {
                        fruits.remove(fruit);
                        AudioPlayer.EATFRUIT.play();
                        score += fruit.points;
                    }
                }
                Rectangle pacmanRectGhost = pacman.boundingBox;
                for (int i = 0; i < ghosts.size(); i++) { //eat ghosts
                    Ghost ghost = ghosts.get(i);
                    if (pacmanRectGhost.intersects(ghost.boundingBox)) {
                        if (ghost.isGhostEatable() == true) {
                            ghost.eat();
                            if (this.numOfGhostsEaten() == 1) {
                                score += 200;
                            } else if (this.numOfGhostsEaten() == 2) {
                                score += 400;
                            } else if (this.numOfGhostsEaten() == 3) {
                                score += 800;
                            } else if (this.numOfGhostsEaten() == 4) {
                                score += 1600;
                            }
                        } else if (ghost.isGhostEaten() == true) {
                            // do nothing
                        } else {
                            pacman.die();
                            for (int j = 0; j < ghosts.size(); j++) {
                                ghosts.get(j).reset();
                            }
                            AudioPlayer.DEATH.play();
                            numLives--;
                            isGlobal = true;
                            globalCount = 0;
                        }
                    }
                }
            }
            else{
                if(ghostDeadTimer < 1000){
                    ghostDeadTimer+=dt;
                }
                else{
                    ghostDeadTimer = 0;
                    ghostDead = false;
                    for (int i = 0; i < ghosts.size(); i++) {
                        Ghost ghost = ghosts.get(i);
                        ghost.justAte = false;
                    }
                }
            }
        }
        else {
            pacman.update(dt);
        }
    }

    public void draw(Graphics2D g) {
        //Draw background
        g.setColor(Color.black);
        g.fillRect(0, 0, 336, 492);
        //Draw blocks
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            block.draw(g);
        }
        //Draw dots
        for (int i = 0; i < dots.size(); i++) {
            Dot dot = dots.get(i);
            dot.draw(g);
        }
        for (int i = 0; i < sdots.size(); i++) {
            SuperDot sdot = sdots.get(i);
            sdot.draw(g);
        }
        //Draw number of lives
        GameObject lives = new GameObject() {
            public void draw(Graphics2D g) {
                int lives = spriteI;
                for (int i = lives; i > 0; i--) {
                    this.drawSprite(g, 24, 0, 11, 10 + ((i - 1) * 25), 410);
                }
            }
        };
        lives.spriteI = numLives;
        lives.draw(g);
        //Draw level
        GameObject lvl = new GameObject() {
            public void draw(Graphics2D g) {
                int offSet = 0;
                int lvl = spriteI;
                for (int i = lvl; i > 0; i--) {
                    this.drawSprite(g, 24, i - 1, 5, 312 - offSet, 410);
                    offSet += 24;
                }
            }
        };
        lvl.spriteI = level;
        lvl.draw(g);
        //Draw gameover
        if (numLives <= 0) {
            Symbols gameover = new Symbols("game over", 112, 240);
            gameover.draw(g);
        }
        //Draw score as word
        Symbols scoreWordSymbols = new Symbols("score", 2, 2);
        scoreWordSymbols.alignment = Symbols.Alignment.LEFT_JUSTIFIED;
        scoreWordSymbols.draw(g);
        //Draw highscore
        Symbols scoreWordSymbols2 = new Symbols("highscore", 336, 2);
        scoreWordSymbols2.alignment = Symbols.Alignment.RIGHT_JUSTIFIED;
        scoreWordSymbols2.draw(g);
        //Draw score
        String scoreString = Integer.toString(score);
        if (score == 0) {
            scoreString = "00";
        }
        Symbols scoreNumberSymbols = new Symbols(scoreString, 2, 16);
        scoreNumberSymbols.draw(g);
        //Draw HighScore
        String HSstr = Integer.toString(highscore);
        if (highscore == 0) {
            HSstr = "00";
        }
        Symbols scoreNumberSymbols2 = new Symbols(HSstr, 336, 16);
        scoreNumberSymbols2.alignment = Symbols.Alignment.RIGHT_JUSTIFIED;
        scoreNumberSymbols2.draw(g);
        //Draw pause
        if (paused) {
            Symbols pauseSymbols = new Symbols("paused", 130, 20);
            pauseSymbols.alignment = Symbols.Alignment.LEFT_JUSTIFIED;
            pauseSymbols.draw(g);
        }
        //DEBUG
        if (PacmanGame.DEBUG) {
            g.setColor(Color.white); //DEMO...DELETE LATER
            g.drawLine(0, y, PacmanGame.WIDTH, y); //DEMO...DELETE LATER
        }
        //Fruit draws
        if (!fruits.isEmpty()) {
            for (int i = 0; i < fruits.size(); i++) {
                Fruit fruit = fruits.get(i);
                fruit.draw(g);
            }
        }

        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).draw(g);
        }
        if (!ghostDead) {
            pacman.draw(g);
        }
    }

    public void death (){
        numLives--;
    }

    public void keyPressed(KeyEvent e) {
        pacman.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public boolean isLocationFree(Rectangle r) {
        boolean free = true;
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (r.intersects(block.boundingBox)) free = false;
        }
        return free;
    }

    public boolean isLocationFree(Rectangle r, boolean isGhostReturning) {
        boolean free = true;
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (r.intersects(block.boundingBox)) {
                if(isGhostReturning == true && block.isGhostAccessible() == true) {
                    free = true;
                } else {
                    free = false;
                }
            }
        }
        return free;
    }

    public int getScore() {
        return score;
    }

    public int getGlobalCount() {
        return globalCount;
    }

    public int getDotTimer() {
        return dotTimer;
    }

    public void setDotTimer(int timer) {
        dotTimer = timer;
    }

    public int getPacmanX() {
        return pacman.getCoordinateX();
    }

    public int getPacmanY() {
        return pacman.getCoordinateY();
    }

    public int getPacmanVelX() {
        return pacman.getVelX();
    }

    public int getPacmanVelY() {
        return pacman.getVelY();
    }

    public int getPinkX() {
        for (int i = 0; i < ghosts.size(); i++) {
            if(ghosts.get(i).getColor().equals("pink")) {
                return ghosts.get(i).getCoordinateX();
            }
        }
        return 0;
    }

    public int getPinkY() {
        for (int i = 0; i < ghosts.size(); i++) {
            if(ghosts.get(i).getColor().equals("pink")) {
                return ghosts.get(i).getCoordinateY();
            }
        }
        return 0;
    }

    public int numOfGhostsInBox() {
        int count = 0;
        for (int i = 0; i < ghosts.size(); i++) {
            if(ghosts.get(i).getBoxStatus()) {
                count++;
            }
        }
        return count;
    }

    public int numOfGhostsEaten() {
        int count = 0;
        for (int i = 0; i < ghosts.size(); i++) {
            if(ghosts.get(i).wasGhostEaten()) {
                count++;
            }
        }
        return count;
    }
}

