package GameData;

import Geom.Point3D;
/**
 * A class that represents a "fruit" in the pacman game.
 * @author Oranit
 * @author Tal
 * @author Amitai
 * 
 */
public class Fruit {
	private timeData time;
	private Point3D point;
	private double Weight;
	private int ID;

	/**
	 * A regular constructor.
	 * @param id  An ID for every fruit in the field
	 * @param lat is the x coordinate
	 * @param lon is the y coordinate
	 * @param alt is the z coordinate
	 * @param weight is the score, the pacman gets when he eat the fruit 
	 */
	public Fruit(int id,double lat,double lon,double alt,double wei ) {
		time = new timeData();
		point=new Point3D(lat,lon,alt);
		ID=id;
		Weight=wei;
	}
	/**
	 * A constructor for Strings parameters, taken from a CSV file.
	 */
	public Fruit(String id,String lat,String lon,String alt,String weight ) {
		time = new timeData();
		point=new Point3D(lat,lon,alt);
		this.setWeight(weight);
		this.setID(id);
	}
	/**
	 * Copy constructor.
	 * @param fruit the fruit you want to copy.
	 */
	public Fruit(Fruit fruit) {
		this.time = fruit.time;
		point=new Point3D(fruit.getPoint());
		Weight = fruit.getWeight();
		ID = fruit.getID();
	}
	/**
	 * Get a time. when we construct new class of this type it save the corrent time
	 * @return time stamp
	 */
	public String GetTime() {
		return time.getTime();
	}
	/**
	 * get fruit location
	 * @return GPS point
	 */
	public Point3D getPoint() {
		return point;
	}

	public double getWeight() {
		return this.Weight;
	}

	public int getID() {
		return this.ID;
	}

	private void setID(String id) {
		ID=Integer.parseInt(id);
	}

	private void setWeight(String w) {
		Weight=Double.parseDouble(w);
	}

	public void setPoint(Point3D p) {
		point = new Point3D(p);
	}

	public String toString() {
		return "Fruit info [ "+"Point: "+this.getPoint()+" ID:"+this.getID()+" Weight:"+this.getWeight()+"]";
	}

}
