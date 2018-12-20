package GameData;

import Algorithms.Path;
import Geom.Point3D;

public class Pacman {
	private Path shortPath;
	private timeData time;
	private Point3D point;
	private double speed;
	private double radius;
	private int ID;
	private int score;
	
	public Pacman(Pacman Pacman) {
		this.time=Pacman.time;
		this.point=new Point3D(Pacman.point);
		this.ID=Pacman.ID;
		this.speed=Pacman.speed;
		this.radius=Pacman.radius;
		this.score=Pacman.score;
	}
	public Pacman(int id, double lat ,double lon , double alt, double sp ,double rad) {
		time = new timeData();
		point = new Point3D(lat,lon,alt);
		ID=id;
		speed=sp;
		radius=rad;
		score = 0;
	}
	
	public Pacman(String id, String lat ,String lon , String alt, String sp ,String rad) {
		time = new timeData();
		point = new Point3D(lat,lon,alt);
		this.setID(id);
		this.setRadius(rad);
		this.setSpeed(sp);
		score = 0;
	}
	
	public void addScore(double n) {
		score += n;
	}
	
	public String GetTime() {
		return time.getTime();
	}

	public Point3D getPoint() {
		return point;
	}

	public int getID() {
		return ID;
	}
	
	public double getSpeed() {
		return speed;
	}

	public double getRadius() {
		return radius;
	}
	
	public Path getPath() {
		return shortPath;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setPoint(Point3D p) {
		point = new Point3D(p);
	}

	private void setSpeed(String sp) {
		speed = Double.parseDouble(sp);
	}

	private void setRadius(String rad) {
		radius = Double.parseDouble(rad);
	}

	private void setID(String id) {
		ID = Integer.parseInt(id);
	}
	
	public void setPath(Path p) {
		shortPath = new Path(p);
	}
	
	public void setSpeed(int s) {
		this.speed=s;
	}

	public String toString() {
		return "Pacman info [ "+"Point: "+this.getPoint()+" ID:"+this.getID()+" Speed:"+this.getSpeed()+" Radius:"+this.getRadius()+"]";
	}

}
