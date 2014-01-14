package com.google.coderetreat.helloworld.main;

import java.util.HashSet;
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
		
		public Set<Coordinate> getNeighbors() {
			final Set<Coordinate> neighbors = new HashSet<>();
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
	
	private Set<Coordinate> nodes;
	
	public GameOfLife () {
		nodes = new HashSet<>();
	}
	
	public State getNodeState(int x, int y) {
		return nodes.contains(new Coordinate(x, y)) ? State.LIVE : State.DEAD;
	}
	
	public State getNodeState(Coordinate node) {
		return getNodeState(node.x, node.y);
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
		final Set<Coordinate> newNodes = new HashSet<>();

		for (Coordinate node : nodes) {
			tick(newNodes, node);	
		}
		
		this.nodes = newNodes;
	}
	
	public void tick(final Set<Coordinate> newNodes, Coordinate node) {
		Set<Coordinate> neighbors = node.getNeighbors();
		Set<Coordinate> liveNeighbors = findLiveNodes(neighbors);
		
		if (nodeLivesNext(node, liveNeighbors.size()))
			newNodes.add(node);
		else if (liveNeighbors.isEmpty())
			return;

		for (Coordinate neighbor : neighbors) {
			tick(newNodes, neighbor);
		}
	}
	
	private Set<Coordinate> findLiveNodes(Set<Coordinate> nodes) {
		Set<Coordinate> liveNodes = new HashSet<>();
		for (Coordinate node : nodes) {
			if (State.LIVE == getNodeState(node)) {
				liveNodes.add(node);
			}
		}
		
		return liveNodes;
	}

	public boolean nodeLivesNext(Coordinate node, int numNeighbors) {
		return State.LIVE == getNodeState(node.x, node.y) &&
				numNeighbors == 2 || numNeighbors == 3;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();
		
		game.setNodeState(-1, 0, State.LIVE);
		game.setNodeState(0, 0, State.LIVE);
		game.setNodeState(1, 0, State.LIVE);
		
		System.out.println(game);
		
		game.tick();
		
		System.out.println(game);
	}
}
