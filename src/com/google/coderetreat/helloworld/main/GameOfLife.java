package com.google.coderetreat.helloworld.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author samwoodard
 *
 */
public class GameOfLife {
	
	public static class Coordinate {
		public final int x, y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public List<Coordinate> getNeighbors() {
			final List<Coordinate> neighbors = new ArrayList<>();
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i == x && j == y) continue;
					neighbors.add(new Coordinate(i, j));
				}
			}
			return neighbors;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinate other = (Coordinate) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}		
	}

	public static enum State {
		DEAD,
		LIVE
	}
	
	private final Set<Coordinate> nodes;
	
	public GameOfLife () {
		nodes = new HashSet<>();
	}
	
	public State getNodeState(int x, int y) {
		return nodes.contains(new Coordinate(x, y)) ? State.LIVE : State.DEAD;
	}
	
	public void setNodeState(int x, int y, State state) {
		Coordinate node = new Coordinate(x, y);
		
		if (State.LIVE == state) {
			nodes.add(node);
		} else {
			nodes.remove(node);
		}
	}
	
	public void tick() {
		
	}
}
