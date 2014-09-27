import static org.junit.Assert.*;

import org.junit.Test;


public class PacmanTest {

	@Test
	public void isAliveTest() {
		Pacman pacman = new Pacman();
		assertTrue("Is pacman alive: ",pacman.isAlive());
	}

}
