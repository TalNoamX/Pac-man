package GameData;

import Geom.Geom_element;
import Geom.Point3D;

public class Fruit  {
Point3D point;
double Weight;
int ID;
public Fruit(String id,String lat,String lon,String alt,String weight ) {
	point=new Point3D(lat,lon,alt);
	this.setWeight(weight);
	this.setID(id);
}

	public Geom_element getGeom() {
		return point;
	}

	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
	}
	public double grtweight() {
		return this.Weight;
	}
	public int getid() {
		return this.ID;
	}

	private void setID(String id) {
		ID=Integer.parseInt(id);
	}
	private void setWeight(String w) {
		Weight=Double.parseDouble(w);
	}

}
