package GameData;

import Geom.Geom_element;
import Geom.Point3D;

public class Packman {
	private timeData time;
	private Point3D point;
	private double speed;
	private double radius;
	private int ID;

	public Packman(String id, String lat ,String lon , String alt, String sp ,String rad) {
		time = new timeData();
		point = new Point3D(lat,lon,alt);
		this.setID(id);
		this.setRadius(rad);
		this.setSpeed(sp);
	}
	public String GetTime() {
		return time.getTime();
	}

	public Geom_element getGeom() {
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

	private void setSpeed(String sp) {
		speed = Double.parseDouble(sp);
	}

	private void setRadius(String rad) {
		radius = Double.parseDouble(rad);
	}

	private void setID(String id) {
		ID = Integer.parseInt(id);
	}

	public void translate(Point3D vec) {
		// TODO check how to convert pixel to coords.
	}

	public String toString() {
		return "Packman info [ "+"Point: "+this.getGeom()+" ID:"+this.getID()+" Speed:"+this.getSpeed()+" Radius:"+this.getRadius()+"]";
	}

}
