package Algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GameData.Fruit;
import GameData.Pacman;
import Geom.Point3D;

public class ShortestPathAlgoThread extends Thread{
	Pacman pac;

	public ShortestPathAlgoThread (Pacman pac) {
		this.pac=pac;
	}
	public void run() {
		Iterator<PathNode> pathIter = pac.getPath().iterator();
		while(pathIter.hasNext()) { //run on the path arraylist of every pacman
			PathNode fru=pathIter.next();
			fullGamePath(pac, fru.getFruit()); //call the function
			pac.getPath().remove(fru);
			pathIter = pac.getPath().iterator();

		}
	}
	public synchronized void fullGamePath (Pacman pac, Fruit fru) { //this function take the path of every pacman and move the pucman to every fruit on uts path.
		double x,y,z; 
		MyCoords coords=new MyCoords();
		double runTime=pac.getPath().get(0).getRunTime(); //runTime
		double dist=coords.distance3d(pac.getPoint(),fru.getPoint()); //distance between the pacman and each fruit.
		Point3D midvec=coords.vector3D(pac.getPoint(),fru.getPoint()); //the vector between pacman and fruit.
		if(runTime>0) { //check if the runTime is valid.  
			x=midvec.x()/runTime; //Divide the vector lat, lon, and alt distance by the runTime  to make a new distance unit.
			y=midvec.y()/runTime; 
			z=midvec.z()/runTime; 
			while(dist>=pac.getRadius()) { //check if the distance is still bigger then the radius.
				midvec=new Point3D(x,y,z); //this new vector is the pacman next step on the way to the fruit.
				pac.setPoint(coords.add(pac.getPoint(), midvec)); //we move the pacman to the vector coords after convert its to a GPS point.
				runTime=runTime-1;//runtime -1 because we just make progress by one 1 second
				midvec=coords.vector3D(pac.getPoint(), fru.getPoint()); //make new vector
				x=midvec.x()/runTime; //Divide the vector lat, lon, and alt distance by the runTime  to make a new distance unit.
				y=midvec.y()/runTime;
				z=midvec.z()/runTime;
				dist=coords.distance3d(pac.getPoint(), fru.getPoint()); //take the new distance
				System.out.println("pacman: "+pac.getID()+" fruit: "+fru.getID());
			}
		}
		pac.addScore(pac.getPath().get(0).getFruit().getWeight()); //the pacman arrived to the fruit and update its score
	}

}
