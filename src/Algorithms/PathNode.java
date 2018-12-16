package Algorithms;

public class PathNode {
	private int pacmanID; //pacman ID
	private int fruitID; //fruit ID
	private double runTime; //is the distance between pacman and fruit divided by pacman speed. 
	
	public PathNode(int pacmanID,int fruitID,double runTime) {
		this.pacmanID=pacmanID;
		this.fruitID=fruitID;
		this.runTime=runTime;
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