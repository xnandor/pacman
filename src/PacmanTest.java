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
