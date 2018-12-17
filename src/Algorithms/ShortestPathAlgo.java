package Algorithms;

import GameData.Game;

public class ShortestPathAlgo {
	private Game game;
	private Path shortestPath;

	public ShortestPathAlgo(Game game) {
		this.game=game;
	}
	public void pacmansPath() {
		this.getTheShortestPath();
		for(int i=0;i<game.pList().size();i++) {
			int pacmanID=game.pList().get(i).getID();
			
			for(int k=0;k<shortestPath.size();k++) {
				int shortListPacID=shortestPath.get(k).getPacmanID();
				
				for(int j=0;j<game.pList().get(i).getPath().size();j++) {
					if(shortestPath.get(k).getFruitID() == game.pList().get(i).getPath().get(j).getFruitID()) {
						if(shortListPacID != pacmanID) {
							game.pList().get(i).getPath().remove(j);
						}
					}
				}
				
			}
			
		}

	}

	private Path getTheShortestPath() {
		for(int i=0;i<game.pList().size();i++) {
			Path temp = new Path(game.fList(),game.pList().get(i));
			game.pList().get(i).setPath(temp); //you need to continue each pacman path somewhere!!! maybe here maybe@@@@@@@@
			if(i==0) {
				shortestPath=new Path(temp);
			}
			else {
				for(int j=0;j<temp.size();j++) {					
					for(int k=0;k<shortestPath.size();k++) {
						if(temp.get(j).getFruitID() == shortestPath.get(k).getFruitID()) {
							if(temp.get(j).getRunTime()<shortestPath.get(k).getRunTime()) {
								shortestPath.get(k).setRunTime(temp.get(j).getRunTime());
								shortestPath.get(k).setPacmanID(temp.get(j).getPacmanID());
							}
						}
					}
				}
			}
		}
		return shortestPath;
	}
}
