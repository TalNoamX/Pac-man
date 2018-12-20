package Algorithms;

import GameData.Fruit;
import GameData.timeData;

public class PathNode {
	private Fruit fruit; //keep a deep copy of the fruit actual 
	private int pacmanID; //Pacman ID
	private int fruitID; //Fruit ID
	private double runTime; //runTime is the distance between pacman and fruit divided by pacman speed. 
	private timeData time;
	
	public PathNode(Fruit fruit,int pacmanID,int fruitID,double runTime) { //constructor
		this.fruit = new Fruit(fruit);
		this.pacmanID=pacmanID;
		this.fruitID=fruitID;
		this.runTime=runTime;
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
		return time.getTime();
	}
	public void setTime() {
		time = new timeData();
	}
	
}