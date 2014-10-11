import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PacmanGame extends JFrame {

    GamePanel gamePanel = new GamePanel();
    public Graphics g;
    public boolean running = true;
    public long dt = 0L;
    public long timePreviousFrame = System.currentTimeMillis();
    public long timeCurrentFrame  = System.currentTimeMillis();
    public long timeStarted       = System.currentTimeMillis();
    public long timeInterval      = 40L; //25 fps
    public Room currentRoom;

    public PacmanGame() {
	this.initGame();
    }

    //Game Initialization - Place something here if you only want it to happen globally when the game is started.
    public void initGame() {
	JFrame frame = this;
	frame.setMinimumSize(new Dimension(280 , 400)); //Fixed resolution - Don't change, timing and graphics rely on this resolution.
	frame.setTitle("Pacman");
	g = frame.getContentPane().getGraphics();
	frame.getContentPane().add(gamePanel);
	currentRoom = new Room(1); //Start at level one
	gamePanel.room = currentRoom;
	frame.pack();
	frame.setVisible(true);
	while (running) {
	    gameLoop();
	}
    }

    public void gameLoop() {
	//CLOCK
	this.timeCurrentFrame = System.currentTimeMillis();
	this.dt = this.timeCurrentFrame - this.timePreviousFrame;
	this.timePreviousFrame = this.timeCurrentFrame;
	long timeComputationStart = System.currentTimeMillis();
	//UPDATE AND DRAW
	this.update(dt);
	this.repaint();
        //this.paintComponent(g);
	//SLEEP IF NEEDED
	try {
	    long timeComputationEnd = System.currentTimeMillis();
	    long timeComputationTaken = timeComputationEnd - timeComputationStart;
	    long timeToSleep = this.timeInterval - timeComputationTaken;
	} catch (Exception e) {
	    System.err.println("ERROR: Could not sleep main thread.");
	    e.printStackTrace();
	}
    } 

    //Game Release - Do before game closes
    public void releaseGame() {

    }

    //UPDATE LOOP
    public void update(long dt) {
	currentRoom.update(dt);
    }

    public class GamePanel extends JPanel {
	public Room room;
	//DRAW LOOP
	public void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D)g;
	    currentRoom.draw(g2);
	}
    }

    public static void main(String[] args) {
	PacmanGame game = new PacmanGame();
	System.out.println(game.getTitle());
    }

}
