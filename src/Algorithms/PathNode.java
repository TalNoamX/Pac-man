package Algorithms;

import GameData.Fruit;
import GameData.timeData;

/**
 * This class represent node for the path of every pacman.
 * It saves every detail the algorithm need to calculate the shortest path algorithm
 * @author Tal
 * @author Oranit
 * @author Amitai
 */
public class PathNode {
	private Fruit fruit; //keep a deep copy of the fruit actual 
	private int pacmanID; //Pacman ID
	private int fruitID; //Fruit ID
	private double runTime; //runTime is the distance between pacman and fruit divided by pacman speed. 
	private timeData eatenTime; // keep the time that the pacman eat this fruit.
	/**
	 * Constructor to create a node with the essential information
	 * @param fruit allow to have access to the fruit params.
	 * @param pacmanID thr pacman ID given
	 * @param fruitID the fruit ID given
	 * @param runTime the time it takes the pacman to eat the fruit
	 */
	public PathNode(Fruit fruit,int pacmanID,int fruitID,double runTime) { //constructor
		this.fruit = new Fruit(fruit);
		this.pacmanID=pacmanID;
		this.fruitID=fruitID;
		this.runTime=runTime;
		eatenTime = new timeData();
	}
	public Fruit getFruit() {
		return fruit;
	}

	public double getRunTime() {
		return runTime;
	}

	public int getPacmanID() {
		return pacmanID;
	}

	public int getFruitID() {
		return fruitID;
	}

	void setPacmanID(int pacmanID) { //the algorithm use this setter, that is why it is package friendly
		this.pacmanID = pacmanID;
	}

	void setRunTime(double runTime) {//the algorithm use this setter, that is why it is package friendly
		this.runTime = runTime;
	}
	
	public String toString() {
		return "{ Pacman ID: "+this.getPacmanID()+" friut ID: "+this.getFruitID()+" Time: "+this.getRunTime()+"}";
	}
	
	public String GetTime() {
		return eatenTime.getTime();
	}
	public void setTime() { // use when fruit is eaten by a pacman. (ShortestPathAlgoThread)
		eatenTime = new timeData();
	}
	
}