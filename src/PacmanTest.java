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
	public void moveLeftTest()
	{
	  Pacman pacman = new Pacman();
	  int x = pacman.getX();
	  pacman.moveLeft();
	  assertTrue("Pacman moved left:", x > pacman.getX());
	  assertFalse("Pacman did not move:", x <= pacman.getX());
		
	}
	
	
	@Test
	public void extraLifeTest()
	{
	   Pacman pacman = new Pacman();
	   Dot dot = new Dot();
	   dot.consume();
	   dot.incrementpoints();//Need to implement points counter in Dot class
	   assertTrue("Pacman gets a life:", pacman.extralife() <= dot.incrementpoints());
	   assertFalse("Pacman doesn't get a life:", pacman.extralife() > dot.incrementpoints());	      
		
	}
	/////////////////////////////////////////////////////////

}
