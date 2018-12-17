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

		String s ="C:\\Users\\טל\\Desktop\\game_1543684662657.csv" ;
		Game g = new Game(s);
		ShortestPathAlgo STA = new ShortestPathAlgo(g);
		STA.pacmansPath();
		Iterator<Pacman> it=g.pList().iterator();
		TempPackmanPath r=new TempPackmanPath();
		while(it.hasNext()) {
			Pacman temp=it.next();
			r.pathcalculation(temp,g.fList());
			System.out.println("hello");
		}

	}

	public void pathcalculation(Pacman pac,ArrayList<Fruit> arr) {
		ArrayList<Fruit> res=sortpath(pac, arr);
		fullgamepath(res, pac);
	}

	public void path2(Pacman pac, Fruit fru,int FruitIndex) {
		MyCoords coords=new MyCoords();
		double dis=pac.getPath().get(FruitIndex).getRunTime();
		Point3D midvec=coords.vector3D(pac.getPoint(),fru.getPoint());
		double x= midvec.x()/dis;
		double y=midvec.y()/dis;
		midvec = new Point3D(x,y);
		while(dis>0) {
			pac.setPoint(coords.add(pac.getPoint(), midvec));
			dis=dis-pac.getSpeed()-pac.getRadius();
			if(dis<pac.getRadius()) {
				break;
			}
			midvec=coords.vector3D(pac.getPoint(),fru.getPoint());
			x= midvec.x()/dis;
			y=midvec.y()/dis;
			midvec=new Point3D(x, y);
			System.out.println(pac.getPoint().toString());
		}


	}

	public void fullgamepath(ArrayList<Fruit> arr,Pacman pac) {
		Iterator<Fruit> it=arr.iterator();
		int FruitIndex=0;
		while(it.hasNext()) {
			Fruit fru=it.next();
			path2(pac, fru,FruitIndex);
			pac.setPoint(fru.getPoint());
			FruitIndex++;

		}

	}

	public ArrayList<Fruit> sortpath(Pacman pac,ArrayList<Fruit> arr) {
		ArrayList<Fruit> res=new ArrayList<Fruit>();
		Iterator<Fruit> it=arr.iterator();
		Iterator<PathNode> itnode=pac.getPath().iterator();
		while(itnode.hasNext()) {
			PathNode tempnode=itnode.next();
			while(it.hasNext()) {
				Fruit tempfru=it.next();
				if(tempnode.getFruitID()==tempfru.getID()) {
					res.add(tempfru);

				}
			}
			it=arr.iterator();
		}
		return res;
	}

}
