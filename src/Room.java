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
    long score = 0L;
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
        int[] word = {12,9,22,5,19};
        Word LivesDrawWord = new Word(word,265,415);
        LivesDrawWord.draw(g);
        //draw numLives on board
        int[] num = {numLives};
        Number LivesDrawNum = new Number(num,289,430);
        LivesDrawNum.draw(g);
        if(numLives <= 0){
            int[] word1 = {7,1,13,5};
            Word Game = new Word(word1,105,240);
            Game.draw(g);
            int[] word2 = {15,22,5,18};
            Word Over = new Word(word2,180,240);
            Over.draw(g);
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

