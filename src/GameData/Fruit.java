package GameData;

import Geom.Geom_element;
import Geom.Point3D;
/**
 * A class that represents the fruit on the map for tha packman to eat.
 * @author Oranit
 * @author Tal
 * @author Amitai
 * 
 */
public class Fruit implements PackmanFruit  {
	Point3D point;
	double Weight;
	int ID;
	
	/**
	 * A constructor that is build from Strings, to read from a CSV file.
	 * @param id  An ID
	 * @param lat  part of a coordinate
	 * @param lon part of a coordinate
	 * @param alt part of a coordinate
	 * @param weight The fruit weight for the calculation of the score 
	 */
	public Fruit(String id,String lat,String lon,String alt,String weight ) {
		point=new Point3D(lat,lon,alt);
		this.setWeight(weight);
		this.setID(id);
	}
	/**
	 * A getter
	 */
	@Override
	public Geom_element getGeom() {
		return point;
	}
	/**
	 * T
	 */
	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
	}
	public double getWeight() {
		return this.Weight;
	}
	@Override
	public int getID() {
		return this.ID;
	}

	private void setID(String id) {
		ID=Integer.parseInt(id);
	}
	private void setWeight(String w) {
		Weight=Double.parseDouble(w);
	}
	public String toString() {
		return "Fruit info - "+"Point: "+this.getGeom()+" ID:"+this.getID()+" Weight:"+this.getWeight();
	}

}
