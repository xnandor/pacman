import static org.junit.Assert.*;

import org.junit.Test;


public class PacmanTest {

    //Eric A Bischoff's tests
    //////////////////////////////////////////////////
    @Test
    public void isAliveTest() {
		Pacman pacman = new Pacman();
		assertTrue("Pacman is alive: ",pacman.isAlive());
		pacman.kill();
		assertFalse("Pacman is dead:", pacman.isAlive());
	    }
	
    @Test
    public void isSuperTest() {
		Pacman pacman = new Pacman();
		assertFalse("Pacman is not super:", pacman.isSuperPacman());
		pacman.powerUp();
		assertTrue("Pacman is super:", pacman.isSuper);
    }
    //////////////////////////////////////////////////

    //Jared Griffith's tests//Can commit changes//
    //Was not able to be tested//
    @Test
    public void moveLeftTest() {
	Pacman pacman = new Pacman();
	int x = pacman.getX();
	pacman.moveLeft();
	assertTrue("Pacman moved left:", x > pacman.getX());
	assertFalse("Pacman did not move:", x <= pacman.getX());
    }

    @Test
    public void extraLifeTest() {
	Pacman pacman = new Pacman();
	Dot dot = new Dot();
	dot.consume();
	dot.incrementpoints();//Need to implement points counter in Dot class
	assertTrue("Pacman gets a life:", pacman.extralife() <= dot.incrementpoints());
	assertFalse("Pacman doesn't get a life:", pacman.extralife() > dot.incrementpoints());
    }
    /////////////////////////////////////////////////////////

    //Brian Hester's tests
    //////////////////////////////////////////////////
    @Test
    public void isGhostEatableTest() {
        Ghost ghost = new Ghost();
        assertFalse("Ghost is not eatable: ",ghost.isEatable());
        ghost.eatable();
        assertTrue("Ghost is eatable: ",ghost.isEatable());
    }
    
    @Test
    public void isOutOfLives() {
        Pacman pacman = new Pacman();
        assertFalse("Pacman has more lives: ",pacman.isOutOfLives());
        pacman.loseLife();
        pacman.loseLife();
        assertTrue("Pacman is out of lives: ",pacman.isOutOfLives());
    }
    //////////////////////////////////////////////////
	
    //Nathan Dolzonek's tests
    //////////////////////////////////////////////////
    @Test
    public void pacmanMovesUp() {
		Pacman pacman = new Pacman();
		int y = pacman.getY();
		pacman.moveUp();
		assertTrue("Pacman moved up: ", y < pacman.getY());
		assertFalse("Pacman failed to move up: ", y >= pacman.getY());
    }
	
    @Test
    public void doesDotExist() {
		Dot dot = new Dot(); //will have to implement class Dot (extends GameObject?)
		assertTrue("Dot exists: ", dot.doesExist());
		dot.consume();
		assertFalse("dot does not exist", dot.doesExist());	
    }
    //////////////////////////////////////////////////
}
