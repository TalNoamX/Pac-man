package Algorithms;

import java.util.Iterator;

import GameData.Game;
import GameData.Pacman;

public class ShortestPathAlgo {
	Game g; //change the name after you finish!!@@@
	
	public ShortestPathAlgo(Game game) {
		this.g=game;
	}
	
	public void ShortestPath() {
		Iterator<Pacman> itP = g.pList().iterator();
	}
}
