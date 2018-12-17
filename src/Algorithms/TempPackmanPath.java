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
		ShortestPathAlgo STA = new ShortestPathAlgo(g);
		STA.pacmansPath();
		ArrayList<Pacman> arr=new ArrayList<>(g.pList());
		Iterator<Pacman> it=arr.iterator();
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

	public void path2(Pacman pac, Fruit fru) {
		MyCoords coords=new MyCoords();
		PathNode node=findefruitinpath(pac, fru);
		double dis=node.getRunTime();
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
		while(it.hasNext()) {
			Fruit fru=it.next();
			path2(pac, fru);
			pac.setPoint(fru.getPoint());

		}

	}

	public PathNode findefruitinpath(Pacman pac,Fruit fru) {
		PathNode node=new PathNode(0, 0, 0);
		Iterator<PathNode> it2=pac.getPath().iterator();
		boolean flag=false;
		while(it2.hasNext() && flag==false) {
			PathNode temp= it2.next();
			if(temp.getFruitID()==fru.getID())
				node=new PathNode(temp.getPacmanID(), temp.getFruitID(), temp.getRunTime());
		}
		return node;
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
