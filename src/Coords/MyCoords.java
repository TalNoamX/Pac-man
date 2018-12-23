package Coords;

import Geom.Point3D;
/**
 * A class that implements coords_conveter.
 * @author Oranit
 * @author Tal
 */
public class MyCoords implements coords_converter{
	private static final double EarthR = 6371000;

	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters).
	 * @param gps the point in degrees.
	 * @param vector the point in vectors.
	 * param lonNorm - the norma longtitude.
	 * @return the new point.
	 */
	@Override
	public Point3D add(Point3D gps, Point3D vector) {
		double y,x,z,lonNorm;
		lonNorm = Math.cos((gps.x()*Math.PI)/180);
		x = gps.x()+Math.toDegrees(Math.asin(vector.x()/EarthR));
		y = gps.y()+Math.toDegrees(Math.asin(vector.y()/(EarthR*lonNorm)));
		z = gps.z()+vector.z();
		Point3D gps2 = new Point3D(x,y,z);
		return gps2;
	}
	
	/**
	 * computes the 3D distance (in meters) between the two gps like points, 
	 * using the vector3D function. 
	 * @param gps 0 gps 1 - the two points.
	 * @return the distance.
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D vectorDis = new Point3D(this.vector3D(gps0, gps1));
		return Math.sqrt(vectorDis.x()*vectorDis.x()+vectorDis.y()*vectorDis.y());
	}
	
	/**
	 *  computes the 3D vector (in meters) between two gps like points.
	 *  @param gps 0, gps1 - the two gps points.
	 *  param lonNorm - the norma longtitude.
	 *  param x/y/xMeter - the point values in meters.
	 *  @return the vector.
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double xMeter,yMeter,zMeter,lonNorm;
		lonNorm = Math.cos((gps0.x()*Math.PI)/180);
		xMeter = Math.sin(Math.toRadians(gps1.x()-gps0.x()))*EarthR;
		yMeter = Math.sin(Math.toRadians(gps1.y()-gps0.y()))*EarthR*lonNorm;
		zMeter = gps1.z()-gps0.z();
		Point3D vector = new Point3D(xMeter,yMeter,zMeter); 
		return vector;
	}
	
	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1.
	 * for this function, we used the formulas in this website
	 * http://tchester.org/sgm/analysis/peaks/how_to_get_view_params.html
	 * @param gps0, gps 1 - the two points. 
	 * @return an array of asimuth, elevation, distcance (in this order).
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double dist = distance3d(gps0,gps1);
		double elevation = (180/Math.PI)*((gps1.z()-gps0.z())/dist-dist/(2*EarthR));
		double deltaD= gps1.y()-gps0.y();
		double asimuth = Math.toDegrees(Math.atan2(Math.sin(Math.toRadians(deltaD))*Math.cos(Math.toRadians(gps1.x())),
				(Math.cos(Math.toRadians(gps0.x()))*Math.sin(Math.toRadians(gps1.x())))-Math.sin(Math.toRadians(gps0.x()))*Math.cos(Math.toRadians(gps1.x()))*Math.cos(Math.toRadians(deltaD))));
		if (asimuth<0) asimuth+=360;
		double[] Polar = {asimuth,elevation,dist};
		return Polar;
	}
	
	/**
	 * @param p the point
	 * @return true if this point is a valid lat, lon , lat coordinate.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(-90>p.x() || p.x()>90) return false;
		if(-180>p.y() || p.y()>190) return false;
		if(-450>p.z()) return false;
		return true;
	}
}
