package GameData;

import Geom.Geom_element;
import Geom.Point3D;

public class Fruit implements PackmanFruit  {
	Point3D point;
	double Weight;
	int ID;
	
	
	public Fruit(String id,String lat,String lon,String alt,String weight ) {
		point=new Point3D(lat,lon,alt);
		this.setWeight(weight);
		this.setID(id);
	}
	@Override
	public Geom_element getGeom() {
		return point;
	}
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
