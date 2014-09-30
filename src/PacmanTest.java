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
}
