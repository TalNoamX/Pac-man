package Algorithms;

import java.util.Iterator;
import Coords.MyCoords;
import GameData.Fruit;
import GameData.Game;
import GameData.Pacman;
import Geom.Point3D;

public class ShortestPathAlgo {
	private Game game;
	private Path shortestPath;

	public ShortestPathAlgo(Game game) { //constructor
		this.game=game;
		this.pacmansPath(); // we want to split the run time of this algorithm so we call this function upon initialization.
	}
	
	public void Start() { //This function activate the shortest path. it take every pacman to it's fruit "physically".
		Iterator<Pacman> it=game.pList().iterator();
		while(it.hasNext()) {
			Pacman pac=it.next();
			Iterator<PathNode> looper = pac.getPath().iterator();
			int fruitIndex=0;
			while(looper.hasNext()) {
				PathNode fru=looper.next();
				fullGamePath(pac, fru.getFruit(),fruitIndex);
				fruitIndex++;
			}
		}
	}
	private void fullGamePath(Pacman pac, Fruit fru,int fruitIndex) {
		double x,y,z;
		MyCoords coords=new MyCoords();
		double runTime=pac.getPath().get(fruitIndex).getRunTime();
		double dist=coords.distance3d(pac.getPoint(),fru.getPoint());
		Point3D midvec=coords.vector3D(pac.getPoint(),fru.getPoint());
		if(runTime>0) {
			x=midvec.x()/runTime;
			y=midvec.y()/runTime;
			z=midvec.z()/runTime;
			while(dist>=pac.getRadius()) {
				midvec=new Point3D(x,y,z);
				pac.setPoint(coords.add(pac.getPoint(), midvec));
				runTime=runTime-1;
				midvec=coords.vector3D(pac.getPoint(), fru.getPoint());
				x=midvec.x()/runTime;
				y=midvec.y()/runTime;
				z=midvec.z()/runTime;
				dist=coords.distance3d(pac.getPoint(), fru.getPoint());
			}
		}
		pac.addScore(pac.getPath().get(fruitIndex).getFruit().getWeight());
	}
	private void pacmansPath() {
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
			game.pList().get(i).setPath(temp);
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