package Algorithms;

import GameData.Fruit;

public class PathNode {
	private Fruit fruit;
	private int pacmanID; //pacman ID
	private int fruitID; //fruit ID
	private double runTime; //is the distance between pacman and fruit divided by pacman speed. 
	
	public PathNode(Fruit fruit,int pacmanID,int fruitID,double runTime) {
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

	void setPacmanID(int pacmanID) {
		this.pacmanID = pacmanID;
	}

	void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	
	public String toString() {
		return "{ Pacman ID: "+this.getPacmanID()+" friut ID: "+this.getFruitID()+" Time: "+this.getRunTime()+"}";
	}
	
}