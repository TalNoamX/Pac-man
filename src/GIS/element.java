package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
/** 
 * A class that represents a line in the csv file
 * @author Tal
 * @author Oranit
 *
 */
public class element implements GIS_element {
	private Point3D Point;
	private metaData Data;
	private otherData other;
/**
 * A constructor, each parameter is a coloumn in the csv file
 * @param mac
 * @param ssid
 * @param authMode
 * @param ftSeen
 * @param channel
 * @param rssi
 * @param lat
 * @param lon
 * @param alt
 * @param accuMeters
 * @param type
 */
	public element(String mac, String ssid,String authMode, String ftSeen, String channel, String rssi, String lat, String lon, String alt, String accuMeters, String type) {
		Point = new Point3D(lat,lon,alt); // entering the coordinates to a new point
		Data = new metaData(ftSeen); // the time
		other = new otherData(mac,ssid,authMode,channel,rssi,accuMeters,type);// all the rest
	}
	
	/**
	 * getter for the element
	 */
	@Override
	public Geom_element getGeom() {
		return Point;
	}
	 
	/**
	 * getter for the time data
	 */
	@Override
	public Meta_data getData() {
		return Data;
	}
	/**
	 * getter for the rest of the data
	 * @return
	 */
	public otherData getOtherData() {
		return other;
	}

	/**
	 * Changes the point to a shifted point.
	 */
	@Override
	public void translate(Point3D vec) {
		MyCoords mC = new MyCoords();
		this.Point = mC.add(vec, this.Point);
	}

}
