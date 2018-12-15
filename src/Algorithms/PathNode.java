package Algorithms;

public class PathNode {
	private int pacmanID;
	private int fruitID;
	private double runTime;
	
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
	
}