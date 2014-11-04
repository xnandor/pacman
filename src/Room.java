import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Room implements KeyListener {

    ArrayList<GameObject> scene = new ArrayList<GameObject>();
    Pacman pacman;
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Dot> dots = new ArrayList<Dot>();
    int[] board = {};
    int numLives = 3;
    int score = 0;
    int y = 0; //for debug
    int frame = 0; //for debug

    public Room(int level) {
        pacman = new Pacman(this);
        //Block sprite theme information located in Block object)
        int[] board1 = {
            2, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 45, 44, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,  1,
            4,100,100,100,100,100,100,100,100,100,100,100,100, 27, 26,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 40,100, 41, 15, 15, 15, 40,100, 27, 26,100, 41, 15, 15, 15, 40,100, 41, 15, 15, 40,100,  3,
            4,100, 27,  0,  0, 26,100, 27,  0,  0,  0, 26,100, 27, 26,100, 27,  0,  0,  0, 26,100, 27,  0,  0, 26,100,  3,
            4,100, 43, 22, 22, 42,100, 43, 22, 22, 22, 42,100, 43, 42,100, 43, 22, 22, 22, 42,100, 43, 22, 22, 42,100,  3,
            4,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,  3,
            4,100, 41, 15, 15, 40,100, 41, 40,100, 41, 15, 15, 15, 15, 15, 15, 40,100, 41, 40,100, 41, 15, 15, 40,100,  3,
            4,100, 43, 22, 22, 42,100, 27, 26,100, 43, 22, 22, 37, 36, 22, 22, 42,100, 27, 26,100, 43, 22, 22, 42,100,  3,
            4,100,100,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100, 27, 26,100,100,100,100,100,100,  3,
            6, 13, 13, 13, 13, 40,100, 27, 38, 15, 15, 40,  0, 27, 26,  0, 41, 15, 15, 39, 26,100, 41, 13, 13, 13, 13,  5,
            0,  0,  0,  0,  0,  4,100, 27, 36, 22, 22, 42,  0, 43, 42,  0, 43, 22, 22, 37, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
            0,  0,  0,  0,  0,  4,100, 27, 26,  0, 31, 13, 35, -1, -1, 34, 13, 30,  0, 27, 26,100,  3,  0,  0,  0,  0,  0,
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
            4,100,100,100, 27, 26,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100, 27, 26,100,100,100,  3,
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
            }
        }
    }

    public void update(float dt) {
        pacman.update(dt);

        //CHECK FOR EATS
        Rectangle pacmanRect = pacman.boundingBox;
        for (int i = 0; i < dots.size(); i++) { //eat dots
            Dot dot = dots.get(i);
            if (pacmanRect.intersects(dot.boundingBox)) {
                dots.remove(i);
                //increments score by 10
                score = score + 10;
            }
        }
        //DEBUG
        if (PacmanGame.DEBUG) {
            y = (int)(100*Math.sin((double)this.frame/10)) + 200;
            frame++;
        }
    }

    public void draw(Graphics2D g) {
        //Draw Background
        g.setColor(Color.black);
        g.fillRect(0 , 0 , 336 , 492);
        //Draw Blocks
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            block.draw(g);
        }
        //Draw Dots
        for (int i = 0; i < dots.size(); i++) {
            Dot dot = dots.get(i);
            dot.draw(g);
        }
        //draw lives on board
        int[] wordI = {12,9,22,5,19};
        int[] wordJ = {2,2,2,2,2};
        Symbols LivesDrawWord = new Symbols(12,wordI,wordJ,265,415);
        LivesDrawWord.draw(g);
        //draw numLives on board
        if(numLives == 3){
            int[] numI = {2,2,2};
            int[] numJ = {11,11,11};
            Symbols LivesDrawNum = new Symbols(24,numI,numJ,262,430);
            LivesDrawNum.draw(g);
        }
        else if(numLives == 2){
            int[] numI = {2,2};
            int[] numJ = {11,11};
            Symbols LivesDrawNum = new Symbols(24,numI,numJ,262,430);
            LivesDrawNum.draw(g);
        }
        else if(numLives == 1){
            int[] numI = {2};
            int[] numJ = {11};
            Symbols LivesDrawNum = new Symbols(24,numI,numJ,262,430);
            LivesDrawNum.draw(g);
        }
        else {
            int[] numI = {11};
            int[] numJ = {11};
            Symbols LivesDrawNum = new Symbols(24,numI,numJ,262,430);
            LivesDrawNum.draw(g);
        }
        //draws Game Over on the board.
        if(numLives <= 0){
            int[] word1I = {7,1,13,5};
            int[] word1J = {2,2,2,2};
            Symbols Game = new Symbols(12,word1I,word1J,105,240);
            Game.draw(g);
            int[] word2I = {15,22,5,18};
            int[] word2J = {2,2,2,2};
            Symbols Over = new Symbols(12,word2I,word2J,180,240);
            Over.draw(g);
        }
        //draws the word score on board
        int[] scorew = {19,3,15,18,5};
        Word ScoreWord = new Word(scorew, 100, 2);
        ScoreWord.draw(g);
        //Converts the int score to a string
        //The array is then converted into an
        //array of integers
        String str = Integer.toString(score);
        int strLength = str.length();
        int[] intArray = new int[str.length()];
        for(int i=0; i < str.length(); i++){
            intArray[i] = str.charAt(i) - '0';
        }
        //For the length of the score string
        for(int k=0; k<strLength; k++){
            //Run this switch statement against the k
            //element in the integer array
            switch (intArray[k]){
                //If the case is zero(if intArray[k] = 0) fall into this statement
            case 0:
                //If k is equivalent to 0 (1st position of the score
                //) then draw the first element of the array
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                //If k is equivalent to 1(2nd position of the score)
                //then draw the second element of the array
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                //If k is equivalent to 2(3rd position of the score)
                //then draw the third element of the array
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                //If k is equivalent to 3(4th position of the score)
                //then draw the fourth element of the array
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
                //Each successive case works the same as the one above
            case 1:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 2:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 3:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 4:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 5:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 6:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 7:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 8:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            case 9:
                if(k==0){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,100,20);
                    ScoreDraw.draw(g);
                }
                if(k==1){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,120,20);
                    ScoreDraw.draw(g);
                }
                if(k==2){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,140,20);
                    ScoreDraw.draw(g);
                }
                if(k==3){
                    int[] scoreNum = {intArray[k]};
                    Number ScoreDraw = new Number(scoreNum,160,20);
                    ScoreDraw.draw(g);
                }
                break;
            default:
            }
        }
        //DEBUG
        if (PacmanGame.DEBUG) {
            g.setColor(Color.white); //DEMO...DELETE LATER
            g.drawLine(0, y, PacmanGame.WIDTH, y); //DEMO...DELETE LATER
        }
        pacman.draw(g);
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

}

