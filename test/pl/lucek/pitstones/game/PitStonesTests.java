package pl.lucek.pitstones.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pl.lucek.pitstones.game.Game;
import pl.lucek.pitstones.game.Game.Player;

public class PitStonesTests {

	private Game g;

	@Before
	public void setUp() {
		g = new Game();
	}

	@Test
	public void newGamePitsSet() throws Exception {
		int[] expected = { 6, 6, 6, 6, 6, 6, 0 };
		assertArrayEquals(expected, g.firstPlayerPits);
		assertArrayEquals(expected, g.secondPlayerPits);
	}

	@Test
	public void randomSow() throws Exception {
		g.playerSow(Player.FIRST, 1);
		assertArrayEquals(new int[] { 7, 0, 7, 7, 7, 7, 1 }, g.firstPlayerPits);
		g.playerSow(Player.SECOND, 3);
		assertArrayEquals(new int[] { 7, 7, 7, 0, 7, 7, 1 }, g.secondPlayerPits);
	}

	@Test
	public void nextPlayerTurn() throws Exception {
		g.playerSow(Player.FIRST, 2);
		assertEquals(Player.SECOND, g.nextTurn);		
	}

	@Test
	public void anotherSow() throws Exception {
		g.playerSow(Player.FIRST, 0);
		assertEquals(Player.FIRST, g.nextTurn);
	}
	
	@Test
	public void captureStonesFromOpponent() throws Exception {
		g.firstPlayerPits = new int [] {2, 2, 0, 4, 5, 6, 7};
		g.secondPlayerPits = new int [] {1, 2, 3, 4, 5, 6, 7};
		g.playerSow(Player.FIRST, 0);
		assertArrayEquals(new int [] {0, 3, 5, 4, 5, 6, 7}, g.firstPlayerPits);
		assertArrayEquals(new int [] {1, 2, 3, 0, 5, 6, 7}, g.secondPlayerPits);
	}

}
