package GameData;

import Geom.Geom_element;
import Geom.Point3D;

public class Packman implements PackmanFruit {
	private Point3D point;
	private double speed;
	private double radius;
	private int ID;
	
	public Packman(String id, String sp ,String rad , String lat ,String lon , String alt) {
		point = new Point3D(lat,lon,alt);
		this.setID(id);
		this.setRadius(rad);
		this.setSpeed(sp);
	}

	@Override
	public Geom_element getGeom() {
		return point;
	}
	@Override
	public void translate(Point3D vec) {
		// TODO check how to convert pixel to coords.
	}
	@Override
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
	
}
