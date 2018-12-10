package GameData;

import Geom.Geom_element;
import Geom.Point3D;

public interface PackmanFruit {
 
	public  Geom_element getGeom();
	public int getID();
	public void translate(Point3D vec);
	public String toString();
}
