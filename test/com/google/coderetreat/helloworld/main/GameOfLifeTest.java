/**
 * 
 */
package com.google.coderetreat.helloworld.main;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.coderetreat.helloworld.main.GameOfLife.Coordinate;
import com.google.coderetreat.helloworld.main.GameOfLife.State;

/**
 * @author samwoodard
 *
 */
public class GameOfLifeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNodeState() {
		GameOfLife game = new GameOfLife();
		assertEquals(State.DEAD, game.getNodeState(0, 0));
		game.setNodeState(0, 0, State.LIVE);
		assertEquals(State.LIVE, game.getNodeState(0, 0));
	}
	
	@Test
	public void testGetNeighbors() {
		Coordinate node = new Coordinate(0, 0);
		Set<Coordinate> expected = new HashSet<>(Arrays.asList(new Coordinate[] {
			new Coordinate(-1, -1),
			new Coordinate(0, -1),
			new Coordinate(1, -1),
			new Coordinate(1, 0),
			new Coordinate(1, 1),
			new Coordinate(0, 1),
			new Coordinate(-1, 1),
			new Coordinate(-1, 0)
		}));
		
		assertEquals(expected, new HashSet<>(node.getNeighbors()));;
	}
}
