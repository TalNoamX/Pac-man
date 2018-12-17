package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GameData.Fruit;
import GameData.Game;
import GameData.Pacman;
import Geom.Point3D;

public class TempPackmanPath {

	public static void main(String[] args) {

		String s ="C:\\Users\\amitai\\Desktop\\game_1543684662657.csv" ;
		Game g = new Game(s);
		g.pList().get(0).setSpeed(2);
		ShortestPathAlgo STA = new ShortestPathAlgo(g);
		STA.pacmansPath();
		TempPackmanPath r=new TempPackmanPath();
		r.Start(g.pList());
	}

	public void Start(ArrayList<Pacman> pList) {
		Iterator<Pacman> it=pList.iterator();
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

	public void fullGamePath(Pacman pac, Fruit fru,int fruitIndex) {
		double x,y,z;
		MyCoords coords=new MyCoords();
		double runTime=pac.getPath().get(fruitIndex).getRunTime();
		double dist=coords.distance3d(pac.getPoint(),fru.getPoint());
		Point3D midvec=coords.vector3D(pac.getPoint(),fru.getPoint());
		System.out.println("pacman ID: "+pac.getID()+" fruit ID: "+fru.getID()+" distance: "+dist);
		if(runTime>0) {
			x=midvec.x()/runTime;
			y=midvec.y()/runTime;
			z=midvec.z()/runTime;

			while(dist>=pac.getRadius()) {
				System.out.println("pacman ID: "+pac.getID()+" fruit ID: "+fru.getID()+" distance: "+dist);
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
		System.out.println("pacman score: "+pac.getScore());
	}

}
